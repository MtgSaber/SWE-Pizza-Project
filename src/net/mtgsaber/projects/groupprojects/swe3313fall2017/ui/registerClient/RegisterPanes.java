package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.registerClient;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.Item;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.ItemCategory;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.Order;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.Events;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.Panes;

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
        protected void hookEvents(Events eventsDefinition) {

        }

        @Override
        protected void refresh() {

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
            }

            @Override
            protected void build() {
                super.getChildren().addAll(
                        new HBox(
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
            protected void hookEvents(Events eventsDefinition) {

            }

            @Override
            protected void refresh() {

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
            static class OrderesViewPane extends ViewPane {
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

                        PizzaBuilderViewPane(Item<ItemCategory.Pizza> represented) {
                            super();

                            lvToppings = new ListView<>();
                            chcbxPizzaBase = new ChoiceBox<>();
                            btConfirm = new Button();
                            btCancel = new Button();

                            build();
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
                                    )
                            );
                        }

                        @Override
                        protected void hookEvents(Events eventsDefinition) {

                        }

                        @Override
                        protected void refresh() {

                        }
                    }

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

                    final ListView<Item<ItemCategory.Pizza>> pizzas;
                    final ListView<BeverageOption> beverages;

                    OrderEditorViewPane(Order order) {
                        super();
                        pizzas = new ListView<>();
                        beverages = new ListView<>();

                        build();
                    }

                    @Override
                    protected void build() {

                    }

                    @Override
                    protected void hookEvents(Events eventsDefinition) {

                    }

                    @Override
                    protected void refresh() {

                    }
                }

                @Override
                protected void build() {

                }

                @Override
                protected void hookEvents(Events eventsDefinition) {

                }

                @Override
                protected void refresh() {

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
                protected void hookEvents(Events eventsDefinition) {

                }

                @Override
                protected void refresh() {

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
                protected void hookEvents(Events eventsDefinition) {

                }

                @Override
                protected void refresh() {

                }
            }
        }

        @Override
        protected void build() {

        }

        @Override
        protected void hookEvents(Events eventsDefinition) {

        }

        @Override
        protected void refresh() {

        }
    }
}
