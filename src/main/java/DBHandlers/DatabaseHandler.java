package DBHandlers;
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
 * <p> Этот абстрактный класс предназначен для создания обработчиков, адаптированных к различным БД.
 *
 */
public abstract class DatabaseHandler implements IDataLoader {
    /**
     * Объект {@link Properties}, используемый для обработки конфигурации, содержащей данные для подключения к БД.
     */
    protected Properties config = new Properties();

    /**
     * Данные для подключения.
     */
    protected final String URL, USER, PASSWORD;

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

    /** Реализация интерфейса {@link IDataLoader}.
     * Извлекает данные из БД и возвращает список объектов {@link Row}.
     * <p> Метод считывает таблицу "TREES", после чего собирает результат в список.
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
