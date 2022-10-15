package framework.Player;

import simple.hooks.filters.SimpleSkills.Skills;
import simple.robot.api.ClientContext;

public class Skill {
    public static int getXP(Skills skill) {
        return ClientContext.instance().skills.experience(skill);
    }

    public static int getLvl(Skills skill) {
        return ClientContext.instance().skills.level(skill);
    }

    public static int getRealLvl(Skills skill) {
        return ClientContext.instance().skills.realLevel(skill);
    }

    public static int getPercentage(Skills skill) {
        return (int) (((float) getLvl(skill) / getRealLvl(skill)) * 100);
    }

    public static int getGainedXP(Skills skills, int starting_xp)
    {
        return getXP(skills) - starting_xp;
    }

}
