package com.github.liyibo1110.algorithms4.graph;

public class CC {

	private boolean[] marked;
	private int[] id;
	private int count;
	
	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		//探索所有顶点
		for(int s=0;s<G.V();s++) {
			if(!marked[s]) {
				dfs(G, s);
				//每当完成了一次dfs，则找到1个连通分量
				count++;	
			}
		}
	}
	
	public void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;	//借用，标记新的连通分量编号
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	
	public int id(int v) {
		return id[v];
	}
	
	public int count() {
		return count;
	}
}
