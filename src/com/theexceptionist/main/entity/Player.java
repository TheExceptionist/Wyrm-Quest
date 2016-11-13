package com.theexceptionist.main.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.theexceptionist.main.GameMain;
import com.theexceptionist.main.HUD;
import com.theexceptionist.main.Handler;
import com.theexceptionist.main.assets.Assets;
import com.theexceptionist.main.assets.Jukebox;
import com.theexceptionist.main.input.InputHandler;

public class Player extends Entity{
	public InputHandler i;
	public int jumps = 0;
	public int gold = 0, relics = 0;
	private Random r;
	private int waitTime, coolDown;
	//Band-Aid get rid of soon
	private int coolDown2;
	
	private int sec, sec1,sec2, n;
	
	public Player(String name, int type, int health, int[] level, int[] attProficient, int[] defProficient, int attack, int armor, InputHandler i){
		super(name, type, health, level, attProficient, defProficient, attack, armor);
		this.level = level;
		this.i = i;
		this.r = new Random();
		this.waitTime = r.nextInt(500) + 500;
		
		init();
	}
	
	public Player(String name, int type, int health, int[] level, int[] attProficient, int[] defProficient, int attack, int armor, BufferedImage[] texture, InputHandler i){
		super(name, type, health, level, attProficient, defProficient, attack, armor, texture);
		this.level = level;
		this.i = i;
		
		init();
	}
	
	public void render(Graphics g){
		super.render(g);
		n++;
		
		if(facing == 1){
		if(!this.moving && !this.attacking && !hurt){
			//System.out.println(Assets.knight[0]);
			if(n == 20){
				sec++;
				n = 0;
			}
			if(sec == 0){
				g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
			}if(sec == 1){
				g.drawImage(Assets.knight[2], x, y - 8, 48, 48, null);
			}if(sec == 2){
				g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
				sec = 0;
			}
		}
		
		if(this.moving && !this.attacking && !hurt){
			if(n == 20){
				sec1++;
				n = 0;
			}
			if(sec1 == 0){
				g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
			}if(sec1 == 1){
				g.drawImage(Assets.knight[6], x, y - 8, 48, 48, null);
			}if(sec1 == 2){
				g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
				sec1 = 0;
			}
		}
		
		if(this.attacking && !hurt){
			if(n == 20){
				sec2++;
				n = 0;
			}
			if(sec2 == 0){
				g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
			}if(sec2 == 1){
				g.drawImage(Assets.knight[8], x, y - 8, 48, 48, null);
			}if(sec2 == 2){
				g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
				sec2 = 0;
				this.attacking = false;
			}
		}
		
		if(hurt){
			if(n == 20){
				sec2++;
				n = 0;
			}
			
			if(sec2 == 0){
				g.drawImage(Assets.knight[1], x, y - 8, 48, 48, null);
			}else if(sec2 == 1){
				g.drawImage(Assets.knight[1], x, y - 8, 48, 48, null);
				hurt = false;
				sec2 = 0;
			}else{
				g.drawImage(Assets.knight[1], x, y - 8, 48, 48, null);
				sec2 = 1;
			}
		}
		}else{
			if(!this.moving && !this.attacking && !hurt){
				//System.out.println(Assets.knight[0]);
				if(n == 20){
					sec++;
					n = 0;
				}
				if(sec == 0){
					g.drawImage(Assets.knight[12], x, y - 8, 48, 48, null);
				}if(sec == 1){
					g.drawImage(Assets.knight[10], x, y - 8, 48, 48, null);
				}if(sec == 2){
					g.drawImage(Assets.knight[12], x, y - 8, 48, 48, null);
					sec = 0;
				}
			}
			
			if(this.moving && !this.attacking && !hurt){
				if(n == 20){
					sec1++;
					n = 0;
				}
				if(sec1 == 0){
					g.drawImage(Assets.knight[12], x, y - 8, 48, 48, null);
				}if(sec1 == 1){
					g.drawImage(Assets.knight[18], x, y - 8, 48, 48, null);
				}if(sec1 == 2){
					g.drawImage(Assets.knight[12], x, y - 8, 48, 48, null);
					sec1 = 0;
				}
			}
			
			if(this.attacking && !hurt){
				if(n == 20){
					sec2++;
					n = 0;
				}
				if(sec2 == 0){
					g.drawImage(Assets.knight[12], x, y - 8, 48, 48, null);
				}if(sec2 == 1){
					g.drawImage(Assets.knight[16], x, y - 8, 48, 48, null);
				}if(sec2 == 2){
					g.drawImage(Assets.knight[12], x, y - 8, 48, 48, null);
					sec2 = 0;
					this.attacking = false;
				}
			}
			
			if(hurt){
				if(n == 20){
					sec2++;
					n = 0;
				}
				
				if(sec2 == 0){
					g.drawImage(Assets.knight[11], x, y - 8, 48, 48, null);
				}else if(sec2 == 1){
					g.drawImage(Assets.knight[11], x, y - 8, 48, 48, null);
					if(r.nextInt(2) == 0){
						Jukebox.play("playerhurt");
					}else{
						Jukebox.play("playerhurt2");
					}
					hurt = false;
					sec2 = 0;
				}else{
					g.drawImage(Assets.knight[11], x, y - 8, 48, 48, null);
					sec2 = 1;
				}
			}
		}
		
		
		
		//g.setColor(Color.RED);
		//g.drawRect(this.x, this.y, 40, this.height);
		/*}else if(sec == 3){
			g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
		}else if(sec == 4){
			g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
		}else if(sec == 5){
			g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
		}else if(sec == 6){
			g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
		}else if(sec == 7){
			g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
		}else if(sec == 8){
			g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
		}else if(sec == 9){
			g.drawImage(Assets.knight[0], x, y - 8, 48, 48, null);
		}*/
	}
	
	public void levelUp(){
		Jukebox.play("level");
		int increase = r.nextInt(20)+5;
		this.MaxHealth += increase;
		this.health = this.MaxHealth;
		this.level[0]++;
	}
	
	public void tick(){
		super.tick();
		//velX = 5;
		
		if((this.attack+this.defProficient[0]+this.attProficient[0]+this.armor)/4 > this.level[0] * 10 || this.relics == this.level[0] * 2){
			levelUp();
		}
		
		if(coolDown > 0){
			coolDown--;
		}
		
		if(coolDown2 > 0){
			coolDown2--;
		}
		
		if(i.up.down &&!jumping){
			jumps++;
			jumping = true;
			
			Jukebox.play("jump");
			
			if(jumps < 2){
				falling = false;
				moveUp();
			}
			
			
			//System.out.println(velX+" "+velY);
		}else
		if(i.right.down){
			moveRight();
			if(r.nextInt(2) == 0){
				if(Jukebox.isPlaying("walk") || Jukebox.isPlaying("walk2"))
				Jukebox.play("walk");
			}else{
				if(Jukebox.isPlaying("walk") || Jukebox.isPlaying("walk2"))
				Jukebox.play("walk2");
			}
		}else
		if(i.left.down){
			moveLeft();
			if(r.nextInt(2) == 0){
				if(Jukebox.isPlaying("walk1") || Jukebox.isPlaying("walk2"))
				Jukebox.play("walk1");
			}else{
				if(Jukebox.isPlaying("walk1") || Jukebox.isPlaying("walk2"))
				Jukebox.play("walk2");
			}
		}else
		if(i.down.down){
			moveDown();
			if(Jukebox.isPlaying("walk") || Jukebox.isPlaying("walk2"))
			Jukebox.play("walk2");
		}else{
			stop();
		}
		
		if(i.attack.down){
			attack();
			if(r.nextInt(2) == 0){
				Jukebox.play("sword");
			}else{
				Jukebox.play("sword2");
			}
		}
		
		if(i.attack.down){
			attack();
			if(r.nextInt(2) == 0){
				Jukebox.play("sword");
			}else{
				Jukebox.play("sword2");
			}
		}
		for(int n = 0; n < GameMain.h.objects.size(); n++){
			GameObject tempObject = GameMain.h.objects.get(n);
			if(tempObject.type == GameObject.type_shop){
				if(this.getCollisionBox().intersects(tempObject.getCollisionBox())){
					int amount;
					if(i.heart.down && coolDown <= 0){
						amount = Math.round((this.MaxHealth - this.health)/2);
					if(this.gold > amount){
						this.gold -= amount;
						HUD.message = amount+" Gold Spent";
						HUD.life = 100;
						this.health += 5;
						this.coolDown = 10;
					}else{
						HUD.message = "Not Enough Gold";
						HUD.life = 100;
						this.coolDown = 10;	
					}
					}else
					if(i.weapon.down && coolDown <= 0){
						amount = Math.round(this.attack/2);
					if(this.gold > amount){
						this.attack += 2;
						this.gold -= amount;
						HUD.message = amount+" Gold Spent";
						HUD.life = 100;
						this.coolDown = 10;
					}else{
						HUD.message = "Not Enough Gold";
						HUD.life = 100;
						this.coolDown = 10;	
					}
					}else
					if(i.defense.down && coolDown <= 0){
						amount = Math.round(this.armor/2);
						if(this.gold > amount){
							this.armor += 2;
							this.gold -= amount;
							HUD.message = amount+" Gold Spent";
							HUD.life = 100;
							this.coolDown = 10;
						}else{
							HUD.message = "Not Enough Gold";
							HUD.life = 100;
							this.coolDown = 10;	
						}
					}
				}
			}
		}
		addProficient();
	}
	
	public void addProficient(){
		if(waitTime > 0){
			waitTime--;
		}
		if(waitTime <= 0){
			this.defProficient[0] += r.nextInt(3);
			this.defProficient[1] += r.nextInt(3);
			this.defProficient[2] += r.nextInt(3);
			this.defProficient[3] += r.nextInt(3);
			
			this.attProficient[0] += r.nextInt(3);
			this.attProficient[1] += r.nextInt(3);
			this.attProficient[2] += r.nextInt(3);
			this.attProficient[3] += r.nextInt(3);
			
			waitTime = r.nextInt(500) + 500;
		}
	}
	
	public void attack(){
		super.attack();
		for(int i = 0; i < GameMain.h.objects.size(); i++){
			GameObject tempObject = GameMain.h.objects.get(i);
			if(tempObject.type == GameObject.type_mob){
				//System.out.println(facing);
				if(facing == 0 && tempObject.x  + tempObject.width < this.x + 40 && this.x  > tempObject.x + tempObject.width){
					//System.out.println("Working 2 "+facing);
					((Entity) tempObject).setDamage(this);
				}
				if(facing == 1 && tempObject.x > this.x + this.width && this.x + this.width + 40 > tempObject.x){
					//System.out.println("Working 1"+facing);
					((Entity) tempObject).setDamage(this);
				}	
			}/*if(tempObject.type == GameObject.type_exit && GameMain.gameScene == 5){
				if(facing == 0 && tempObject.x + tempObject.width < this.x && this.x - 40 > tempObject.x + tempObject.width){
					//System.out.println("Working 2 "+facing);
					GameMain.gameScene = 4;
				}
				if(facing == 1 && tempObject.x > this.x + this.width && this.x + this.width + 40 > tempObject.x){
					//System.out.println("Working 1"+facing);
					GameMain.gameScene = 4;
				}	
				/*GameMain.themeStopped = false;
				Jukebox.stop("theme");
				Jukebox.play("main_theme");*/
			//}
			if(tempObject.type == GameObject.type_exit && GameMain.gameScene == 2){
				//System.out.println("Working");
				if(facing == 0 && tempObject.x + tempObject.width < this.x && this.x - 40 > tempObject.x + tempObject.width){
					//System.out.println("Working 2 "+facing);
					GameMain.gameScene = 5;
				}
				if(facing == 1 && tempObject.x > this.x + this.width && this.x + this.width + 40 > tempObject.x){
					//System.out.println("Working 1"+facing);
					GameMain.gameScene = 5;
				}	
				
				coolDown2 = 100;
				//return;
				/*GameMain.themeStopped = false;
				Jukebox.stop("theme");
				Jukebox.play("main_theme");*/
			}else if(tempObject.type == GameObject.type_exit && GameMain.gameScene == 5 && coolDown2 <= 0){
				if(facing == 0 && tempObject.x + tempObject.width < this.x && this.x - 40 > tempObject.x + tempObject.width){
					//System.out.println("Working 2 "+facing);
					GameMain.gameScene = 4;
				}
				if(facing == 1 && tempObject.x > this.x + this.width && this.x + this.width + 40 > tempObject.x){
					//System.out.println("Working 1"+facing);
					GameMain.gameScene = 4;
				}	
				/*GameMain.themeStopped = false;
				Jukebox.stop("theme");
				Jukebox.play("main_theme");*/
			}

		}
	}
	
	public void die(Handler h){
		super.die(h);
		GameMain.gameScene = 3;
		GameMain.themeStopped = false;
		Jukebox.stop("theme");
		Jukebox.stop("theme2");
		Jukebox.stop("theme_invert");
		Jukebox.play("main_theme");
	}
	
	public void setDamage(Entity attacker){
		super.setDamage(attacker);
		hurt = true;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getRelics() {
		return relics;
	}

	public void setRelics(int relics) {
		this.relics = relics;
	}
	
	
}
