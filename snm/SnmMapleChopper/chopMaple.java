package snm.SnmMapleChopper;

import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.api.methods.interfaces.Tabs.Tab;
import org.hexbot.api.util.Condition;
import org.hexbot.api.util.Time;
import org.hexbot.api.wrapper.Area;
//import org.hexbot.api.util.Timer;
import org.hexbot.api.wrapper.GameObject;
import org.hexbot.api.wrapper.GroundItem;
import org.hexbot.script.Worker;

import snm.SnmMapleChopper.GeoGraph;

class chopMaple extends Worker {
int mapleTree = 2108;
int mapleLog = 1517;
int nest = 5073;

    private GameObject tree;
	//private GameObject log;
	
    public chopMaple(MethodContext a) {
        super(a);
    }

    @Override
    public boolean validate() {
        tree = context.gameObjects.getNearest(mapleTree); 
        return tree != GameObject.EMPTY && !context.inventory.isFull() && GeoGraph.SEERS_MAPLE_AREA.contains(context.players.getLocal()) && tree.getId() == mapleTree;
    }

    @Override
    public void work() {
        //Make sure the flax is visible
        if (!tree.isVisible()) {
            if (context.calculations.distanceTo(tree) < 4) {
                context.camera.turnTo(tree);
            } else {
                context.walking.walk(tree);
            }
        }

        //Chop da tree
        final int count = context.inventory.getCount(mapleLog);
        if ((context.players.getLocal().getAnimationIndex() == -1) && ((GeoGraph.SEERS_MAPLE_AREA.contains(context.players.getLocal()) || 
        		GeoGraph.SEERS_SOUTH_MAPLE_AREA.contains(context.players.getLocal()))
        	)) {
        	tree = context.gameObjects.getNearest(mapleTree);
        	if (context.groundItems.getNearest("Bird nest") != GroundItem.EMPTY){
        		context.groundItems.getNearest("Bird nest").click();
        		Time.sleep(5700, 6900);
        	}if (context.players.getLocal().isInCombat()){
        		flee(GeoGraph.SEERS_SOUTH_MAPLE_AREA);
        	}
        	tree.interact("Chop down");
        	Time.sleep(3700, 4900);
        	
        	Tab tab = (context.inventory.getCount() %2 == 1) ? Tab.STATS : Tab.INVENTORY;
        	context.tabs.open(tab);
        	
        }if ((context.players.getLocal().getAnimationIndex() != -1) && Time.waitFor(new Condition()  {
            @Override
            public boolean accept() {
                return context.inventory.getCount(mapleLog) > count;
            }
        }, 1000)) {
        	MapleChopper.logCount++;
        }
    }

	private void flee(Area seersSouthMapleArea) {
		context.walking.walk(seersSouthMapleArea.getCentralTile());
		// TODO Auto-generated method stub
		
	}
}