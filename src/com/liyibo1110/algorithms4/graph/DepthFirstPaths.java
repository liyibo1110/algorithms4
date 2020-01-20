package com.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths {

	private boolean[] marked;
	private int[] edgeTo;	//起点到另一个顶点，已知路径上最后一个顶点
	private final int s;	//起点
	
	public DepthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		//先得能联通
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<>();
		for(int x=v; x!=s; x=edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}
}