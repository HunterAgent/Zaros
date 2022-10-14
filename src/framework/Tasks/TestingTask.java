package framework.Tasks;

import framework.Teleportation.Location;
import framework.Teleportation.Teleportation;
import simple.hooks.scripts.task.Task;
import simple.hooks.simplebot.teleporter.Teleporter;
import simple.robot.api.ClientContext;

public class TestingTask extends Task {
    public TestingTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        if (!Teleportation.teleport(Location.WOODCUTTING_GUILD))
        {
            ctx.log("Failed on code");
            Teleporter tel = new Teleporter(ctx);
            if (!tel.teleportStringPath("Skilling", "Wilderness: Agility Course"))
            {
                ctx.log("Failed regualar");
            }
        }
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
