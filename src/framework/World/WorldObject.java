package framework.World;

import framework.Utils.Logger;
import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import java.util.List;

public class WorldObject {
    public static boolean isValid(SimpleObject obj) {
        return obj != null && obj.validateInteractable();
    }

    public static SimpleObject getNearest(String name) {
        return ClientContext.instance().objects.populate().filter(name).nearest().next();
    }

    public static SimpleObject getNearest(String ... name) {
        return ClientContext.instance().objects.populate().filter(name).nearest().next();
    }

    public static SimpleObject getNearest(int id) {
        return ClientContext.instance().objects.populate().filter(id).nearest().next();
    }

    public static SimpleObject getNearest(int ... id) {
        return ClientContext.instance().objects.populate().filter(id).nearest().next();
    }

    public static SimpleObject getNearest(List<Integer> id) {
        return ClientContext.instance().objects.populate().filter(obj -> id.contains(obj.getId())).nearest().next();
    }

    public static SimpleObject getNearest(int id, WorldPoint point) {
        return ClientContext.instance().objects.populate().filter(id).nearest(point).next();
    }

    public static SimpleObject getNearestWithinArea(String name, WorldArea area) {
        return ClientContext.instance().objects.populate().filter(name).filter(o -> area.containsPoint(o.getLocation())).nearest().next();
    }

    public static SimpleObject getNearestWithinArea(int[] id, WorldArea area) {
        return ClientContext.instance().objects.populate().filter(id).filter(o -> area.containsPoint(o.getLocation())).nearest().next();
    }

    public static SimpleObject getNearestWithinArea(List<Integer> id, WorldArea area) {
        return ClientContext.instance().objects.populate().filter(obj -> id.contains(obj.getId())).filter(o -> area.containsPoint(o.getLocation())).nearest().next();
    }
}
