/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamil
 */
public class Lab4Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int choice=1;
        Scanner scanner = new Scanner(System. in);
        while (choice!=0)
        {
            System.out.println("Wybierz jedną z opcji:");
            System.out.println("1 - dodaj nową klasę");
            System.out.println("2 - zamień istniejącą klasę");
            System.out.println("3 - wypisz wszystkie klasy");
            System.out.println("4 - usuń klasę");
            System.out.println("0 - WYJŚCIE");
            choice=scanner.nextInt();
            switch(choice){
                case 1:
            {
                try {
                    System.out.println(newClass());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Lab4Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case 2: 
            {
                try {
                    System.out.println(switchClass());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Lab4Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case 3:
                    System.out.println(getAllClasses());
                    break;
                case 4:
                    System.out.println(removeCl());
                    break;
                default:
                    break;
            }
        }

    }

    private static String getAllClasses() {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.getAllClasses();
    }

    private static String removeClass(java.lang.String number, java.lang.String letter) {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.removeClass(number, letter);
    }

    private static String createNewClass(java.lang.String number, java.lang.String letter) {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.createNewClass(number, letter);
    }

    private static String addTutorToClass(java.lang.String number, java.lang.String letter, java.lang.String name, java.lang.String surname, java.lang.String year) {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.addTutorToClass(number, letter, name, surname, year);
    }
    
    private static String addStudentToClass(java.lang.String number, java.lang.String letter, java.lang.String name, java.lang.String surname, java.lang.String year) {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.addStudentToClass(number, letter, name, surname, year);
    }

    private static String replaceClass(java.lang.String number, java.lang.String letter) {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.replaceClass(number, letter);
    }
    

    private static String newClass() throws FileNotFoundException {
        
        Scanner scann = new Scanner(System. in);
        System.out.println("Podaj ścieżke do pliku z klasą:");
        String filename = scann.nextLine();
        Scanner readfile = new Scanner(new File(filename));
        String[] line;
        String[] classData;
        String singleLine;
        singleLine = readfile.nextLine();
        classData = singleLine.toLowerCase().trim().split("\\s+");
        createNewClass(classData[0],classData[1]);
        singleLine = readfile.nextLine();
        line = singleLine.toLowerCase().trim().split("\\s+");
        addTutorToClass(classData[0],classData[1],line[1],line[2],line[3]);
        while (readfile.hasNext()) {
            singleLine = readfile.nextLine();
            line = singleLine.toLowerCase().trim().split("\\s+");
            if(line.length==1)
                continue;
            addStudentToClass(classData[0],classData[1],line[0],line[1],line[2]);
    }
        return "Klasa dodana";
}

    private static String switchClass() throws FileNotFoundException {
        Scanner scann = new Scanner(System. in);
        System.out.println("Podaj ścieżke do pliku z klasą:");
        String filename = scann.nextLine();
        Scanner readfile = new Scanner(new File(filename));
        String[] line;
        String[] classData;
        String singleLine;
        singleLine = readfile.nextLine();
        classData = singleLine.toLowerCase().trim().split("\\s+");
        replaceClass(classData[0],classData[1]);
        singleLine = readfile.nextLine();
        line = singleLine.toLowerCase().trim().split("\\s+");
        addTutorToClass(classData[0],classData[1],line[1],line[2],line[3]);
        while (readfile.hasNext()) {
            singleLine = readfile.nextLine();
            line = singleLine.toLowerCase().trim().split("\\s+");
            if(line.length==1)
                continue;
            addStudentToClass(classData[0],classData[1],line[0],line[1],line[2]);
    }
        return "Klasa zamieniona";

    }

    private static String removeCl() {
        
        Scanner scann = new Scanner(System. in);
        System.out.println("Podaj numer klasy:");
        String number = scann.nextLine();
        System.out.println("Podaj litere klasy:");
        String letter = scann.nextLine();
        return removeClass(number,letter);
    }

}
