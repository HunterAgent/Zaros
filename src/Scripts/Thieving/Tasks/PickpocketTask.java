package Scripts.Thieving.Tasks;

import framework.Player.Inventory;
import framework.Player.Player;
import framework.World.Npc;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class PickpocketTask extends Task {

    private final String name;
    private SimpleNpc target;

    public PickpocketTask(ClientContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    @Override
    public void run() {
        if (Npc.isValid(target)) {
            // TODO: If we increase this to other NPCs, need to also disable attacking
            if (!target.click(0))
                target.click("Pickpocket");
        }
    }

    @Override
    public String status() {
        return "Pickpocketing " + this.name;
    }

    @Override
    public boolean condition() {
        target = Npc.getNearest(this.name);
        return target != null && !Inventory.isFull();
    }
}
