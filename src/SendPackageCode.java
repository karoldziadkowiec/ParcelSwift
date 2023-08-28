import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendPackageCode implements ActionListener {

    private JFrame frame;
    private JPanel mainPanel, upperPanel, leftPanel, leftInsidePanel;
    private JLabel logoLabel, instructionLabel;
    private JButton backButton, confirmButton;
    private JButton[] numberButtons = new JButton[10]; // numbers 0-9
    JButton deleteButton, clearButton;
    private JTextField codeTextField;
    private Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);

    private java.util.List<Shipment> shipments;
    String phoneNumber = null;
    public SendPackageCode(java.util.List<Shipment> shipments, String phoNum) {

        this.shipments = shipments;
        phoneNumber = phoNum;

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

        leftPanel = new JPanel();
        leftPanel.setLayout(null); // Changed to 4 rows, 3 columns
        leftPanel.setBounds(0, 85, 350, 415);
        leftPanel.setBackground(Color.WHITE);
        mainPanel.add(leftPanel);

        logoLabel = new JLabel("ParcelSwift");
        logoLabel.setForeground(Color.WHITE);
        Font logoFont = new Font("Comic Sans MS", Font.BOLD, 50);
        logoLabel.setFont(logoFont);
        logoLabel.setBounds(205, 20, 350, 50);
        upperPanel.add(logoLabel);

        leftInsidePanel = new JPanel();
        leftInsidePanel.setLayout(new GridLayout(4, 3, 10, 10)); // Changed to 4 rows, 3 columns
        leftInsidePanel.setBounds(40, 40, 270, 300);
        leftInsidePanel.setBackground(Color.WHITE);
        leftPanel.add(leftInsidePanel);

    }

    private void addComponents() {

        codeTextField = new JTextField();
        codeTextField.setBounds(378, 200, 280, 50);
        codeTextField.setBackground(Color.WHITE);
        codeTextField.setFont(appFont);
        mainPanel.add(codeTextField);

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

        instructionLabel = new JLabel("Enter the 6-digit code below.");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(378, 150, 450, 50);
        mainPanel.add(instructionLabel);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(440, 270, 218, 50);
        confirmButton.setBackground(new Color(43, 43, 43));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(appFont);
        mainPanel.add(confirmButton);

        backButton = new JButton("Back");
        backButton.setBounds(440, 330, 218, 50);
        backButton.setBackground(new Color(43, 43, 43));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(appFont);
        mainPanel.add(backButton);
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                codeTextField.setText(codeTextField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == clearButton) {
            codeTextField.setText("");
        }
        if (e.getSource() == deleteButton) {
            String string = codeTextField.getText();
            codeTextField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                codeTextField.setText(codeTextField.getText() + string.charAt(i));
            }
        }

    }

    private void ShowNewWindow() {

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = codeTextField.getText().trim();

                if (code.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid code.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (code.length() != 6) {
                    JOptionPane.showMessageDialog(frame, "Code must have exactly 6 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean codeExists = false;

                    for (Shipment shipment : shipments) {
                        if (shipment.code != null && shipment.code.equals(code)) {
                            codeExists = true;
                            break;
                        }
                    }

                    if (codeExists) {
                        JOptionPane.showMessageDialog(frame, "This code is already in use. Please enter a new code.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        frame.dispose();
                        new SendPackageSize(shipments, phoneNumber, code);
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SendPackageTelNum(shipments);
            }
        });

    }

    public static void main(String[] args) {

    }
}
