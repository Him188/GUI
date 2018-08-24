package moe.him188.gui.utils;

import cn.nukkit.Player;

/**
 * 忽略所有异常
 *
 * @author Him188moe @ GUI Project
 */
public class ExceptionConsumerIgnore<E extends Exception> implements ExceptionConsumer<E> {
    @Override
    public void catchException(Player player, E exception) {

    }

    @Override
    public void accept(Player player, E[] exceptions) {

    }
}
