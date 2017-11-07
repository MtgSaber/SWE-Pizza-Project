import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.dbInterface.DBInterface;

/**
 * Author: Andrew Arnold (11/6/2017)
 */
public class DBTest4 {
    public static void main(String[] args) {
        try {
            DBInterface dbInterface =
                    new DBInterface("D:\\Programming\\Projects\\SWE-Pizza-Project\\Database\\PizzaProjectDatabase.accdb");
            System.out.println("Starting Database Test 4: Updating Rows");
            System.out.println("\tUpdating ID 2 from Table Customers; Column name to JavaCorpTest4");
            System.out.println("\t\t" + dbInterface.update(
                        "Customers",
                        2,
                        new String[]{"CustomerName"},
                        new String[]{"JavaCorpTest4"}
                    ));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
