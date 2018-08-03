package moe.him188.gui.template.element;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementLabel;
import moe.him188.gui.template.response.TemplateResponse;

/**
 * @author Him188moe @ GUI Project
 * @see ElementLabel
 */
public class TemplateElementLabel<K> extends TemplateElement<K> {
    private final String name;

    public TemplateElementLabel(K elementKey, String name) {
        super(elementKey);

        this.name = name;
    }

    @Override
    public Element build() {
        return new ElementLabel(this.name);
    }

    @Override
    public Response parseResponse(Object object) {
        return new Response();
    }

    public static class Response extends TemplateResponse<Object> {
        private Response() {
            super(null);
        }

        @Override
        public Object get() {
            return null;
        }
    }
}
