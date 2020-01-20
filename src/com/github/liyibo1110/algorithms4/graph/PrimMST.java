package com.github.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimMST {

	private Edge[] edgeTo;	//距离树最近的边
	private double[] distTo;	//
	private boolean[] marked;	//最小生成树的顶点
	private IndexMinPQ<Double> pq;	//有效的横切边
	
	public PrimMST(EdgeWeightedGraph G) {
		
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		for(int v=0; v<G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		pq = new IndexMinPQ<>(G.V());
		
		//处理0号顶点
		distTo[0] = 0.0d;
		pq.insert(0, 0.0);
		visit(G, 0);
		//处理直到没有任何有效横切边
		while(!pq.isEmpty()) {
			//将最近的顶点添加到树中
			visit(G, pq.delMin());
		}
	}
	
	public void visit(EdgeWeightedGraph G, int v) {
		//将顶点v添加到树中
		marked[v] = true;
		for(Edge e : G.adj(v)) {
			int w = e.other(v);
			if(marked[w]) continue;	//v-w失效
			if(e.weight() < distTo[w]) {
				//连接w和树的最佳边变为e
				edgeTo[w] = e;
				distTo[w] = e.weight();
				if(pq.contains(w)) {
					pq.changeKey(w, distTo[w]);
				}else {
					pq.insert(w, distTo[w]);
				}
			}
		}
	}
	
	public Iterable<Edge> edges(){
		return null;
	}
	
	public double weight() {
		return 0d;
	}
}
