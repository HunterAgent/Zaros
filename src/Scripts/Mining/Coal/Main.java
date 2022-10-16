package Scripts.Mining.Coal;

import Scripts.Mining.Rock;
import Scripts.Mining.Tasks.MineTask;
import framework.Camera;
import framework.Player.Player;
import framework.Player.Skill;
import framework.Tasks.*;
import framework.Utils.Logger;
import framework.Utils.Utils;
import framework.World.Areas;
import framework.World.Bank;
import framework.World.BankTask;
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

import static simple.hooks.filters.SimpleSkills.Skills.MINING;
import static simple.robot.utils.ScriptUtils.formatTime;


@ScriptManifest(
        author = "HunterAgent",
        category = Category.MINING,
        description = "Mine coal in the mining guild ease and luck",
        discord = "HunterAgent#8066",
        name = "Lucky Coal Miner",
        servers = {"Zaros"},
        version = "1.0")
public class Main extends TaskScript implements InventoryChangeListener {

    private long startTime;
    private int startXp, startLvl, coalMined;
    private final List<Task> tasks = new ArrayList<>();

    public void validateSkillLevel() {
        if (Skill.getRealLvl(MINING) < 60) {
            Logger.log("Stopping script, needed thieving level was not met");
            ctx.stopScript();
        }
    }

    public void validatePickaxe() {
        if (!Player.hasPickaxe()) {
            Logger.log("Stopping script, missing pickaxe");
            ctx.stopScript();
        }
    }

    public void validate() {
        validateSkillLevel();
//        validatePickaxe();
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {
    }

    @Override
    public void onExecute() {

        startTime = System.currentTimeMillis();
        startXp = Skill.getXP(MINING);
        startLvl = Skill.getLvl(MINING);

        Camera.setupDefaultCameraZoom();

        tasks.addAll(Arrays.asList(
                new AntiBanTask(ctx, 5),
                new BankTask(ctx, Bank.MINING_GUILD),
                new MineTask(ctx, Rock.COAL, Areas.makeArea(3029, 9713, 3014, 9727)),
//                new TeleportTask(ctx, Location.LLETYA, Areas.LLETYA),
                new RunTask(ctx, 30)
//                new PickpocketTask(ctx, "Goreu", Areas.LLETYA_GOREU, false),
//                new ShortTravelTask(ctx, Areas.LLETYA, Areas.LLETYA_GOREU)
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
        int gainedXp = Skill.getGainedXP(MINING, startXp);
        ctx.log("Uptime: " + formatTime(startTime, System.currentTimeMillis()));
        ctx.log("Total XP: " + Utils.formatNumber(gainedXp));
    }

    @Override
    public void paint(Graphics g) {
        int gainedXp = Skill.getGainedXP(MINING, startXp);
        g.setColor(new Color(30, 50, 70, 200));
        g.fillRoundRect(545, 360, 192, 105, 15, 15);
        g.setColor(Color.WHITE);
        g.drawString("Status: " + getScriptStatus(), 550, 380);
        g.drawString("Level: " +
                        Utils.formatNumber(Skill.getLvl(MINING)) + " (" +
                        Utils.formatNumber(Skill.getLvl(MINING) - startLvl) + ")",
                550, 400);
        g.drawString("XP: " +
                        Utils.formatNumber(gainedXp) + " (" +
                        Utils.formatNumber(this.ctx.paint.valuePerHour(gainedXp, startTime)) + ")",
                550, 420);
        g.drawString("Coal: " +
                        Utils.formatNumber(coalMined) + " (" +
                        Utils.formatNumber(this.ctx.paint.valuePerHour(coalMined, startTime)) + ")",
                550, 440);
        g.drawString("Uptime: " + formatTime(startTime, System.currentTimeMillis()), 550, 460);
    }

    @Override
    public void onChange(InventoryChangeEvent inventoryChangeEvent) {
        if (inventoryChangeEvent.getItemName().equals("Coal"))
            coalMined++;
    }
}
