package Scripts.Thieving.Tasks;

import framework.Player.Inventory;
import framework.Player.Player;
import framework.World.Npc;
import framework.World.Travel;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class PickpocketTask extends Task {

    private final String name;
    private final WorldArea area;
    private final boolean leftClick;
    private SimpleNpc target;

    public PickpocketTask(ClientContext ctx, String name, WorldArea area, Boolean leftClick) {
        super(ctx);
        this.name = name;
        this.area = area;
        this.leftClick = leftClick;
    }

    @Override
    public void run() {
        if (Npc.isValid(target)) {
            if (leftClick) {
                if (!target.click(0)) {
                    target.click("Pickpocket");
                }
            }
            else {
                target.click("Pickpocket");
            }
        } else {
            Travel.travel(target);
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
