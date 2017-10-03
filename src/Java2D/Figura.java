/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java2D;

import java.awt.Color;
import java.awt.geom.GeneralPath;

/**
 *
 * @author edwin
 */


public class Figura {

    /**
     * @return the X
     */
   public float[] getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(float[] X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public float[] getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(float[] Y) {
        this.Y = Y;
    }

    /**
     * @return the path
     */
    public GeneralPath getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(GeneralPath path) {
        this.path = path;
    }

    /**
     * @return the col
     */
    public Color getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(Color col) {
        this.col = col;
    }

    /**
     * @return the velx
     */
    public int getVelx() {
        return velx;
    }

    /**
     * @param velx the velx to set
     */
    public void setVelx(int velx) {
        this.velx = velx;
    }

    /**
     * @return the vely
     */
    public int getVely() {
        return vely;
    }

    /**
     * @param vely the vely to set
     */
    public void setVely(int vely) {
        this.vely = vely;
    }
    
    private float X[];//el vector de x
    private float Y[] ;//el vector de y
    
    private GeneralPath path;
    private Color col;
    private int velx;
    private int vely;
}
