package Scripts.Mining;

import framework.Camera;
import framework.Player.Player;
import framework.Player.Skill;
import framework.World.WorldObject;
import lombok.Getter;
import simple.hooks.filters.SimpleSkills;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import java.util.Arrays;

public enum Rock {
    CLAY("Clay", 1, new int[]{1162, 1163}),
    COPPER("Copper ore", 1, new int[]{10943, 11161}),
    TIN("Tin", 1, new int[] {11360, 11361}),
    IRON("Iron", 10, new int[] {11364, 11365}),
    SILVER("Silver", 20, new int[] {11368, 11369}),
    COAL("Coal", 30, new int[] {11366, 11367}),
    GOLD("Gold", 40, new int[] {0}),
    GEM("Gem", 40, new int[] {11380, 11381}),
    MITHRIL("Mithril", 55, new int[] {11372, 11372}),
    ADAMANTITE("Adamantite", 70, new int[] {11374, 11375}),
    RUNITE("Runite", 85,new int[] {0} )
    ;

    @Getter
    private String name;
    @Getter
    private int reqLvl;
    @Getter
    private int[] rockId;

    Rock(String name, int reqLvl, int[] rockId) {
        this.name = name;
        this.reqLvl = reqLvl;
        this.rockId = rockId;
    }

    public SimpleObject getNearest(WorldArea limitArea) {
        SimpleObject rock = WorldObject.getNearestWithinArea(this.name, limitArea);
        if (!WorldObject.isValid(rock))
        {
            rock = WorldObject.getNearestWithinArea(this.rockId, limitArea);
        }

        return rock;
    }
}
