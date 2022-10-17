package framework.World;

import framework.Player.Inventory;
import framework.Player.Player;
import framework.Teleportation.Location;
import framework.Teleportation.Teleportation;
import framework.Utils.Logger;
import lombok.Getter;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import static framework.World.BankType.*;

public enum Bank {
    DRAYNOR(BOOTH, Location.DRAYNOR, Areas.makeArea(3097, 3239, 3079, 3252)),
    LLETYA(DEPOSIT, Location.LLETYA, Areas.LLETYA_BANK);

    @Getter
    private final BankType type;
    @Getter
    private final Location teleport;
    @Getter
    private final WorldArea area;

    Bank(BankType type, Location teleport, WorldArea area)
    {
        this.type = type;
        this.teleport = teleport;
        this.area = area;
    }

    public boolean open() {
        if (isOpen())
            return true;

        SimpleObject bank = WorldObject.getNearestWithinArea(this.type.getName(), this.area);
        if (WorldObject.isValid(bank))
        {
            return bank.click(0) || bank.click(this.type.getAction()) || Travel.travel(bank);
        }

        if (!this.area.containsPoint(Player.getLocation())) {
            if (bank != null) {
                Logger.log("Walking to bank");
                Travel.travel(bank);
            } else if (Travel.reachable(area)) {
                Logger.log("Walking to bank area");
                Travel.travel(area);
            } else {
                Teleportation.teleport(this.teleport);
            }
        }

        return false;
    }

    public static boolean isOpen() {
        return ClientContext.instance().bank.bankOpen();
    }

    public static boolean depositAll() {
        ClientContext.instance().bank.depositInventory();
        return Inventory.isEmpty();
    }

    public static boolean close() {
        return ClientContext.instance().bank.closeBank();
    }
}
