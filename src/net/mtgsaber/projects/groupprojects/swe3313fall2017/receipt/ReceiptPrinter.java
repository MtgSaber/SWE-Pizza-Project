package net.mtgsaber.projects.groupprojects.swe3313fall2017.receipt;

import javax.swing.*;
import java.awt.*;

/**
 * Generates a string representation of a receipt and prints it.
 * @author Andrew Arnold
 * @since 0.0
 */
public class ReceiptPrinter {
    public static void print(String text) {
        JTextPane jtp = new JTextPane();
        jtp.setBackground(Color.white);
        jtp.setText(text);
        boolean show = true;
        try {
            jtp.print(null, null, show, null, null, show);
        } catch (java.awt.print.PrinterException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) { print("Hello World!"); }
}
