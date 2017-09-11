
package paint;

import java.awt.*;

public class Bresenham {

    private int pixel = 1;
    private Graphics gr;
    private Color[][] FrameBuffer;
    private Color corPincel;

    public Bresenham(Graphics g, Color[][] fb, Color cor) {
        gr = g;
        FrameBuffer = fb;
        corPincel = cor;
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

        while ((x1 != x2) || (y1 != y2)) {
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

            gr.drawRect(x1, y1, pixel, pixel);

        }

        return FrameBuffer;
    }

}
