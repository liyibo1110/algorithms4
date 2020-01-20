package com.github.liyibo1110.algorithms4.search;

public class SeparateChainingHashST<K extends Comparable<K>, V> {

	private int N;	//总节点数
	private int M;	//桶的大小
	private SequentialSearchST<K, V>[] st;	//每个桶的链表数组
	
	public SeparateChainingHashST() {
		this(997);
	}
	
	public SeparateChainingHashST(int M) {
		this.M = M;
		st = (SequentialSearchST<K, V>[])new SequentialSearchST[M];
		for(int i=0; i<M; i++) {
			st[i] = new SequentialSearchST<>();
		}
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public V get(K key) {
		return (V)st[hash(key)].get(key);
	}
	
	public void put(K key, V val) {
		st[hash(key)].put(key, val);
	}
}
