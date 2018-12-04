package br.ufma.lsdi.service;

import br.ufma.lsdi.model.domain.PollutionData;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Resource;
import org.springframework.stereotype.Service;
import util.IndexUtil;
import util.Util;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CapabiltyDataService {
    private List<PollutionData> instanciasCVS;
    private static Resource resource = new Resource();

    public List<CapabilityDataAuxiliar> getDataByCapability(Resource resource, String capability, String ano)  {
        try {
            instanciasCVS = Util.lerInstanciasPollution("C:\\madrid\\Madrid"+ano+".csv");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<CapabilityDataAuxiliar> listData = new ArrayList<>();

        for (PollutionData pollutionData : instanciasCVS) {
            if (pollutionData.getStation().equals("28079017")) {
                listData.add(montarData(resource, pollutionData, capability));
            }
        }
        return listData;
    }

    private CapabilityDataAuxiliar montarData(Resource resource, PollutionData pollutionData, String capability) {
        CapabilityDataAuxiliar data = new CapabilityDataAuxiliar();
        data.setTimestamp(pollutionData.getDate());
        //data.setResource(resource);
        if (capability.equals(IndexUtil.OZONE)) {
            data.setValue(pollutionData.getOzoneO3());
        } else if (capability.equals(capability.equals(IndexUtil.NITROGEN_DIOXIDE))) {
            data.setValue(pollutionData.getNitrogenDioxideNO2());
        } else if (capability.equals(IndexUtil.SULFURE_DIOXIDE)) {
            data.setValue(pollutionData.getSulphurDioxideSO2());
        } else if (capability.equals(IndexUtil.PM10)) {
            data.setValue(pollutionData.getParticlesPM10());
        } else {
            data.setValue(pollutionData.getParticlesPM25());
        }
        return data;
    }
}