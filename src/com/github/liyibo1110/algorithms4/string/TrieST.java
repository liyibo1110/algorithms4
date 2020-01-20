package com.github.liyibo1110.algorithms4.string;

import edu.princeton.cs.algs4.Queue;

public class TrieST<V> {

	private static int R = 256;	//基数
	private Node root;
	
	private static class Node{
		private Object val;
		private Node[] next = new Node[R];
	}

	public V get(String key) {
		Node x = get(root, key, 0);
		if(x == null) {
			return null;
		}else {
			return (V)x.val;
		}
	}
	
	private Node get(Node x, String key, int d) {
		if(x == null) return null;
		if(d == key.length()) return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d+1);
	}
	
	public void put(String key, V val) {
		root = put(root, key, val, 0);
	}
	
	private Node put(Node x, String key, V val, int d) {
		//如果key存在于以x为根结点的子单词查找树，则更新关联值
		if(x == null) {
			x = new Node();
		}
		//到了key的最后一个字符，则给定val并返回
		if(d == key.length()) {
			x.val = val;
			return x;
		}
		char c = key.charAt(d);	//放置相应字符
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}
	
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}
	
	public Iterable<String> keysWithPrefix(String pre){
		Queue<String> q = new Queue<>();
		collect(get(root, pre, 0), pre, q);
		return q;
	}
	
	private void collect(Node x, String pre, Queue<String> q) {
		if(x == null) return;
		if(x.val != null) q.enqueue(pre);
		for(char c=0; c<R; c++) {
			collect(x.next[c], pre+c, q);
		}
	}
	
	public Iterable<String> keysThatMatch(String pat){
		Queue<String> q = new Queue<>();
		collect(root, "", pat, q);
		return q;
	}
	
	private void collect(Node x, String pre, String pat, Queue<String> q) {
		int d = pre.length();
		if(x == null) return;
		if(d == pat.length() && x.val != null) q.enqueue(pre);
		if(d == pat.length()) return;	//说明长度够了，但是还没找到，不用再递归下层了
		
		char next = pat.charAt(d);
		for(char c=0; c<R; c++) {
			if(next == '.' || next == c) {
				collect(x.next[c], pre+c, pat, q);
			}
		}
	}
	
	public String longestPrefixOf(String s) {
		int length = search(root, s, 0, 0);
		return s.substring(0, length);
	}
	
	private int search(Node x, String s, int d, int length) {
		if(x == null) return length;
		if(x.val != null) length = d;	//记录最近的一个有值的结点
		if(d == s.length()) return length;	//说明是完整匹配到了
		char c = s.charAt(d);
		return search(x.next[c], s, d+1, length);
	}
	
	public void delete(String key) {
		root = delete(root, key, 0);
	}
	
	private Node delete(Node x, String key, int d) {
		if(x == null) return null;
		if(d == key.length()) {
			x.val = null;
		}else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d+1);
		}
		//这一轮找完了
		if(x.val != null) return x;
		for(char c=0; c<R; c++) {
			if(x.next[c] != null) {
				return null;
			}
		}
		return null;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if(x == null) return 0;
		int count = 0;
		if(x.val != null) count++;
		for(char c=0; c<R; c++) {
			count += size(x.next[c]);
		}
		return count;
	}
}
