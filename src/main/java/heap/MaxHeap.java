package heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大堆（Max Heap）实现
 * 特性：父节点 >= 子节点
 * 使用数组存储完全二叉树
 * 索引关系：
 * - 父节点：(i-1)/2
 * - 左子节点：2*i+1
 * - 右子节点：2*i+2
 */
public class MaxHeap {
    
    private List<Integer> heap;
    
    public MaxHeap() {
        this.heap = new ArrayList<>();
    }
    
    public MaxHeap(int[] array) {
        this.heap = new ArrayList<>();
        for (int value : array) {
            heap.add(value);
        }
        buildHeap();
    }
    
    private int parent(int i) {
        return (i - 1) / 2;
    }
    
    private int leftChild(int i) {
        return 2 * i + 1;
    }
    
    private int rightChild(int i) {
        return 2 * i + 2;
    }
    
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    /**
     * 获取堆的大小
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * 判断堆是否为空
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    /**
     * 获取最大值（堆顶元素）
     * 时间复杂度：O(1)
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }
    
    /**
     * 插入元素
     * 时间复杂度：O(log n)
     * 步骤：
     * 1. 将元素添加到数组末尾
     * 2. 向上调整（heapify up）
     */
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }
    
    /**
     * 向上调整（用于插入操作）
     * 将新插入的元素与父节点比较，如果大于父节点则交换
     */
    private void heapifyUp(int index) {
        while (index > 0 && heap.get(index) > heap.get(parent(index))) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    
    /**
     * 删除并返回最大值（堆顶元素）
     * 时间复杂度：O(log n)
     * 步骤：
     * 1. 用最后一个元素替换堆顶
     * 2. 删除最后一个元素
     * 3. 向下调整（heapify down）
     */
    public int extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        
        int max = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);
        
        if (!isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        
        return max;
    }
    
    /**
     * 向下调整（用于删除操作）
     * 将当前元素与子节点比较，如果小于最大的子节点则交换
     */
    private void heapifyDown(int index) {
        int largest = index;
        int left = leftChild(index);
        int right = rightChild(index);
        
        while (true) {
            if (left < heap.size() && heap.get(left) > heap.get(largest)) {
                largest = left;
            }
            
            if (right < heap.size() && heap.get(right) > heap.get(largest)) {
                largest = right;
            }
            
            if (largest == index) {
                break;
            }
            
            swap(index, largest);
            index = largest;
            left = leftChild(index);
            right = rightChild(index);
        }
    }
    
    /**
     * 从数组构建堆
     * 时间复杂度：O(n)
     * 从最后一个非叶子节点开始向下调整
     */
    private void buildHeap() {
        for (int i = parent(heap.size() - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }
    
    /**
     * 堆排序
     * 时间复杂度：O(n log n)
     */
    public static int[] heapSort(int[] array) {
        MaxHeap heap = new MaxHeap(array);
        int[] sorted = new int[array.length];
        
        for (int i = array.length - 1; i >= 0; i--) {
            sorted[i] = heap.extractMax();
        }
        
        return sorted;
    }
    
    /**
     * 获取第 K 大的元素
     */
    public int getKthLargest(int k) {
        if (k <= 0 || k > size()) {
            throw new IllegalArgumentException("Invalid k");
        }
        
        MaxHeap tempHeap = new MaxHeap();
        for (int value : heap) {
            tempHeap.insert(value);
        }
        
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = tempHeap.extractMax();
        }
        
        return result;
    }
    
    /**
     * 打印堆（数组形式）
     */
    public void printHeap() {
        System.out.print("Heap: [");
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.get(i));
            if (i < heap.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    /**
     * 打印树形结构
     */
    public void printTree() {
        printTreeHelper(0, "", true);
    }
    
    private void printTreeHelper(int index, String prefix, boolean isTail) {
        if (index >= heap.size()) {
            return;
        }
        
        System.out.println(prefix + (isTail ? "└── " : "├── ") + heap.get(index));
        
        int left = leftChild(index);
        int right = rightChild(index);
        
        if (left < heap.size()) {
            printTreeHelper(left, prefix + (isTail ? "    " : "│   "), right >= heap.size());
        }
        if (right < heap.size()) {
            printTreeHelper(right, prefix + (isTail ? "    " : "│   "), true);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 最大堆演示 ===\n");
        
        MaxHeap maxHeap = new MaxHeap();
        
        System.out.println("插入元素: 10, 20, 15, 30, 40, 25, 5");
        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(15);
        maxHeap.insert(30);
        maxHeap.insert(40);
        maxHeap.insert(25);
        maxHeap.insert(5);
        
        System.out.print("堆数组表示: ");
        maxHeap.printHeap();
        
        System.out.println("\n树形结构:");
        maxHeap.printTree();
        
        System.out.println("\n获取最大值: " + maxHeap.peek());
        
        System.out.println("\n删除最大值: " + maxHeap.extractMax());
        System.out.print("删除后的堆: ");
        maxHeap.printHeap();
        
        System.out.println("\n树形结构:");
        maxHeap.printTree();
        
        System.out.println("\n堆排序演示:");
        int[] array = {10, 20, 15, 30, 40, 25, 5};
        System.out.print("原数组: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        
        int[] sorted = MaxHeap.heapSort(array);
        System.out.print("\n排序后: ");
        for (int num : sorted) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        System.out.println("\n从数组构建堆:");
        int[] array2 = {3, 9, 2, 1, 4, 5, 8, 7, 6};
        MaxHeap heap2 = new MaxHeap(array2);
        System.out.print("构建的堆: ");
        heap2.printHeap();
        heap2.printTree();
    }
}
