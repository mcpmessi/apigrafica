/*
* MESSIAS PINHEIRO
* CLEBER RODRIGUES
*/
package paint;

import java.awt.*;

public class Bresenham {

    private Graphics graphic;
    private int pixelSize = 1;
    private Color corPincel;
    private Color[][] FrameBuffer;

    public Bresenham(Graphics g, Color[][] fbuffer, Color color) {
        graphic = g;
        FrameBuffer = fbuffer;
        corPincel = color;
    }

    public Color[][] setReta(Point pInicial, Point pFinal) {

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
