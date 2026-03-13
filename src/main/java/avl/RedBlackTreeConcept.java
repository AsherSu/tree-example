package avl;

/**
 * 红黑树概念说明
 * 
 * 红黑树是另一种自平衡二叉搜索树，相比AVL树：
 * 1. AVL树要求严格平衡（高度差≤1），红黑树要求相对平衡
 * 2. AVL树查询效率更高，红黑树插入删除效率更高
 * 3. AVL树旋转次数更多，红黑树旋转次数更少
 * 
 * 红黑树的五个性质：
 * 1. 每个节点要么是红色，要么是黑色
 * 2. 根节点是黑色
 * 3. 所有叶子节点（NIL）是黑色
 * 4. 红色节点的两个子节点都是黑色（不能有连续的红色节点）
 * 5. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点
 * 
 * 通过这些性质，红黑树保证：
 * 最长路径不会超过最短路径的2倍，从而保证了O(log n)的时间复杂度
 * 
 * 应用场景：
 * - Java TreeMap / TreeSet
 * - C++ STL map / set
 * - Linux 进程调度（CFS调度器）
 * - Nginx 定时器管理
 * 
 * 由于红黑树实现较复杂（需要考虑多种情况的旋转和重新着色），
 * 且面试中通常只需要理解原理，这里提供概念说明。
 * 实际使用时推荐使用语言自带的红黑树实现（如Java的TreeMap）。
 */
public class RedBlackTreeConcept {
    
    public static void main(String[] args) {
        System.out.println("=== 红黑树 vs AVL树 对比 ===\n");
        
        System.out.println("【平衡性】");
        System.out.println("AVL树: 严格平衡，任意节点左右子树高度差≤1");
        System.out.println("红黑树: 相对平衡，最长路径≤最短路径的2倍\n");
        
        System.out.println("【性能对比】");
        System.out.println("AVL树查询: O(log n) - 更快，因为树更平衡");
        System.out.println("红黑树查询: O(log n) - 稍慢");
        System.out.println("AVL树插入/删除: O(log n) - 可能需要多次旋转");
        System.out.println("红黑树插入/删除: O(log n) - 最多3次旋转\n");
        
        System.out.println("【使用场景】");
        System.out.println("AVL树: 查询密集型应用（读多写少）");
        System.out.println("红黑树: 插入删除频繁的应用（读写均衡）\n");
        
        System.out.println("【实际应用】");
        System.out.println("AVL树: 数据库索引、需要快速查询的场景");
        System.out.println("红黑树: Java集合框架、C++ STL、Linux内核\n");
        
        System.out.println("【面试要点】");
        System.out.println("1. 理解五个性质的含义");
        System.out.println("2. 知道通过「旋转」和「重新着色」来保持平衡");
        System.out.println("3. 理解为什么最长路径≤最短路径的2倍");
        System.out.println("4. 了解与AVL树的区别和各自适用场景");
        System.out.println("5. 不需要手写完整代码，但要能说清楚原理\n");
        
        System.out.println("【示例：Java TreeMap使用（底层是红黑树）】");
        demonstrateTreeMap();
    }
    
    private static void demonstrateTreeMap() {
        java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();
        
        System.out.println("\n插入数据到TreeMap（自动保持有序）:");
        map.put(50, "五十");
        map.put(30, "三十");
        map.put(70, "七十");
        map.put(20, "二十");
        map.put(40, "四十");
        map.put(60, "六十");
        map.put(80, "八十");
        
        System.out.println("TreeMap内容（自动排序）: " + map);
        
        System.out.println("\n范围查询:");
        System.out.println("30-60之间的元素: " + map.subMap(30, true, 60, true));
        
        System.out.println("\n查找操作:");
        System.out.println("小于等于45的最大键: " + map.floorKey(45));
        System.out.println("大于等于45的最小键: " + map.ceilingKey(45));
        
        System.out.println("\n第一个和最后一个:");
        System.out.println("第一个元素: " + map.firstEntry());
        System.out.println("最后一个元素: " + map.lastEntry());
    }
}
