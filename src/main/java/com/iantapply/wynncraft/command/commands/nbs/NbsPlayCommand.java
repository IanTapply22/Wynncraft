package com.iantapply.wynncraft.command.commands.nbs;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.command.WynncraftCommand;
import com.iantapply.wynncraft.nbs.handling.NBSFormatDecoder;
import com.iantapply.wynncraft.nbs.handling.NBSSong;
import com.iantapply.wynncraft.nbs.players.PlayerOrientedSongPlayer;
import org.bukkit.SoundCategory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.InputStream;

public class NbsPlayCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "play";
    }

    @Override
    public String syntax() {
        return "play <file name>";
    }

    @Override
    public String description() {
        return "Plays an NBS file from the internal resources folder.";
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        try {
            Player player = (Player) sender;
            InputStream inputStreamSong = getClass().getClassLoader().getResourceAsStream("songs/" + args[0] + ".nbs");
            NBSSong song = NBSFormatDecoder.parse(inputStreamSong);
            NbsCore.player = new PlayerOrientedSongPlayer(Wynncraft.getInstance().getNbsCore(), song);
            NbsCore.player.setFadeStart((byte) 10);
            NbsCore.player.setPlayer(player);
            NbsCore.player.setSoundCategory(SoundCategory.RECORDS);

            NbsCore.player.addPlayer(player);
            NbsCore.player.setPlaying(true);
            sender.sendMessage("Started playing NBS song: " + args[0]);
        } catch (Exception e) {
            sender.sendMessage("NBS play command failed with exception: " + e.getMessage());
        }
    }
}
