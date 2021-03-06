package moe.him188.gui.utils;

import cn.nukkit.Player;
import cn.nukkit.Server;
import org.jetbrains.annotations.NotNull;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypePlayer extends InputType<Player> {
    @NotNull
    @Override
    public Player parseResponse(String content) throws InputFormatException {
        Player player = Server.getInstance().getPlayer(content);
        if (player == null) {
            throw new InputFormatException(InputFormatException.ReasonDefaults.PLAYER_NOT_FOUND, content, new NullPointerException());
        }

        return player;
    }
}
