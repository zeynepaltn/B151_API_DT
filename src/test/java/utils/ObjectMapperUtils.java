package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {
    // <T> T --> Herhangi bir veri tipini temsil eder. (Generic)
    // readValue() metodu, birinci parametrede aldığı String datayı, ikinci parametrede belirttiğimiz data tipine çevirir.
    public static <T> T convertJsonToJava(String json, Class<T> cls){//Generic method, istedigim class tipiyle bu  methodu kullanabilirim
        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}