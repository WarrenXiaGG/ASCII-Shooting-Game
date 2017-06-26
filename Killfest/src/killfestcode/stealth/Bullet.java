package killfestcode.stealth;

import java.awt.Graphics2D;
import java.util.ArrayList;
import killfestcode.stealth.Stealth;
import static killfestcode.stealth.Stealth.tempbullets;

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
    public void render(Graphics2D g,int x,int y,boolean xt,boolean yt) throws InterruptedException{
        
        x = XLOC-x+1000;
        y = YLOC-y+500;   
        if(xt == true){
            x = XLOC;
        }
        if(yt == true){
            y = YLOC;
        }
        g.drawString(bullet, x+250,  y+40);
        if(x>= 2010|| x<-600){
             ArrayList<Bullet> temp = Stealth.tempbullets.get("destroy");
            temp.add(this);
            Stealth.tempbullets.put("destroy", temp);
        }
    }
    public boolean update(){
        setXLOC(getXLOC()+speed);
        
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
            ArrayList<Bullet> temp = Stealth.tempbullets.get("destroy");
            
            temp.add(this);
            Stealth.tempbullets.put("destroy", temp);
            p.health = p.health - 1;
        }
        return hit;
    }
    public boolean collidesWith(Player p)
    {
        int [] a = p.getHITBOX();
        boolean hit = (this. XLOC > a[0]) && (this. XLOC < a[1]) && (this. YLOC > a[2]) && (this. YLOC < a[3]);
        if(hit)
        {
            ArrayList<Bullet> temp = Stealth.tempbullets.get("destroy");
            
            temp.add(this);
            Stealth.tempbullets.put("destroy", temp);
            p.health = p.health - 3;
        }
        return hit;
    }
}
