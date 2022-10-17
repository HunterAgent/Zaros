package framework.Player;

import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Inventory {
    public static boolean predicate(SimpleItem item, int ... itemId) {
        Stream<Integer> array = Arrays.stream(itemId).boxed();
        Predicate<Integer> filter = id -> item.getId() == id;
        return array.anyMatch(filter);
    }

    public static boolean predicate(SimpleItem item, String... itemName) {
        Stream<String> array = Stream.of(itemName);
        Predicate<String> filter = arr -> item.getName().toLowerCase().contains(arr.toLowerCase());
        return array.anyMatch(filter);
    }

    public static SimpleItemQuery<SimpleItem> filter(String... itemName) {
        return ClientContext.instance().inventory.populate().filter(p -> predicate(p, itemName));
    }

    public static SimpleItemQuery<SimpleItem> filter(int ... itemId) {
        return ClientContext.instance().inventory.populate().filter(p -> predicate(p, itemId));
    }

    public static SimpleItemQuery<SimpleItem> filter(Predicate<SimpleItem> p) {
        return ClientContext.instance().inventory.populate().filter(p);
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

    public static int count(String ... itemName) {
        return filter(itemName).population();
    }
    public static boolean onlyContains(String... itemName) {
        return ClientContext.instance().inventory.populate().population() - count(itemName) == 0;
    }

    public static boolean contains(List<String> names) {
        return !filter(item -> names.contains(item.getName().toLowerCase())).isEmpty();
    }

    public static SimpleItemQuery<SimpleItem> getItems(String... itemName) {
        return filter(itemName);
    }

    public static SimpleItemQuery<SimpleItem> getItems(int ... itemId) {
        return filter(itemId);
    }

    public static SimpleItem getItem(String... itemName) {
        return getItems(itemName).next();
    }

    public static SimpleItem getItem(int ... id) {
        return getItems(id).next();
    }

    public static boolean isFull() {
        return ClientContext.instance().inventory.inventoryFull();
    }

    public static boolean isEmpty() {
        return ClientContext.instance().inventory.isEmpty();
    }

    public static boolean leftClickUseItemOnItem(String name1, String name2) {
        SimpleItem item1 = getItem(name1);
        SimpleItem item2 = getItem(name2);
        return item1 != null && item2 != null && item1.click(0) && item2.click(0);
    }

    public static boolean leftClickUseItemOnItem(int id1, int id2) {
        SimpleItem item1 = getItem(id1);
        SimpleItem item2 = getItem(id2);
        return item1 != null && item2 != null && item1.click(0) && item2.click(0);
    }

    public static boolean useItemOnItem(String name1, String name2) {
        SimpleItem item1 = getItem(name1);
        SimpleItem item2 = getItem(name2);
        return item1 != null && item2 != null && ClientContext.instance().inventory.itemOnItem(item1, item2);
    }

    public static boolean hasBrews() {
        return Inventory.contains(6691, 6689, 6687, 6685);
    }
}
