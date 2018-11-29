package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.PollutionData;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.Data;
import br.ufma.lsdi.model.domain.auxiliar.ResourceDataAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Resource;
import br.ufma.lsdi.model.domain.interscity.ResourceCapability;
import br.ufma.lsdi.service.interscity.ResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.Util;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimulatorCarbonoService {

    private static List<PollutionData> instanciasCVS;
    private static Resource resource = new Resource();
    private static ResourceDataAuxiliar resourceDataAuxiliar;

    @Autowired
    private static ResourceClient resourceClient ;


    public static void main(String[] args) throws FileNotFoundException, ParseException {
        instanciasCVS = Util.lerInstanciasPollution("Madrid2001.csv");

        resourceDataAuxiliar = resourceClient.getResourceByUuid("70b8f4fe-3f17-4dcb-beff-cfb586fb344f");
        resource = resourceDataAuxiliar.getData();

        scheduleCarbono();
    }



    //@Scheduled(fixedRate = 3000)
    public static void scheduleCarbono() throws FileNotFoundException, ParseException {
        instanciasCVS = Util.lerInstanciasPollution("Madrid2001.csv");

        //resourceDataAuxiliar = resourceClient.getResourceByUuid("70b8f4fe-3f17-4dcb-beff-cfb586fb344f");
        List<CapabilityDataAuxiliar> listData = new ArrayList<>();
        CapabilityDataAuxiliar data = new CapabilityDataAuxiliar();
        //calculatedMean(instanciasCVS);
        for (PollutionData pollutionData : instanciasCVS) {
            if (pollutionData.getStation().equals("28079024")) {
                data.setValue(pollutionData.getNitrogenDioxideNO2());
                data.setTimestamp(pollutionData.getDate());
                data.setResource(resource);
                listData.add(data);
                data = new CapabilityDataAuxiliar();
            }
        }


    }


    /**
     * Atuliza ResourceCapability na base de dados passando o campo NOVO para FALSE
     *
     * @param resourceCapability
     * @throws Exception
     */
    private static void updateResourceCapability(ResourceCapability resourceCapability) throws Exception {
        try {
            // ResourceCapability capability = resourceCapabilityRepository.save(resourceCapability);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void sendData(Data data1, String uuid) throws Exception {

    }


}
