package moe.him188.gui.utils;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeString extends InputType<String> {
    @Override
    public String parseResponse(String content) throws InputFormatException {
        return content;
    }
}
