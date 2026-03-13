# 树形数据结构实现集合

本项目包含了常见树形数据结构的完整Java实现，每个数据结构都在独立的目录中，包含详细的注释和演示代码。

## 项目结构

```
tree-example/
├── bst/                          # 二叉搜索树
│   └── BinarySearchTree.java     # BST完整实现
├── heap/                         # 堆
│   ├── MaxHeap.java              # 最大堆实现
│   ├── MinHeap.java              # 最小堆实现
│   └── PriorityQueue.java        # 优先队列实现
├── avl/                          # 平衡树
│   ├── AVLTree.java              # AVL树完整实现（含旋转）
│   └── RedBlackTreeConcept.java  # 红黑树概念说明
└── trie/                         # 字典树
    └── Trie.java                 # Trie树完整实现
```

## 数据结构详解

### 1. 二叉搜索树 (BST) - `bst/BinarySearchTree.java`

**特性**：左子树 < 根节点 < 右子树

**实现功能**：
- ✅ 查找操作（递归和迭代两种实现）
- ✅ 插入操作（递归和迭代两种实现）
- ✅ 删除操作（三种情况处理）
- ✅ 查找最小值/最大值
- ✅ 三种遍历方式（前序、中序、后序）
- ✅ 获取树高度

**时间复杂度**：
- 查找：O(h)，h为树高（最坏O(n)，平均O(log n)）
- 插入：O(h)
- 删除：O(h)

**运行示例**：
```bash
cd bst
javac BinarySearchTree.java
java bst.BinarySearchTree
```

---

### 2. 堆 (Heap) - `heap/`

**特性**：完全二叉树，快速获取最大值或最小值

#### 2.1 最大堆 - `MaxHeap.java`
- 父节点 ≥ 子节点
- 堆顶元素是最大值

#### 2.2 最小堆 - `MinHeap.java`
- 父节点 ≤ 子节点
- 堆顶元素是最小值

#### 2.3 优先队列 - `PriorityQueue.java`
- 基于堆实现的优先队列
- 支持按优先级处理任务

**实现功能**：
- ✅ 插入元素（heapify up）
- ✅ 删除堆顶（heapify down）
- ✅ 获取堆顶元素
- ✅ 从数组构建堆
- ✅ 堆排序
- ✅ 获取第K大/小元素

**时间复杂度**：
- 插入：O(log n)
- 删除堆顶：O(log n)
- 查看堆顶：O(1)
- 构建堆：O(n)

**应用场景**：
- 任务调度系统
- 医院急诊室排队
- CPU进程调度
- Top K问题

**运行示例**：
```bash
cd heap
javac MaxHeap.java
java heap.MaxHeap

javac MinHeap.java
java heap.MinHeap

javac PriorityQueue.java
java heap.PriorityQueue
```

---

### 3. 平衡树 (AVL Tree) - `avl/`

**特性**：自平衡二叉搜索树，任意节点的左右子树高度差≤1

#### 3.1 AVL树 - `AVLTree.java`

**四种旋转操作**：
1. **LL型**：右旋转（左子树的左子树过高）
2. **RR型**：左旋转（右子树的右子树过高）
3. **LR型**：先左旋后右旋（左子树的右子树过高）
4. **RL型**：先右旋后左旋（右子树的左子树过高）

**实现功能**：
- ✅ 插入（自动平衡）
- ✅ 删除（自动平衡）
- ✅ 查找
- ✅ 四种旋转操作
- ✅ 平衡性检查
- ✅ 树形结构可视化（显示高度和平衡因子）

**时间复杂度**：
- 所有操作：O(log n)（严格保证）

**优势**：
- 解决了普通BST退化成链表的问题
- 保证最坏情况下的O(log n)性能

#### 3.2 红黑树概念 - `RedBlackTreeConcept.java`

**说明**：
- 提供红黑树的五个性质说明
- AVL树 vs 红黑树的对比分析
- 实际应用场景介绍
- Java TreeMap使用示例（底层是红黑树）

**面试要点**：
- ✅ 理解红黑树的五个性质
- ✅ 知道通过旋转和重新着色保持平衡
- ✅ 理解AVL树和红黑树的区别
- ✅ 了解各自的适用场景
- ⚠️ 不需要手写完整代码

**运行示例**：
```bash
cd avl
javac AVLTree.java
java avl.AVLTree

javac RedBlackTreeConcept.java
java avl.RedBlackTreeConcept
```

---

### 4. 字典树 (Trie) - `trie/Trie.java`

**特性**：利用字符串的公共前缀来减少查询时间

**实现功能**：
- ✅ 插入单词
- ✅ 查找单词
- ✅ 前缀匹配
- ✅ 删除单词
- ✅ 自动补全（获取所有以指定前缀开头的单词）
- ✅ 词频统计
- ✅ 最长公共前缀查找
- ✅ 树形结构可视化

**时间复杂度**：
- 插入：O(m)，m为字符串长度
- 查找：O(m)
- 前缀查找：O(m)

**应用场景**：
- 🔍 搜索引擎自动补全/提示功能
- ✏️ 拼写检查
- 🌐 IP路由表（最长前缀匹配）
- 📊 词频统计
- 📝 字符串排序

**运行示例**：
```bash
cd trie
javac Trie.java
java trie.Trie
```

---

## 数据结构对比

| 数据结构 | 查找 | 插入 | 删除 | 特点 | 适用场景 |
|---------|------|------|------|------|---------|
| BST | O(h) | O(h) | O(h) | 简单，可能退化 | 基础应用 |
| AVL树 | O(log n) | O(log n) | O(log n) | 严格平衡 | 查询密集 |
| 红黑树 | O(log n) | O(log n) | O(log n) | 相对平衡，旋转少 | 读写均衡 |
| 堆 | O(n) | O(log n) | O(log n) | 快速获取最值 | 优先队列 |
| Trie | O(m) | O(m) | O(m) | 前缀匹配 | 字符串处理 |

*注：h为树高，n为节点数，m为字符串长度*

## 学习建议

### 1. 基础阶段
- 先掌握 **BST**，理解二叉搜索树的基本原理
- 学习 **堆**，理解完全二叉树和数组表示

### 2. 进阶阶段
- 学习 **AVL树**，理解旋转操作如何解决平衡问题
- 了解 **红黑树** 的概念和应用场景

### 3. 应用阶段
- 学习 **Trie树**，掌握字符串处理的高效方法
- 将这些数据结构应用到实际问题中

### 4. 面试准备
- **必须会写**：BST、堆、Trie
- **必须理解**：AVL树的旋转操作
- **了解原理**：红黑树的性质和应用
- **实际应用**：知道何时使用哪种数据结构

## 编译运行

### 方式一：单独运行每个文件
```bash
# BST
javac bst/BinarySearchTree.java
java bst.BinarySearchTree

# 最大堆
javac heap/MaxHeap.java
java heap.MaxHeap

# 最小堆
javac heap/MinHeap.java
java heap.MinHeap

# 优先队列
javac heap/PriorityQueue.java
java heap.PriorityQueue

# AVL树
javac avl/AVLTree.java
java avl.AVLTree

# 红黑树概念
javac avl/RedBlackTreeConcept.java
java avl.RedBlackTreeConcept

# Trie树
javac trie/Trie.java
java trie.Trie
```

### 方式二：批量编译
```bash
# 编译所有Java文件
javac bst/*.java heap/*.java avl/*.java trie/*.java

# 运行各个示例
java bst.BinarySearchTree
java heap.MaxHeap
java heap.MinHeap
java heap.PriorityQueue
java avl.AVLTree
java avl.RedBlackTreeConcept
java trie.Trie
```

## 常见面试题

### BST相关
- 验证二叉搜索树
- 二叉搜索树的第K小元素
- 二叉搜索树转换为累加树

### 堆相关
- 数组中的第K个最大元素
- 合并K个排序链表
- 前K个高频元素

### 平衡树相关
- 理解旋转操作的原理
- AVL树与红黑树的区别
- 为什么Java用红黑树而不是AVL树

### Trie相关
- 实现搜索自动补全系统
- 单词搜索II
- 最长公共前缀

## 扩展学习

- **B树/B+树**：数据库索引常用
- **线段树**：区间查询和修改
- **树状数组**：前缀和的高效实现
- **哈夫曼树**：数据压缩

## 贡献

欢迎提交Issue和Pull Request来改进这个项目！

## 许可

MIT License
