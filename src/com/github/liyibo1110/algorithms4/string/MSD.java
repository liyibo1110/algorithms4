package com.github.liyibo1110.algorithms4.string;

import java.util.ArrayList;
import java.util.List;

import com.github.liyibo1110.algorithms4.sort.Insert;

public class MSD {

	private static int R = 127;	//基数
	private static final int M = 0;	//小数组的切换阈值
	private static String[] aux;	//数据分类的辅助数组
	
	/**
	 * d可能超过形参数组，要返回-1表示字符串下标已越界
	 */
	private static int charAt(String s, int d) {
		if(d < s.length()) {
			return s.charAt(d);
		}else {
			return -1;
		}
	}
	
	private static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N-1, 0);
	}
	
	public static void sort(String[] a, int lo, int hi, int d) {
		
		//如果子字符串长度小于16，则直接用插入排序来排序数组
		if(hi <= lo + M) {
			new Insert().sort(a, lo, hi, d);
			return;
		}
		int[] count = new int[R+2];
		for(int i=lo; i<=hi; i++) {
			//System.out.println(i + ", " + a[i] + ", " + d + ", " + charAt(a[i], d));
			count[charAt(a[i], d) + 2]++;
		}
		for(int r=0; r<R+1; r++) {	//将频率转换为索引
			count[r+1] += count[r];
		}
		for(int i=lo; i<=hi; i++) {	//将元素根据索引表，进行分类
			aux[count[charAt(a[i], d) + 1]++] = a[i];
			//aux里面已经排好序了
		}
		for(int i=lo; i<=hi; i++) {	//回写覆盖原来的数组内容
			a[i] = aux[i-lo];
		}
		//递归的为每个字符为键进行排序
		for(int r=0; r<R; r++) {
			sort(a, lo+count[r], lo+count[r+1]-1, d+1);
		}
		
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("she");
		list.add("sells");
		list.add("seashells");
		list.add("by");
		list.add("the");
		
		list.add("sea");
		list.add("shore");
		list.add("the");
		list.add("shells");
		list.add("she");
		
		list.add("sells");
		list.add("are");
		list.add("surely");
		list.add("seashells");
		String[] a = new String[list.size()];
		list.toArray(a);
		for(String s : a) {
			System.out.println(s);
		}
		sort(a);
		System.out.println("排序后");
		for(String s : a) {
			System.out.println(s);
		}
		//System.out.println((int)"z".charAt(0));
	}
}
