package framework.Tasks;

import framework.World.Bank;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class FailSafeCloseBankTask extends Task {
    public FailSafeCloseBankTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        Bank.close();
    }

    @Override
    public String status() {
        return "FailSafe - closing bank";
    }

    @Override
    public boolean condition() {
        return Bank.isOpen();
    }
}
