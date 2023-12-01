import DBConnectors.H2Reader;
import DBConnectors.IReader.Row;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * <h2>Выполнение домашнего задания 2 (HW2).</h1>
 *
 * @author Сорокина Надежда, группа ЗБ-ПИ21-2.
 * <p><a href="https://www.github.com">Удаленный репозиторий проекта на Github.</a>
 * @version 30.11.2024
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<Row> H2LoadedData = new H2Reader("src/main/resources/db-configs/h2.properties").load();
        List<Tree> TreesFromH2 = TreeBuilder.createListOfTrees(H2LoadedData);
        Files.writeString(Path.of("output.csv"), String.valueOf(sum(TreesFromH2)));
    }

    static public int sum(List<Tree> trees) {
        return trees.stream()
                .mapToInt(tree -> tree.getLeaves().size())
                .sum();
    }
}
