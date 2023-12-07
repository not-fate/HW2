package DBConnectors;
import java.io.IOException;

/**
 * <H2>Реализация DatabaseHandler для работы с H2.</H2>
 */
public class H2Reader extends DatabaseHandler {
    public H2Reader() throws IOException {
        super("src/main/resources/db-configs/h2.properties");
    }
}
