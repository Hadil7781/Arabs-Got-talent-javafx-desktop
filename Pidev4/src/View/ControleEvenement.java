/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entity.Evenement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Khawla
 */
public class ControleEvenement {
    /***************************************************************************************/
    public static boolean controleTitre(Evenement e) {
       String str= (e.getTitle()).toLowerCase();
        if(e.getTitle().equals(" ")) {
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
     public static boolean controleDescription(Evenement e) {
       String str= (e.getDescription()).toLowerCase();
        if(e.getDescription().length()==0) {
            return false;
           
        }
        
         
       return true;

        
    }
     /***************************************************************************************/
     public static boolean controleDate(Evenement e) throws ParseException {
        LocalDate ajourdhui = LocalDate.now();
         DateTimeFormatter outputDateFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
                  DateTimeFormatter outputDateFormat1 =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
                  LocalDate dateTime = LocalDate.parse(e.getDatedebut(), outputDateFormat);
                  LocalDate dateTime1 = LocalDate.parse(e.getDatefin(), outputDateFormat);
                  
                  System.out.println("date debut ="+dateTime);
                  System.out.println("date now ="+ajourdhui);

        if(dateTime.isAfter(dateTime1)) {
            return false;
        }
        
        if(ajourdhui.compareTo(dateTime)>0) {
            return false;
        }
   
        if(e.getDatedebut().equals(" ") &&e.getDatefin().equals(" ") ) {
            return false;
        }
      return true;
     
    }
     
    /***************************************************************************************/ 
     public static boolean controlePrix(Evenement e ) {
        char c ;
        String str= String.valueOf(e.getPrix());
        if(str.length()==0) {
            return false;
           
        }
        char[] ch =str.toCharArray();
         for(int i =0; i<ch.length;i++) {
             if(str.charAt(i)>='a' && str.charAt(i)<='z') {
                 return false;
             }
                     c=ch[i];
                     

             if(ch[i]=='-') {
                 return false;
             }
        
            String convertCharToString = Character.toString(c);
             int nb = Integer.parseInt(convertCharToString);
             if(!((nb >=0 && nb<=9))) {
                 return false;
             }
         }
       return true;

         
     }
     
     /***************************************************************************************/
     
   public static boolean  controleNbMin(Evenement e) {
        char c ;
        
         
        String str= String.valueOf(e.getNombreMinParticipants());
    
         if(str.length()==0) {
            return false;
           
        }
        char[] ch =str.toCharArray();
         for(int i =0; i<ch.length;i++) {
             if(str.charAt(i)>='a' && str.charAt(i)<='z') {
                 return false;
             }
                     c=ch[i];
                     

             if(ch[i]=='-') {
                 return false;
             }
        
            String convertCharToString = Character.toString(c);
             int nb = Integer.parseInt(convertCharToString);
             if(!((nb >=0 && nb<=9))) {
                 return false;
             }
         }
         
         
     
       return true;

       
   }
        
   public static boolean  controleNbMax(Evenement e) {
     
 char c ;
        String str= String.valueOf(e.getNombreMaxParticipants());
        if(str.length()==0) {
            return false;
           
        }
        char[] ch =str.toCharArray();
         for(int i =0; i<ch.length;i++) {
             if(str.charAt(i)>='a' && str.charAt(i)<='z') {
                 return false;
             }
                     c=ch[i];
                     

             if(ch[i]=='-') {
                 return false;
             }
        
            String convertCharToString = Character.toString(c);
             int nb = Integer.parseInt(convertCharToString);
             if(!((nb >=0 && nb<=9))) {
                 return false;
             }
         }
       return true;

         
       
   }
}

