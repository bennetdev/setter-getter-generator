/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Implementations.*;
import java.io.BufferedReader;
/**
 *
 * @author Bennet
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Controllers.*;
/**
 * Write a description of class Writer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Model
{
    // instance variables - replace the example below with your own

    private Generator generator;
    private File folder;
    private ArrayList<File> files;
    private Controller controller;
    /**
     * Constructor for objects of class Writer
     */
    public Model(Controller controller)
    {
        // initialise instance variables
        setFiles(new ArrayList<>());
        setController(controller);
    }


    public void prepareFile(File file, ArrayList<String> lines, int size){
        try (FileWriter writer = new FileWriter(file, false);
        BufferedWriter bw = new BufferedWriter(writer)) {
        for(int i = 0; i < size; i++){
            bw.write(lines.get(i) + "\n");
        }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
    }

    public void saveToFile(String text){
        //FileWriter zum Speichern

        try (FileWriter writer = new FileWriter(getFolder() + "\\"+ getGenerator().getKlassenName() + ".java", true);
        BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(text + "\n");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }
    public void getFiles(File folder){
        setFolder(folder);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                if(file.getName().split("\\.")[1].equals("java")){
                    files.add(file);
                }
            }
        }
        for(File f : getFiles()){
            getWriteLine(f);
            readFile(f);
        }
        
    }
    public void readFile(File file){
        setGenerator(new Generator(file.getName().split("\\.")[0], this));
        
        String type = "";
        String name = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        
             String strCurrentLine;
        
             while ((strCurrentLine = br.readLine()) != null) {
                 for(int i = 0; i < strCurrentLine.split(" ").length;i++){
                    if(strCurrentLine.split(" ")[i].equals("private")){
                        type = strCurrentLine.split(" ")[i + 1];
                        name = strCurrentLine.split(" ")[i + 2].split(";")[0];
                        getGenerator().getVars().add(type + "." + name);
                    }
                 } 
             }

             saveToFile("\n");
             getGenerator().execute();
             
        
          } catch (IOException e) {
           e.printStackTrace();
        }
    }
    
    public void getWriteLine(File file){
        int size = 0;
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        
            
             String strCurrentLine;
        
             while ((strCurrentLine = br.readLine()) != null) {
                list.add(strCurrentLine);
             }
             for(int i = list.size() - 1; i >= 0; i--){
                 if(list.get(i).contains("}")){
                     size = i;
                     break;
                 }
             }
          } catch (IOException e) {
           e.printStackTrace();
        }
        prepareFile(file, list, size);
    }

    public File getFolder() {
        return folder;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }
    public Generator getGenerator(){
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }
    
    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}


