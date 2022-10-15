package framework.Player;

import net.runelite.api.VarPlayer;
import net.runelite.api.coords.WorldPoint;
import simple.robot.api.ClientContext;

public class Player {

    public static WorldPoint getLocation() {
        return ClientContext.instance().players.getLocal().getLocation();
    }

    public static boolean isPoisonedOrVenomed() {
        return ClientContext.instance().getClient().getVar(VarPlayer.IS_POISONED) >= 30;
    }

    public static boolean inCombat() {
        return ClientContext.instance().players.getLocal().inCombat();
    }

    public static int getHealth() {
        return ClientContext.instance().players.getLocal().getHealth();
    }

    public static boolean isRunning()
    {
        return ClientContext.instance().pathing.running();
    }

    public static int getRunEnergy()
    {
        return ClientContext.instance().pathing.energyLevel();
    }

    public static void toggleRun(boolean state)
    {
       ClientContext.instance().pathing.running(state);
    }
}
