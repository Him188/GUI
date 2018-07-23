package moe.him188.gui.window.defaults;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import moe.him188.gui.window.ResponsibleFormWindowSimple;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 提示窗口 <br>
 * Tip window
 *
 * @author Him188moe @ ProjectARK Project
 */
public class TipWindow extends ResponsibleFormWindowSimple {
    public TipWindow(String title, String content, String buttonText) {
        super(title, content, new ArrayList<ElementButton>() {
            {
                add(new ElementButton(buttonText));
            }
        });
    }

    public TipWindow(String content, String buttonText) {
        super("提示 | Announcement", content, new ArrayList<ElementButton>() {
            {
                add(new ElementButton(buttonText));
            }
        });
    }

    public TipWindow onClickedPlayerly(Consumer<Player> listener) {
        Objects.requireNonNull(listener);
        return (TipWindow) super.onClicked((id, player) -> listener.accept(player));
    }
}
