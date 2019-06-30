package moe.him188.gui.window;

import moe.him188.gui.template.Template;
import org.jetbrains.annotations.NotNull;

/**
 * Alias of the Responsible one
 *
 * @author Him188moe @ GUI project
 */
public class FormTemplated<K> extends ResponsibleFormWindowTemplated<K> {
    public FormTemplated(@NotNull Template<K> template) {
        super(template);
    }

    public FormTemplated(String title, @NotNull Template<K> template) {
        super(title, template);
    }
}
