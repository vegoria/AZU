/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab4;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author vegor
 */

@WebService(serviceName = "Lab4WebService")
public class Lab4WebService {
    @EJB
    private IClassesContainer classesContainer;
    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "getAllClasses")
    public String getAllClasses() {
        return classesContainer.printAllClasses();
    }
    
    @WebMethod(operationName = "getConcreteClass")
    public String getConcreteClass(@WebParam(name = "number") String number,
                                   @WebParam(name = "letter") String letter)
    {
        
        int classNumber = Integer.parseInt(number);
        
        StudentClass studentClass = classesContainer.findClass(classNumber, letter); 
        if (studentClass==null)
            return "Nie ma takiej klasy";
        return studentClass.toString();
    }
    
    @WebMethod(operationName = "createNewClass")
    public String createNewClass(@WebParam(name = "number") String number,
                                 @WebParam(name = "letter") String letter)
    {
        
        StudentClass studentClass = new StudentClass(Integer.parseInt(number), letter);
        classesContainer.addClass(studentClass);
        return studentClass.getLetter();
    }
    
    @WebMethod(operationName = "addTutorToClass")
    public String addTutorToClass(@WebParam(name = "number") String number,
                                @WebParam(name = "letter") String letter,
                                @WebParam(name = "name") String name,
                                @WebParam(name = "surname") String surname,
                                @WebParam(name = "year") String year)
    {
        Person tutor = new Person(name, surname, Integer.parseInt(year), Person.PersonType.NAUCZYCIEL);
        classesContainer.addTutorToClass(Integer.parseInt(number), letter, tutor);
        return "Dodano nauczyciela";
    }
    
    @WebMethod(operationName = "addStudentToClass")
    public String addStudentToClass(@WebParam(name = "number") String number,
                                @WebParam(name = "letter") String letter,
                                @WebParam(name = "name") String name,
                                @WebParam(name = "surname") String surname,
                                @WebParam(name = "year") String year)
    {
        
        Person student = new Person(name, surname, Integer.parseInt(year), Person.PersonType.UCZEN);
        classesContainer.addStudentToClass(Integer.parseInt(number), letter, student);
        return "Dodano ucznia";
    }
    
    @WebMethod(operationName = "removeClass")
    public String removeClass(@WebParam(name = "number") String number,
                            @WebParam(name = "letter") String letter)
    {
        classesContainer.removeClass(Integer.parseInt(number), letter);
        return "Klasa usunięta";
    }
    
    @WebMethod(operationName = "replaceClass")
    public String replaceClass(@WebParam(name = "number") String number,
                                 @WebParam(name = "letter") String letter)
    {
        classesContainer.removeClass(Integer.parseInt(number), letter);
        StudentClass studentClass = new StudentClass(Integer.parseInt(number), letter);
        classesContainer.addClass(studentClass);
        return "Klasa zamieniona";
    }
    
}
