package com.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstPaths {

	private boolean[] marked;	//到达该顶点的最短路径已知吗
	private int[] edgeTo;	//到达该顶点的已知路径上最后一个顶点
	private final int s;	//起点
	
	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new Queue<>();
		marked[s] = true;	//标记起点
		queue.enqueue(s);	//加入队列
		while(!queue.isEmpty()) {
			int v = queue.dequeue();
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					edgeTo[w] = v;
					marked[w] = true;
					queue.enqueue(w);
				}
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
