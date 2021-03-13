/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entity.Publication;

/**
 *
 * @author Khawla
 */
public class ControlePublication {
    /***************************************************************************************/
    public static boolean controleTitre(Publication p) {
       String str= (p.getTitre()).toLowerCase();
        if(p.getTitre().length()==0) {
            return false;
           
        }
        char[] ch =str.toCharArray();
         for(int i =0; i<ch.length;i++) {
             char c =ch[i];
             if(!((c >='a' && c<='z') ||(String.valueOf(ch).equals(" ")))) {
                 return false;
             }
         }
       return true;

        
    }
    /***************************************************************************************/
       public static boolean controleDescription(Publication p) {
       String str= (p.getDescription()).toLowerCase();
        if(p.getDescription().length()==0) {
            return false;
           
        }
        char[] ch =str.toCharArray();
         for(int i =0; i<ch.length;i++) {
             char c =ch[i];
             if(!((c >='a' && c<='z') ||(String.valueOf(ch).equals(" ")))) {
                 return false;
             }
         }
       return true;

        
    }
    
}
