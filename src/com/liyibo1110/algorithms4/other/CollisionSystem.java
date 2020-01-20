package com.liyibo1110.algorithms4.other;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;

public class CollisionSystem {

	private MinPQ<Event> pq;	//优先队列
	private double t = 0.0d;	//模拟时钟
	private Particle[] particles;	//粒子数组
	
	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
	}
	
	private void predictCollistions(Particle a, double limit) {
		if(a == null) return;
		//判断和其他粒子碰撞的event是否要生成
		for(int i=0; i<particles.length; i++) {
			double dt = a.timeToHit(particles[i]);
			if(t+dt < limit) {
				pq.insert(new Event(t+dt, a, particles[i]));
			}
		}
		//判断墙碰撞的event是否要生成
		double dtX = a.timeToHitVerticalWall();
		if(t+dtX < limit) {
			pq.insert(new Event(t+dtX, a, null));
		}
		double dtY = a.timeToHitHorizontalWall();
		if(t+dtY < limit) {
			pq.insert(new Event(t+dtY, null, a));
		}
	}
	
	public void redraw(double limit, double Hz) {
		//重画所有粒子
		StdDraw.clear();
		for(int i=0; i<particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show(20);
		if(t < limit) {
			//粒子都为null，代表这个事件是重画所有粒子事件
			pq.insert(new Event(t+1.0/Hz, null, null));
		}
	}
	
	public void simulate(double limit, double Hz) {
		pq = new MinPQ<>();
		//添加所有粒子的碰撞事件
		for(int i=0; i<particles.length; i++) {
			predictCollistions(particles[i], limit);
		}
		//添加重画事件（最高优先级）
		pq.insert(new Event(0, null, null));
		while(!pq.isEmpty()) {
			Event event = pq.delMin();
			if(!event.isValid()) continue;
			for(int i=0; i<particles.length; i++) {
				particles[i].move(event.time - t);
			}
			t = event.time;	//因为是优先队列，所以t可以保证是不断累加的
			Particle a = event.a;
			Particle b = event.b;
			if(a != null && b != null) {	//2个粒子碰撞
				a.bounceOff(b);
			}else if(a != null && b == null) {	//撞到水平墙
				a.bounceOffHorizontalWall();
			}else if(a == null && b != null) {	//撞到垂直墙
				b.bounceOffVerticalWall();
			}else {
				redraw(limit, Hz);
			}
			predictCollistions(a, limit);
			predictCollistions(b, limit);
		}
	}
	
	private class Event implements Comparable<Event>{
		
		private final double time;
		private final Particle a, b;
		private final int countA, countB;
		
		public Event(double t, Particle a, Particle b) {
			//在时间t与a或者b相关的新事件
			this.time = t;
			this.a = a;
			this.b = b;
			if(a != null) {
				countA = a.count();
			}else {
				countA = -1;
			}
			if(b != null) {
				countB = b.count();
			}else {
				countB = -1;
			}
		}
		
		public boolean isValid(){
			//a或b的count不能和事件里的count不一致，不一致说明在别的事件中碰撞过了
			if(a != null && a.count() != countA) {
				return false;
			}
			if(b != null && b.count() != countB) {
				return false;
			}
			return true;
		}
		
		@Override
		public int compareTo(Event that) {
			if(this.time < that.time) {
				return -1;
			}else if(this.time > that.time) {
				return +1;
			}else {
				return 0;
			}
		}
	}
	
	public static void main(String[] args) {
		StdDraw.show(0);
		int N = Integer.parseInt(args[0]);
		Particle[] particles = new Particle[N];
		for(int i=0; i<N; i++) {
			particles[i] = new Particle();
		}
		CollisionSystem system = new CollisionSystem(particles);
		system.simulate(10000, 0.5);
	}
}
