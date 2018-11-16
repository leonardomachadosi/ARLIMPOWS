package util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by breno on 16/05/2017.
 */
public class FotoUtil {

    public static String servidor = "/home/SIISP";
    private static final String LINK_FOTO = "/foto-detento?foto=";
    private static final String IMAGEM_NAO_ENCONTRADA = "/resources/imagens/photo-not-found.png";

    private static String encoder(String path) {
        try {
            path = servidor + path;
            path = URLEncoder.encode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String getFoto(String path) {
        return StringUtils.isNotBlank(path) ? LINK_FOTO + encoder(path) : IMAGEM_NAO_ENCONTRADA;
    }

    public static byte[] downloadFTP(String diretorio) throws IOException {
        try {
            InputStream inputStream = new FileInputStream("" + diretorio);
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            return null;
        }
    }
}
