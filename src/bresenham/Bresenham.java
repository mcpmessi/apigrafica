/*
 * Calculo de reta com bresenham
* MESSIAS PINHEIRO
* CLEBER RODRIGUES
*/
package bresenham;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import retas.FrameBuffer;
import retas.MinMaxReta;

/**
 * @author MESSI-PC
 */
public class Bresenham {
    private ArrayList<FrameBuffer> pontos;
    
    //Para recorte e preenchimento
    private Graphics graphic;
    private final int pixelSize = 1;
    private Color corPincel;
    private Color[][] FrameBuffer;
    
    
    public ArrayList<retas.FrameBuffer> getArrayBuffer(MinMaxReta p){
        pontos = new ArrayList<>();
        Bresenham.this.drawRect(p.getX1(), p.getY1(), p.getX2(), p.getY2());
        return pontos;
    }
    /**
     * Desenho de RETA com bresenham
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void drawRect(int x1, int y1, int x2, int y2) {
        //Calculo delta X
        int deltax = Math.abs(x2 - x1);
        //Calculo delta y
        int deltay = Math.abs(y2 - y1);
        
        
        //Sinal de X e Y
        double signumx = Math.signum(x2 - x1);
        double signumy = Math.signum(y2 - y1);
        
        //Controlador de reflexao
        Boolean reflete = false;
        
        int x = x1;
        int y = y1;
        
        if(signumx < 0){
            x -= 1;
        }
        if(signumy < 0){
            y -= 1;
        }
        
        if(deltay > deltax){
            int tempx = deltax;
            deltax = deltay;
            deltay = tempx;
            reflete = true;
        }
        
        //calcular erro
        int erro = 2 * deltay - deltax;
        
        //perfumaria -----------------------
        Random rand = new Random();
        int RGB = rand.nextInt(255);
        int incr = 30;
        Color COLOR;
        //----------------------------------
        
        for(int i = 0; i < deltax; i++){
            System.out.println("("+x+","+y+")");
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
            
            while(erro >= 0){
                if(reflete){
                    x += signumx;
                }else{
                    y += signumy;
                }
                erro = erro - 2 * deltax;
            }
            
            if(reflete){
                y += signumy;
            }else{
                x += signumx;
            }
            erro = erro + 2 * deltay;
        }
    }
    
    
    

    /**
     * Chamar antes de desenhar
     * @param g
     * @param fbuffer
     * @param color
     */
    public void drawRectConfig(Graphics g, Color[][] fbuffer, Color color) {
        graphic = g;
        FrameBuffer = fbuffer;
        corPincel = color;
    }
    /**
     * Desenho à mão-livre com bresenham
     * @param pInicial
     * @param pFinal
     * @return FrameBuffer
     */
    public Color[][] drawRect(Point pInicial, Point pFinal) {

        int x1 = (int) pInicial.getX();
        int y1 = (int) pInicial.getY();
        int x2 = (int) pFinal.getX();
        int y2 = (int) pFinal.getY();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int abs = dx - dy;

        int px, py;

        px = (x1 < x2) ? 1 : -1;
        py = (y1 < y2) ? 1 : -1;

        while ((y1 != y2) || (x1 != x2)) {
            int p = 2 * abs;

            if (p > -dy) {
                abs = abs - dy;
                x1 = x1 + px;
            }
            if (p < dx) {
                abs = abs + dx;
                y1 = y1 + py;
            }
            //preencher o framebuffer e pintar
            try {
                FrameBuffer[x1][y1] = corPincel;
                System.err.println("(" + x1 + "," + y1 + ")>>");
            } catch (Exception ex) {
                System.err.println("Ultrapassou o canvas");
                return FrameBuffer;
            }
            graphic.drawRect(x1, y1, pixelSize, pixelSize);
        }

        return FrameBuffer;
    }
}
