package moe.him188.gui.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeFloat extends InputType<Float> {
    @NotNull
    @Override
    public Float parseResponse(String content) throws InputFormatException {
        try {
            return (float) Double.parseDouble(content);
        } catch (NumberFormatException e) {
            throw new InputFormatException(InputFormatException.ReasonDefaults.NUMBER_FORMAT, content, e);
        }
    }
}
