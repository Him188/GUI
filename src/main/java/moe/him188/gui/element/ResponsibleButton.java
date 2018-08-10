package moe.him188.gui.element;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import moe.him188.gui.window.listener.action.ClickListener;
import moe.him188.gui.window.listener.action.ClickListenerSimple;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 能监听点击事件的按钮, 注意这个按钮只能用于 {@link FormWindowSimple}
 *
 * @author Him188moe @ GUI Project
 */
public class ResponsibleButton extends ElementButton {
    private transient ClickListener listener = null;

    public ResponsibleButton(@NotNull String text) {
        super(Objects.requireNonNull(text));
    }

    public ResponsibleButton(@NotNull String text, @NotNull ClickListener onClicked) {
        this(text);
        setClickListener(onClicked);
    }

    public ResponsibleButton(@NotNull String text, @NotNull ClickListenerSimple onClicked) {
        this(text, (ClickListener) onClicked);
    }

    public ResponsibleButton(@NotNull String text, @NotNull ElementButtonImageData image) {
        super(Objects.requireNonNull(text), image);
    }

    public ResponsibleButton(@NotNull String text, @NotNull ElementButtonImageData image, @NotNull ClickListener onClicked) {
        this(text, image);
        setClickListener(onClicked);
    }

    public ResponsibleButton(@NotNull String text, @NotNull ElementButtonImageData image, @NotNull ClickListenerSimple onClicked) {
        this(text, image, (ClickListener) onClicked);
    }

    public ResponsibleButton setClickListener(@NotNull ClickListener listener) {
        Objects.requireNonNull(listener);
        this.listener = listener;
        return this;
    }

    public ResponsibleButton setClickListener(@NotNull ClickListenerSimple listener) {
        return this.setClickListener((ClickListener) listener);
    }

    public void callClicked(@NotNull Player player) {
        if (this.listener != null) {
            this.listener.accept(player);
        }
    }

    public ClickListener getClickListener() {
        return listener;
    }
}
