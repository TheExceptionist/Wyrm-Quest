package com.theexceptionist.main.assets;

import java.awt.image.BufferedImage;

public class Sprite {
	private BufferedImage sheet;
	
	public Sprite(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int col, int row, int rowSize, int colSize, int w, int h){
		//returns cropped out image of the sheet
		return sheet.getSubimage(col * colSize, row * rowSize, w, h);
	}
	
	public BufferedImage getImage(){
		return sheet;
	}
	
	public int getImageWidth(){
		return sheet.getWidth();
	}
}
