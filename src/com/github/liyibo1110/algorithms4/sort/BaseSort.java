package com.github.liyibo1110.algorithms4.sort;

public abstract class BaseSort {

	public abstract void sort(Comparable[] a);
	
	/**
	 * 判断第1个元素，是否小于第2个元素
	 * @param v
	 * @param w
	 * @return
	 */
	public boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w) < 0);
	}
	
	/**
	 * 交换集合的2个元素
	 * @param a
	 * @param i
	 * @param j
	 */
	public void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public void show(Comparable[] a) {
		for(Comparable t : a) {
			System.out.println(t + " ");
		}
		System.out.println();
	}
	
	/**
	 * 检测给定数组是不是有序的（即由小到大）
	 * @param a
	 * @return
	 */
	public boolean isSorted(Comparable[] a) {
		for(int i=1; i<a.length; i++) {
			if(less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}
}
