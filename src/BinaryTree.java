import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class BinaryTree<E extends Comparable<E>> implements AbstractBinaryTree<E> {

    private E key;
    private BinaryTree<E> leftChild;
    private BinaryTree<E> rightChild;

    public BinaryTree() {}

    public BinaryTree(E key) {
        this.key = key;
    }

    public BinaryTree(E key, BinaryTree<E> leftChild) {
        this.key = key;
        this.leftChild = leftChild;
    }

    public BinaryTree(E key, BinaryTree<E> leftChild, BinaryTree<E> rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public E getKey() {
        return key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        if (leftChild != null) {
            return leftChild;
        } else {
            throw new NullPointerException("Левое поддерево отсуствует!");
        }

    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        if(rightChild != null) {
            return rightChild;
        } else {
            throw new NullPointerException("Правое поддерево отсуствует!");
        }
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(key);
        buffer.append('\n');

        if (rightChild != null) {
            rightChild.print(buffer,  childrenPrefix +("├── "), "    ");
        }

        if (leftChild != null) {
            leftChild.print(buffer, childrenPrefix + ("└── "), "    ");
        }
    }

    @Override
    public String asIndentedPreOrder(int indentation) {
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, indentation).mapToObj(i -> " ").forEach(builder::append);
        builder.append(key).append("\n");
        if (leftChild != null) {
            builder.append(leftChild.asIndentedPreOrder(indentation + 2));
        }
        if (rightChild != null) {
            builder.append(rightChild.asIndentedPreOrder(indentation + 2));
        }

        return builder.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if(leftChild != null) {
            result.addAll(leftChild.preOrder());
        }
        if(rightChild != null) {
            result.addAll(rightChild.preOrder());
        }
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if(leftChild != null) {
            result.addAll(leftChild.inOrder());
        }
        result.add(this);
        if(rightChild != null) {
            result.addAll(rightChild.inOrder());
        }
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if(leftChild != null) {
            result.addAll(leftChild.postOrder());
        }
        if(rightChild != null) {
            result.addAll(rightChild.postOrder());
        }
        result.add(this);
        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if(leftChild != null) {
            leftChild.forEachInOrder(consumer);
        }
        consumer.accept(key);
        if(rightChild != null) {
            rightChild.forEachInOrder(consumer);
        }
    }

    public void printInOrder() {
        forEachInOrder(e -> System.out.print(e + " "));
    }

    public void orderBfs() {
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            BinaryTree<E> current = queue.poll();
            System.out.print(current.getKey() + " ");

            if (current.leftChild != null) {
                queue.add((BinaryTree<E>) current.getLeft());
            }

            if (current.rightChild != null) {
                queue.add((BinaryTree<E>) current.getRight());
            }
        }
    }

    public void orderDfs() {
        dfs(this);
    }

    private void dfs(BinaryTree<E> node) {

        if (node.leftChild != null) {
            dfs((BinaryTree<E>) node.getLeft());
        }

        if (node.rightChild != null) {
            dfs((BinaryTree<E>) node.getRight());
        }

        System.out.print(node.getKey() + " ");
    }

    @Override
    public void deleteSubTree(E key) {
        // Удаляем узел и всю его ветвь
        if(this.key.equals(key)) {
            this.key = null;
            this.leftChild = null;
            this.rightChild = null;
        } else {
            toDeleteSubTree(this, key);
        }
    }

    private BinaryTree<E> toDeleteSubTree(BinaryTree<E> current, E key) {
        if (current == null) {
            return current;
        }
        if(current.key.equals(key)) {
            current = null;
        } else {
            current.leftChild = toDeleteSubTree(current.leftChild, key);
            current.rightChild = toDeleteSubTree(current.rightChild, key);
        }
        return current;
    }

    public static BinaryTree<String> constructBinaryTree(String[] words, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (int) Math.round((start + end) / 2.0);
        BinaryTree<String> root = new BinaryTree<>(words[mid]);
        root.leftChild = constructBinaryTree(words, start, mid - 1);
        root.rightChild = constructBinaryTree(words, mid + 1, end);
        return root;
    }
}
