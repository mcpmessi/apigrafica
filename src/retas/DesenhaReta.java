
package retas;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JDialog;

class Grid extends Canvas {
    int width, height, rows, columns;
    
    ArrayList<FrameBuffer> coordenadas;

    public Grid(int width, int height, int lines, int columns, ArrayList<FrameBuffer> coordinates) {
        construct(width, height);
        //-----------------------------
        this.rows = lines;
        this.columns = columns;
        this.coordenadas = coordinates;
    }
    
    private void construct(int w, int h){
        this.setSize(width = w, height = h);
    }
    
    @Override
    public void paint(Graphics g) {
        int k_rows_col;
        width = getSize().width;
        height = getSize().height;
        
        int height_linha = height / (rows);
        for (k_rows_col = 0; k_rows_col < rows; k_rows_col++) {
            g.drawLine(0, k_rows_col * height_linha, width, k_rows_col * height_linha);
        }

        int width_coluna = width / (columns);
        for (k_rows_col = 0; k_rows_col < columns; k_rows_col++) {
            g.drawLine(k_rows_col * width_coluna, 0, k_rows_col * width_coluna, height);
        }
        
        //pintar os pontos
        coordenadas.stream().map((re) -> {
            g.setColor(re.getColor());
            return re;
        }).forEachOrdered((re) -> {
            g.fillRect(
                    re.getX() * width_coluna, 
                    re.getY() * height_linha, 
                    width_coluna, 
                    height_linha);
        });
    }
}
/**
 * Criar janela
 **/
public class DesenhaReta extends JDialog {
    
    int w, h, rows, columns;
    String title;
    ArrayList<FrameBuffer> coordinates;
    /**
     * 
     * @param title titulo
     * @param w largura
     * @param h altura
     * @param rows linhas 
     * @param p pontos min max da reta
     * @param columns 
     */
    public DesenhaReta(String title, int w, int h, int rows, int columns, MinMaxReta p) {
        
        this.title = title;
        this.h = h;
        this.w = w;
        this.rows = rows;
        this.columns = columns;
       
        coordinates = new retas.Bresenham().getArrayBuffer(p);
        //
        construct();
    }
    
    private void construct(){
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        this.setSize(w, h);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        Grid grid = new Grid(w, h, rows, columns, this.coordinates);
        
        this.add(grid);
    }
        
  
}

