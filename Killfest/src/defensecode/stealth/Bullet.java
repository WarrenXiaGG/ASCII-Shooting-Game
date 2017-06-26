package defensecode.stealth;

import java.awt.Graphics2D;
import java.util.ArrayList;
import static defensecode.stealth.Stealth.tempbullets;

public class Bullet {
    public int XLOC;
    public int YLOC;
    public int speed;
    final String bullet = "***";
    
    public Bullet() {
        XLOC = 115;
        YLOC = 912;
        speed = 10;
    }

    public Bullet(int x, int y, int s)
    {
        XLOC = x;
        YLOC = y;
        speed = s;
    }
    public void render(Graphics2D g) throws InterruptedException{
        
       
        g.drawString(bullet, XLOC,  YLOC);
    }
    public boolean update(){
        setXLOC(getXLOC()+speed);
        if(getXLOC() >= 2010|| getXLOC() < 0){
            Stealth.tempbullets.get("destroy").add(this);
        }
        return true;
            
        
        
    }
    public String getBullet() {
        return bullet;
    }

    public int getXLOC() {
        return XLOC;
    }

    public void setXLOC(int XLOC) {
        this.XLOC = XLOC;
    }

    public int getYLOC() {
        return YLOC;
    }

    public void setYLOC(int YLOC) {
        this.YLOC = YLOC;
    } 
    
//    public boolean collidesWith(Player p)
//    {
//        int [] a = p.getHITBOX();
//        return (this. XLOC > a[0]) && (this. XLOC < a[1]) && (this. YLOC > a[2]) && (this. YLOC < a[3]);
//    }
    public boolean collidesWith(Enemy p)
    {
        int [] a = p.getHITBOX();
        boolean hit = (this. XLOC > a[0]) && (this. XLOC < a[1]) && (this. YLOC > a[2]) && (this. YLOC < a[3]);
        if(hit)
        {

            Stealth.tempbullets.get("destroy").add(this);
           
        }
        return hit;
    }
        public boolean collidesWith(Player p)
    {
        int [] a = p.getHITBOX();
        boolean hit = (this. XLOC > a[0]) && (this. XLOC < a[1]) && (this. YLOC > a[2]) && (this. YLOC < a[3]);
        if(hit)
        {
            Stealth.tempbullets.get("destroy").add(this);
           
        }
        return hit;
    }
}