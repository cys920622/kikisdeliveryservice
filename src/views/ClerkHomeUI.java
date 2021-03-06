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

    public ClerkHomeUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Clerk view"));
        setLayout(new BorderLayout(5, 5));

        // NEW TAB
        JTabbedPane jtab = new JTabbedPane();

        //TAB1: START A NEW DELIVERY WITH A NEW OR EXISTING CLIENT
        //TAB1: CREATE NEW CLIENT OR GRAB EXISTING CLIENT AND ADD NEW DELIVERY
        JComponent tab1 = new JPanel();
        tab1.setSize(1200,600);
        ClerkUI createClientTable = new ClerkUI("SELECT * " +
                "FROM clients " +
                "LEFT JOIN address " +
                "ON clients.PC=address.PC " +
                "and clients.house_num=address.house_num"
                , "Sender Info");
        createClientTable.setSize(createClientTable.getWidth(), createClientTable.getHeight());
        tab1.add(createClientTable);
        tab1.add(new ClientAddressUI(1));
        jtab.add("Start a New Delivery", tab1);
        // ------------------------------------------------------------------------

        //TAB2: UPDATE EXISTING CLIENTS
        JComponent tab2 = new JPanel();
        ClerkUI editClientTable = new ClerkUI("SELECT * " +
                "FROM clients " +
                "LEFT JOIN address " +
                "ON clients.PC=address.PC " +
                "and clients.house_num=address.house_num " +
                "LEFT JOIN delivery " +
                "ON clients.clID=delivery.sender_ID or " +
                "clients.clID=delivery.receiver_ID"
                , "Clients Info");
        editClientTable.setSize(editClientTable.getWidth(), editClientTable.getHeight());
        tab2.add(editClientTable);
        tab2.add(new ClientAddressUI(3));
        tab2.setSize(1200,700);
        jtab.add("Update a Client and associated Deliveries", tab2);


        //TAB3: BROWSE DELIVERIES AND PARCELS
        JComponent tab3 = new JPanel();
        ClerkUI browseDeliveries = new ClerkUI(
                "SELECT * " +
                        "FROM delivery " +
                        "LEFT JOIN parcel " +
                        "ON delivery.dID=parcel.dID"
                , "Browse Deliveries and its Parcels");
        tab3.add(browseDeliveries);
        tab3.add(new DeliveryParcelUI());
        tab3.setSize(1200, 800);
        jtab.add("browse Deliveries", tab3);

        // ------------------------------------------------------------------------


        //ADD TAB: BROWSE PAYMENTS
        JComponent tab4 = new JPanel();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));

        JComponent browseCreditPayments = new ClerkUI(
                "SELECT amount, payID, onDate, credit_card.dID, credit_card_num, CSV, name, expiry_date, credit_card.type " +
                        "FROM credit_card " +
                        "LEFT JOIN delivery " +
                        "ON credit_card.dID=delivery.dID"
                , "Credit Card Payments");
        browseCreditPayments.setSize(browseCreditPayments.getWidth(), browseCreditPayments.getHeight());
        panel.add(browseCreditPayments);

        JComponent browseCashPayments = new ClerkUI(
                "SELECT amount, payID, onDate, cash.dID " +
                        "FROM cash " +
                        "LEFT JOIN delivery " +
                        "ON cash.dID=delivery.dID"
                , "Cash Payments");
        browseCashPayments.setSize(browseCashPayments.getWidth(), browseCashPayments.getHeight());
        panel.add(browseCashPayments);

        tab4.add(panel);
        tab4.add(new CashCreditUI());
        jtab.add("browse Payments", tab4);

        add(jtab, BorderLayout.CENTER);
        jtab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

}
