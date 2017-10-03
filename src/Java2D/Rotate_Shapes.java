/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Edwin GitHub:D0naldo
 */
public class Rotate_Shapes extends JPanel implements KeyListener {

    /**
     * @param args the command line arguments
     */
    int W = 600;
    int H = 600;

    int Cant = 4;

    /****************Cambiar por tu Figura***************/
    float xs[] = {-2, 0, 0, 2, 2, 0, -2, -4, -4, -2};
    float ys[] = {2, 2, 0, 0, -2, -2, -2, -2, 0, 0};
    /****************Cambiar por tu Figura***************/
    
    public static ArrayList<Figura> figuras = new ArrayList<>();//se crea un arraylist

    int cores = 2; //cantidad nucleos 
    int mycore; //nucleos del sistema

    public Rotate_Shapes() throws HeadlessException {
        super.setPreferredSize(new Dimension(W, H));
        super.addKeyListener(this);
        mycore = Runtime.getRuntime().availableProcessors();

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(this.getWidth() / 2, this.getHeight() / 2);
        pintarfig(g2);
    }

    public void pintarfig(Graphics2D g2) {
        for (int i = 0; i < figuras.size(); i++) {
            g2.setColor(figuras.get(i).getCol());
            g2.fill(figuras.get(i).getPath());

            g2.setColor(Color.BLACK);
            g2.draw(figuras.get(i).getPath());

        }
    }

    public void moverF() {
        try {

            int anchoFr = super.getWidth();
            int altoFr = super.getHeight();

            for (int i = 0; i < figuras.size(); i++) {

                int anchoFig = figuras.get(i).getPath().getBounds().width;

                int altofig = figuras.get(i).getPath().getBounds().height;
                int posXFig = figuras.get(i).getPath().getBounds().x;
                int posYFig = figuras.get(i).getPath().getBounds().y;
                //--------------------------------------------------------------------------- X

                if ((anchoFig + posXFig) >= anchoFr - figuras.get(i).getVelx()) {

                    int nuevaVelX = figuras.get(i).getVelx() * -1;
                    figuras.get(i).setVelx(nuevaVelX);
                }

                if (posXFig + figuras.get(i).getVelx() <= 0) {

                    int nuevaVelX = figuras.get(i).getVelx() * -1;
                    figuras.get(i).setVelx(nuevaVelX);
                }

                //--------------------------------------------------------------------------- Y
                if ((altofig + posYFig) >= altoFr - figuras.get(i).getVely()) {
                    int nuevavely = figuras.get(i).getVely() * (-1);
                    figuras.get(i).setVely(nuevavely);
                }
                if (posYFig + figuras.get(i).getVely() <= 0) {
                    int nuevavely = figuras.get(i).getVely() * (-1);
                    figuras.get(i).setVely(nuevavely);
                }
                //-------------------------------------------------------------------------------

                //-------------------------------------------------------------------------------       

                for (int j = 0; j < figuras.get(i).getX().length; j++) {

                    figuras.get(i).getX()[j] = figuras.get(i).getX()[j] ;
                    figuras.get(i).getY()[j] = figuras.get(i).getY()[j] ;
                }//finde del for evctor de coordenadas
                figuras.get(i).setPath(this.crearFigura(figuras.get(i).getX(), figuras.get(i).getY()));

            }

            Thread.sleep(100);
            repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rotate_Shapes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//mueve la figuras

    public void rotar(double giro) {
        float x1;
        float x2;
        int o=3;
        Random r = new Random();

        for (int i = 0; i < this.figuras.size(); i++) {

            
            for (int j = 0; j < figuras.get(i).getX().length; j++) {
                x1 = (float) (((figuras.get(i).getX()[j]) * Math.cos(giro)) - ((figuras.get(i).getY()[j]) * Math.sin(giro)));
                x2 = (float) (((figuras.get(i).getX()[j] ) * Math.sin(giro)) + ((figuras.get(i).getY()[j]) * Math.cos(giro)));
                figuras.get(i).getX()[j] = x1;
                figuras.get(i).getY()[j] = x2 ;
            }
            figuras.get(i).setPath(this.crearFigura(figuras.get(i).getX(), figuras.get(i).getY()));
            figuras.get(i).getVelx();
            figuras.get(i).getVely();
        }

        try {
            Thread.sleep(20);
            repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rotate_Shapes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//Rota en 0,0

    public void rotar2(double giro) {//Rota sobre su propio eje
        float x1;
        float lo1 = 0;
        float lo2 = 0;
        float x2;

        for (int i = 0; i < this.figuras.size(); i++) {
            lo1 = (float) figuras.get(i).getPath().getBounds().getCenterX();

            lo2 = (float) figuras.get(i).getPath().getBounds().getCenterY();

            for (int j = 0; j < figuras.get(i).getX().length; j++) {
                x1 = (float) (((figuras.get(i).getX()[j] - lo1) * Math.cos(giro)) - ((figuras.get(i).getY()[j] - lo2) * Math.sin(giro))) + lo1;
                x2 = (float) (((figuras.get(i).getX()[j] - lo1) * Math.sin(giro)) + ((figuras.get(i).getY()[j] - lo2) * Math.cos(giro))) + lo2;
                figuras.get(i).getX()[j] = x1;
                figuras.get(i).getY()[j] = x2;
            }

            figuras.get(i).setPath(this.crearFigura(figuras.get(i).getX(), figuras.get(i).getY()));

        }
        try {
            Thread.sleep(20); //calculas la velocidad ah moverse
            repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rotate_Shapes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void escalar(float vx, float vy, float sx, float sy) {//escala con la tecla A y S
        for (int i = 0; i < this.figuras.size(); i++) {

            for (int j = 0; j < figuras.get(i).getX().length; j++) {
                figuras.get(i).getX()[j] = ((figuras.get(i).getX()[j] - vx) * sx) + vx;
                figuras.get(i).getY()[j] = ((figuras.get(i).getY()[j] - vy) * sy) + vy;
            }

            figuras.get(i).setPath(this.crearFigura(figuras.get(i).getX(), figuras.get(i).getY()));
        }
        
        try {
            Thread.sleep(5);
            repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rotate_Shapes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

     public void crearFigura() {

        for (int i = 0; i < Cant; i++) {
            float nx[] = new float[xs.length];
            float ny[] = new float[ys.length];

            System.arraycopy(xs, 0, nx, 0, xs.length);//copia array de un vector a otro
            System.arraycopy(ys, 0, ny, 0, ys.length);//copia array de un vector a otro

            Random r = new Random();
            int ran = r.nextInt(30);
            for (int j = 0; j < nx.length; j++) {
                nx[j] = xs[j] * (ran == 0 ? 1 : ran);//if terciario
                ny[j] = ys[j] * (ran == 0 ? 1 : ran);//if terciario
            }

            int trasx = r.nextInt(200);
            int trasy = r.nextInt(200);
            for (int j = 0; j < nx.length; j++) {
                nx[j] = nx[j] + (trasx == 0 ? 1 : trasx);
                ny[j] = ny[j] + (trasy == 0 ? 1 : trasx);
            }

            Figura miFigura = new Figura();
            miFigura.setX(nx);
            miFigura.setY(ny);
            miFigura.setCol(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            miFigura.setVelx(r.nextInt(50));
            miFigura.setVely(r.nextInt(30));
            miFigura.setPath(this.crearFigura(nx, ny));
            figuras.add(miFigura);
        }

    }

    public static GeneralPath crearFigura(float[] x, float[] y) {
        GeneralPath figura = new GeneralPath();  // crear objeto GeneralPath
        figura.moveTo(x[0], y[0]);// establecer la coordenada inicial de la ruta general

        for (int a = 0; a < x.length; a++)//Recorre los arreglos para unir punto con punto
        {
            figura.lineTo(x[a], y[a]);
        }
        figura.closePath();//Cierra el grupo de puntos creados
        return figura;
    }

    public static void main(String[] args) {
        Rotate_Shapes r = new Rotate_Shapes();
        JFrame jf = new JFrame();
        jf.add(r);
        jf.setTitle("Figuras");
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setMinimumSize(new Dimension(r.W, r.H));
        jf.setVisible(true);
        jf.addKeyListener(r);
        r.crearFigura();
        for (int i = 0; i < 1000; i++) {
//             r.moverF();
            for (int j = 0; j < figuras.size(); j++)
            {
                r.rotar2(Math.toRadians(1));
                
            }    
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //7throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        switch (ke.getKeyCode()) {

            case KeyEvent.VK_S:
                //System.out.println("derecha");
                for (int i = 0; i < figuras.size(); i++) {
                    this.escalar(figuras.get(i).getX()[1], figuras.get(i).getY()[1], 0.99f, 0.99f);
                }
//                System.out.println("S");
                break;
                
            case KeyEvent.VK_A:
                //System.out.println("derecha");
                for (int i = 0; i < figuras.size(); i++) {
                    this.escalar(figuras.get(i).getX()[1], figuras.get(i).getY()[1], 1.01f, 1.01f);
                }
//                System.out.println("A");
                break;

            default:
                System.out.println("Tecla incorrecta");
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
