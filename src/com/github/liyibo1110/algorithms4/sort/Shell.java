package com.github.liyibo1110.algorithms4.sort;

/**
 * 希尔排序
 * 1.加强版的插入排序
 * @author liyibo
 *
 */
public class Shell extends BaseSort{

	public void sort(Comparable[] a) {
		
		int h = 1;
		while(h < a.length/3) {
			h = 3*h+1;	//1,4,13,40,121,364,1092, ...
		}
		
		while(h >= 1) {
			for(int i=h; i<a.length; i++) {
				for(int j=i; j>=h && less(a[j], a[j-h]); j-=h) {
					exch(a, j, j-h);
				}
			}
			h = h/3;
		}
		
		
	}
	
	public static void main(String[] args) {
		Integer[] array = {4,6,7,9,3,5,1};
		Shell shell = new Shell();
		shell.sort(array);
		if(shell.isSorted(array)) {
			shell.show(array);
		}
	}
}
