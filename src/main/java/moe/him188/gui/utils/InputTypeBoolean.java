package moe.him188.gui.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeBoolean extends InputType<Boolean> {
    @NotNull
    @Override
    public Boolean parseResponse(String content) throws InputFormatException {
        switch (content.toLowerCase()) {
            case "1":
            case "yes":
                return true;
        }
        return Boolean.parseBoolean(content);
    }
}
