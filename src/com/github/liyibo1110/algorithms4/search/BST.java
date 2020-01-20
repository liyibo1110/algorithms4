package com.github.liyibo1110.algorithms4.search;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Queue;

public class BST<K extends Comparable<K>, V> {

	private class Node{
		private K key;
		private V val;
		Node left, right;
		private int N;	//该节点下面的节点总数
		public Node(K key, V val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	private Node root;
	
	public void put(K key, V val) {
		root = put(root, key, val);
	}
	
	public Node put(Node x, K key, V val) {
		//到最下面了
		if(x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			x.left = put(x.left, key, val);
		}else if(cmp > 0) {
			x.right = put(x.right, key, val);
		}else {
			x.val = val;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public V get(K key) {
		return get(root, key);
	}
	
	private V get(Node x, K key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			return get(x.left, key);
		}else if(cmp > 0) {
			return get(x.right, key);
		}else {
			return x.val;
		}
	}
	
	public void delete(K key) {
		delete(root, key);
	}
	
	private Node delete(Node x, K key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			x.left = delete(x.left, key);
		}else if(cmp > 0) {
			x.right = delete(x.right, key);
		}else {	//开始删除节点
			//如果只是单枝就好办了，直接返回存在的下枝
			if(x.left == null) return x.right;
			if(x.right == null) return x.left;
			//左右分枝都有，则要麻烦一些了
			Node t = x;
			x = min(t.right);	//找到右枝最小的元素，当做被删除节点的替代者
			x.right = deleteMin(t.right);	//干掉右枝最小的元素
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
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
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) {
			return x;
		}
		if(cmp < 0) {
			return floor(x.left, key);
		}else {
			Node t = floor(x.right, key);
			if(t != null) {
				return t;
			}else {
				return x;
			}
		}
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
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) {
			return rank(key, x.left);
		}else if(cmp > 0) {
			return 1 + size(x.left) + rank(key, x.right);
		}else {
			return size(x.left);
		}
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
		if(x == null) return null;
		if(k == 0) return root;	//书上漏了
		int t = size(x.left);
		if(t > k) {
			return select(x.left, k);	//左子树总节点大于k，则继续找下个左子树
		}else if(t < k) {
			return select(x.right, k-t-1);
		}else {
			return x;
		}
	}
	
	public void deleteMin() {
		deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if(x.left == null) return x.right;
		//巧妙
		x.left = deleteMin(x.right);
		//重算本层节点
		x.N = size(x.left) + size(x.right) + 1;
		return x;	//让上层递归也进行重算
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
		if(hi.compareTo(lo) < 0) {
			return 0;
		}else if(contains(hi)) {
			return rank(hi) - rank(lo) + 1;	//算它自己
		}else {
			return rank(hi) - rank(lo);
		}
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
		if(x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		//当前结点大于范围下限，则递归先找左子树
		if(cmplo < 0) keys(x.left, queue, lo, hi);
		//加入当前节点至queue
		if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
		//当前结点小于范围上限，则递归再找右子树
		if(cmphi > 0) keys(x.right, queue, lo, hi);
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
		BST<String, Integer> st = new BST<>();
		for(int i=0; i<list.size(); i++) {
			st.put(list.get(i), i);
		}
		for(String s : st.keys()) {
			System.out.println(s);
		}
	}
}
