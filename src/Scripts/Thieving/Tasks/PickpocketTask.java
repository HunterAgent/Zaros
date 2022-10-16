package Scripts.Thieving.Tasks;

import framework.Player.Inventory;
import framework.Player.Player;
import framework.World.Npc;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class PickpocketTask extends Task {

    private final String name;
    private final WorldArea area;
    private SimpleNpc target;

    public PickpocketTask(ClientContext ctx, String name, WorldArea area) {
        super(ctx);
        this.name = name;
        this.area = area;
    }

    @Override
    public void run() {
        if (Npc.isValid(target)) {
            // TODO: If we increase this to other NPCs, need to also disable attacking
            if (!target.click(0))
                target.click("Pickpocket");
        } else {
            ClientContext.instance().pathing.step(target.getLocation());
        }
    }

    @Override
    public String status() {
        return "Pickpocketing " + this.name;
    }

    @Override
    public boolean condition() {
        target = Npc.getNearestWithinArea(this.name, this.area);
        return target != null && !Inventory.isFull();
    }
}
