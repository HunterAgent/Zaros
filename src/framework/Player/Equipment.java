package framework.Player;

import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Equipment {
    public static boolean predicate(SimpleItem item, String... itemName) {
        Stream<String> array = Stream.of(itemName);
        Predicate<String> filter = arr -> item.getName().toLowerCase().contains(arr.toLowerCase());
        return array.anyMatch(filter);
    }

    public static boolean predicate(SimpleItem item, int ... itemId) {
        Stream<Integer> array = Arrays.stream(itemId).boxed();
        Predicate<Integer> filter = id -> item.getId() == id;
        return array.anyMatch(filter);
    }

    public static SimpleItemQuery<SimpleItem> filter(int ... itemId) {
        return ClientContext.instance().equipment.populate().filter(p -> predicate(p, itemId));
    }

    public static SimpleItemQuery<SimpleItem> filter(String... itemName) {
        return ClientContext.instance().equipment.populate().filter(p -> predicate(p, itemName));
    }

    public static SimpleItemQuery<SimpleItem> filter(Predicate<SimpleItem> p) {
        return ClientContext.instance().equipment.populate().filter(p);
    }

    public static boolean containsAll(String... itemName) {
        return filter(itemName).population() == itemName.length;
    }

    public static boolean contains(String... itemName) {
        return filter(itemName).population() > 0;
    }

    public static boolean contains(int ... itemId) {
        return filter(itemId).population() > 0;
    }

    public static boolean contains(List<String> names) {
        return !filter(item -> names.contains(item.getName().toLowerCase())).isEmpty();
    }

    public static SimpleItemQuery<SimpleItem> getItems(String... itemName) {
        return filter(itemName);
    }

    public static SimpleItem getItem(String... itemName) {
        return getItems(itemName).next();
    }
}
