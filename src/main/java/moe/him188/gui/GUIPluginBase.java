package moe.him188.gui;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import moe.him188.gui.window.WindowManager;

/**
 * @author Him188moe @ GUI Project
 */
public class GUIPluginBase extends PluginBase {
    private static GUIPluginBase instance;
    private ListenerManager listenerManager;

    public GUIPluginBase() {
        super();


        instance = this;
        listenerManager = new ListenerManager();
    }

    public static GUIPluginBase getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        listenerManager.register();
    }

    private class ListenerManager {
        private WindowManager.RespondedListener respondedListener;
        private WindowManager.GoBackListener goBackListener;

        private ListenerManager() {
            respondedListener = new WindowManager.RespondedListener();
            goBackListener = new WindowManager.GoBackListener();
        }

        private void register() {
            Server.getInstance().getPluginManager().registerEvents(goBackListener, GUIPluginBase.this);
            Server.getInstance().getPluginManager().registerEvents(respondedListener, GUIPluginBase.this);
        }
    }
}
