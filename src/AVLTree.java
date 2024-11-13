public class AVLTree {

    static class Node {
        int data;
        Node left, right;
        int height;

        Node(int data) {
            this.data = data;
            left = right = null;
            height = 1;
        }
    }


    private Node root;

    public AVLTree() {
        root = null;
    }


    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }


    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    public void insert(int data) {
        root = insertRec(root, data);
    }


    private Node insertRec(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertRec(node.left, data);
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);
        } else {
            return node;
        }


        node.height = Math.max(height(node.left), height(node.right)) + 1;


        int balance = getBalance(node);


        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }

        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }


        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
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
        AVLTree tree = new AVLTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(25);
        tree.insert(5);
        tree.insert(1);

        System.out.println("In-order traversal of the AVL Tree:");
        tree.inorder();
        System.out.println();
    }
}
