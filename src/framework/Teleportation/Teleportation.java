package framework.Teleportation;

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
        return ClientContext.instance().magic.castHomeTeleport();
    }
}
