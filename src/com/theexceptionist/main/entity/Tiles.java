package com.theexceptionist.main.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.theexceptionist.main.assets.Assets;

public class Tiles extends GameObject{
	public int variant;
	public int sec, a;
	private Random r;
	public boolean hasRelic;
	
	public Tiles(String name, int type) {
		super(name, type);
		this.r = new Random();
		
		if(this.name == "Grass"){
			variant = r.nextInt(4);
		}
		if(this.name == "Grass Wall"){
			variant = r.nextInt(5);
		}
		if(this.name == "Grass Bottom"){
			variant = r.nextInt(2);
		}
		if(this.name == "Wooden Wall"){
			variant = r.nextInt(3);
		}
		if(this.name == "Chest" && r.nextInt(100) < 10){
			this.hasRelic = true;
		}
	}
	
	public Tiles(String name, int type, int amount) {
		super(name, type);
		this.amount = amount;
	}
	
	public void render(Graphics g){
		a++;
		

		//if(shouldRender){
			super.render(g);
			if(this.type == GameObject.type_standstill){
				if(a == 60){
					sec++;
					a = 0;
				}
				if(this.name == "Grass" && variant == 0){
					g.drawImage(Assets.grass[0], x, y, 16,16, null);
				}
				if(this.name == "Grass" && variant == 1){
					g.drawImage(Assets.grass[1], x, y, 16,16, null);
				}
				if(this.name == "Grass" && variant == 2){
					if(sec == 0){
						g.drawImage(Assets.grass[2], x, y, 16,16, null);
					}
					if(sec == 1){
						g.drawImage(Assets.grass[3], x, y, 16,16, null);
					}
					if(sec == 2){
						g.drawImage(Assets.grass[4], x, y, 16,16, null);
					}
					if(sec == 3){
						g.drawImage(Assets.grass[2], x, y, 16,16, null);
						sec = 0;
					}
				}
				if(this.name == "Grass" && variant == 3){
					if(sec == 0){
						g.drawImage(Assets.grass[5], x, y, 16,16, null);
					}
					if(sec == 1){
						g.drawImage(Assets.grass[6], x, y, 16,16, null);
					}
					if(sec == 2){
						g.drawImage(Assets.grass[7], x, y, 16,16, null);
					}
					if(sec == 3){
						g.drawImage(Assets.grass[2], x, y, 16,16, null);
						sec = 0;
					}
				}
			}
			if(this.type == GameObject.type_standstillwall){
				if(this.name == "Grass Wall" && variant == 0){
					g.drawImage(Assets.dirt[0], x, y, 16,16, null);
				}
				if(this.name == "Grass Wall" && variant == 1){
					g.drawImage(Assets.dirt[1], x, y, 16,16, null);				
				}
				if(this.name == "Grass Wall" && variant == 2){
					g.drawImage(Assets.dirt[2], x, y, 16,16, null);
				}
				if(this.name == "Grass Wall" && variant == 3){
					g.drawImage(Assets.dirt[3], x, y, 16,16, null);
				}
				if(this.name == "Grass Wall" && variant == 4){
					g.drawImage(Assets.dirt[4], x, y, 16,16, null);
				}
			}
			if(this.type == GameObject.type_standstillbottom){
				if(this.name == "Grass Bottom" && variant == 0){
					g.drawImage(Assets.stone[0], x, y, 16,16, null);
				}
				if(this.name == "Grass Bottom" && variant == 1){
					g.drawImage(Assets.stone[1], x, y, 16,16, null);				
				}
			}
			if(this.name == "Gold"){
				if(a == 10){
					sec++;
					a = 0;
				}
				if(sec == 0){
					g.drawImage(Assets.coin[0], x, y, 16, 16, null);
				}
				if(sec == 1){
					g.drawImage(Assets.coin[1], x, y, 16, 16, null);
				}
				if(sec == 2){
					g.drawImage(Assets.coin[1], x, y, 16, 16, null);
					sec = 0;
				}
			}
			if(this.name == "Shop"){
				g.drawImage(Assets.shop[0], x - 16, y - 13, 32, 32, null);
			}
			if(this.name == "Tree"){
				if(a == 10){
					sec++;
					a = 0;
				}
				if(sec == 0){
					g.drawImage(Assets.tree[0], x - 16, y - 45, 32, 64, null);
				}else if(sec == 1){
					g.drawImage(Assets.tree[1], x - 16, y - 45, 32, 64, null);
				}else if(sec == 2){
					g.drawImage(Assets.tree[0], x - 16, y - 45, 32, 64, null);
					sec = 0;
				}
			}
			if(this.name == "Wooden Wall"){
				if(variant == 0){
					g.drawImage(Assets.wooden[0], x, y, 16, 16, null);
				}else if(variant == 1){
					g.drawImage(Assets.wooden[1], x, y, 16, 16, null);
				}else if(variant == 2){
					g.drawImage(Assets.wooden[2], x, y, 16, 16, null);
				}
			}
			if(this.name == "Ancient Relic"){
				g.drawImage(Assets.relic[0], x, y, 16, 16, null);
			}
			if(this.name == "Chest"){
				if(this.state == 0){
					g.drawImage(Assets.chest[0], x - 8, y - 10, 32, 32, null);
				}else{
					g.drawImage(Assets.chest[4], x - 8, y - 10, 32, 32, null);
				}
			}
			if(this.name == "Exit"){
				g.drawImage(Assets.door[0], x, y, 32, 32, null);
			}
		//}
	}
}
