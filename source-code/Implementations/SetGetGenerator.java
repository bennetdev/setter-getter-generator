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
public class SetGetGenerator{
private Generator gen;

      
    /**
     * Constructor for objects of class SetGetGenerator
     */
    public SetGetGenerator(Generator gen)
    {
        // initialise instance variables
        setGen(gen);
        setterAusgeben();
        getterAusgeben();
    }

    
    private void setterAusgeben(){
        getGen().getWriter().saveToFile("    //Setter");
        for(String s : getGen().getVars()){
            getGen().getWriter().saveToFile("    public void set" + getGen().capitalizeFirst(s.split("\\.")[1]) + "(" + s.split("\\.")[0] + " " +  s.split("\\.")[1] + "){");
            getGen().getWriter().saveToFile("        this." + s.split("\\.")[1] + " = " + s.split("\\.")[1] + ";");
            getGen().getWriter().saveToFile("    }");
        }
        getGen().getWriter().saveToFile("");
    }
    
    private void getterAusgeben(){
        getGen().getWriter().saveToFile("    //Getter");
        for(String s : getGen().getVars()){
            getGen().getWriter().saveToFile("    public " + s.split("\\.")[0] + " get" + getGen().capitalizeFirst(s.split("\\.")[1]) + "(){" );
            getGen().getWriter().saveToFile("        return " + s.split("\\.")[1] + ";");
            getGen().getWriter().saveToFile("    }");
        }    
        getGen().getWriter().saveToFile("");
    }
    
    private Generator getGen(){
        return gen;
    }

    public void setGen(Generator gen) {
        this.gen = gen;
    }

}
