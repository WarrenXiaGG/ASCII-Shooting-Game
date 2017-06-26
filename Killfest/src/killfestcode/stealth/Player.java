package killfestcode.stealth;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
    public int health;
    public int ammo = 50;
    private int timer = 0;
    private boolean inverted;
    public boolean reloading;
    private int XLOC;
    private int YLOC;
    final int Xlength = 124;
    final int Ylength = 248;
    final String[] player = {"  $$$$$$$=  $$$$", "$$$$$$$$$$$$$$$$$", "+$$$$$$$$$$$$$$$$$", " $$$$$~=~~~==$$$$$$", " =~~~~=~~~==~~~~~~", " ~~~~~~~~MMM=~MMM~~", " =~~~~~~~~MM~~~MM=", " ~~~~~=~~~~=~~~~~~", "  ~~~~~~~~~=~~~~~", "    =~=~~~~=~~~", "     ??+===~??", "     ?????????", "     ????????ZZZZZZZZ", "     ????????$~M", "     ????????$", "     ?????????", "     ?????????", "     ?????????", "     ?????????"};
    
    public Player()
            
    {
        health = 100;
        XLOC = 110;
        YLOC = 900;
        inverted = false;
    }
    public void render(Graphics2D g,int x,int y){       
        for (int i = 0; i < 19; i++) {
            g.drawString(player[i], x, i * 13 + y);
          }
         String healths="[";
          int counter=0;
          
          for(int i = 0;i<20;i++){
              if(counter < health/5){
                  healths+="❤";
              }else{
                  healths+=" ";
              }
              counter++;
          }
          healths+="]";
          g.setColor(Color.red);
          g.drawString(healths, x-40, y-40);
           g.setColor(Color.BLACK);
          
            g.drawString("Ammo Left: "+ ammo, 0, 100);
           
    }
    public void update(){
    if(reloading){
    if(timer > 90){
        reloading = false;
        ammo = 45;
        timer = 0;
    }else{
        timer++;

    }
    }}
    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
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

    public String[] getPlayer() {
            return player;
    }
    
    public int[] getHITBOX() {
        int[] a = {XLOC-300, XLOC + Xlength-300, YLOC-50, YLOC + Ylength-50};
        return a;
    }
}