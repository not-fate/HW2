package Trees;

import DBHandlers.IDataLoader.Row;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Класс, реализующий построение списка деревьев.
 */
public class TreeBuilder {

    /**
     * Checked-исключение для ситуаций, когда невозможно построить список деревьев.
     */
    public static class InvalidTreeStructureException extends Exception {
        public InvalidTreeStructureException(String message) {
            super(message);
        }
    }

    /**
     * Строит список деревьев ({@link Tree}) на основе данных из <code>List<{@link Row}></code>.
     * @throws InvalidTreeStructureException при дублировании id узла;
     *                                       обнаружении узла, упоминаемого только в качестве родителя;
     *                                       обнаружении зацикливания.
     */
    public static ArrayList<Tree> createListOfTrees(List<Row> data) throws InvalidTreeStructureException {
        var nodesMap = new HashMap<Integer, Tree.Node>();
        for (Row row : data) {

            int id = row.id();
            int parentId = row.parentId();

            if (!nodesMap.containsKey(id))
                nodesMap.put(id, new Tree.Node(id));

            var node = nodesMap.get(id);
            if (node.getParentNode() != null)
                // id узла должно быть уникальным в пределах программы.
                throw new InvalidTreeStructureException("Дублирование id узла: " + id);


            if (parentId != id) {
                if (!nodesMap.containsKey(parentId))
                    nodesMap.put(parentId, new Tree.Node(parentId));
                nodesMap.get(parentId).addChild(node);
            } else node.setParentNode(node);
        }
        var trees = new ArrayList<Tree>();

        for (var item : nodesMap.values()) {
            checkLoop(item);
            if (item.isRoot())
                trees.add(new Tree(item));
        }

        for (var node : nodesMap.values())
            if (node.getParentNode() == null)
                throw new InvalidTreeStructureException("Узел " + node.getId() + " не создается явно " +
                        "и упоминается только в качестве родителя.");
        return trees;
    }

    /**
     * Т.к. дерево по определению является нецикличным графом, необходимо убедиться, что узлы не зацикливаются.
     * @throws InvalidTreeStructureException если обнаружена цикличность.
     */
    private static void checkLoop(Tree.Node node) throws InvalidTreeStructureException {
        var set = new HashSet<Integer>();
        while (!node.isRoot()) {
            if (set.contains(node.getId())) throw new InvalidTreeStructureException("Обнаружено зацикливание.");
            set.add(node.getId());
            node = node.getParentNode();
        }
    }
}




