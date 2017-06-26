/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealth;

import java.awt.Graphics2D;

/**
 *
 * @author Arnold
 */
public class Wall {

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;
    String wall = "■";
    
    public void render(Graphics2D g){
        
        g.drawString(wall, x, y);
    }

}

