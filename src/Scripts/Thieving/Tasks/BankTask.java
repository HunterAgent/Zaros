package Scripts.Thieving.Tasks;

import framework.Player.Inventory;
import framework.World.Bank;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class BankTask extends Task {

    private final Bank bank;

    public BankTask(ClientContext ctx, Bank bank) {
        super(ctx);
        this.bank = bank;
    }

    @Override
    public void run() {
        this.bank.open();
        Bank.depositAll();
    }

    @Override
    public String status() {
        return "Banking";
    }

    @Override
    public boolean condition() {
        return Inventory.isFull();
    }
}
