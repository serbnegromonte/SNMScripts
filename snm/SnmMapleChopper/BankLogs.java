package snm.SnmMapleChopper;

import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.script.Worker;
import snm.SnmMapleChopper.GeoGraph;
class BankLogs extends Worker {

    public BankLogs(MethodContext a) {
        super(a);
    }

    @Override
    public boolean validate() {
        return context.inventory.isFull() && GeoGraph.SEERS_BANK_AREA.contains(context.players.getLocal());
    }

    @Override
    public void work() {
        if (context.bank.open()) {
            context.bank.depositAll();

            if (!context.inventory.contains("Maple logs")) {
                context.bank.close();
            }
        }
    }
}