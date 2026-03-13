package heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小堆（Min Heap）实现
 * 特性：父节点 <= 子节点
 * 使用数组存储完全二叉树
 */
public class MinHeap {
    
    private List<Integer> heap;
    
    public MinHeap() {
        this.heap = new ArrayList<>();
    }
    
    public MinHeap(int[] array) {
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
    
    public int size() {
        return heap.size();
    }
    
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    /**
     * 获取最小值（堆顶元素）
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
     */
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }
    
    private void heapifyUp(int index) {
        while (index > 0 && heap.get(index) < heap.get(parent(index))) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    
    /**
     * 删除并返回最小值（堆顶元素）
     * 时间复杂度：O(log n)
     */
    public int extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        
        int min = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);
        
        if (!isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        
        return min;
    }
    
    private void heapifyDown(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);
        
        while (true) {
            if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            
            if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            
            if (smallest == index) {
                break;
            }
            
            swap(index, smallest);
            index = smallest;
            left = leftChild(index);
            right = rightChild(index);
        }
    }
    
    private void buildHeap() {
        for (int i = parent(heap.size() - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }
    
    /**
     * 堆排序（升序）
     * 时间复杂度：O(n log n)
     */
    public static int[] heapSort(int[] array) {
        MinHeap heap = new MinHeap(array);
        int[] sorted = new int[array.length];
        
        for (int i = 0; i < array.length; i++) {
            sorted[i] = heap.extractMin();
        }
        
        return sorted;
    }
    
    /**
     * 获取第 K 小的元素
     */
    public int getKthSmallest(int k) {
        if (k <= 0 || k > size()) {
            throw new IllegalArgumentException("Invalid k");
        }
        
        MinHeap tempHeap = new MinHeap();
        for (int value : heap) {
            tempHeap.insert(value);
        }
        
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = tempHeap.extractMin();
        }
        
        return result;
    }
    
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
        System.out.println("=== 最小堆演示 ===\n");
        
        MinHeap minHeap = new MinHeap();
        
        System.out.println("插入元素: 10, 20, 15, 30, 40, 25, 5");
        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(15);
        minHeap.insert(30);
        minHeap.insert(40);
        minHeap.insert(25);
        minHeap.insert(5);
        
        System.out.print("堆数组表示: ");
        minHeap.printHeap();
        
        System.out.println("\n树形结构:");
        minHeap.printTree();
        
        System.out.println("\n获取最小值: " + minHeap.peek());
        
        System.out.println("\n删除最小值: " + minHeap.extractMin());
        System.out.print("删除后的堆: ");
        minHeap.printHeap();
        
        System.out.println("\n树形结构:");
        minHeap.printTree();
        
        System.out.println("\n堆排序演示（升序）:");
        int[] array = {10, 20, 15, 30, 40, 25, 5};
        System.out.print("原数组: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        
        int[] sorted = MinHeap.heapSort(array);
        System.out.print("\n排序后: ");
        for (int num : sorted) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        System.out.println("\n第 K 小元素:");
        MinHeap heap2 = new MinHeap(new int[]{7, 10, 4, 3, 20, 15});
        System.out.println("第 3 小的元素: " + heap2.getKthSmallest(3));
    }
}
