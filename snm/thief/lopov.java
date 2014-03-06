package snm.thief;

import org.hexbot.api.event.RenderEvent;
import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.api.methods.interfaces.Skills;
import org.hexbot.api.util.Time;
import org.hexbot.api.util.Timer;
import org.hexbot.api.wrapper.Area;
import org.hexbot.script.AbstractScript;
import org.hexbot.script.Category;
import org.hexbot.script.ScriptManifest;
import org.hexbot.api.event.MessageEvent;

import snm.GeoGraph;
import snm.Methods;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: SNM
 * Date: March 3rd 2014
 * Copyright under GPL license by author.
 */
@ScriptManifest(author = "SerbNegroMonte", name = "ArdyThief - by SNM", description = "pickpockets guards", version = 1.1f, category = Category.THIEVING)
public class lopov extends AbstractScript implements RenderEvent{
    long startTime = System.currentTimeMillis();
    int startExp = context.skills.getCurrentExp(Skills.THIEVING);
    int foodID = 0;
    Area workArea = GeoGraph.ARDY_MARKET;
    MessageEvent me;
    public lopov(MethodContext context) {
        super(context);
    }

    @Override
    public int loop() {
    	
    	Methods.runArdyThief(context, "Guard", foodID, workArea);
    	
    	return 0;
    }

    @Override
    public void onRender(Graphics g) {
        g.setColor(Color.WHITE);
        long runtime = System.currentTimeMillis() - startTime;
        int expGain = context.skills.getCurrentExp(Skills.THIEVING) - startExp;
        int invCount = context.inventory.getCount();
        g.drawString(Timer.format(runtime), 390, 270);
        g.drawString("Animation" + context.players.getLocal().getInteractingIndex(), 390, 285);
        g.drawString("Inventory Items" + invCount, 390, 300);
        g.drawString("Exp Gained: " + (expGain) + " /H (" + Skills.getPerHour(runtime, expGain) + ")", 390, 315);
    }



	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}

public void onMessageReceived(int sndr, String sndrStr, String msg) {
		if (msg.contains("stunned")){
			System.out.println("You are stunned hold on");
			Time.sleep(1500, 2500);
		}else if(msg.contains("You pick")){
			Time.sleep(500, 900);
			System.out.println("Picked pockets");		
		}else {
			Time.sleep(500, 900);
		}
		
	}

	
}