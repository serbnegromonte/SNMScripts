package snm.woodcut.mapleChopper;

import org.hexbot.api.methods.core.MethodContext;
import org.hexbot.script.Worker;
import snm.woodcut.mapleChopper.GeoGraph;
class BankLogs extends Worker {
boolean wieldingAxe = true;
    public BankLogs(MethodContext a) {
        super(a);
    }

    @Override
    public boolean validate() {
        return (context.inventory.isFull() || context.inventory.contains("Maple logs")) && GeoGraph.SEERS_BANK_AREA.contains(context.players.getLocal());
    }

    @Override
    public void work() {
        if (context.bank.open()) {
        	if (wieldingAxe){
            context.bank.depositAll();
        	}else{
        		context.inventory.getItem("Maple logs").interact("Deposit-All");
        	}
            if (!context.inventory.contains("Maple logs")) {
                context.bank.close();
            }
        }
    }
}