package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典树（Trie树 / 前缀树）实现
 * 
 * 特点：
 * 1. 专门用于字符串处理
 * 2. 利用字符串的公共前缀来减少查询时间
 * 3. 空间换时间的典型应用
 * 
 * 应用场景：
 * 1. 搜索引擎的自动补全/提示功能
 * 2. 拼写检查
 * 3. IP路由表（最长前缀匹配）
 * 4. 词频统计
 * 5. 字符串排序
 * 
 * 时间复杂度：
 * - 插入：O(m)，m为字符串长度
 * - 查找：O(m)
 * - 前缀查找：O(m)
 */
public class Trie {
    
    private TrieNode root;
    
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        int wordCount;
        
        TrieNode() {
            this.children = new HashMap<>();
            this.isEndOfWord = false;
            this.wordCount = 0;
        }
    }
    
    public Trie() {
        this.root = new TrieNode();
    }
    
    /**
     * 插入单词
     * 时间复杂度：O(m)，m为单词长度
     */
    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        
        TrieNode current = root;
        
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        
        if (!current.isEndOfWord) {
            current.isEndOfWord = true;
        }
        current.wordCount++;
    }
    
    /**
     * 查找单词是否存在
     * 时间复杂度：O(m)
     */
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }
    
    /**
     * 查找前缀是否存在
     * 时间复杂度：O(m)
     */
    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }
    
    /**
     * 查找节点
     */
    private TrieNode findNode(String str) {
        if (str == null) {
            return null;
        }
        
        TrieNode current = root;
        
        for (char ch : str.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return null;
            }
            current = node;
        }
        
        return current;
    }
    
    /**
     * 删除单词
     */
    public boolean delete(String word) {
        return delete(root, word, 0);
    }
    
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;
            current.wordCount = 0;
            return current.children.isEmpty();
        }
        
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        
        if (node == null) {
            return false;
        }
        
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);
        
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.isEmpty() && !current.isEndOfWord;
        }
        
        return false;
    }
    
    /**
     * 获取所有以指定前缀开头的单词
     */
    public List<String> getAllWordsWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        TrieNode node = findNode(prefix);
        
        if (node != null) {
            collectWords(node, prefix, words);
        }
        
        return words;
    }
    
    private void collectWords(TrieNode node, String currentWord, List<String> words) {
        if (node.isEndOfWord) {
            words.add(currentWord);
        }
        
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            collectWords(entry.getValue(), currentWord + entry.getKey(), words);
        }
    }
    
    /**
     * 获取所有单词
     */
    public List<String> getAllWords() {
        List<String> words = new ArrayList<>();
        collectWords(root, "", words);
        return words;
    }
    
    /**
     * 获取单词数量
     */
    public int getWordCount(String word) {
        TrieNode node = findNode(word);
        return (node != null && node.isEndOfWord) ? node.wordCount : 0;
    }
    
    /**
     * 计算树中的单词总数
     */
    public int getTotalWords() {
        return countWords(root);
    }
    
    private int countWords(TrieNode node) {
        int count = 0;
        
        if (node.isEndOfWord) {
            count += node.wordCount;
        }
        
        for (TrieNode child : node.children.values()) {
            count += countWords(child);
        }
        
        return count;
    }
    
    /**
     * 查找最长公共前缀
     */
    public String findLongestCommonPrefix() {
        StringBuilder prefix = new StringBuilder();
        TrieNode current = root;
        
        while (current.children.size() == 1 && !current.isEndOfWord) {
            Map.Entry<Character, TrieNode> entry = current.children.entrySet().iterator().next();
            prefix.append(entry.getKey());
            current = entry.getValue();
        }
        
        return prefix.toString();
    }
    
    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return root.children.isEmpty();
    }
    
    /**
     * 打印树结构
     */
    public void printTree() {
        System.out.println("Trie结构:");
        printTree(root, "", "");
    }
    
    private void printTree(TrieNode node, String prefix, String chars) {
        if (node == root) {
            System.out.println("(root)");
        }
        
        List<Map.Entry<Character, TrieNode>> entries = new ArrayList<>(node.children.entrySet());
        
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<Character, TrieNode> entry = entries.get(i);
            boolean isLast = (i == entries.size() - 1);
            char ch = entry.getKey();
            TrieNode child = entry.getValue();
            
            String marker = child.isEndOfWord ? " *" : "";
            System.out.println(prefix + (isLast ? "└── " : "├── ") + ch + marker);
            
            printTree(child, prefix + (isLast ? "    " : "│   "), chars + ch);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 字典树（Trie）演示 ===\n");
        
        Trie trie = new Trie();
        
        System.out.println("插入单词: apple, app, application, apply, banana, band, bandana");
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("apply");
        trie.insert("banana");
        trie.insert("band");
        trie.insert("bandana");
        
        System.out.println("\n字典树结构（*表示单词结尾）:");
        trie.printTree();
        
        System.out.println("\n查找操作:");
        System.out.println("查找 'app': " + trie.search("app"));
        System.out.println("查找 'appl': " + trie.search("appl"));
        System.out.println("查找 'banana': " + trie.search("banana"));
        
        System.out.println("\n前缀查找:");
        System.out.println("'app' 是否为前缀: " + trie.startsWith("app"));
        System.out.println("'ban' 是否为前缀: " + trie.startsWith("ban"));
        System.out.println("'cat' 是否为前缀: " + trie.startsWith("cat"));
        
        System.out.println("\n自动补全（搜索提示）:");
        System.out.println("以 'app' 开头的单词: " + trie.getAllWordsWithPrefix("app"));
        System.out.println("以 'ban' 开头的单词: " + trie.getAllWordsWithPrefix("ban"));
        
        System.out.println("\n所有单词:");
        System.out.println(trie.getAllWords());
        
        System.out.println("\n词频统计示例:");
        Trie freqTrie = new Trie();
        freqTrie.insert("hello");
        freqTrie.insert("hello");
        freqTrie.insert("hello");
        freqTrie.insert("world");
        freqTrie.insert("world");
        System.out.println("'hello' 出现次数: " + freqTrie.getWordCount("hello"));
        System.out.println("'world' 出现次数: " + freqTrie.getWordCount("world"));
        System.out.println("总单词数: " + freqTrie.getTotalWords());
        
        System.out.println("\n搜索引擎自动提示模拟:");
        Trie searchTrie = new Trie();
        String[] searches = {"java", "javascript", "java tutorial", "java programming", 
                            "python", "python tutorial", "programming"};
        for (String s : searches) {
            searchTrie.insert(s);
        }
        
        System.out.println("用户输入 'java' 时的提示:");
        List<String> suggestions = searchTrie.getAllWordsWithPrefix("java");
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println((i + 1) + ". " + suggestions.get(i));
        }
        
        System.out.println("\n用户输入 'python' 时的提示:");
        suggestions = searchTrie.getAllWordsWithPrefix("python");
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println((i + 1) + ". " + suggestions.get(i));
        }
        
        System.out.println("\n用户输入 'prog' 时的提示:");
        suggestions = searchTrie.getAllWordsWithPrefix("prog");
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println((i + 1) + ". " + suggestions.get(i));
        }
        
        System.out.println("\n删除操作:");
        trie.delete("app");
        System.out.println("删除 'app' 后:");
        System.out.println("查找 'app': " + trie.search("app"));
        System.out.println("查找 'apple': " + trie.search("apple"));
        System.out.println("以 'app' 开头的单词: " + trie.getAllWordsWithPrefix("app"));
    }
}
