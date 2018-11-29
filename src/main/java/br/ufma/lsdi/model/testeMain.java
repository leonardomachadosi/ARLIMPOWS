package br.ufma.lsdi.model;

import br.ufma.lsdi.model.domain.PollutionData;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.ResourceDataAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Resource;
import br.ufma.lsdi.repository.ResourceCapabilityRepository;
import br.ufma.lsdi.service.interscity.CapabilityClient;
import br.ufma.lsdi.service.interscity.ResourceClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import util.IndexUtil;
import util.Util;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testeMain {
    private static List<PollutionData> instanciasCVS;
    private static Gson gson = new Gson();
    private static Resource resource = new Resource();
    private static ResourceDataAuxiliar resourceDataAuxiliar;

    @Autowired
    private static CapabilityClient capabilityClient;

    @Autowired
    private static ResourceClient resourceClient ;


    public static void main(String[] args) throws FileNotFoundException, ParseException {

    instanciasCVS = Util.lerInstanciasPollution("Madrid2001.csv");

        //resourceDataAuxiliar = resourceClient.getResourceByUuid("70b8f4fe-3f17-4dcb-beff-cfb586fb344f");
        List<CapabilityDataAuxiliar> listData = new ArrayList<>();
        CapabilityDataAuxiliar data = new CapabilityDataAuxiliar();
        //calculatedMean(instanciasCVS);
        for (PollutionData pollutionData : instanciasCVS) {
            if (pollutionData.getStation().equals("28079024")) {
              data.setValue(pollutionData.getNitrogenDioxideNO2());
              data.setTimestamp(pollutionData.getDate());
              //data.setResource(getResource());
              listData.add(data);
              data = new CapabilityDataAuxiliar();
        }
        }
        System.out.println(gson.toJson(listData));

    }

    private static Resource getResource(){
        resource.setId(5207);
        resource.setCreatedAt( "2018-11-28T00:39:07.470Z");
        resource.setUpdatedAt("2018-11-28T00:39:07.470Z");
        resource.setLat(-2.498837);
        resource.setLon(-44.311237);
        resource.setStatus("Ativo");
        resource.setDescription("ESTAÇÃO PRAIA PONTA DA AREA");
        resource.setUuid("70b8f4fe-3f17-4dcb-beff-cfb586fb344f");
        List<String> capabilities= Arrays.asList(
                "SULFURE_DIOXIDE",
                "NITROGEN_DIOXIDE",
                "OZONE",
                "PM10",
                "PM25"
        );
       // resource.setCapabilities(capabilities);
        return resource;
    }


    private static void printData(PollutionData pollutionData){
        System.out.println(pollutionData.getStation()+" - "+
                pollutionData.getNitrogenDioxideNO2()+" / "+
                pollutionData.getSulphurDioxideSO2()+" / "+
                pollutionData.getOzoneO3()+" / "+
                pollutionData.getParticlesPM10()+" / "+
                pollutionData.getParticlesPM25());
    }
/*
    private static void calculatedMean(List<PollutionData> instancias){
        Double mediaSO2 = 0.0, mediaO3 =0.0 ,mediaSNO2 = 0.0,mediaPM10 = 0.0,mediaPM25 = 0.0;
         int cont=0;
        for (PollutionData pollutionData : instancias){
            if (pollutionData.getDate().contains("2017-11-21 ")&& pollutionData.getStation().equals("28079004")) {
                cont++;
                if (pollutionData.getSulphurDioxideSO2() > 0.0){
                    mediaSO2 += pollutionData.getSulphurDioxideSO2();
                }
                if (pollutionData.getNitrogenDioxideNO2() > 0){
                    mediaSNO2 += pollutionData.getNitrogenDioxideNO2();
                }
                if (pollutionData.getOzoneO3() > 0){
                    mediaO3 += pollutionData.getOzoneO3();
                }
                if (pollutionData.getParticlesPM25() >0){
                    mediaPM25 += pollutionData.getParticlesPM25();
                }
                if (pollutionData.getParticlesPM10() >0){
                    mediaPM10 += pollutionData.getParticlesPM10();
                }
            }
        }
        System.out.println(IndexUtil.getIndexScore(mediaSO2/cont).get(0)+" - "+mediaSO2/cont+"\n"+
                IndexUtil.getIndexScore(mediaSNO2/cont).get(0)+" - "+mediaSNO2/cont+"\n"+
                IndexUtil.getIndexScore(mediaO3/cont).get(0)+" - "+mediaO3/cont+"\n"+
                IndexUtil.getIndexScore(mediaPM25/cont).get(0)+" - "+mediaPM25/cont+"\n"+
                IndexUtil.getIndexScore(mediaPM10/cont).get(0)+" - "+mediaPM10/cont);
    }*/
}
