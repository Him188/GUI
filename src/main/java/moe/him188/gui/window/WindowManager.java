package moe.him188.gui.window;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerItemHeldEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.form.window.FormWindow;
import moe.him188.gui.template.Template;
import moe.him188.gui.template.element.TemplateElementInput;
import moe.him188.gui.template.response.TemplateResponses;
import moe.him188.gui.utils.Backable;
import moe.him188.gui.utils.InputTypes;

import java.util.HashMap;
import java.util.Map;

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

    public static final class GoBackListener implements Listener {
        private static Map<Integer, FormWindow> lastWindows;

        public GoBackListener() {
            lastWindows = new HashMap<>();
        }

        @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
        public void onResponded(PlayerFormRespondedEvent event) {
            FormWindow parent = lastWindows.remove(event.getPlayer().getLoaderId());
            if (parent != null) {
                if (event.getWindow() instanceof Backable) {
                    ((Backable) event.getWindow()).setParent(parent);
                }
            }
            lastWindows.put(event.getPlayer().getLoaderId(), event.getWindow());
        }

        @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
        public void onQuit(PlayerQuitEvent event) {
            lastWindows.remove(event.getPlayer().getLoaderId());
        }

        private static ResponsibleFormWindowTemplated test;

        @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
        public void onChat(PlayerItemHeldEvent event) {
            if (test == null) {
                test = new ResponsibleFormWindowTemplated<String>(new Template<String>() {
                    {
                        addElement(new TemplateElementInput<>("input", "input", InputTypes.STRING));
                    }
                }) {
                    @Override
                    public void onClosed(Player player) {
                        this.goBack(player);
                    }

                    @Override
                    public void onResponded(TemplateResponses<String> responses, Player player) {
                        applyLastResponse();
                    }
                };
            }
            event.getPlayer().showFormWindow(new ResponsibleFormWindowSimple("", "一级窗口").onClosed(player -> {
                player.showFormWindow(test);
            }));
        }
    }
}
