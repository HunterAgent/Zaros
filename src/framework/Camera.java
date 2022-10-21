package framework;

import simple.hooks.interfaces.SimpleLocatable;
import simple.hooks.simplebot.Game;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;

public class Camera {

    public enum Zoom{
        MAX_ZOOM,
        ZOOM_2,
        ZOOM_3,
        MED_ZOOM,
        ZOOM_5,
        ZOOM_6,
        MIN_ZOOM
    }

    public static void setZoom(Zoom zoom) {
        Game.Tab original_tab = Widget.getGameTab();
        if (!Widget.isTabOpen(Game.Tab.OPTIONS)) {
            Widget.openTab(Game.Tab.OPTIONS);
            ClientContext.instance().sleepCondition(() -> Widget.isTabOpen(Game.Tab.OPTIONS), 1000);
        }

        SimpleWidget display_widget = Widget.getWidget(116, 112);
        if (!Widget.isValidWidget(display_widget)) {
            ClientContext.instance().log("Failed switching to display settings tab");
            Widget.openTab(original_tab);
            return;
        }

        ClientContext.instance().sleepCondition(() -> display_widget.click(0), 1000);
        ClientContext.instance().sleep(500);

        SimpleWidget widget = Widget.getWidget(116, 92 + zoom.ordinal());
        if (!Widget.isValidWidget(widget)) {
            ClientContext.instance().log("Failed getting Zoom Widget");
            Widget.openTab(original_tab);
            return;
        }

        if (widget.click(0) || widget.click(0) || widget.click(0)) {
            ClientContext.instance().log("Successfully adjusted zoom");
        } else {
            ClientContext.instance().log("Failed adjusting zoom");
        }

        Widget.openTab(original_tab);
    }

    public static void zoomOut() {
        setZoom(Zoom.MAX_ZOOM);
    }

    public static void setNorth() {
        SimpleWidget north = Widget.getWidget(548, 20);
        if (Widget.isValidWidget(north)) {
            if (!north.click("Look North"))
            {
                ClientContext.instance().log("Failed setting camera to north");
            }
        }
    }

    public static void setupDefaultCameraZoom() {
        ClientContext ctx = ClientContext.instance();
        setNorth();
        zoomOut();
        ctx.viewport.pitch(true);
        ctx.viewport.pitch(true);
        ctx.viewport.pitch(true);
    }

    public static boolean turnTo(SimpleLocatable obj)
    {
        return ClientContext.instance().viewport.turnTo(obj);
    }

}
