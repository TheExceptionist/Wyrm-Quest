package com.theexceptionist.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.theexceptionist.main.assets.Assets;
import com.theexceptionist.main.entity.Player;

public class HUD {
	private Player p;
	private Font f;
	public static String message = "";
	public static int life = 0;
	
	public HUD(Player p){
		this.p = p;
		f = new Font("Arial", Font.BOLD ,10);
	}
	
	public void render(Graphics g){
		if(life > 0){
			life--;
		}
		g.setFont(f);
		g.setColor(Color.GREEN);

		g.drawImage(Assets.coin[0], 0, 0, 16, 16, null);
		g.drawString(""+p.getGold(), 20, 10);
		g.drawImage(Assets.heart[0], 40, 0, 16, 16, null);
		g.drawString(""+p.getHealth()+"/"+p.getMaxHealth(), 55, 10);
		g.drawImage(Assets.relic[0], 80, 0, 16, 16, null);
		g.drawString(""+p.getRelics(),100, 10);
		g.drawImage(Assets.level[0], 120, 0, 16, 16, null);
		g.drawString(""+p.getLevel()[0],140, 10);
		g.drawImage(Assets.sword[0], 160, 0, 16, 16, null);
		g.drawString(""+p.getAttack(),180, 10);
		g.drawImage(Assets.shield[0], 200, 0, 16, 16, null);
		g.drawString(""+p.getArmor(),220, 10);
		g.drawImage(Assets.relic[1], 0, 200, 16, 16, null);
		g.drawString(""+p.defProficient[0],20, 220);
		g.drawImage(Assets.shield[1], 30, 200, 16, 16, null);
		g.drawString(""+p.attProficient[0],60, 220);
		
		
		g.drawString(HUD.message,200, 220);
		
		if(life <= 0){
			HUD.message = "";
		}
//		for(int i = 0; i < p.getHealth(); i++){
			//g.drawImage(Assets.heart1, 130 + 20 * i, -5, null);
	//	}
		
		//for(int i = 0; i < p.getPancakes(); i++){
			//g.drawImage(Assets.waffle1, 490 + 34 * i, 0, null);
		//}
		
		//g.drawString("Health: ", 100, 10);
		//g.drawString("Money: "+p.getMoney(), 7, 10);
		//g.drawString("Score: "+p.getScore(), 230, 10);
		//g.drawString("Pancakes: ", 430, 10);
		//g.drawString("Wave: "+GameMain.wave, 595, 10);
		
		//if(GameMain.lose){
		///	g.setFont(new Font("Arial", Font.BOLD ,25));
		//	g.drawString("Game Over!!!!!", 230, 180);
		//	g.setFont(new Font("Arial", Font.BOLD , 5));
		//	g.drawString("Press enter to try again, press esc to go to the main menu.", 240, 200);
		//}
	}
}
