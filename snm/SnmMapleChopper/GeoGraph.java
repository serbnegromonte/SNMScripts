package snm.SnmMapleChopper;

import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.api.methods.movement.Walking;
import org.hexbot.api.wrapper.Area;
import org.hexbot.api.wrapper.Tile;

//import java.util.Random;

public class GeoGraph extends Walking{

	
	public GeoGraph(MethodContext c) {
		super(c);
		// TODO Auto-generated constructor stub
	}


	public static final Area SEERS_MAPLE_AREA = new Area(
            new Tile(2720, 3497, 0),
            new Tile(2735, 3506, 0));

    public static final Area SEERS_BANK_AREA = new Area(
            new Tile(2729, 3490, 0),
            new Tile(2722, 3493, 0));
    
    public static final Area SEERS_SOUTH_MAPLE_AREA = new Area(
            new Tile(2719, 3463, 0),
            new Tile(2731, 3484, 0));
       
    public static final Area SEERS_YEW_AREA = new Area(
            new Tile(2704, 3458, 0),
            new Tile(2717, 3469, 0));


   public static Area getArea(Area a){   
	   return a;
   }
   
   public void goToArea(Area a){
 	    context.walking.walk(a.getCentralTile());
    }
 	 
    
    public double distanceArea(Area a, Area b){
    	return a.getCentralTile().distanceTo(b.getCentralTile());
    	
    }

}
