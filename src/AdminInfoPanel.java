import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminInfoPanel {

    private JFrame frame;
    private JPanel mainPanel, upperPanel;
    private JLabel logoLabel, instructionLabel;
    private JButton logoutButton;
    private JScrollPane scrollPane;
    private JTextArea shipmentTextArea;
    private Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);

    private java.util.List<Shipment> shipments;
    String PIN = null;

    public AdminInfoPanel(java.util.List<Shipment> shipments, String pin) {

        this.shipments = shipments;
        PIN = pin;

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

        instructionLabel = new JLabel("See the details of the lockers in the ParcelSwift:");
        Font adminFont = new Font("Comic Sans MS", Font.BOLD, 24);
        instructionLabel.setFont(adminFont);
        instructionLabel.setBounds(55, 80, 600, 50);
        mainPanel.add(instructionLabel);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 130, 650, 255);
        mainPanel.add(scrollPane);

        shipmentTextArea = new JTextArea();
        shipmentTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        shipmentTextArea.setEditable(false);
        scrollPane.setViewportView(shipmentTextArea);

        for (Shipment shipment : shipments) {
            String details = "Number: " + shipment.number + ", Size: " + shipment.size +
                    ", Code: " + shipment.code + ", TelNumOfSender: " + shipment.telNumOfSender +
                    ", TelNumOfReceiver: " + shipment.telNumOfReceiver + "\n";
            shipmentTextArea.append(details);
        }

            logoutButton = new JButton("Log out");
            logoutButton.setBounds(20, 400, 150, 50);
            logoutButton.setBackground(new Color(43, 43, 43));
            logoutButton.setForeground(Color.WHITE);
            logoutButton.setFont(appFont);
            mainPanel.add(logoutButton);

        }


    private void ShowNewWindow() {

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminLoginPanel(shipments);
            }
        });

    }

    public static void main(String[] args) {

    }
}
