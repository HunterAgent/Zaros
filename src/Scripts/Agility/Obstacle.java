package Scripts.Agility;

import framework.World.WorldObject;
import lombok.Getter;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import java.util.Arrays;

public enum Obstacle {

    LOG_BALANCE("Log balance", "Walk-across", 23145),
    OBSTACLE_NET("Obstacle net", "Climb-over", 23134, 23135),
    TREE_BRANCH_UP("Tree branch", "Climb", 23559),
    BALANCING_ROPE("Balancing rope", "Walk-on", 23557),
    TREE_BRANCH_DOWN("Tree branch", "Climb-down", 23560),
    OBSTACLE_PIPE("Obstacle pipe", "Squeeze-through", 23138),

    ROUGH_WALL("Rough wall", "Climb", 11404, 11633, 14412, 14946),
    TIGHTROPE("Tightrope", "Cross", 11405, 11406, 14398, 14409, 14932, 14987, 14992),
    NARROW_WALL("Narrow wall", "Balance", 11430),
    JUMP_UP_WALL("Wall", "Jump-up", 11630),
    GAP("Gap", "Jump", 11631, 14399, 14928, 14929, 14930, 14844, 14845, 14846, 14847, 14848, 14897),
    CRATE("Crate", "Climb-down", 11632),

    CABLE("Cable", "Swing-across", 14402),
    ZIP_LINE("Zip line", "Teeth-grip", 14403),
    TROPICAL_TREE("Tropical tree", "Swing-across", 14404),
    ROOF_TOP_BEAMS("Roof top beams", "Climb", 11634),

    CLOTHES_LINE("Clothes line", "Cross", 14413),
    GAP_LEAP("Gap", "Leap", 14414, 14833, 14834, 14835, 14947, 14990),
    BALANCE_WALL("Wall", "Balance", 14832),
    HURDLE_LEDGE("Ledge", "Hurdle", 14836),
    EDGE("Edge", "Jump-off", 14841),

    TALL_TREE("Tall tree", "Climb", 14843),
    POLE_VAULT("Pole-vault", "Vault", 14894),

    HAND_HOLDS("Hand holds", "Cross"),
    LEDGE("Ledge", "Jump"),

    CLIMB_WALL("Wall", "Climb-up", 14927),
    EDGE_JUMP("Edge", "Jump", 14931),

    GAP_HURDLE("Gap", "Hurdle", 14991),
    PILE_OF_FISH("Pile of fish", "Jump-in", 14994),

    WOODEN_BEAMS("Wooden beams", "Climb-up"),
    PLANK("Plank", "Walk-on"),
    STEEP_ROOF("Steep roof", "Balance-across")
    ;

    @Getter
    private final String name, action;
    @Getter
    private final int[] ids;

    Obstacle(String name, String action, int ... ids) {
        this.name = name;
        this.action = action;
        this.ids = ids;
    }

    public boolean handle(WorldArea area){
        SimpleObject obstacleObj = WorldObject.getNearestWithinArea(this.getName(), area);
        if (!WorldObject.isValid(obstacleObj))
        {
            ClientContext.instance().log("Failed to name locate obstacle: " + this.getName());
            obstacleObj = WorldObject.getNearestWithinArea(this.getIds(), area);
        }

        if (!WorldObject.isValid(obstacleObj)) {
            ClientContext.instance().log("Failed to id locate obstacle: " + Arrays.toString(this.getIds()));
            obstacleObj = WorldObject.getNearest(this.getIds());
        }

        if (!WorldObject.isValid(obstacleObj)) {
            ClientContext.instance().pathing.step(area.randomTile().dz(ClientContext.instance().pathing.plane()));
            return false;
        }

        return obstacleObj.click(0) || obstacleObj.click(this.getAction());
    }
}
