package com.theexceptionist.main.menu;

import java.awt.Graphics;

import com.theexceptionist.main.assets.Assets;

public class Menu {
	public void render(Graphics g){
		g.drawImage(Assets.menu, 15, 180, 64, 64, null);
	}
}
