package com.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class SymbolGraph {

	private ST<String, Integer> st;	//符号名->索引
	private String[] keys;	//索引->符号名
	private Graph G;
	
	public SymbolGraph(String stream, String sp) {
		st = new ST<>();
		In in = new In(stream);
		while(in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			for(int i=0; i<a.length; i++) {
				if(!st.contains(a[i])) {
					//又是借用size自增之前的值，来当做下标
					st.put(a[i], st.size());
				}
			}
		}
		keys = new String[st.size()];
		for(String name : st.keys()) {
			keys[st.get(name)] = name;
		}
		G = new Graph(st.size());
		//以上构造了顶点之间的索引，还要第二遍来构造图
		in = new In(stream);
		while(in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			//将顶点和该行其它顶点相连
			int v = st.get(a[0]);
			for(int i=1; i<a.length; i++) {
				G.addEdge(v, st.get(a[i]));
			}
		}
	}
	
	public boolean contains(String key) {
		return st.contains(key);
	}
	
	public int index(String key) {
		return st.get(key);
	}
	
	public String name(int v) {
		return keys[v];
	}
	
	public Graph G() {
		return G;
	}
}
