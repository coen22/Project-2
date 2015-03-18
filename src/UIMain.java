
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UIMain extends JFrame {

    /**
     * Runs the rest program
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(UIMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        UIMain UI = new UIMain();
    }

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
    private File file;
    private JFileChooser fileChooser;
    private int offsetX = 0;
    private int offsetY = 0;
    private double zoom = 1;
    private int x;
    private int y;
    private ArrayList<ArrayList<Vertex>> shapes;
    private boolean checkInside;

    /**
     * Main Constructor of the GUI
     *
     * @throws HeadlessException
     */
    public UIMain() throws HeadlessException {
        //Sets the JFrame Parmeters
        setUndecorated(true);
        setFocusable(true);
        setTitle("Polygon Calculations");
        setDefaultCloseOperation(3);

        //creates the actual engine to run calculations
        engine = new Launch();
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
        //JPanels to hold most interactive Interfaces
        JPanel holder1 = new JPanel();
        JPanel holder2 = new JPanel();
        //Sets the colour of the GUI
        holder1.setBackground(Color.darkGray);
        holder2.setBackground(Color.darkGray);

        fileChooser = new JFileChooser();
        fileChooser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                file = fileChooser.getSelectedFile();
                engine.createPolyLineFromFile(file);
                try {
                    link();
                } catch (EmptySequenceException ex) {
                }
                canvas.repaint();
            }
        });

        canvas = new JPanel() {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                //--------------------------------------------------------------
                //Draws the bounding box

//                if (!listListRec.isEmpty()) {
//                    for (int k = 0; k < listListRec.get(selectPoly).size(); k++) {
//                        g2.setColor(Color.white);
//                        g2.setStroke(new BasicStroke(1f));
//                        g2.draw(listListRec.get(selectPoly).get(k));
//                        //System.out.println(listListRec.get(selectPoly).size() + 1);
//
//                    }
//                }
                //--------------------------------------------------------------
                //Draws the grid
                for (int i = -500; i < 500; i++) {
                    g2.setColor(Color.white);
                    g2.draw(new Line2D.Double((i * 100) * zoom, -Integer.MAX_VALUE, (i * 100) * zoom, Integer.MAX_VALUE));
                    g2.draw(new Line2D.Double(-Integer.MAX_VALUE,
                            (canvas.getVisibleRect().getHeight() / zoom - (i * 100)) * zoom,
                            Integer.MAX_VALUE,
                            (canvas.getVisibleRect().getHeight() / zoom - (i * 100)) * zoom
                    ));
                }

                //--------------------------------------------------------------
                //Draws the polylines
                if (shapes != null && !shapes.isEmpty()) {
                    int i = 0;
                    for (ArrayList<Vertex> shape : shapes) {
                        i++;
                        if (!hidOtherLine || i - 1 == selectPoly) {
                            Path2D.Double tmp = new Path2D.Double(Path2D.WIND_NON_ZERO, 1);
                            for (int j = 0; j < shape.size(); j++) {
                                if (j != shape.size() - 1 && shape.get(j) == (shape.get(0))) {
                                    tmp.moveTo((zoom * (shape.get(j).getX())) + offsetX, (zoom * canvas.getVisibleRect().height - zoom * shape.get(j).getY()) - ((zoom - 1) * canvas.getVisibleRect().height) - offsetY);
                                } else {
                                    tmp.lineTo((zoom * (shape.get(j).getX())) + offsetX, (zoom * canvas.getVisibleRect().height - zoom * shape.get(j).getY()) - ((zoom - 1) * canvas.getVisibleRect().height) - offsetY);
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
                    //--------------------------------------------------------------
                    //Draws the info
                    if (!engine.getListOfPolyLine().isEmpty() && engine.getListOfPolyLine().get(selectPoly).isClosed()) {
                        g2.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                        g2.setColor(new Color(20, 20, 20, 100));
                        g2.fill(new Rectangle2D.Double(canvas.getVisibleRect().getWidth() - 258, 18, 200, 65 + 18 + 18));
                        g2.setColor(Color.WHITE);
                        g2.drawString("Number Of lines: " + (engine.getListOfPolyLine().get(selectPoly).size() - 1),
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 58);
                        g2.drawString("Number Of Vertices: " + (engine.getListOfPolyLine().get(selectPoly).size() - 1),
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 40);
                        g2.drawString("Area: " + Math.abs(engine.getListOfPolyLine().get(selectPoly).area()),
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 58 + 18);
                        g2.drawString("Length: " + Math.round(engine.getListOfPolyLine().get(selectPoly).length()),
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 58 + 18 * 2);
                        g2.drawString("Current Zoom: " + (double) Math.round(zoom * 1000) / 1000,
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 58 + 18 * 3);

                    } else if (!engine.getListOfPolyLine().isEmpty()) {
                        g2.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                        g2.setColor(new Color(20, 20, 20, 100));
                        g2.fill(new Rectangle2D.Double(canvas.getVisibleRect().getWidth() - 258, 18, 200, 65 + 18 + 18));
                        g2.setColor(Color.WHITE);
                        g2.drawString("Number Of lines: " + (engine.getListOfPolyLine().get(selectPoly).size() - 1),
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 58);
                        g2.drawString("Number Of Vertices: " + engine.getListOfPolyLine().get(selectPoly).size(),
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 40);
                        g2.drawString("Area: " + "NaN",
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 58 + 18);
                        g2.drawString("Length: " + Math.round(engine.getListOfPolyLine().get(selectPoly).length()),
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 58 + 18 * 2);
                        g2.drawString("Current Zoom: " + (double) Math.round(zoom * 1000) / 1000,
                                (int) ((canvas.getVisibleRect().getWidth()) - 250), 58 + 18 * 3);
                    }
                }
            }
        };
        canvas.setBackground(Color.gray.darker());
        canvas.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isShiftDown()) {
                    offsetX = x - (-e.getX());
                    offsetY = y - (e.getY() - canvas.getVisibleRect().height);
                    canvas.repaint();
                } else if (!change) {
                    if (!listListRec.isEmpty()) {
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
                                            if (!engine.getListOfPolyLine().isEmpty()) {
                                                engine.getListOfPolyLine().get(selectPoly).changeElementAt(j, new Vertex(e.getX(), canvas.getVisibleRect().height - e.getY()));
                                            }
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

            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });
        canvas.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double tmp = zoom;
                zoom += e.getUnitsToScroll() * 0.01;
                if (zoom <= 0) {
                    zoom = tmp;
                }
                canvas.repaint();
            }
        });
        canvas.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isShiftDown()) {
                    x = e.getX();
                    y = e.getY();
                } else if (checkInside) {
                    JOptionPane.showMessageDialog(null,
                            "Is the point inside: " + engine.getListOfPolyLine().get(selectPoly).pointInside(new Vertex(e.getX(), canvas.getVisibleRect().height - e.getY())) + '\n' + "x: " + e.getX() + '\n' + "y: " + (canvas.getVisibleRect().height - e.getY()),
                            getTitle(),
                            JOptionPane.INFORMATION_MESSAGE);
                    checkInside = false;
                } else if (addNewPolyLine && !secondPoint) {
                    secondPoint = true;
                    engine.getListOfPolyLine().add(new PolyLine(new Vertex(e.getX(), canvas.getVisibleRect().height - e.getY())));
                    selectPoly = whichPoly.getItemCount() - 1;
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
                change = false;
                checkBox.setSelected(false);
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
        JButton read = new JButton("Read From File");
        read.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(fileChooser);

            }
        });
        JButton seeIfInside = new JButton("See if point is inside polyline");
        seeIfInside.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                checkInside = true;

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
        holder2.add(read);
        holder2.add(seeIfInside);
        JPanel holderholder = new JPanel();
        holderholder.setLayout(new GridLayout(2, 0));
        holderholder.add(holder1);
        holderholder.add(holder2);
        add(holderholder, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
    }

    private void link() throws EmptySequenceException {
        ArrayList<Rectangle2D.Double> listRec = new ArrayList<>();

        listListRec.clear();
        shapes = new ArrayList<>();
        ArrayList<PolyLine> listOfPolyLine = engine.getListOfPolyLine();
        for (int j = 0;
                j < listOfPolyLine.size();
                j++) {
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
                addNewPolyLine = false;
            } else if (!listOfPolyLine.get(j).isClosed()) {
                checkBox.setEnabled(true);
            }
        }

        if (whichPoly.getItemCount()
                != engine.getListOfPolyLine().size() && !engine.getListOfPolyLine().isEmpty()) {
            whichPoly.addItem(whichPoly.getItemCount() + 1);

        }
    }
}
