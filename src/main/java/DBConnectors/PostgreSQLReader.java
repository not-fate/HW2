package DBConnectors;
import java.io.IOException;

/**
 * <H2>Реализация DatabaseHandler для работы с PostgreSQL.</H2>
 */
public class PostgreSQLReader extends DatabaseHandler {
    public PostgreSQLReader(String config) throws IOException {
        super(config);
    }
}

