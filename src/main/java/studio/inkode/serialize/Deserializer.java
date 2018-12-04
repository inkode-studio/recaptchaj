package studio.inkode.serialize;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

/**
 * Created on 04.12.2018.
 *
 * @author Maxim Seredkin
 */
public interface Deserializer {
    <ObjectT> ObjectT deserialize(@NotNull("inpurt stream must be not null") InputStream inputStream,
                                  @NotNull("target object class must be not null") Class<ObjectT> objectClass);
}
