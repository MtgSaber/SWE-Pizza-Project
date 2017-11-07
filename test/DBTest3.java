import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.dbInterface.DBInterface;

/**
 * Author: Andrew Arnold (11/6/2017)
 */
public class DBTest3 {
    public static void main(String[] args) {
        try {
            DBInterface dbInterface =
                    new DBInterface("D:\\Programming\\Projects\\SWE-Pizza-Project\\Database\\PizzaProjectDatabase.accdb");
            System.out.println("Starting Database Test 3: Deleting Rows");
            System.out.println("\tDeleting ID 1 from Customers");
            System.out.println("\t\t" + dbInterface.delete("Customers", 1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
