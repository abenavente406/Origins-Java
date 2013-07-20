package com.rcode.origins.gui;

import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.rcode.origins.entity.Player;
import com.rcode.origins.inventory.InventoryPlayer;
import com.rcode.origins.item.Item;

public class InventoryScreen {

	public Image screen;
	
	public Image[] items = new Image[36];
	
	private InventoryPlayer invPlayer;
	
	//Coordinates for the boxes
	private Point[] slots_top;
	private Point[] slots_mid_1;
	private Point[] slots_mid_2;
	private Point[] slots_bottom;
	
	//Rectangles for boxes
	private Rectangle[] slots_top_boxes;
	private Rectangle[] slots_mid_1_boxes;
	private Rectangle[] slots_mid_2_boxes;
	private Rectangle[] slots_bottom_boxes;
	
	
	public InventoryScreen() throws SlickException{
		init();
	}
	
	private void init() throws SlickException{
		this.screen = new Image("res/gui/inventory_player.png");
		invPlayer = Player.inventory;
		
		slots_top = new Point[]{new Point(1, 345), new Point(35, 345), new Point(69, 345), new Point(103, 345), new Point(137, 345), 
				new Point(171, 345), new Point(205, 345), new Point(239, 345), new Point(273, 345)};
		slots_mid_1 = new Point[]{new Point(1, 379), new Point(35, 379), new Point(69, 379), new Point(103, 379), new Point(137, 379), 
				new Point(171, 379), new Point(205, 379), new Point(239, 379), new Point(273, 379)};
		slots_mid_2 = new Point[]{new Point(1, 413), new Point(35, 413), new Point(69, 413), new Point(103, 413), new Point(137, 413), 
				new Point(171, 413), new Point(205, 413), new Point(239, 413), new Point(273, 413)};
		slots_bottom = new Point[]{new Point(1, 447), new Point(35, 447), new Point(69, 447), new Point(103, 447), new Point(137, 447), 
				new Point(171, 447), new Point(205, 447), new Point(239, 447), new Point(273, 447)};
		
		slots_top_boxes = new Rectangle[]{new Rectangle(slots_top[0].x, slots_top[0].y, 32, 32), 
				new Rectangle(slots_top[1].x, slots_top[1].y, 32, 32),
				new Rectangle(slots_top[2].x, slots_top[2].y, 32, 32),
				new Rectangle(slots_top[3].x, slots_top[3].y, 32, 32),
				new Rectangle(slots_top[4].x, slots_top[4].y, 32, 32),
				new Rectangle(slots_top[5].x, slots_top[5].y, 32, 32),
				new Rectangle(slots_top[6].x, slots_top[6].y, 32, 32),
				new Rectangle(slots_top[7].x, slots_top[7].y, 32, 32), 
				new Rectangle(slots_top[8].x, slots_top[8].y, 32, 32)};
		
		slots_mid_1_boxes = new Rectangle[]{new Rectangle(slots_mid_1[0].x, slots_mid_1[0].y, 32, 32), 
				new Rectangle(slots_mid_1[1].x, slots_mid_1[1].y, 32, 32),
				new Rectangle(slots_mid_1[2].x, slots_mid_1[2].y, 32, 32),
				new Rectangle(slots_mid_1[3].x, slots_mid_1[3].y, 32, 32),
				new Rectangle(slots_mid_1[4].x, slots_mid_1[4].y, 32, 32),
				new Rectangle(slots_mid_1[5].x, slots_mid_1[5].y, 32, 32),
				new Rectangle(slots_mid_1[6].x, slots_mid_1[6].y, 32, 32),
				new Rectangle(slots_mid_1[7].x, slots_mid_1[7].y, 32, 32), 
				new Rectangle(slots_mid_1[8].x, slots_mid_1[8].y, 32, 32)};
		
		slots_mid_2_boxes = new Rectangle[]{new Rectangle(slots_mid_2[0].x, slots_mid_2[0].y, 32, 32), 
				new Rectangle(slots_mid_2[1].x, slots_mid_2[1].y, 32, 32),
				new Rectangle(slots_mid_2[2].x, slots_mid_2[2].y, 32, 32),
				new Rectangle(slots_mid_2[3].x, slots_mid_2[3].y, 32, 32),
				new Rectangle(slots_mid_2[4].x, slots_mid_2[4].y, 32, 32),
				new Rectangle(slots_mid_2[5].x, slots_mid_2[5].y, 32, 32),
				new Rectangle(slots_mid_2[6].x, slots_mid_2[6].y, 32, 32),
				new Rectangle(slots_mid_2[7].x, slots_mid_2[7].y, 32, 32), 
				new Rectangle(slots_mid_2[8].x, slots_mid_2[8].y, 32, 32)};
		
		slots_bottom_boxes = new Rectangle[]{new Rectangle(slots_bottom[0].x, slots_bottom[0].y, 32, 32), 
				new Rectangle(slots_bottom[1].x, slots_bottom[1].y, 32, 32),
				new Rectangle(slots_bottom[2].x, slots_bottom[2].y, 32, 32),
				new Rectangle(slots_bottom[3].x, slots_bottom[3].y, 32, 32),
				new Rectangle(slots_bottom[4].x, slots_bottom[4].y, 32, 32),
				new Rectangle(slots_bottom[5].x, slots_bottom[5].y, 32, 32),
				new Rectangle(slots_bottom[6].x, slots_bottom[6].y, 32, 32),
				new Rectangle(slots_bottom[7].x, slots_bottom[7].y, 32, 32), 
				new Rectangle(slots_bottom[8].x, slots_bottom[8].y, 32, 32)};
		
	}
	
	public void update(GameContainer gc, int delta){
		for (Rectangle r: slots_top_boxes){
			
		}
	}
	
	public void renderInventoryScreen(Graphics g){
		screen.draw(0,0);
		
		if (invPlayer.getAvailableIndex() < 9 && invPlayer.getAvailableIndex() >= 0){
			for (Item i: Player.inventory.getItems()){
				if (i != null){
					i.icon.draw(slots_top[invPlayer.getAvailableIndex() - 1].x, slots_top[invPlayer.getAvailableIndex()].y);
				}
			}
		}else if (invPlayer.getAvailableIndex() < 18 && invPlayer.getAvailableIndex() >= 9){
			for (Item i: Player.inventory.getItems()){
				if (i != null){
					i.icon.draw(slots_mid_1[invPlayer.getAvailableIndex() - 1].x, slots_mid_1[invPlayer.getAvailableIndex()].y);
				}
			}
		}else if (invPlayer.getAvailableIndex() < 27 && invPlayer.getAvailableIndex() >= 18){
			for (Item i: Player.inventory.getItems()){
				if (i != null){
					i.icon.draw(slots_mid_2[invPlayer.getAvailableIndex() - 1].x, slots_mid_2[invPlayer.getAvailableIndex()].y);
				}
			}
		}else if (invPlayer.getAvailableIndex() < 36 && invPlayer.getAvailableIndex() >= 27){
			for (Item i: Player.inventory.getItems()){
				if (i != null){
					i.icon.draw(slots_bottom[invPlayer.getAvailableIndex() - 1].x, slots_bottom[invPlayer.getAvailableIndex()].y);
				}
			}
		}
	}
	
}
