/*
 * Calculo de reta com bresenham
 * :::>
 */
package bresenham;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import retas.FrameBuffer;
import retas.MinMaxReta;

/**
 *
 * @author MESSI-PC
 */
public class Bresenham {
    
    private ArrayList<FrameBuffer> pontos;
    public ArrayList<FrameBuffer> getArrayBuffer(MinMaxReta p){
        pontos = new ArrayList<>();
        calcule(p.getX1(), p.getY1(), p.getX2(), p.getY2());   
        return pontos;
    }
    
    public void calcule(int x1, int y1, int x2, int y2) {
        int slope;
        int dx, dy, incE, incNE, d, x, y;
        
        if (x1 > x2) {
            calcule(x2, y2, x1, y1);
            return;
        }
        
        dx = x2 - x1;
        dy = y2 - y1;

        if (dy < 0) {
            slope = -1;
            dy = -dy;
        } else {
            slope = 1;
        }
        
        incE = 2 * dy;
        incNE = 2 * dy - 2 * dx;
        d = 2 * dy - dx;
        y = y1;
       
        
        Random rand = new Random();
        
        int RGB = rand.nextInt(255);
        int incr = 30;
        
        Color COLOR;
        for (x = x1; x <= x2; x++) {
            
            //"perfumarias" degradê ida e volta.
            RGB += incr;
            if (RGB > 120) {
                RGB = 120;
                incr *= -1;
            } else if (RGB < 30) {
                RGB = 30;
                incr *= -1;
            }
            //-----
            COLOR = new Color(RGB, RGB, RGB);
            pontos.add(new FrameBuffer(x, y, COLOR));
            //-----
            if (d <= 0) {
                d += incE;
            } else {
                d += incNE;
                y += slope;
            }
        }
    }
}
