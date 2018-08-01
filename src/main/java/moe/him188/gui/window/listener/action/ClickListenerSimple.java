package moe.him188.gui.window.listener.action;

import cn.nukkit.Player;

/**
 * @author Him188moe @ GUI Project
 */
@FunctionalInterface
public interface ClickListenerSimple extends ClickListener {
    void run();

    @Override
    default void accept(Player player) {

    }
}
