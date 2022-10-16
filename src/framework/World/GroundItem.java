package framework.World;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.robot.api.ClientContext;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class GroundItem {
    public static boolean predicate(SimpleGroundItem item, int ... itemId) {
        Stream<Integer> array = Arrays.stream(itemId).boxed();
        Predicate<Integer> filter = id -> item.getId() == id;
        return array.anyMatch(filter);
    }

    public static boolean predicate(SimpleGroundItem item, String... itemName) {
        Stream<String> array = Stream.of(itemName);
        Predicate<String> filter = name -> item.getName().toLowerCase().contains(name.toLowerCase());
        return array.anyMatch(filter);
    }

    public static SimpleEntityQuery<SimpleGroundItem> filter(int ... itemId) {
        return ClientContext.instance().groundItems.populate().filter(p -> predicate(p, itemId));
    }

    public static SimpleEntityQuery<SimpleGroundItem> filter(String... itemName) {
        return ClientContext.instance().groundItems.populate().filter(p -> predicate(p, itemName));
    }

    public static SimpleEntityQuery<SimpleGroundItem> filter(Predicate<SimpleGroundItem> p) {
        return ClientContext.instance().groundItems.populate().filter(p);
    }

    public static boolean isValid(SimpleGroundItem gItem) {
        return gItem != null && ClientContext.instance().pathing.reachable(gItem) && gItem.visibleOnScreen() && gItem.validateInteractable();
    }

    public static SimpleGroundItem getNearest() {
        return ClientContext.instance().groundItems.populate().nearest().next();
    }

    public static SimpleGroundItem getNearest(String name) {
        return filter(name).nearest().next();
    }

    public static SimpleGroundItem getNearest(int id) {
        return filter(id).nearest().next();
    }

    public static boolean isLootNearby() {
        return ClientContext.instance().groundItems.populate().size() > 0;
    }
}
