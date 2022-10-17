package Scripts.Minigames.Wintertodt;

import framework.Player.Inventory;
import framework.Player.Player;
import framework.World.Travel;
import framework.World.WorldObject;
import lombok.Getter;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import java.util.Arrays;
import java.util.concurrent.TransferQueue;
import java.util.stream.Collectors;


public class Brazier {
    public enum State {

        BROKEN(29313, "Hammer", "Fix"),
        UNLIT(29312, "Tinderbox", "Light"),
        LIT(29314, "Bruma kindling", "Feed");

        @Getter
        private final int id;
        @Getter
        private final String reqItemName, action;

        State (int id, String reqItemName, String action) {
            this.id = id;
            this.reqItemName = reqItemName;
            this.action = action;
        }

        public static State get(int id) {
            for (State s : State.values()) {
                if (s.id == id)
                    return s;
            }

            return null;
        }
    }

    public static SimpleObject getNearestWithinArea(WorldArea area) {
        return WorldObject.getNearestWithinArea(
                Arrays.stream(State.values()).map(State::getId).collect(Collectors.toList()),
                area);
    }

    public static boolean canHandle(State s) {
        return s != null && Inventory.contains(s.reqItemName);
    }

    public static boolean canHandleNearest(WorldArea area) {
        SimpleObject brazier = getNearestWithinArea(area);
        if (brazier == null)
            return false;

        return canHandle(State.get(brazier.getId()));
    }

    public static void handle(SimpleObject brazier) {
        if (brazier == null)
            return;

        State s = State.get(brazier.getId());
        if (!canHandle(s))
            return;

        if (WorldObject.isValid(brazier)) {
            brazier.click(0);
            if (!ClientContext.instance().sleepCondition(Player::isAnimating, 1000)) {
                brazier.click(s.getAction());
            }
        } else {
            Travel.travel(brazier);
        }
    }

    public static void handleNearestWithinArea(WorldArea area) {
        handle(getNearestWithinArea(area));
    }
}
