package com.liyibo1110.algorithms4.search;

public class LinearProbingHashST<K extends Comparable<K>, V> {

	private int N;	//总节点数
	private int M = 16;	//桶的大小
	private K[] keys;
	private V[] vals;
	
	public LinearProbingHashST() {
		keys = (K[])new Object[M];
		vals = (V[])new Object[M];
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	private void resize(int cap) {
		
	}
	
	public V get(K key) {
		//遍历数组，如果key存在，则一定在下一个空位之前有，否则就是没有
		for(int i=hash(key); keys[i]!=null; i=(i+1)%M) {
			if(keys[i].equals(key)) {
				return vals[i];
			}
		}
		return null;
	}
	
	public void put(K key, V val) {
		
		if(N>=M/2) resize(2*M);
		int i;
		for(i=hash(key); keys[i]!=null; i=(i+1)%M) {
			//原来的key则覆盖val
			if(keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		//到这里说明已找到了合适的空位i
		keys[i] = key;
		vals[i] = val;
		N++;
	}
}
