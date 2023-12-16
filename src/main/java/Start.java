import DBHandlers.IDataLoader.Row;
import DBHandlers.PostgreSQLHandler;
import DBHandlers.H2Handler;
import Trees.Tree;
import Trees.TreeBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * <h2>Выполнение домашнего задания 2 (HW2).</h1>
 *
 * @author Сорокина Надежда, группа ЗБ-ПИ21-2.
 * <p><a href="https://github.com/not-fate/HW2">Удаленный репозиторий проекта на Github.</a>
 * @version 07.12.2023
 */

public class Start {
    public static void main(String[] args) throws Exception {
        List<Row> H2LoadedData = new H2Handler().load();
        List<Tree> TreesFromH2 = TreeBuilder.createListOfTrees(H2LoadedData);
        Files.writeString(Path.of("output.csv"), String.valueOf(sum(TreesFromH2)));
    }


    static public int sum(List<Tree> trees) {
        return trees.stream()
                .mapToInt(tree -> tree.getLeaves().size())
                .sum();
    }
}
