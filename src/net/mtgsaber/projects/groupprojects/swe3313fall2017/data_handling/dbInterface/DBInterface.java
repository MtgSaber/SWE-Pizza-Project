package net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling.dbInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Facilitates easy and simple manipulation of the databases used.
 * @author Andrew Arnold
 * @since 0.0
 */
public class DBInterface {
    private final String DB_LOC;
    private Connection conDB;

    public DBInterface (String DB_LOC) {
        this.DB_LOC = DB_LOC;
        try {
            conDB = DriverManager.getConnection("jdbc:ucanaccess://" + DB_LOC);
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public String[] getAvailableTables() {
        ArrayList<String> results = new ArrayList<>();
        try {
            ResultSet rs = conDB.getMetaData().getTables(null, null, "%", null);
            while (rs.next())
                results.add(rs.getString(3));
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        return results.toArray(new String[results.size()]);
    }

    public String[] getAvailableColumns(String table)
            throws Exception {
        if (Arrays.binarySearch(getAvailableTables(), table) < 0) throw new Exception("No such table: " + table);
        ArrayList<String> results = new ArrayList<>();
        try {
            ResultSet rs = conDB.getMetaData().getColumns(null, null, table, null);
            while (rs.next())
                results.add(rs.getString(4));
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        return results.toArray(new String[results.size()]);
    }

    public String[][] getValues(String table, String[] columns)
            throws Exception {
        String[] actualColumns = getAvailableColumns(table);
        /*
        for (String column : actualColumns)
            System.out.println("Asserted Column Name:\t" + column);
        /*
        for (String column : columns)
            if (Arrays.binarySearch(actualColumns, column) < 0) throw new Exception("No such column: " + column);
            */
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        ArrayList<String> columnResults;
        ResultSet rs;
        for (String column : columns) {
            columnResults = new ArrayList<>();
            rs = conDB.createStatement().executeQuery("Select " + column.toUpperCase() + " FROM " + table);
            while (rs.next())
                columnResults.add(rs.getString(1));
            results.add(columnResults);
        }
        ArrayList<String[]> resultsPhase2 = new ArrayList<>();
        for (ArrayList<String> list : results)
            resultsPhase2.add(list.toArray(new String[list.size()]));
        return resultsPhase2.toArray(new String[resultsPhase2.size()][]);
    }

    /* PENDING RESULTS FROM DBTest1
    public Boolean writeValues(String table, String column, String[] values) {

    }

    public Boolean writeValues(String table, String[] column, String[][] values) {

    }

    public Boolean close() {

    }
    */
}
