package com.theexceptionist.main.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.theexceptionist.main.GameMain;
import com.theexceptionist.main.menu.SplashText;

public class Entity extends GameObject{
	public int level[], currentLevel, attack, armor;
	public int weight = 1, MaxVel = 5;
	public int[] attProficient;
	public int[] defProficient; 
	
	public boolean jumping = false, falling = false, moving = false, attacking, hurt = false;
	public int MaxHealth;
	private Random r;

	public Entity(String name, int type, int health, int[] level, int[] attProficient, int[] defProficient, int attack, int armor){
		super(name, type);
		this.level = level;
		this.health = health;
		this.MaxHealth = health;
		this.attack = attack;
		this.armor = armor;
		this.attProficient = attProficient;
		this.defProficient = defProficient;
		this.r = new Random();
		
		this.currentLevel = this.level[r.nextInt(level.length)];
		
		if(this.currentLevel > 1){
			setupLevel();
		}
		
		init();
	}
	
	public Entity(String name, int type, int health, int[] level, int[] attProficient, int[] defProficient, int attack, int armor, BufferedImage[] texture){
		super(name, type);
		this.level = level;
		this.health = health;
		this.attack = attack;
		this.armor = armor;
		this.attProficient = attProficient;
		this.defProficient = defProficient;
		this.textures = texture;
		
		this.currentLevel = this.level[r.nextInt(level.length)];
		
		if(this.currentLevel > 1){
			setupLevel();
		}
		init();
	}
	
	//Maybe add proficient to enemies?
	
	public void setupLevel(){
		health += (r.nextInt(10)+1) * this.currentLevel;
		armor += (r.nextInt(2)+0) * this.currentLevel;
		attack += (r.nextInt(2)+0) * this.currentLevel;
	}
	
	public void tick(){
		super.tick();
		
		if(jumping || falling){
			velY += weight;
			
			if(velY > MaxVel || velX > MaxVel){
				velY = MaxVel;
			}
		}
		
		if(velX != 0){
			moving = true;
		}else{
			moving = false;
		}
		
		if(this.health > this.MaxHealth){
			this.health = this.MaxHealth;
		}
		
		//System.out.println(this.velY);
	}
	
	public void attack(){
		this.attacking = true;
	}
	
	public void setDamage(Entity attacker){
		//&health -= Round(_Attack * (_Wp(0)/2)) - Round(&Armor + (&Armor * (&Def(0)/10)))  
		int damage = Math.round(attacker.attack * (r.nextInt(Math.round(attacker.attProficient[0]/2 + 1))+ attacker.attProficient[0]/4) - Math.round(this.armor + (this.armor * (r.nextInt(Math.round(this.defProficient[0]/10 + 1))))));
		//System.out.println(new SplashText("Text", -2, this.x, this.y,""+damage ,20));
		//GameMain.h.add(new SplashText("Text", -2, this.x, this.y,""+damage ,20));
		this.health -= damage;
		this.hurt = true;
	}
	
	public void render(Graphics g){
		super.render(g);
	}

	public int getMaxHealth() {
		return MaxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		MaxHealth = maxHealth;
	}

	public int[] getLevel() {
		return level;
	}

	public void setLevel(int level[]) {
		this.level = level;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	
}
