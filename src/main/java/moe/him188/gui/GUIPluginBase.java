package moe.him188.gui;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import moe.him188.gui.window.WindowManager;

/**
 * @author Him188moe @ GUI Project
 */
public class GUIPluginBase extends PluginBase {
    private static GUIPluginBase instance;

    {
        instance = this;
    }

    public static GUIPluginBase getInstance() {
        return instance;
    }

    private WindowManager.RespondedListener listener;

    @Override
    public void onEnable() {
        super.onEnable();

        if (listener == null) {
            listener = new WindowManager.RespondedListener();
        }
        Server.getInstance().getPluginManager().registerEvents(listener, this);
    }
}
