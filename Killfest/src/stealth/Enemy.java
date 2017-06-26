/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealth;

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
    public int mode;
    public int health = 25;

    public Enemy(int XLOC, int YLOC) {
        this.XLOC = XLOC;
        this.YLOC = YLOC;
        mode = Math.round((float) Math.random() * 1 + 1);
        System.out.println(mode);
    }
    private int Xlength = 124;
    private int Ylength = 248;
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
    final String[] player2 = {
        "    __",
        "    '_')",
        "     )(---.",
        "    (( )___\\",
        "   //-\\\\ \\\\"
    };

    public Enemy() {
        XLOC = 1610;
        YLOC = 700;

    }

    public void update() {
        //this.XLOC= this.XLOC-1;

        if (health < 0) {
            ArrayList<Enemy> temp = Stealth.tempenemies.get("destroy");
            temp.add(this);
            Stealth.tempenemies.put("destroy", temp);
            Stealth.score++;

        }
        if (Math.random() < 0.05) {
            if (mode == 1) {

                ArrayList<Bullet> temp = Stealth.tempbullets.get("make");
                temp.add(new Bullet(this.XLOC - 250, this.YLOC + 120, -10));
                Stealth.tempbullets.put("make", temp);
            } else {
                ArrayList<Bullet> temp = Stealth.tempbullets.get("make");
                temp.add(new Bullet(this.XLOC-250, this.YLOC, -10));
                Stealth.tempbullets.put("make", temp);
            }
        }

    }

    public void render(Graphics2D g, int x, int y, boolean xt, boolean yt) {
        x = XLOC - x + 1000;
        y = YLOC - y + 500;

        if (xt == true) {
            x = XLOC;
        }
        if (yt == true) {
            y = YLOC;
        }
        String[] temp;
        if (mode == 1) {
            temp = playeri;
        } else {
            temp = player2;
        }
        for (int i = 0; i < temp.length; i++) {
            if (mode > 2) {
                g.drawString(temp[i], x, i * 13 + y);
            } else {
                g.drawString(temp[i], x, i * 13 + y);
            }
        }
        String healths = "[";
        int counter = 0;

        for (int i = 0; i < 10; i++) {
            if (counter < health / 5) {
                healths += "❤";
            } else {
                healths += " ";
            }
            counter++;
        }
        healths += "]";
        g.setColor(Color.red);
        g.drawString(healths, x + 40, y - 40);
        g.setColor(Color.BLACK);
    }

    public int[] getHITBOX() {
        int[] a = new int[4];
        if (mode == 1) {
            a[0] = XLOC - 200;
            a[1] = XLOC + Xlength - 200;
            a[2] = YLOC - 50;
            a[3] = YLOC + Ylength - 50;
        } else {
            Ylength = 65;
            a[0] = XLOC - 250;
            a[1] = XLOC + Xlength - 250;
            a[2] = YLOC - 30;
            a[3] = YLOC + Ylength - 30;
        }
        return a;

    }

}
