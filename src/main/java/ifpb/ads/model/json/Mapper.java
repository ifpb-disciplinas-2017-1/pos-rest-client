package ifpb.ads.model.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/07/2017, 13:56:26
 */
public class Mapper {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            .configure(SerializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false);

    public String toString(Object obj) throws MapperException {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new MapperException(ex);
        }
    }

    public <T> T toObject(String json, Class<T> clazz) throws MapperException {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException ex) {
            throw new MapperException(ex);
        }
    }

    public <T> List<T> toList(String json) throws MapperException {
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (IOException ex) {
            throw new MapperException(ex);
        }
    }

    public <Z> List<Z> toList(String json, Class<Z> clazz) throws MapperException {
        try {
            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);
            return objectMapper.readValue(json, javaType);
        } catch (IOException ex) {
            throw new MapperException(ex);
        }
    }
}
