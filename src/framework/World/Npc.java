package framework.World;

import framework.Camera;
import framework.Utils.Logger;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import java.util.function.Predicate;

public class Npc {
    public static SimpleNpc getNearest(String ... name) {
        return ClientContext.instance().npcs.populate().filter(name).nearest().next();
    }

    public static SimpleNpc getNearest(int ... id) {
        return ClientContext.instance().npcs.populate().filter(id).nearest().next();
    }

    public static SimpleNpc getMonster(String ... name) {
        return ClientContext.instance().npcs.populate().filter(name).filter(npc -> npc != null && !npc.isDead()).nearest().next();
    }

    public static SimpleNpc getMonster(int ... id) {
        return ClientContext.instance().npcs.populate().filter(id).filter(npc -> npc != null && !npc.isDead()).nearest().next();
    }

    public static SimpleNpc getMonster(Predicate<SimpleNpc> npcFilter, int ... id) {
        return ClientContext.instance().npcs.populate().filter(id).filter(npc -> npc != null && !npc.isDead()).filter(npcFilter).nearest().next();
    }

    public static boolean validate(SimpleNpc npc) {
        if (npc == null) return false;

        Logger.log("NPC is not null");
        if (!npc.visibleOnScreen())
        {
            Logger.log("NPC is not visible");
            Camera.turnTo(npc);
            Logger.log("Turning camera");
            Travel.travel(npc);
            Logger.log("Traveling");
        }

        return npc.visibleOnScreen();
    }

    public static SimpleNpc getNearestWithinArea(String name, WorldArea area) {
        return ClientContext.instance().npcs.populate().filter(name).filter(npc -> area.containsPoint(npc.getLocation())).nearest().next();
    }
}
