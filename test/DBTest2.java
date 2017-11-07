import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.dbInterface.DBInterface;

/**
 * Author: Andrew Arnold (11/6/2017)
 */
public class DBTest2 {
    public static void main(String[] args) {
        try {
            DBInterface dbInterface =
                    new DBInterface("D:\\Programming\\Projects\\SWE-Pizza-Project\\Database\\PizzaProjectDatabase.accdb");
            System.out.println("Starting Database Test 2: Writing Rows");
            System.out.println("Writing to table Customers");
            System.out.println(dbInterface.writeValues("Customers", new String[]{
                    "TestCustomer2",
                    "314159265",
                    "5678_Bar_Court",
                    "1",
                    "1"
            }));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
