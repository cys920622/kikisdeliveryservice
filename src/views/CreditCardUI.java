package views;

import controllerBeans.CreditCardBean;
import entityClasses.CreditCard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class CreditCardUI extends JPanel {
    private JTextField amountField = new JTextField(12);
    private JTextField payIDField = new JTextField(3);
    private JTextField onDateField = new JTextField(10);
    private JTextField dIDField = new JTextField(6);
    private JTextField cc_numField = new JTextField(30);
    private JTextField csvField = new JTextField(3);
    private JTextField nameField = new JTextField(30);
    private JTextField expiryField = new JTextField(5);
    private JTextField typeField = new JTextField(255);

    private JButton createButton = new JButton("New...");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");

    private CreditCardBean bean = new CreditCardBean();

    public CreditCardUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Parcel details"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);

        amountField.setText(String.valueOf(0.0));
        payIDField.setText(String.valueOf(0));
        dIDField.setText(String.valueOf(0));
        csvField.setText(String.valueOf(0));

    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(createButton);
        createButton.addActionListener(new ButtonHandler());
        panel.add(updateButton);
        updateButton.addActionListener(new ButtonHandler());
        panel.add(deleteButton);
        deleteButton.addActionListener(new ButtonHandler());
        panel.add(firstButton);
        firstButton.addActionListener(new ButtonHandler());
        panel.add(lastButton);
        lastButton.addActionListener(new ButtonHandler());
        panel.add(nextButton);
        nextButton.addActionListener(new ButtonHandler());
        return panel;
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Amount"), "align label");
        panel.add(amountField, "wrap");
        panel.add(new JLabel("Pay ID"), "align label");
        panel.add(payIDField, "wrap");
        panel.add(new JLabel("Paid on Date"), "align label");
        panel.add(onDateField, "wrap");
        panel.add(new JLabel("Delivery ID"), "align label");
        panel.add(dIDField, "wrap");
        panel.add(new JLabel("Credit Card Number"), "align label");
        panel.add(cc_numField, "wrap");
        panel.add(new JLabel("CSV"), "align label");
        panel.add(csvField, "wrap");
        panel.add(new JLabel("Name"), "align label");
        panel.add(nameField, "wrap");
        panel.add(new JLabel("Expiry Date"), "align label");
        panel.add(expiryField, "wrap");
        panel.add(new JLabel("Type"), "align label");
        panel.add(typeField, "wrap");
        return panel;
    }

    private CreditCard getFieldData() {
        CreditCard cc = new CreditCard();
        cc.setPayID(Integer.parseInt(payIDField.getText()));
        cc.setAmount(Float.parseFloat(amountField.getText()));
        cc.setOnDate(onDateField.getText());
        cc.setdID(Integer.parseInt(dIDField.getText()));
        cc.setCredit_card_num(cc_numField.getText());
        cc.setCSV(Integer.parseInt(csvField.getText()));
        cc.setName(nameField.getText());
        cc.setExpiry_date(expiryField.getText());
        cc.setType(typeField.getText());
        return cc;
    }

    private void setFieldData(CreditCard cc) {
        payIDField.setText(String.valueOf(cc.getPayID()));
        amountField.setText(String.valueOf(cc.getAmount()));
        onDateField.setText(cc.getOnDate());
        dIDField.setText(String.valueOf(cc.getdID()));
        cc_numField.setText(cc.getCredit_card_num());
        csvField.setText(String.valueOf(cc.getCSV()));
        nameField.setText(cc.getName());
        expiryField.setText(cc.getExpiry_date());
        typeField.setText(cc.getType());
    }

    private boolean isEmptyFieldData() {
        return (payIDField.getText().trim().isEmpty()
                && amountField.getText().trim().isEmpty()
                && onDateField.getText().trim().isEmpty()
                && dIDField.getText().trim().isEmpty()
                && cc_numField.getText().trim().isEmpty()
                && csvField.getText().trim().isEmpty()
                && nameField.getText().trim().isEmpty()
                && expiryField.getText().trim().isEmpty()
                && typeField.getText().trim().isEmpty());

    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CreditCard cc = getFieldData();
            switch (e.getActionCommand()) {
                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create empty record");
                    }
                    if (bean.create(cc) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Credit Card transaction " + String.valueOf(cc.getPayID()) +
                                        " for delivery" + String.valueOf(cc.getdID())
                                        + " was successful");
                        createButton.setText("New...");
                        break;
                    }
                case "New...":
                    cc.setdID(0);
                    cc.setOnDate("");
                    cc.setAmount(0);
                    cc.setdID(0);
                    cc.setCredit_card_num("");
                    cc.setCSV(0);
                    cc.setName("");
                    cc.setExpiry_date("");
                    cc.setType("");
                    createButton.setText("Save");
                    break;

                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't update empty record");
                    }
                    if (bean.update(cc) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Credit Cash transaction " + String.valueOf(cc.getPayID()) +
                                        " for delivery" + String.valueOf(cc.getdID())
                                        + " was updated");
                    }
                    break;
                case "Delete":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't delete empty record");
                    }
                    cc = bean.getCurrent();
                    bean.delete();
                    JOptionPane.showMessageDialog(null,
                            "Cash transaction " + String.valueOf(cc.getPayID()) +
                                    " for delivery" + String.valueOf(cc.getdID())
                                    + " was deleted");
                    break;
                case "First":
                    setFieldData(bean.moveFirst());
                    break;
                case "Last":
                    setFieldData(bean.moveLast());
                    break;
                case "Next":
                    setFieldData(bean.moveNext());
                    break;
                case "Previous":
                    setFieldData(bean.movePrevious());
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invaild command");
            }
        }
    }

}
