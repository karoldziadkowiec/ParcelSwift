import javax.swing.*;
import java.awt.*;
public class CenterWindow {

    public static void centerWindowOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;

        frame.setLocation(x, y);
    }
}