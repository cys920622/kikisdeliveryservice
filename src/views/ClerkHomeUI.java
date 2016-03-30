package views;

import controllerBeans.ClerkBean;
import entityClasses.Address;
import net.miginfocom.layout.Grid;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by stellafang. on 2016-03-27.
 */
public class ClerkHomeUI extends JPanel{
//    private JButton browseClients = new JButton("Browse Clients");
//    private JButton editClients = new JButton("Create or Update Clients");
//    private JButton browseDeliveries = new JButton("Browse Deliveries");
//    private JButton editDeliveries= new JButton("Create or Update Deliveries");
    private ClerkBean bean = new ClerkBean();

    public ClerkHomeUI() {
        //new GridLayout(1,1);
        setBorder(new TitledBorder(
                new EtchedBorder(), "Clerk view"));
//        setLayout(new BorderLayout(5,5));
//        initButtons().setVisible(true);
//        add(initButtons(), BorderLayout.CENTER);

        // NEW TAB
        JTabbedPane jtab = new JTabbedPane();

        //TAB1: START A NEW DELIVERY WITH A NEW OR EXISTING CLIENT
        //TAB1: CREATE NEW CLIENT OR GRAB EXISTING CLIENT AND ADD NEW DELIVERY
        JComponent tab1 = new JPanel();
        tab1.setSize(1200,600);
        ClerkUI createClient = new ClerkUI("SELECT * " +
                "FROM clients " +
                "LEFT JOIN address " +
                "ON clients.PC=address.PC " +
                "and clients.house_num=address.house_num"
                , "Clients Info");
        createClient.setSize(createClient.getWidth(), createClient.getHeight());
        tab1.add(createClient);
        tab1.add(new ClientAddressUI(1));
        jtab.add("Start a New Delivery", tab1);
        // ------------------------------------------------------------------------

        //TAB2: UPDATE EXISTING CLIENTS
        JComponent tab2 = new JPanel();
        ClerkUI editClient = new ClerkUI("SELECT * " +
                "FROM clients " +
                "LEFT JOIN address " +
                "ON clients.PC=address.PC " +
                "and clients.house_num=address.house_num"
                , "Clients Info");
        editClient.setSize(editClient.getWidth(), editClient.getHeight());
        tab2.add(editClient);
        tab2.add(new ClientAddressUI(2));
        jtab.add("Update a Client", tab2);


        //TAB3: BROWSE DELIVERIES AND PARCELS
        JComponent browseDeliveries = new ClerkUI(
                "SELECT * " +
                        "FROM delivery " +
                        "LEFT JOIN parcel " +
                        "ON delivery.dID=parcel.dID"
                , "Browse Deliveries and its Parcels");
        jtab.add("browse Deliveries", browseDeliveries);
        // ------------------------------------------------------------------------


        //ADD TAB: BROWSE PAYMENTS
        JComponent everythingPayments = new JPanel();
        everythingPayments.setLayout(new GridLayout(1,1));
        everythingPayments.setSize(300,100);
        JComponent browseCreditPayments = new ClerkUI(
                "SELECT amount, payID, onDate, credit_card.dID, credit_card_num, CSV, name, expiry_date, credit_card.type " +
                        "FROM credit_card " +
                        "LEFT JOIN delivery " +
                        "ON credit_card.dID=delivery.dID"
                , "Credit Card Payments");
        everythingPayments.add(browseCreditPayments);
        JComponent browseCashPayments = new ClerkUI(
                "SELECT amount, payID, onDate, cash.dID " +
                        "FROM cash " +
                        "LEFT JOIN delivery " +
                        "ON cash.dID=delivery.dID"
                , "Cash Payments");
        everythingPayments.add(browseCashPayments);
        jtab.add("browse Payments", everythingPayments);


        add(jtab, BorderLayout.CENTER);
        jtab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }


//    private JPanel initButtons() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
//        panel.add(browseClients);
//        browseClients.addActionListener(new ButtonHandler());
//        panel.add(editClients);
//        editClients.addActionListener(new ButtonHandler());
//        panel.add(browseDeliveries);
//        browseDeliveries.addActionListener(new ButtonHandler());
//        panel.add(editDeliveries);
//        editDeliveries.addActionListener(new ButtonHandler());
//        return panel;
//    }


//    private class ButtonHandler implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JFrame f = new JFrame();
//            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            f.getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
//            switch (e.getActionCommand()) {
//                case "Browse Clients":
//                    ClerkUI clerk = new ClerkUI();
//                    JTable table1 = clerk.initTable("SELECT * FROM clients LEFT JOIN " +
//                            "address ON clients.PC=address.PC and clients.house_num=address.house_num");
//                    table1.setAutoCreateRowSorter(true);
//                    //table1.getAutoResizeMode();
//                    //table1.getAutoscrolls();
//                    f.add(clerk);
//                    break;
//                case "Create or Update Clients":
//                    ClientsUI clientsUI = new ClientsUI();
//                    add(clientsUI);
//                    break;
//                case "Browse Deliveries":
//
//                    break;
//                case "Create or Update Deliveries":
//                    break;
//
//                default:
//                    JOptionPane.showMessageDialog(null,
//                            "Invalid command");
//            }
//            f.setSize(700, 800);
//            f.setVisible(true);
//        }
//    }


}
