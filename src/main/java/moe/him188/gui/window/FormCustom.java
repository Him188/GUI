package moe.him188.gui.window;

import cn.nukkit.form.element.Element;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Alias of the Responsible one
 *
 * @author Him188moe @ GUI project
 */
public class FormCustom extends ResponsibleFormWindowCustom {
    public FormCustom() {
    }

    public FormCustom(String title) {
        super(title);
    }

    public FormCustom(String title, Element... contents) {
        super(title, contents);
    }

    public FormCustom(String title, @NotNull List<Element> contents) {
        super(title, contents);
    }

    public FormCustom(String title, @NotNull List<Element> contents, @NotNull String icon) {
        super(title, contents, icon);
    }
}
