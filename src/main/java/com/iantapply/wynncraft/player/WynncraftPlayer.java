package com.iantapply.wynncraft.player;

import com.iantapply.wynncraft.database.model.PlayerModel;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * A class to interact with a player based on their stored Wynncraft player data model
 */
@Getter @Setter
public class WynncraftPlayer {
    private PlayerModel playerModel;
    private final Player player;

    public WynncraftPlayer(Player player, PlayerModel playerModel) {
        this.player = player;
        this.playerModel = playerModel;
    }

    public void initialize() {
        if (this.player == null) {
            Logger.log(LoggingLevel.ERROR, "Player is null, cannot initialize Wynncraft player instance");
            return;
        }

        /* Set chat name and tablist name */
        String name = (this.playerModel.getNickname() != null) ? this.playerModel.getNickname() : this.playerModel.getUsername();
        this.player.displayName(Component.text("[" + this.playerModel.getServer() + "] " +  name)); // Changes chat + tab list name

        /* Set gamemode and prep player object */
        this.player.setGameMode(GameMode.ADVENTURE);

        // Start sending proper actionbar
        // TODO: Send properly formatted unicode characters
//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                //󐀜󏾼󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏾦󏾜󏿂󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󐀠󏾘󏿿󏾘󐀭󐀂󐀂󏾭󏾨󐀂󐀂󐀨󏿴󏿳󏿺󏿾󏿺󏾘󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󏿿󐀊
//                //󏿿
//                //󐀉󏾩󏾘
//                player.sendActionBar(Component.text("\uDB00\uDC1C\uE089\uDAFF\uDFBC\uE080\uDAFF\uDFFF\uE081\uDAFF\uDFFF\uE081\uDAFF\uDFFF\uE081\uDAFF\uDFFF\uE081\uDAFF\uDFFF\uE081\uDAFF\uDFFF\uE081\uDAFF\uDFFF\uE081\uDAFF\uDFFF\uE081\uDAFF\uDFFF\uE082\uDAFF\uDFA6\uDAFF\uDF9C\uE029\uDAFF\uDFC2\uE020\uDAFF\uDFFF\uE021\uDAFF\uDFFF\uE021\uDAFF\uDFFF\uE021\uDAFF\uDFFF\uE021\uDAFF\uDFFF\uE021\uDAFF\uDFFF\uE021\uDAFF\uDFFF\uE021\uDAFF\uDFFF\uE021\uDAFF\uDFFF\uE022\uDB00\uDC20\uDAFF\uDF98\uE00A\uDAFF\uDFFF\uDAFF\uDF98\uDB00\uDC2D\uE011\uE014\uE015\uDB00\uDC02\uE01F\uDB00\uDC02\uE011\uE014\uE015\uDAFF\uDFAD\uDAFF\uDFA8\uE011\uE014\uE016\uE016\uDB00\uDC02\uE01F\uDB00\uDC02\uE011\uE014\uE016\uE016\uDB00\uDC28\uDAFF\uDFF4\uE0AA\uDAFF\uDFF3\uDAFF\uDFFA\uE005\uDAFF\uDFFE\uE003\uDAFF\uDFFA\uDAFF\uDF98\u0001\uE060\uDAFF\uDFFF\uE061\uDAFF\uDFFF\uE027\uDAFF\uDFFF\uE029\uDAFF\uDFFF\uE028\uDAFF\uDFFF\uE061\uDAFF\uDFFF\uE012\uDAFF\uDFFF\uE004\uDAFF\uDFFF\uE061\uDAFF\uDFFF\uE063\uDAFF\uDFFF\uE021\uDAFF\uDFFF\uE025\uDAFF\uDFFF\uE027\uDAFF\uDFFF\uE026\uDAFF\uDFFF\uE061\uDAFF\uDFFF\uE064\uDB00\uDC0A").font(Key.key("minecraft:hud/gameplay/default/bottom_middle")));
//            }
//        }.runTaskTimer(Wynncraft.getInstance(), 0, 40);
    }
}
