package com.theexceptionist.main.assets;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteLoader {
	public BufferedImage load(String path){
		try{
			return ImageIO.read(getClass().getResource(path));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
