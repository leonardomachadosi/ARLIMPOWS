package util;


import br.ufma.lsdi.model.domain.Index;

public class IndexUtil {

    public static String PM25 ="PM25";
    public static String PM10 ="PM10";
    public static String OZONE_1H="OZONE_1H";
    public static String OZONE_8H ="OZONE_8H";
    public static String SULFURE_DIOXIDE_1H ="SULFURE_DIOXIDE_1H";
    public static String SULFURE_DIOXIDE_24H ="SULFURE_DIOXIDE_24H";
    public static String NITROGEN_DIOXIDE_1H ="NITROGEN_DIOXIDE_1H";
    public static String CARBON_MONOXIDE_8H ="CARBON_MONOXIDE_8H";

    public static int calculateIndex(double concetrationMean, String particula){
         Index index = new Index();

        if (particula.equals(PM25)){
            index = getIndexPM25();

        }else if (particula.equals(PM10)){
            index = getIndexPM10();

        }else if (particula.equals(OZONE_1H)){
            index = getIndexOzone_1H();

        }else if (particula.equals(OZONE_8H)){
            index = getIndexOzone_8H();

        }else if (particula.equals(SULFURE_DIOXIDE_1H)){
            index = getIndexSulfure_dioxide_1H();


        }else if (particula.equals(SULFURE_DIOXIDE_24H)){
            index = getIndexSulfure_dioxide_24H();


        }else if (particula.equals(NITROGEN_DIOXIDE_1H)){
            index = getIndexNitrogen_dioxide_1H();

        }else if (particula.equals(CARBON_MONOXIDE_8H)){
            index = getIndexCarbon_monoxide_8H();
        }
        return calculateIndex(getIndexQuality(concetrationMean,index),concetrationMean, index);
    }

    private static String getIndexQuality(Double particula, Index index){
        if (particula < index.getConcentrationGood()){
            return "good";
        }else if (particula < index.getConcentrationModerate() ) {
            return "moderate";
        }else if (particula <index.getSensitive()){
            return "sensitive";
        }else if(particula < index.getConcentrationUnhealthy()){
            return "unhealthy";
        }else if(particula < index.getConcentrationVeryUnhealthy()){
            return "very_unhealthy";
        }else {
            return "hazardous";
        }

    }

    public static String getIndexQuality(int indexScore){
        if (indexScore <= 50){
            return "good";
        }else if (indexScore >50 && indexScore <=100) {
            return "moderate";
        }else if (indexScore >100 && indexScore <=150){
            return "sensitive";
        }else if(indexScore >150 && indexScore <=200){
            return "unhealthy";
        }else if(indexScore >200 && indexScore <=300){
            return "very_unhealthy";
        }else {
            return "hazardous";
        }

    }

    private static int calculateIndex(String indexQuality, Double concetration, Index index){
        int indexScore =0;
        if (indexQuality.equals("good")){

            indexScore = (int) (((index.getGood() - 0)/
                        (index.getConcentrationGood() - 0)) *
                         (concetration - 0)) + 0;

        }else if (indexQuality.equals("moderate") ) {
            indexScore = (int) (((index.getModerate() - index.getGood()+1)/
                    (index.getConcentrationModerate() - index.getConcentrationGood()+1)) *
                    (concetration - index.getConcentrationGood()+1)) + index.getGood()+1;

        }else if (indexQuality.equals("sensitive")){
            indexScore = (int) (((index.getSensitive() - index.getModerate()+1)/
                    (index.getConcentrationSensitive() - index.getConcentrationModerate()+1)) *
                    (concetration - index.getConcentrationModerate()+1)) + index.getModerate()+1;

        }else if(indexQuality.equals("unhealthy")){
            indexScore = (int) (((index.getUnhealthy() - index.getSensitive()+1)/
                    (index.getConcentrationUnhealthy() - index.getConcentrationSensitive()+1)) *
                    (concetration - index.getConcentrationSensitive()+1)) + index.getSensitive()+1;

        }else if(indexQuality.equals("very_unhealthy")){
            indexScore = (int) (((index.getVeryUnhealthy() - index.getUnhealthy()+1)/
                    (index.getConcentrationVeryUnhealthy() - index.getConcentrationUnhealthy()+1)) *
                    (concetration - index.getConcentrationUnhealthy()+1)) + index.getUnhealthy()+1;

        }else if(indexQuality.equals("hazardous")){
            indexScore = (int) (((index.getHazardous() - index.getVeryUnhealthy()+1)/
                    (index.getConcentrationHazardous() - index.getConcentrationVeryUnhealthy()+1)) *
                    (concetration - index.getConcentrationVeryUnhealthy()+1)) + index.getVeryUnhealthy()+1;

        }

        return indexScore;
    }


    public static Index getIndexPM25(){
        Index indexQuality = new Index();
        indexQuality.setGood(50);
        indexQuality.setConcentrationGood(12.0);
        indexQuality.setModerate(100);
        indexQuality.setConcentrationModerate(35.4);
        indexQuality.setSensitive(150);
        indexQuality.setConcentrationSensitive(55.4);
        indexQuality.setUnhealthy(200);
        indexQuality.setConcentrationUnhealthy(150.4);
        indexQuality.setVeryUnhealthy(300);
        indexQuality.setConcentrationVeryUnhealthy(250.4);
        indexQuality.setHazardous(500);
        indexQuality.setConcentrationHazardous(500.4);

        return indexQuality;
    }

    public static Index getIndexOzone_1H(){
        Index indexQuality = new Index();
        indexQuality.setGood(100);
        indexQuality.setConcentrationGood(124.0);
        indexQuality.setModerate(150);
        indexQuality.setConcentrationModerate(164.0);
        indexQuality.setSensitive(200);
        indexQuality.setConcentrationSensitive(204.0);
        indexQuality.setUnhealthy(300);
        indexQuality.setConcentrationUnhealthy(404.0);
        indexQuality.setVeryUnhealthy(400);
        indexQuality.setConcentrationVeryUnhealthy(504.0);
        indexQuality.setHazardous(400);
        indexQuality.setConcentrationHazardous(604.0);

        return indexQuality;
    }

    public static Index getIndexPM10(){
        Index indexQuality = new Index();
        indexQuality.setGood(50);
        indexQuality.setConcentrationGood(54.0);
        indexQuality.setModerate(100);
        indexQuality.setConcentrationModerate(154.0);
        indexQuality.setSensitive(150);
        indexQuality.setConcentrationSensitive(254.0);
        indexQuality.setUnhealthy(200);
        indexQuality.setConcentrationUnhealthy(354.0);
        indexQuality.setVeryUnhealthy(300);
        indexQuality.setConcentrationVeryUnhealthy(424.0);
        indexQuality.setHazardous(500);
        indexQuality.setConcentrationHazardous(604.0);

        return indexQuality;
    }

    public static Index getIndexOzone_8H(){
        Index indexQuality = new Index();
        indexQuality.setGood(50);
        indexQuality.setConcentrationGood(54.0);
        indexQuality.setModerate(100);
        indexQuality.setConcentrationModerate(70.0);
        indexQuality.setSensitive(150);
        indexQuality.setConcentrationSensitive(85.0);
        indexQuality.setUnhealthy(200);
        indexQuality.setConcentrationUnhealthy(105.0);
        indexQuality.setVeryUnhealthy(300);
        indexQuality.setConcentrationVeryUnhealthy(200.0);

        return indexQuality;
    }


    public static Index getIndexSulfure_dioxide_1H(){
        Index indexQuality = new Index();
        indexQuality.setGood(50);
        indexQuality.setConcentrationGood(35.0);
        indexQuality.setModerate(100);
        indexQuality.setConcentrationModerate(75.0);
        indexQuality.setSensitive(150);
        indexQuality.setConcentrationSensitive(185.0);
        indexQuality.setUnhealthy(200);
        indexQuality.setConcentrationUnhealthy(304.0);

        return indexQuality;
    }

    public static Index getIndexSulfure_dioxide_24H(){
        Index indexQuality = new Index();
        indexQuality.setVeryUnhealthy(300);
        indexQuality.setConcentrationVeryUnhealthy(604.0);
       indexQuality.setHazardous(500);
        indexQuality.setConcentrationHazardous(1004.0);

        return indexQuality;
    }

    public static Index getIndexNitrogen_dioxide_1H(){
        Index indexQuality = new Index();
        indexQuality.setGood(50);
        indexQuality.setConcentrationGood(53.0);
        indexQuality.setModerate(100);
        indexQuality.setConcentrationModerate(100.0);
        indexQuality.setSensitive(150);
        indexQuality.setConcentrationSensitive(360.0);
        indexQuality.setUnhealthy(200);
        indexQuality.setConcentrationUnhealthy(649.0);
        indexQuality.setVeryUnhealthy(300);
        indexQuality.setConcentrationVeryUnhealthy(1249.0);
        indexQuality.setHazardous(500);
        indexQuality.setConcentrationHazardous(2049.0);

        return indexQuality;
    }

    public static Index getIndexCarbon_monoxide_8H(){
        Index indexQuality = new Index();
        indexQuality.setGood(50);
        indexQuality.setConcentrationGood(4.4);
        indexQuality.setModerate(100);
        indexQuality.setConcentrationModerate(9.4);
        indexQuality.setSensitive(150);
        indexQuality.setConcentrationSensitive(12.4);
        indexQuality.setUnhealthy(200);
        indexQuality.setConcentrationUnhealthy(15.4);
        indexQuality.setVeryUnhealthy(300);
        indexQuality.setConcentrationVeryUnhealthy(30.4);
        indexQuality.setHazardous(500);
        indexQuality.setConcentrationHazardous(50.4);

        return indexQuality;
    }

}
