package br.ufma.lsdi.controller.task;

import br.ufma.lsdi.model.domain.Catalog;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.Data;
import br.ufma.lsdi.model.domain.auxiliar.ResourceAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Capability;
import br.ufma.lsdi.model.domain.interscity.Resource;
import br.ufma.lsdi.repository.ResourceRepository;
import br.ufma.lsdi.service.interscity.CapabilityClient;
import br.ufma.lsdi.service.interscity.ResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import util.Util;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

@Service
public class TaskTest {


    @Autowired
    private ResourceClient resourceClient;
    @Autowired
    private CapabilityClient capabilityClient;
    private List<Resource> resources;
    private int lastPosition = 0;
    private ResourceAuxiliar resourceAuxiliar;
    private List<String> uuids;
    private List<String> capabilities;
    private Catalog catalog;

    public TaskTest() {
        this.lastPosition = 0;
    }

    private List<Resource> carregarRecursosSensores() {
        List<Resource> resources = new ArrayList<>();
        uuids = new ArrayList<>();
        resourceAuxiliar = resourceClient.getAllResourceSensor();
        if (resourceAuxiliar != null && !resourceAuxiliar.getResources().isEmpty()) {
            for (Resource re : resourceAuxiliar.getResources()) {
                if (re.getLat() != null) {
                    for (String cap : re.getCapabilities()) {
                        if (cap.equals("PM10")) {
                            if (!re.getUuid().equals("907850e5-e6ef-4958-9e83-1e461a535355")) {
                                resources.add(re);
                            }
                        }
                    }
                }
            }
        }
        return resources;
    }


    @Scheduled(fixedRate = 3600000)
    public void senData() {
        carregarRecursosSensores();
        resources = carregarRecursosSensores();
        try {
            List<String> lista = Arrays
                    .asList("new/8b1b3926-ccc9-4a41-8d6b-3baff7c0f520.csv",
                            "new/34cbc83e-08d5-42fb-aadc-db714f31a669.csv",
                            "new/70b8f4fe-3f17-4dcb-beff-cfb586fb344f.csv",
                            "new/90a76c4c-6cd1-4d42-a2f3-d4a2c20dbeb9.csv",
                            "new/907850e5-e6ef-4958-9e83-1e461a535355.csv",
                            "new/d6d5972c-af0d-4a7a-8f9c-4fa7cf282003.csv",
                            "new/eae9ead0-9236-43b5-95a2-55933d0bf272.csv");
            for (String s : lista) {
                List<String[]> linhas = Util.lerAquivoBalneabilidade(s);
                String[] linha = new String[7];
                for (int i = 0; i < linhas.size(); i++) {
                    if (i == 0) {
                        linha = linhas.get(lastPosition);
                    } else if (i == lastPosition + 1) {
                        linha = linhas.get(lastPosition + 1);
                    }
                    if (linha[7] != null && !linha[7].isEmpty()) {
                        for (Resource resource : resources) {
                            if (linha[7].equals(resource.getUuid())) {
                                if (linha[1] != null && !linha[1].isEmpty()) {
                                    Data data = new Data();
                                    data.setData(gerarDadoContexto(resource, linha, 1));
                                    enviarDadoContexto(data, resource.getUuid(), Util.NITROGEN_DIOXIDE);
                                }
                                if (linha[2] != null && !linha[2].isEmpty()) {
                                    Data data = new Data();
                                    data.setData(gerarDadoContexto(resource, linha, 2));
                                    enviarDadoContexto(data, resource.getUuid(), Util.OZONE);
                                }
                                if (linha[3] != null && !linha[3].isEmpty()) {
                                    Data data = new Data();
                                    data.setData(gerarDadoContexto(resource, linha, 3));
                                    enviarDadoContexto(data, resource.getUuid(), Util.PM10);
                                }
                                if (linha[4] != null && !linha[4].isEmpty()) {
                                    Data data = new Data();
                                    data.setData(gerarDadoContexto(resource, linha, 4));
                                    enviarDadoContexto(data, resource.getUuid(), Util.PM25);
                                }
                                if (linha[5] != null && !linha[5].isEmpty()) {
                                    Data data = new Data();
                                    data.setData(gerarDadoContexto(resource, linha, 5));
                                    enviarDadoContexto(data, resource.getUuid(), Util.SULFURE_DIOXIDE);
                                }
                            }
                        }
                    }


                }
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        lastPosition++;
    }


    public List<CapabilityDataAuxiliar> gerarDadoContexto(Resource resource, String[] linha, int position) {
        CapabilityDataAuxiliar capabilityDataAuxiliar = new CapabilityDataAuxiliar();
        capabilityDataAuxiliar.setValue(Double.valueOf((String) linha[position]));
        capabilityDataAuxiliar.setResource(resource);
        capabilityDataAuxiliar.setLat(resource.getLat());
        capabilityDataAuxiliar.setLon(resource.getLon());
        capabilityDataAuxiliar.setTimestamp(linha[0]);
        List<CapabilityDataAuxiliar> capabilityDataAuxiliars = new ArrayList<>();
        capabilityDataAuxiliars.add(capabilityDataAuxiliar);
        return capabilityDataAuxiliars;
    }

    public void enviarDadoContexto(Data data, String uuid, String capability) {
        capabilityClient.saveCapabilityData(data, uuid, capability);
    }


}
