package studio.inkode.serialize;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

/**
 * Created on 04.12.2018.
 *
 * @author Maxim Seredkin
 */
public class DefaultDeserializer implements Deserializer {
    private final Deserializer deserializer;

    public DefaultDeserializer() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        this.deserializer = new JacksonDeserializer(objectMapper);
    }

    @Override
    public <ObjectT> ObjectT deserialize(@NotNull("inpurt stream must be not null") InputStream inputStream,
                                         @NotNull("target object class must be not null") Class<ObjectT> objectClass) {
        return deserializer.deserialize(inputStream, objectClass);
    }
}
