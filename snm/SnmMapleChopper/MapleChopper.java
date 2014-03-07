package snm.SnmMapleChopper;

import java.awt.Graphics;
import java.util.Arrays;

import org.hexbot.api.event.RenderEvent;
import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.api.methods.interfaces.Skills;
import org.hexbot.api.wrapper.GroundItem;
import org.hexbot.script.Category;
import org.hexbot.script.Employer;
import org.hexbot.script.ScriptManifest;


@ScriptManifest(name = "SnmMapleChopper - By SNM", description = "Cuts and banks Maples in Seers Village", author = "SerbNegroMonte", version = 1.3f, category = Category.WOODCUTTING)
public class MapleChopper extends Employer implements RenderEvent,WoodcutEvent {

    static int logCount;
    static int nestsPicked;
    static int exp;
    static int totalExpGain;
    public int treesDowned = 0;
	public int logsPerTree = 0;
    public MapleChopper(MethodContext a) {
        super(a);
    }

    @Override
    public void onStart() {
        System.out.println("Starting Chopper - " + getManifest().version());
        exp = context.skills.getCurrentExp(Skills.WOODCUTTING);
        submit(new goToMaples(context), new chopMaple(context), new goToBank(context), new BankLogs(context));
    }

    @Override
    public void onStop() {
        System.out.println("Stopping Chopper");
        System.out.println("Banked " + logCount + "logs, gaining " + totalExpGain + "EXP");
    }

    @Override
    public void onRender(Graphics graphics) {
    	totalExpGain = context.skills.getCurrentExp(Skills.WOODCUTTING)-exp;
        graphics.drawString("Logs: " + logCount, 390, 270);
        graphics.drawString("Exp:" + (totalExpGain), 390, 285);
        graphics.drawString("Nests" + nestsPicked, 390, 300);
        graphics.drawString("Chopper - By SNM", 390, 315);
    }

	@Override
	public void onMessageReceived(int sndr, String msg) {
		// TODO Auto-generated method stub
		if (msg.contains("nest")){
			pickNest("birdNestName");
			System.out.println("Picking up bird's nest");
		}
	}
	
	@Override
	public void onMessageReceived(int sndr, String sndrStr, String msg) {
		// TODO Auto-generated method stub
		if (msg.contains("nest")){
			pickNest(birdNestName);
			nestsPicked++;
			System.out.println("Picking a bird's nest");
		}else if (msg.contains("swing your axe at")){
			treesDowned++;
			System.out.println("You got " + logsPerTree + " logs from this tree");
			logsPerTree = 0;
			System.out.println("Chopping new tree");
		}else if(msg.contains("You get some")){
			logsPerTree++;
		}else{
			//System.out.println("Sender: " + sndr + " MSG " + msg);
		}
	}

	@Override
	public void pickNest(String string) {
		GroundItem nest = context.groundItems.getNearest("Bird nest");
		if (nest != GroundItem.EMPTY){
			//context.groundItems.getNearest("Bird nest").interact("Take");
		//nest.interact("Take");
			nest.click();
		System.out.println("OOOO real nest picked: ");
		System.out.println(Arrays.asList(nest.getDefinition().getActions()));
		}else {
			System.out.println("You just typed nest nub: ");
			
		}
		// TODO Auto-generated method stub
		
	}


    
}
