package moe.him188.gui.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeString extends InputType<String> {
    @NotNull
    @Override
    public String parseResponse(String content) throws InputFormatException {
        return content;
    }
}
