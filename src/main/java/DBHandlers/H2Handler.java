package DBHandlers;
import java.io.IOException;

/**
 * <H2>Реализация DatabaseHandler для работы с H2.</H2>
 */
public class H2Handler extends DatabaseHandler {
    public H2Handler() throws IOException {
        super("/db-configs/h2.properties");
    }
}
