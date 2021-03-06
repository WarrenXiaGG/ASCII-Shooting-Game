/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defensecode.stealth;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Arnold
 */
public class Enemy {
    public int XLOC;
    public int YLOC;
    public int health = 25;
    private int Xlength=124;
    private int Ylength=248;
    final String[] playeri = {
        "          $$$$  =$$$$$$$  ",
        "        $$$$$$$$$$@$$$$$$",
        "      $$$$$$$$$$$$$$$$$+",
        "    $$$$$$@=~~~=~$$$$$",
        "      ~~~~~~==~~~=~~~~=",
        "    ~~MMM~=MMM~~~~~~~~",
        "      =MM~~~MM~~~~~@~~=",
        "      ~~~WW~=@~~~=~~~~~",
        "      ~~~~~=~~~~~~~~~",
        "            ~~~=~~~~=~=",
        "              ??~===+??",
        "              ??@??????",
        "ZZZZZZZZ???????",
        "         M~$??@?????",
        "              $????????",
        "              ????@????",
        "              ??????@??",
        "              ?????????",
        "              ??????@??"}; 
    public Enemy()
            
    {
        XLOC = 1900;
        YLOC = (int) (Math.random()*1000);
        
    }
public Enemy(int XLOC, int YLOC) {
        this.XLOC = XLOC;
        this.YLOC = YLOC;
    }
    public void update(){
        this.XLOC= this.XLOC-1;
        if(this.XLOC  < 0){
            ArrayList<Enemy> temp = Stealth.tempenemies.get("destroy");
            temp.add(this);
            Stealth.tempenemies.put("destroy", temp);
            Stealth.first.health-=20;
        }
        System.out.println(health);
        if(health<0){
             ArrayList<Enemy> temp = Stealth.tempenemies.get("destroy");
            
            temp.add(this);
            Stealth.score++;
            Stealth.tempenemies.put("destroy", temp);
            
        }
        if(Math.random()<0.005)
        {
            ArrayList<Bullet> temp = Stealth.tempbullets.get("make");
            temp.add(new Bullet(this.XLOC - 20, this.YLOC +150, -10));
            Stealth.tempbullets.put("make", temp);
        }
            
    }
    public void render(Graphics2D g){
          for (int i = 0; i < 19; i++) {
            g.drawString(playeri[i], XLOC, i * 13 + YLOC);
          }
          String healths="[";
          int counter=0;
          
          for(int i = 0;i<10;i++){
              if(counter < health/5){
                  healths+="❤";
              }else{
                  healths+=" ";
              }
              counter++;
          }
          healths+="]";
          g.setColor(Color.red);
          g.drawString(healths, XLOC+40, YLOC-40);
           g.setColor(Color.BLACK);
    }
    public int[] getHITBOX ()
    {
        int a[] = {XLOC,XLOC +Xlength,YLOC, YLOC+Ylength};
        return a;
    }
    
    
}
