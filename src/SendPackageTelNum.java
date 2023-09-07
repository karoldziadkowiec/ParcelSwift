import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SendPackageTelNum implements ActionListener {
    private JFrame frame;
    private JPanel mainPanel, upperPanel, leftPanel, leftInsidePanel;
    private JLabel logoLabel, instructionLabel;
    private JButton backButton, confirmButton;
    private JButton[] numberButtons = new JButton[10]; // numbers 0-9
    JButton deleteButton, clearButton;
    private JTextField telNumTextField;
    private Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);

    private java.util.List<Shipment> shipments;

    public SendPackageTelNum(java.util.List<Shipment> shipments) {
        this.shipments = shipments;

        initializeFrame();
        addComponents();
        openNewWindow();
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("ParcelSwift");
        frame.setLayout(null);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());

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

        leftPanel = new JPanel();
        leftPanel.setLayout(null); // Changed to 4 rows, 3 columns
        leftPanel.setBounds(0, 85, 350, 415);
        leftPanel.setBackground(Color.WHITE);
        mainPanel.add(leftPanel);

        leftInsidePanel = new JPanel();
        leftInsidePanel.setLayout(new GridLayout(4, 3, 10, 10)); // Changed to 4 rows, 3 columns
        leftInsidePanel.setBounds(40, 40, 270, 300);
        leftInsidePanel.setBackground(Color.WHITE);
        leftPanel.add(leftInsidePanel);
    }

    private void addComponents() {
        ImageIcon mobilePhoneIcon = new ImageIcon("mobilePhone.png");
        JLabel logoImageLabel = new JLabel(mobilePhoneIcon);
        logoImageLabel.setBounds(470, 95, mobilePhoneIcon.getIconWidth(), mobilePhoneIcon.getIconHeight());
        mainPanel.add(logoImageLabel);

        telNumTextField = new JTextField();
        telNumTextField.setBounds(378, 240, 280, 50);
        telNumTextField.setBackground(Color.WHITE);
        telNumTextField.setFont(appFont);
        mainPanel.add(telNumTextField);

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        clearButton.setFont(appFont);
        clearButton.setFocusable(false);
        clearButton.setBounds(30, 110, 105, 50);
        clearButton.setBackground(new Color(136, 23, 23));
        clearButton.setForeground(Color.WHITE);

        deleteButton = new JButton("<-");
        deleteButton.addActionListener(this);
        deleteButton.setFont(appFont);
        deleteButton.setFocusable(false);
        deleteButton.setBounds(245, 110, 105, 50);
        deleteButton.setBackground(new Color(43, 43, 43));
        deleteButton.setForeground(Color.WHITE);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(appFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(77, 77, 77));
            numberButtons[i].setForeground(Color.WHITE);
        }

        leftInsidePanel.add(numberButtons[7]);
        leftInsidePanel.add(numberButtons[8]);
        leftInsidePanel.add(numberButtons[9]);
        leftInsidePanel.add(numberButtons[4]);
        leftInsidePanel.add(numberButtons[5]);
        leftInsidePanel.add(numberButtons[6]);
        leftInsidePanel.add(numberButtons[1]);
        leftInsidePanel.add(numberButtons[2]);
        leftInsidePanel.add(numberButtons[3]);
        leftInsidePanel.add(numberButtons[0]);
        leftInsidePanel.add(clearButton);
        leftInsidePanel.add(deleteButton);

        instructionLabel = new JLabel("Enter phone number below.");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(378, 190, 450, 50);
        mainPanel.add(instructionLabel);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(440, 300, 218, 50);
        confirmButton.setBackground(new Color(43, 43, 43));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(appFont);
        mainPanel.add(confirmButton);

        backButton = new JButton("Back");
        backButton.setBounds(440, 370, 218, 50);
        backButton.setBackground(new Color(43, 43, 43));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(appFont);
        mainPanel.add(backButton);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                telNumTextField.setText(telNumTextField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == clearButton) {
            telNumTextField.setText("");
        }
        if (e.getSource() == deleteButton) {
            String string = telNumTextField.getText();
            telNumTextField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                telNumTextField.setText(telNumTextField.getText() + string.charAt(i));
            }
        }
    }

    private void openNewWindow() {
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = telNumTextField.getText().trim();

                if (phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid telephone number.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (phoneNumber.length() != 9) {
                    JOptionPane.showMessageDialog(frame, "Telephone number must have exactly 9 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.dispose();
                    new SendPackageCode(shipments, phoneNumber);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainPanel(shipments);
            }
        });
    }

    public static void main(String[] args) {
    }

}
