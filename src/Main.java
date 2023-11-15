import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>(4,
                new BinaryTree<>(2,
                        new BinaryTree<>(1),
                        new BinaryTree<>(3)),
                new BinaryTree<>(6,
                        new BinaryTree<>(5),
                        new BinaryTree<>(7)));
//        BinaryTree<Integer> tree = new BinaryTree<>(3,
//                new BinaryTree<>(2,
//                        new BinaryTree<>(1)),
//                new BinaryTree<>(5,
//                        new BinaryTree<>(4)));
        System.out.println("Вставив элементы, получили такое дерево:");
        System.out.println(tree);
        System.out.println("postOrder:");
        for (AbstractBinaryTree<Integer> num: tree.postOrder()) {
            System.out.print(num.getKey() + " ");
        }
        System.out.println();
        System.out.println("preOrder:");
        for (AbstractBinaryTree<Integer> num: tree.preOrder()) {
            System.out.print(num.getKey() + " ");
        }
        System.out.println();
        System.out.println("asIndentedPreOrder:");
        System.out.print(tree.asIndentedPreOrder(0));
        System.out.println("inOrder: ");
        tree.printInOrder();
        System.out.println();
        System.out.println("bfs: ");
        tree.orderBfs();
        System.out.println();
        System.out.println("dfs: ");
        tree.orderDfs();
        System.out.println("\n");

        String sentence = "Я усталым таким ещё не был!";
        String[] words = sentence.split(" ");
        System.out.println("Индивидуальное задание!");
        System.out.println(Arrays.toString(words));
        BinaryTree<String> treeOfWords = BinaryTree.constructBinaryTree(words, 0, words.length-1);
        System.out.println(treeOfWords);
        System.out.println("Удаление слова усталым: ");
        treeOfWords.deleteSubTree("усталым");
        System.out.println(treeOfWords);
        System.out.println("Теперь предложение такое:");
        treeOfWords.printInOrder();
        System.out.println("\n");

        System.out.println("Бинарное дерево поиска!");
        BinarySearchTree<Integer> treeSearch = new BinarySearchTree<>();
        treeSearch.insert(4);
        treeSearch.insert(2);
        treeSearch.insert(1);
        treeSearch.insert(6);
        treeSearch.insert(3);
        treeSearch.insert(7);
        treeSearch.insert(5);
        System.out.println(treeSearch);
        System.out.println("Есть ли элемент 0?");
        System.out.println(treeSearch.contains(0));
        System.out.println("Есть ли элемент 5?");
        System.out.println(treeSearch.contains(5));
        System.out.println("Метод search:");
        System.out.println(treeSearch.search(6).toString());
    }
}