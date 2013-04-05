/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean
 */
public class DescriptionGenerator {
    String template;
    String description;
    
    public DescriptionGenerator(String html){
        this.template = html;
    }
    
    public void modify(String pattern, String replacement){
        //if there's been no modifications yet then load base template into description
        if(this.description == null || this.description.trim().equals("")){
            this.description = this.template;
        }
        this.description = this.description.replace(pattern, replacement);
    }
    
    public String getDescription(){
        return this.description;
    }
    
    private void generateHTMLFile(){
        
    }
}
