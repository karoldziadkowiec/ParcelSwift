import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel {

    JFrame frame;
    JPanel mainPanel, upperPanel;
    JLabel logoLabel, instructionLabel;
    JButton sendPackageButton, pickUpPackageButton, adminPanelButton;
    Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);

    private java.util.List<Shipment> shipments = new java.util.ArrayList<>();

    MainPanel(java.util.List<Shipment> shipments) {

        this.shipments = shipments;

        initializeFrame();
        addComponents();
        ShowNewWindow();
        frame.setVisible(true);

    }

    public void initializeFrame() {

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

    }

    private void addComponents() {

        logoLabel = new JLabel("ParcelSwift");
        logoLabel.setForeground(Color.WHITE);
        Font logoFont = new Font("Comic Sans MS", Font.BOLD, 50);
        logoLabel.setFont(logoFont);
        logoLabel.setBounds(205, 20, 350, 50);
        upperPanel.add(logoLabel);

        ImageIcon logoIcon = new ImageIcon("logo.png");
        JLabel logoImageLabel = new JLabel(logoIcon);
        logoImageLabel.setBounds(260, 75, logoIcon.getIconWidth(), logoIcon.getIconHeight());
        mainPanel.add(logoImageLabel);

        instructionLabel = new JLabel("Select the service want to use.");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(175, 225, 450, 50);
        mainPanel.add(instructionLabel);

        sendPackageButton = new JButton("Send package");
        sendPackageButton.setLayout(null);
        sendPackageButton.setBounds(240, 280, 220, 50);
        sendPackageButton.setBackground(new Color(43, 43, 43));
        sendPackageButton.setForeground(Color.WHITE);
        sendPackageButton.setFont(appFont);
        mainPanel.add(sendPackageButton);

        pickUpPackageButton = new JButton("Pick up package");
        pickUpPackageButton.setLayout(null);
        pickUpPackageButton.setBounds(240, 340, 220, 50);
        pickUpPackageButton.setBackground(new Color(43, 43, 43));
        pickUpPackageButton.setForeground(Color.WHITE);
        pickUpPackageButton.setFont(appFont);
        mainPanel.add(pickUpPackageButton);

        adminPanelButton = new JButton("Admin panel");
        adminPanelButton.setLayout(null);
        adminPanelButton.setBounds(260, 400, 180, 30);
        adminPanelButton.setBackground(Color.WHITE);
        adminPanelButton.setForeground(new Color(43, 43, 43));
        adminPanelButton.setFont(appFont);
        mainPanel.add(adminPanelButton);

    }

    private void ShowNewWindow() {
        sendPackageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SendPackageTelNum(shipments);
            }
        });
        pickUpPackageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new PickUpPackageTelNum();
            }
        });
        adminPanelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminLoginPanel(shipments);
            }
        });
    }

    public static void main(String[] args) {



    }

}
