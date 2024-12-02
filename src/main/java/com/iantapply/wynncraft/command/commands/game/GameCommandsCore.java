package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.Wynncraft;

public class GameCommandsCore {

    /**
     * Stages all game related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new HelpCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new BuyCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new ClassCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new DailyCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new KillCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new DuelCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new FindCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new FixQuestsCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new FixStartCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new ForumCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new ReportCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new HubCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new TradeCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new ItemLockCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new SkipTutorialCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new StreamCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new SwitchCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new TotemsCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new ClaimItemBombCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new ClaimIngredientBombCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new RulesCommand());
    }
}
