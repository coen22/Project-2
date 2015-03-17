
import java.awt.BasicStroke;
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
        setSize(1000, 800);

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
                x = deltaX;
                y = deltaY;
                if (shapes != null && !shapes.isEmpty()) {
                    for (ArrayList<Vertex> shape : shapes) {
//                        for(int k = 0; k<listRec.size();k++){
////                        for (Rectangle2D.Double listRec1 : listRec) {
//                            g2.setColor(Color.white);
//                            g2.setStroke(new BasicStroke(1f));
//                            g2.draw(listRec.get(k));
//
//                        }
                        Path2D.Double tmp = new Path2D.Double(Path2D.WIND_NON_ZERO, 1);
                        for (int j = 0; j < shape.size(); j++) {
                            g2.setColor(Color.white.darker().darker());
                            g2.setStroke(new BasicStroke(3f));

                            if (j != shape.size() - 1 && shape.get(j) == (shape.get(0))) {
                                tmp.moveTo(shape.get(j).getX(), shape.get(j).getY());
                            } else {
                                tmp.lineTo(shape.get(j).getX(), shape.get(j).getY());
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
                    for (int j = 0; j < listRec.size(); j++) {
                        if (listRec.get(j).x < e.getX() && e.getX() < listRec.get(j).x + listRec.get(j).width
                                && listRec.get(j).y < e.getY() && e.getY() < listRec.get(j).y + listRec.get(j).height) {
                            try {
                                engine.getListOfPolyLine().get(0).changeElementAt(j, new Vertex(e.getX(), e.getY()));
                            } catch (EmptySequenceException ex) {
                                System.out.println(ex);
                            }
                            listRec.get(j).x = e.getX() - 20;
                            listRec.get(j).y = e.getY() - 20;
                            canvas.repaint();
                            try {
                                link();
                            } catch (EmptySequenceException ex) {
                            }
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
                    listRec.add(new Rectangle2D.Double(e.getX() - 20, e.getY() - 20, 40, 50));
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

    private ArrayList<ArrayList<Vertex>> shapes;

    private void link() throws EmptySequenceException {
        listRec.clear();
        shapes = new ArrayList<>();
        ArrayList<PolyLine> listOfPolyLine = engine.getListOfPolyLine();
        //PolyLine tmp1 : listOfPolyLine
        for (int j = 0; j < listOfPolyLine.size(); j++) {
            ArrayList<Vertex> vertexList = new ArrayList<>();
            for (int i = 0; i < listOfPolyLine.get(j).size(); i++) {
                Vertex tmp2 = (Vertex) listOfPolyLine.get(j).elementAt(i);
                vertexList.add(tmp2);
                listRec.add(new Rectangle2D.Double(tmp2.getX() - 20, tmp2.getY() - 20, 40, 40));

            }
            shapes.add(vertexList);
        }
    }
}
