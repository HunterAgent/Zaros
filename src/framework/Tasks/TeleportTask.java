package framework.Tasks;

import framework.Player.Player;
import framework.Teleportation.Location;
import framework.Teleportation.Teleportation;
import framework.World.Areas;
import lombok.Setter;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class TeleportTask extends Task {

    @Setter
    private Location loc;
    @Setter
    private WorldArea target;

    public TeleportTask(ClientContext ctx, Location loc, WorldArea target) {
        super(ctx);
        this.loc = loc;
        this.target = target;
    }

    @Override
    public void run() {
        Teleportation.teleport(this.loc);
    }

    @Override
    public String status() {
        return "Teleporting to " + loc.getName();
    }

    @Override
    public boolean condition() {
        return !Areas.containsIgnoreZ(this.target, Player.getLocation());
    }
}
