public class BinarySearchTree {
    static class Node {
        int data;
        Node left, right;
        Node(int item) {
            data = item;
            left = right = null;
        }
    }
    Node root;
    public BinarySearchTree() {
        root = null;
    }
    public void insert(int data) {
        root = insertRec(root, data);
    }
    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }
    public boolean search(int data) {
        return searchRec(root, data);
    }
    private boolean searchRec(Node root, int data) {

        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }

        if (data < root.data) {
            return searchRec(root.left, data);
        } else {
            return searchRec(root.right, data);
        }
    }
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node root, int data) {
        if (root == null) {
            return root;
        }
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.data = minValue(root.right);

            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }
    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("In-order traversal:");
        tree.inorder();
        System.out.println();

        System.out.println("Searching for 40 in the tree: " + tree.search(40));
        System.out.println("Searching for 90 in the tree: " + tree.search(90));

        tree.delete(20);
        System.out.println("In-order traversal after deleting 20:");
        tree.inorder();
        System.out.println();

        tree.delete(30);
        System.out.println("In-order traversal after deleting 30:");
        tree.inorder();
        System.out.println();
    }
}

