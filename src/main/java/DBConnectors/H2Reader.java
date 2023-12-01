package DBConnectors;
import java.io.IOException;

/**
 * <H2>Реализация DatabaseHandler для работы с H2.</H2>
 */
public class H2Reader extends DatabaseHandler {
    public H2Reader(String config) throws IOException {
        super(config);
    }
}
