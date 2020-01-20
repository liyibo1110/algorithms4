package com.github.liyibo1110.algorithms4.graph;

import edu.princeton.cs.algs4.Stack;

public class SP {

	private DirectedEdge[] edgeTo;
	private double[] distTo;
	
	public SP(EdgeWeightedDigraph G, int s) {
		
	}
	
	public double distTo(int v) {
		return distTo[v];
	}
	
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<>();
		for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
			path.push(e);
		}
		return path;
	}
}
