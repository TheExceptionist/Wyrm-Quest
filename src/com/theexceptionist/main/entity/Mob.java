package com.theexceptionist.main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.theexceptionist.main.GameMain;
import com.theexceptionist.main.HUD;
import com.theexceptionist.main.assets.Assets;
import com.theexceptionist.main.assets.Jukebox;

public class Mob extends Entity{
	public static int AIWander = 0, AIStatic = 1; 
	public int AIPackage;
	private Rectangle lineOfSight;
	private boolean facingLeft = false;
	private int sec, n;
	private Random r;
	private int coolDown = 0;
	private int waitTime;
	private boolean hasRelic = false;

	public Mob(String name, int type, int health, int[] level,
			int[] attProficient, int[] defProficient, int attack, int armor, int AIPackage, Rectangle sight) {
		super(name, type, health, level, attProficient, defProficient, attack, armor);
		this.AIPackage = AIPackage;
		this.lineOfSight = sight;
		this.r = new Random();
		this.waitTime = r.nextInt(50)+20;
		//this.falling = false;
		this.velX = 2 - r.nextInt(4);
		if(r.nextInt(100) <= 25){
			hasRelic = true;
		}
	}
	
	public void tick(){
		super.tick();
		
		updateLineOfSight();
		
		if(AIPackage == AIStatic){
			AiStatic();
		}
		
		if(AIPackage == AIWander){
			AiWander();
		}
		
		if(coolDown > 0){
			coolDown--;
		}
		
		if(waitTime > 0){
			waitTime--;
		}
	}
	
	public void AiWander(){
		for(GameObject o : GameMain.h.objects){
			if(this.getCollisionBox().intersects(o.getCollisionBox())){
				if(o.type == GameObject.type_standstillwall){
					this.velX *= -1;
				}
				if(o.type == GameObject.type_standstill){
					this.falling = false;
				}else{
					this.falling = true;
				}
			}
			
			if(o.type == GameObject.type_player){
				if(this.getLineOfSight().intersects(o.getCollisionBox()) && coolDown <= 0){
					this.attack((Player)o);
					o.knockback();
					coolDown = 200;
				}
			}
		}
	}
	
	public void AiStatic(){
		this.velX = 0;
		this.velY = 0;
		
		if(this.facing == -1){
			facingLeft = true;
			
			if(lineOfSight.x + lineOfSight.width > this.x){
				lineOfSight.x -= lineOfSight.width/2;
			}
		}
		if(this.facing == 1){
			facingLeft = false;
			
			if(lineOfSight.x + lineOfSight.width < this.x){
				lineOfSight.x += lineOfSight.width/2;
			}
		}
		
		for(int i = 0; i < GameMain.h.objects.size(); i++){
			GameObject tempObject = GameMain.h.objects.get(i);
			
			if(tempObject.type == GameObject.type_player){
				if(this.getLineOfSight().intersects(tempObject.getCollisionBox()) && coolDown <= 0){
					this.attack((Player)tempObject);
					tempObject.knockback();
					coolDown = 200;
				}
			}
		}
		
		if(waitTime <= 0){
			facing *= -1;
			waitTime = r.nextInt(50)+20;
			//System.out.println("Working "+facing);
		}
		//System.out.println(waitTime);
		//System.out.println(facingLeft);
		
	}
	
	public void attack(Player p){
		super.attack();
		p.setDamage(this);
	}
	
	public void updateLineOfSight(){
		lineOfSight.x = this.x;
		lineOfSight.y = this.y;
	}
	
	public void die(){
		super.die(GameMain.h);
		if(this.hasRelic){
			GameMain.h.awardRelic();
		}
		HUD.message = this.name+" Died!";
		HUD.life = 100;
	}
	
	public void render(Graphics g){
		super.render(g);
		n++;
		if(n == 20){
			sec++;
			n = 0;
			
			if(hurt){
				hurt = false;	
			}
		}
		//if(shouldRender){
		if(this.name == "Lizard"){
			if(facing == 1){
			if(sec == 0 && !hurt && !attacking){
				g.drawImage(Assets.lizardmen[0], x - 20, y - 15, 64, 64, null);
			}
			if(sec == 1 && !hurt && !attacking){
				g.drawImage(Assets.lizardmen[1], x - 20, y - 15, 64, 64, null);
			}
			if(sec == 2 && !hurt && !attacking){
				g.drawImage(Assets.lizardmen[0], x - 20, y - 15, 64, 64, null);
				sec = 0;
			}
			
			if(hurt && !attacking){
				int rand = r.nextInt(3);
				if(rand == 0){
					Jukebox.play("lizardhurt");
				}else if(rand == 1){
					Jukebox.play("lizardhurt2");
				}else{
					Jukebox.play("lizardhurt3");
				}
				g.drawImage(Assets.lizardmen[3], x - 20, y - 15, 64, 64, null);
			}
			
			if(attacking){
				if(sec == 0){
					g.drawImage(Assets.lizardmen[2], x - 20, y - 15, 64, 64, null);
				}
				if(sec == 1){
					g.drawImage(Assets.lizardmen[4], x - 20, y - 15, 64, 64, null);
				}
				if(sec == 2){
					g.drawImage(Assets.lizardmen[2], x - 20, y - 15, 64, 64, null);
					sec = 0;
				}
			}
			}else{
				if(sec == 0 && !hurt && !attacking){
					g.drawImage(Assets.lizardmen[6], x - 20, y - 15, 64, 64, null);
				}
				if(sec == 1 && !hurt && !attacking){
					g.drawImage(Assets.lizardmen[5], x - 20, y - 15, 64, 64, null);
				}
				if(sec == 2 && !hurt && !attacking){
					g.drawImage(Assets.lizardmen[6], x - 20, y - 15, 64, 64, null);
					sec = 0;
				}
				
				if(hurt && !attacking){
					int rand = r.nextInt(3);
					if(rand == 0){
						Jukebox.play("lizardhurt");
					}else if(rand == 1){
						Jukebox.play("lizardhurt2");
					}else{
						Jukebox.play("lizardhurt3");
					}
					g.drawImage(Assets.lizardmen[8], x - 20, y - 15, 64, 64, null);
				}
				
				if(attacking){
					if(sec == 0){
						g.drawImage(Assets.lizardmen[9], x - 20, y - 15, 64, 64, null);
					}
					if(sec == 1){
						g.drawImage(Assets.lizardmen[7], x - 20, y - 15, 64, 64, null);
					}
					if(sec == 2){
						g.drawImage(Assets.lizardmen[9], x - 20, y - 15, 64, 64, null);
						sec = 0;
					}
				}
				}
			}
		
		if(this.name == "Pilgrim"){
			if(sec == 0){
				g.drawImage(Assets.pilgrim[0], x - 20, y - 15, 20, 32, null);
			}
			if(sec == 1){
				g.drawImage(Assets.pilgrim[1], x - 20, y - 15, 20, 32, null);
			}
			if(sec == 2){
				g.drawImage(Assets.pilgrim[2], x - 20, y - 15, 20, 32, null);
			}
			if(sec == 3){
				g.drawImage(Assets.pilgrim[3], x - 20, y - 15, 20, 32, null);
			}
			if(sec == 4){
				g.drawImage(Assets.pilgrim[4], x - 20, y - 15, 20, 32, null);
			}
			if(sec == 5){
				g.drawImage(Assets.pilgrim[5], x - 20, y - 15, 20, 32, null);
			}
			if(sec == 6){
				g.drawImage(Assets.pilgrim[6], x - 20, y - 15, 20, 32, null);
			}
			if(sec == 7){
				g.drawImage(Assets.pilgrim[7], x - 20, y - 15, 20, 32, null);
			}
			if(sec == 8){
				g.drawImage(Assets.pilgrim[8], x - 20, y - 15, 20, 32, null);
			}
			if(sec == 9){
				g.drawImage(Assets.pilgrim[0], x - 20, y - 15, 20, 32, null);
				sec = 0;
			}
		}
			/*g.setColor(Color.WHITE);
			g.drawRect(lineOfSight.x, lineOfSight.y, lineOfSight.width, lineOfSight.height);*/
		}
			//}
	
	
	public Rectangle getLineOfSight(){
		return lineOfSight;
	}

}
