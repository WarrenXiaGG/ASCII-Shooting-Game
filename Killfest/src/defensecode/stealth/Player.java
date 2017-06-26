package defensecode.stealth;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {

    public int health;
    private boolean inverted;
    private int XLOC;
    private int YLOC;
    final int Xlength = 124;
    final int Ylength = 248;
    final String[] player = {"  $$$$$$$=  $$$$", "$$$$$$$$$$$$$$$$$", "+$$$$$$$$$$$$$$$$$", " $$$$$~=~~~==$$$$$$", " =~~~~=~~~==~~~~~~", " ~~~~~~~~MMM=~MMM~~", " =~~~~~~~~MM~~~MM=", " ~~~~~=~~~~=~~~~~~", "  ~~~~~~~~~=~~~~~", "    =~=~~~~=~~~", "     ??+===~??", "     ?????????", "     ????????ZZZZZZZZ", "     ????????$~M", "     ????????$", "     ?????????", "     ?????????", "     ?????????", "     ?????????"};

    public Player() {
        health = 100;
        XLOC = 110;
        YLOC = 900;
        inverted = false;
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < 19; i++) {
            g.drawString(player[i], XLOC, i * 13 + YLOC);
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
          g.drawString(healths, XLOC+40, YLOC-40);
          g.setColor(Color.BLACK);
    }

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
        int[] a = {XLOC, XLOC + Xlength, YLOC, YLOC + Ylength};
        return a;
    }
}
