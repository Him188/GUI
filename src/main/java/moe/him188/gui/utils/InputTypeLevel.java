package moe.him188.gui.utils;

import cn.nukkit.Server;
import cn.nukkit.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeLevel extends InputType<Level> {
    @NotNull
    @Override
    public Level parseResponse(String content) throws InputFormatException {
        Level level = Server.getInstance().getLevelByName(content);
        if (level == null) {
            throw new InputFormatException(InputFormatException.ReasonDefaults.LEVEL_NOT_FOUND, content, new NullPointerException());
        }
        return level;
    }
}
