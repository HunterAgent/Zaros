package framework.Tasks;

import framework.Player.Player;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class RunTask extends Task {

    private final int threshold;
    public RunTask(ClientContext ctx, int threshold) {
        super(ctx);
        this.threshold = threshold;
    }

    @Override
    public void run() {
        Player.toggleRun(true);
    }

    @Override
    public String status() {
        return "Enabling running";
    }

    @Override
    public boolean condition() {
        return !Player.isRunning() && Player.getRunEnergy() >= this.threshold;
    }
}
