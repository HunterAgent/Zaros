package Scripts.Thieving.Elfs;

import Scripts.Thieving.Tasks.BankTask;
import Scripts.Thieving.Tasks.CoinPouchTask;
import Scripts.Thieving.Tasks.EquipRogueTask;
import Scripts.Thieving.Tasks.PickpocketTask;
import framework.Camera;
import framework.Player.Skill;
import framework.Tasks.*;
import framework.Teleportation.Location;
import framework.Utils.Logger;
import framework.Utils.Utils;
import framework.World.Areas;
import framework.World.Bank;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static simple.hooks.filters.SimpleSkills.Skills.THIEVING;
import static simple.robot.utils.ScriptUtils.formatTime;


@ScriptManifest(
        author = "HunterAgent",
        category = Category.THIEVING,
        description = "Pickpocket Goreu the Elf with ease and luck",
        discord = "HunterAgent#8066",
        name = "Lucky Elf Pickpocket",
        servers = {"Zaros"},
        version = "1.0")
public class Main extends TaskScript {

    private long startTime;
    private int startXp, startLvl;
    private final List<Task> tasks = new ArrayList<>();

    public void validateSkillLevel()
    {
        if (Skill.getRealLvl(THIEVING) < 85)
        {
            Logger.log("Stopping script, needed thieving level was not met");
            ctx.stopScript();
        }
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {
    }

    @Override
    public void onExecute() {
        validateSkillLevel();

        startTime = System.currentTimeMillis();
        startXp = Skill.getXP(THIEVING);
        startLvl = Skill.getLvl(THIEVING);

        Camera.setupDefaultCameraZoom();

        tasks.addAll(Arrays.asList(
                new AntiBanTask(ctx, 5),
                new RejuvenationBoxExactHealTask(ctx, 6),
                new CoinPouchTask(ctx),
                new BankTask(ctx, Bank.LLETYA),
                new EquipRogueTask(ctx),
                new TeleportTask(ctx, Location.LLETYA, Areas.LLETYA),
                new RunTask(ctx, 30),
                new PickpocketTask(ctx, "Goreu", Areas.LLETYA_GOREU, false),
                new ShortTravelTask(ctx, Areas.LLETYA, Areas.LLETYA_GOREU)
        ));
    }

    @Override
    public boolean prioritizeTasks() {
        return true;
    }

    @Override
    public List<Task> tasks() {
        return tasks;
    }

    @Override
    public void onTerminate() {
        int gainedXp = Skill.getGainedXP(THIEVING, startXp);
        ctx.log("Uptime: " + formatTime(startTime, System.currentTimeMillis()));
        ctx.log("Total XP: " + Utils.formatNumber(gainedXp));
    }

    @Override
    public void paint(Graphics g) {
        int gainedXp = Skill.getGainedXP(THIEVING, startXp);
        g.setColor(new Color(30, 50, 70, 200));
        g.fillRoundRect(545, 380, 192, 85, 15, 15);
        g.setColor(Color.WHITE);
        g.drawString("Status: " + getScriptStatus(), 550, 400);
        g.drawString("Level: " +
                        Utils.formatNumber(Skill.getLvl(THIEVING)) + " (" +
                        Utils.formatNumber(Skill.getLvl(THIEVING) - startLvl) + ")",
                550, 420);
        g.drawString("XP: " +
                        Utils.formatNumber(gainedXp) + " (" +
                        Utils.formatNumber(this.ctx.paint.valuePerHour(gainedXp, startTime)) + ")",
                550, 440);
        g.drawString("Uptime: " + formatTime(startTime, System.currentTimeMillis()), 550, 460);
    }
}
