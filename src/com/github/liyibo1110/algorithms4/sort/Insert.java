package com.github.liyibo1110.algorithms4.sort;

/**
 * 插入排序
 * 1.运行时间和输入有关（本身越有序，越会节省排序时间）
 * 2.数据移动多（插入元素后，后面的元素都要位移）
 * 3.操作特征是：第一轮排前2个元素，第二轮排前3个元素，因为之前都是有序的，所以新元素比前一个元素大，则不需要再往前比较
 * 因此对于大部分已有序的数组，插入排序可能是最快的
 * @author liyibo
 *
 */
public class Insert extends BaseSort{

	public void sort(Comparable[] a) {
		for(int i=1; i<a.length; i++) {
			for(int j=i; j>0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
	}
	
	public void sort(String[] a, int lo, int hi, int d) {
		for(int i=lo; i<=hi; i++) {
			for(int j=i; j>lo && less(a[j], a[j-1], d); j--){
				exch(a, j, j-1);
			}
		}
	}
	
	/**
	 * 跳过前d个字符，比较后面的
	 */
	private boolean less(String v, String w, int d) {
		return v.substring(d).compareTo(w.substring(d)) < 0;
	}
	
	public static void main(String[] args) {
		Integer[] array = {4,6,7,9,3,5,1};
		Insert insert = new Insert();
		insert.sort(array);
		if(insert.isSorted(array)) {
			insert.show(array);
		}
	}
}
