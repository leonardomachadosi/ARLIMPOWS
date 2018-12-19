package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.Catalog;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.Data;
import br.ufma.lsdi.model.domain.auxiliar.ResourceAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Resource;
import br.ufma.lsdi.service.interscity.CapabilityClient;
import br.ufma.lsdi.service.interscity.ResourceClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.Util;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CapabiltyController {


    @Autowired
    private ResourceClient resourceClient;
    @Autowired
    private CapabilityClient capabilityClient;

    private List<Resource> resources;

    private List<String> uuids;
    private List<String> capabilities;
    private Catalog catalog;
    private ResourceAuxiliar resourceAuxiliar;


    @RequestMapping(value = "/capability/saveContextData/{arquivo}", method = RequestMethod.GET)
    public void saveDataContext(@PathVariable(value = "arquivo") String arquivo) {
        List<Resource> resources = carregarRecursosSensores();
        try {
            if (resources != null && !resources.isEmpty()) {
                List<String[]> linhas = Util.lerAquivoBalneabilidade(arquivo + ".csv");
                String uuid = "";
                Resource resource;
                for (String[] linha : linhas) {
                    switch (linha[6]) {
                        case "28079035":
                            uuid = "0ad0d474-9fd9-4718-8522-4586d04c8eea";
                            resource = getResource(resources, uuid);
                            if (resource != null) {
                                save(linha, resource);
                            }
                            break;
                        case "28079018":
                            uuid = "ec6cd49b-2375-428e-992b-a7de256ea4d0";
                            resource = getResource(resources, uuid);
                            if (resource != null) {
                                save(linha, resource);
                            }
                            break;
                        case "28079024":
                            uuid = "fdd1b608-0ed4-4bcc-b231-0afeb1add98e";
                            resource = getResource(resources, uuid);
                            if (resource != null) {
                                save(linha, resource);
                            }
                            break;
                        case "28079038":
                            uuid = "d8718720-13e8-4260-af8f-e3e3cf4628d3";
                            resource = getResource(resources, uuid);
                            if (resource != null) {
                                save(linha, resource);
                            }
                            break;
                        case "28079050":
                            uuid = "ed6b5b77-839e-43c0-b978-b6f0c0e1ef0c";
                            resource = getResource(resources, uuid);
                            if (resource != null) {
                                save(linha, resource);
                            }
                            break;
                        case "28079017":
                            uuid = "311477c1-c653-4362-9cc2-87000ec3ea43";
                            resource = getResource(resources, uuid);
                            if (resource != null) {
                                save(linha, resource);
                            }
                            break;
                        case "28079036":
                            uuid = "f76c01bc-7f30-4037-a984-951e3bd10fe7";
                            resource = getResource(resources, uuid);
                            if (resource != null) {
                                save(linha, resource);
                            }
                            break;
                        case "28079040":
                            uuid = "c2669d0b-f1b8-44fc-8e5a-303932b8b3c0";
                            resource = getResource(resources, uuid);
                            if (resource != null) {
                                save(linha, resource);
                            }
                            break;
                    }

                }
            }


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

    private List<Resource> carregarRecursosSensores() {
        List<Resource> resources = new ArrayList<>();
        uuids = new ArrayList<>();
        resourceAuxiliar = resourceClient.getAllResourceSensor();
        if (resourceAuxiliar != null && !resourceAuxiliar.getResources().isEmpty()) {
            for (Resource re : resourceAuxiliar.getResources()) {
                if (re.getLat() != null) {
                    for (String cap : re.getCapabilities()) {
                        if (cap.equals("PM10")) {
                            if (!re.getUuid().equals("50f94544-0264-41dc-9521-3f7b74d949df")) {
                                resources.add(re);
                            }
                        }
                    }
                }
            }
        }
        return resources;
    }

    public Resource getResource(List<Resource> resources, String uuid) {
        for (Resource resource : resources) {
            if (resource.getUuid().equals(uuid)) {
                return resource;
            }
        }

        return null;
    }

    public void save(String[] linha, Resource resource) {
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