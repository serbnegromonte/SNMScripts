package snm;

import java.awt.Point;
import java.util.Random;
import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.api.methods.input.Keyboard;
import org.hexbot.api.methods.input.Mouse;
import org.hexbot.api.methods.interactive.Players;
import org.hexbot.api.methods.interfaces.Inventory;
import org.hexbot.api.methods.interfaces.Tabs;
import org.hexbot.api.methods.interfaces.Tabs.Tab;
import org.hexbot.api.methods.util.Calculations;
import org.hexbot.api.util.Time;
import org.hexbot.api.wrapper.Area;
import org.hexbot.api.wrapper.Interactive;
import org.hexbot.api.wrapper.InventoryItem;
import org.hexbot.api.wrapper.Npc;
import org.hexbot.api.wrapper.Tile;
import snm.Constants;

public class Methods implements Constants{
	
	public static int antibans;
	Players plyr = new Players(null);
    static Mouse mice = new Mouse(null);
    static Keyboard robot = new Keyboard(null);
    Calculations calc = new Calculations(null);
    static Tabs atab = new Tabs(null);
    static Tab [] tabarr = Tabs.Tab.values();
    
	private final java.util.Random random = new java.util.Random();
	
	public boolean isBusy() {
		boolean flag = false;
		for (int i = 0; i < 4; i++) {
			if (plyr.getLocal().getAnimationIndex() != -1) {
				flag = true;
				break;
			}
			Time.sleep(300, 600);
		}
		return flag;
	}
	
	public void moveMice(int a, int b){
		int argx, argy;
		int rx = random.nextInt(8) + (-4);
		int ry = random.nextInt(8) + (-4);
		argx = a + rx;
		argy = b + ry;
		Point p = new Point(argx, argy);
		//return p;
		mice.move(p);
	}
	
	public void mouseRoam(int a, int b, int luft){
		int argx, argy;
		int sluft = ((luft%2 == 0) ? (luft+0) : (luft +1)); 
		int rx = (random.nextInt((sluft)) + (sluft/2 * -1));
		int ry = (random.nextInt((sluft)) + (sluft/2 * -1));
		argx = a + rx;
		argy = b + ry;
		Point p = new Point(argx, argy);
		//return p;
		mice.move(p);
	}
	public static long getGainedExp(long a, long b) {
		long expGain = b - a;
		// TODO Auto-generated method stub
		return expGain;
	}
	
	public static boolean eatFood(MethodContext c, int id){
		InventoryItem food;
		if (id == 0){
			id = getSmartFood(c.inventory);
			if(id < 0){
				System.out.println("You have no cheap food to eat");
				return false;
			}else{
			c.tabs.open(Tab.INVENTORY);
			food = c.inventory.getItem(id);
			food.interact("Eat");
			System.out.println("Eating" + food.getName());
			return true;
			}
		}else { 
			if (c.inventory.contains(id)){
			c.tabs.open(Tab.INVENTORY);
			food = c.inventory.getItem(id);
			food.interact("Eat");
			System.out.println("Eating" + food.getName());
			return true;
		}else{
			System.out.println("You dont have that type of food");
		return false;
			}
		}
	}
	
	public static int getSmartFood(Inventory inv){
		int [] foods = Constants.cheapFood;
		for(int i=0;i<foods.length;i++){
			if (inv.contains(foods[i])) {
				return foods[i];
				}
		}
		return -1;
	}
	
	public static boolean runArdyThief(MethodContext c, String npc_str, int foodID, Area a){
		if(c.players.getLocal().getHealth() < 20){
    		System.out.println("Your health is below 40");
    		boolean healed = eatFood(c, foodID);
    		Time.sleep(600, 900);
    		if (healed){
    			pickpocket(c, npc_str , a);
    		}
    		if(!healed){
    			Methods.getStallFood(c);
    		}
    	}else {
		if (c.players.getLocal().getAnimationIndex() == -1 && a.contains(c.players.getLocal())) {
			pickpocket(c, npc_str , a);
		}else if (!a.contains(c.players.getLocal())){
        	lookFor(c, a);
    }
    	}
		return true;
	}
	
	public static boolean pickpocket(MethodContext c, String npc_str, Area a){
    	System.out.println("Picking pocket");
            Npc npc = c.npcs.getNearest(npc_str);
            boolean succ = npc.interact("Pickpocket");
                if(succ){
                	System.out.println("SUCCESS");
                Time.sleep(1600, 1900);
                }else{ 
                	System.out.println("FAILURE");
                	Time.sleep(3400, 4800); 
                	}
		return false;
	}
	
	public static void lookFor(MethodContext c, Area a){
		Tile rat = getRandomTileInArea(a);
		c.camera.turnTo(a.getCentralTile());
		c.walking.walk(rat);
	}
	
	public static Tile getRandomTileInArea(Area a){
		Random rnd = new Random();
		Tile[] tiles = a.getTiles();
		int idx = rnd.nextInt(tiles.length);
		return tiles[idx];
		
	}
	public static boolean getStallFood(MethodContext c) {
		Tile t = new Tile(2667, 3312);
		c.walking.walk(t);
		Interactive bs = c.gameObjects.getNearest(Constants.BAKE_STALL);
		while (!c.inventory.isFull()){
			if (c.players.getLocal().getInteractingIndex() == -1){
		bs.interact("Steal-from");
		Time.sleep(2500, 3000);	
			}
		}
		return true;
		// TODO Auto-generated method stub
		
	} 
}