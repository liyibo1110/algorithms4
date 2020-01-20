package com.liyibo1110.algorithms4.search;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Queue;

public class RedBlackBST<K extends Comparable<K>, V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	
	private class Node{
		private K key;
		private V val;
		private Node left, right;
		private int N;	//该节点下面的节点总数
		private boolean color;
		public Node(K key, V val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x) {
		if(x == null) return false;
		return x.color == RED;
	}
	
	public Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;	//原来是啥就是啥
		h.color = RED;	//肯定是红
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	public Node rotateRight(Node h) {
		Node x = h.right;
		h.left = x.right;
		x.right = h;
		x.color = h.color;	//原来是啥就是啥
		h.color = RED;	//肯定是红
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	public void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	public void put(K key, V val) {
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	public Node put(Node h, K key, V val) {
		//到最下面了
		if(h == null) return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
		if(cmp < 0) {
			h.left = put(h.left, key, val);
		}else if(cmp > 0) {
			h.right = put(h.right, key, val);
		}else {
			h.val = val;
		}
		
		//增加红黑树的特性
		if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right)) flipColors(h);
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	public V get(K key) {
		return get(root, key);
	}
	
	private V get(Node x, K key) {
		return null;
	}
	
	public void delete(K key) {
		delete(root, key);
	}
	
	private Node delete(Node x, K key) {
		return null;
	}
	
	public boolean contains(K key) {
		return get(key) != null;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if(x == null) {
			return 0;
		}else {
			return x.N;
		}
	}
	
	public K min() {
		return min(root).key;
	}
	
	private Node min(Node x) {
		if(x.left == null) return x;
		return min(x.left);
	}
	
	public K max() {
		return max(root).key;
	}
	
	private Node max(Node x) {
		if(x.right == null) return x;
		return max(x.right);
	}
	
	/**
	 * 小于等于key的最大键
	 * @param key
	 * @return
	 */
	public K floor(K key) {
		Node x = floor(root, key);
		if(x == null) {
			return null;
		}else {
			return x.key;
		}
	}
	
	private Node floor(Node x, K key) {
		return null;
	}
	
	/**
	 * 大于等于key的最小键
	 * @param key
	 * @return
	 */
	public K ceiling(K key) {
		return null;
	}
	
	/**
	 * 小于key的键的数量
	 * @param key
	 * @return
	 */
	public int rank(K key) {
		return rank(key, root);
	}
	
	private int rank(K key, Node x) {
		return 0;
	}
	
	/**
	 * 返回排名为k的key
	 * @param k
	 * @return
	 */
	public K select(int k) {
		return select(root, k).key;
	}
	
	private Node select(Node x, int k) {
		return null;
	}
	
	public void deleteMin() {
		deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		return x;
	}
	
	public void deleteMax() {
		delete(max());
	}
	
	/**
	 * 返回2个key之间的key数量
	 * @param lo
	 * @param hi
	 * @return
	 */
	public int size(K lo, K hi) {
		return 0;
	}
	
	public Iterable<K> keys(){
		return keys(min(), max());
	}
	
	public Iterable<K> keys(K lo, K hi){
		Queue<K> queue = new Queue<K>();
		keys(root, queue, lo, hi);
		return queue;
	}
	
	public void keys(Node x, Queue<K> queue, K lo, K hi) {
			
	}
	
	public static void main(String[] args) {
		//构造用例
		List<String> list = new ArrayList<>();
		list.add("S");list.add("E");
		list.add("A");list.add("R");
		list.add("C");list.add("H");
		list.add("E");list.add("X");
		list.add("A");list.add("M");
		list.add("P");list.add("L");
		list.add("E");
		RedBlackBST<String, Integer> st = new RedBlackBST<>();
		for(int i=0; i<list.size(); i++) {
			st.put(list.get(i), i);
		}
		for(String s : st.keys()) {
			System.out.println(s);
		}
	}
}
