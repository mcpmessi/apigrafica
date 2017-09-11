/*
* MESSIAS PINHEIRO
* CLEBER RODRIGUES
*/
package paint;

import java.awt.Color;
import java.awt.Graphics;

public class Preenchimento {

    public FrameBuffer frameBuffer;
    public Graphics g;

    public Preenchimento(int x, int y, Color corPincel, Color corPreencher, FrameBuffer fb, Graphics g) {
        frameBuffer = fb;
        this.g = g;
        g.setColor(corPreencher);
        init(x, y, corPincel, corPreencher);
    }

    public void init(int x, int y, Color corPincel, Color corPreencher) {

        try {
            if (frameBuffer.getFrameBuffer()[x][y] != corPincel && frameBuffer.getFrameBuffer()[x][y] != corPreencher) {
                System.err.println(">>"+frameBuffer.getFrameBuffer()[x][y].toString());
                g.drawRect(x, y, 1, 1);
                
                frameBuffer.getFrameBuffer()[x][y] = corPreencher;
                init(x + 1, y, corPincel, corPreencher);
                init(x - 1, y, corPincel, corPreencher);
                init(x, y + 1, corPincel, corPreencher);
                init(x, y - 1, corPincel, corPreencher);
            }
        } catch (StackOverflowError ex) {
            System.err.println("..");
        }

    }
}
