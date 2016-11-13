package com.theexceptionist.main.map;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapLoader {
	private BufferedImage image;
	//19 pixels
	//60 pixels
	
	public BufferedImage loadMap(String path){
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public int[][] loadLevelFromMap(BufferedImage image){
		int h = image.getHeight();
		int w = image.getWidth();
		int[][] map = new int[w][h];
		
		//System.out.println(w+" "+h);
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				
				//Gets RGB VALUES
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue  = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255){
					map[xx][yy] = 1;
				}else if(red == 0 && green == 80 && blue == 255){
					map[xx][yy] = 10;
					//System.out.println("Hello");
				}else if(red == 128 && green == 128 && blue == 128){
					
					map[xx][yy] = 2;
				}else if(red == 0 && green == 127 && blue == 70){
					
					map[xx][yy] = 12;
				}else if(red == 214 && green == 127 && blue == 255){
					
					map[xx][yy] = -1;
				}else if(red == 33 && green == 0 && blue == 127){
					
					map[xx][yy] = 3;
				}else if(red == 255 && green == 127 && blue == 127){
					
					map[xx][yy] = 5;
				}else if(red == 255 && green == 216 && blue == 0){
					
					map[xx][yy] = 4;
				}else if(red == 127 && green == 0 && blue == 55){
					//System.out.println(red+" "+green+" "+blue);
					map[xx][yy] = 6;
				}else if(red == 124 && green == 85 && blue == 63){
					//System.out.println(red+" "+green+" "+blue);
					map[xx][yy] = 7;
				}else if(red == 119 && green == 69 && blue == 69){
					//System.out.println(red+" "+green+" "+blue);
					map[xx][yy] = 13;
				}else if(red == 65 && green == 79 && blue == 117){
					//System.out.println(red+" "+green+" "+blue);
					map[xx][yy] = 8;
				}else if(red == 117 && green == 95 && blue == 0){
					//System.out.println(red+" "+green+" "+blue);
					map[xx][yy] = 9;
				}else if(red == 54 && green == 120 && blue == 124){
					//System.out.println(red+" "+green+" "+blue);
					map[xx][yy] = 20;
				}else{
					//System.out.println(red+" "+green+" "+blue);
					map[xx][yy] = 0;
				}
				
			}
		}
		
		return map;
	}
}
