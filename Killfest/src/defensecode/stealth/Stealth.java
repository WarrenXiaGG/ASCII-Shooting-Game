package defensecode.stealth;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Stealth extends JPanel implements KeyListener {

    static JFrame f = new JFrame();
    static int cooldown = 0;
    public static int score = 0;
    int xoff = 0;
    int yoff = 1000 - (19 * 13);
    static ArrayList<Enemy> enemies = new ArrayList<>();
    public static HashMap<String, ArrayList<Enemy>> tempenemies = new HashMap();
    static ArrayList<Health> healths = new ArrayList<>();
    public static HashMap<String, ArrayList<Health>> temphealths = new HashMap();
    static ArrayList<Bullet> bullets = new ArrayList<>();
    public static HashMap<String, ArrayList<Bullet>> tempbullets = new HashMap();

    static Player first = new Player();
    Graphics2D g2;
    // Bullet one = new Bullet(first.getXLOC()+120, first.getYLOC()+159, 10);

    final int PAD = 20;

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        update();
        render(g2);

    }

    public void update() {
        first.setXLOC(xoff);
        first.setYLOC(yoff);
        for (Health e : healths) {
            if (e.collidesWith(first)) {
                System.out.println("collected health");
                ArrayList<Health> temp = Stealth.temphealths.get("destroy");
                temp.add(e);
                Stealth.temphealths.put("destroy", temp);
            }
        }
        for (Bullet bullet : bullets) {
            bullet.update();
            for (Enemy enemy : enemies) {
                if (bullet.collidesWith(enemy)) {
                    enemy.health -= 2;
                }
                if (bullet.collidesWith(first)) {
                    first.health -= 0.5;
                }
                if (first.health <= 0) {
                    try {
                        Thread.sleep(1000);
                        f.dispose();
                        JOptionPane.showMessageDialog(null, "Your score is: " + score);
                        System.exit(0);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Stealth.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        }

        for (Bullet e : tempbullets.get("destroy")) {
            bullets.remove(e);
        }

        for (Bullet e : tempbullets.get("make")) {
            bullets.add(e);
        }
        
        tempbullets.put("destroy", new ArrayList<>());
        tempbullets.put("make", new ArrayList<>());


        for (Enemy e : enemies) {
           e.update();
        }

        for (Health e : temphealths.get("destroy")) {
            healths.remove(e);
            System.out.println("test health" + e.toString());
        }

        for (Health e : temphealths.get("make")) {
            healths.add(e);
        }
        
        temphealths.put("destroy", new ArrayList<>());
        temphealths.put("make", new ArrayList<>());
        
        enemies.removeAll(tempenemies.get("destroy"));
        enemies.addAll(tempenemies.get("make"));
        
        tempenemies.put("destroy", new ArrayList<>());
        tempenemies.put("make", new ArrayList<>());
    }

    public void render(Graphics2D g) {
        first.render(g);
        try {
            for (Bullet e : bullets) {
                e.render(g);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Stealth.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Enemy e : enemies) {
            e.render(g);
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Stealth.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Health e : healths) {
            e.render(g);
        }
    }

    public static void main(String[] args) {

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Stealth());
        f.setState(Frame.NORMAL);
        f.setSize(2000, 1000);
        f.setLocation(0, 0);
        f.setVisible(true);
        while (true) {
            f.repaint();
            if (cooldown < 0) {
                enemies.add(new Enemy());
                cooldown = 30000000;
                if (Math.random() < 0.2 && healths.size() <= 3) {
                    healths.add(new Health());
                }
            } else {
                cooldown--;
            }

        }

//        while(true) {
//            Thread.sleep(100);       
//            updateLogic();
//            f.repaint();
//        }
    }

    public Stealth() {
        tempbullets.put("make", new ArrayList<Bullet>());
        tempbullets.put("destroy", new ArrayList<Bullet>());
        tempenemies.put("make", new ArrayList<Enemy>());
        tempenemies.put("destroy", new ArrayList<Enemy>());
        temphealths.put("make", new ArrayList<Health>());
        temphealths.put("destroy", new ArrayList<Health>());
        enemies.add(new Enemy());
        this.g2 = (Graphics2D) g2;
        bindKeyWith("y.up", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), new VerticalAction(-15));
        bindKeyWith("y.down", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), new VerticalAction(15));
        bindKeyWith("x.left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), new HorizontalAction(-15));
        bindKeyWith("x.right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), new HorizontalAction(15));
        bindKeyWith("bullet", KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), new BulletAction(100));
    }

    protected void bindKeyWith(String name, KeyStroke keyStroke, Action action) {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(keyStroke, name);
        am.put(name, action);
    }

    public abstract class MoveAction extends AbstractAction {

        private int delta;

        public MoveAction(int delta) {
            this.delta = delta;
        }

        public int getDelta() {
            return delta;
        }

        protected abstract void applyDelta();

        @Override
        public void actionPerformed(ActionEvent e) {
            applyDelta();
        }

    }

    public class VerticalAction extends MoveAction {

        public VerticalAction(int delta) {

            super(delta);
        }

        @Override
        protected void applyDelta() {
            int delta = getDelta();
            yoff += delta;
            if (yoff < 0) {
                yoff = 0;

            } else if (yoff + 100 > getHeight()) {
                yoff = getHeight() - 100;
            }
            first.setYLOC(yoff);

            repaint();
        }

    }

    public class HorizontalAction extends MoveAction {

        public HorizontalAction(int delta) {
            super(delta);
        }

        @Override
        protected void applyDelta() {
            int delta = getDelta();
            xoff += delta;

            if (xoff < 0) {
                xoff = 0;

            } else if (xoff + 100 > getWidth()) {
                xoff = getWidth() - 100;
            }
            first.setXLOC(xoff);
            repaint();
        }

    }

    public class BulletAction extends MoveAction {

        public BulletAction(int delta) {
            super(delta);
        }

        @Override
        protected void applyDelta() {
            ArrayList<Bullet> temp = Stealth.tempbullets.get("make");
            temp.add(new Bullet(first.getXLOC() + 120, first.getYLOC() + 159, 15));
            Stealth.tempbullets.put("make", temp);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
