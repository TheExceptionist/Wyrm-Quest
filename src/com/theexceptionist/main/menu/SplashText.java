package com.theexceptionist.main.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.theexceptionist.main.GameMain;
import com.theexceptionist.main.entity.GameObject;

public class SplashText extends GameObject {
	public int x, y, velX, velY, life;
	public Font f = new Font("Arial", Font.BOLD ,30);
	public String info;
	
	public SplashText(String name, int type, int x, int y, String info, int life){
		super(name, type);
		this.x = x;
		this.y = y;
		this.info = info;
		this.life = life;
		this.velY = -5;
	}
	
	public void tick(){
		this.x += velX;
		this.y += velY;
		
		if(this.life > 0){
			this.life--;
		}
		
		if(this.life <= 0){
			this.die(GameMain.h);
		}
	}
	
	public void render(Graphics g){
		g.setFont(f);
		g.setColor(Color.RED);
		g.drawString(this.info, x, y);
	}
}
