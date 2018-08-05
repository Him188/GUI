package moe.him188.gui.template.element;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementDropdown;
import moe.him188.gui.template.response.TemplateResponse;
import moe.him188.gui.utils.ResponseParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Him188moe @ GUI Project
 */
public class TemplateElementDropdown<K> extends TemplateElement<K> {
    private final String name;
    private final List<String> options;
    private final int defaultOption;

    /**
     * @see ElementDropdown
     */
    public TemplateElementDropdown(K elementKey, String name, List<String> options, int defaultOption) {
        super(elementKey);
        this.name = name;
        this.options = options;
        this.defaultOption = defaultOption;
    }

    public TemplateElementDropdown(K elementKey, String name) {
        this(elementKey, name, new ArrayList<>());
    }

    public TemplateElementDropdown(K elementKey, String name, List<String> options) {
        this(elementKey, name, options, 0);
    }

    @Override
    public String getName() {
        return name;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getDefaultOption() {
        return defaultOption;
    }

    @Override
    public Element build() {
        return new ElementDropdown(this.name, this.options, this.defaultOption);
    }

    @Override
    public Response parseResponse(Object object) throws ResponseParseException {
        try {
            return new Response(Integer.parseInt(String.valueOf(object)));
        } catch (NumberFormatException e) {
            throw new ResponseParseException(this, e);
        }
    }

    public static class Response extends TemplateResponse<Integer> {
        private Response(int response) {
            super(response);
        }

        @Override
        public Integer get() {
            return response;
        }
    }
}
