package moe.him188.gui.template.element;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementSlider;
import moe.him188.gui.template.response.TemplateResponse;
import moe.him188.gui.utils.ResponseParseException;
import org.jetbrains.annotations.NotNull;

/**
 * @author Him188moe @ GUI Project
 * @see ElementSlider
 */
public class TemplateElementSlider<K> extends TemplateElement<K> {
    private final String name;
    private final float min;//0
    private final float max;//100
    private final int step;
    private final float defaultValue;

    public TemplateElementSlider(@NotNull K elementKey, String name, int min, int max) {
        this(elementKey, name, min, max, -1);
    }

    public TemplateElementSlider(@NotNull K elementKey, String name, int min, int max, int step) {
        this(elementKey, name, min, max, step, 0f);
    }

    public TemplateElementSlider(@NotNull K elementKey, String name, int min, int max, int step, float defaultValue) {
        super(elementKey);
        this.name = name;
        this.min = min;
        this.max = max;
        if (step != -1f && step > 0) {
            this.step = step;
        } else {
            this.step = 0;
        }
        this.defaultValue = defaultValue;
    }

    @Override
    public String getName() {
        return name;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public int getStep() {
        return step;
    }

    public float getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Element build() {
        return new ElementSlider(this.name, this.min, this.max, this.step, this.defaultValue);
    }

    @Override
    public Response parseResponse(Object object) throws ResponseParseException {
        try {
            return new Response(Float.parseFloat(String.valueOf(object)));
        } catch (NumberFormatException e) {
            throw new ResponseParseException(this, e);
        }
    }

    public static class Response extends TemplateResponse<Float> {
        private Response(float response) {
            super(response);
        }

        @Override
        public Float get() {
            return response;
        }
    }
}
