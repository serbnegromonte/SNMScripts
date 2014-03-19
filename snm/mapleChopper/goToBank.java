package snm.woodcut.mapleChopper;

import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.script.Worker;
import snm.woodcut.mapleChopper.GeoGraph;

class goToBank extends Worker {

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