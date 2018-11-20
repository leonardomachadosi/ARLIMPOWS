package br.ufma.lsdi.controller.task;

import br.ufma.lsdi.model.domain.interscity.Resource;
import br.ufma.lsdi.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TaskTest {

    static Random random = new Random();
    private static int val = 30;

    @Autowired
    private static ResourceRepository resourceRepository;

    private static List<Resource> resources;


    public static void main(String[] args) {
//        scheduleFixedRateTask();
    }

    private static int gerarRamdomNegativo() {

        int valRamdom = random.nextInt(2);
        int valRange5 = random.nextInt(5);

        if (valRamdom == 1) {
            return valRange5;
        } else {
            return valRange5 * (-1);
        }
    }

    private static int gerarRamdomRange() {
        return random.nextInt(10);
    }

    public void getRecursos() {

    }


    //    @Scheduled(fixedRate = 100)
    public static void generateCarbonValue() {

        resources = new ArrayList<>();
        resources = resourceRepository.findAll();

        if(!resources.isEmpty()){
            if (val < 20) {
                val = val + gerarRamdomRange();
            } else if (val > 210) {
                val = val - gerarRamdomRange();
            } else {
                val = val + gerarRamdomNegativo();
            }

        }

    }


}
