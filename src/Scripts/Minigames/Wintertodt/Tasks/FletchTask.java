package Scripts.Minigames.Wintertodt.Tasks;

import framework.Player.Inventory;
import framework.Player.Player;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class FletchTask extends Task {
    public FletchTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        Inventory.useItemOnItem("Knife", "Bruma root");
        ClientContext.instance().sleepCondition(Player::isAnimating, 500);
    }

    @Override
    public String status() {
        return "Fletching roots";
    }

    @Override
    public boolean condition() {
        return !Player.isAnimating() &&
                Inventory.contains("Knife") &&
                Inventory.contains("Bruma root") &&
                Inventory.isFull();
    }
}
