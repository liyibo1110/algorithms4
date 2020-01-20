package com.github.liyibo1110.algorithms4.sort;

/**
 * 希尔排序
 * 1.加强版的插入排序
 * @author liyibo
 *
 */
public class MergeBU extends BaseSort{

	private Comparable[] aux;
	
	public void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for(int sz=1; sz<N; sz=sz+sz) {	//sz为子数组大小，每次加倍
			for(int lo=0; lo<N-sz; lo+=sz+sz) {	//lo为子数组索引
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
			}
		}
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
		MergeBU merge = new MergeBU();
		merge.sort(array);
		if(merge.isSorted(array)) {
			merge.show(array);
		}
	}
}
