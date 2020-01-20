package com.github.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {

	private final int V;	//顶点数目
	private int E;			//边数目
	private Bag<Edge>[] adj;	//邻接表
	
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[])new Bag[V];
		for(int v=0; v<V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}
	
	public EdgeWeightedGraph(In in) {
		this(in.readInt());	//读取V并初始化
		int E = in.readInt();	//继续读取E
		for(int i=0; i<E; i++) {
			//添加一条边
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			addEdge(new Edge(v, w, weight));
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public Iterable<Edge> edges(){
		Bag<Edge> b = new Bag<>();
		for(int v=0; v<V; v++) {
			for(Edge e: adj[v]) {
				if(e.other(v) > v) {
					b.add(e);
				}
			}
		}
		return b;
	}
}
