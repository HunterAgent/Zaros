package Scripts.Agility;

import framework.World.WorldObject;
import lombok.Getter;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public enum Obstacle {

    LOG_BALANCE("Log balance", "Walk-across"),
    OBSTACLE_NET("Obstacle net", "Climb-over"),
    TREE_BRANCH_UP("Tree branch", "Climb"),
    BALANCING_ROPE("Balancing rope", "Walk-on"),
    TREE_BRANCH_DOWN("Tree branch", "Climb-down"),
    OBSTACLE_PIPE("Obstacle pipe", "Squeeze-through"),

    ROUGH_WALL("Rough wall", "Climb"),
    TIGHTROPE("Tightrope", "Cross"),
    NARROW_WALL("Narrow wall", "Balance"),
    JUMP_UP_WALL("Wall", "Jump-up"),
    GAP("Gap", "Jump"),
    CRATE("Crate", "Climb-down"),

    CABLE("Cable", "Swing-across"),
    ZIP_LINE("Zip line", "Teeth-grip"),
    TROPICAL_TREE("Tropical tree", "Swing-across"),
    ROOF_TOP_BEAMS("Roof top beams", "Climb"),

    CLOTHES_LINE("Clothes line", "Cross"),
    GAP_LEAP("Gap", "Leap"),
    BALANCE_WALL("Wall", "Balance"),
    HURDLE_LEDGE("Ledge", "Hurdle"),
    EDGE("Edge", "Jump-off"),

    TALL_TREE("Tall tree", "Climb"),
    POLE_VAULT("Pole-vault", "Vault"),

    HAND_HOLDS("Hand holds", "Cross"),
    LEDGE("Ledge", "Jump"),

    CLIMB_WALL("Wall", "Climb-up"),
    EDGE_JUMP("Edge", "Jump"),

    GAP_HURDLE("Gap", "Hurdle"),
    PILE_OF_FISH("Pile of fish", "Jump-in"),

    WOODEN_BEAMS("Wooden beams", "Climb-up"),
    PLANK("Plank", "Walk-on"),
    STEEP_ROOF("Steep roof", "Balance-across")
    ;

    @Getter
    private String name, action;

    Obstacle(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public boolean handle(WorldArea area){
        SimpleObject obstacleObj = WorldObject.getNearestWithinArea(this.getName(), area);
        if (!WorldObject.isValid(obstacleObj) || !obstacleObj.click(this.getAction())) {
            ClientContext.instance().pathing.step(area.randomTile().dz(ClientContext.instance().pathing.plane()));
            return false;
        }
        return true;

    }

}
