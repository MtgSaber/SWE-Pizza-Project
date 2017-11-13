package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.registerClient;

import javafx.stage.Stage;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.Item;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.ItemCategory;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.dbInterface.DBInterface;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.Events;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Author: Andrew Arnold (10/18/2017, DBInterface dbInterface)
 */
public class RegisterEvents extends Events {
    static class LogonPane_Events {
        static void actionBTLogIn(RegisterPanes.LogonPane logonPane, DBInterface dbInterface) {
            RegisterPanes.MainPane mainPane = new RegisterPanes.MainPane(new Stage(), dbInterface, logonPane);
            logonPane.lockControls();
            mainPane.stage.show();
        }
    }

    static class MainPane_Events {
        static class HomeToolBar_Events {
            static void actionBTOrders(RegisterPanes.MainPane mainPane, DBInterface dbInterface) {
                try {
                    mainPane.viewPane = new RegisterPanes.MainPane.ViewPane.OrdersViewPane(
                            dbInterface.getActiveOrders(),
                            mainPane
                    );
                    mainPane.refresh();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            static void actionBTCustomers(RegisterPanes.MainPane mainPane, DBInterface dbInterface) {

            }

            static void actionBTMenu(RegisterPanes.MainPane mainPane, DBInterface dbInterface) {

            }

            static void actionBTLogout(RegisterPanes.MainPane mainPane, DBInterface dbInterface) {
                mainPane.stage.close();
                mainPane.logonPane.unlockControls();
            }
        }

        static class ViewPane_Events {
            static class OrdersViewPane_Events {
                static class OrderEditorViewPane_Events {
                    static class PizzaBuilderViewPane_Events {
                        static void actionBTConfirm(
                                RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane
                                        .PizzaBuilderViewPane pizzaBuilderViewPane,
                                DBInterface dbInterface
                        ) {
                            LinkedList<Item<ItemCategory.Topping>> toppings = new LinkedList<>();
                            for (RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaBuilderViewPane.ToppingOption toppingOption:
                                 pizzaBuilderViewPane.lvToppings.getItems()) {
                                if (toppingOption.isSelected())
                                    toppings.add(toppingOption.represented);
                            }
                            ItemCategory.Pizza pizza = new ItemCategory.Pizza(
                                    pizzaBuilderViewPane.represented.getName(),
                                    pizzaBuilderViewPane.chcbxPizzaBase.getValue(),
                                    toppings
                            );
                            for (RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaViewCell
                                 pizzaViewCell : pizzaBuilderViewPane.orderEditorViewPane.pizzas.getItems()) {
                                if (pizzaViewCell.pizzaItem.getCategory() == pizza) {
                                    pizzaBuilderViewPane.orderEditorViewPane.pizzas.getItems().remove(pizzaViewCell);
                                    pizzaBuilderViewPane.orderEditorViewPane.pizzas.getItems().add(
                                            new RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaViewCell(
                                                    new Item<ItemCategory.Pizza>(
                                                            pizza.getName(),
                                                            pizza,
                                                            dbInterface.calculatePriceOfPizza(pizza)),
                                                    pizzaBuilderViewPane.orderEditorViewPane
                                    ));
                                }
                            }
                            pizzaBuilderViewPane.orderEditorViewPane.refresh();
                            pizzaBuilderViewPane.mainPane.viewPane = pizzaBuilderViewPane.orderEditorViewPane;
                            pizzaBuilderViewPane.mainPane.refresh();
                        }
                        static void actionBTCancel(
                                RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane
                                        .PizzaBuilderViewPane pizzaBuilderViewPane,
                                DBInterface dbInterface
                        ) {
                            pizzaBuilderViewPane.mainPane.viewPane = pizzaBuilderViewPane.orderEditorViewPane;
                            pizzaBuilderViewPane.mainPane.refresh();
                        }
                    }

                    static class PizzaViewCell_Events {
                        static void actionBTRemove(
                                RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane
                                        .PizzaViewCell pizzaViewCell,
                                DBInterface dbInterface
                        ) {
                            pizzaViewCell.orderEditorViewPane.pizzas.getItems().remove(pizzaViewCell);
                            pizzaViewCell.orderEditorViewPane.refresh();
                        }
                        static void actionBTEdit(
                                RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane
                                        .PizzaViewCell pizzaViewCell,
                                DBInterface dbInterface
                        ) {
                            RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaBuilderViewPane
                                    pizzaBuilderViewPane = new RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaBuilderViewPane(
                                            pizzaViewCell.pizzaItem, pizzaViewCell.orderEditorViewPane
                            );
                            pizzaBuilderViewPane.mainPane.viewPane = pizzaBuilderViewPane;
                            pizzaBuilderViewPane.mainPane.refresh();
                        }
                    }

                    static void actionBTCancel(
                            RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane editorViewPane,
                            DBInterface dbInterface
                    ) {
                        editorViewPane.mainPane.viewPane = editorViewPane.ordersViewPane;
                        editorViewPane.mainPane.refresh();
                    }

                    static void actionBTConfirm(
                            RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane editorViewPane,
                            DBInterface dbInterface
                    ) {
                        LinkedList<Item> items = new LinkedList<>();
                        for (RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.PizzaViewCell
                                pizzaViewCell : editorViewPane.pizzas.getItems()
                                )
                            items.add(pizzaViewCell.pizzaItem);
                        for (RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane.BeverageOption
                             beverageOption : editorViewPane.beverages.getItems())
                            if (beverageOption.getQty() > 0)
                                for (int i = 1; i <= beverageOption.getQty(); i++)
                                    items.add(beverageOption.represented);
                        for (Item item : editorViewPane.order.getItems())
                            if (!items.contains(item))
                                editorViewPane.order.removeItem(item);
                        for (Item item : items)
                            if (!Arrays.asList(editorViewPane.order.getItems()).contains(item))
                                editorViewPane.order.addItem(item);
                        editorViewPane.mainPane.viewPane = editorViewPane.ordersViewPane;
                        editorViewPane.mainPane.refresh();
                    }
                }

                static class OrderTile_Events {
                    static void actionBTEdit(
                            RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderTile orderTile,
                            DBInterface dbInterface
                    ) {
                        RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane orderEditorViewPane =
                                new RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane(
                                        orderTile.order,
                                        orderTile.ordersViewPane
                        );
                        orderEditorViewPane.mainPane.viewPane = orderEditorViewPane;
                        orderEditorViewPane.mainPane.refresh();
                    }

                    static void actionBTComplete(
                            RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderTile orderTile,
                            DBInterface dbInterface
                    ) {
                        //TODO: Remove completed orders from the pending orders table in database here.
                        orderTile.ordersViewPane.orders.remove(orderTile.order);
                        orderTile.ordersViewPane.refresh();
                    }
                }

                static void actionBTNew(
                        RegisterPanes.MainPane.ViewPane.OrdersViewPane ordersViewPane,
                        DBInterface dbInterface
                ) {
                    RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane orderEditorViewPane =
                            new RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane(
                                    null,
                                    ordersViewPane
                            );
                    orderEditorViewPane.mainPane.viewPane = orderEditorViewPane;
                    orderEditorViewPane.mainPane.refresh();
                }

                static void actionBTRefresh(
                        RegisterPanes.MainPane.ViewPane.OrdersViewPane ordersViewPane,
                        DBInterface dbInterface
                ) {

                }
            }

            static class CustomersViewPane_Events {}

            static class MenuViewPane_Events {}
        }
    }
}
