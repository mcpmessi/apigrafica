package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class FrameBuffer {

    private boolean[][] memoryframeBuffer;
    private Color[][] frameBuffer;
    private Dimension dimensaoBackground, tamanhoQuadro;
    private Color corFundo, corQuadro;

    public FrameBuffer(Dimension dm, Color corFundo, Color corQuadro) {
        init(dm, corFundo, corQuadro);

    }

    private void init(Dimension dm, Color corFundo, Color corQuadro) {
        dm.setSize(dm.getWidth(), dm.getHeight());
        setDimensionBkg(dm);
        setFrameBuffer(new Color[dm.width][dm.height]);
        setTamanhoQuadro(new Dimension(dm.width / 2, dm.height / 2));
        setMemoryframeBuffer(new boolean[getDimensionBkg().width][getDimensionBkg().height]);
        setCorFundo(corFundo);
        setCorQuadro(corQuadro);
    }

    public void setMemoryframeBuffer(boolean[][] mFb) {
        memoryframeBuffer = mFb;
        for (int i = 0; i < getDimensionBkg().width; i++) {
            for (int j = 0; j < getDimensionBkg().height; j++) {
                memoryframeBuffer[i][j] = false;
            }
        }

        for (int i = getTamanhoQuadro().width / 2; i < getTamanhoQuadro().width + getTamanhoQuadro().width / 2; i++) {
            for (int j = getTamanhoQuadro().height / 2; j < getTamanhoQuadro().height + getTamanhoQuadro().height / 2; j++) {
                memoryframeBuffer[i][j] = true;
            }
        }
    }

    public boolean[][] getMemoryframeBuffer() {
        return memoryframeBuffer;
    }

    public void recorte(Graphics g) {
        g.setColor(getCorFundo());
        g.fillRect(0, 0, getDimensionBkg().width, getTamanhoQuadro().height / 2);
        g.fillRect(0, getTamanhoQuadro().height + getTamanhoQuadro().height / 2, getDimensionBkg().width, getTamanhoQuadro().height / 2);
        g.fillRect(0, 0, getTamanhoQuadro().width / 2, getDimensionBkg().height);
        g.fillRect(getTamanhoQuadro().width + getTamanhoQuadro().width / 2, 0, getTamanhoQuadro().width / 2, getDimensionBkg().height);

        for (int i = 0; i < getDimensionBkg().width; i++) {
            for (int j = 0; j < getDimensionBkg().height; j++) {
                if (getMemoryframeBuffer()[i][j] == false) {
                    getFrameBuffer()[i][j] = getCorFundo();
                }
            }
        }
    }

    public void criarQuadroDePintura(Graphics g) {
        g.setColor(getCorQuadro());
        g.drawRect(0, 0, getDimensionBkg().width, getDimensionBkg().height);
        g.setColor(getCorQuadro());
        g.fillRect(
                getTamanhoQuadro().width / 2, 
                getTamanhoQuadro().height / 2, 
                getTamanhoQuadro().width, 
                getTamanhoQuadro().height);
    }

    public Color getCorQuadro() {
        return corQuadro;
    }

    public void setCorQuadro(Color corQuadro) {
        this.corQuadro = corQuadro;
    }

    public Color getCorFundo() {
        return corFundo;
    }

    public void setCorFundo(Color corFundo) {
        this.corFundo = corFundo;

        for (int i = 0; i < getDimensionBkg().width; i++) {
            for (int j = 0; j < getDimensionBkg().height; j++) {
                getFrameBuffer()[i][j] = corFundo;
                getFrameBuffer()[i][0] = Color.BLACK;
                getFrameBuffer()[i][getDimensionBkg().height - 1] = Color.BLACK;
                getFrameBuffer()[0][j] = Color.BLACK;
                getFrameBuffer()[getDimensionBkg().width - 1][j] = Color.BLACK;
            }
        }
    }

    public Color[][] getFrameBuffer() {
        return frameBuffer;
    }

    public void setFrameBuffer(Color[][] frameBuffer) {
        this.frameBuffer = frameBuffer;
    }

    public Dimension getDimensionBkg() {
        return dimensaoBackground;
    }

    public void setDimensionBkg(Dimension dm) {
        dimensaoBackground = dm;
    }

    public Dimension getTamanhoQuadro() {
        return tamanhoQuadro;
    }

    public void setTamanhoQuadro(Dimension dm) {
        tamanhoQuadro = dm;
    }

}
