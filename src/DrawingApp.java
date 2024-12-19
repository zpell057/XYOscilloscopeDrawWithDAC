/*
* Classes handling the drawing window.
* */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class DrawingApp extends JFrame {
    ImageBuilder imageBuilder;
    public DrawingApp() {
        setTitle("Drawing App");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imageBuilder = new ImageBuilder(600, 600, 16, 16);
        DrawingPanel drawingPanel = new DrawingPanel(imageBuilder);
        add(drawingPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Saving image...");
                imageBuilder.saveImage();
                dispose();
            }
        });

        setVisible(true);

    }

}

class DrawingPanel extends JPanel {
    private final ArrayList<Line> lines = new ArrayList<>();
    private Point startPoint;

    public DrawingPanel(ImageBuilder imageBuilder) {
        setBackground(Color.WHITE);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point endPoint = e.getPoint();
                imageBuilder.setPoint(endPoint.x, endPoint.y);
                //System.out.println(endPoint.toString());
                lines.add(new Line(startPoint, endPoint));
                startPoint = endPoint;
                repaint();
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        for (Line line : lines) {
            g2.drawLine(line.getStart().x, line.getStart().y, line.getEnd().x, line.getEnd().y);
        }
    }

    private static class Line {
        private final Point start;
        private final Point end;

        public Line(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        public Point getStart() {
            return start;
        }

        public Point getEnd() {
            return end;
        }
    }
}
