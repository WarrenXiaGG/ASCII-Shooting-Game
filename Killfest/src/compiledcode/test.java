/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiledcode;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Joi
 */
public class test {

    public static void main(String[] args) throws IOException {
        
        while(true){
        String b = JOptionPane.showInputDialog("Input defense/killfest, or null to terminate");
        if(b.equalsIgnoreCase("defense"))
        {
        File file = new File("defend.jar");
        Desktop a = Desktop.getDesktop();
        a.open(file);
        }
        else if(b.equalsIgnoreCase("killfest"))
        {
            File file = new File("kill.jar");
        Desktop a = Desktop.getDesktop();
        a.open(file);
        } else 
            break;
        }
    }
}