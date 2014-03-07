package snm.SnmMapleChopper;

import org.hexbot.api.event.MessageEvent;


public interface WoodcutEvent extends MessageEvent{
	//axes
		public static final int runeAxe =1359;
		public static final int brokenAxe = 506;
		public static final int blackAxe = 1361;
		public static final int steelAxe = 1353;
		public static final int adamantAxe = 1357;
	//trees
		public static final int normalTree = 1276;
		public static final int oakTree = 2102;
		public static final int willowTree = 2104;
		public static final int mapleTree = 2108;
		public static final int yewTree = 2104;
		public static final int magicTree = 2104;
		public static final int madTree = 10835;
	//logs	
		public static final int normalLog = 0;
		public static final int oakLog = 1521;
		public static final int willowLog = 1519;
		public static final int mapleLog = 1517;
		public static final int teakLog = 6333;
		public static final int mahoganyLog = 6332;
		public static final int yewLog = 1515;
		public static final int magicLog = 0;
		
	//items	
		public static final int birdNestID= 5073;
		public static final String birdNestName= "Bird nest";
		
	//messages
		
	//wcstats
		
		
	public void onMessageReceived(int sndr, String msg);

	public void pickNest(String string);

	void onMessageReceived(int sndr, String sndrStr, String msg);
}