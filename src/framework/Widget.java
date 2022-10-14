package framework;

import simple.hooks.simplebot.Game;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;

import java.util.ArrayList;

public class Widget {

//    Might not work, .getChildren is throwing me a NPE
    // MIGHT NEED TO USE DYNAMIC CHILDREN?
//    public static ArrayList<SimpleWidget> getAllChildren(SimpleWidget w){
//        ArrayList<SimpleWidget> children = new ArrayList<>();
//        if (w != null) {
//            ClientContext.instance().log(String.valueOf(w.getId()));
//            children.add(w);
//            for (SimpleWidget c : w.getChildren()) {
//                children.addAll(getAllChildren(c));
//            }
//        }
//        return children;
//    }

    public static SimpleWidget getWidget(int id, int sub_id) {
        return ClientContext.instance().widgets.getWidget(id, sub_id);
    }

    public static SimpleWidget getWidget(int id, int sub_id, int child_id) {
        SimpleWidget tmp = ClientContext.instance().widgets.getWidget(id, sub_id);
        return tmp == null ? null : tmp.getChild(child_id);
    }

    public static SimpleWidget getChildContainingName(SimpleWidget parent, String child_text) {
        if (parent == null) return null;
        for (SimpleWidget child: parent.getChildren()) {
            if (child.getName().toLowerCase().contains(child_text.toLowerCase())) {
                return child;
            }
        }
        return null;
    }

    public static SimpleWidget getChildContainingName(int id, int sub_id, String child_text) {
        SimpleWidget parent = ClientContext.instance().widgets.getWidget(id, sub_id);
        return getChildContainingName(parent, child_text);
    }

    public static SimpleWidget getChildContainingText(SimpleWidget parent, String child_text) {
        if (parent == null) return null;
        for (SimpleWidget child: parent.getChildren()) {
            if (child.getText().toLowerCase().contains(child_text.toLowerCase())) {
                return child;
            }
        }
        return null;
    }

    public static SimpleWidget getChildContainingText(int id, int sub_id, String child_text) {
        SimpleWidget parent = ClientContext.instance().widgets.getWidget(id, sub_id);
        return getChildContainingText(parent, child_text);
    }

    public static boolean isValidWidget(SimpleWidget w) {
        return w != null && w.visibleOnScreen() && w.validateInteractable();
    }

    public static Game.Tab getGameTab() {
        return ClientContext.instance().game.tab();
    }

    public static void openTab(Game.Tab tab) {
        if (!isTabOpen(tab)) ClientContext.instance().game.tab(tab);
    }

    public static boolean isTabOpen(Game.Tab tab) {
        return ClientContext.instance().game.tab().equals(tab);
    }
}
