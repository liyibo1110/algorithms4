package com.github.liyibo1110.algorithms4.sort;

/**
 * 选择排序
 * 1.运行时间和输入无关（就算本身已有序或者都一样，并不会节省排序时间）
 * 2.数据移动最少（即每次只会交换2个元素的值，交换次数和数组大小为线性N的关系）
 * 3.操作特征是：假定当轮首元素为默认最小，通过遍历来找出本轮最小下标，然后交换首元素和最小下标的元素
 * @author liyibo
 *
 */
public class Select extends BaseSort{

	public void sort(Comparable[] a) {
		for(int i=0; i<a.length; i++) {
			int min = i;	//首元素下标，假定第一个最小
			for(int j=i+1; j<a.length; j++) {
				//内循环，找出本轮最小的元素，修改下标
				if(less(a[j], a[min]))	min = j;
			}
			exch(a, i, min);	//强制交换首元素和最小元素（下标可能一样）
		}
	}
	
	public static void main(String[] args) {
		Integer[] array = {4,6,7,9,3,5,1};
		Select select = new Select();
		select.sort(array);
		if(select.isSorted(array)) {
			select.show(array);
		}
	}
}
