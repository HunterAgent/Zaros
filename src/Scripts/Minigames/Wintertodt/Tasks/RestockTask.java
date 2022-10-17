package Scripts.Minigames.Wintertodt.Tasks;

import framework.Camera;
import framework.Player.Inventory;
import framework.Player.Player;
import framework.World.Areas;
import framework.World.Travel;
import framework.World.WorldObject;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class RestockTask extends Task {

    private final WorldArea RESUPPLY_AREA = Areas.makeArea(1621, 3972, 1639, 3986);
    private final int HAMMER_CRATE = 29316;
    private final int KNIFE_CRATE = 29317;
    private final int AXE_CRATE = 29318;
    private final int TINDER_CRATE = 29319;

    private boolean hasSupplies()
    {
        return Player.hasAxe()&&
                Inventory.contains("Hammer") &&
                Inventory.contains("Tinderbox") &&
                Inventory.contains("Knife");
    }

    private boolean collectCrate(int id) {
        SimpleObject crate = WorldObject.getNearest(id);
        if (crate == null)
            return false;

        if (WorldObject.isValid(crate)) {
            return crate.click(0);
        }

        Travel.travel(crate);
        Camera.turnTo(crate);

        return false;
    }

    public RestockTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        if (!RESUPPLY_AREA.containsPoint(Player.getLocation()))
        {
            Travel.travel(RESUPPLY_AREA);
        }

        if (!Inventory.contains("Hammer"))
            collectCrate(HAMMER_CRATE);

        else if (!Inventory.contains("Knife"))
            collectCrate(KNIFE_CRATE);

        else if (!Inventory.contains("Tinderbox"))
            collectCrate(TINDER_CRATE);

        else if (!Player.hasAxe())
            collectCrate(AXE_CRATE);

    }

    @Override
    public String status() {
        return "Getting supplies";
    }

    @Override
    public boolean condition() {
        return Areas.WINTERTODT_GAME.containsPoint(Player.getLocation()) &&
                !hasSupplies() &&
                !Inventory.isFull() &&
                !Player.isAnimating();
    }
}
