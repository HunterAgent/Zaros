package framework;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class Object {
    public static boolean isValid(SimpleObject obj) {
        return obj != null && obj.visibleOnScreen() && obj.validateInteractable();
    }

    public static SimpleObject getNearest(String name) {
        return ClientContext.instance().objects.populate().filter(name).nearest().next();
    }

    public static SimpleObject getNearest(int id) {
        return ClientContext.instance().objects.populate().filter(id).nearest().next();
    }

    public static SimpleObject getNearest(int id, WorldPoint point) {
        return ClientContext.instance().objects.populate().filter(id).nearest(point).next();
    }

    public static SimpleObject getNearestWithinArea(String name, WorldArea area) {
        return ClientContext.instance().objects.populate().filter(name).filter(o -> area.containsPoint(o.getLocation())).nearest().next();
    }
}
