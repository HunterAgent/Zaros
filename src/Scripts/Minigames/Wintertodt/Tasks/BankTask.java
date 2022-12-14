package Scripts.Minigames.Wintertodt.Tasks;

import framework.Player.Inventory;
import framework.Player.Player;
import framework.World.Areas;
import framework.World.Bank;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class BankTask extends Task {
    public BankTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        Bank.WINTERTOD.open();
        Bank.depositAllExcept("axe", "Tinderbox", "Knife", "Hammer");
        if (Bank.withdraw("Saradomin brew(4)", 4))
            Bank.close();
    }

    @Override
    public String status() {
        return "Banking";
    }

    @Override
    public boolean condition() {
        return !Inventory.hasBrews() &&
                (!Areas.WINTERTODT_GAME.containsPoint(Player.getLocation()) || Player.getHealthPercent() < 65);
    }
}
