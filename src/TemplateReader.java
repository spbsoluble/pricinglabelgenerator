/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.*;

public class TemplateReader {
    private String fileName;
    private String html = "";
    
    public TemplateReader(){
        this.fileName = "default.html";
        readFile();
    }
    
    public TemplateReader(String filename){
        this.fileName = filename;
        readFile();
    }
    
    private boolean readFile(){
        InputStream in = null;
        try {
            if(this.fileName == null || this.fileName.trim().equals(""))
                return false;
            File f = new File(this.fileName);
            in = new FileInputStream(f);
            this.html = IOUtils.toString(in);
            //htmlFile = htmlFile.replace("[product_title]", "HUR IS MAH TITLE LOLZ");
            //System.out.println(htmlFile.contains("[product_title]"));
            //System.out.print(this.html);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TemplateReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(TemplateReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
    }
    
    public String getHTML(){
        return this.html;
    }
}
