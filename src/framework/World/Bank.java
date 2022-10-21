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
    LLETYA(DEPOSIT, Location.LLETYA, Areas.LLETYA_BANK),
    MINING_GUILD(DEPOSIT, null, Areas.MINING_GUILD_BANK),
    WINTERTOD(CHEST, Location.WINTERTODT, Areas.WINTERTODT_LOBBY),
    EDGE(BOOTH, Location.EDGEVILLE, Areas.EDGEVILLE_AREA);

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
            return (bank.click(0) || bank.click(this.type.getAction()) || Travel.travel(bank)) &&
                    ClientContext.instance().sleepCondition(Bank::isOpen, 2000);
        }

        if (!this.area.containsPoint(Player.getLocation())) {
            if (bank != null && Travel.reachable(bank)) {
                Logger.log("Walking to bank");
                Travel.travel(bank);
            } else if (Travel.reachable(area) && Travel.distance(area) < 20) {
                Logger.log("Walking to bank area");
                Travel.travel(area);
            } else {
                Logger.log("Teleporting to bank");
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

    public static boolean depositAllExcept(String ... names) {
        ClientContext.instance().bank.depositAllExcept(names);
        return Inventory.onlyContains(names);
    }

    public static boolean withdraw(String name, int amount) {
       ClientContext.instance().bank.withdraw(name, amount);
       return Inventory.count(name) >= amount;
    }

    public static boolean close() {
        return ClientContext.instance().bank.closeBank();
    }
}
