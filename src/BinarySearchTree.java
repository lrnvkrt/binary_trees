public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E>{
    private Node<E> root;

    public BinarySearchTree() {}

    private BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    @Override
    public void insert(E element) {
        if (root == null) {
            root = new Node<>(element);
        } else {
            toInsert(root, element);
        }
    }

    private void toInsert(Node<E> node, E element) {
        if (element.compareTo(node.value) < 0) {
            if (node.leftChild == null) {
                node.leftChild = new Node<>(element);
            } else {
                toInsert(node.leftChild, element);
            }
        } else {
            if (node.rightChild == null) {
                node.rightChild = new Node<>(element);
            } else {
                toInsert(node.rightChild, element);
            }
        }
    }


    @Override
    public boolean contains(E element) {
        if(root == null) {
            return false;
        }
        if (root.value == element) {
            return true;
        } else {
            return toContains(root, element);
        }
    }

    private boolean toContains(Node<E> node, E element) {
        if (element.compareTo(node.value) == 0) {
            return true;
        }
        if (element.compareTo(node.value) < 0) {
            if (node.leftChild == null) {
                return false;
            } else {
                return toContains(node.leftChild, element);
            }
        } else {
            if (node.rightChild == null) {
                return false;
            } else {
                return toContains(node.rightChild, element);
            }
        }
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        Node<E> node = searchRecursive(root, element);
        BinarySearchTree<E> newTree = new BinarySearchTree<>();

        if (node != null) {
            newTree.root = node;
        }

        return newTree;
    }

    private Node<E> searchRecursive(Node<E> current, E element) {
        if (current == null || element.equals(current.value)) {
            return current;
        }
        if (element.compareTo(current.value) < 0) {
            return searchRecursive(current.leftChild, element);
        } else {
            return searchRecursive(current.rightChild, element);
        }
    }

    @Override
    public Node<E> getRoot() {
        return root;
    }

    @Override
    public Node<E> getLeft() {
        if (root != null && root.leftChild != null) {
            return root.leftChild;
        } else {
            throw new NullPointerException("Левое поддерево отсуствует!");
        }
    }

    @Override
    public Node<E> getRight() {
        if (root != null && root.rightChild != null) {
            return root.rightChild;
        } else {
            throw new NullPointerException("Правое поддерево отсуствует!");
        }
    }

    @Override
    public E getValue() {
        if(root != null) {
            return root.value;
        } else {
            throw new NullPointerException("Значение узла равно null!");
        }
    }

    @Override
    public String toString() {
        if (this.root == null) {
            return "";
        } else {
            StringBuilder buffer = new StringBuilder(50);
            print(root, buffer, "", "");
            return buffer.toString();
        }
    }

    private void print(Node<E> node, StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(node.value);
        buffer.append('\n');

        if (node.rightChild != null) {
            print(node.rightChild, buffer,  childrenPrefix + ("├── "), childrenPrefix + "    ");
        }

        if (node.leftChild != null) {
            print(node.leftChild, buffer, childrenPrefix + ("└── "), childrenPrefix + "    ");
        }
    }
}
