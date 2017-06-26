/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defensecode.stealth;

import java.awt.Graphics2D;
import java.util.ArrayList;




/**
 *
 * @author Joi
 */

public class Health {

    public int XLOC, YLOC;

    public Health() {
        this.XLOC = (int) (Math.random() *1500);
        this.YLOC = (int) (Math.random() *500);
    }
    final String[] healthbox = {
        "--------------",
        "|                 |",
        "|       H       |",
        "|                 |",
        "--------------"};
    
    
    public boolean collidesWith(Player p)
    {
        int [] a = p.getHITBOX();
        boolean hit = (this. XLOC > a[0]) && (this. XLOC < a[1]) && (this. YLOC > a[2]) && (this. YLOC < a[3]);
        if(hit)
        {
            Stealth.first.health = 100;
            System.out.println("FULL HEALTH");
        }
        return hit;
    }
    public void render(Graphics2D g)
    {
        for(int i = 0; i < healthbox.length; i++)
        {
            g.drawString(healthbox[i], XLOC,  YLOC + (i * 13));
        }
    }
    

}
