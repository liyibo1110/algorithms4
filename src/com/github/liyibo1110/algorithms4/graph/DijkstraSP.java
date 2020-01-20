package com.github.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class DijkstraSP {

	private DirectedEdge[] edgeTo;	//距离树最近的边
	private double[] distTo;	//
	private IndexMinPQ<Double> pq;	//有效的横切边
	
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<>(G.V());
		for(int v=0; v<G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		//处理0号顶点
		distTo[s] = 0.0d;
		pq.insert(s, 0.0);
		//处理直到没有任何有效横切边
		while(!pq.isEmpty()) {
			//将最近的顶点添加到树中
			relax(G, pq.delMin());
		}
	}
	
	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				//连接w和树的最佳边变为e
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
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
