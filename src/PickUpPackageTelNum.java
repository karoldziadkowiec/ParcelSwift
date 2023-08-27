import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PickUpPackageTelNum {
    private JFrame frame;
    private JPanel mainPanel, upperPanel;
    private JLabel logoLabel, instructionLabel;
    private JButton backButton, confirmButton;
    private Font appFont = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 22);

    public PickUpPackageTelNum() {
        initializeFrame();
        addComponents();
        //ShowNewWindow();
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

    }

    private void addComponents() {

        logoLabel = new JLabel("ParcelSwift");
        logoLabel.setForeground(Color.WHITE);
        Font logoFont = new Font("Comic Sans MS", Font.BOLD, 50);
        logoLabel.setFont(logoFont);
        logoLabel.setBounds(205, 20, 350, 50);
        upperPanel.add(logoLabel);


    }

    public static void main(String[] args) {

    }

}

