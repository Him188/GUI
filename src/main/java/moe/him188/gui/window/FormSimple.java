package moe.him188.gui.window;

import cn.nukkit.form.element.ElementButton;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Alias of the Responsible one
 *
 * @author Him188moe @ GUI project
 */
public class FormSimple extends ResponsibleFormWindowSimple {
    public FormSimple() {
    }

    public FormSimple(String content) {
        super(content);
    }

    public FormSimple(String title, String content) {
        super(title, content);
    }

    public FormSimple(String title, String content, String... buttons) {
        super(title, content, buttons);
    }

    public FormSimple(String title, String content, ElementButton... buttons) {
        super(title, content, buttons);
    }

    public FormSimple(String title, String content, @NotNull List<ElementButton> buttons) {
        super(title, content, buttons);
    }
}
