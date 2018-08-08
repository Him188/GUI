package moe.him188.gui.window.listener.action;

import cn.nukkit.Player;

import java.util.function.Consumer;

/**
 * @author Him188moe @ GUI Project
 */
@FunctionalInterface
public interface ClickListener extends Consumer<Player> {
    void accept(Player player);
}
