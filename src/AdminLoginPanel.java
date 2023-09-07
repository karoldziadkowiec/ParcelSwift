import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginPanel implements ActionListener{
    private JFrame frame;
    private JPanel mainPanel, upperPanel, leftPanel, leftInsidePanel;
    private JLabel logoLabel, instructionLabel;
    private JButton loginButton, backButton;
    private JButton[] numberButtons = new JButton[10]; // numbers 0-9
    JButton deleteButton, clearButton;
    private JTextField pinTextField;
    private Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);

    private java.util.List<Shipment> shipments;

    private final String PIN = "777777";

    public AdminLoginPanel(java.util.List<Shipment> shipments) {
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
        leftPanel.setBackground(new Color(77, 77, 77));
        mainPanel.add(leftPanel);

        leftInsidePanel = new JPanel();
        leftInsidePanel.setLayout(new GridLayout(4, 3, 10, 10)); // Changed to 4 rows, 3 columns
        leftInsidePanel.setBounds(40, 40, 270, 300);
        leftInsidePanel.setBackground(new Color(77, 77, 77));
        leftPanel.add(leftInsidePanel);
    }

    private void addComponents() {
        ImageIcon pinCodeIcon = new ImageIcon("pinCode.png");
        JLabel logoImageLabel = new JLabel(pinCodeIcon);
        logoImageLabel.setBounds(470, 130, pinCodeIcon.getIconWidth(), pinCodeIcon.getIconHeight());
        mainPanel.add(logoImageLabel);

        pinTextField = new JTextField();
        pinTextField.setBounds(378, 250, 280, 50);
        pinTextField.setBackground(Color.WHITE);
        pinTextField.setFont(appFont);
        mainPanel.add(pinTextField);

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
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setForeground(Color.BLACK);

        for (int i = 0; i < 10; i++) {

            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(appFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.WHITE);
            numberButtons[i].setForeground(Color.BLACK);
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

        instructionLabel = new JLabel("ADMIN PANEL");
        Font adminFont = new Font("Comic Sans MS", Font.BOLD, 32);
        instructionLabel.setFont(adminFont);
        instructionLabel.setBounds(400, 90, 450, 50);
        mainPanel.add(instructionLabel);

        instructionLabel = new JLabel("Enter the parcel locker PIN.");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(378, 210, 450, 50);
        mainPanel.add(instructionLabel);

        loginButton = new JButton("Log in");
        loginButton.setBounds(440, 310, 218, 50);
        loginButton.setBackground(new Color(43, 43, 43));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(appFont);
        mainPanel.add(loginButton);

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
                pinTextField.setText(pinTextField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == clearButton) {
            pinTextField.setText("");
        }
        if (e.getSource() == deleteButton) {
            String string = pinTextField.getText();
            pinTextField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                pinTextField.setText(pinTextField.getText() + string.charAt(i));
            }
        }
    }

    private void openNewWindow() {
        loginButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                String enteredPin = pinTextField.getText().trim();

                if (enteredPin.equals(PIN)) {
                    frame.dispose();
                    new AdminInfoPanel(shipments, PIN);
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid parcel locker PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainPanel(shipments);
            }

        });
    }

    public static void main(String[] args) {
    }
}