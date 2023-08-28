import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
public class SendPackageLocker {

    private JFrame frame;
    private JPanel mainPanel, upperPanel;
    private JLabel logoLabel, instructionLabel, instruction2Label, timerLabel;
    private JButton confirmButton, retryButton, backButton;
    private Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);
    private Timer timer;
    private int secondsLeft = 30;

    private java.util.List<Shipment> shipments;
    String phoneNumber = null, code = null, size = null;

    public SendPackageLocker(java.util.List<Shipment> shipments, String phoNum, String cod, String sizee) {

        this.shipments = shipments;
        phoneNumber = phoNum;
        code = cod;
        size = sizee;

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

        timerLabel = new JLabel();
        timerLabel.setFont(appFont);
        timerLabel.setBounds(215, 315, 450, 50);
        mainPanel.add(timerLabel);

    }

    private void addComponents() {

        ImageIcon openedIcon = new ImageIcon("opened.png");
        JLabel logoImageLabel = new JLabel(openedIcon);
        logoImageLabel.setBounds(265, 90, openedIcon.getIconWidth(), openedIcon.getIconHeight());
        mainPanel.add(logoImageLabel);

        instructionLabel = new JLabel("The parcel locker is open!");
        instructionLabel.setFont(appFont);
        instructionLabel.setBounds(215, 255, 450, 50);
        mainPanel.add(instructionLabel);

        instruction2Label = new JLabel("Put the package in the locker.");
        instruction2Label.setFont(appFont);
        instruction2Label.setBounds(205, 285, 450, 50);
        mainPanel.add(instruction2Label);

        confirmButton = new JButton("Parcel inside");
        confirmButton.setBounds(470, 390, 200, 50);
        confirmButton.setBackground(new Color(43, 43, 43));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(appFont);
        mainPanel.add(confirmButton);

        retryButton = new JButton("Open again");
        retryButton.setBounds(20, 330, 150, 50);
        retryButton.setBackground(new Color(43, 43, 43));
        retryButton.setForeground(Color.WHITE);
        retryButton.setFont(appFont);
        mainPanel.add(retryButton);

        backButton = new JButton("Back");
        backButton.setBounds(20, 390, 150, 50);
        backButton.setBackground(new Color(43, 43, 43));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(appFont);
        mainPanel.add(backButton);

        startTimer();
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft > 0) {
                    secondsLeft--;
                    updateTimerLabel();
                } else {
                    timer.stop();
                    instructionLabel.setText("");
                    instruction2Label.setText("Time's up! Please proceed.");
                }
            }
        });
        timer.start();
    }

    private void updateTimerLabel() {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        timerLabel.setBounds(240, 325, 450, 50);
        Font timerFont = new Font("Comic Sans MS", Font.BOLD, 26);
        timerLabel.setFont(timerFont);
        timerLabel.setText(String.format("Time left: %02d:%02d", minutes, seconds));
    }

    private void ShowNewWindow() {

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SendPackageFinish(shipments, phoneNumber, code, size);
            }
        });

        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SendPackageLocker(shipments, phoneNumber, code, size);
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SendPackageSize(shipments, phoneNumber, code);
            }
        });
    }

    public static void main(String[] args) {



    }

}
