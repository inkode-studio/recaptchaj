package studio.inkode.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 04.12.2018.
 *
 * @author Maxim Seredkin
 */
public class JacksonDeserializer implements Deserializer {
    private final ObjectMapper objectMapper;

    public JacksonDeserializer(@NotNull("object mapper must be not null") ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <ObjectT> ObjectT deserialize(@NotNull("inpurt stream must be not null") InputStream inputStream,
                                         @NotNull("target object class must be not null") Class<ObjectT> objectClass) {
        try {
            return objectMapper.readValue(inputStream, objectClass);
        } catch (IOException exception) {
            return null;
        }
    }
}
