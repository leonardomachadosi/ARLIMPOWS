package br.ufma.lsdi.controller.task;

import br.ufma.lsdi.model.domain.CarbonMonoxide;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TaskTest {

    static Random random = new Random();
    private static int val = 30;

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

    //    @Scheduled(fixedRate = 100)
    public static void generateCarbonValue() {

        CarbonMonoxide carbonMonoxide = new CarbonMonoxide();

        if (val < 20) {
            val = val + gerarRamdomRange();
        } else if (val > 210) {
            val = val - gerarRamdomRange();
        } else {
            val = val + gerarRamdomNegativo();
        }



    }


}
