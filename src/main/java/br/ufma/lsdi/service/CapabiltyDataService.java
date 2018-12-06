package br.ufma.lsdi.service;

import br.ufma.lsdi.model.domain.PollutionData;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Resource;
import org.springframework.stereotype.Service;
import util.DateUtil;
import util.IndexUtil;
import util.Util;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

@Service
public class CapabiltyDataService {
    private List<PollutionData> instanciasCVS;

    public List<CapabilityDataAuxiliar> getDataByCapability(Resource resource, String capability, String ano) throws Exception {
        try {
            instanciasCVS = Util.lerInstanciasPollution("C:\\madrid\\Madrid" + ano + ".csv");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<CapabilityDataAuxiliar> listData = new ArrayList<>();

        for (PollutionData pollutionData : instanciasCVS) {
            if (pollutionData.getStation().equals("28079036")) {
                listData.add(montarData(resource, pollutionData, capability));
            }
        }


      /*  Collections.sort(listData, new Comparator<CapabilityDataAuxiliar>() {
            @Override
            public int compare(CapabilityDataAuxiliar o1, CapabilityDataAuxiliar o2) {
                if (o1.getMes() < o2.getMes()) {
                    return -1;
                }
                if (o1.getMes() > o2.getMes()) {
                    return 1;
                }
                return 0;
            }
        });*/

        return listData;
    }

    private CapabilityDataAuxiliar montarData(Resource resource, PollutionData pollutionData, String capability) {
        CapabilityDataAuxiliar dataContext = new CapabilityDataAuxiliar();
        dataContext.setTimestamp(pollutionData.getDate());
        dataContext.setResource(resource);
        //Date date= DateUtil.convertTimestampData(pollutionData.getDate());
        if (capability.equals(IndexUtil.OZONE)) {
            dataContext.setValue(pollutionData.getOzoneO3());
        } else if (capability.equals(capability.equals(IndexUtil.NITROGEN_DIOXIDE))) {
            dataContext.setValue(pollutionData.getNitrogenDioxideNO2());
        } else if (capability.equals(IndexUtil.SULFURE_DIOXIDE)) {
            dataContext.setValue(pollutionData.getSulphurDioxideSO2());
        } else if (capability.equals(IndexUtil.PM10)) {
            dataContext.setValue(pollutionData.getParticlesPM10());
        } else {
            dataContext.setValue(pollutionData.getParticlesPM25());
        }
        dataContext.setLat(resource.getLat());
        dataContext.setLon(resource.getLon());

        return dataContext;
    }
}