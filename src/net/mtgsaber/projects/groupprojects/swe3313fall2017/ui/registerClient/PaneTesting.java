package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.registerClient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
}
