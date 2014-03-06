package snm;

import org.hexbot.api.methods.movement.Walking;
import org.hexbot.api.wrapper.Area;
import org.hexbot.api.wrapper.Tile;

//import java.util.Random;

public class GeoGraph{
public Area area;
	private Walking wlk = new Walking(null);
	
	public static final Area FLAX_AREA = new Area(
            new Tile(2778, 3518, 0),
            new Tile(2783, 3518, 0),
            new Tile(2784, 3511, 0),
            new Tile(2777, 3511, 0));

    public static final Area SEERS_BANK_AREA = new Area(
            new Tile(2730, 3498, 0),
            new Tile(2721, 3498, 0),
            new Tile(2721, 3489, 0),
            new Tile(2731, 3488, 0));
    
    public static final Area ALKHRD_BANK_AREA = new Area(
            new Tile(3269, 3164, 0),
            new Tile(3270, 3164, 0),
            new Tile(3270, 3170, 0),
            new Tile(3269, 3170, 0));

    public static final Tile[] PATH_TO_FLAX = new Tile[]{
            new Tile(2726, 3486),
            new Tile(2727, 3493),
            new Tile(2728, 3471),
            new Tile(2732, 3455),
            new Tile(2739, 3441),
            new Tile(2743, 3448)};

    public static final Tile[] PATH_TO_BANK = new Tile[]{
            new Tile(2743, 3448),
            new Tile(2739, 3441),
            new Tile(2732, 3455),
            new Tile(2728, 3471),
            new Tile(2726, 3486),
            new Tile(2727, 3493)};
    
    public static final Area SPIN_AREA = new Area(
            new Tile(2713, 3470, 0),
            new Tile(2717, 3470, 0),
            new Tile(2717, 3473, 0),
            new Tile(2713, 3473, 0));
    
    // SEERS AGILE COURSE
    public static final Area SEERS_0 = new Area(
            new Tile(2728, 3486, 0),
            new Tile(2731, 3486, 0),
            new Tile(2731, 3489, 0),
            new Tile(2728, 3489, 0));
   
    public static final Area SEERS_1 = new Area(
            new Tile(2722, 3493, 0),
            new Tile(2722, 3494, 0),
            new Tile(2723, 3494, 0),
            new Tile(2723, 3493, 0));
    
    
    public static final Area SEERS_2 = new Area(
            new Tile(2709, 3490, 0),
            new Tile(2710, 3488, 0),
            new Tile(2709, 3488, 0),
            new Tile(2710, 3490, 0));
    
    
    public static final Area SEERS_3 = new Area(
            new Tile(2710, 3480, 0),
            new Tile(2710, 3477, 0),
            new Tile(2711, 3477, 0),
            new Tile(2711, 3480, 0));
    
    
    public static final Area SEERS_4 = new Area(
            new Tile(2704, 3470, 0),
            new Tile(2700, 3470, 0),
            new Tile(2700, 3471, 0),
            new Tile(2704, 3471, 0));
    
    
    public static final Area SEERS_5 = new Area(
            new Tile(2702, 3465, 0),
            new Tile(2702, 3460, 0),
            new Tile(2701, 3460, 0),
            new Tile(2701, 3465, 0));
    
public static final Area ARDY_MARKET = new Area(
		new Tile(2649, 3292, 0),
		new Tile(2678, 3324, 0)
		);
    
    public static Area [] SEERS_COURSE_AREAS = {SEERS_0,SEERS_1,SEERS_2,SEERS_3,SEERS_4,SEERS_5};
   
    
   public Area getArea(Area a){   
	   return this.area;
   }
   
   public void goToArea(Area a){
 	    wlk.walk(goToTleArea(a));
    }
    
    private static Tile goToTleArea(Area at){
 	   Tile t = at.getCentralTile();
 	   return t; 
 	 
    }
    
    public double distanceArea(Area a, Area b){
    	return a.getCentralTile().distanceTo(b.getCentralTile());
    	
    }

}
