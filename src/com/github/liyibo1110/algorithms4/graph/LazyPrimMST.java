package com.github.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {

	private boolean[] marked;	//最小生成树的顶点
	private Queue<Edge> mst;	//最小生成树的边
	private MinPQ<Edge> pq;	//横切边（包括失效的边）
	
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<>();
		marked = new boolean[G.V()];
		mst = new Queue<>();
		//处理0号顶点
		visit(G, 0);
		//处理直到没有所有横切边
		while(!pq.isEmpty()) {
			//删除并返回权重最小的边，来进行下一个顶点的定位
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if(marked[v] && marked[w]) continue;	//失效则跳过这个边
			mst.enqueue(e);	//可以加到树里了
			if(!marked[v]) visit(G, v);
			if(!marked[w]) visit(G, w);
		}
	}
	
	public void visit(EdgeWeightedGraph G, int v) {
		//标记顶点v，并将所有和v连接（未被标记顶点）的边加入pq
		marked[v] = true;
		for(Edge e : G.adj(v)) {
			if(!marked[e.other(v)]) {
				pq.insert(e);
			}
		}
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight() {
		return 0d;
	}
}
