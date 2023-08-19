package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.JsonPlaceHolderPojo;

public class ObjectMapperUtils {

    //<T> T --> Herhangi bir veri tipini temsil eder
    //readValue methodu , birtinci parametrede aldigi string data'yi , ikinci parametrede belirttigimzi
    //data tipine cevirir.
    public static <T> T convertJsonToJava(String json, Class<T> cls){

        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
