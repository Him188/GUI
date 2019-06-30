package moe.him188.gui.window;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerKickEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.player.PlayerSettingsRespondedEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.window.FormWindow;
import moe.him188.gui.utils.Backable;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于管理窗口回调
 *
 * @author Him188moe @ GUI Project
 */
public final class WindowManager {

    public static final class RespondedListener implements Listener {
        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        public void onResponded(PlayerFormRespondedEvent event) {
            processResponseEvent(event.getWindow(), event.getResponse(), event.getPlayer());
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        public void onResponded(PlayerSettingsRespondedEvent event) {
            processResponseEvent(event.getWindow(), event.getResponse(), event.getPlayer());
        }

        private void processResponseEvent(FormWindow window, FormResponse response, Player player) {
            if (ResponsibleFormWindowSimpleAdvanced.onEvent(window, response, player)) {
                return;
            }
            if (ResponsibleFormWindowSimple.onEvent(window, response, player)) {
                return;
            }
            if (ResponsibleFormWindowTemplated.onEvent(window, response, player)) {
                return;
            }
            if (ResponsibleFormWindowCustom.onEvent(window, response, player)) {
                return;
            }
            ResponsibleFormWindowModal.onEvent(window, response, player);
        }
    }

    public static final class GoBackListener implements Listener {
        private static Map<Integer, FormWindow> lastWindows;

        public GoBackListener() {
            lastWindows = new HashMap<>();
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        public void onResponded(PlayerFormRespondedEvent event) {
            FormWindow parent = lastWindows.remove(event.getPlayer().getLoaderId());
            if (parent != null) {
                if (event.getWindow() instanceof Backable) {
                    ((Backable) event.getWindow()).setParent(parent);
                }
            }
            lastWindows.put(event.getPlayer().getLoaderId(), event.getWindow());
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        public void onQuit(PlayerQuitEvent event) {
            lastWindows.remove(event.getPlayer().getLoaderId());
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        public void onKick(PlayerKickEvent event) {
            lastWindows.remove(event.getPlayer().getLoaderId());
        }
    }
}
