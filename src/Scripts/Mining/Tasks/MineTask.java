package Scripts.Mining.Tasks;

import Scripts.Mining.Rock;
import framework.Camera;
import framework.Player.Inventory;
import framework.Player.Player;
import framework.Player.Skill;
import framework.Utils.Logger;
import framework.World.WorldObject;
import simple.hooks.filters.SimpleSkills;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class MineTask extends Task {

    private final Rock rockType;
    private SimpleObject rock;
    private final WorldArea mineArea;

    public MineTask(ClientContext ctx, Rock rockType, WorldArea mineArea) {
        super(ctx);
        this.rockType = rockType;
        this.mineArea = mineArea;

        if (Skill.getRealLvl(SimpleSkills.Skills.MINING) < this.rockType.getReqLvl()) {
            Logger.log("Stopping script, mining level not high enough for " + this.rockType.getName());
            ctx.stopScript();
        }
    }

    @Override
    public void run() {
        if (WorldObject.isValid(rock)) {
            if (!ClientContext.instance().sleepCondition(Player::isAnimating, 300)) {
                rock.click(0);
            }
        } else {
            Camera.turnTo(rock);
        }
    }

    @Override
    public String status() {
        return "Mining " + this.rockType.getName();
    }

    @Override
    public boolean condition() {
        rock = this.rockType.getNearest(this.mineArea);
        return !Inventory.isFull() && rock != null && Player.hasPickaxe() && !Player.isAnimating();
    }
}
