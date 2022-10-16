package framework.Tasks;

import framework.Player.Skill;
import simple.hooks.filters.SimpleSkills;
import simple.robot.api.ClientContext;

public class RejuvenationBoxExactHealTask extends RejuvenationBoxHealTask{
    public RejuvenationBoxExactHealTask(ClientContext ctx, int threshold) {
        super(ctx, (int) Math.ceil(threshold / (double) Skill.getRealLvl(SimpleSkills.Skills.HITPOINTS) * 100));
    }
}
