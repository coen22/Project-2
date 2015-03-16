
import java.awt.BorderLayout;
import java.awt.Color;
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
        inti();

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
        canvas.setBackground(Color.blue);
        canvas.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                    
            }
        });

        JPanel holder = new JPanel();

        holder.setSize(500, 600);
        holder.setMaximumSize(new Dimension(500, 600));
        holder.setMinimumSize(new Dimension(500, 600));
        holder.setBackground(Color.red);

        add(holder, BorderLayout.SOUTH);
        add(canvas, BorderLayout.NORTH);
    }

}
