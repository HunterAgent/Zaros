package framework.Tasks;

import framework.Player.Player;
import framework.Teleportation.Location;
import framework.Teleportation.Teleportation;
import framework.Utils.Logger;
import simple.hooks.scripts.task.Task;
import simple.hooks.simplebot.teleporter.Teleporter;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class TestingTask extends Task {
    public TestingTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        Logger.log("Logging objects:");
        for (SimpleObject x : ctx.objects.populate().filter(o -> o.distanceTo(ctx.players.getLocal()) < 3)) {
            if (x != null)
                Logger.log(x.getName() + ": " + Integer.toString(x.getId()));
        }
        Logger.log("\n");
    }

    @Override
    public String status() {
        return "Testing";
    }

    @Override
    public boolean condition() {
        return true;
    }
}
