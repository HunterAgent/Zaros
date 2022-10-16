package Scripts.Agility.AllInOne;

import Scripts.Agility.Course;
import Scripts.Agility.Tasks.CourseTask;
import Scripts.Agility.Tasks.CourseTravelTask;
import Scripts.Agility.Tasks.MarksLootTask;
import framework.Camera;
import framework.Player.Skill;
import framework.Tasks.RunTask;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static simple.hooks.filters.SimpleSkills.Skills.AGILITY;
import static simple.robot.utils.ScriptUtils.formatTime;

@ScriptManifest(
        author = "HunterAgent",
        category = Category.AGILITY,
        description = "Complete 1-99 agility with ease and luck",
        discord = "HunterAgent#8066",
        name = "Lucky Agility",
        servers = {"Zaros"},
        version = "2.1")
public class Main extends TaskScript implements InventoryChangeListener {

    private long startTime;
    private int startXp, totalMarks = 0;
    private final List<Task> tasks = new ArrayList<>();
    private final Course course = Course.GNOME;
    CourseTask courseTask = new CourseTask(ctx, course);
    CourseTravelTask travelTask = new CourseTravelTask(ctx, course);
    MarksLootTask lootTask = new MarksLootTask(ctx);
    private boolean isProgressive = true;

    private void updateCourse(Course course) {
        ctx.log("Updating to new course: " + course.name());
        courseTask.setCourse(course);
        travelTask.setCourse(course);
    }


    @Override
    public void onChatMessage(ChatMessage chatMessage) {
        if (isProgressive && chatMessage.getMessage().contains("agility level")) {
            // Needed because the Skill level updates only after
            String message = chatMessage.getMessage().replace(".", "");
            int lvl = Integer.parseInt(message.substring(message.lastIndexOf(" ") + 1));
            updateCourse(Course.getMaxCourse(lvl));
        }

    }

    @Override
    public void onExecute() {
        startTime = System.currentTimeMillis();
        startXp = Skill.getXP(AGILITY);

        Camera.setupDefaultCameraZoom();
        updateCourse(Course.getMaxCourse(Skill.getLvl(AGILITY)));

        tasks.addAll(Arrays.asList(lootTask, new RunTask(ctx, 30), courseTask, travelTask));
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
        int gainedXp = Skill.getGainedXP(AGILITY, startXp);
        ctx.log("Uptime: " + formatTime(startTime, System.currentTimeMillis()));
        ctx.log("Total XP: " + Utils.formatNumber(gainedXp));
        ctx.log("Total marks: " + Utils.formatNumber(totalMarks));
    }

    @Override
    public void paint(Graphics g) {
        int gainedXp = Skill.getGainedXP(AGILITY, startXp);
        g.setColor(new Color(30, 50, 70, 200));
        g.fillRoundRect(545, 380, 192, 85, 15, 15);
        g.setColor(Color.WHITE);
        g.drawString("Status: "  + getScriptStatus(), 550, 400);
        g.drawString("XP: " +
                        Utils.formatNumber(gainedXp) + " (" +
                        Utils.formatNumber(this.ctx.paint.valuePerHour(gainedXp, startTime)) + ")",
                550, 420);
        g.drawString("MOG: " +
                        Utils.formatNumber(totalMarks) + " (" +
                        Utils.formatNumber(this.ctx.paint.valuePerHour(totalMarks, startTime)) + ")",
                550, 440);
        g.drawString("Uptime: " + formatTime(startTime, System.currentTimeMillis()), 550, 460);
    }

    @Override
    public void onChange(InventoryChangeEvent inventoryChangeEvent) {
        if (inventoryChangeEvent.getItemName().equals("Mark of grace") && inventoryChangeEvent.getNewQuantity() != 0) {
            totalMarks += inventoryChangeEvent.getQuantityChange();
        }
    }
}
