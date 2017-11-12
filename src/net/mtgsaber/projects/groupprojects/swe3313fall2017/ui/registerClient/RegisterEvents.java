package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.registerClient;

import net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.Events;

/**
 * Author: Andrew Arnold (10/18/2017)
 */
public class RegisterEvents extends Events {
    static class LogonPane_Events {
        void actionBTLogIn(RegisterPanes.LogonPane logonPane) {

        }
    }

    static class MainPane_Events {
        static class HomeToolBar_Events {
            void actionBTOrders(RegisterPanes.MainPane.HomeToolBar homeToolBar) {

            }

            void actionBTCustomers(RegisterPanes.MainPane.HomeToolBar homeToolBar) {

            }

            void actionBTMenu(RegisterPanes.MainPane.HomeToolBar homeToolBar) {

            }

            void actionBTLogout(RegisterPanes.MainPane.HomeToolBar homeToolBar) {

            }
        }

        static class ViewPane_Events {
            static class OrdersViewPane_Events {
                static class OrderEditorViewPane_Events {
                    static class PizzaBuilderViewPane_Events {
                        static void actionBTConfirm(
                                RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane
                                        .PizzaBuilderViewPane pizzaBuilderViewPane
                        ) {}
                        static void actionBTCancel(
                                RegisterPanes.MainPane.ViewPane.OrdersViewPane.OrderEditorViewPane
                                        .PizzaBuilderViewPane pizzaBuilderViewPane
                        ) {}
                    }

                    static class BeverageOption_Events {}

                    static class PizzaViewCell_Events{}
                }

                static class OrderTile_Events {}
            }

            static class CustomersViewPane_Events {}

            static class MenuViewPane_Events {}
        }
    }
}
