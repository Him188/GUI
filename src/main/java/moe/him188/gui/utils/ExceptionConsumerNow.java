package moe.him188.gui.utils;

import cn.nukkit.Player;

import java.lang.reflect.Array;

/**
 * 收到每个异常立即处理
 *
 * @author Him188moe @ GUI Project
 */
public abstract class ExceptionConsumerNow<E extends Exception> implements ExceptionConsumer<E> {
    @SuppressWarnings("unchecked")
    @Override
    public void catchException(Player player, E exception) {
        this.accept(player, (E[]) Array.newInstance(exception.getClass().getComponentType(), 1));
    }
}
