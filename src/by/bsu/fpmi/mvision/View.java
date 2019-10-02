package by.bsu.fpmi.mvision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View extends JFrame {
    private JPanel settings;
    private JPanel canvas;
    private JRadioButton line;
    private JRadioButton circle;

    private int prev_x = -1;
    private int prev_y = -1;

    public View(){
        super("Lines and Circles");
        settings = new JPanel();
        canvas = new JPanel();
        line = new JRadioButton("Line");
        circle = new JRadioButton("Circle");

        interfaceCreator();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (line.isSelected()){
                    if (prev_x > 0 && prev_y > 0){
                        drawLine(prev_x, prev_y, e.getX(), e.getY());
                        prev_x = -1;
                        prev_y = -1;
                    }
                    else{
                        prev_x = e.getX();
                        prev_y = e.getY();
                    }
                }
                else{
                    drawCircle(e.getX(),e.getY(),80);
                }
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
    }

    private void interfaceCreator(){
        ButtonGroup group = new ButtonGroup();
        settings.setSize(800,50);
        canvas.setSize(800,750);
        settings.setBackground(new Color(255, 218, 185));
        canvas.setBackground(new Color(250, 240, 230));
        group.add(line);
        group.add(circle);
        settings.add(line);
        settings.add(circle);
        add(settings);
        add(canvas);
    }
    private void drawLine(int x1, int y1, int x2, int y2){
        Graphics g = getGraphics();
        int pdx, pdy, es, el, err;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int incx = sign(x2-x1);
        int incy = sign(y2-y1);

        if (dx > dy){
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;
        }

        int x = x1;
        int y = y1;
        err = el / 2;
        g.drawLine(x, y, x, y);
        for (int t = 0; t < el; t++){
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;
                y += incy;
            } else {
                x += pdx;
                y += pdy;
            }

            g.drawLine(x, y, x, y);
        }
    }

    private int sign(int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
    }

    private void drawCircle(int x1, int y1, int radius){
        Graphics g = getGraphics();
        int x = 0, y = radius;
        int delta = 1 - 2*radius;
        int error = 0;
        while (y >= 0){
            g.drawLine(x1 + x, y1 + y, x1 + x, y1 + y);
            g.drawLine(x1 + x, y1 - y, x1 + x, y1 - y);
            g.drawLine(x1 - x, y1 + y, x1 - x, y1 + y);
            g.drawLine(x1 - x, y1 - y, x1 - x, y1 - y);
            error = 2 *(delta + y) - 1;
            if (delta < 0 && error <= 0){
                delta += 2 * ++x + 1;
                continue;
            }
            if (delta > 0 && error > 0){
                delta += 1 - 2 *--y;
                continue;
            }
            delta += 2*(++x - y--);
        }
    }
}
