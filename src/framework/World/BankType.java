package framework.World;

import lombok.Getter;

public enum BankType {
    BOOTH("Bank booth", "Bank"),
    DEPOSIT("Bank Deposit Box", "Deposit");

    @Getter
    private final String name, action;

    BankType(String name, String action)
    {
        this.name = name;
        this.action = action;
    }
}
