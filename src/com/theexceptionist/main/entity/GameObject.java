package com.theexceptionist.main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.theexceptionist.main.GameMain;
import com.theexceptionist.main.Handler;
import com.theexceptionist.main.assets.Jukebox;

public class GameObject {
	public int x, y, width, height, velX, velY;
	public int health;
	public int ID;
	public int facing = 0;
	public static int type_dropBounds = -1,type_standstill = 0, type_standstillwall = 1, type_standstillbottom = 2, type_treasure = 3,type_shop = 4,type_exit = 5,type_background = 6, type_mob = 10, type_player = 11;
	//grounded will be called from collision class
	public boolean hasGravity, hurtOnTouch, canDestroy, isMovable, grounded;
	public String name;
	public BufferedImage[] textures;
	//type = 0 standstill block
	private int gravity;
	protected int type;
	protected Rectangle collisionBox;
	protected int amount;
	protected int state = 0;
	//protected boolean shouldRender = false;
	
	public GameObject(String name, int type){
		this.name = name;
		this.type = type;
		
		init();
	}
	
	protected void init(){
		//StandStill Block
		if(this.type == type_standstill || this.type == type_standstillwall || this.type == type_shop){
			this.hasGravity = false;
			this.hurtOnTouch = false;
			this.canDestroy = false;
			this.isMovable = false;
			this.grounded = false;
		}
		
		if(this.type == type_mob || this.type == type_player){
			this.hasGravity = true;
			this.hurtOnTouch = true;
			this.canDestroy = true;
			this.isMovable = true;
			//this.grounded = ;
		}
	}
	
	public void setPosition(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		this.collisionBox = new Rectangle(x, y, w, h);		
	}
	
	//public void 
	
	public void tick(){
		if(isMovable){
			x += velX;
			y += velY;
		}
		
		if(hasGravity && !grounded){
			velY += gravity;
		}
		
		if(health <= 0 && canDestroy){
			die(GameMain.h);
		}
		
		doCollisions();
	}
	
	public void doCollisions(){
		for(int i = 0; i < GameMain.h.objects.size(); i++){
			GameObject tempObject = GameMain.h.objects.get(i);
			
			
			if(tempObject.getCollisionBox().intersects(this.getCollisionBox())){
					if(this.type == GameObject.type_player && tempObject.type == GameObject.type_dropBounds){
						this.die(GameMain.h);
					}
					if(this.type == GameObject.type_player && tempObject.type == GameObject.type_treasure){
						if(tempObject.name == "Gold"){
							tempObject.die(GameMain.h);
							((Player)this).gold += tempObject.amount;
							Jukebox.play("coin");
						}
						if(tempObject.name == "Chest" && tempObject.state == 0){
							if(((Tiles)tempObject).hasRelic){
								((Player)this).relics++;// += tempObject.amount;
							}else{
								((Player)this).gold += tempObject.amount;
							}
							Jukebox.play("coin2");
						}
						if(tempObject.name == "Chest"){
							tempObject.state = 1;
						}
						if(tempObject.name == "Ancient Relic"){
							if(GameMain.gameScene == 2){
								tempObject.die(GameMain.h);
								GameMain.gameScene = 6;
								Jukebox.stop("theme");
								Jukebox.loop("theme_invert");
							}else if(GameMain.gameScene == 6){
								tempObject.die(GameMain.h);
								GameMain.gameScene = 2;
								Jukebox.stop("theme_invert");
								Jukebox.loop("theme");
							}
						}
					}
					if(this.type == GameObject.type_player  && tempObject.type == GameObject.type_standstill){
						//System.out.println(tempObject.type);
						//Jukebox.play("land");
						this.velY = 0;
						((Entity)this).jumping = false;
						((Entity)this).falling = false;
						
						
						if(tempObject.name == "Grass"){
							this.y = (tempObject.y - tempObject.height * 2);
						}else{
							this.y = tempObject.y - tempObject.height * 2;
						}
						
						if(this instanceof Player){
							((Player)this).jumps = 0;
						}	
					}else{
						if(this.type == GameObject.type_player){
							((Entity)this).falling = true;
						}
					}
					if(this.type == GameObject.type_mob  && tempObject.type == GameObject.type_standstill){
						//System.out.println(tempObject.type);
						this.velY = 0;
						((Entity)this).jumping = false;
						((Entity)this).falling = false;
						
						
						if(tempObject.name == "Grass"){
							this.y = (tempObject.y - tempObject.height * 2) + 4;
						}else{
							this.y = tempObject.y - tempObject.height * 2;
						}
						
						if(this instanceof Player){
							((Player)this).jumps = 0;
						}	
					}else{
						if(this.type == GameObject.type_mob){
							((Entity)this).falling = true;
						}
					}
					if(this.type == GameObject.type_player  && tempObject.type == GameObject.type_standstillwall){
						this.velX = 0;
						//System.out.println(this.x+" "+tempObject.x+" "+tempObject.width);
						if(tempObject.x > this.x){
						    //System.out.println("Working");
							this.x = tempObject.x - this.collisionBox.width - 10;
						}else if(tempObject.x < this.x){
							this.x = tempObject.x + tempObject.width - 5;
						}
						//((Entity)this).jumping = false;
						//((Entity)this).falling = false;
					}
					if(this.type == GameObject.type_player  && tempObject.type == GameObject.type_standstillbottom){
						this.velY = 0;
						//((Entity)this).jumping = false;
						//((Entity)this).falling = false;
					}
			}
		}
	}
	
	protected void knockback(){
		velX *= -1;
		moveUp();
	}
	
	protected void moveLeft(){velX = -5; facing = 0;}
	protected void moveRight(){velX = 5;facing = 1;}
	protected void moveUp(){velY = -12;facing = 2;}
	protected void moveDown(){velY = 5;facing = 3;}
	protected void stop(){velX = 0;}
	
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isHasGravity() {
		return hasGravity;
	}

	public void setHasGravity(boolean hasGravity) {
		this.hasGravity = hasGravity;
	}

	public boolean isHurtOnTouch() {
		return hurtOnTouch;
	}

	public void setHurtOnTouch(boolean hurtOnTouch) {
		this.hurtOnTouch = hurtOnTouch;
	}

	public boolean isCanDestroy() {
		return canDestroy;
	}

	public void setCanDestroy(boolean canDestroy) {
		this.canDestroy = canDestroy;
	}

	public boolean isMovable() {
		return isMovable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public boolean isGrounded() {
		return grounded;
	}

	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage[] getTextures() {
		return textures;
	}

	public void setTextures(BufferedImage[] textures) {
		this.textures = textures;
	}

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void render(Graphics g){
		
	}
	
	public void die(Handler h){
		h.remove(this);
	}
	
	public Rectangle getCollisionBox(){
		collisionBox.x = this.x;
		collisionBox.y = this.y;
		if(this.name == "Player"){
			collisionBox.x += 10;
		}
		return collisionBox;
	}
}
