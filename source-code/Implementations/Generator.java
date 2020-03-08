/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementations;

/**
 *
 * @author Bennet
 */
import Models.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Write a description of class Generator here.
 *
 * Bennet Märtin
 * 3.0
 */
public class Generator
{
    // instance variables - replace the example below with your own
    private ArrayList<String> vars;
    private String klassenName;
    private Model model;
    private String path;
    /**
     * Constructor for objects of class Generator
     */
    public Generator(String klassenName, Model model)
    {
        setVars(new ArrayList<>());
        model = model;
        setKlassenName(klassenName);
        setModel(model);
        
    }

    public void execute(){
        konstruktorAusgeben();    
        new SetGetGenerator(this);
        getWriter().saveToFile("}");
    }

    private String checkKomma(int length, int index){
        String returnString = "";
        if(length <= 1){
            returnString = "";
        }
        else{
            if(getVars().size() == index + 2 && getVars().get(index  + 1).contains("ArrayList")){
                returnString = "";
            }
            else{
                returnString = ", ";
            }
                
        }
        return returnString;
    }
    private String getParameters(){
        String parameters = "(";
        int length = (getVars().size());
        int i = 0;
        for(String s : getVars()){
            if(!s.contains("ArrayList")){
                String typ = s.split("\\.")[0];
                String name = s.split("\\.")[1];
                parameters += (typ + " " + name + checkKomma(length, i));
            }
            length--;
            i++;
        }
        return parameters;
    }
    public String capitalizeFirst(String word){
        String newWord = "";
        for(int i = 0; i < word.length(); i++){
            if(i == 0){
                newWord += Character.toUpperCase(word.toCharArray()[i]);
            }
            else{
                newWord += word.toCharArray()[i];
            }
        }
        return newWord;
    }
    public void konstruktorAusgeben(){
        getWriter().saveToFile("    public " + getKlassenName() + getParameters() + "){");
        for(String s : getVars()){
            getWriter().saveToFile("        " + "set" + capitalizeFirst(s.split("\\.")[1]) + "(" + (s.split("\\.")[0].contains("ArrayList") ? "new ArrayList<>()" : s.split("\\.")[1]) + ");");
        }
        getWriter().saveToFile("    }");
        getWriter().saveToFile("");
    }
    
    public String getKlassenName(){
        return klassenName;
    }
    private void setKlassenName(String klassenName){
        this.klassenName = klassenName;
    }
    public ArrayList<String> getVars(){
        return vars;
    }
    public String getPath(){
        return path;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    public Model getWriter(){
        return model;
    }

    public void setVars(ArrayList<String> vars) {
        this.vars = vars;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
}   
    
