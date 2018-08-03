package moe.him188.gui.utils;

import cn.nukkit.Server;
import cn.nukkit.level.Level;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeLevel extends InputType<Level> {
    @Override
    public Level parseResponse(String content) throws InputFormatException {
        Level level = Server.getInstance().getLevelByName(content);
        if (level == null) {
            throw new InputFormatException(InputFormatException.Reason.LEVEL_NOT_FOUND, content, new NullPointerException());
        }
        return level;
    }
}
