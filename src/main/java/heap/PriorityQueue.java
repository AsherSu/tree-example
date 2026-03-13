package heap;

/**
 * 优先队列实现（基于最小堆）
 * 支持带优先级的元素操作
 * 优先级越小，优先级越高
 */
public class PriorityQueue<T> {
    
    private static class Element<T> implements Comparable<Element<T>> {
        T value;
        int priority;
        
        Element(T value, int priority) {
            this.value = value;
            this.priority = priority;
        }
        
        @Override
        public int compareTo(Element<T> other) {
            return Integer.compare(this.priority, other.priority);
        }
    }
    
    private java.util.PriorityQueue<Element<T>> heap;
    
    public PriorityQueue() {
        this.heap = new java.util.PriorityQueue<>();
    }
    
    /**
     * 入队（插入元素）
     * 时间复杂度：O(log n)
     */
    public void enqueue(T value, int priority) {
        heap.offer(new Element<>(value, priority));
    }
    
    /**
     * 出队（获取并删除最高优先级元素）
     * 时间复杂度：O(log n)
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return heap.poll().value;
    }
    
    /**
     * 查看最高优先级元素
     * 时间复杂度：O(1)
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return heap.peek().value;
    }
    
    /**
     * 查看最高优先级
     */
    public int peekPriority() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return heap.peek().priority;
    }
    
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    public int size() {
        return heap.size();
    }
    
    public void clear() {
        heap.clear();
    }
    
    public static void main(String[] args) {
        System.out.println("=== 优先队列演示 ===\n");
        
        PriorityQueue<String> pq = new PriorityQueue<>();
        
        System.out.println("添加任务到优先队列:");
        pq.enqueue("低优先级任务", 5);
        pq.enqueue("紧急任务", 1);
        pq.enqueue("普通任务", 3);
        pq.enqueue("重要任务", 2);
        pq.enqueue("次要任务", 4);
        
        System.out.println("\n按优先级顺序处理任务:");
        while (!pq.isEmpty()) {
            String task = pq.dequeue();
            System.out.println("处理: " + task);
        }
        
        System.out.println("\n医院急诊室示例:");
        PriorityQueue<String> emergency = new PriorityQueue<>();
        
        emergency.enqueue("患者A - 轻伤", 3);
        emergency.enqueue("患者B - 危重", 1);
        emergency.enqueue("患者C - 普通", 5);
        emergency.enqueue("患者D - 重伤", 2);
        emergency.enqueue("患者E - 中等", 4);
        
        System.out.println("治疗顺序:");
        int order = 1;
        while (!emergency.isEmpty()) {
            int priority = emergency.peekPriority();
            String patient = emergency.dequeue();
            System.out.println(order++ + ". [优先级 " + priority + "] " + patient);
        }
        
        System.out.println("\nCPU任务调度示例:");
        PriorityQueue<String> scheduler = new PriorityQueue<>();
        
        scheduler.enqueue("系统进程", 0);
        scheduler.enqueue("用户程序A", 10);
        scheduler.enqueue("实时任务", 1);
        scheduler.enqueue("后台服务", 15);
        scheduler.enqueue("用户程序B", 10);
        
        System.out.println("执行顺序:");
        while (!scheduler.isEmpty()) {
            System.out.println("执行: " + scheduler.dequeue());
        }
    }
}
