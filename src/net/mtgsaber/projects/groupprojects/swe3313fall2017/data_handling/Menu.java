package net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Author: Andrew Arnold (10/23/2017)
 */
public class Menu {
    private final LinkedList<Item> items;

    public Menu(Item[] items) {
        this.items = new LinkedList<>(Arrays.asList(items));
    }

    public LinkedList<Item> getItems() {
        LinkedList<Item> items = new LinkedList<>();
        items.addAll(this.items);
        return items;
    }

    public <T extends ItemCategory> LinkedList<Item<T>> getItemsOfCategory(T key) {
        LinkedList<Item<T>> categorizedItems = new LinkedList<>();
        for (Item item : getItems()) {
            if (item.getCategory().getClass() == key.getClass()) {
                categorizedItems.add(item);
            }
        }
        return categorizedItems;
    }
}
