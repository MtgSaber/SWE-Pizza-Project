package net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Sort of an Enumeration of the available Item Categories for which an Item can be typed.
 * Author: Andrew Arnold (10/23/2017)
 */
public abstract class ItemCategory {

    public static final class PizzaBase extends ItemCategory {
        private static final PizzaBase instance = new PizzaBase();
        @Override
        public ItemCategory getInstance() {
            return instance;
        }

        @Override
        public String getName() {
            return getClass().getName();
        }
    }

    public static final class Beverage extends ItemCategory {
        private static final Beverage instance = new Beverage();

        @Override
        public ItemCategory getInstance() {
            return instance;
        }

        @Override
        public String getName() {
            return getClass().getName();
        }
    }

    public static final class Topping extends ItemCategory {
        private static final Topping instance = new Topping();

        @Override
        public ItemCategory getInstance() {
            return instance;
        }

        @Override
        public String getName() {
            return getClass().getName();
        }
    }

    public static final class Pizza extends ItemCategory {
        private final Item<PizzaBase> base;
        private final LinkedList<Item<Topping>> toppings;
        private final String name;

        public Pizza(String name, Item<PizzaBase> base, Item<Topping>[] toppings) {
            this.name = name;
            this.base = base;
            this.toppings = new LinkedList<>(Arrays.asList(toppings));
        }

        @Override
        public String getName() { return name; }

        public Item<PizzaBase> getBase() {
            return base;
        }

        public LinkedList<Item<Topping>> getToppings() {
            LinkedList<Item<Topping>> toppings = new LinkedList<>();
            toppings.addAll(this.toppings);
            return toppings;
        }

        @Override
        public ItemCategory getInstance() {
            return this;
        }
    }

    public abstract ItemCategory getInstance();
    public abstract String getName();
}
