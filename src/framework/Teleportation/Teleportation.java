package framework.Teleportation;

import framework.Player.Player;
import framework.World.Areas;
import simple.hooks.simplebot.teleporter.Teleporter;
import simple.robot.api.ClientContext;

public class Teleportation {
    public static boolean teleport(Location loc)
    {
        Teleporter teleporter = new Teleporter(ClientContext.instance());
        if (!teleporter.opened())
            teleporter.open();
        return teleporter.teleportStringPath(loc.getCategory().name(), loc.getName());
    }

    public static boolean home()
    {
        ClientContext.instance().magic.castHomeTeleport();
        return ClientContext.instance().sleepCondition(
                () -> Areas.EDGEVILLE_AREA.containsPoint(Player.getLocation()) && !Player.isAnimating(),
                1000);
    }
}
