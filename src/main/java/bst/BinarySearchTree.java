package bst;

/**
 * 二叉搜索树（Binary Search Tree）实现
 * 特性：左子树 < 根节点 < 右子树
 * 支持查找、插入、删除操作
 */
public class BinarySearchTree {
    
    private Node root;
    
    static class Node {
        int value;
        Node left;
        Node right;
        
        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    
    public BinarySearchTree() {
        this.root = null;
    }
    
    /**
     * 查找操作 - 递归实现
     * 时间复杂度：O(h)，h为树高
     */
    public boolean search(int value) {
        return searchRecursive(root, value);
    }
    
    private boolean searchRecursive(Node node, int value) {
        if (node == null) {
            return false;
        }
        
        if (value == node.value) {
            return true;
        } else if (value < node.value) {
            return searchRecursive(node.left, value);
        } else {
            return searchRecursive(node.right, value);
        }
    }
    
    /**
     * 查找操作 - 迭代实现
     * 时间复杂度：O(h)
     */
    public boolean searchIterative(int value) {
        Node current = root;
        
        while (current != null) {
            if (value == current.value) {
                return true;
            } else if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        return false;
    }
    
    /**
     * 插入操作 - 递归实现
     * 时间复杂度：O(h)
     */
    public void insert(int value) {
        root = insertRecursive(root, value);
    }
    
    private Node insertRecursive(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        
        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        }
        
        return node;
    }
    
    /**
     * 插入操作 - 迭代实现
     * 时间复杂度：O(h)
     */
    public void insertIterative(int value) {
        Node newNode = new Node(value);
        
        if (root == null) {
            root = newNode;
            return;
        }
        
        Node current = root;
        Node parent = null;
        
        while (current != null) {
            parent = current;
            if (value < current.value) {
                current = current.left;
            } else if (value > current.value) {
                current = current.right;
            } else {
                return;
            }
        }
        
        if (value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    /**
     * 删除操作
     * 时间复杂度：O(h)
     * 三种情况：
     * 1. 删除叶子节点：直接删除
     * 2. 删除只有一个子节点的节点：用子节点替代
     * 3. 删除有两个子节点的节点：用右子树的最小值（或左子树的最大值）替代
     */
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }
    
    private Node deleteRecursive(Node node, int value) {
        if (node == null) {
            return null;
        }
        
        if (value < node.value) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRecursive(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            
            node.value = findMin(node.right);
            node.right = deleteRecursive(node.right, node.value);
        }
        
        return node;
    }
    
    /**
     * 查找最小值
     */
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMin(root);
    }
    
    private int findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }
    
    /**
     * 查找最大值
     */
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMax(root);
    }
    
    private int findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }
    
    /**
     * 中序遍历（左 -> 根 -> 右）
     * 对于BST，中序遍历结果是有序的
     */
    public void inorderTraversal() {
        inorderTraversal(root);
        System.out.println();
    }
    
    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.value + " ");
            inorderTraversal(node.right);
        }
    }
    
    /**
     * 前序遍历（根 -> 左 -> 右）
     */
    public void preorderTraversal() {
        preorderTraversal(root);
        System.out.println();
    }
    
    private void preorderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }
    
    /**
     * 后序遍历（左 -> 右 -> 根）
     */
    public void postorderTraversal() {
        postorderTraversal(root);
        System.out.println();
    }
    
    private void postorderTraversal(Node node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.value + " ");
        }
    }
    
    /**
     * 获取树的高度
     */
    public int getHeight() {
        return getHeight(root);
    }
    
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
    
    /**
     * 判断树是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        
        System.out.println("=== 二叉搜索树演示 ===\n");
        
        System.out.println("插入节点: 50, 30, 70, 20, 40, 60, 80");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        System.out.print("中序遍历（有序）: ");
        bst.inorderTraversal();
        
        System.out.print("前序遍历: ");
        bst.preorderTraversal();
        
        System.out.print("后序遍历: ");
        bst.postorderTraversal();
        
        System.out.println("\n查找操作:");
        System.out.println("查找 40: " + bst.search(40));
        System.out.println("查找 25: " + bst.search(25));
        
        System.out.println("\n最小值: " + bst.findMin());
        System.out.println("最大值: " + bst.findMax());
        System.out.println("树高度: " + bst.getHeight());
        
        System.out.println("\n删除节点 20（叶子节点）");
        bst.delete(20);
        System.out.print("中序遍历: ");
        bst.inorderTraversal();
        
        System.out.println("删除节点 30（有两个子节点）");
        bst.delete(30);
        System.out.print("中序遍历: ");
        bst.inorderTraversal();
        
        System.out.println("删除节点 50（根节点）");
        bst.delete(50);
        System.out.print("中序遍历: ");
        bst.inorderTraversal();
    }
}
