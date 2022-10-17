package Scripts.Minigames.Wintertodt.Tasks;

import framework.Player.Inventory;
import framework.Player.Player;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class HealTask extends Task {
    public HealTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        SimpleItem brew = Inventory.getItem("Saradomin brew");
        if (brew != null) {
            brew.click(0);
            ClientContext.instance().sleepCondition(Player::isAnimating, 1000);
        }
    }

    @Override
    public String status() {
        return "Healing";
    }

    @Override
    public boolean condition() {
        return Inventory.contains("Saradomin brew") && Player.getHealthPercent() < 65;
    }
}
