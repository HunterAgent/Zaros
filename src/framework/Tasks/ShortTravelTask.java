package framework.Tasks;

import framework.Player.Player;
import framework.World.Travel;
import net.runelite.api.coords.WorldPoint;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class ShortTravelTask extends Task {

    private final WorldArea generalArea, destination;

    public ShortTravelTask(ClientContext ctx, WorldArea generalArea, WorldArea destination) {
        super(ctx);
        this.generalArea = generalArea;
        this.destination = destination;
    }

    @Override
    public void run() {
        Travel.travel(this.destination);
    }

    @Override
    public String status() {
        return "Traveling";
    }

    @Override
    public boolean condition() {
        return this.generalArea.containsPoint(Player.getLocation()) && !this.destination.containsPoint(Player.getLocation());
    }
}
