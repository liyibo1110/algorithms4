package com.github.liyibo1110.algorithms4.sort;

/**
 * 希尔排序
 * 1.加强版的插入排序
 * @author liyibo
 *
 */
public class Merge extends BaseSort{

	private Comparable[] aux;
	
	public void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, 0, a.length-1);
	}
	
	private void sort(Comparable[] a, int lo, int hi) {
		if(hi<=lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid);	//左半排序
		sort(a, mid+1, hi);	//右半排序
		merge(a, lo, mid, hi);
	}
	
	public void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo;
		int j = mid+1;
		
		//全部复制到aux数组里
		for(int k=lo; k<=hi; k++) {
			aux[k] = a[k];
		}
		
		for(int k=lo; k<=hi; k++) {
			if(i>mid) {	//左半边没了
				a[k] = aux[j++];
			}else if(j>hi) {	//右半边没了
				a[k] = aux[i++];
			}else if(less(aux[j], aux[i])) {
				a[k] = aux[j++];	//右半边比左半边的小
			}else {
				a[k] = aux[i++];	//右半边不比左半边小
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] array = {4,6,7,9,3,5,1};
		Merge merge = new Merge();
		merge.sort(array);
		if(merge.isSorted(array)) {
			merge.show(array);
		}
	}
}
