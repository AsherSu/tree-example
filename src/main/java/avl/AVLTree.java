package avl;

/**
 * AVL树实现（自平衡二叉搜索树）
 * 特性：
 * 1. 满足BST性质：左子树 < 根 < 右子树
 * 2. 任意节点的左右子树高度差不超过1
 * 3. 通过旋转操作保持平衡
 * 
 * 四种旋转操作：
 * 1. LL型：右旋转
 * 2. RR型：左旋转
 * 3. LR型：先左旋后右旋
 * 4. RL型：先右旋后左旋
 */
public class AVLTree {
    
    private Node root;
    
    static class Node {
        int value;
        int height;
        Node left;
        Node right;
        
        Node(int value) {
            this.value = value;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }
    
    public AVLTree() {
        this.root = null;
    }
    
    /**
     * 获取节点高度
     */
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
    
    /**
     * 更新节点高度
     */
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }
    
    /**
     * 获取平衡因子
     * 平衡因子 = 左子树高度 - 右子树高度
     * 如果平衡因子 > 1，说明左子树过高
     * 如果平衡因子 < -1，说明右子树过高
     */
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
    
    /**
     * 右旋转（LL型）
     * 用于修复左子树过高的情况
     * 
     *       y                    x
     *      / \                  / \
     *     x   C    ----->      A   y
     *    / \                      / \
     *   A   B                    B   C
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node B = x.right;
        
        x.right = y;
        y.left = B;
        
        updateHeight(y);
        updateHeight(x);
        
        return x;
    }
    
    /**
     * 左旋转（RR型）
     * 用于修复右子树过高的情况
     * 
     *     x                      y
     *    / \                    / \
     *   A   y      ----->      x   C
     *      / \                / \
     *     B   C              A   B
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node B = y.left;
        
        y.left = x;
        x.right = B;
        
        updateHeight(x);
        updateHeight(y);
        
        return y;
    }
    
    /**
     * 左右旋转（LR型）
     * 先对左子节点进行左旋，再对当前节点进行右旋
     * 
     *       z                z                  y
     *      / \              / \                / \
     *     x   D            y   D     --->     x   z
     *    / \              / \                / \ / \
     *   A   y            x   C              A  B C  D
     *      / \          / \
     *     B   C        A   B
     */
    private Node rotateLeftRight(Node z) {
        z.left = rotateLeft(z.left);
        return rotateRight(z);
    }
    
    /**
     * 右左旋转（RL型）
     * 先对右子节点进行右旋，再对当前节点进行左旋
     * 
     *     x              x                    y
     *    / \            / \                  / \
     *   A   z          A   y       --->    x   z
     *      / \            / \              / \ / \
     *     y   D          B   z            A  B C  D
     *    / \                / \
     *   B   C              C   D
     */
    private Node rotateRightLeft(Node x) {
        x.right = rotateRight(x.right);
        return rotateLeft(x);
    }
    
    /**
     * 重新平衡节点
     */
    private Node rebalance(Node node) {
        updateHeight(node);
        int balance = getBalance(node);
        
        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                return rotateLeftRight(node);
            } else {
                return rotateRight(node);
            }
        }
        
        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                return rotateRightLeft(node);
            } else {
                return rotateLeft(node);
            }
        }
        
        return node;
    }
    
    /**
     * 插入节点
     * 时间复杂度：O(log n)
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
        } else {
            return node;
        }
        
        return rebalance(node);
    }
    
    /**
     * 删除节点
     * 时间复杂度：O(log n)
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
        
        return rebalance(node);
    }
    
    /**
     * 查找节点
     * 时间复杂度：O(log n)
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
     * 查找最小值
     */
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
        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }
    
    /**
     * 获取树的高度
     */
    public int getHeight() {
        return height(root);
    }
    
    /**
     * 判断树是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * 检查树是否平衡
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }
    
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        
        int balance = Math.abs(getBalance(node));
        if (balance > 1) {
            return false;
        }
        
        return isBalanced(node.left) && isBalanced(node.right);
    }
    
    /**
     * 中序遍历
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
     * 打印树形结构（包含高度和平衡因子）
     */
    public void printTree() {
        printTree(root, "", true);
    }
    
    private void printTree(Node node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }
        
        System.out.println(prefix + (isTail ? "└── " : "├── ") + 
                          node.value + " [h:" + node.height + ", bf:" + getBalance(node) + "]");
        
        if (node.left != null || node.right != null) {
            if (node.left != null) {
                printTree(node.left, prefix + (isTail ? "    " : "│   "), node.right == null);
            }
            if (node.right != null) {
                printTree(node.right, prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== AVL树演示 ===\n");
        
        AVLTree avl = new AVLTree();
        
        System.out.println("插入元素: 10, 20, 30, 40, 50, 25");
        System.out.println("（这个序列会触发多次旋转）\n");
        
        avl.insert(10);
        System.out.println("插入 10:");
        avl.printTree();
        
        avl.insert(20);
        System.out.println("\n插入 20:");
        avl.printTree();
        
        avl.insert(30);
        System.out.println("\n插入 30（触发左旋转 - RR型）:");
        avl.printTree();
        
        avl.insert(40);
        System.out.println("\n插入 40:");
        avl.printTree();
        
        avl.insert(50);
        System.out.println("\n插入 50（触发左旋转 - RR型）:");
        avl.printTree();
        
        avl.insert(25);
        System.out.println("\n插入 25（触发右左旋转 - RL型）:");
        avl.printTree();
        
        System.out.println("\n中序遍历（有序）: ");
        avl.inorderTraversal();
        
        System.out.println("树的高度: " + avl.getHeight());
        System.out.println("树是否平衡: " + avl.isBalanced());
        
        System.out.println("\n查找操作:");
        System.out.println("查找 25: " + avl.search(25));
        System.out.println("查找 15: " + avl.search(15));
        
        System.out.println("\n删除 40:");
        avl.delete(40);
        avl.printTree();
        System.out.print("中序遍历: ");
        avl.inorderTraversal();
        
        System.out.println("\n=== 对比：普通BST vs AVL树 ===\n");
        System.out.println("顺序插入 1-7:");
        
        System.out.println("\nAVL树保持平衡:");
        AVLTree avl2 = new AVLTree();
        for (int i = 1; i <= 7; i++) {
            avl2.insert(i);
        }
        avl2.printTree();
        System.out.println("AVL树高度: " + avl2.getHeight());
        
        System.out.println("\n说明: 如果是普通BST，顺序插入会退化成链表，高度为7");
        System.out.println("AVL树通过旋转保持平衡，高度仅为3，查询效率大大提高！");
    }
}
