package Scripts.Agility;

import framework.Player.Player;
import framework.Teleportation.Location;
import framework.World.Areas;
import javafx.util.Pair;
import lombok.Getter;
import net.runelite.api.coords.WorldPoint;
import simple.robot.utils.WorldArea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public enum Course {

    GNOME(1,
            Areas.makeArea(2469, 3440, 2489, 3414, 0),
            Location.AGILITY_GNOME_COUSE,
            new ArrayList<>(Arrays.asList(
            new Pair<>(Obstacle.LOG_BALANCE, Areas.makeArea(2471, 3437, 2477, 3432, 0)),
            new Pair<>(Obstacle.OBSTACLE_NET, Areas.makeArea(2471, 3431, 2477, 3424, 0)),
            new Pair<>(Obstacle.TREE_BRANCH_UP, Areas.makeArea(2470, 3425, 2477, 3421, 1)),
            new Pair<>(Obstacle.BALANCING_ROPE, Areas.makeArea(2471, 3422, 2482, 3417, 2)),
            new Pair<>(Obstacle.TREE_BRANCH_DOWN, Areas.makeArea(2481, 3422, 2489, 3417, 2)),
            new Pair<>(Obstacle.OBSTACLE_NET, Areas.makeArea(2481, 3417, 2489, 3427, 0)),
            new Pair<>(Obstacle.OBSTACLE_PIPE, Areas.makeArea(2481, 3426, 2489, 3434, 0))
            ))),
    DRYNOR(10,
            Areas.makeArea(3106, 3284, 3081, 3248, 0),
            Location.AGILITY_DRYNORE_ROODTOP,
            new ArrayList<>(Arrays.asList(
            new Pair<>(Obstacle.ROUGH_WALL, Areas.makeArea(3102, 3281, 3106, 3276, 0)),
            new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(3103, 3281, 3093, 3276, 3)),
            new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(3086, 3270, 3093, 3279, 3)),
            new Pair<>(Obstacle.NARROW_WALL, Areas.makeArea(3088, 3270, 3094, 3263, 3)),
            new Pair<>(Obstacle.JUMP_UP_WALL, Areas.makeArea(3086, 3263, 3091, 3256, 3)),
            new Pair<>(Obstacle.GAP, Areas.makeArea(3086, 3256, 3096, 3254, 3)),
            new Pair<>(Obstacle.CRATE, Areas.makeArea(3095, 3256, 3103, 3263, 3))
    ))),
    AL_KHARID(20,
            Areas.makeArea(3261, 3155, 3326, 3200, 0),
            Location.AGILITY_ALKHARID_ROOFTOP,
            new ArrayList<>(Arrays.asList(
            new Pair<>(Obstacle.ROUGH_WALL, Areas.makeArea(3270, 3197, 3277, 3195, 0)),
            new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(3270, 3193, 3276, 3177, 3)),
            new Pair<>(Obstacle.CABLE, Areas.makeArea(3262, 3179, 3283, 3158, 3)),
            new Pair<>(Obstacle.ZIP_LINE, Areas.makeArea(3282, 3168, 3313, 3159, 3)),
            new Pair<>(Obstacle.TROPICAL_TREE, Areas.makeArea(3313, 3159, 3319, 3172, 1)),
            new Pair<>(Obstacle.ROOF_TOP_BEAMS, Areas.makeArea(3313, 3172, 3319, 3180, 2)),
            new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(3319, 3177, 3308, 3189, 3)),
            new Pair<>(Obstacle.GAP, Areas.makeArea(3309, 3183, 3296, 3196, 3))
    ))),
    VARROCK(30,
            Areas.makeArea(3189, 3421, 3242, 3391, 0),
            Location.AGILITY_VARROCK_ROOFTOP,
            new ArrayList<>(Arrays.asList(
            new Pair<>(Obstacle.ROUGH_WALL, Areas.makeArea(3221, 3411, 3225, 3416, 0)),
            new Pair<>(Obstacle.CLOTHES_LINE, Areas.makeArea(3211, 3409, 3221, 3420, 3)),
            new Pair<>(Obstacle.GAP_LEAP, Areas.makeArea(3200, 3420, 3211, 3409, 3)),
            new Pair<>(Obstacle.BALANCE_WALL, Areas.makeArea(3200, 3420, 3190, 3407, 1)),
            new Pair<>(Obstacle.GAP_LEAP, Areas.makeArea(3190, 3407, 3200, 3400, 3)),
            new Pair<>(Obstacle.GAP_LEAP, Areas.makeArea(3190, 3401, 3210, 3394, 3)),
            new Pair<>(Obstacle.GAP_LEAP, Areas.makeArea(3211, 3392, 3236, 3404, 3)),
            new Pair<>(Obstacle.HURDLE_LEDGE, Areas.makeArea(3234, 3401, 3241, 3410, 3)),
            new Pair<>(Obstacle.EDGE, Areas.makeArea(3235, 3410, 3241, 3417, 3)),
            new Pair<>(Obstacle.HURDLE_LEDGE, Areas.makeArea(3234, 3401, 3241, 3410, 3))
    ))),
    CANIFIS(40,
            Areas.makeArea(3470, 3508, 3518, 3466, 0),
            Location.AGILITY_CANIFIS_ROOFTOP,
            new ArrayList<>(Arrays.asList(
            new Pair<>(Obstacle.TALL_TREE, Areas.makeArea(3504, 3491, 3509, 3484, 0)),
            new Pair<>(Obstacle.GAP, Areas.makeArea(3503, 3489, 3511, 3501, 2)),
            new Pair<>(Obstacle.GAP, Areas.makeArea(3505, 3501, 3494, 3508, 2)),
            new Pair<>(Obstacle.GAP, Areas.makeArea(3494, 3506, 3481, 3497, 2)),
            new Pair<>(Obstacle.GAP, Areas.makeArea(3481, 3501, 3473, 3489, 3)),
            new Pair<>(Obstacle.POLE_VAULT, Areas.makeArea(3475, 3489, 3487, 3477, 2)),
            new Pair<>(Obstacle.GAP, Areas.makeArea(3486, 3480, 3506, 3467, 3)),
            new Pair<>(Obstacle.GAP, Areas.makeArea(3506, 3473, 3517, 3484, 2))
    ))),
//    FALADOR(50,
//            Areas.makeArea(3006, 3329, 3054, 3369, 0),
//            Location.AGILITY_FALADOR,
//            new ArrayList<>(Arrays.asList(
//                new Pair<>(Obstacle.ROUGH_WALL, Areas.makeArea(3034, 3342, 3041, 3338, 0)),
//                new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(3034, 3341, 3042, 3344, 3)),
//                new Pair<>(Obstacle.HAND_HOLDS, Areas.makeArea(3042, 3340, 3053, 3355, 3)),
//                new Pair<>(Obstacle.GAP, Areas.makeArea(3046, 3354, 3052, 3360, 3)),
//                new Pair<>(Obstacle.GAP, Areas.makeArea(3049, 3360, 3042, 3368, 3)),
//                new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(3042, 3365, 3026, 3356, 3)),
//                new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(3030, 3357, 3022, 3351, 3)),
//                new Pair<>(Obstacle.GAP, Areas.makeArea(3022, 3356, 3008, 3350, 3)),
//                new Pair<>(Obstacle.LEDGE, Areas.makeArea(3022, 3350, 3015, 3344, 3)),
//                new Pair<>(Obstacle.LEDGE, Areas.makeArea(3015, 3347, 3008, 3343, 3)),
//                new Pair<>(Obstacle.LEDGE, Areas.makeArea(3014, 3343, 3008, 3334, 3)),
//                new Pair<>(Obstacle.LEDGE, Areas.makeArea(3012, 3334, 3020, 3330, 3))
//            ))),
    SEERS(60,
//            Areas.makeArea(2732, 3500, 2695, 3456, 0),
            new WorldArea(
                    new WorldPoint( 2731, 3500, 0),
                    new WorldPoint( 2690, 3500, 0),
                    new WorldPoint( 2690, 3453, 0),
                    new WorldPoint( 2703, 3453, 0),
                    new WorldPoint( 2703, 3468, 0),
                    new WorldPoint( 2731, 3468, 0)),
            Location.AGILITY_SEERS_ROOFTOP,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(Obstacle.CLIMB_WALL, Areas.makeArea(2731, 3490, 2726, 3484, 0)),
                    new Pair<>(Obstacle.GAP, Areas.makeArea(2731, 3489, 2716, 3499, 3)),
                    new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(2716, 3499, 2703, 3484, 2)),
                    new Pair<>(Obstacle.GAP, Areas.makeArea(2707, 3485, 2717, 3473, 2)),
                    new Pair<>(Obstacle.GAP, Areas.makeArea(2718, 3475, 2698, 3468, 3)),
                    new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(3042, 3365, 3026, 3356, 2)),
                    new Pair<>(Obstacle.EDGE_JUMP, Areas.makeArea(2696, 3468, 2705, 3457, 2))
            ))),
    RELLEKKA(80,
            Areas.makeArea(2664, 3681, 2612, 3647, 0),
            Location.AGILITY_RELLEKKA_ROOFTOP,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(Obstacle.ROUGH_WALL, Areas.makeArea(2623, 3676, 2629, 3678, 0)),
                    new Pair<>(Obstacle.GAP_LEAP, Areas.makeArea(2627, 3677, 2620, 3670, 3)),
                    new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(2614, 3670, 2624, 3656, 3)),
                    new Pair<>(Obstacle.GAP_LEAP, Areas.makeArea(2623, 3659, 2634, 3650, 3)),
                    new Pair<>(Obstacle.GAP_HURDLE, Areas.makeArea(2637, 3648, 2647, 3656, 3)),
                    new Pair<>(Obstacle.TIGHTROPE, Areas.makeArea(2642, 3656, 2653, 3665, 3)),
                    new Pair<>(Obstacle.PILE_OF_FISH, Areas.makeArea(2652, 3663, 2662, 3678, 3))
            ))),
    ARDY(90,
            Areas.makeArea(2677, 3294, 2651, 3320, 0),
            Location.AGILITY_ARDOUGNE_ROOFTOP,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(Obstacle.WOODEN_BEAMS, Areas.makeArea(2672, 3299, 2676, 3296, 0)),
                    new Pair<>(Obstacle.GAP, Areas.makeArea(2670, 3298, 2672, 3312, 3)),
                    new Pair<>(Obstacle.PLANK, Areas.makeArea(2666, 3319, 2659, 3317, 3)),
                    new Pair<>(Obstacle.GAP, Areas.makeArea(2659, 3319, 2652, 3315, 3)),
                    new Pair<>(Obstacle.GAP, Areas.makeArea(2652, 3308, 2654, 3315, 3)),
                    new Pair<>(Obstacle.STEEP_ROOF, Areas.makeArea(2650, 3310, 2655, 3299, 3)),
                    new Pair<>(Obstacle.GAP, Areas.makeArea(2655, 3300, 2659, 3296, 3))
            )));

    @Getter
    private WorldArea area;
    @Getter
    private Location loc;
    @Getter
    private ArrayList<Pair<Obstacle, WorldArea>> obstacles;
    @Getter
    private int reqLvl;

    Course(int reqLvl, WorldArea area, Location loc, ArrayList<Pair<Obstacle, WorldArea>> obstacles) {
        this.reqLvl = reqLvl;
        this.area = area;
        this.loc = loc;
        this.obstacles = obstacles;
    }

    public boolean inCourse(WorldPoint point) {
        for (Pair<Obstacle, WorldArea> obstacle: this.getObstacles()) {
           if (obstacle.getValue().containsPoint(point)) {
               return true;
           }
        }
        return false;
    }

    private Pair<Obstacle, WorldArea> getNextObstacle() {
        for (Pair<Obstacle, WorldArea> obstacle: this.getObstacles()) {
            if (obstacle.getValue().containsPoint(Player.getLocation())) {
                return obstacle;
            }
        }
        return null;
    }

    public boolean handleNextObstacle() {
        Pair<Obstacle, WorldArea> ob = getNextObstacle();
        if (ob == null) return false;
        return ob.getKey().handle(ob.getValue());
    }

    static public Course getMaxCourse(int currentLvl) {
        return Arrays.stream(Course.values()).filter(c -> c.reqLvl <= currentLvl).max(Comparator.comparing(Course::getReqLvl)).orElse(GNOME);
    }
}
