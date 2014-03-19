package snm.woodcut.mapleChopper;

import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.script.Worker;
import snm.woodcut.mapleChopper.GeoGraph;

class goToMaples extends Worker {
    public goToMaples(MethodContext a) {
        super(a);
    }

    @Override
    public boolean validate() {
        return !context.inventory.isFull() && (!GeoGraph.SEERS_MAPLE_AREA.contains(context.players.getLocal()) || !context.gameObjects.getNearest("Maple tree").isVisible());
    }

    @Override
    public void work() {
    	context.walking.walk(GeoGraph.SEERS_MAPLE_AREA.getCentralTile());
    }
}