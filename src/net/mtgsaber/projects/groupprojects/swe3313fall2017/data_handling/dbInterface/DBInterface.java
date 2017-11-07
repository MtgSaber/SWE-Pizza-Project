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
        if (!Arrays.asList(getAvailableTables()).contains(table))
            throw new Exception("No such table:\t" + table);
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
        for (String column : columns)
            if (!Arrays.asList(actualColumns).contains(column))
                throw new Exception("No such column:\t" + column);
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

    /**
     *
     * @param table table name
     * @param values list of ordered values that make up the row. length must equal available columns minus 1.
     * @return success of write request
     * @throws Exception If the table specified doesn't exist in this Database; If <code>values</code> doesn't
     * conform to @param.
     */
    public Boolean writeValues(String table, String[] values)
            throws Exception {
        if (!Arrays.asList(getAvailableTables()).contains(table))
            throw new Exception("No such table:\t" + table);
        if (values.length != getAvailableColumns(table).length - 1)
            throw new Exception("Invalid column count:\t" + values.length + "\n\tnumber of values must equal " +
                    "available columns minus 1 (due to primary key column being auto-generated).");
        StringBuilder columnSelection = new StringBuilder();
        StringBuilder valuesSelection = new StringBuilder();
        String[] actualColumns = getAvailableColumns(table);
        for (int i = 1; i < actualColumns.length; i++) {
            if (i > 1) {
                columnSelection.append(',');
                valuesSelection.append(',');
            }
            valuesSelection.append('\'');
            columnSelection.append(actualColumns[i]);
            valuesSelection.append(values[i-1]);
            valuesSelection.append('\'');
        }
        System.out.println("Asserted columns:\t" + columnSelection.toString());
        System.out.println("Asserted values:\t" + valuesSelection.toString());
        return conDB.createStatement().execute("INSERT INTO " + table + "(" + columnSelection.toString() + ") VALUES" +
                " (" + valuesSelection + ")");
    }

    public Boolean delete(String table, Integer rowID)
            throws Exception {
        if (!Arrays.asList(getAvailableTables()).contains(table))
            throw new Exception("No such table:\t" + table);
        if (!Arrays.asList(getValues(table, new String[] {"ID"})[0]).contains(rowID.toString()))
            throw new Exception("No such ID:\t" + rowID.toString());
        return conDB.createStatement().execute("DELETE FROM " + table + " WHERE ID = " + rowID.toString());
    }

    public Boolean update(String table, Integer rowID, String[] columns, String[] values)
            throws Exception {
        if (values.length == 0) return false;
        if (columns.length != values.length)
            throw new Exception("columns list length not equal values list width:\t[" + columns.length + " != " +
                    values.length + "]");
        if (!Arrays.asList(getAvailableTables()).contains(table))
            throw new Exception("No such table:\t" + table);
        if (!Arrays.asList(getValues(table, new String[] {"ID"})[0]).contains(rowID.toString()))
            throw new Exception("No such ID:\t" + rowID.toString());

        StringBuilder assignmentsSelection = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            if (i > 0) assignmentsSelection.append("\' ");
            assignmentsSelection.append(columns[i]);
            assignmentsSelection.append(" = \'");
            assignmentsSelection.append(values[i]);
            assignmentsSelection.append('\'');
        }

        return conDB.createStatement().execute("UPDATE " + table + " SET " + assignmentsSelection.toString() +
                " WHERE ID = '" + rowID.toString() + "'");
    }

    public void close() throws SQLException {
        conDB.close();
    }
}
