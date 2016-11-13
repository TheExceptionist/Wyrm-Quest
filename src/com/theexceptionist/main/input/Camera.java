package com.theexceptionist.main.input;

import com.theexceptionist.main.GameMain;
import com.theexceptionist.main.entity.Player;
import com.theexceptionist.main.math.Vector;

public class Camera {
	private int x, y, velX, velY;
	private Vector target;
	
	public Camera(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	//Not used yet
	public void tick(Player p){
		x = -p.getX() + 100;
		y = -p.getY() + 100;
		
		/*if(x < ){
			
		}*/
		//System.out.println(x+" "+y+" "+p.getX()+" "+p.getY());
	}
}
