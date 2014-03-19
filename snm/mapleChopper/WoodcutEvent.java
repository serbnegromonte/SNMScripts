package snm.woodcut.mapleChopper;

import org.hexbot.api.event.InventoryEvent;
import org.hexbot.api.event.MessageEvent;


public interface WoodcutEvent extends MessageEvent, InventoryEvent{

	//items	
		public static final int birdNestID= 5073;
		public static final String birdNestName= "Bird nest";
		
	//messages
		
	//wcstats
		
		
	public void onMessageReceived(int sndr, String msg);

	public void pickNest(String string);

	void onMessageReceived(int sndr, String sndrStr, String msg);
}