package DBConnectors;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <h2><b>DatabaseHandler</b> инкапсулирует методы для
 * создания подключения и получения данных из БД.</h2>
 * <p> Он реализует интерфейс {@link IReader}.
 * <p> Этот абстрактный класс предназначен для создания обработчиков, адаптированных к различным системам баз данных.
 *
 */
public abstract class DatabaseHandler implements IReader {
    /**
     * Объект {@link Properties}, используемый для обработки конфигурации, содержащей данные для подключения к базе данных.
     */
    protected Properties config = new Properties();

    /**
     * URL для подключения к базе данных.
     */
    protected final String URL;

    /**
     * Имя пользователя для доступа к базе данных.
     */
    protected final String USER;

    /**
     * Пароль для доступа к базе данных.
     */
    protected final String PASSWORD;

    /**
     * Инициализирует DatabaseHandler, загружая детали подключения из значений переданного <i>properties</i>.
     *
     * @param path Путь к файлу конфигурации.
     */
    public DatabaseHandler(String path) throws IOException {
        config.load(new FileReader(path));
        URL = config.getProperty("URL");
        USER = config.getProperty("USER");
        PASSWORD = config.getProperty("PASSWORD");
    }

    /**
     * Устанавливает соединение с базой данных с использованием сохраненных деталей подключения.
     *
     * @return {@link Connection}, представляющий подключение к базе данных.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Извлекает данные из БД и возвращает список объектов {@link Row}.
     * <p> Метод считывает таблицу "TREES", после чего собирает результат в <code>ArrayList</code>.
     * Каждый элемент <code>ArrayList</code> содержит <code>record</code> {@link Row} с двумя полями типа <code>int</code>:
     * <p><b>— id</b> (уникальный идентификатор узла),</p>
     * <p><b>— parentID</b> (уникальный идентификатор родительского узла).</p>
     *
     * @return Список Row, содержащий извлеченные данные.
     */
    @Override
    public List<Row> load() throws SQLException {
        var result = new ArrayList<Row>();
        try (var dbConnection = getConnection()) {
            ResultSet resultSet = dbConnection.createStatement().executeQuery("SELECT id, parent_id FROM TREES");
            while (resultSet.next())
                result.add(new Row(resultSet.getInt("id"), resultSet.getInt("parent_id")));
        }
        return result;
    }
}
