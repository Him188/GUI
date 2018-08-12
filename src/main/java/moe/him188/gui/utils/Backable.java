package moe.him188.gui.utils;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindow;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * 标记这个表单支持返回上一级.
 *
 * @author Him188moe @ GUI Project
 */
public interface Backable {

    void setParent(@Nullable FormWindow window);

    FormWindow getParent();

    default void goBack(Player player) throws NoParentWindowFoundException {
        Objects.requireNonNull(player);
        if (getParent() == null) {
            throw new NoParentWindowFoundException();
        }
        player.showFormWindow(this.getParent());
    }
}
