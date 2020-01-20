package com.github.liyibo1110.algorithms4.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 希尔排序
 * 1.加强版的插入排序
 * @author liyibo
 *
 */
public class Quick extends BaseSort{

	public void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length-1);
	}
	
	private void sort(Comparable[] a, int lo, int hi) {
		if(hi<=lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);	//左半排序
		sort(a, j+1, hi);	//右半排序
	}
	
	public int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi+1;
		Comparable v = a[lo];
		
		while(true) {
			while(less(a[++i], v)) {	//左侧找到比v还要大的
				if(i == hi) break;
			}
			while(less(v, a[--j])) {	//右侧找到比v还要小的
				if(j == lo) break;
			}
			if(i>=j) break;
			exch(a, i, j);	//交换顺序之后再继续找
		}
		exch(a, lo, j);
		return j;
	}
	
	public static void main(String[] args) {
		Integer[] array = {4,6,7,9,3,5,1};
		Quick quick = new Quick();
		quick.sort(array);
		if(quick.isSorted(array)) {
			quick.show(array);
		}
	}
}
