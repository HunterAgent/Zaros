package Scripts.Minigames.Wintertodt.Tasks;

import framework.Player.Inventory;
import framework.Player.Player;
import framework.World.Areas;
import framework.World.Travel;
import framework.World.WorldObject;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class RejPotTask extends Task {
    public RejPotTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        if (Inventory.contains(20698)) {
            Inventory.leftClickUseItemOnItem(20697, 20698);
            ClientContext.instance().sleepCondition(Player::isAnimating, 500);
        } else {
            SimpleObject bush = WorldObject.getNearest("Sprouting Roots");
            // TODO: Export to WorldObject.interact(shouldLeftClick, action, area)
            if (bush != null) {
                if (WorldObject.isValid(bush)) {
                    bush.click(0);
                    if (!ClientContext.instance().sleepCondition(Player::isAnimating, 1000)) {
                        bush.click("Pick");
                        ClientContext.instance().sleepCondition(Player::isAnimating, 500);
                    }
                } else {
                    Travel.travel(bush);
                }
            } else {
                Travel.travel(Areas.WINTERTODT_CORNER);
            }
        }

    }

    @Override
    public String status() {
        return "Making Rej potions";
    }

    @Override
    public boolean condition() {
        return Inventory.contains(20697);
    }
}
