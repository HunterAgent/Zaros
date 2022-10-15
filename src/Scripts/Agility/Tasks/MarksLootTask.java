package Scripts.Agility.Tasks;

import framework.World.GroundItem;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.robot.api.ClientContext;

public class MarksLootTask extends Task {
    private SimpleGroundItem mark;

    public MarksLootTask(ClientContext ctx) {
        super(ctx);
        this.mark = null;
    }

    @Override
    public void run() {
        mark.click("Take");
    }

    @Override
    public String status() {
        return "Looting Mark of grace";
    }

    @Override
    public boolean condition() {
        mark = GroundItem.getNearest("Mark of grace");
        return GroundItem.isValid(mark);
    }
}
