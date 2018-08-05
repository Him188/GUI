package moe.him188.gui.template.element;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementToggle;
import moe.him188.gui.template.response.TemplateResponse;
import moe.him188.gui.utils.ResponseParseException;

/**
 * @author Him188moe @ GUI Project
 * @see ElementToggle
 */
public class TemplateElementToggle<K> extends TemplateElement<K> {
    private final String name;
    private final boolean defaultValue;

    public TemplateElementToggle(K elementKey, String name) {
        this(elementKey, name, false);
    }

    public TemplateElementToggle(K elementKey, String name, boolean defaultValue) {
        super(elementKey);

        this.name = name;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Element build() {
        return new ElementToggle(this.name, this.defaultValue);
    }

    @Override
    public Response parseResponse(Object object) throws ResponseParseException {
        try {
            return new Response(Boolean.parseBoolean(String.valueOf(object)));
        } catch (NumberFormatException e) {
            throw new ResponseParseException(this, e);
        }
    }

    public static class Response extends TemplateResponse<Boolean> {
        private Response(boolean response) {
            super(response);
        }

        @Override
        public Boolean get() {
            return response;
        }
    }
}
