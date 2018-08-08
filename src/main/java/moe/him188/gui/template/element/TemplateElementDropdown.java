package moe.him188.gui.template.element;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementDropdown;
import moe.him188.gui.template.response.TemplateResponse;
import moe.him188.gui.utils.ResponseParseException;
import org.jetbrains.annotations.NotNull;

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
    public TemplateElementDropdown(@NotNull K elementKey, String name, List<String> options, int defaultOption) {
        super(elementKey);
        this.name = name;
        this.options = options;
        this.defaultOption = defaultOption;
    }

    public TemplateElementDropdown(@NotNull K elementKey, String name) {
        this(elementKey, name, new ArrayList<>());
    }

    public TemplateElementDropdown(@NotNull K elementKey, String name, List<String> options) {
        this(elementKey, name, options, 0);
    }

    @Override
    public String getName() {
        return name;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getOption(int id) {
        return this.options.get(id);
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
            int response = Integer.parseInt(String.valueOf(object));
            return new Response(response, this.getOption(response));
        } catch (NumberFormatException e) {
            throw new ResponseParseException(this, e);
        }
    }

    public static class Response extends TemplateResponse<Integer> {
        private final String option;

        private Response(int response, String option) {
            super(response);
            this.option = option;
        }

        @Override
        public Integer get() {
            return response;
        }

        public String getOption() {
            return option;
        }
    }
}
