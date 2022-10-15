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

import static framework.World.BankType.BOOTH;

public enum Bank {
    DRAYNOR(BOOTH, Location.DRAYNOR, Areas.makeArea(3097, 3239, 3079, 3252));

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

    public boolean open()
    {
        if (isOpen())
            return true;

        SimpleObject bank = WorldObject.getNearestWithinArea(this.type.getName(), this.area);
        if (WorldObject.isValid(bank))
        {
            return bank.click(0) || bank.click(this.type.getAction());
        }

        if (!this.area.containsPoint(Player.getLocation())) {
            if (bank != null && !WorldObject.isValid(bank)) {
                Logger.log("Walking to bank");
                ClientContext.instance().pathing.step(bank.getLocation());
            } else {
                Logger.log("Teleporting to bank");
                Teleportation.teleport(this.teleport);
            }
        }

        return false;
    }

    public static boolean isOpen()
    {
        return ClientContext.instance().bank.bankOpen();
    }

    public static boolean depositAll()
    {
        ClientContext.instance().bank.depositInventory();
        return Inventory.isEmpty();
    }
}
