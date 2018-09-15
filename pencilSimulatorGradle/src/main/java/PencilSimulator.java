/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pencilsimulator;

import java.util.Queue;
import java.util.LinkedList;

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
    public Queue<Integer> erasedWordLocs;
    
    public PencilSimulator(String initialText, Integer initPointHealth, Integer initPencilLength, Integer initEraserHealth){
        paperText = initialText;
        pointHealth = initPointHealth;
        MAX_POINT_HEALTH = initPointHealth;
        pencilLength = initPencilLength;
        eraserHealth = initEraserHealth;
        erasedWordLocs = new LinkedList<Integer>();
    }
    
    public Integer insertText(String iText){
        if(erasedWordLocs.isEmpty()){
            System.out.println("Cannot insert anywhere!");
            return(-1);
        }
        String tempPaperText = "";        
        Integer insertStart = erasedWordLocs.remove();
        Integer insertIter = 0;
        for(int k = 0; k < paperText.length(); k++){
            if(k < insertStart){
                tempPaperText = tempPaperText.concat(paperText.substring(k, k+1));
            }
            else if(k >= insertStart && insertIter < iText.length()){
                if(pointHealth > 0){
                    if(!Character.isSpaceChar(paperText.charAt(k))){
                        tempPaperText = tempPaperText.concat("@");
                    }
                    else{
                        tempPaperText = tempPaperText.concat(iText.substring(insertIter, insertIter+1));
                    }
                }
                else{
                    if(!Character.isSpaceChar(paperText.charAt(k))){
                        tempPaperText = tempPaperText.concat(paperText.substring(k, k+1));
                    }
                    else{
                        tempPaperText = tempPaperText.concat(" ");
                    }
                }
                pointHealth = pointHealth - calculateWritingCost("" + iText.charAt(insertIter));
                if(pointHealth < 0){
                    pointHealth = 0;
                }
                insertIter = insertIter + 1;
            }
            else{
                tempPaperText = tempPaperText.concat(paperText.substring(k, k+1));
            }
        }
        
        paperText = tempPaperText;
        return(0);
    }
    
    public Integer eraseText(String eraseTarget){
        int prevPos = 0;        
        int currPos = paperText.indexOf(eraseTarget, prevPos);
        int tempPos;
        String tempPaperText = "";
        while(prevPos-1 != currPos && currPos+1 < paperText.length()){
            prevPos = currPos+1;
            tempPos = paperText.indexOf(eraseTarget, prevPos);
            if(tempPos != -1){
               currPos = tempPos; 
            }            
        }
        
        if(currPos != -1){
            //Store currPos as we embed backward erasing capability beyond here
            erasedWordLocs.add(currPos);
            tempPos = currPos + eraseTarget.length() - 1;
            if(eraserHealth < calculateErasureCost(eraseTarget)){
                currPos = tempPos - eraserHealth - whiteSpaceCount(eraseTarget) + 1;
            }
            for(int k = 0; k < paperText.length(); k++){
                if(k < currPos){
                    tempPaperText = tempPaperText.concat(paperText.substring(k, k+1));
                }
                else if(k >= currPos && k <= tempPos && eraserHealth > 0){
                    tempPaperText = tempPaperText.concat(" ");
                    if(calculateErasureCost(paperText.substring(k, k+1)) > 0){
                        eraserHealth = eraserHealth - 1;
                    }
                }
                else{
                    tempPaperText = tempPaperText.concat(paperText.substring(k, k+1));
                }
            }
            paperText = tempPaperText;
        }
        
        return(currPos);
    }
    
    public Integer sharpenPencil(){
        if(pencilLength > 0){
            pointHealth = MAX_POINT_HEALTH;
            pencilLength = pencilLength - 1;
        }
        return(pointHealth);
    }
    
    public Integer addText(String newText){
        Integer tempPointHealth = pointHealth - calculateWritingCost(newText);
        Integer characterCost;
        String textToWrite = "";        
        if(tempPointHealth < 0){
            for(int j = 0; j < newText.length(); j++){
                if(pointHealth > 0){
                    String charToWrite = "" + newText.charAt(j);
                    characterCost = calculateWritingCost(charToWrite);
                    pointHealth = pointHealth - characterCost;
                    textToWrite = textToWrite + charToWrite;
                }
                else{
                    textToWrite = textToWrite + " ";
                }                
            }
            paperText = paperText + textToWrite;
            pointHealth = 0;
        }
        else{
            paperText = paperText + newText;
            pointHealth = pointHealth - calculateWritingCost(newText);
        }
        System.out.println(tempPointHealth.toString());                
        
        return(tempPointHealth);
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
    
    public Integer calculateErasureCost(String str){
        Integer retCost = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isWhitespace(str.charAt(i))){
                retCost = retCost + 0;
            }        
            else{
                retCost = retCost + 1;
            }
        }
        return(retCost);
    }
    
    public Integer whiteSpaceCount(String str){
        Integer retCount = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isWhitespace(str.charAt(i))){
                retCount = retCount + 1;
            }  
        }
        return(retCount);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Testing" + " and again...");
    }
    
}
