package com.liyibo1110.algorithms4.search;

import java.util.ArrayList;
import java.util.List;

public class ST<K extends Comparable<K>, V> {

	public void put(K key, V value) {
		
	}
	
	public V get(K key) {
		return null;
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
		return 0;
	}
	
	public K min() {
		return null;
	}
	
	public K max() {
		return null;
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
		return null;
	}
	
	/**
	 * 小于key的键的数量
	 * @param key
	 * @return
	 */
	public int rank(K key) {
		return 0;
	}
	
	/**
	 * 返回排名为k的key
	 * @param k
	 * @return
	 */
	public K select(int k) {
		return null;
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
		return null;
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
		ST<String, Integer> st = new ST<>();
		for(int i=0; i<list.size(); i++) {
			st.put(list.get(i), i);
		}
		for(String s : st.keys()) {
			System.out.println(s);
		}
	}
}
