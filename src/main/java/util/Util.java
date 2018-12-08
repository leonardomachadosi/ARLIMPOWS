package util;


import br.ufma.lsdi.model.domain.PollutionData;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {


    private static Calendar calendar = Calendar.getInstance();
    public static String URL_BASE = "http://cidadesinteligentes.lsdi.ufma.br/";
    private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");// HH:mm:ss");

    public static List<PollutionData> lerInstanciasPollution(String arquivo) throws ParseException, FileNotFoundException {
        List<PollutionData> pollutionInstances = new ArrayList<>();
        InputStream inputStream = new FileInputStream(arquivo);
        CVSReader cvsReader = new CVSReader(inputStream);
        List<String[]> linhas = cvsReader.read();
        for (String[] linha : linhas) {
            PollutionData data = new PollutionData(linha[6],
                    linha[0]+".428Z",
                    parseDouble(linha[1]),
                    parseDouble(linha[5]),
                    parseDouble(linha[2]),
                    parseDouble(linha[4]),
                    parseDouble(linha[3]));
            pollutionInstances.add(data);
        }

        return pollutionInstances;
    }

    private static Double parseDouble(String valor) {
        Double result;
        Boolean teste = true;
        StringBuilder sb = new StringBuilder();
        String[] arrayValor =  valor.split("");
        if (valor.length()>10){
            for (int i =0; i<arrayValor.length; i++){
                if (teste){
                    sb.append(arrayValor[i]);

                    if (arrayValor[i].equals(".")){
                        sb.append(arrayValor[i+1]);
                        sb.append(arrayValor[i+2]);
                        teste = false;
                    }
                }

            }
            result =Double.parseDouble(sb.toString())+20;
            return result;
        }

        if (valor != null && !valor.equals("")) {
            result = Double.parseDouble(valor)+20;
            return result;
        } else {
            return 0.0;
        }
    }


    public static void gravarArquivo(String nomeArquivo, String dados) throws IOException {
        try{
            // Cria arquivo
            File file = new File(nomeArquivo);

            // Se o arquivo nao existir, ele gera
            if (!file.exists()) {
                file.createNewFile();
            }

            // Prepara para escrever no arquivo
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Escreve e fecha arquivo
            bw.write(dados);
            bw.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}