package com.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {

	private final int V;	//顶点数目
	private int E;			//边数目
	private Bag<Integer>[] adj;	//邻接表
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[])new Bag[V];
		for(int v=0; v<V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	
	public Graph(In in) {
		this(in.readInt());	//读取V并初始化
		int E = in.readInt();	//继续读取E
		for(int i=0; i<E; i++) {
			//添加一条边
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int degree(int v) {
		int degree = 0;
		for(int w : adj(v)) {
			degree++;
		}
		return degree;
	}
	
	public int maxDegree() {
		int max = 0;
		for(int v=0; v<V(); v++) {
			if(degree(v) > max) {
				max = degree(v);
			}
		}
		return max;
	}
	
	public double avgDegree() {
		return 2*E()/V();
	}
	
	public int numberOfSelfLoops() {
		int count = 0;
		for(int v=0; v<V(); v++) {
			for(int w : adj(v)) {
				if(v == w) count++;
			}
		}
		return count/2;
	}
	
	@Override
	public String toString() {
		String s = V + " vertices, " + E + " edges\n";
		for(int v=0; v<V; v++) {
			s += v + ": ";
			for(int w : adj(v)) {
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}
}
