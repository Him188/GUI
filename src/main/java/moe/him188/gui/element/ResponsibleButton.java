package moe.him188.gui.element;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import com.google.gson.annotations.Expose;
import moe.him188.gui.window.listener.action.ClickListener;

import java.util.Objects;

/**
 * 能监听点击事件的按钮, 注意这个按钮只能用于 {@link FormWindowSimple}
 *
 * @author Him188moe @ ProjectARK Project
 */
public class ResponsibleButton extends ElementButton {
    @Expose(serialize = false, deserialize = false)
    private ClickListener listener = null;

    public ResponsibleButton(String text) {
        super(Objects.requireNonNull(text));
    }

    public ResponsibleButton(String text, ClickListener onClicked) {
        this(text);
        setClickListener(onClicked);
    }

    public ResponsibleButton(String text, ElementButtonImageData image) {
        super(Objects.requireNonNull(text), image);
    }

    public ResponsibleButton(String text, ElementButtonImageData image, ClickListener onClicked) {
        this(text, image);
        setClickListener(onClicked);
    }

    public ResponsibleButton setClickListener(ClickListener listener) {
        this.listener = listener;
        return this;
    }

    public void callClicked(Player player) {
        if (this.listener != null) {
            this.listener.accept(player);
        }
    }

    public ClickListener getClickListener() {
        return listener;
    }
}
