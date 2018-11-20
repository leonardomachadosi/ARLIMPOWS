package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.Data;
import br.ufma.lsdi.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import util.IndexUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class SimulatorCarbonoService {

    static Random random = new Random();
    private static double media = 0;
    private static int i = 0;
    private static int val = 0;
    private static int valorInicial = 0;
    private static int valorAnterior = 0;
    private static boolean iniciar = true;

    @Autowired
    private static ResourceRepository resourceRepository;

    public static void main(String[] args) {
        scheduleCarbono();
    }

    /**
     * Função para gerar um valor randômico
     *
     * @param range
     * @return inteiro
     */
    private static int getRamdom(int range) {
        return random.nextInt(range);
    }

    /**
     * Função para gerar um valor randômico negativo e positivo
     *
     * @return inteiro
     */
    private static int geraRamdomNegativo() {
        int[] range = {0, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        int vaRamdom = random.nextInt(11);
        return range[vaRamdom];
    }

    /**
     * Função para gerar um valor randômico inicial
     *
     * @return
     */
    private static void gerarValorInicial(){
        int[] range = new int[75];
        int aux =25;
        for (int i = 0; i < 75; i++ ){
            range[i] = aux;
            aux++;
        }
        valorInicial =  range[getRamdom(75)];
    }

    /**
     * Função para enviar dados simulados para a plataforma InterSCity
     */
    @Scheduled(fixedRate = 1000) //300000
    public static void scheduleCarbono() {
        if(iniciar){

        }

    }


    private static int valPM25 = getRamdom(10);

    @Scheduled(fixedRate = 100)
    public static void scheduleNitrogenio() {

        Data data = new Data();
        List<CapabilityDataAuxiliar> listCapabilityDataAuxiliar = new ArrayList<>();
        CapabilityDataAuxiliar capabilityDataAuxiliar = new CapabilityDataAuxiliar();

        //CarbonMo carbonMonoxide = new CarbonMonoxide();

        if (val < 4) {
            val = val + getRamdom(2);
        } else if (val > 30) {
            val = val - getRamdom(5);
        } else if (val > 50) {
            val = val - getRamdom(10);
        } else {
            val = val + geraRamdomNegativo();
        }


        //capabilityController.sendDataToCapability("545fd940-6906-4127-8be9-af2396dbd3aa","CarbonMonoxide",data);


        // int indexScore = IndexUtil.calculateIndexPM25_24H(4, IndexUtil.CARBON_MONOXIDE_8H);
        //System.out.println(indexScore);
    }


}
