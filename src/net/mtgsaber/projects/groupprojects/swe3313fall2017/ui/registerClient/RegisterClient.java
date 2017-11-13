package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.registerClient;

import javafx.stage.Stage;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.dbInterface.DBInterface;
import net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.Client;

/**
 * The main class for the Register Client program GUI. Must implement all abstract methods from <code>Client</code>.
 * Other classes can be added to the package to support this, but this must be the main class to which the backend
 * will make its invocations.
 * @author (name of whoever writes this).
 * @since 0.0
 * @see net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.Client
 */
public class RegisterClient extends Client {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DBInterface dbInterface = new DBInterface
                ("D:\\Programming\\Projects\\SWE-Pizza-Project\\Database\\PizzaProjectDatabase.accdb");
        RegisterPanes.LogonPane logonPane = new RegisterPanes.LogonPane(primaryStage, dbInterface);
        logonPane.stage.show();
    }
}
