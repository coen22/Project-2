
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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
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

/**
 * The GUI for PolyLine Handler
 *
 * @author Kareem Horstink
 * @version 0.9
 */
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
            System.out.println(ex);
        }
        UIMain UI = new UIMain();
    }

    private ArrayList<ArrayList<Rectangle2D.Double>> listListRec = new ArrayList<>();
    private Engine engine = null;
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
    private ArrayList<ArrayList<Vertex>> shapes;
    private boolean checkInside;
    private boolean first = false;
    private int x = 0;
    private int y = 0;
    private final int GridSize = 50;
    private ArrayList<Vertex> intersection = new ArrayList<>();

    /**
     * Main Constructor of the GUI
     *
     * @throws HeadlessException
     */
    public UIMain() throws HeadlessException {
        //Sets the JFrame Parmeters
//        setUndecorated(true);
        setFocusable(true);
        setTitle("Polygon Calculations");
        setDefaultCloseOperation(3);

        //creates the actual engine to run calculations
        engine = new Engine();
        JOptionPane.showMessageDialog(rootPane, "All units are measured in pixels \nTo pan: Hold shift and drag"
                + "\nTo zoom: Use scroll wheel\nTo move points: Click and drag\nTo Enter Coordinates: Right click with add new points selected", "GUI Help",JOptionPane.INFORMATION_MESSAGE);

        init();

        //Gets the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //Centers the JFrame to the middle of the screen and sets it to full screen
        setSize(1024, 768);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Sets the UI to be visiable
        setVisible(true);

        //refreshes the data
        try {
            canvas.repaint();
            link();
        } catch (EmptySequenceException ex) {
            System.out.println(ex);
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

        //--------------------------------------------------------------------//
        //Creates the file chooser to select which file to import
        fileChooser = new JFileChooser();
        fileChooser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"CancelSelection".equals(e.getActionCommand())) {
                    file = fileChooser.getSelectedFile();
                    engine.createPolyLineFromFile(file);
                    try {
                        link();
                    } catch (EmptySequenceException ex) {
                        System.out.println(ex);
                    }
                    canvas.repaint();
                }
            }
        });

        //--------------------------------------------------------------------//
        //Creates the canvas to draw the polyline and ploygons as well as the
        //various mouse listeners
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
                //Draws the grid and the axis
                for (int i = -500; i < 500; i++) {
                    g2.setColor(Color.lightGray);
                    g2.setFont(new Font("TimesRoman", Font.PLAIN, 10));
                    g2.draw(new Line2D.Double((((i * GridSize) * zoom) + (offsetX)), -Integer.MAX_VALUE, (((i * GridSize) * zoom) + (offsetX)), Integer.MAX_VALUE));
                    g2.draw(new Line2D.Double(-Integer.MAX_VALUE,
                            (canvas.getVisibleRect().getHeight() / zoom - (i * GridSize)) * zoom - offsetY,
                            Integer.MAX_VALUE,
                            (canvas.getVisibleRect().getHeight() / zoom - (i * GridSize)) * zoom - offsetY
                    ));
                    g2.drawString(Integer.toString(GridSize * i), (int) ((i * GridSize + 2) * zoom) + offsetX, canvas.getVisibleRect().height - offsetY);
                    if (i != 0) {
                        g2.drawString(Integer.toString(GridSize * i), offsetX + 2, (int) ((canvas.getVisibleRect().height / zoom - i * GridSize - 2) * zoom - offsetY));
                    }
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
                            g2.setColor(Color.black);
//                            for (int k = 0; k < i; k++) {
//                                if (k == 2) {
//                                    g2.setColor(g2.getColor().darker());
//                                }
//                                g2.setColor(g2.getColor().darker());
//                            }
                            g2.setStroke(new BasicStroke(3f));
                            g2.draw(tmp);
                            for (int j = 0; j < shape.size(); j++) {
                                g2.setColor(Color.white);
                                g2.draw(new Rectangle2D.Double(shape.get(j).getX() * zoom + offsetX, canvas.getVisibleRect().height * zoom - shape.get(j).getY() * zoom - (canvas.getVisibleRect().height * (zoom - 1)) - offsetY, 1, 1));
                            }
                        }
                    }
                    for (int j = 0; j < intersection.size(); j++) {
                        g2.setColor(Color.red);
                        g2.draw(new Ellipse2D.Double(intersection.get(j).getX() * zoom + offsetX,
                                canvas.getVisibleRect().height * zoom - intersection.get(j).getY() * zoom
                                - canvas.getVisibleRect().getHeight() * (zoom - 1) - offsetY, 4, 4));

                    }

                    //--------------------------------------------------------------
                    //Writes the info to top left corner
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

        //Mouse listen for motion
        //Eg: moving points, moving grid
        canvas.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!e.isShiftDown()) {
                    intersection.clear();
                }
                if (e.getButton() == 0) {
                    if (!first && e.isShiftDown()) {
                        first = true;
                        x = e.getX();
                        y = e.getY();
                    }
                    if (e.isShiftDown()) {
                        offsetX -= x - e.getX();
                        offsetY += y - e.getY();
                        canvas.repaint();
                        try {
                            link();
                        } catch (EmptySequenceException ex) {
                            System.out.println(ex);
                        }
                        x = e.getX();
                        y = e.getY();
                    } else if (!change) {
                        if (!listListRec.isEmpty()) {
                            dragged:
                            for (int j = 0; j < listListRec.get(selectPoly).size(); j++) {
                                for (int i = listListRec.get(selectPoly).size() - 1; i > 0; i--) {
                                    if (listListRec.get(selectPoly).get(j).x < e.getX()
                                            && e.getX() < listListRec.get(selectPoly).get(j).x
                                            + listListRec.get(selectPoly).get(j).width
                                            && listListRec.get(selectPoly).get(j).y < e.getY()
                                            && e.getY() < listListRec.get(selectPoly).get(j).y
                                            + listListRec.get(selectPoly).get(j).height) {
                                        if (i != j
                                                && listListRec.get(selectPoly).get(i).x < e.getX()
                                                && e.getX() < listListRec.get(selectPoly).get(i).x
                                                + listListRec.get(selectPoly).get(i).width
                                                && listListRec.get(selectPoly).get(i).y < e.getY()
                                                && e.getY() < listListRec.get(selectPoly).get(i).y
                                                + listListRec.get(selectPoly).get(i).height) {
                                            break dragged;
                                        } else {
                                            try {
                                                if (!engine.getListOfPolyLine().isEmpty()) {
                                                    engine.getListOfPolyLine().get(selectPoly).changeElementAt(j,
                                                            new Vertex(e.getX() / zoom - offsetX / zoom, canvas.getVisibleRect().height / zoom - e.getY() / zoom - offsetY / zoom));
                                                }
                                            } catch (EmptySequenceException ex) {
                                                System.out.println(ex);
                                            }
                                            try {
                                                link();
                                            } catch (EmptySequenceException ex) {
                                                System.out.println(ex);
                                            }
                                            canvas.repaint();
                                        }
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

        //Mouse wheel listener to set the zoom level
        canvas.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double tmp = zoom;
                zoom -= e.getUnitsToScroll() * 0.01;
                if (zoom <= 0.1) {
                    zoom = tmp;
                }
                canvas.repaint();
                try {
                    link();
                } catch (EmptySequenceException ex) {
                    System.out.println(ex);
                }
            }
        });

        //Mouse listener to do various things:
        //set the points to check if its inside a polygon or not,
        //to able to add a new polyline,
        //to able add new points to an existing poly line.
        canvas.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    if (checkInside) {
                        WNAlgorithm tmp = new WNAlgorithm(engine.getListOfPolyLine().get(selectPoly),
                                new Vertex(e.getX() / zoom - offsetX / zoom, canvas.getVisibleRect().height / zoom - e.getY() / zoom - offsetY / zoom));
                        JOptionPane.showMessageDialog(null,
                                "Is the point inside: " + !tmp.isOutside() + '\n'
                                + "x: " + (e.getX() / zoom - offsetX)
                                + '\n' + "y: "
                                + (canvas.getVisibleRect().height / zoom - e.getY() / zoom - offsetY / zoom),
                                getTitle(),
                                JOptionPane.INFORMATION_MESSAGE);
                        checkInside = false;
                    } else if (addNewPolyLine && !secondPoint) {
                        secondPoint = true;
                        engine.getListOfPolyLine().add(new PolyLine(
                                new Vertex(e.getX() / zoom - offsetX, canvas.getVisibleRect().height / zoom - e.getY() / zoom - offsetY / zoom)));
                        selectPoly = whichPoly.getItemCount() - 1;
                    } else if (addNewPolyLine && secondPoint) {
                        engine.getListOfPolyLine().get(
                                engine.getListOfPolyLine().size() - 1).insertLast(
                                        new Vertex(e.getX() / zoom - offsetX, canvas.getVisibleRect().height / zoom - e.getY() / zoom - offsetY / zoom));
                        addNewPolyLine = false;
                        secondPoint = false;
                        try {
                            link();
                        } catch (EmptySequenceException ex) {
                            System.out.println(ex);
                        }
                        repaint();
                    } else if (change && !engine.getListOfPolyLine().isEmpty() && !e.isShiftDown()) {
                        engine.getListOfPolyLine().get(selectPoly).insertLast(
                                new Vertex(e.getX() / zoom - offsetX, canvas.getVisibleRect().height / zoom - e.getY() / zoom - offsetY / zoom));
                        try {
                            link();
                        } catch (EmptySequenceException ex) {
                            System.out.println(ex);
                        }
                        canvas.repaint();

                    }
                } else if (!engine.getListOfPolyLine().isEmpty() && change && !engine.getListOfPolyLine().get(selectPoly).isClosed()) {

                    String tmpString = (String) JOptionPane.showInputDialog("Please Enter Coordinates", "100.0, 100.0");
                    System.out.println(tmpString);

                    if (tmpString != null && !tmpString.isEmpty() && tmpString.contains(",")) {
                        try {

                            String stringArray[] = tmpString.split(",");
                            for (int i = 0; i < stringArray.length; i++) {
                                stringArray[i] = stringArray[i].trim();
                            }
                            engine.getListOfPolyLine().get(selectPoly).insertLast(
                                    new Vertex(Double.parseDouble(stringArray[0]), Double.parseDouble(stringArray[1])));
                            try {
                                link();
                            } catch (EmptySequenceException ex) {
                                System.out.println(ex);
                            }
                            canvas.repaint();
                        } catch (NumberFormatException ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                first = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        //--------------------------------------------------------------------//
        //Combo box to hold which lines can be changed and selected
        whichPoly = new JComboBox<Integer[]>();
        whichPoly.addItem(1);
        whichPoly.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectPoly = whichPoly.getSelectedIndex();
                try {
                    link();
                } catch (EmptySequenceException ex) {
                    System.out.println(ex);
                }
                canvas.repaint();
            }
        });

        //--------------------------------------------------------------------//
        //Checkbox to see if a user wants to add new points
        checkBox = new JCheckBox("Add new points");
        checkBox.setForeground(Color.WHITE);
        checkBox.setBackground(holder1.getBackground());
        checkBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                change = !change;
            }
        });

        //--------------------------------------------------------------------//
        //A button to close the current selected polyline
        JButton close = new JButton("Close Current Polyline");
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!engine.getListOfPolyLine().isEmpty()) {
                    change = false;
                    checkBox.setSelected(false);
                    engine.getListOfPolyLine().get(selectPoly).closeLine();
                    try {

                        link();
                        canvas.repaint();
                    } catch (EmptySequenceException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });

        //--------------------------------------------------------------------//
        //A button to calculate the number of intersects
        JButton calc = new JButton("Calculate intercepts");
        calc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!engine.getListOfPolyLine().isEmpty())
                intersection = BentleyOttmannRB.findIntersects(engine.getListOfPolyLine().get(selectPoly), null);
                JOptionPane.showMessageDialog(canvas, "Number of intercepts: " + intersection.size());
                canvas.repaint();

                try {
                    link();
                } catch (EmptySequenceException ex) {
                    System.out.println(ex);
                }
            }
        });

        //--------------------------------------------------------------------//
        //Label
        JLabel label = new JLabel("Polyline to manipulate:");
        label.setForeground(Color.white);

        //--------------------------------------------------------------------//
        //Check box to set all non selected lines invisible
        JCheckBox visible = new JCheckBox("Hide other lines");
        visible.setForeground(Color.WHITE);
        visible.setBackground(holder1.getBackground());
        visible.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hidOtherLine = !hidOtherLine;
                canvas.repaint();
            }
        });

        //--------------------------------------------------------------------//
        //A button to add a new line
        JButton adddNewLine = new JButton("New line");
        adddNewLine.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPolyLine = true;
            }
        });

        //--------------------------------------------------------------------//
        //A button to read a polyline/polygon from a file
        JButton read = new JButton("Read From File");
        read.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(fileChooser);

            }
        });

        //--------------------------------------------------------------------//
        //A button to see if a point is a polygon
        JButton seeIfInside = new JButton("Is point inside polyline");
        seeIfInside.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                checkInside = true;

            }
        });

        //--------------------------------------------------------------------//
        //A button to reset the navigation
        JButton resetView = new JButton("Reset view");
        resetView.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                offsetX = 0;
                offsetY = 0;
                zoom = 1;
                canvas.repaint();
            }
        });

        //--------------------------------------------------------------------//
        //A button to see if multiple lines intersect
        JButton checkMutilplePolyLines = new JButton("Multiple lines intersect");
        checkMutilplePolyLines.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String tmpString = (String) JOptionPane.showInputDialog("Please Select the line to compare to", "1");
                if(BentleyOttmannRB.polyLinesIntersecting(engine.getListOfPolyLine().get(selectPoly), engine.getListOfPolyLine().get(Integer.parseInt(tmpString) - 1))){
                	JOptionPane.showMessageDialog(rootPane, "These Polylines intersect.");
                }
                else{
                	JOptionPane.showMessageDialog(rootPane, "These Polylines do not intersect.");
                }
            }
        });

        //--------------------------------------------------------------------//
        //A button to see if the shape is Simple
        JButton isSimple = new JButton("Is simple");
        isSimple.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!engine.getListOfPolyLine().isEmpty()) {
                	if(engine.getListOfPolyLine().get(selectPoly).isSimple()){
                		JOptionPane.showMessageDialog(rootPane, "The Polyline is simple.");
                	}
                	else{
                	JOptionPane.showMessageDialog(rootPane, "The Polyline is self intersecting. ");
                	}	
                }	
            }
        });

        //--------------------------------------------------------------------//
        //Adds items to holders and set the layout
        holder1.add(label);
        holder1.add(whichPoly);
        holder1.add(visible);
        holder1.add(Box.createRigidArea(new Dimension(5, 0)));
        holder1.add(checkBox);
        holder1.add(Box.createRigidArea(new Dimension(5, 0)));
        holder1.add(close);
        holder1.add(Box.createRigidArea(new Dimension(5, 0)));
        holder1.add(adddNewLine);

        holder2.add(calc);
        holder2.add(Box.createRigidArea(new Dimension(5, 0)));
        holder2.add(isSimple);
        holder2.add(Box.createRigidArea(new Dimension(5, 0)));

        holder2.add(seeIfInside);
        holder1.add(Box.createRigidArea(new Dimension(5, 0)));

        holder2.add(Box.createRigidArea(new Dimension(5, 0)));

        holder1.add(resetView);
        holder1.add(Box.createRigidArea(new Dimension(5, 0)));
        holder2.add(checkMutilplePolyLines);
        
        holder2.add(Box.createRigidArea(new Dimension(5, 0)));
        holder2.add(read);
        
        JPanel holderholder = new JPanel();
        holderholder.setLayout(new GridLayout(2, 0));
        holderholder.add(holder1);
        holderholder.add(holder2);
        add(holderholder, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
    }

    /**
     * Brings all the relevant info to the GUI from the engine
     *
     * @throws EmptySequenceException Error thrown from polyline
     */
    private void link() throws EmptySequenceException {
        //ensure the previous layers of rectangles are empty
        listListRec.clear();

        //Recreates the shapes layers
        shapes = new ArrayList<>();

        //Gets the vertices from the engine
        ArrayList<PolyLine> listOfPolyLine = engine.getListOfPolyLine();

        //Runs as many time as there is layers
        for (int i = 0; i < listOfPolyLine.size(); i++) {
            //Creates a new list of vertices to store the current layer of vertices
            ArrayList<Vertex> vertexList = new ArrayList<>();
            //Creates a new list of rectangles to store the current layer of rectangles
            ArrayList<Rectangle2D.Double> listRec = new ArrayList<>();

            //Runs as many times as there is vertices
            for (int j = 0; j < listOfPolyLine.get(i).size(); j++) {
                Vertex tmp2 = (Vertex) listOfPolyLine.get(i).elementAt(j);
                vertexList.add(tmp2);
                listRec.add(new Rectangle2D.Double((tmp2.getX() * zoom - 20) + offsetX, (canvas.getVisibleRect().height * zoom - tmp2.getY() * zoom - 20 - ((zoom - 1) * canvas.getVisibleRect().height)) - offsetY, 40, 40));
            }

            //Add the layer to the layers-List (notice the 's' in layers)
            listListRec.add(new ArrayList(listRec));
            shapes.add(vertexList);

            //Sets the check box to be disabled if the polyline is closed
            if (listOfPolyLine.get(selectPoly).isClosed()) {
                checkBox.setEnabled(false);
                addNewPolyLine = false;
            } else if (!listOfPolyLine.get(i).isClosed()) {
                checkBox.setEnabled(true);
            }
        }

        //Updates the comboBox with the current item
        while (whichPoly.getItemCount()
                != engine.getListOfPolyLine().size() && !engine.getListOfPolyLine().isEmpty()) {
            whichPoly.addItem(whichPoly.getItemCount() + 1);
        }
    }
}
