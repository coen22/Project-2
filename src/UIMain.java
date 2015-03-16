
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UIMain extends JFrame implements Observer {
    private Launch engine;
    private JPanel canvas;
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
        
        //creates the actual engine
        engine = new Launch();
        //adds the current object as an observer
        engine.addObserver(this);
        
        //Gets the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //Centers the JFrame to the middle of the screen
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        //Sets the UI to be visiable 
        setVisible(true);
    }

    /**
     * Creates the actual user interface
     */
    private void inti() {
        canvas = new JPanel();
        canvas.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

}
