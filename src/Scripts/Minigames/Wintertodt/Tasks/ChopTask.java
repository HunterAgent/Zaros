package Scripts.Minigames.Wintertodt.Tasks;

import framework.Player.Inventory;
import framework.Player.Player;
import framework.World.Areas;
import framework.World.Travel;
import framework.World.WorldObject;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class ChopTask extends Task {

    private SimpleObject roots;

    public ChopTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        if (WorldObject.isValid(roots)) {
            roots.click(0);
            if (!ClientContext.instance().sleepCondition(Player::isAnimating, 1000)) {
                roots.click("Chop");
            }
        } else {
            Travel.travel(roots);
        }
    }

    @Override
    public String status() {
        return "Chopping roots";
    }

    @Override
    public boolean condition() {
        roots = WorldObject.getNearestWithinArea("Bruma roots", Areas.WINTERTODT_CORNER);
        return roots != null && Player.hasAxe() && !Inventory.isFull() && !Player.isAnimating();
    }
}
