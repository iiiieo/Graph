import javax.swing.*;
import java.awt.*;

public class InfoFrame extends JFrame {
    public InfoFrame(String text) throws HeadlessException {
        setSize(600, 450);
        setLocationRelativeTo(null);

        JTextArea display = new JTextArea(16, 58);
        display.setEditable(false);
        display.setText(text);
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scroll);

        setVisible(true);
    }
}
