/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.SinglyLinkedList;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Profesor Lic. Gilberth Chaves Avila
 */
public class Utility {
    
    private static SinglyLinkedList singlyList = new SinglyLinkedList();
    private static DoublyLinkedList doublyList = new DoublyLinkedList();
    private static CircularLinkedList circularList = new CircularLinkedList();
    private static CircularDoublyLinkedList circularDoublyList = new CircularDoublyLinkedList();

    public static SinglyLinkedList getSinglyList() {
        return singlyList;
    }

    public static void setSinglyList(SinglyLinkedList list) {
        if (Utility.singlyList.isEmpty()) {       
            Utility.singlyList = list;
        }else{
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.singlyList.contains(list.getNode(i).data)) {
                        Utility.singlyList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static DoublyLinkedList getDoublyList() {
        return doublyList;
    }

    public static void setDoublyList(DoublyLinkedList list) {
        if (Utility.doublyList.isEmpty()) {       
            Utility.doublyList = list;
        }else{
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.doublyList.contains(list.getNode(i).data)) {
                        Utility.doublyList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static CircularLinkedList getCircularList() {
        return circularList;
    }

    public static void setCircularList(CircularLinkedList list) {
        if (Utility.circularList.isEmpty()) {       
            Utility.circularList = list;
        }else{
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.circularList.contains(list.getNode(i).data)) {
                        Utility.circularList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static CircularDoublyLinkedList getCircularDoublyList() {
        return circularDoublyList;
    }

    public static void setCircularDoublyList(CircularDoublyLinkedList list) {
        if (Utility.circularDoublyList.isEmpty()) {       
            Utility.circularDoublyList = list;
        }else{
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.circularDoublyList.contains(list.getNode(i).data)) {
                        Utility.circularDoublyList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
    public static int random(){
        return 1+(int) Math.floor(Math.random()*99); 
    }
    
    public static int random(int bound){
        //return 1+random.nextInt(bound);
        return 1+(int) Math.floor(Math.random()*bound); 
    }
    
    public static int random(int boundLow, int boundMax){
        //return 1+random.nextInt(bound);
        return (int) ((Math.random() * (boundMax+1 - boundLow)) + boundLow); 
    }
    
    public static String format(double value){
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }
    
    public static String $format(double value){
        return new DecimalFormat("$###,###,###,###.##")
                .format(value);
    }
     public static String perFormat(double value){
         //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }
    //Puede funcionar para estudiante y curso
    public static boolean equals(Object a, Object b) {
        
        switch(instanceOf(a,b)){//Devuelve String
            case "integer":
                Integer x = (Integer)a; Integer y = (Integer)b;
                return x.equals(y);
            case "string":
                String s1 = (String)a; String s2 = (String)b;
               // return s1.compareTo(s2)==0; Op1
                return s1.equalsIgnoreCase(s2); //Op2
            case "career":
                Career c1 = (Career)a;
                Career c2 = (Career)b;
                return c1.equals(c2);
        }
        return false;//En cualquier otro caso retorne false;
    }

    private static String instanceOf(Object a, Object b) {
        if(a instanceof Integer && b instanceof Integer){return "integer";}
        if(a instanceof String && b instanceof String){return "string";}
        if(a instanceof Career && b instanceof Career){return "career";}
//        if(a instanceof JobPosition && b instanceof JobPosition){return "JobPosition";}//Comparo 2 empleados
        return "unknown";
    }

    public static boolean lessT(Object a, Object b) {
        switch(instanceOf(a,b)){//Devuelve String
            case "integer":
                Integer x = (Integer)a; Integer y = (Integer)b;
                return x<y;
            case "string":
                String s1 = (String)a; String s2 = (String)b;
                return s1.compareTo(s2)<0;//Primero mayor que segundo
               
        }
        return false;//En cualquier otro caso retorne false;
    }
    
    
    public static boolean greaterT(Object a, Object b) {
        switch(instanceOf(a,b)){//Devuelve String
            case "integer":
                Integer x = (Integer)a; Integer y = (Integer)b;
                return x>y;
            case "string":
                String s1 = (String)a; String s2 = (String)b;
                return s1.compareTo(s2)>0;//Primero mayor que segundo
        }
        return false;//En cualquier otro caso retorne false;
    }

    public static String dateFormat(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}

