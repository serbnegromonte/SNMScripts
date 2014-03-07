package snm.SnmMapleChopper;

import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.script.Worker;
import snm.SnmMapleChopper.GeoGraph;

class goToBank extends Worker {
GeoGraph gg = new GeoGraph(context);

    public goToBank(MethodContext a) {
        super(a);
    }

    @Override
    public boolean validate() {
        return context.inventory.isFull() && !GeoGraph.SEERS_BANK_AREA.contains(context.players.getLocal());
    }

    @Override
    public void work() {
        context.walking.walk(GeoGraph.SEERS_BANK_AREA.getCentralTile());
    }
}