package com.liyibo1110.algorithms4.string;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.Stack;

public class NFA {

	private char[] re;	//匹配转换
	private Digraph G;
	private int M;	//状态数量
	
	public NFA(String regexp) {
		Stack<Integer> ops = new Stack<>();
		re = regexp.toCharArray();	//如果是中文，要麻烦很多
		M = re.length;
		G = new Digraph(M+1);	//完成状态需要额外的位置
		
		for(int i=0; i<M; i++) {
			int lp = i;	//lp可变，i不可变
			//判断是否为(或|
			if(re[i] == '(' || re[i] == '|') {
				ops.push(i);
			}else if(re[i] == ')') {
				int or = ops.pop();
				if(re[or] == '|') {
					lp = ops.pop();
					G.addEdge(lp, or+1);
					G.addEdge(or, i);
				}else {
					//就是(
					lp = or;
				}
				//lp最终要么指向前一个(，要么指向当前i
			}
			//注意是判断下一个字符是否为*
			if(i < M-1 && re[i+1] == '*') {
				//建立双向边
				G.addEdge(lp, i+1);
				G.addEdge(i+1, lp);
			}
			//判断其他状态边
			if(re[i] == '(' || re[i] == '*' || re[i] == ')') {
				G.addEdge(i, i+1);
			}
		}
	}
	
	/**
	 * 该状态机能否识别txt文本
	 */
	public boolean recognizes(String txt) {
		Bag<Integer> pc = new Bag<>();
		DirectedDFS dfs = new DirectedDFS(G, 0);
		for(int v=0; v<G.V(); v++) {
			//遍历每个NFA状态，查看从状态0可否到达
			if(dfs.marked(v)) pc.add(v);
		}
		
		//遍历文本每个字符，都要遍历才可以，不能中途跳出for
		for(int i=0; i<txt.length(); i++) {
			//计算txt[i+1]可能到达的所有NFA状态
			Bag<Integer> match = new Bag<>();
			for(int v : pc) {
				if(v < M) {
					//如果v不是最终节点，尝试是否匹配或是通配，配上了则
					if(re[v] == txt.charAt(i) || re[v] == '.') {
						match.add(v+1);
					}
				}
			}
			
			pc = new Bag<>();
			//重置dfs，由状态0变成尝试匹配字符后的新状态
			dfs = new DirectedDFS(G, match);
			for(int v=0; v<G.V(); v++) {
				//遍历每个NFA状态，查看从匹配状态下一个位置，可否到达
				if(dfs.marked(v)) pc.add(v);
			}
		}
		
		for(int v : pc) {
			//都尝试过后再次检查目前可到达的状态，如果有终结状态，说明可以走通
			if(v == M) {
				return true;
			}
		}

		return false;
	}
}
