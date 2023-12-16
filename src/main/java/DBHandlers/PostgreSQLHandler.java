package DBHandlers;
import java.io.IOException;

/**
 * <H2>Реализация DatabaseHandler для работы с PostgreSQL.</H2>
 */
public class PostgreSQLHandler extends DatabaseHandler {
    public PostgreSQLHandler() throws IOException {
        super("/db-configs/postgres.properties");
    }
}

