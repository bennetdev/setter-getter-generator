/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Views.*;
import Models.*;

/**
 *
 * @author Bennet
 */
public class Controller{
    private ChooseView chooseView;
    private Model model;
    
    public Controller(){
        
        this.model = new Model(this);
        new ChooseView(this, model).setVisible(true);
    }
    
    
    //Setter and Getter
    public ChooseView getChooseView() {
        return chooseView;
    }

    public void setChooseView(ChooseView chooseView) {
        this.chooseView = chooseView;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
