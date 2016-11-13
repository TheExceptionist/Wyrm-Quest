package com.theexceptionist.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.theexceptionist.main.assets.Jukebox;
import com.theexceptionist.main.entity.GameObject;
import com.theexceptionist.main.entity.Player;

public class Handler {
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	//public LinkedList<GameObject> renders = new LinkedList<GameObject>();
	public Player p;
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++){
			GameObject o = objects.get(i);
			
			if(o.getType() != GameObject.type_player){
				if(o.x < p.x + 200 && o.x > p.x - 200 && o.y > p.y - 150 && o.y < p.y + 150){
					o.render(g);
				}
			}else{
				o.render(g);
			}
		}
	}

	public void tick() {
		for(int i = 0; i < objects.size(); i++){
			GameObject o = objects.get(i);
			
			if(o.getType() == GameObject.type_mob){
				if(o.x < p.x + 100 && o.x > p.x - 100 && o.y > p.y - 100 && o.y < p.y + 100){
					o.tick();
				}
			}else if(o.getType() == GameObject.type_player){
				o.tick();
				p.x = o.x;
				p.y = o.y;
			}
		}
	}
	
	public void remove(GameObject o){
		objects.remove(o);
	}	
	
	public void add(GameObject o){
		objects.add(o);
		if(o instanceof Player){
			this.p = (Player) o;
		}
	}
	
	public void clear(){
		objects.clear();
	}
	

	public void awardRelic() {
		p.relics++;
		HUD.message = "Relic Obtained!";
		HUD.life = 200;
		Jukebox.play("coin2");
	}
}
