package com.theexceptionist.main.assets;

import java.awt.image.BufferedImage;

public class Assets {
//	Example Init
//	public static BufferedImage door;
	public static BufferedImage background;
	public static BufferedImage background2;
	public static BufferedImage logo;
	public static BufferedImage castle, castleback;
	public static BufferedImage titlescreen;
	public static BufferedImage menu;
	public static BufferedImage[] grass = new BufferedImage[8];
	public static BufferedImage[] door = new BufferedImage[1];
	public static BufferedImage[] shop = new BufferedImage[1];
	public static BufferedImage[] wooden = new BufferedImage[3];
	public static BufferedImage[] tree = new BufferedImage[2];
	public static BufferedImage[] dirt = new BufferedImage[5];
	public static BufferedImage[] stone = new BufferedImage[2];
	public static BufferedImage[] heart = new BufferedImage[1];
	public static BufferedImage[] coin = new BufferedImage[2];
	public static BufferedImage[] relic = new BufferedImage[2];
	public static BufferedImage[] chest = new BufferedImage[5];
	public static BufferedImage[] shield = new BufferedImage[2];
	public static BufferedImage[] level = new BufferedImage[1];
	public static BufferedImage[] sword = new BufferedImage[1];
	public static BufferedImage[] knight = new BufferedImage[20];
	public static BufferedImage[] lizardmen = new BufferedImage[10];
	public static BufferedImage[] pilgrim = new BufferedImage[9];
	
	public static void load(){
		SpriteLoader load = new SpriteLoader();
		
		//Example new Sprite
		Sprite back = new Sprite(load.load("/art/background.png"));
		Sprite back1 = new Sprite(load.load("/art/background1.png"));
		
		Sprite log = new Sprite(load.load("/art/ink_logo.png"));
		
		Sprite title = new Sprite(load.load("/art/title_screen.png"));
		
		Sprite cas = new Sprite(load.load("/art/castle.png"));
		
		Sprite casbac = new Sprite(load.load("/art/castle.png"));
		
		Sprite hea = new Sprite(load.load("/sprites/heart.png"));
		
		Sprite coi = new Sprite(load.load("/sprites/Coin.png"));
		
		Sprite rel = new Sprite(load.load("/sprites/relic.png"));
		
		Sprite che = new Sprite(load.load("/sprites/chest.png"));
		
		Sprite swo = new Sprite(load.load("/sprites/Blade.png"));
		Sprite lvl = new Sprite(load.load("/sprites/FullHelm.png"));
		Sprite she = new Sprite(load.load("/sprites/Cross.png"));
		
		Sprite kni = new Sprite(load.load("/sprites/Knight.png"));
		
		Sprite liz = new Sprite(load.load("/sprites/Lizardmen.png"));
		
		Sprite kni2 = new Sprite(load.load("/sprites/Knight2.png"));
		
		Sprite liz2 = new Sprite(load.load("/sprites/Lizardmen2.png"));
		
		Sprite doo = new Sprite(load.load("/sprites/Door.png"));
		
		Sprite sho = new Sprite(load.load("/sprites/House.png"));
		
		Sprite men = new Sprite(load.load("/sprites/WallBlock.png"));
		
		Sprite rel2 = new Sprite(load.load("/sprites/Blade2.png"));
		
		Sprite she2 = new Sprite(load.load("/sprites/Cross2.png"));
			
		Sprite tre = new Sprite(load.load("/sprites/Tree.png"));
		
		Sprite woo = new Sprite(load.load("/sprites/WoodenPlanks.png"));
		Sprite woo2 = new Sprite(load.load("/sprites/WoodenPlanks2.png"));
		Sprite woo3 = new Sprite(load.load("/sprites/WoodenPlanks3.png"));
		
		Sprite pil = new Sprite(load.load("/sprites/pilgrim.png"));
		
		Sprite gra1 = new Sprite(load.load("/sprites/GrassVariant_3.png"));
		Sprite gra2 = new Sprite(load.load("/sprites/GrassVariant_4.png"));
		Sprite gra3 = new Sprite(load.load("/sprites/GrassVariant_1.png"));
		Sprite gra4 = new Sprite(load.load("/sprites/GrassVariant_2.png"));
		
		Sprite dir1 = new Sprite(load.load("/sprites/DirtVariant1.png"));
		Sprite dir2 = new Sprite(load.load("/sprites/DirtVariant2.png"));
		Sprite dir3 = new Sprite(load.load("/sprites/DirtVariant3.png"));
		Sprite dir4 = new Sprite(load.load("/sprites/DirtVariant4.png"));
		Sprite dir5 = new Sprite(load.load("/sprites/DirtVariant5.png"));
		
		Sprite sto1 = new Sprite(load.load("/sprites/StoneVariant.png"));
		Sprite sto2 = new Sprite(load.load("/sprites/StoneVariant1.png"));
		//Example crop
		background = back.crop(0, 0, 400, 400, 400, 400);
		background2 = back1.crop(0, 0, 400, 400, 400, 400);
		
		logo = log.crop(0, 0, 1400, 800, 1400, 800);
		
		castle = cas.crop(0, 0, 1400, 797, 1400, 797);
		
		castleback = casbac.crop(0, 0, 1400, 797, 1400, 797);
		
		titlescreen = title.crop(0, 0, 916, 134, 912, 134);
		
		menu = title.crop(0, 0, 32, 32, 32, 32);
		
		door[0] = doo.crop(0, 0, 32, 32, 32, 32);
		
		shop[0] = sho.crop(0, 0, 32, 32, 32, 32);
		
		grass[0] = gra1.crop(0,0,16,16,16,16);
		grass[1] = gra2.crop(0,0,16,16,16,16);
		
		wooden[0] = woo.crop(0,0,16,16,16,16);
		wooden[1] = woo2.crop(0,0,16,16,16,16);
		wooden[2] = woo3.crop(0,0,16,16,16,16);
		
		tree[0] = tre.crop(0, 0, 32, 32, 32, 32);
		tree[1] = tre.crop(0, 1, 32, 32, 32, 32);
		
		//System.out.println(gra3.getImageWidth());
		
		grass[2] = gra3.crop(0, 0, 16, 16, 16, 16);
		grass[3] = gra3.crop(1, 0, 16, 16, 16, 16);
		grass[4] = gra3.crop(0, 1, 16, 16, 16, 16);
		
		grass[5] = gra4.crop(0, 0, 16, 16, 16, 16);
		grass[6] = gra4.crop(1, 0, 16, 16, 16, 16);
		grass[7] = gra4.crop(0, 1, 16, 16, 16, 16);
		
		dirt[0] = dir1.crop(0,0,16,16,16,16);
		dirt[1] = dir2.crop(0,0,16,16,16,16);
		dirt[2] = dir3.crop(0, 0, 16, 16, 16, 16);
		dirt[3] = dir4.crop(0, 0, 16, 16, 16, 16);
		dirt[4] = dir5.crop(0, 0, 16, 16, 16, 16);
		
		stone[0] = sto1.crop(0,0,16,16,16,16);
		stone[1] = sto2.crop(0,0,16,16,16,16);
		
		heart[0] = hea.crop(0, 0, 32, 32, 32, 32);
		
		relic[0] = rel.crop(0, 0, 16, 16, 16, 16);
		relic[1] = rel2.crop(0, 0, 32, 32, 32, 32);	
		
		sword[0] = swo.crop(0, 0, 32, 32, 32, 32);
		level[0] = lvl.crop(0, 0, 32, 32, 32, 32);
		shield[0] = she.crop(0, 0, 32, 32, 32, 32);
		shield[1] = she2.crop(0, 0, 32, 32, 32, 32);
		
		coin[0] = coi.crop(0, 0, 16, 16, 16, 16);
		coin[1] = coi.crop(0, 1, 16, 16, 16, 16);
		
		chest[0] = che.crop(0, 0, 32, 32, 32, 32);
		chest[1] = che.crop(1, 0, 32, 32, 32, 32);
		chest[2] = che.crop(0, 1, 32, 32, 32, 32);
		chest[3] = che.crop(1, 1, 32, 32, 32, 32);
		chest[4] = che.crop(0, 2, 32, 32, 32, 32);
		
		knight[0] = kni.crop(0, 0, 32, 32, 32, 32);
		knight[1] = kni.crop(1, 0, 32, 32, 32, 32);
		knight[2] = kni.crop(2, 0, 32, 32, 32, 32);
		knight[3] = kni.crop(0, 1, 32, 32, 32, 32);
		knight[4] = kni.crop(1, 1, 32, 32, 32, 32);
		knight[5] = kni.crop(2, 1, 32, 32, 32, 32);
		knight[6] = kni.crop(0, 2, 32, 32, 32, 32);
		knight[7] = kni.crop(1, 2, 32, 32, 32, 32);
		knight[8] = kni.crop(2, 2, 32, 32, 32, 32);
		knight[9] = kni.crop(0, 3, 32, 32, 32, 32);
		
		knight[10] = kni2.crop(0, 0, 32, 32, 32, 32);
		knight[11] = kni2.crop(1, 0, 32, 32, 32, 32);
		knight[12] = kni2.crop(2, 0, 32, 32, 32, 32);
		knight[13] = kni2.crop(0, 1, 32, 32, 32, 32);
		knight[14] = kni2.crop(1, 1, 32, 32, 32, 32);
		knight[15] = kni2.crop(2, 1, 32, 32, 32, 32);
		knight[16] = kni2.crop(0, 2, 32, 32, 32, 32);
		knight[17] = kni2.crop(1, 2, 32, 32, 32, 32);
		knight[18] = kni2.crop(2, 2, 32, 32, 32, 32);
		knight[19] = kni2.crop(0, 3, 32, 32, 32, 32);
		
		
		//System.out.println(pil.getImageWidth());
		pilgrim[0] = pil.crop(0, 0, 32, 20, 20, 32);
		pilgrim[1] = pil.crop(1, 0, 32, 20, 20, 32);
		pilgrim[2] = pil.crop(2, 0, 32, 20, 20, 32);
		pilgrim[3] = pil.crop(3, 0, 32, 20, 20, 32);
		pilgrim[4] = pil.crop(0, 1, 32, 20, 20, 32);
		pilgrim[5] = pil.crop(1, 1, 32, 20, 20, 32);
		pilgrim[6] = pil.crop(2, 1, 32, 20, 20, 32);
		pilgrim[7] = pil.crop(3, 1, 32, 20, 20, 32);
		pilgrim[8] = pil.crop(0, 2, 32, 20, 20, 32);
		
		lizardmen[0] = liz.crop(0, 0, 32, 32, 32, 32);
		lizardmen[1] = liz.crop(1, 0, 32, 32, 32, 32);
		lizardmen[2] = liz.crop(0, 1, 32, 32, 32, 32);
		lizardmen[3] = liz.crop(1, 1, 32, 32, 32, 32);
		lizardmen[4] = liz.crop(0, 2, 32, 32, 32, 32);
		
		lizardmen[5] = liz2.crop(0, 0, 32, 32, 32, 32);
		lizardmen[6] = liz2.crop(1, 0, 32, 32, 32, 32);
		lizardmen[7] = liz2.crop(0, 1, 32, 32, 32, 32);
		lizardmen[8] = liz2.crop(1, 1, 32, 32, 32, 32);
		lizardmen[9] = liz2.crop(1, 2, 32, 32, 32, 32);
	}
}
