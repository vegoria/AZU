/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab04client;


import com.lab4.Person;
import com.lab4.StudentClass;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamil
 */
public class Lab04Client {

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
                    System.out.println(CreateOrReplaceClass(1));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Lab04Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case 2: 
            {
                try {
                    System.out.println(CreateOrReplaceClass(2));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Lab04Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case 3:
                    printAllClasses();
                    break;
                case 4:
                    System.out.println(removeCl());
                    break;
                default:
                    break;
            }
        }
    }
    
    
    private static String CreateOrReplaceClass(int choice) throws FileNotFoundException {
        StudentClass stClass ;
        Person tutor;
        Person student;
        List<Person> studList = new ArrayList<>();
        Scanner scann = new Scanner(System. in);
        System.out.println("Podaj ścieżke do pliku z klasą:");
        String filename = scann.nextLine();
        Scanner readfile = new Scanner(new File(filename));
        String[] line;
        String[] classData;
        String singleLine;
        singleLine = readfile.nextLine();
        classData = singleLine.toLowerCase().trim().split("\\s+");
        
        singleLine = readfile.nextLine();
        line = singleLine.toLowerCase().trim().split("\\s+");
        //tutor=new Person(line[1],line[2],Integer.parseInt(line[3]));
        tutor = new Person();
        tutor.setName(line[1]);
        tutor.setSurname(line[2]);
        tutor.setBirthYear(Integer.parseInt(line[3]));
        tutor.setAsTutor();
        while (readfile.hasNext()) {
            singleLine = readfile.nextLine();
            line = singleLine.toLowerCase().trim().split("\\s+");
            if(line.length==1)
                continue;
            //student=new Person(line[0],line[1],Integer.parseInt(line[2]));
            student=new Person();
            student.setName(line[0]);
            student.setSurname(line[1]);
            student.setBirthYear(Integer.parseInt(line[2]));
            student.setAsStudent();
            studList.add(student);
    }
        //stClass = new StudentClass(classData[0],classData[1],tutor,studList);
        stClass=new StudentClass();
        stClass.setNumber(Integer.parseInt(classData[0]));
        stClass.setLetter(classData[1]);
        stClass.setTutor(tutor);
        stClass.setStudents(studList);
        if(choice == 1)
            createNewClass(stClass);
        else
            replaceClass(stClass);
        return "Klasa dodana";
}

    private static String removeCl() {
        
        Scanner scann = new Scanner(System. in);
        System.out.println("Podaj numer klasy:");
        String number = scann.nextLine();
        System.out.println("Podaj litere klasy:");
        String letter = scann.nextLine();
        return removeClass(number,letter);
    }

    private static String createNewClass(com.lab4.StudentClass sClass) {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.createNewClass(sClass);
    }
    
    
    private static void printAllClasses() {
    List<String> classes = getAllClasses();
    for (String cl : classes){
        System.out.println(cl);
    }
    }

    private static java.util.List<java.lang.String> getAllClasses() {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.getAllClasses();
    }

    private static StudentClass getConcreteClass(java.lang.String number, java.lang.String letter) {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.getConcreteClass(number, letter);
    }

    private static String removeClass(java.lang.String number, java.lang.String letter) {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.removeClass(number, letter);
    }

    private static String replaceClass(com.lab4.StudentClass sClass) {
        com.lab4.Lab4WebService_Service service = new com.lab4.Lab4WebService_Service();
        com.lab4.Lab4WebService port = service.getLab4WebServicePort();
        return port.replaceClass(sClass);
    }

    
}
