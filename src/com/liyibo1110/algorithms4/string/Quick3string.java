package com.liyibo1110.algorithms4.string;

import java.util.ArrayList;
import java.util.List;

import com.liyibo1110.algorithms4.sort.Insert;

public class Quick3string {

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
		sort(a, 0, a.length-1, 0);
	}
	
	public static void sort(String[] a, int lo, int hi, int d) {
		if(hi<=lo) return;
		//lo和hi是定值，lt和gt是游标，用来遍历
		int lt = lo, gt = hi;
		int v = charAt(a[lo], d);	//此轮的切分字符
		int i = lo + 1;	//跳过切分字符对应首字符串，从第2个开始移动位置
		while(i<=gt) {
			int t = charAt(a[i], d);
			if(t<v) {
				exch(a, lt++, i++);
			}else if(t>v){
				exch(a, i, gt--);	//放到最右边，同时右界-1
			}else {
				i++;
			}
		}
		//递归排序左中右3个分段
		sort(a, lo, lt-1, d);
		if(v>=0) sort(a, lo, gt, d+1);
		sort(a, gt+1, hi, d);
	}
	
	public static void exch(String[] a, int i, int j) {
		String t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("edu.princeton.cs");
		list.add("com.apple");
		list.add("edu.princeton.cs");
		list.add("com.cnn");
		list.add("com.google");
		
		list.add("edu.uva.cs");
		list.add("edu.princeton.cs");
		list.add("edu.princeton.cs.www");
		list.add("edu.uva.cs");
		list.add("edu.uva.cs");
		
		list.add("edu.uva.cs");
		list.add("com.adobe");
		list.add("edu.princeton.ee");
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
