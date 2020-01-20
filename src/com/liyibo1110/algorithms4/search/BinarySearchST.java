package com.liyibo1110.algorithms4.search;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Queue;

public class BinarySearchST<K extends Comparable<K>, V> {

	private K[] keys;
	private V[] vals;
	private int N;
	
	public BinarySearchST(int capacity) {
		keys = (K[])new Comparable[capacity];
		vals = (V[])new Object[capacity];
	}
	
	public void put(K key, V val) {
		//先查找
		int i = rank(key);
		//找到相同key则直接更新，否则插入
		if(i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}else {
			//从右往左操作
			for(int j = N; j > i; j--) {
				keys[j] = keys[j-1];
				vals[j] = vals[j-1];
			}
			keys[i] = key;
			vals[i] = val;
			N++;
		}
	}
	
	public V get(K key) {
		if(isEmpty()) return null;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) {
			return vals[i];
		}else {
			return null;
		}
	}
	
	public void delete(K key) {
		put(key, null);
	}
	
	public boolean contains(K key) {
		return get(key) != null;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return N;
	}
	
	public K min() {
		return keys[0];
	}
	
	public K max() {
		return keys[N-1];
	}
	
	/**
	 * 小于等于key的最大键
	 * @param key
	 * @return
	 */
	public K floor(K key) {
		return null;
	}
	
	/**
	 * 大于等于key的最小键
	 * @param key
	 * @return
	 */
	public K ceiling(K key) {
		return keys[rank(key)];
	}
	
	/**
	 * 小于key的键的数量
	 * @param key
	 * @return
	 */
	public int rank(K key) {
		int lo = 0;
		int hi = N-1;
		while(lo <= hi) {
			int mid = lo + (hi - lo)/2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0) {	//在左边，减少hi
				hi = mid - 1;
			}else if(cmp > 0) {	//在在右边，增大lo
				lo = mid + 1;
			}else {
				return mid;
			}
		}
		return lo;
	}
	
	/**
	 * 返回排名为k的key
	 * @param k
	 * @return
	 */
	public K select(int k) {
		return keys[k];
	}
	
	public void deleteMin() {
		delete(min());
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
	
	public Iterable<K> keys(K lo, K hi){
		Queue<K> q = new Queue<>();
		for(int i=rank(lo); i<rank(hi); i++) {
			q.enqueue(keys[i]);
		}
		//上面不包含hi下标对应，还要追加
		if(contains(hi)) {
			q.enqueue(keys[rank(hi)]);
		}
		return q;
	}
	
	public Iterable<K> keys(){
		return keys(min(), max());
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
		BinarySearchST<String, Integer> st = new BinarySearchST<>(100);
		for(int i=0; i<list.size(); i++) {
			st.put(list.get(i), i);
		}
		for(String s : st.keys()) {
			System.out.println(s);
		}
	}
}
