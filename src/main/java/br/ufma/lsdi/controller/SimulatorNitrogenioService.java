package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.CarbonMonoxide;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import util.IndexUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Service
public class SimulatorNitrogenioService {

    public static void main(String[] args) {
        scheduleCarbono();
    }


    private static Calendar calendar = Calendar.getInstance();
    static Random random = new Random();
    private static int getRamdom(int range){
        return random.nextInt(range);
    }

    private static int geraRamdomNegativo(){
        int [] range = {0,-40,-30,-20,-10,-5,0,10,20,30,40,50};
        int val_ramdom = random.nextInt(11);
        return range[val_ramdom];
    }



    private static double media =0;
    private static int i =0;

    private static int val = getRamdom(100);

    @Scheduled(fixedRate = 10)
    public static void scheduleCarbono() {
        if (val < 40) {
            val = val + getRamdom(7);

        }else if (val > 1400) {
            val = val - getRamdom(100);

        }else{
            val = val + geraRamdomNegativo();
        }

        i=i+1;
        media = ((media + val) /2);

        if (i == 12) {
            int indexScore = IndexUtil.calculateIndex(media, IndexUtil.NITROGEN_DIOXIDE_1H);
            System.out.println(IndexUtil.getIndexQuality(indexScore)+" - "+ media);

            Data data = new Data();
            List<CapabilityDataAuxiliar> listCapabilityDataAuxiliar = new ArrayList<>();
            CapabilityDataAuxiliar capabilityDataAuxiliar = new CapabilityDataAuxiliar();
            capabilityDataAuxiliar.setTimestamp(calendar.getTime().toString());
            capabilityDataAuxiliar.setLat(-2.481841);
            capabilityDataAuxiliar.setLon(-44.259712);
            capabilityDataAuxiliar.setValue(indexScore);
            listCapabilityDataAuxiliar.add(capabilityDataAuxiliar);
            data.setData(listCapabilityDataAuxiliar);

            i=0;
            media=0;
        }
        //System.out.println(indexScore);
    }





}
