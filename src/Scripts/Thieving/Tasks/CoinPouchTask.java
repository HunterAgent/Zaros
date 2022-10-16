package Scripts.Thieving.Tasks;

import framework.Player.Inventory;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class CoinPouchTask extends Task {

    private SimpleItem pouch;

    public CoinPouchTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public void run() {
        pouch.click(0);
    }

    @Override
    public String status() {
        return "Opening coin pouches";
    }

    @Override
    public boolean condition() {
        pouch = Inventory.getItem("Coin Pouch");
        return pouch != null && (pouch.getQuantity() == 28 || Inventory.isFull());
    }
}
