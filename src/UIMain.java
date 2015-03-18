
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UIMain extends JFrame implements Observer {

    private ArrayList<Rectangle2D.Double> listRec = new ArrayList<>();
    private ArrayList<ArrayList<Rectangle2D.Double>> listListRec = new ArrayList<>();
    private Launch engine = null;
    private JPanel canvas;
    private Boolean change = false;
    private JCheckBox checkBox;
    private int selectPoly = 0;
    private JComboBox whichPoly;
    private boolean hidOtherLine = false;
    private boolean addNewPolyLine = false;
    private boolean secondPoint = false;

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
     * 1024,768 * @throws HeadlessException
     */
    public UIMain() throws HeadlessException {
//        setUndecorated(true);
        setFocusable(true);
        setTitle("Polygoneeeeee Calc");
        setDefaultCloseOperation(3);
        //creates the actual engine
        engine = new Launch();
        //adds the current object as an observer
        engine.addObserver(this);
        init();

        //Gets the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //Centers the JFrame to the middle of the screen and sets it to full screen
        setSize(1024, 768);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Sets the UI to be visiable 
        setVisible(true);

        try {
            link();
        } catch (EmptySequenceException ex) {
        }
    }

    /**
     * Creates the actual user interface
     */
    private void init() {
        JPanel holder1 = new JPanel();
        JPanel holder2 = new JPanel();
        holder1.setBackground(Color.darkGray);
        holder2.setBackground(Color.darkGray);
        canvas = new JPanel() {
            int x = 0;
            int y = 0;

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
//                for (int k = 0; k < listListRec.get(selectPoly).size(); k++) {
//                    g2.setColor(Color.white);
//                    g2.setStroke(new BasicStroke(1f));
//                    g2.draw(listListRec.get(selectPoly).get(k));
//                    System.out.println(listListRec.get(selectPoly).size() + 1);
//
//                }

                if (shapes != null && !shapes.isEmpty()) {
                    int i = 0;
                    for (ArrayList<Vertex> shape : shapes) {
                        i++;
                        if (!hidOtherLine || i - 1 == selectPoly) {
                            Path2D.Double tmp = new Path2D.Double(Path2D.WIND_NON_ZERO, 1);
                            for (int j = 0; j < shape.size(); j++) {
                                if (j != shape.size() - 1 && shape.get(j) == (shape.get(0))) {
                                    tmp.moveTo(shape.get(j).getX(), canvas.getVisibleRect().height - shape.get(j).getY());
                                } else {
                                    tmp.lineTo(shape.get(j).getX(), canvas.getVisibleRect().height - shape.get(j).getY());
                                }
                            }
                            g2.setColor(Color.white);
                            for (int k = 0; k < i; k++) {
                                if (k == 2) {
                                    g2.setColor(g2.getColor().darker());
                                }
                                g2.setColor(g2.getColor().darker());
                            }
                            g2.setStroke(new BasicStroke(3f));
                            g2.draw(tmp);
                            for (int j = 0; j < shape.size(); j++) {
                                g2.setColor(Color.white);
                                g2.draw(new Rectangle2D.Double(shape.get(j).getX(), canvas.getVisibleRect().height - shape.get(j).getY(), 1, 1));
                            }
                        }
                    }

                }
            }
        };
        canvas.setBackground(Color.gray.darker());
        canvas.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!change) {
                    dragged:
                    for (int j = 0; j < listListRec.get(selectPoly).size(); j++) {
                        for (int i = listListRec.get(selectPoly).size() - 1; i > 0; i--) {

                            if (listListRec.get(selectPoly).get(j).x < e.getX()
                                    && e.getX() < listListRec.get(selectPoly).get(j).x + listListRec.get(selectPoly).get(j).width
                                    && listListRec.get(selectPoly).get(j).y < e.getY()
                                    && e.getY() < listListRec.get(selectPoly).get(j).y + listListRec.get(selectPoly).get(j).height) {
                                if (i != j
                                        && listListRec.get(selectPoly).get(i).x < e.getX()
                                        && e.getX() < listListRec.get(selectPoly).get(i).x + listListRec.get(selectPoly).get(i).width
                                        && listListRec.get(selectPoly).get(i).y < e.getY()
                                        && e.getY() < listListRec.get(selectPoly).get(i).y + listListRec.get(selectPoly).get(i).height) {
                                    break dragged;
                                } else {
                                    try {
                                        engine.getListOfPolyLine().get(selectPoly).changeElementAt(j, new Vertex(e.getX(), canvas.getVisibleRect().height - e.getY()));
                                    } catch (EmptySequenceException ex) {
                                        System.out.println(ex);
                                    }
                                    try {
                                        link();
                                    } catch (EmptySequenceException ex) {
                                    }
                                    canvas.repaint();
                                }
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
                if (addNewPolyLine && !secondPoint) {
                    secondPoint = true;
                    engine.getListOfPolyLine().add(new PolyLine(new Vertex(e.getX(), canvas.getVisibleRect().height - e.getY())));
                } else if (addNewPolyLine && secondPoint) {
                    engine.getListOfPolyLine().get(engine.getListOfPolyLine().size() - 1).insertLast(new Vertex(e.getX(), canvas.getVisibleRect().height - e.getY()));
                    addNewPolyLine = false;
                    secondPoint = false;
                    try {
                        link();
                    } catch (EmptySequenceException ex) {
                    }
                    repaint();
                } else if (change) {
                    engine.getListOfPolyLine().get(selectPoly).insertLast(new Vertex(e.getX(), canvas.getVisibleRect().height - e.getY()));
                    try {
                        link();
                    } catch (EmptySequenceException ex) {
                    }
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
        whichPoly = new JComboBox<Integer[]>();
        whichPoly.addItem(1);
        whichPoly.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectPoly = whichPoly.getSelectedIndex();
                try {
                    link();
                } catch (EmptySequenceException ex) {
                }

            }
        });
        checkBox = new JCheckBox("Add new points");
        checkBox.setForeground(Color.WHITE);
        checkBox.setBackground(holder1.getBackground());
        checkBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                change = !change;
            }
        });
        JButton close = new JButton("Close Current Polyline");
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                engine.getListOfPolyLine().get(selectPoly).closeLine();
                try {
                    link();
                    canvas.repaint();
                } catch (EmptySequenceException ex) {
                }
            }
        });
        JButton calc = new JButton("Calculate number of intercepts");
        calc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JLabel label = new JLabel("Select Polyline To manipulate:");
        JCheckBox visable = new JCheckBox("Set non current to invisable");
        visable.setForeground(Color.WHITE);
        visable.setBackground(holder1.getBackground());
        visable.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hidOtherLine = !hidOtherLine;
                canvas.repaint();
            }
        });
        label.setForeground(Color.white);
        JButton adddNewLine = new JButton("Add new line");
        adddNewLine.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPolyLine = true;
            }
        });
        holder1.add(label);
        holder1.add(whichPoly);
        holder1.add(visable);
        holder1.add(Box.createRigidArea(new Dimension(5, 0)));
        holder1.add(checkBox);
        holder1.add(Box.createRigidArea(new Dimension(5, 0)));
        holder1.add(close);
        holder1.add(Box.createRigidArea(new Dimension(5, 0)));
        holder2.add(calc);
        holder1.add(adddNewLine);
        JPanel holderholder = new JPanel();
        holderholder.setLayout(new GridLayout(2, 0));
        holderholder.add(holder1);
        holderholder.add(holder2);
        add(holderholder, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
    }

    private ArrayList<ArrayList<Vertex>> shapes;

    private void link() throws EmptySequenceException {
        listListRec.clear();
        listRec.clear();
        shapes = new ArrayList<>();
        ArrayList<PolyLine> listOfPolyLine = engine.getListOfPolyLine();
        for (int j = 0; j < listOfPolyLine.size(); j++) {
            ArrayList<Vertex> vertexList = new ArrayList<>();
            for (int i = 0; i < listOfPolyLine.get(j).size(); i++) {
                Vertex tmp2 = (Vertex) listOfPolyLine.get(j).elementAt(i);
                vertexList.add(tmp2);
                listRec.add(new Rectangle2D.Double(tmp2.getX() - 20, canvas.getVisibleRect().height - tmp2.getY() - 20, 40, 40));
            }
            listListRec.add(new ArrayList(listRec));
            listRec.clear();
            shapes.add(vertexList);
            if (listOfPolyLine.get(selectPoly).isClosed()) {
                checkBox.setEnabled(false);
            } else if (!listOfPolyLine.get(j).isClosed()) {
                checkBox.setEnabled(true);
            }
        }
        if (whichPoly.getItemCount() != engine.getListOfPolyLine().size()) {
            whichPoly.addItem(whichPoly.getItemCount() + 1);

        }
    }
}
