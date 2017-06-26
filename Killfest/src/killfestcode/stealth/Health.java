/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package killfestcode.stealth;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import killfestcode.stealth.Stealth;




/**
 *
 * @author Joi
 */

public class Health {

    public int XLOC, YLOC;

    public Health() {
        this.XLOC = (int) (Math.random() *2700);
        this.YLOC = (int) (Math.random() *1700);
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
        boolean hit = (this. XLOC -250> a[0]) && (this. XLOC-250 < a[1]) && (this. YLOC > a[2]) && (this. YLOC < a[3]);
        if(hit)
        {
            Stealth.first.health = 100;
        }
        return hit;
    }
    public void render(Graphics2D g,int x,int y,boolean tx,boolean ty)
    {
        g.setColor(Color.red);
         x = XLOC-x +1000;
        y = YLOC-y +500;
    
       if(tx == true){
            x = XLOC;
        }
        if(ty == true){
            y = YLOC;
        }
        for(int i = 0; i < healthbox.length; i++)
        {
            g.drawString(healthbox[i], x,  y + (i * 13));
        }
    }
    

}

