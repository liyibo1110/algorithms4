package com.liyibo1110.algorithms4.sort;

public class MaxPQ<T extends Comparable<T>>{

	private T[] pq;
	private int N = 0;
	
	public MaxPQ() {
		pq = (T[])new Comparable[1000 + 1];
	}
	
	public MaxPQ(int max) {
		pq = (T[])new Comparable[max + 1];
	}
	
	public MaxPQ(T[] a) {
		pq = a;
	}
	
	/**
	 * 插入元素
	 * @param v
	 */
	public void insert(T v) {
		//先放到最后，然后尝试上浮
		pq[++N] = v;
		swim(N);
	}
	
	/**
	 * 返回最大元素
	 * @return
	 */
	public T max() {
		return pq[1];
	}
	
	/**
	 * 删除并返回最大元素
	 * @return
	 */
	public T delMax() {
		T max = pq[1];
		exch(1, N--);	//将最后一个节点置换进来，当做老大
		pq[N+1] = null;
		sink(1);	//让他下潜到合适的位置
		return max;
	}
	
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	/**
	 * 上浮元素至正确位置
	 * @param k
	 */
	private void swim(int k) {
		//k没到起点，并且比上层大
		while(k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	
	/**
	 * 下潜元素至正确位置
	 * @param k
	 */
	private void sink(int k) {
		//无脑往下
		while(2*k <= N) {
			int j = 2*k;
			//尝试向更大的子节点下潜
			if(j < N && less(j, j+1)) j++;
			if(less(j, k)) break;	//如果不比子节点更小，则停止下潜
			exch(k, j);	//下潜一步
			k = j;
		}
	}
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j) {
		T t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
}
