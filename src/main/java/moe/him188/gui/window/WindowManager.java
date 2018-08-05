package moe.him188.gui.window;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;

/**
 * 用于管理窗口回调
 *
 * @author Him188moe @ GUI Project
 */
public final class WindowManager {

    public static final class RespondedListener implements Listener {
        @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
        public void onResponded(PlayerFormRespondedEvent event) {
            if (ResponsibleFormWindowSimpleAdvanced.onEvent(event)) {
                return;
            }
            if (ResponsibleFormWindowSimple.onEvent(event)) {
                return;
            }
            if (ResponsibleFormWindowTemplated.onEvent(event)) {
                return;
            }
            if (ResponsibleFormWindowCustom.onEvent(event)) {
                return;
            }
            ResponsibleFormWindowModal.onEvent(event);
        }
    }
}
