package DBHandlers;

import java.sql.SQLException;
import java.util.List;


public interface IDataLoader {
    /**
     * <h3>Представление записи из таблицы TREES.</h3>
     * @param id уникальный идентификатор узла;
     * @param parentId уникальный идентификатор родительского узла.
     */
    record Row (int id, int parentId) {
    }
    List<Row> load() throws SQLException;
}
