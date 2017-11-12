package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.registerClient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.Customer;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.Item;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.ItemCategory;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.Order;

import java.util.LinkedList;

/**
 * Author: Andrew Arnold (11/8/2017)
 */
public class PaneTesting {
    public static class TestPaneLogon extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            RegisterPanes.LogonPane logonPane = new RegisterPanes.LogonPane(primaryStage);
            logonPane.stage.setScene(new Scene(logonPane));
            logonPane.stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    public static class TestPaneHomeToolbar extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            RegisterPanes.MainPane.HomeToolBar homeToolBar = new RegisterPanes.MainPane.HomeToolBar();
            primaryStage.setScene(new Scene(homeToolBar));
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

    public static class TestPaneOrderEditorViewPane extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane orderEditorViewPane =
                    new RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane(null);
            primaryStage.setScene(new Scene(orderEditorViewPane));
            primaryStage.show();
        }

        public static void main(String[] args) {launch(args);}
    }

    public static class TestOrderEditorViewPane_BeverageOption extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.BeverageOption beverageOption =
                    new RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.BeverageOption(
                            new Item<>("Cola", new ItemCategory.Beverage(), 3.14)
                    );

            ListView<RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.BeverageOption>
                    lvDrinkOption = new ListView<>();

            lvDrinkOption.getItems().add(beverageOption);

            Pane pane = new Pane(lvDrinkOption);

            primaryStage.setScene(new Scene(pane));
            primaryStage.show();
        }

        public static void main(String[] args) { launch(args); }
    }

    public static class TestOrderEditorViewPane_PizzaBuilderPane_ToppingOption extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaBuilderViewPane.ToppingOption
                    toppingOption = new RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaBuilderViewPane.ToppingOption(
                            new Item<>("Left Beef", new ItemCategory.Topping(), 6.28),
                    true
            );

            ListView<RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaBuilderViewPane
                    .ToppingOption> lvToppingOptions = new ListView<>();

            lvToppingOptions.getItems().add(toppingOption);

            primaryStage.setScene(new Scene(lvToppingOptions));
            primaryStage.show();
        }

        public static void main(String[] args) { launch(args); }
    }

    public static class TestOrderEditorViewPane_PizzaViewCell extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            ListView<RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaViewCell>
                    pizzaViewCellListView = new ListView<>();

            LinkedList<Item<ItemCategory.Topping>> listToppings = new LinkedList<>();
            listToppings.add(new Item<ItemCategory.Topping>("Bones", new ItemCategory.Topping(), 720));

            pizzaViewCellListView.getItems().add(new RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaViewCell(
                    new Item<>(
                            "Boneless Pizza",
                            new ItemCategory.Pizza(
                                    "Boneless Pizza",
                                    new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                    listToppings
                            ),
                            23)
            ));

            primaryStage.setScene(new Scene(pizzaViewCellListView));
            primaryStage.show();
        }

        public static void main(String[] args) { launch(args); }
    }

    public static class TestOrderEditorViewPane_PizzaBuilderPane extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            LinkedList<Item<ItemCategory.Topping>> listToppings = new LinkedList<>();
            listToppings.add(new Item<ItemCategory.Topping>("Bones", new ItemCategory.Topping(), 720));

            RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaBuilderViewPane
                    pizzaBuilderViewPane = new RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaBuilderViewPane(
                            new Item<>(
                                    "Boneless Pizza",
                                    new ItemCategory.Pizza(
                                        "Boneless Pizza",
                                        new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                        listToppings
                                    ),
                                    23)
            );

            primaryStage.setScene(new Scene(pizzaBuilderViewPane));
            primaryStage.show();
        }

        public static void main(String[] args) { launch(args);}
    }

    public static class TestOrdersViewPane extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            Customer customer = new Customer("Aaron", "123 Foo Lane", 3, new Order[] {});
            LinkedList<Item<ItemCategory.Topping>> listToppings = new LinkedList<>();
            listToppings.add(new Item<ItemCategory.Topping>("Bones", new ItemCategory.Topping(), 720));
            Order[] orders = new Order[] {
                    new Order(
                            customer,
                            new Item[] {
                                    new Item<>(
                                            "Boneless Pizza",
                                            new ItemCategory.Pizza(
                                                    "Boneless Pizza",
                                                    new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                                    listToppings
                                            ),
                                            23),
                                    new Item<>(
                                            "Cola",
                                            new ItemCategory.Beverage(),
                                            6.66
                                    )
                            }
                    ),
                    new Order(
                            customer,
                            new Item[] {
                                    new Item<>(
                                            "Boneless Pizza",
                                            new ItemCategory.Pizza(
                                                    "Boneless Pizza",
                                                    new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                                    listToppings
                                            ),
                                            23),
                                    new Item<>(
                                            "Cola",
                                            new ItemCategory.Beverage(),
                                            6.66
                                    )
                            }
                    ),
                    new Order(
                            customer,
                            new Item[] {
                                    new Item<>(
                                            "Boneless Pizza",
                                            new ItemCategory.Pizza(
                                                    "Boneless Pizza",
                                                    new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                                    listToppings
                                            ),
                                            23),
                                    new Item<>(
                                            "Cola",
                                            new ItemCategory.Beverage(),
                                            6.66
                                    )
                            }
                    ),
                    new Order(
                            customer,
                            new Item[] {
                                    new Item<>(
                                            "Boneless Pizza",
                                            new ItemCategory.Pizza(
                                                    "Boneless Pizza",
                                                    new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                                    listToppings
                                            ),
                                            23),
                                    new Item<>(
                                            "Cola",
                                            new ItemCategory.Beverage(),
                                            6.66
                                    )
                            }
                    ),
                    new Order(
                            customer,
                            new Item[] {
                                    new Item<>(
                                            "Boneless Pizza",
                                            new ItemCategory.Pizza(
                                                    "Boneless Pizza",
                                                    new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                                    listToppings
                                            ),
                                            23),
                                    new Item<>(
                                            "Cola",
                                            new ItemCategory.Beverage(),
                                            6.66
                                    )
                            }
                    ),
                    new Order(
                            customer,
                            new Item[] {
                                    new Item<>(
                                            "Boneless Pizza",
                                            new ItemCategory.Pizza(
                                                    "Boneless Pizza",
                                                    new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                                    listToppings
                                            ),
                                            23),
                                    new Item<>(
                                            "Cola",
                                            new ItemCategory.Beverage(),
                                            6.66
                                    )
                            }
                    ),
                    new Order(
                            customer,
                            new Item[] {
                                    new Item<>(
                                            "Boneless Pizza",
                                            new ItemCategory.Pizza(
                                                    "Boneless Pizza",
                                                    new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                                    listToppings
                                            ),
                                            23),
                                    new Item<>(
                                            "Cola",
                                            new ItemCategory.Beverage(),
                                            6.66
                                    )
                            }
                    ),
                    new Order(
                            customer,
                            new Item[] {
                                    new Item<>(
                                            "Boneless Pizza",
                                            new ItemCategory.Pizza(
                                                    "Boneless Pizza",
                                                    new Item<>("Small-Thin", new ItemCategory.PizzaBase(), 4.20),
                                                    listToppings
                                            ),
                                            23),
                                    new Item<>(
                                            "Cola",
                                            new ItemCategory.Beverage(),
                                            6.66
                                    )
                            }
                    ),
            };

            RegisterPanes.MainPane.ViewPane.OrdersViewPane ordersViewPane = new RegisterPanes.MainPane.ViewPane.OrdersViewPane(
                    orders
            );

            RegisterPanes.MainPane mainPane = new RegisterPanes.MainPane();

            mainPane.viewPane = ordersViewPane;

            mainPane.refresh();

            primaryStage.setScene(new Scene(mainPane));
            primaryStage.show();
        }

        public static void main(String[] args) { launch(args); }
    }

    public static class TestMainPane extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            RegisterPanes.MainPane mainPane = new RegisterPanes.MainPane();

            primaryStage.setScene(new Scene(mainPane));
            primaryStage.show();
        }


        public static void main(String[] args) { launch(args); }
    }
}
