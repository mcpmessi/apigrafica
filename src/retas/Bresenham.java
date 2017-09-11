/*
 * Calculo de reta com bresenham
* MESSIAS PINHEIRO
* CLEBER RODRIGUES
*/
package retas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
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
}
