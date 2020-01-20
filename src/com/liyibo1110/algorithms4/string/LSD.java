package com.liyibo1110.algorithms4.string;

import java.util.ArrayList;
import java.util.List;

public class LSD {

	public static void sort(String[] a, int W) {
		
		int N = a.length;
		int R = 256;
		String[] aux = new String[N];
		for(int d=W-1; d>=0; d--) {
			int[] count = new int[R+1];	//计算出现频率
			for(int i=0; i<N; i++) {
				count[a[i].charAt(d) + 1]++;
			}
			for(int r=0; r<R; r++) {	//将频率转换为索引
				count[r+1] += count[r];
			}
			for(int i=0; i<N; i++) {	//将元素根据索引表，进行分类
				aux[count[a[i].charAt(d)]++] = a[i];
				//aux里面已经排好序了
			}
			for(int i=0; i<N; i++) {	//回写覆盖原来的数组内容
				a[i] = aux[i];
			}
		}
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("4PGC938");
		list.add("2IYE230");
		list.add("3CI0720");
		list.add("1ICK750");
		list.add("10HV845");
		
		list.add("4JZY524");
		list.add("1ICK750");
		list.add("3CI0720");
		list.add("10HV845");
		list.add("10HV845");
		
		list.add("2RLA629");
		list.add("2RLA629");
		list.add("3ATW723");
		String[] a = new String[list.size()];
		list.toArray(a);
		for(String s : a) {
			System.out.println(s);
		}
		sort(a, 7);
		System.out.println("排序后");
		for(String s : a) {
			System.out.println(s);
		}
	}
}
