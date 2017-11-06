import net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.dbInterface.DBInterface;

/**
 * Author: Andrew Arnold (11/1/2017)
 */
public class DBTest1 {
    public static void main(String[] args) {
        try {
            System.out.println("Starting Database Test...");
            DBInterface dbInterface = new DBInterface("D:\\Programming\\Projects\\SWE-Pizza-Project\\Database\\PizzaProjectDatabase.accdb");
            System.out.println("Available Tables:");
            for (String table : dbInterface.getAvailableTables())
                System.out.println("\tTable:\t" + table);
            for (String string : dbInterface.getAvailableTables()) {
                System.out.println("Table:\t" + string);
                for (String string1 : dbInterface.getAvailableColumns(string)) {
                    System.out.println("\tColumn:\t" + string1);

                    for (String[] string2 : dbInterface.getValues(string, new String[] {string1})) {
                        for (int i = 0; i < string2.length; i++)
                            System.out.println("\t\t[" + i + "]:\t" + string2[i]);
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
