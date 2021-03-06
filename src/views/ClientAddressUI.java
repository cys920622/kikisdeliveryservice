package views;

import controllerBeans.ClientAddressBean;
import controllerBeans.ClientsBean;
import entityClasses.ClientAddress;
import entityClasses.Clients;
import javafx.scene.layout.GridPane;
import net.miginfocom.swing.MigLayout;
//import sun.awt.VariableGridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by stellafang. on 2016-03-29.
 */
public class ClientAddressUI extends JPanel{
    private JTextField clIDField = new JTextField(11);
    private JTextField fnameField = new JTextField(30);
    private JTextField lnameField = new JTextField(30);
    private JTextField PCField = new JTextField(7);
    private JTextField house_numField = new JTextField(11);
    private JTextField phone_numField = new JTextField(12);
    private JTextField countryField = new JTextField(20);
    private JTextField provinceField = new JTextField(2);
    private JTextField cityField = new JTextField(20);
    private JTextField street_nameField = new JTextField(30);
    private JTextField updatedIDField = new JTextField(6);

    private JButton createButton = new JButton("Create");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");
    private JButton previousButton = new JButton("Previous");
    private JButton nextStepButton = new JButton("Next Step");
    private JButton makePaymentButton = new JButton("Make Payment");
    private JButton clearButton = new JButton("Clear");

    private int clID = new Random().nextInt(Integer.MAX_VALUE);
    private int randdID = 0;

    private int tabNumber;
    private String tabName;

    private ClientAddressBean bean = new ClientAddressBean();

    public ClientAddressUI(int tabNumber) {
        this.tabNumber = tabNumber;

        if (tabNumber == 1) {
            tabName = "Enter Sender Info";
        }
        else if (tabNumber == 2) {
            tabName = "Enter Receiver Info";
        }
        else if (tabNumber == 3) {
            tabName = "Update a Client Info";
        }
        else if (tabNumber == 4) {
            tabName = "Update the Receiver Info";
        }

        setBorder(new TitledBorder(
                new EtchedBorder(), tabName));
        setLayout(new BorderLayout(1, 1));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);

        if(tabNumber == 3) {
            add(initUpdateDelivery(), BorderLayout.SOUTH);
        }
        clIDField.setText(String.valueOf(0));
        house_numField.setText(String.valueOf(0));
        updatedIDField.setText(String.valueOf(0));
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        if (tabNumber == 1) {
            panel.add(createButton);
            createButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
            panel.add(firstButton);
            firstButton.addActionListener(new ButtonHandler());
            panel.add(lastButton);
            lastButton.addActionListener(new ButtonHandler());
            panel.add(nextButton);
            nextButton.addActionListener(new ButtonHandler());
            panel.add(previousButton);
            previousButton.addActionListener(new ButtonHandler());
            panel.add(nextStepButton);
            nextStepButton.addActionListener(new ButtonHandler());
        }
        else if (tabNumber == 2) {
            panel.add(createButton);
            createButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
            panel.add(firstButton);
            firstButton.addActionListener(new ButtonHandler());
            panel.add(lastButton);
            lastButton.addActionListener(new ButtonHandler());
            panel.add(nextButton);
            nextButton.addActionListener(new ButtonHandler());
            panel.add(previousButton);
            previousButton.addActionListener(new ButtonHandler());
        }
        else if(tabNumber == 3) {
            panel.add(updateButton);
            updateButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
            panel.add(deleteButton);
            deleteButton.addActionListener(new ButtonHandler());
            panel.add(firstButton);
            firstButton.addActionListener(new ButtonHandler());
            panel.add(lastButton);
            lastButton.addActionListener(new ButtonHandler());
            panel.add(nextButton);
            nextButton.addActionListener(new ButtonHandler());
            panel.add(previousButton);
            previousButton.addActionListener(new ButtonHandler());
        }
        else if (tabNumber == 4) {
            panel.add(updateButton);
            updateButton.addActionListener(new ButtonHandler());
            panel.add(clearButton);
            clearButton.addActionListener(new ButtonHandler());
            panel.add(deleteButton);
            deleteButton.addActionListener(new ButtonHandler());
            panel.add(firstButton);
            firstButton.addActionListener(new ButtonHandler());
            panel.add(lastButton);
            lastButton.addActionListener(new ButtonHandler());
            panel.add(nextButton);
            nextButton.addActionListener(new ButtonHandler());
            panel.add(previousButton);
            previousButton.addActionListener(new ButtonHandler());

        }


        return panel;
    }

    private JPanel initUpdateDelivery() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Enter Delivery ID to update"), "align label");
        panel.add(updatedIDField, "wrap");
        panel.add(nextStepButton, "wrap");
        nextStepButton.addActionListener(new ButtonHandler());
        return panel;
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Client ID"), "align label");
        panel.add(clIDField, "wrap");
        panel.add(new JLabel("First name"), "align label");
        panel.add(fnameField, "wrap");
        panel.add(new JLabel("Last name"), "align label");
        panel.add(lnameField, "wrap");
        panel.add(new JLabel("Postal code"), "align label");
        panel.add(PCField, "wrap");
        panel.add(new JLabel("House number"), "align label");
        panel.add(house_numField, "wrap");
        panel.add(new JLabel("Phone number"), "align label");
        panel.add(phone_numField, "wrap");
        panel.add(new JLabel("Country"), "align label");
        panel.add(countryField, "wrap");
        panel.add(new JLabel("Province"), "align label");
        panel.add(provinceField, "wrap");
        panel.add(new JLabel("City"), "align label");
        panel.add(cityField, "wrap");
        panel.add(new JLabel("Street name"), "align label");
        panel.add(street_nameField, "wrap");
        return panel;
    }


    public Boolean checkFieldData() {
        try {
            if ( clIDField.getText().length() > 11) {
                JOptionPane.showMessageDialog(null, "ClID can only be 11 numbers long");
                return false;
            }
            Integer.parseInt(clIDField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The Client ID can only be a number");
            return false;
        }

        try {
            if ( house_numField.getText().length() > 11) {
                JOptionPane.showMessageDialog(null, "ClID can only be max 11 numbers long");
                return false;
            }
            Integer.parseInt(house_numField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The house number can only be a number");
            return false;
        }


        if (fnameField.getText().length()>30) {
            JOptionPane.showMessageDialog(null, "First name can only be max 30 characters long");
            return false;
        }
//        if (fnameField.getText().matches(".*\\d+.*")) {
//            JOptionPane.showMessageDialog(null, "First Name cannot include numbers");
//        }

        if (lnameField.getText().length()>30) {
            JOptionPane.showMessageDialog(null, "Last name can only be max 30 characters long");
            return false;
        }
        if (PCField.getText().trim().length()>7) {
            JOptionPane.showMessageDialog(null, "Postal Code can only be 7 characters long");
            return false;
        }

        if (countryField.getText().trim().length()>20) {
            JOptionPane.showMessageDialog(null, "Postal Code can only be max 20 characters long");
            return false;
        }

        if (provinceField.getText().length() > 2) {
            JOptionPane.showMessageDialog(null, "Province can only be 2 letters long");
            return false;
        }

        if (provinceField.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "City can only be max 20 characters long");
            return false;
        }

        if (provinceField.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Street name can only be max 30 characters long");
            return false;
        }
        return true;
    }

    public ClientAddress getFieldData() {
        ClientAddress ca = new ClientAddress();
        if (checkFieldData()) {
            ca.setClID(Integer.parseInt(clIDField.getText()));
            ca.setFname(fnameField.getText());
            ca.setLname(lnameField.getText());
            ca.setPC(PCField.getText());
            ca.setHouse_num(Integer.parseInt(house_numField.getText()));
            ca.setPhone_num(phone_numField.getText());
            ca.setCountry(countryField.getText());
            ca.setProvince(provinceField.getText());
            ca.setCity(cityField.getText());
            ca.setStreet_name(street_nameField.getText());
            ca.setdID(Integer.parseInt(updatedIDField.getText()));
            return ca;
        } else return null;
    }


    private void setFieldData(ClientAddress ca) {
        clIDField.setText(String.valueOf(ca.getClID()));
        fnameField.setText(ca.getFname());
        lnameField.setText(ca.getLname());
        PCField.setText(ca.getPC());
        house_numField.setText(String.valueOf(ca.getHouse_num()));
        phone_numField.setText(ca.getPhone_num());
        countryField.setText(ca.getCountry());
        provinceField.setText(ca.getProvince());
        cityField.setText(ca.getCity());
        street_nameField.setText(ca.getStreet_name());
        street_nameField.setText(ca.getStreet_name());
        updatedIDField.setText(String.valueOf(ca.getdID()));

    }

    private boolean isEmptyFieldData() {
        return (clIDField.getText().trim().equals("0")
                || fnameField.getText().trim().isEmpty()
                || lnameField.getText().trim().isEmpty()
                || PCField.getText().trim().isEmpty()
                || house_numField.getText().trim().equals("0")
                || phone_numField.getText().trim().isEmpty()
                || countryField.getText().trim().isEmpty()
                || provinceField.getText().trim().isEmpty()
                || cityField.getText().trim().isEmpty()
                || street_nameField.getText().trim().isEmpty());
    }


    public void setclID(int clID) {
        this.clID = clID;
    }

    private void setrandDID(int dID) {
        this.randdID = dID;

    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientAddress ca = getFieldData();

            switch (e.getActionCommand()) {
                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in remaining fields");
                        break;
                    }
                    if (bean.create(ca) != null) {
                        JOptionPane.showMessageDialog(null,
                                "New Client: " + ca.getFname() + " " + ca.getLname() +
                                        " with ClientID " + String.valueOf(ca.getClID())
                                        + " was created successfully.");
                        createButton.setText("Create");
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Please check if you have entered a unique Client ID, and a postal code and house number from an existing address");
                        break;
                    }
                case "Create":
                    ca.setClID(clID);
                    ca.setFname("");
                    ca.setLname("");
                    ca.setPC("");
                    ca.setHouse_num(0);
                    ca.setPhone_num("");
                    ca.setCountry("");
                    ca.setProvince("");
                    ca.setCity("");
                    ca.setStreet_name("");
                    setFieldData(ca);
                    createButton.setText("Save");
                    break;

                case "Clear":
                    ca.setClID(clID);
                    ca.setFname("");
                    ca.setLname("");
                    ca.setPC("");
                    ca.setHouse_num(0);
                    ca.setPhone_num("");
                    ca.setCountry("");
                    ca.setProvince("");
                    ca.setCity("");
                    ca.setStreet_name("");
                    setFieldData(ca);
                    break;

                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in missing info");
                        break;
                    }
                    if (bean.update(ca) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Client: " + ca.getFname() + " " + ca.getLname() +
                                        " with ClientID " + String.valueOf(ca.getClID())
                                        + " was edited successfully.");
                        break;
                    }

                case "Delete":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in missing info");
                        break;
                    }
                    if (!bean.delete(ca)) {
                        JOptionPane.showMessageDialog(null,
                                "Client was successfully deleted.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                "Delete all Deliveries associated with " +
                                        "Client in order " +
                                        "to delete Client.");
                    }

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
                    break;
                case "Next Step":
                    if(isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Please fill in missing info");
                        break;
                    }

                    if (tabNumber == 1){
                        JFrame tab1_1Frame = new JFrame();
                        tab1_1Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        tab1_1Frame.setSize(1500, 780);
                        JComponent tab1_1 = new JPanel();

                        JPanel panel1 = new JPanel();

                        setrandDID(new Random().nextInt((999999 - 0) + 1));

                        ClerkUI showCenters = new ClerkUI("SELECT * from center", "Valid Centers");
                        tab1_1.add(panel1.add(showCenters));


                        final int randreceiver = new Random().nextInt((999999 - 0) + 1);

                        DeliveryUI deliveryui1 = new DeliveryUI(false, "select * from delivery");
                        ClientAddressUI clientAddressUI1 = new ClientAddressUI(2);
                        ParcelUI parcelUI1 = new ParcelUI(false, "select * from parcel");

                        deliveryui1.setRanddID(randdID);
                        deliveryui1.setSender(ca.getClID());
                        deliveryui1.setReceiver(randreceiver);
                        deliveryui1.setPreferredSize(new Dimension(620, 300));
                        tab1_1.add(panel1.add(deliveryui1));
                        panel1.setAlignmentY(TOP_ALIGNMENT);


                        JPanel panel2 = new JPanel();


                        clientAddressUI1.setclID(randreceiver);
                        clientAddressUI1.setPreferredSize(new Dimension(620, 415));
                        tab1_1.add(panel2.add(clientAddressUI1));


                        parcelUI1.setdID(randdID); //give parcel same dID as delivery
                        parcelUI1.setPreferredSize(new Dimension(400, 400));
                        tab1_1.add(panel2.add(parcelUI1));

                        tab1_1.add(panel2.add(makePaymentButton));
                        makePaymentButton.addActionListener(new ButtonHandler());
                        panel2.setAlignmentY(BOTTOM_ALIGNMENT);

                        tab1_1Frame.add(tab1_1);
                        tab1_1Frame.setVisible(true);
                    }
                    else if (tabNumber == 3) {
                        if (ca.getdID()==0) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter a valid delivery ID");
                            break;
                        }
                        JFrame tab1_1Frame = new JFrame();
                        tab1_1Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        tab1_1Frame.setSize(1500, 780);
                        JComponent tab1_1 = new JPanel();

                        JPanel panel1 = new JPanel();

                        ClerkUI showCenters = new ClerkUI("SELECT * from center", "Valid Centers");
                        tab1_1.add(panel1.add(showCenters));

                        final int randreceiver = new Random().nextInt((999999 - 0) + 1);

                        DeliveryUI deliveryui = new DeliveryUI(true, "SELECT * " +
                                "FROM delivery " +
                                "WHERE delivery.dID = '"+ca.getdID()+"'");
                        deliveryui.setRanddID(ca.getdID());
                        deliveryui.setSender(ca.getClID());
                        deliveryui.setReceiver(randreceiver);
                        deliveryui.setPreferredSize(new Dimension(620, 300));
                        tab1_1.add(panel1.add(deliveryui));
                        panel1.setAlignmentY(TOP_ALIGNMENT);


                        JPanel panel2 = new JPanel();

                        ClientAddressUI clientAddressUI = new ClientAddressUI(4);
                        //1 is for tab1, able to create new sender/receiver
                        clientAddressUI.setclID(randreceiver);
                        clientAddressUI.setPreferredSize(new Dimension(620, 500));
                        tab1_1.add(panel2.add(clientAddressUI));

                        ParcelUI parcelUI = new ParcelUI(true, "SELECT * " +
                                "FROM parcel " +
                                "WHERE parcel.dID = '"+ca.getdID()+"'");
                        parcelUI.setdID(ca.getdID()); //give parcel same dID as delivery
                        parcelUI.setPreferredSize(new Dimension(400, 400));
                        tab1_1.add(panel2.add(parcelUI));


                        tab1_1Frame.add(tab1_1);
                        tab1_1Frame.setVisible(true);
                    }
                    break;
                case "Make Payment":
//                    if(clientAddressUI1.isEmptyFieldData() &&
//                            deliveryui1.isEmptyFieldData()&&
//                            parcelUI1.isEmptyFieldData()) {
//                        JOptionPane.showMessageDialog(null,
//                                "Please fill in missing info");
//                        break;
//                    }
                    JFrame tab1_2Frame = new JFrame();
                    tab1_2Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    tab1_2Frame.setSize(1000, 500);
                    JComponent tab1_2 = new JPanel();

                    JPanel panel1 = new JPanel();

                    CashUI cashUI = new CashUI(true);
                    cashUI.setdID(randdID);
                    cashUI.setPreferredSize(new Dimension(400, 400));

                    CreditCardUI creditCardUI = new CreditCardUI(true);
                    creditCardUI.setdID(randdID);
                    creditCardUI.setPreferredSize(new Dimension(400, 400));

                    tab1_2.add(panel1.add(cashUI));
                    tab1_2.add(panel1.add(creditCardUI));

                    panel1.setAlignmentY(CENTER_ALIGNMENT);

                    tab1_2Frame.add(tab1_2);
                    tab1_2Frame.setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid command");
            }
        }
    }
}
