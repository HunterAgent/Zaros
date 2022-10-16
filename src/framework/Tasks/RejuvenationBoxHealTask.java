package framework.Tasks;

import framework.Player.Player;
import framework.Teleportation.Teleportation;
import framework.World.Areas;
import framework.World.WorldObject;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class RejuvenationBoxHealTask extends Task {
    private final int threshold;
    public RejuvenationBoxHealTask(ClientContext ctx, int threshold) {
        super(ctx);
        this.threshold = threshold;
    }

    @Override
    public void run() {
        if (!Areas.EDGEVILLE_AREA.containsPoint(Player.getLocation()))
        {
            Teleportation.home();
        }

//        SimpleObject box = WorldObject.getNearest("Rejuvenation box");
        SimpleObject box = WorldObject.getNearest(60003);
        if (WorldObject.isValid(box))
        {
            if (!box.click(0))
                box.click("Heal");
        }
    }

    @Override
    public String status() {
        return "Rejuvenating";
    }

    @Override
    public boolean condition() {
        return Player.getHealthPercent() < threshold;
    }
}
