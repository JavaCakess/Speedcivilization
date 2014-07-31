package org.fountanio.ent;

public abstract class Entity implements Ent {
	
	public int x, y, w, h;
	
	public Entity(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public abstract void render();
	public abstract void update();
	
	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		this.x = x;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y = y;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setW(int w) {
		// TODO Auto-generated method stub
		this.w = w;
	}

	@Override
	public void setH(int h) {
		// TODO Auto-generated method stub
		this.h = h;
	}

	@Override
	public int getW() {
		// TODO Auto-generated method stub
		return w;
	}

	@Override
	public int getH() {
		// TODO Auto-generated method stub
		return h;
	}

	
}
