/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pencilsimulator;

/**
 *
 * @author joexi
 */
public class PencilSimulator{
    
    public String paperText;
    public Integer pointHealth;
    public Integer MAX_POINT_HEALTH;
    public Integer pencilLength;
    public Integer eraserHealth;
    
    public PencilSimulator(String initialText, Integer initPointHealth, Integer initPencilLength, Integer initEraserHealth){
        paperText = initialText;
        pointHealth = initPointHealth;
        MAX_POINT_HEALTH = initPointHealth;
        pencilLength = initPencilLength;
        eraserHealth = initEraserHealth;
    }
    
    public void addText(String newText){
        paperText = paperText + newText;
        pointHealth = pointHealth - calculateWritingCost(newText);
    }
    
    public Integer calculateWritingCost(String str){
        Integer retCost = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isWhitespace(str.charAt(i))){
                retCost = retCost + 0;
            }        
            else if(Character.isLowerCase(str.charAt(i))){
                retCost = retCost + 1;
            } 
            else if(Character.isUpperCase(str.charAt(i))){
                retCost = retCost + 2;
            }
            else{
                retCost = retCost + 1;
            }
        }
        return(retCost);
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Testing" + " and again...");
    }
    
}
