package Scripts.Minigames.Wintertod;

import Scripts.Minigames.Wintertod.Tasks.RestockTask;
import framework.Camera;
import framework.Player.Skill;
import framework.Tasks.AntiBanTask;
import framework.Tasks.RunTask;
import framework.Utils.Logger;
import framework.Utils.Utils;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.listeners.InventoryChangeEvent;
import simple.hooks.scripts.listeners.InventoryChangeListener;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static simple.hooks.filters.SimpleSkills.Skills.FIREMAKING;
import static simple.robot.utils.ScriptUtils.formatTime;


@ScriptManifest(
        author = "HunterAgent",
        category = Category.MINIGAMES,
        description = "Plays Wintertod with ease and luck",
        discord = "HunterAgent#8066",
        name = "Lucky Wintertod",
        servers = {"Zaros"},
        version = "0.1")
public class Main extends TaskScript implements InventoryChangeListener {

    private long startTime;
    private int startXp, startLvl, coalMined;
    private final List<Task> tasks = new ArrayList<>();

    public void validateSkillLevel() {
        if (Skill.getRealLvl(FIREMAKING) < 50) {
            Logger.log("Stopping script, needed firemaking level was not met");
            ctx.stopScript();
        }
    }

    public void validate() {
        validateSkillLevel();
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {
    }

    @Override
    public void onExecute() {

        startTime = System.currentTimeMillis();
        startXp = Skill.getXP(FIREMAKING);
        startLvl = Skill.getLvl(FIREMAKING);

        Camera.setupDefaultCameraZoom();

        tasks.addAll(Arrays.asList(
                new AntiBanTask(ctx, 5),
                new RunTask(ctx, 30),
                new RestockTask(ctx)
        ));

        validate();
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
        int gainedXp = Skill.getGainedXP(FIREMAKING, startXp);
        ctx.log("Uptime: " + formatTime(startTime, System.currentTimeMillis()));
        ctx.log("Total XP: " + Utils.formatNumber(gainedXp));
    }

    @Override
    public void paint(Graphics g) {
        int gainedXp = Skill.getGainedXP(FIREMAKING, startXp);
        g.setColor(new Color(30, 50, 70, 200));
        g.fillRoundRect(545, 360, 192, 105, 15, 15);
        g.setColor(Color.WHITE);
        g.drawString("Status: " + getScriptStatus(), 550, 380);
        g.drawString("Level: " +
                        Utils.formatNumber(Skill.getLvl(FIREMAKING)) + " (" +
                        Utils.formatNumber(Skill.getLvl(FIREMAKING) - startLvl) + ")",
                550, 400);
        g.drawString("XP: " +
                        Utils.formatNumber(gainedXp) + " (" +
                        Utils.formatNumber(this.ctx.paint.valuePerHour(gainedXp, startTime)) + ")",
                550, 420);
        g.drawString("Crates: " +
                        Utils.formatNumber(coalMined) + " (" +
                        Utils.formatNumber(this.ctx.paint.valuePerHour(coalMined, startTime)) + ")",
                550, 440);
        g.drawString("Uptime: " + formatTime(startTime, System.currentTimeMillis()), 550, 460);
    }

    @Override
    public void onChange(InventoryChangeEvent inventoryChangeEvent) {
        if (inventoryChangeEvent.getItemName().equals("Crate"))
            coalMined++;
    }
}
