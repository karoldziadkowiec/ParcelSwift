import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendPackageSize {

    private JFrame frame;
    private JPanel mainPanel, upperPanel;
    private JLabel logoLabel, instructionLabel;
    private JButton sButton, mButton, lButton, xlButton, backButton;
    private Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);

    private java.util.List<Shipment> shipments;
    String phoneNumber = null, code = null;

    public SendPackageSize(java.util.List<Shipment> shipments, String phoNum, String cod) {

        this.shipments = shipments;
        phoneNumber = phoNum;
        code = cod;

        initializeFrame();
        addComponents();
        ShowNewWindow();
        frame.setVisible(true);
    }

    private void initializeFrame() {

        frame = new JFrame("ParcelSwift");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 700, 500);
        mainPanel.setBackground(new Color(230, 207, 5));
        frame.add(mainPanel);

        upperPanel = new JPanel();
        upperPanel.setLayout(null);
        upperPanel.setBounds(0, 0, 700, 85);
        upperPanel.setBackground(new Color(43, 43, 43));
        mainPanel.add(upperPanel);

        logoLabel = new JLabel("ParcelSwift");
        logoLabel.setForeground(Color.WHITE);
        Font logoFont = new Font("Comic Sans MS", Font.BOLD, 50);
        logoLabel.setFont(logoFont);
        logoLabel.setBounds(205, 20, 350, 50);
        upperPanel.add(logoLabel);

    }

    private void addComponents() {

        ImageIcon parcelIcon = new ImageIcon("parcel.png");
        JLabel logoImageLabel = new JLabel(parcelIcon);
        logoImageLabel.setBounds(285, 90, parcelIcon.getIconWidth(), parcelIcon.getIconHeight());
        mainPanel.add(logoImageLabel);

        instructionLabel = new JLabel("Select the package size.");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(225, 215, 450, 50);
        mainPanel.add(instructionLabel);

        sButton = new JButton("S");
        sButton.setLayout(null);
        sButton.setBounds(260, 270, 80, 80);
        sButton.setBackground(new Color(43, 43, 43));
        sButton.setForeground(Color.WHITE);
        sButton.setFont(appFont);
        mainPanel.add(sButton);

        mButton = new JButton("M");
        mButton.setLayout(null);
        mButton.setBounds(350, 270, 80, 80);
        mButton.setBackground(new Color(43, 43, 43));
        mButton.setForeground(Color.WHITE);
        mButton.setFont(appFont);
        mainPanel.add(mButton);

        lButton = new JButton("L");
        lButton.setLayout(null);
        lButton.setBounds(260, 360, 80, 80);
        lButton.setBackground(new Color(43, 43, 43));
        lButton.setForeground(Color.WHITE);
        lButton.setFont(appFont);
        mainPanel.add(lButton);

        xlButton = new JButton("XL");
        xlButton.setLayout(null);
        xlButton.setBounds(350, 360, 80, 80);
        xlButton.setBackground(new Color(43, 43, 43));
        xlButton.setForeground(Color.WHITE);
        xlButton.setFont(appFont);
        mainPanel.add(xlButton);

        backButton = new JButton("Back");
        backButton.setBounds(20, 390, 150, 50);
        backButton.setBackground(new Color(43, 43, 43));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(appFont);
        mainPanel.add(backButton);

    }

    private void ShowNewWindow() {

        String s = "S", m = "M", l = "L", xl = "XL";

        sButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleButtonAction(s);
            }
        });

        mButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleButtonAction(m);
            }
        });

        lButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleButtonAction(l);
            }
        });

        xlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleButtonAction(xl);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SendPackageCode(shipments, phoneNumber);
            }
        });

    }

    private void handleButtonAction(String size) {

        boolean nullSizeFound = false;

        for (Shipment shipment : shipments) {
            if (shipment.size == null) {
                nullSizeFound = true;
                break;
            }
        }

        if (nullSizeFound) {
            JOptionPane.showMessageDialog(frame, "All available sizes are already selected. Please choose another size.");
        }
        else {
            boolean suitableShipmentFound = false;

            for (Shipment shipment : shipments) {
                if (shipment.size != null && shipment.size.equals(size) && shipment.code == null) {
                    suitableShipmentFound = true;
                    break;
                }
            }

            if (suitableShipmentFound) {
                frame.dispose();
                new SendPackageLocker(shipments, phoneNumber, code, size);
            } else {
                JOptionPane.showMessageDialog(frame, "No available lockers for the selected size. Please choose another size.");
                frame.dispose();
                new SendPackageSize(shipments, phoneNumber, code);
            }

        }

    }


    public static void main(String[] args) {

    }

}
