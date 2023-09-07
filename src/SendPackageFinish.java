import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SendPackageFinish {
    private JFrame frame;
    private JPanel mainPanel, upperPanel;
    private JLabel logoLabel, instructionLabel;
    private JButton finishButton;
    private Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);

    private List<Shipment> shipments;
    private String phoneNumber, code, size;

    public SendPackageFinish(List<Shipment> shipments, String phoNum, String cod, String sizee) {
        this.shipments = shipments;
        phoneNumber = phoNum;
        code = cod;
        size = sizee;

        initializeFrame();
        addComponents();
        openNewWindow();
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

        instructionLabel = new JLabel("The parcel is inside.");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(235, 265, 450, 50);
        mainPanel.add(instructionLabel);

        instructionLabel = new JLabel("The package has been sent successfully.");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(135, 295, 450, 50);
        mainPanel.add(instructionLabel);

        finishButton = new JButton("Finish");
        finishButton.setBounds(235, 360, 200, 50);
        finishButton.setBackground(new Color(43, 43, 43));
        finishButton.setForeground(Color.WHITE);
        finishButton.setFont(appFont);
        mainPanel.add(finishButton);
    }

    private void openNewWindow() {
        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Shipment shipment : shipments) {
                    if (shipment.size.equals(size) && shipment.code == null) {
                        shipment.code = code;
                        shipment.telNumOfSender = phoneNumber;
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
