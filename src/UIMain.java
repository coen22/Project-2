
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UIMain extends JFrame implements Observer {

    private ArrayList<Rectangle2D.Double> listRec = new ArrayList<>();
    private Launch engine = null;
    private JPanel canvas;
    private Boolean change = false;
    private int deltaX;
    private int deltaY;

    /**
     * Runs the rest program
     *
     * @param args
     */
    public static void main(String[] args) {
        UIMain UI = new UIMain();
    }

    /**
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

    }

    /**
     *
     * @throws HeadlessException
     */
    public UIMain() throws HeadlessException {
        setFocusable(true);
        setTitle("Polygoneeeeee Calucations");
        setDefaultCloseOperation(3);
        setSize(800, 600);
        listRec.add(new Rectangle2D.Double(200, 200, 25, 25));

        //creates the actual engine
        engine = new Launch();
        //adds the current object as an observer
        engine.addObserver(this);
        try {
            link();
        } catch (EmptySequenceException ex) {
        }

        init();

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
    private void init() {
        canvas = new JPanel() {
            int x = 0;
            int y = 0;

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.white);
                x = deltaX;
                y = deltaY;
                if (shapes != null && !shapes.isEmpty()) {
                    for (ArrayList<Vertex> shape : shapes) {
                        Path2D.Double tmp = new Path2D.Double(Path2D.WIND_NON_ZERO, 1);

                        for (Vertex shape1 : shape) {
                            if (shape1 == (shape.get(0))) {
                                tmp.moveTo(shape1.getX(), shape1.getY());
                            } else {
                                tmp.lineTo(shape1.getX(), shape1.getY());
                                System.out.println("x: " + shape1.getX());
                                System.out.println("y: " + shape1.getY());

                            }
                        }
                        g2.draw(tmp);

                    }

                }
            }
        };
        canvas.setBackground(Color.gray.darker());
        canvas.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!change) {
                    for (Rectangle2D.Double listRec1 : listRec) {
                        if (listRec1.x < e.getX() && e.getX() < listRec1.x + listRec1.width
                                && listRec1.y < e.getY() && e.getY() < listRec1.y + listRec1.height) {
                            listRec1.x = e.getX() - 12.5;
                            listRec1.y = e.getY() - 12.5;
                            canvas.repaint();
                        }
                    }
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });

        canvas.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (change) {
                    listRec.add(new Rectangle2D.Double(e.getX() - 12.5, e.getY() - 12.5, 25, 25));
                    canvas.repaint();

                }
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

        JCheckBox checkBox = new JCheckBox("Add new points");
        checkBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                change = !change;
            }
        });

        JButton calc = new JButton("Calculate number of intercepts");
        calc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        holder.add(checkBox);
        holder.add(calc);
        holder.setSize(500, 600);
        holder.setMaximumSize(new Dimension(500, 600));
        holder.setMinimumSize(new Dimension(500, 600));
        holder.setBackground(Color.darkGray);

        add(holder, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
    }
    ArrayList<ArrayList<Vertex>> shapes;

    private void link() throws EmptySequenceException {
        shapes = new ArrayList<>();
        ArrayList<PolyLine> listOfPolyLine = engine.getListOfPolyLine();
        for (PolyLine tmp1 : listOfPolyLine) {
            ArrayList<Vertex> vertexList = new ArrayList<>();
            vertexList.add((Vertex) tmp1.elementAt(0));
            for (int i = 1; i < tmp1.size(); i++) {
                Vertex tmp2 = (Vertex) tmp1.elementAt(i);
                vertexList.add(tmp2);
            }
            shapes.add(vertexList);
        }
    }
}
