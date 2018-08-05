package moe.him188.gui.template.element;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementInput;
import moe.him188.gui.template.response.TemplateResponse;
import moe.him188.gui.utils.InputFormatException;
import moe.him188.gui.utils.InputType;
import moe.him188.gui.utils.InputTypes;
import moe.him188.gui.utils.ResponseParseException;

/**
 * @author Him188moe @ GUI Project
 * @see ElementInput
 */
public class TemplateElementInput<K, R> extends TemplateElement<K> {
    private final InputType<R> type;

    private final String name;
    private final String placeholder;
    private final String defaultText;

    /**
     * @param type 可输入的数据类型
     *
     * @see InputTypes
     */
    public TemplateElementInput(K elementKey, InputType<R> type, String name, String placeholder, String defaultText) {
        super(elementKey);
        this.type = type;

        this.name = name;
        this.placeholder = placeholder;
        this.defaultText = defaultText;
    }

    public TemplateElementInput(K elementKey, InputType<R> type, String name) {
        this(elementKey, type, name, "");
    }

    public TemplateElementInput(K elementKey, InputType<R> type, String name, String placeholder) {
        this(elementKey, type, name, placeholder, "");
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public InputType<R> getType() {
        return type;
    }

    @Override
    public Element build() {
        return new ElementInput(this.name, this.placeholder, this.defaultText);
    }

    @Override
    public Response<R> parseResponse(Object object) throws ResponseParseException {
        String response = object == null ? "" : object.toString();
        try {
            return new Response<>(this.type.parseResponse(response));
        } catch (InputFormatException e) {
            throw new ResponseParseException(this, e);
        }
    }

    public static class Response<R> extends TemplateResponse<R> {
        private Response(R response) {
            super(response);
        }

        @Override
        public R get() {
            return response;
        }
    }
}
