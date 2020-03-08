/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementations;
import Views.*;
import javax.swing.UIManager;
import Controllers.*;
/**
 *
 * @author Bennet
 */
public class Main {
    public static void main(String[] args){
        try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
    } 
    catch (Exception e) {
       // handle exception
    }
        new Controller();
    }
}
