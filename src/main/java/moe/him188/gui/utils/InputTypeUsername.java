package moe.him188.gui.utils;

import cn.nukkit.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeUsername extends InputType<String> {
    @NotNull
    @Override
    public String parseResponse(String content) throws InputFormatException {
        if (isValid(content)) {
            return content;
        } else {
            throw new InputFormatException(InputFormatException.ReasonDefaults.USERNAME_FORMAT, content);
        }
    }

    /**
     * Copied from nukkit
     *
     * @param username username
     *
     * @return valid or not
     *
     * @see Player#handleDataPacket loginPacket (at line 2100~)
     */
    public static boolean isValid(String username) {
        int len = username.length();
        if (len > 16 || len < 3) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9') ||
                c == '_' || c == ' '
            ) {
                continue;
            }

            return false;
        }

        return !Objects.equals(username, "rcon") && !Objects.equals(username, "console");
    }
}
