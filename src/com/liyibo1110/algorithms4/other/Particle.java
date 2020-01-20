package com.liyibo1110.algorithms4.other;

public class Particle {

	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private double s;
	
	private double mass;

	//画出粒子
	public void draw() {
		
	}
	
	//根据时间流逝dt，改变粒子的位置
	public void move(double dt) {
		
	}
	
	//参与碰撞的总数
	public int count() {
		return 0;
	}
	
	//距离另外粒子b碰撞所需的时间
	public double timeToHit(Particle b) {
		return 0.0d;
	}
	
	//距离水平墙体碰撞所需的时间
	public double timeToHitHorizontalWall() {
		return 0.0d;
	}
	
	//距离垂直墙体碰撞所需的时间
	public double timeToHitVerticalWall() {
		return 0.0d;
	}
	
	//与另外粒子b碰撞后的速度
	public double bounceOff(Particle b) {
		return 0.0d;
	}
		
	//与水平墙体碰撞后的速度
	public double bounceOffHorizontalWall() {
		return 0.0d;
	}
		
	//与垂直墙体碰撞后的速度
	public double bounceOffVerticalWall() {
		return 0.0d;
	}
	
	public double getRx() {
		return rx;
	}

	public void setRx(double rx) {
		this.rx = rx;
	}

	public double getRy() {
		return ry;
	}

	public void setRy(double ry) {
		this.ry = ry;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getS() {
		return s;
	}

	public void setS(double s) {
		this.s = s;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}
	
}
