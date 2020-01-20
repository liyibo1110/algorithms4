package com.github.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {

	private boolean[] marked;
	private int[] edgeTo;	//起点到另一个顶点，已知路径上最后一个顶点
	private Stack<Integer> cycle;	//如果有环，则保存环中所有顶点
	private boolean[] onStack;	//递归栈当前的所有顶点
	
	public DirectedCycle(Digraph G) {
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		for(int v=0; v<G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
			}
		}
	}
	
	private void dfs(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(hasCycle()) {
				return;	//有环了直接退出
			}else if(!marked[w]){
				edgeTo[w] = v;
				dfs(G, w);
			}else if(onStack[w]) {	//栈里有，说明是环
				cycle = new Stack<>();
				for(int x=v; x!=w; x=edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		//从栈里清除这个当前结点标记
		onStack[v] = false;
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
}
