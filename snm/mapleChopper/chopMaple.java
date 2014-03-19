package snm.woodcut.mapleChopper;

import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.api.methods.interfaces.Tabs.Tab;
import org.hexbot.api.util.Condition;
import org.hexbot.api.util.Time;
//import org.hexbot.api.util.Timer;
import org.hexbot.api.wrapper.GameObject;
import org.hexbot.api.wrapper.GroundItem;
import org.hexbot.api.wrapper.Tile;
import org.hexbot.script.Worker;

import snm.woodcut.mapleChopper.GeoGraph;

class chopMaple extends Worker {
	Integer treeId;
    private GameObject tree;
	//private GameObject log;
	
    public chopMaple(MethodContext a) {
        super(a);
    }

    @Override
    public boolean validate() {
        tree = context.gameObjects.getNearest("Maple tree"); 
        treeId = tree.getId();
        return tree != GameObject.EMPTY 
        		&& !context.inventory.isFull() 
        		&& (GeoGraph.SEERS_MAPLE_AREA.contains(context.players.getLocal()) || GeoGraph.SEERS_SOUTH_MAPLE_AREA.contains(context.players.getLocal()))
        		&& tree.getId() == 2118;
    }

    @Override
    public void work() {
        //Make sure the flax is visible
        if (!tree.isVisible()) {
        	lookForIt();
       
            if (context.calculations.distanceTo(tree) < 4) {
                context.camera.turnTo(tree);
            } else {
                context.walking.walk(tree);
            }
        }

        //Chop da tree
        if ((context.players.getLocal().getAnimationIndex() == -1) && 
        		((GeoGraph.SEERS_MAPLE_AREA.contains(context.players.getLocal()) || (GeoGraph.SEERS_SOUTH_MAPLE_AREA.contains(context.players.getLocal())))
        	)) {
        	tree = context.gameObjects.getNearest("Maple tree");
        	System.out.println("FOUND NEW: " + tree.getName() + "of TYPE: " +tree.getType());
        	if (context.groundItems.getNearest("Bird nest") != GroundItem.EMPTY){
        		context.groundItems.getNearest("Bird nest").click();
        		
        		if (Time.waitFor(new Condition() {
                    @Override
                    public boolean accept() {
                        return context.inventory.contains("Bird nest");
                    }
                }, 3900)) {
                    //MapleChopper.nests++;
                }
        		
        	}if (context.players.getLocal().isInCombat()){
        		System.out.println("Under attack will flee");
        		flee();
        		Time.sleep(3200, 4500);
        	}
        	
        	tree.interact("Chop down");
        	if (Time.waitFor(new Condition() {
                @Override
                public boolean accept() {
                    return context.players.getLocal().isAnimating() && !context.players.getLocal().isMoving();
                }
            }, 3900)) {
                //MapleChopper.nests++;
            }
        	
        	Tab tab = (context.inventory.getCount() %2 == 1) ? Tab.STATS : Tab.INVENTORY;
        	if (!context.tabs.isOpen(tab)){
        	context.tabs.open(tab);
        	}
        	
        }
        
    }

	private void lookForIt() {
		Tile cct = GeoGraph.SEERS_MAPLE_AREA.getCentralTile();
		context.camera.turnTo(cct);
		context.walking.walk(cct);
		System.out.println("looking for trees");
	}

	private void flee() {
		Tile fleetile = new Tile(2725, 3488);
		System.out.println("Runnnnnniiiiiinggggg !!!!!");
		context.walking.walk(fleetile, true);
		// TODO Auto-generated method stub
		
	}
	
}