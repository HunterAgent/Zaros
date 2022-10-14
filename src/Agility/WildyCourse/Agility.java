package Agility.WildyCourse;

import framework.Tasks.TestingTask;
import simple.hooks.filters.SimpleSkills;
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

import static simple.hooks.filters.SimpleSkills.Skills.AGILITY;

@ScriptManifest(
        author = "HunterAgent",
        category = Category.AGILITY,
        description = "Complete 1-99 agility with ease and luck",
        discord = "HunterAgent#8066",
        name = "Lucky Agility",
        servers = {"Zaros"},
        version = "0.1")

public class Agility extends TaskScript implements InventoryChangeListener {

    private long startTime;
    private int startXp, totalMarks = 0;
    private List<Task> tasks = new ArrayList<>();
//    private Course course = Course.GNOME;
//    CourseTask courseTask = new CourseTask(ctx, course);
//    TravelTask travelTask = new TravelTask(ctx, course);
//    LootTask lootTask = new LootTask(ctx);
    private boolean isProgressive = true;

//    private void updateCourse(Course course) {
//        courseTask.setCourse(course);
//        travelTask.setCourse(course);
//    }


    @Override
    public void onChatMessage(ChatMessage chatMessage) {
        if (isProgressive && chatMessage.getMessage().contains("just advanced an agility level.")) {
//            updateCourse(Course.getMaxCourse(ctx.skills.level(AGILITY)));
        }

    }

    @Override
    public void onExecute() {
//        startTime = System.currentTimeMillis();
//        startXp = ctx.skills.experience(SimpleSkills.Skills.AGILITY);
//        Camera.setupCameraZoom();
        tasks.add(new TestingTask(ctx));
//        Tasks.init(ctx);
//        updateCourse(Course.getMaxCourse(ctx.skills.level(AGILITY)));
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
//        int gainedXp = Skill.getXP(AGILITY) - startXp;
//        ctx.log("Uptime: " + this.ctx.paint.formatTime(System.currentTimeMillis() - startTime));
//        ctx.log("Total XP: " + Utils.formatNumber(gainedXp));
//        ctx.log("Total marks: " + Utils.formatNumber(totalMarks));
    }

    @Override
    public void paint(Graphics graphics) {
//        int gainedXp = Skill.getXP(AGILITY) - startXp;
//        long uptime = System.currentTimeMillis() - startTime;
//        Graphics2D g = (Graphics2D) graphics;
//
//        g.drawString("Status: " + getScriptStatus(), 550, 400);
//        g.drawString("Total XP: " +
//                        Utils.formatNumber(gainedXp) + " / " +
//                        Utils.formatNumber(this.ctx.paint.valuePerHour(gainedXp, startTime)),
//                550, 420);
//        g.drawString("Total Marks: " +
//                        Utils.formatNumber(totalMarks) + " / " +
//                        Utils.formatNumber(this.ctx.paint.valuePerHour(totalMarks, startTime)),
//                550, 440);
//        g.drawString("Uptime: " + this.ctx.paint.formatTime(uptime), 550, 460);
    }

    @Override
    public void onChange(InventoryChangeEvent inventoryChangeEvent) {
        if (inventoryChangeEvent.getItemName().equals("Mark of grace") && inventoryChangeEvent.getNewQuantity() != 0) {
            totalMarks += inventoryChangeEvent.getQuantityChange();
        }
    }
}
