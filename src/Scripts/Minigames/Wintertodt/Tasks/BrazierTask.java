package Scripts.Minigames.Wintertodt.Tasks;

import Scripts.Minigames.Wintertodt.Brazier;
import framework.Player.Inventory;
import framework.Player.Player;
import framework.World.Areas;
import framework.World.Travel;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class BrazierTask extends Task {
    public BrazierTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        if (!Areas.WINTERTODT_CORNER.containsPoint(Player.getLocation())) {
            Travel.travel(Areas.WINTERTODT_CORNER);
        }

        Brazier.handleNearestWithinArea(Areas.WINTERTODT_CORNER);
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
                Brazier.canHandleNearest(Areas.WINTERTODT_CORNER);
    }
}
