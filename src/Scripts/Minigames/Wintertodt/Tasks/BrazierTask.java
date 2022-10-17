package Scripts.Minigames.Wintertodt.Tasks;

import Scripts.Minigames.Wintertodt.Brazier;
import framework.Player.Player;
import framework.World.Areas;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class BrazierTask extends Task {
    public BrazierTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        Brazier.handleNearest();
        ClientContext.instance().sleepCondition(Player::isAnimating, 500);
    }

    @Override
    public String status() {
        return "Handling Brazier";
    }

    @Override
    public boolean condition() {
        return Areas.WINTERTODT_GAME.containsPoint(Player.getLocation()) &&
                !Player.isAnimating() &&
                Brazier.canHandleNearest();
    }
}
