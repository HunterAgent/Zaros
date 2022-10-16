package framework.Teleportation;

import framework.Player.Player;
import framework.Widget;
import framework.World.Areas;
import simple.hooks.simplebot.Game;
import simple.hooks.simplebot.teleporter.Teleporter;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;

public class Teleportation {
    public static boolean teleport(Location loc)
    {
//        Widget.openTab(Game.Tab.MAGIC);
//        SimpleWidget homeTele = Widget.getWidget(218, 6);
//        if (Widget.isValidWidget(homeTele) && homeTele.click(loc.getName()) && Player.isAnimating())
//        {
//            return true;
//        }
//        else {
//            ClientContext.instance().mouse.clearMenu();
//        }
//
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
