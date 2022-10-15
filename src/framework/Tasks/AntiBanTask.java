package framework.Tasks;

import framework.Player.Player;
import framework.Teleportation.Teleportation;
import framework.World.Areas;
import simple.hooks.scripts.task.Task;
import simple.hooks.simplebot.AntiBan;
import simple.robot.api.ClientContext;

public class AntiBanTask extends Task {

    private final AntiBan anti_ban;
    private final int pause_length_minutes;

    public AntiBanTask(ClientContext ctx, int pause_length_minutes) {
        super(ctx);
        this.anti_ban = new AntiBan(ctx);
        this.pause_length_minutes = pause_length_minutes;
    }

    @Override
    public void run() {
        Teleportation.home();
        ClientContext.instance().sleep(1000 * 60 * pause_length_minutes);
    }

    @Override
    public String status() {
        return "Avoiding staff";
    }

    @Override
    public boolean condition() {
        return this.anti_ban.staffNearby() && !Areas.EDGEVILLE_AREA.containsPoint(Player.getLocation());
    }
}
