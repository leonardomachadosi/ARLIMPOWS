package br.ufma.lsdi.controller;

import br.ufma.lsdi.model.domain.auxiliar.Auxiliar;
import br.ufma.lsdi.model.domain.auxiliar.CapabilityDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.Data;
import br.ufma.lsdi.model.domain.enums.CapabilityEnum;
import br.ufma.lsdi.model.domain.interscity.ResourceCapability;
import br.ufma.lsdi.repository.ResourceCapabilityRepository;
import br.ufma.lsdi.service.interscity.CapabilityClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    private static List<Auxiliar> listaAnterior;

    @Autowired
    private static CapabilityClient capabilityClient;

    @Autowired
    private static ResourceCapabilityRepository resourceCapabilityRepository;

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
     * @return integer
     */
    private static int gerarValorInicial() {
        int[] range = new int[75];
        int aux = 25;
        for (int i = 0; i < 75; i++) {
            range[i] = aux;
            aux++;
        }
        return range[getRamdom(75)];
    }

    /**
     * Função para enviar dados simulados para a plataforma InterSCity
     */

    @Scheduled(fixedRate = 3000)
    public static void scheduleCarbono() {
        List<ResourceCapability> resourceCapabilities = new ArrayList<>();
        listaAnterior = new ArrayList<>();

        resourceCapabilities =
                resourceCapabilityRepository.findAllByCapacidadeId(CapabilityEnum.CARBOMO.getValue());

        if (!resourceCapabilities.isEmpty()) {

            for (ResourceCapability resourceCapability : resourceCapabilities) {


                if (iniciar) {
                    List<CapabilityDataAuxiliar> list = new ArrayList<>();
                    Data data = new Data();
                    Auxiliar auxiliar = new Auxiliar();
                    valorInicial = gerarValorInicial();
                    CapabilityDataAuxiliar capabilityDataAuxiliar =
                            new CapabilityDataAuxiliar(valorInicial,
                                    resourceCapability.getCapacidade().getLat(), resourceCapability.getCapacidade().getLon(),
                                    new Data().toString());
                    list.add(capabilityDataAuxiliar);
                    data.setData(list);
                    try {
                        sendData(data, resourceCapability.getRecurso().getUuid());
                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    auxiliar.setCapacidades(list);
                    auxiliar.setRecurso(resourceCapability.getRecurso());
                    listaAnterior.add(auxiliar);
                } else if (resourceCapability.getNovo() != null && resourceCapability.getNovo()) {
                    List<CapabilityDataAuxiliar> list = new ArrayList<>();
                    Data data = new Data();
                    Auxiliar auxiliar = new Auxiliar();
                    valorInicial = gerarValorInicial();
                    CapabilityDataAuxiliar capabilityDataAuxiliar =
                            new CapabilityDataAuxiliar(valorInicial,
                                    resourceCapability.getCapacidade().getLat(), resourceCapability.getCapacidade().getLon(),
                                    new Data().toString());
                    list.add(capabilityDataAuxiliar);
                    data.setData(list);
                    try {
                        sendData(data, resourceCapability.getRecurso().getUuid());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    auxiliar.setCapacidades(list);
                    auxiliar.setRecurso(resourceCapability.getRecurso());
                    listaAnterior.add(auxiliar);
                    resourceCapability.setNovo(Boolean.FALSE);
                    try {
                        updateResourceCapability(resourceCapability);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    valorAnterior = 0;
                    val = 0;
                    for (Auxiliar auxiliar : listaAnterior) {
                        if (auxiliar.getRecurso().getUuid().equals(resourceCapability.getRecurso().getUuid())) {
                            for (CapabilityDataAuxiliar capability : auxiliar.getCapacidades()) {
                                valorAnterior = capability.getValue();
                                if (valorAnterior < 20) {

                                    val = valorAnterior + getRamdom(10);

                                } else if (valorAnterior > 210) {
                                    val = valorAnterior - getRamdom(10);
                                } else {
                                    val = valorAnterior + geraRamdomNegativo();
                                }
                                List<CapabilityDataAuxiliar> list = new ArrayList<>();
                                Data data = new Data();
                                capability.setValue(val);
                                CapabilityDataAuxiliar capabilityDataAuxiliar = new CapabilityDataAuxiliar();
                                capabilityDataAuxiliar.setTimestamp(new Date().toString());
                                capabilityDataAuxiliar.setValue(val);
                                list.add(capabilityDataAuxiliar);
                                data.setData(list);
                                try {
                                    sendData(data, resourceCapability.getRecurso().getUuid());
                                    System.out.println();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                }


                iniciar = false;

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
            ResourceCapability capability = resourceCapabilityRepository.save(resourceCapability);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void sendData(Data data1, String uuid) throws Exception {

    }


}
