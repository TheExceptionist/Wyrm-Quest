package com.theexceptionist.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.theexceptionist.main.assets.Assets;
import com.theexceptionist.main.assets.Jukebox;
import com.theexceptionist.main.entity.GameObject;
import com.theexceptionist.main.entity.Mob;
import com.theexceptionist.main.entity.Player;
import com.theexceptionist.main.entity.Tiles;
import com.theexceptionist.main.input.Camera;
import com.theexceptionist.main.input.InputHandler;
import com.theexceptionist.main.map.MapLoader;
import com.theexceptionist.main.menu.Menu;

public class GameMain extends Canvas implements Runnable {
	public static final int WIDTH = 1200, HEIGHT = 900, SCALE = 4;
	public static final Dimension mapDimensions = new Dimension(512, 512);
	public static final String name = "Dungeon Crawler 2: The Wyrm Quest";
	
	public static Handler h;
	
	public int currentLevel = 0;
	
	public Player p;
	
	private InputHandler i;
	
	//Our main game running thread
	private Thread thread;
	private HUD hud;
	private Camera cam;
	private Font f;
	private boolean running = false;
	private boolean loadedLevel2 = false;
	public static boolean themeStopped = false;
	
	//Total number of maps = 5
	private int numMaps = 5;
	public static int gameScene = 0;
	private int timePassed = 0;
	private BufferedImage[] maps = new BufferedImage[numMaps];
	private Menu m = new Menu();
	private static boolean menuHide = true;
	private boolean invertStart = false;

	public int[][][] levels = new int[numMaps][(int) mapDimensions.getWidth()][(int) mapDimensions.getHeight()];
	
	private void init(){
		int[][] level, level2, level3;
		
		//Current X and Y positions
		cam = new Camera(0, 0);
		
		f = new Font("Arial", Font.BOLD ,30);
		
		h = new Handler();
		//Init new Map loader, needs to be done in init function
		MapLoader loader = new MapLoader();
		
		//Need to create the other 4
		maps[0] = loader.loadMap("/map/test_map.png");
		maps[1] = loader.loadMap("/map/test_map_invert.png");
		maps[2] = loader.loadMap("/map/map_2.png");
		
		level = loader.loadLevelFromMap(maps[0]);
		level2 = loader.loadLevelFromMap(maps[1]);
		level3 = loader.loadLevelFromMap(maps[2]);
		
		levels[0] = level;
		levels[1] = level2;
		levels[2] = level3;
		
		i = new InputHandler(this);
		
		Assets.load();
		Jukebox.init();
		
		Jukebox.load("/audio/canon.wav", "main_theme");
		Jukebox.load("/audio/sinfonia.wav", "theme");
		Jukebox.load("/audio/concerto.wav", "theme2");
		Jukebox.load("/audio/sinfonia_invert.wav", "theme_invert");
		
		Jukebox.load("/audio/Jump.wav", "jump");
		Jukebox.load("/audio/coin_get.wav", "coin");
		Jukebox.load("/audio/coin_get2.wav", "coin2");
		Jukebox.load("/audio/land.wav", "land");
		Jukebox.load("/audio/lizard_hurt.wav", "lizardhurt");
		Jukebox.load("/audio/lizard_hurt1.wav", "lizardhurt2");
		Jukebox.load("/audio/lizard_hurt2.wav", "lizardhurt3");
		Jukebox.load("/audio/player_hurt.wav", "playerhurt");
		Jukebox.load("/audio/player_hurt2.wav", "playerhurt2");
		Jukebox.load("/audio/sword_slash.wav", "sword");
		Jukebox.load("/audio/sword_slash1.wav", "sword2");
		Jukebox.load("/audio/walk.wav", "walk");
		Jukebox.load("/audio/walk1.wav", "walk2");
		Jukebox.load("/audio/walk2.wav", "walk3");
		Jukebox.load("/audio/level.wav", "level");
		
		displayMap(levels[currentLevel]);
		
		Jukebox.loop("main_theme");
		//Tiles.createTiles();
	}
	
	public void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
		init();
	}
	
	public void stop(){
		if(running){
			try{
				running = false;
				thread.join();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			return;
		}
	}
	
	
	public static void main(String[] args){
		GameMain main = new GameMain();
		main.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		main.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		main.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		String imagePath = "/sprites/FullHelm.png";
		String imagePath2 = "/misc/cursor.gif";
		InputStream imgStream = GameMain.class.getResourceAsStream(imagePath );
		InputStream imgStream2 = GameMain.class.getResourceAsStream(imagePath2 );
		
		JFrame window = new JFrame(name);
		
		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			BufferedImage myImg = ImageIO.read(imgStream);
			BufferedImage myImg2 = ImageIO.read(imgStream2);
		    Image cursorImage = myImg2;
			window.setIconImage(myImg);
		    Point cursorHotSpot = new Point(0,0);
		    Cursor customCursor = toolkit.createCustomCursor(cursorImage, cursorHotSpot, "Img");
		    window.setCursor(customCursor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(main, BorderLayout.CENTER);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		
		main.start();
	}
	//May remove graphics g later
	//rename to genLevel when that happens
	public void displayMap(int[][] map){
		Rectangle attack = new Rectangle(0, 0, 20, 20); 
		
		for(int xx = 0; xx < map.length; xx++){
			for(int yy = 0; yy < map[xx].length; yy++){
				if(map[xx][yy] == -1){
					Tiles grass = new Tiles("Drop Bounds", GameObject.type_dropBounds);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}
				if(map[xx][yy] == 1){
					Tiles grass = new Tiles("Grass", GameObject.type_standstill);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 2){
					Tiles grass = new Tiles("Grass Wall", GameObject.type_standstillwall);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 3){
					Tiles grass = new Tiles("Grass Bottom", GameObject.type_standstillbottom);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 4){
					Tiles grass = new Tiles("Gold", GameObject.type_treasure, 5);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 5){
					Tiles grass = new Tiles("Chest", GameObject.type_treasure, 20);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 6){
					Tiles grass = new Tiles("Shop", GameObject.type_shop);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 7){
					Tiles grass = new Tiles("Exit", GameObject.type_exit);
				    grass.setPosition(xx*16, yy*16, 32, 32);
					h.add(grass);
				}else if(map[xx][yy] == 8){
					Tiles grass = new Tiles("Tree", GameObject.type_background);
				    grass.setPosition(xx*16, yy*16, 32, 64);
					h.add(grass);
				}else if(map[xx][yy] == 9){
					Tiles grass = new Tiles("Wooden Wall", GameObject.type_standstillwall);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 10){
					int[] attPro = {5,0,0,0};
					int[] defPro = {5,0,0,0};
					int[] levels = {1, 2};
					//System.out.println("Hello");
					
					p = new Player("Player", GameObject.type_player, 20, levels, attPro, defPro, 5, 3, i);
				    p.setPosition(xx*16, (yy*16) - 16, 24, 32);
				    //p.setMeleeCollisionBox(attack);
					h.add(p);
					hud = new HUD(p);
				}else if(map[xx][yy] == 12){
					//System.out.println("Working");
					int[] attPro = {5,0,0,0};
					int[] defPro = {5,0,0,0};
					int[] levels = {1, 3};
					//System.out.println("Hello");
					
					Mob m = new Mob("Lizard", GameObject.type_mob, 20, levels, attPro, defPro, 5, 3, Mob.AIStatic, new Rectangle(0, 0, 20, 40));
				    m.setPosition(xx*16, (yy*16) - 16, 16, 32);
				   // m.setMeleeCollisionBox(attack);
					h.add(m);
				}else if(map[xx][yy] == 13){
					int[] attPro = {8,0,0,0};
					int[] defPro = {7,0,0,0};
					
					//System.out.println("Hello");
					
					//Mob m = new Mob("Pilgrim", GameObject.type_mob, 40, 3, attPro, defPro, 9, 2, Mob.AIWander, new Rectangle(0, 0, 20, 40));
				   // m.setPosition(xx*16, (yy*16) - 16, 16, 16);
				    //p.setMeleeCollisionBox(attack);
					//h.add(m);
				}else if(map[xx][yy] == 20){
					Tiles grass = new Tiles("Ancient Relic", GameObject.type_treasure);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else{
					//g.setColor(Color.GRAY);
					//g.fillRect(xx * 16, yy * 16, 16, 16);
				}
			}
		}
	}
	
	public void displayMap(int[][] map, boolean noInvert){
		Rectangle attack = new Rectangle(0, 0, 20, 20); 
		
		for(int xx = 0; xx < map.length; xx++){
			for(int yy = 0; yy < map[xx].length; yy++){
				if(map[xx][yy] == 1){
					Tiles grass = new Tiles("Grass", GameObject.type_standstill);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 2){
					Tiles grass = new Tiles("Grass Wall", GameObject.type_standstillwall);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 3){
					Tiles grass = new Tiles("Grass Bottom", GameObject.type_standstillbottom);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 4){
					Tiles grass = new Tiles("Gold", GameObject.type_treasure, 5);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 5){
					Tiles grass = new Tiles("Chest", GameObject.type_treasure, 20);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 6){
					Tiles grass = new Tiles("Shop", GameObject.type_shop);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 7){
					Tiles grass = new Tiles("Exit", GameObject.type_exit);
				    grass.setPosition(xx*16, yy*16, 32, 32);
					h.add(grass);
				}else if(map[xx][yy] == 8){
					Tiles grass = new Tiles("Tree", GameObject.type_background);
				    grass.setPosition(xx*16, yy*16, 32, 64);
					h.add(grass);
				}else if(map[xx][yy] == 9){
					Tiles grass = new Tiles("Wooden Wall", GameObject.type_standstillwall);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else if(map[xx][yy] == 10){
					int[] attPro = {5,0,0,0};
					int[] defPro = {5,0,0,0};
					int[] levels = {1, 2};
					
					//System.out.println("Hello");
					
					p = new Player("Player", GameObject.type_player, 20, levels, attPro, defPro, 5, 3, i);
				    p.setPosition(xx*16, (yy*16) - 16, 24, 32);
				    //p.setMeleeCollisionBox(attack);
					h.add(p);
					hud = new HUD(p);
				}else if(map[xx][yy] == 12){
					//System.out.println("Working");
					int[] attPro = {5,0,0,0};
					int[] defPro = {5,0,0,0};
					int[] levels = {1, 3};
					
					//System.out.println("Hello");
					
					Mob m = new Mob("Lizard", GameObject.type_mob, 20,levels , attPro, defPro, 5, 3, Mob.AIStatic, new Rectangle(0, 0, 20, 40));
				    m.setPosition(xx*16, (yy*16) - 16, 16, 32);
				   // m.setMeleeCollisionBox(attack);
					h.add(m);
				}else if(map[xx][yy] == 13){
					int[] attPro = {8,0,0,0};
					int[] defPro = {7,0,0,0};
					
					//System.out.println("Hello");
					
					//Mob m = new Mob("Pilgrim", GameObject.type_mob, 40, 3, attPro, defPro, 9, 2, Mob.AIWander, new Rectangle(0, 0, 20, 40));
				   // m.setPosition(xx*16, (yy*16) - 16, 16, 16);
				    //p.setMeleeCollisionBox(attack);
					//h.add(m);
				}else if(map[xx][yy] == 20 && !noInvert){
					Tiles grass = new Tiles("Ancient Relic", GameObject.type_treasure);
				    grass.setPosition(xx*16, yy*16, 16, 16);
					h.add(grass);
				}else{
					//g.setColor(Color.GRAY);
					//g.fillRect(xx * 16, yy * 16, 16, 16);
				}
			}
		}
	}
	
	private void tick(){
		//Added more later
		if(p!= null){
			cam.tick(p);
		}
		
    	if(!themeStopped && gameScene == 2){
    	   Jukebox.stop("main_theme");
    	   Jukebox.loop("theme");
    		themeStopped = true;
    	}
		
		h.tick();
	}
	

	private void render(){
		
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2 = (Graphics2D) g;
		
		if(gameScene == 0){
			g.drawImage(Assets.logo, 0, 0, GameMain.WIDTH + 100, GameMain.HEIGHT + 100, null);
			timePassed++;
			if(timePassed == 600){
				gameScene++;
				timePassed = 0;
			}
		}
		if(gameScene == 1){
			g.drawImage(Assets.castle, 0, 0, GameMain.WIDTH + 100, GameMain.HEIGHT + 100, null);
			g.drawImage(Assets.titlescreen, GameMain.WIDTH/4, 50, 700, 200, null);
			g.setColor(Color.GREEN);
			g.setFont(f);
			g.drawString("Press The Spacebar to Start", GameMain.WIDTH/4 + 140, GameMain.HEIGHT - GameMain.HEIGHT/10);
			if(i.menu.down){
				gameScene = 2;
			}
		}
        if(gameScene == 2){
			//Scale is 4 18 pixels wide 14 pixel long
			//RENDERING THE GAME SHOULD BE DONE HERE
			///////////
        	if(invertStart){
        		h.clear();
        		displayMap(levels[0], true);
        		invertStart = false;
        	}
    		g2.scale(SCALE, SCALE);
			g.drawImage(Assets.background, 0, 0, 400, 400, null);
			
			g2.translate(cam.getX(), cam.getY());
			
			h.render(g);
			
			//////////////
			g2.translate(-cam.getX(), -cam.getY());
			
			if(hud != null){
				hud.render(g);
			}
        }if(gameScene == 3){
        	g.drawImage(Assets.castle, 0, 0, GameMain.WIDTH + 100, GameMain.HEIGHT + 100, null);
			g.drawImage(Assets.titlescreen, GameMain.WIDTH/4, 50, 700, 200, null);
			g.setColor(Color.GREEN);
			g.setFont(f);
			g.drawString("You Lose!", GameMain.WIDTH/2, GameMain.HEIGHT - GameMain.HEIGHT/10);
			g.drawString("Score: "+Math.round((p.gold * 1000)/2), GameMain.WIDTH/2, GameMain.HEIGHT - GameMain.HEIGHT/10 + 30);
			if(i.menu.down){
				gameScene = 0;
			}
        }
        if(gameScene == 4){
        	g.drawImage(Assets.castle, 0, 0, GameMain.WIDTH + 100, GameMain.HEIGHT + 100, null);
			g.drawImage(Assets.titlescreen, GameMain.WIDTH/4, 50, 700, 200, null);
			g.setColor(Color.GREEN);
			g.setFont(f);
			//Remove later
			Jukebox.stop("theme");
			Jukebox.stop("theme2");
			Jukebox.stop("theme_invert");
			Jukebox.stop("main_theme");
			Jukebox.loop("main_theme");
			g.drawString("You Won!", GameMain.WIDTH/2, GameMain.HEIGHT - GameMain.HEIGHT/10);
			g.drawString("Score: "+Math.round((p.gold * 1000)/2), GameMain.WIDTH/2, GameMain.HEIGHT - GameMain.HEIGHT/10 + 30);
			if(i.menu.down){
				gameScene = 0;
			}
        } if(gameScene == 5){
        	if(!loadedLevel2){
        		h.clear();
        		displayMap(levels[2]);
        		Jukebox.stop("theme");
        		Jukebox.loop("theme2");
        		loadedLevel2 = true;
        	}
			//Scale is 4 18 pixels wide 14 pixel long
			//RENDERING THE GAME SHOULD BE DONE HERE
			///////////
        	if(loadedLevel2){
	    		g2.scale(SCALE, SCALE);
				g.drawImage(Assets.castleback, 0, 0, 400, 400, null);
				
				g2.translate(cam.getX(), cam.getY());
				
				h.render(g);
				
				//////////////
				g2.translate(-cam.getX(), -cam.getY());
				
				if(hud != null){
					hud.render(g);
				}
        	}
        }if(gameScene == 6){
        	if(!invertStart){
        		h.clear();
        		displayMap(levels[1]);
        		invertStart = true;
        	}
			//Scale is 4 18 pixels wide 14 pixel long
			//RENDERING THE GAME SHOULD BE DONE HERE
			///////////
        	if(invertStart){
	    		g2.scale(SCALE, SCALE);
				g.drawImage(Assets.background2, 0, 0, 400, 400, null);
				
				g2.translate(cam.getX(), cam.getY());
				
				h.render(g);
				
				//////////////
				g2.translate(-cam.getX(), -cam.getY());
				
				if(hud != null){
					hud.render(g);
				}
        	}
        }
		
		g.dispose();
		bs.show();
	}


	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public static void showMenu(){
		setMenuHide(false);
	}

	public static boolean isMenuHide() {
		return menuHide;
	}

	public static void setMenuHide(boolean menuHide) {
		GameMain.menuHide = menuHide;
	}
	
}
