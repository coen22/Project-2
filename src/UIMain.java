
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

public class UIMain extends JFrame implements Observer {

    public static void main(String[] args) {
        new UIMain();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public UIMain() throws HeadlessException {

        setFocusable(true);
        setTitle("Polygone Calucations");
        setDefaultCloseOperation(3);
        setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setVisible(true);
    }

}
