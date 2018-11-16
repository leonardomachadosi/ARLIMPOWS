package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by leole on 17/11/2017.
 */
public class JsonConverter {

    public static String toJson(Object entity) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(entity);
    }

    public static Object toObject(String json, Class objecClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, objecClass);
    }
}
