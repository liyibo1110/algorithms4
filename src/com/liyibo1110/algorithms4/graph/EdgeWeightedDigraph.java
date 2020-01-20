package com.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {

	private final int V;	//顶点数目
	private int E;			//边数目
	private Bag<DirectedEdge>[] adj;	//邻接表
	
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[])new Bag[V];
		for(int v=0; v<V; v++) {
			adj[v] = new Bag<DirectedEdge>();
		}
	}
	
	public EdgeWeightedDigraph(In in) {
		this(in.readInt());	//读取V并初始化
		int E = in.readInt();	//继续读取E
		for(int i=0; i<E; i++) {
			//添加一条边
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			addEdge(new DirectedEdge(v, w, weight));
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
		E++;
	}
	
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> b = new Bag<>();
		for(int v=0; v<V; v++) {
			for(DirectedEdge e: adj[v]) {
					b.add(e);
			}
		}
		return b;
	}
}
