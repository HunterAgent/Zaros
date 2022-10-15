package Scripts.Thieving.Tasks;

import framework.Player.Equipment;
import framework.Player.Inventory;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class EquipRogueTask extends Task {

    private SimpleItem rogue_piece = null;

    public EquipRogueTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        rogue_piece.click(0);
    }

    @Override
    public String status() {
        return "Equipping Rouge set";
    }

    @Override
    public boolean condition() {
        rogue_piece = Inventory.getItem("Rogue");
        return rogue_piece != null && !Equipment.contains(rogue_piece.getId());
    }
}
