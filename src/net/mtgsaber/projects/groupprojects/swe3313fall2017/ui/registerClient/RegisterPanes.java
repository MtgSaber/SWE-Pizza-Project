package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.registerClient;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.Item;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.ItemCategory;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.Order;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.Panes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Author: Andrew Arnold (10/18/2017)
 */
public class RegisterPanes extends Panes {
    /**
     * The first pane shown by the program. Logs a user on and displays the Main Pane when successful.
     */
    public static class LogonPane extends StandardPane {
        final Stage stage;
        final TextField tfUsername, tfPassword;
        final Text txtTFUsername, txtTFPassword;
        final Button btLogIn;

        LogonPane(Stage stage) {
            super();

            this.stage = stage;

            tfUsername = new TextField();
            tfPassword = new TextField();

            txtTFUsername = new Text();
            txtTFPassword = new Text();

            btLogIn = new Button();

            controls = new LinkedList<>();
            controls.addAll(Arrays.asList(
                    tfUsername,
                    tfPassword,
                    btLogIn
            ));

            build();

            hookEvents();

            refresh();
        }

        @Override
        protected void build() {
            super.getChildren().addAll(
                    new HBox(
                            4,
                            txtTFUsername,
                            tfUsername
                    ),
                    new HBox(
                            4,
                            txtTFPassword,
                            tfPassword
                    ),
                    btLogIn
            );

            txtTFUsername.setText("Username:\t");
            txtTFPassword.setText("Password:\t\t");
            btLogIn.setText("Log In");
            btLogIn.setTextFill(Color.GREEN);
        }

        @Override
        protected void hookEvents() {

        }

        @Override
        protected void refresh() {

        }

        @Override
        protected void lockControls() {
            super.lockControls();
        }

        @Override
        protected void unlockControls() {
            super.unlockControls();
        }
    }

    /**
     * Primary Pane of the program. Has a toolbar at the top with large buttons. Most of these buttons change the
     * ViewPane displayed in the main display space of this pane.
     */
    static class MainPane extends StandardPane {
        static class HomeToolBar extends StandardPane {
            final Button btOrders, btCustomers, btMenu, btLogout;

            HomeToolBar() {
                super();
                btOrders = new Button();
                btCustomers = new Button();
                btMenu = new Button();
                btLogout = new Button();

                controls = new LinkedList<>();
                controls.addAll(Arrays.asList(
                        btOrders,
                        btCustomers,
                        btMenu,
                        btLogout
                ));

                build();

                hookEvents();

                refresh();
            }

            @Override
            protected void build() {
                super.getChildren().addAll(
                        new HBox(
                                16,
                                btOrders,
                                btCustomers,
                                btMenu,
                                btLogout
                        )
                );

                btOrders.setText("Orders");
                btCustomers.setText("Customers");
                btMenu.setText("Menu");
                btLogout.setText("Log Out");

                super.setMaxWidth(ViewPane.MAX_WIDTH);
            }

            @Override
            protected void hookEvents() {

            }

            @Override
            protected void refresh() {

            }

            @Override
            protected void lockControls() {
                super.lockControls();
            }

            @Override
            protected void unlockControls() {
                super.unlockControls();
            }
        }

        /**
         * The main content of the program pane. Changes according to the task, which can be specified via the toolbar.
         * All subclasses must conform to the maximum size constants defined in this class.
         */
        static abstract class ViewPane extends StandardPane {
            static final Integer MAX_WIDTH = 960;
            static final Integer MAX_HEIGHT = 480;

            protected ViewPane() {
                super();
            }

            /**
             * Displays all active orders. There will be a button bar at the bottom for editing, deletion,
             * completion, and view history.
             */
            static class OrdersViewPane extends ViewPane {
                /**
                 * Allows the user to modify the aspects of the given order.
                 */
                static class OrderEditorViewPane extends ViewPane {
                    /**
                     * Allows the user to create or modify an existing Pizza Item.
                     */
                    static class PizzaBuilderViewPane extends ViewPane {
                        /**
                         * An easy way to represent the selection of toppings. Basically a CheckBox wrapper for a
                         * Topping.
                         */
                        static class ToppingOption extends CheckBox {
                            final Item<ItemCategory.Topping> represented;

                            ToppingOption(Item<ItemCategory.Topping> represented, Boolean selected) {
                                super(represented.getName());
                                this.represented = represented;
                                super.setSelected(selected);
                            }
                        }

                        final Button btConfirm, btCancel;
                        final ListView<ToppingOption> lvToppings;
                        final ChoiceBox<String> chcbxPizzaBase;

                        final Item<ItemCategory.Pizza> represented;

                        PizzaBuilderViewPane(Item<ItemCategory.Pizza> represented) {
                            super();

                            lvToppings = new ListView<>();
                            chcbxPizzaBase = new ChoiceBox<>();
                            btConfirm = new Button();
                            btCancel = new Button();

                            this.represented = represented;

                            super.controls.addAll(Arrays.asList(
                                    lvToppings,
                                    chcbxPizzaBase,
                                    btCancel,
                                    btConfirm
                            ));

                            build();

                            hookEvents();

                            refresh();
                        }

                        @Override
                        protected void build() {
                            super.getChildren().addAll(
                                    chcbxPizzaBase,
                                    lvToppings,
                                    new HBox(
                                            16,
                                            btConfirm,
                                            btCancel
                                    ),
                                    txtStatus
                            );

                            btCancel.setText("Cancel");
                            btConfirm.setText("Confirm");
                        }

                        @Override
                        protected void hookEvents() {

                        }

                        @Override
                        protected void refresh() {
                            chcbxPizzaBase.getItems().add(represented.getCategory().getBase().getName());
                            for (Item<ItemCategory.Topping> topping : represented.getCategory().getToppings())
                                lvToppings.getItems().add(new ToppingOption(topping, true));
                        }

                        @Override
                        protected void lockControls() {
                            super.lockControls();
                        }

                        @Override
                        protected void unlockControls() {
                            super.unlockControls();
                        }
                    }

                    /**
                     * A cell for a ListView. Represents the quantity of a specified beverage as a labelled
                     * TextField, with the beverage name as the label and the quantity as the contents of the TextField.
                     */
                    static class BeverageOption extends HBox {
                        final TextField tfQty;
                        final Text txtName;

                        BeverageOption(Item<ItemCategory.Beverage> represented) {
                            tfQty = new TextField();
                            txtName = new Text(represented.getName());

                            super.setSpacing(32);
                            super.getChildren().addAll(
                                    txtName,
                                    tfQty
                            );
                        }

                        Integer getQty() {
                            Integer ret = 0;
                            try {
                                ret = Integer.parseInt(tfQty.getText());
                            } catch (NumberFormatException nfex) {
                                nfex.printStackTrace();
                            }
                            return ret;
                        }
                    }

                    static class PizzaViewCell extends StandardPane {
                        final Item<ItemCategory.Pizza> pizzaItem;
                        final Text name;
                        final Button btRemove, btEdit;

                        PizzaViewCell(Item<ItemCategory.Pizza> pizzaItem) {
                            super();

                            this.pizzaItem = pizzaItem;
                            name = new Text();
                            btRemove = new Button();
                            btEdit = new Button();

                            super.controls.addAll(Arrays.asList(
                                    btEdit,
                                    btRemove
                            ));

                            build();

                            hookEvents();

                            refresh();
                        }

                        @Override
                        protected void build() {
                            super.getChildren().addAll(
                                    new HBox(
                                            4,
                                            name,
                                            btEdit,
                                            btRemove
                                    )
                            );

                            btEdit.setText("Edit");
                            btRemove.setText("X");

                            name.setText(pizzaItem.getName());
                        }

                        @Override
                        protected void hookEvents() {

                        }

                        @Override
                        protected void refresh() {

                        }

                        @Override
                        protected void lockControls() {
                            super.lockControls();
                        }

                        @Override
                        protected void unlockControls() {
                            super.unlockControls();
                        }
                    }

                    final ListView<PizzaViewCell> pizzas;
                    final ListView<BeverageOption> beverages;
                    final Button btCancel, btConfirm;
                    final Order order;

                    OrderEditorViewPane(Order order) {
                        super();
                        pizzas = new ListView<>();
                        beverages = new ListView<>();
                        btCancel = new Button();
                        btConfirm = new Button();

                        this.order = order;

                        super.controls.addAll(Arrays.asList(btConfirm, btCancel));

                        build();

                        hookEvents();

                        refresh();
                    }

                    @Override
                    protected void build() {
                        super.getChildren().addAll(
                                pizzas,
                                beverages,
                                new HBox(
                                        16,
                                        btConfirm,
                                        btCancel
                                ),
                                txtStatus
                        );

                        btConfirm.setText("Confirm");
                        btCancel.setText("Cancel");
                    }

                    @Override
                    protected void hookEvents() {

                    }

                    @Override
                    protected void refresh() {
                        if (order != null)
                            for (Item item : order.getItems()) {
                                if (item.getCategory() instanceof ItemCategory.Pizza)
                                    pizzas.getItems().add(new PizzaViewCell(item));
                                if (item.getCategory() instanceof ItemCategory.Beverage)
                                    for (BeverageOption beverageOption : beverages.getItems())
                                        if (beverageOption.txtName.getText().equals(item.getName()))
                                            beverageOption.tfQty.setText(
                                                    Integer.toString(Integer.parseInt(beverageOption.tfQty.getText()) + 1)
                                            );
                            }
                    }

                    @Override
                    protected void lockControls() {
                        super.lockControls();
                    }

                    @Override
                    protected void unlockControls() {
                        super.unlockControls();
                    }
                }

                static class OrderTile extends StandardPane {
                    static class ItemViewCell extends Text {
                        final Item represented;

                        ItemViewCell(Item represented) {
                            super("\t");
                            this.represented = represented;

                            if (represented.getCategory() instanceof ItemCategory.Pizza) {
                                StringBuilder pizzaString = new StringBuilder(super.getText());
                                pizzaString.append(represented.getName());
                                pizzaString.append(":\n\t\t");
                                pizzaString.append(((ItemCategory.Pizza) represented.getCategory()).getBase().getName());
                                for (Item<ItemCategory.Topping> toppingItem : ((ItemCategory.Pizza) represented
                                        .getCategory()).getToppings()) {
                                    pizzaString.append("\n\t\t");
                                    pizzaString.append(toppingItem.getName());
                                }
                                super.setText(pizzaString.toString());
                            }

                            if (represented.getCategory() instanceof ItemCategory.Beverage) {
                                StringBuilder drinkString = new StringBuilder(super.getText());
                                drinkString.append(represented.getName());
                                super.setText(drinkString.toString());
                            }
                        }
                    }

                    final Text txtCustomerName, txtOrderNumber, txtLVOrders;
                    final ListView<ItemViewCell> lvItems;
                    final Button btEdit, btCancel, btComplete;
                    final Order order;

                    Integer number;

                    OrderTile(Order order, Integer number) {
                        super();

                        this.order = order;
                        this.txtCustomerName = new Text();
                        this.txtOrderNumber = new Text();
                        this.txtLVOrders = new Text();
                        this.btEdit = new Button();
                        this.btCancel = new Button();
                        this.btComplete = new Button();
                        this.lvItems = new ListView<>();

                        this.number = number;

                        super.controls.addAll(Arrays.asList(
                                btCancel,
                                btComplete,
                                btEdit
                        ));

                        build();

                        hookEvents();

                        refresh();
                    }

                    @Override
                    protected void build() {
                        super.getChildren().addAll(
                                txtOrderNumber,
                                txtCustomerName,
                                txtLVOrders,
                                lvItems,
                                new HBox(
                                        4,
                                        btComplete,
                                        btEdit,
                                        btCancel
                                )
                        );

                        btComplete.setText("Done");
                        btEdit.setText("Edit");
                        btCancel.setText("Delete");
                    }

                    @Override
                    protected void hookEvents() {

                    }

                    @Override
                    protected void refresh() {
                        txtOrderNumber.setText("Order " + number.toString());
                        txtCustomerName.setText(order.getCustomer().getName());
                        txtLVOrders.setText("Items:");
                        for (Item item : order.getItems())
                            lvItems.getItems().add(new ItemViewCell(item));
                    }

                    @Override
                    protected void lockControls() {
                        super.lockControls();
                    }

                    @Override
                    protected void unlockControls() {
                        super.unlockControls();
                    }
                }

                final ArrayList<Order> orders;
                final TilePane tlpnOrders;
                final Button btRefresh;

                OrdersViewPane(Order[] orders) {
                    super();

                    this.orders = new ArrayList<>(Arrays.asList(orders));
                    tlpnOrders = new TilePane();
                    btRefresh = new Button();

                    super.controls.addAll(Arrays.asList(
                            btRefresh
                    ));

                    build();

                    hookEvents();

                    refresh();
                }

                @Override
                protected void build() {
                    super.getChildren().addAll(
                            tlpnOrders,
                            btRefresh,
                            txtStatus
                    );

                    btRefresh.setText("Refresh");

                    tlpnOrders.setPrefColumns(4);
                    tlpnOrders.setPrefRows(2);
                    tlpnOrders.setPrefTileHeight(360);
                    tlpnOrders.setPrefTileWidth(240);

                    tlpnOrders.setPrefHeight(
                            tlpnOrders.getPrefRows() * tlpnOrders.getPrefTileHeight()
                    );

                    super.setPrefHeight(
                            tlpnOrders.getPrefRows() * tlpnOrders.getPrefTileHeight()
                            + btRefresh.getHeight()
                            + super.getSpacing() * 2
                            + 4
                    );

                    super.setPrefWidth(
                            tlpnOrders.getPrefColumns() * tlpnOrders.getPrefTileWidth()
                    );
                }

                @Override
                protected void hookEvents() {

                }

                @Override
                protected void refresh() {
                    tlpnOrders.getChildren().clear();

                    for (int i = 0; i < 8 && i < orders.size(); i++)
                        tlpnOrders.getChildren().add(new OrderTile(orders.get(i), i));
                }

                @Override
                protected void unlockControls() {
                    super.unlockControls();
                    for (Node node : tlpnOrders.getChildren())
                        if (node instanceof OrderTile)
                            ((OrderTile) node).unlockControls();
                }

                @Override
                protected void lockControls() {
                    super.lockControls();
                    for (Node node : tlpnOrders.getChildren())
                        if (node instanceof OrderTile)
                            ((OrderTile) node).lockControls();
                }
            }

            /**
             * Shows all customers in the database. Only names and numbers. Allows for deletion, creation, and viewing.
             */
            static class CustomersViewPane extends ViewPane {
                @Override
                protected void build() {

                }

                @Override
                protected void hookEvents() {

                }

                @Override
                protected void refresh() {

                }

                @Override
                protected void lockControls() {
                    super.lockControls();
                }

                @Override
                protected void unlockControls() {
                    super.unlockControls();
                }
            }

            /**
             * Displays the menu.
             */
            static class MenuViewPane extends ViewPane {
                @Override
                protected void build() {

                }

                @Override
                protected void hookEvents() {

                }

                @Override
                protected void refresh() {

                }

                @Override
                protected void lockControls() {
                    super.lockControls();
                }

                @Override
                protected void unlockControls() {
                    super.unlockControls();
                }
            }

            @Override
            protected void lockControls() {
                super.lockControls();
            }

            @Override
            protected void unlockControls() {
                super.unlockControls();
            }
        }

        final HomeToolBar homeToolBar;

        ViewPane viewPane;

        MainPane() {
            homeToolBar = new HomeToolBar();
            viewPane = new ViewPane.OrdersViewPane(new Order[] {});

            build();

            hookEvents();

            refresh();
        }

        @Override
        protected void build() {
            super.getChildren().addAll(
                    homeToolBar,
                    viewPane,
                    txtStatus
            );
        }

        @Override
        protected void hookEvents() {

        }

        @Override
        protected void refresh() {
            super.getChildren().clear();
            build();
        }

        @Override
        protected void lockControls() {
            super.lockControls();
            homeToolBar.lockControls();
            viewPane.lockControls();
        }

        @Override
        protected void unlockControls() {
            super.unlockControls();
            homeToolBar.unlockControls();
            viewPane.unlockControls();
        }
    }
}
