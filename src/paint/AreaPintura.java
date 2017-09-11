
package paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class AreaPintura extends Canvas implements MouseListener, MouseMotionListener, KeyListener {

    private int x1 = -1, y1 = -1, x2 = -1, y2 = -1;

    private boolean limpar = false;
    private boolean preencher = false;
    private boolean recortar = false;
    private boolean pintar = true;
    private FrameBuffer fb;
    private Color corLateral;
    private Color corPincel;
    private Color corPreenchimento;
    private Color corQuadro;
    
    private Boolean isDrawing = false;
    

    public AreaPintura(int largura, int altura, Color corPincel) {
        config(largura, altura, corPincel);
    }
    
    private void config(int largura, int altura, Color corPincel){
        corLateral = Color.lightGray;
        this.corPincel = corPincel;
        corQuadro = Color.WHITE;
        this.corPreenchimento = Color.GREEN;
        
        fb = new FrameBuffer(new Dimension(largura, altura), corLateral, corQuadro); 
         
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(corPincel);

        if (pintar) {
            fb.criarQuadroDePintura(g);
            pintar = false;
        }
        
        if (isDrawing) {
            Bresenham b = new Bresenham(g, fb.getFrameBuffer(), corPincel);
            fb.setFrameBuffer(b.setReta(new Point(x1, y1), new Point(x2, y2)));
        }

        if (preencher) {
            new Preenchimento(x1, y1, corPincel, corPreenchimento, fb, g);
            preencher = false;
        }

        if (recortar) {
            fb.recorte(g);
            recortar = false;
        }

        if (limpar) {
            fb.setCorFundo(fb.getCorFundo());
            fb.criarQuadroDePintura(g);
            limpar = false;
        }
    }

    public void cortar() {
        recortar = true;
        paint(this.getGraphics());
    }

    public void limprar() {
        cortar();
        limpar = true;
        paint(this.getGraphics());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        preencher = true;
        paint(this.getGraphics());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        paint(this.getGraphics());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.isDrawing = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.isDrawing = true;
        x2 = e.getX();
        y2 = e.getY();
        paint(this.getGraphics());
        x1 = x2;
        y1 = y2;
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
