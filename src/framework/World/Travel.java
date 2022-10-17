package framework.World;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.interfaces.SimpleLocatable;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class Travel {
    public static boolean travel(SimpleLocatable target) {
        return ClientContext.instance().pathing.step(target.getLocation());
    }

    public static boolean travel(WorldArea destination) {
        WorldPoint tile = destination.randomTile();
        while (!reachable(tile))
            tile = destination.randomTile();

        return ClientContext.instance().pathing.step(tile);
    }

    public static boolean reachable(WorldPoint tile) {
        return ClientContext.instance().pathing.reachable(tile);
    }

    public static boolean reachable(SimpleLocatable target) {
        return ClientContext.instance().pathing.reachable(target);
    }

    public static boolean reachable(WorldArea destination) {
        int count = 0;
        WorldPoint tile = destination.randomTile();

        while (!reachable(tile) && count < 5) {
            tile = destination.randomTile();
            count++;
        }

        return count != 5;
    }

    public static double distance(WorldArea destination) {
        final int sample_size = 10;
        double total = 0;

        for (int i = 0; i < sample_size ; i++) {
            total += ClientContext.instance().pathing.distanceTo(destination.randomTile());
        }

        return total / sample_size;
    }

    public static double distance(SimpleLocatable target) {
        return ClientContext.instance().pathing.distanceTo(target.getLocation());
    }
}
