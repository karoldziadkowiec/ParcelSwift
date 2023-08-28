import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PickUpPackageFinish {

    private JFrame frame;
    private JPanel mainPanel, upperPanel;
    private JLabel logoLabel, instructionLabel;
    private JButton finishButton;
    private Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);

    private java.util.List<Shipment> shipments;
    private String phoneNumber, code;

    public PickUpPackageFinish(List<Shipment> shipments, String phoNum, String cod) {

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

        ImageIcon openedIcon = new ImageIcon("parcelInside.png");
        JLabel logoImageLabel = new JLabel(openedIcon);
        logoImageLabel.setBounds(245, 90, openedIcon.getIconWidth(), openedIcon.getIconHeight());
        mainPanel.add(logoImageLabel);

        instructionLabel = new JLabel("The parcel is outside.");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(235, 265, 450, 50);
        mainPanel.add(instructionLabel);

        instructionLabel = new JLabel("The package has been picked up successfully.");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(115, 295, 500, 50);
        mainPanel.add(instructionLabel);

        finishButton = new JButton("Finish");
        finishButton.setBounds(235, 360, 200, 50);
        finishButton.setBackground(new Color(43, 43, 43));
        finishButton.setForeground(Color.WHITE);
        finishButton.setFont(appFont);
        mainPanel.add(finishButton);
    }

    private void ShowNewWindow() {

        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Shipment shipment : shipments) {
                    if (shipment.code != null && shipment.code.equals(code)) {
                        shipment.telNumOfReceiver = phoneNumber;
                        shipment.code = null;
                        shipment.telNumOfSender = null;
                        shipment.telNumOfReceiver = null;
                        break;
                    }
                }

                for (Shipment shipment : shipments) {
                    System.out.println("Number: " + shipment.number + ", Size: " + shipment.size +
                            ", Code: " + shipment.code + ", TelNumOfSender: " + shipment.telNumOfSender +
                            ", TelNumOfReceiver: " + shipment.telNumOfReceiver);
                }

                frame.dispose();
                new MainPanel(shipments);
            }
        });

    }


    public static void main(String[] args) {

    }
}
