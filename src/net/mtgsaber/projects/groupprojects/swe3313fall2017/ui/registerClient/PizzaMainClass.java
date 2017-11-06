package net.mtgsaber.projects.groupprojects.swe3313fall2017.ui.registerClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
// --- Main Class--//
public class PizzaMainClass {
  
   // --- Java Main Class ---- //
   public static void main(String[] args)
   {
       // --- set Jframe ---//
      JFrame frame = new PizzaCostFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Pizza Price GUI");
      frame.setVisible(true);
      frame.setBackground(Color.RED);
   }
}
   // --- Pizza Cost Frame --//
class PizzaCostFrame extends JFrame {
  private JFrame frame;
private JPanel pizzaPanel, centerPanel, pricePanel, checkBoxPanel, radioButtonPanel;
private final int FRAME_WIDTH = 500;
private final int FRAME_HEIGHT = 300;
private ButtonGroup buttonGroup;
// --- Pizza size radio button ----//
  private JRadioButton smallButton, mediumButton, largeButton;
// -- Pizza size checkbox ---- //
  private JCheckBox peppCheckBox, mushCheckBox,peppyPannerCheckBox,mexiconCheckBox;
private JTextField priceTextField;
private double price = 0.0;
private double topPrice = 0.0;
private double showPrice = 0.0;
  private ActionListener listener = new CostListener();
// ==== Pizza Cost Frame ====== //
public PizzaCostFrame() {
pizzaPanel = new JPanel();
pizzaPanel.setLayout(new BorderLayout(10, 10));
createPizzaSizeRadioButtonPanel();
createPizzaTypeCheckBoxCheckBoxPanel();
createPricePanel();
createCenterPanel();
pizzaPanel.add(centerPanel, BorderLayout.CENTER);
pizzaPanel.add(pricePanel, BorderLayout.SOUTH);
frame = new JFrame("Pizza Cost ");
frame.add(pizzaPanel, BorderLayout.CENTER);
frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.pack();
frame.setLocation(100, 100);
frame.setVisible(true);
frame.setBackground(Color.RED);
}
// -=======Create a Panel Pizza Sizes ======= //
  private void createPizzaSizeRadioButtonPanel() {
radioButtonPanel = new JPanel();
radioButtonPanel.setLayout(new GridLayout(3, 1));
radioButtonPanel.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
buttonGroup = new ButtonGroup();
smallButton = new JRadioButton(" Small ");
buttonGroup.add(smallButton);
smallButton.addActionListener(listener);
radioButtonPanel.add(smallButton);
mediumButton = new JRadioButton(" Medium ");
buttonGroup.add(mediumButton);
mediumButton.addActionListener(listener);
radioButtonPanel.add(mediumButton);
largeButton = new JRadioButton(" Large ");
buttonGroup.add(largeButton);
largeButton.addActionListener(listener);
radioButtonPanel.add(largeButton);
}
// ====== Create a Panel for Pizza Types ======== //

private void createPizzaTypeCheckBoxCheckBoxPanel() {
checkBoxPanel = new JPanel();
peppyPannerCheckBox = new JCheckBox(" Peppy Panner Pizza ");
peppyPannerCheckBox.addActionListener(listener);
checkBoxPanel.add(peppyPannerCheckBox);
mexiconCheckBox = new JCheckBox(" Mexican Pizza ");
mexiconCheckBox.addActionListener(listener);
checkBoxPanel.add(mexiconCheckBox);
checkBoxPanel.setLayout(new GridLayout(4, 1));
peppCheckBox = new JCheckBox(" Pepperoni Pizza ");
peppCheckBox.addActionListener(listener);
checkBoxPanel.add(peppCheckBox);
mushCheckBox = new JCheckBox(" Mushrooms Pizza");
mushCheckBox.addActionListener(listener);
checkBoxPanel.add(mushCheckBox);
}
private void createPricePanel() {
pricePanel = new JPanel();
pricePanel.add(new JLabel("Cost:"));
priceTextField = new JTextField(8);
priceTextField.setFont(new Font("Arial", Font.ITALIC, 15));
priceTextField.setEditable(false);
priceTextField.setForeground(Color.white);
priceTextField.setBackground(pricePanel.getBackground());
priceTextField.setDisabledTextColor(Color.white);
priceTextField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
pricePanel.add(priceTextField);
priceTextField.setText(" n/a Price");
}
private void createCenterPanel() {
centerPanel = new JPanel();
centerPanel.add(radioButtonPanel);
centerPanel.add(checkBoxPanel);
}

//------Listener for costing Pizza ---//

private class CostListener implements ActionListener {
public void actionPerformed(ActionEvent event) {
topPrice = 0;
if (smallButton.isSelected()) {
price = 20;
} else if (mediumButton.isSelected()) {
price = 30;
} else if (largeButton.isSelected()) {
price = 40;
}
  if (peppCheckBox.isSelected()) {
topPrice = 5;
}
else if (mushCheckBox.isSelected()) {
topPrice = 7;
}
else if (peppyPannerCheckBox.isSelected()) {
topPrice = 8;
}
   else if (mexiconCheckBox.isSelected()) {
topPrice = 9;
   }
  else if (peppCheckBox.isSelected() && (mushCheckBox.isSelected()))
   {
      topPrice = 10;
}
else if (peppCheckBox.isSelected() && (peppyPannerCheckBox.isSelected()))
       {
      topPrice = 11;
       }
else if (mushCheckBox.isSelected() && (peppyPannerCheckBox.isSelected()))
       {
      topPrice = 11;
       }
   else if (peppCheckBox.isSelected() && (mushCheckBox.isSelected()) &&
(peppyPannerCheckBox.isSelected())&&(mexiconCheckBox.isSelected())) {
   topPrice =10;

}
EventQueue.invokeLater(new Runnable() {
@Override
public void run() {
showPrice = price + topPrice;
priceTextField.setText(" $" + showPrice);
System.out.println("Price displayed");
}
});
}
}
public static void main(String[] args) {
PizzaCostFrame pizza = new PizzaCostFrame();
}
}
