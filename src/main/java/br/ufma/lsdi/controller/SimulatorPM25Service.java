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
public class SimulatorPM25Service {

    public static void main(String[] args) {
        scheduleCarbono();
    }


    private static Calendar calendar = Calendar.getInstance();
        static Random random = new Random();

  private static int getRamdom(int range){
      return random.nextInt(range);
  }

    private static int geraRamdomNegativo(){
      int [] range = {0,-5,-4,-3,-2,-1,0,1,2,3,4,5};
      int val_ramdom = random.nextInt(11);
      return range[val_ramdom];
  }



    private static double media =0;
    private static int i =0;

    private static int val = getRamdom(10);
    @Scheduled(fixedRate = 1000)
    public static void scheduleCarbono() {

        if (val < 20){
            val = val + getRamdom(6);
        } else if(val > 350){
            val = val - getRamdom(30);
        }else{
            val = val + geraRamdomNegativo();
        }

        i=i+1;
        media = ((media + val) /2);
        //System.out.println(media);

        if (i == 12) {
            int indexScore = IndexUtil.calculateIndex(media, IndexUtil.CARBON_MONOXIDE_8H);
            System.out.println(IndexUtil.getIndexQuality(indexScore));

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



     private static int valPM25 = getRamdom(10);
    @Scheduled(fixedRate = 100)
    public static void scheduleNitrogenio() {

        Data data = new Data();
        List<CapabilityDataAuxiliar> listCapabilityDataAuxiliar = new ArrayList<>();
        CapabilityDataAuxiliar capabilityDataAuxiliar = new CapabilityDataAuxiliar();

        //CarbonMo carbonMonoxide = new CarbonMonoxide();

        if (val < 4){
            val = val +getRamdom(2);
        }else if (val > 30){
            val = val - getRamdom(5);
        } else if(val > 50){
            val = val - getRamdom(10);
        }else{
            val = val + geraRamdomNegativo();
        }


        //capabilityController.sendDataToCapability("545fd940-6906-4127-8be9-af2396dbd3aa","CarbonMonoxide",data);


        // int indexScore = IndexUtil.calculateIndexPM25_24H(4, IndexUtil.CARBON_MONOXIDE_8H);
        //System.out.println(indexScore);
    }


}
