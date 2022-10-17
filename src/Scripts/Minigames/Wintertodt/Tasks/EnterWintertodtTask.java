package Scripts.Minigames.Wintertodt.Tasks;

import framework.Player.Player;
import framework.World.Areas;
import framework.World.Travel;
import framework.World.WorldObject;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class EnterWintertodtTask extends Task {
    public EnterWintertodtTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        SimpleObject gate = WorldObject.getNearest("Doors of Dinh");
        if (gate != null) {
            if (WorldObject.isValid(gate)) {
                gate.click(0);
            } else {
                Travel.travel(gate);
            }
        } else {
            final WorldArea gateArea = Areas.makeArea(1625, 3965, 1633, 3952);
            Travel.travel(gateArea);
        }
    }

    @Override
    public String status() {
        return "Entering Wintertodt";
    }

    @Override
    public boolean condition() {
        return !Areas.WINTERTODT_GAME.containsPoint(Player.getLocation()) &&
                Areas.WINTERTODT.containsPoint(Player.getLocation());
    }
}
