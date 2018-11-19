/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab4;

import java.util.List;
import javax.ejb.EJB;
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
     */
    @WebMethod(operationName = "getAllClasses")
    public String getAllClasses() {
        return classesContainer.toString();
    }
    
    @WebMethod(operationName = "getConcreteClass")
    public String getConcreteClass(@WebParam(name = "number") String number,
                                   @WebParam(name = "letter") String letter)
    {
        int classNumber = Integer.parseInt(number);
        StudentClass studentClass = classesContainer.findClass(classNumber, letter);
        return studentClass.toString();
    }
    
    @WebMethod(operationName = "createNewClass")
    public void createNewClass(@WebParam(name = "number") String number,
                                 @WebParam(name = "letter") String letter)
    {
        StudentClass studentClass = new StudentClass(Integer.parseInt(number), letter);
        classesContainer.addClass(studentClass);
    }
    
    @WebMethod(operationName = "addTutorToClass")
    public void addTutorToClass(@WebParam(name = "number") String number,
                                @WebParam(name = "letter") String letter,
                                @WebParam(name = "name") String name,
                                @WebParam(name = "surname") String surname,
                                @WebParam(name = "year") String year)
    {
        StudentClass stClass = classesContainer.findClass(Integer.parseInt(number), letter);
        Person tutor = new Person(name, surname, Integer.parseInt(year), Person.PersonType.NAUCZYCIEL);
        stClass.setTutor(tutor);
    }
    
    @WebMethod(operationName = "addStudentToClass")
    public void addStudentToClass(@WebParam(name = "number") String number,
                                @WebParam(name = "letter") String letter,
                                @WebParam(name = "name") String name,
                                @WebParam(name = "surname") String surname,
                                @WebParam(name = "year") String year)
    {
        StudentClass stClass = classesContainer.findClass(Integer.parseInt(number), letter);
        Person student = new Person(name, surname, Integer.parseInt(year), Person.PersonType.UCZEN);
        stClass.setTutor(student);
    }
    
    @WebMethod(operationName = "removeClass")
    public void removeClass(@WebParam(name = "number") String number,
                            @WebParam(name = "letter") String letter)
    {
        classesContainer.removeClass(Integer.parseInt(number), letter);
    }
    
    @WebMethod(operationName = "replaceClass")
    public void replaceClass(@WebParam(name = "number") String number,
                                 @WebParam(name = "letter") String letter)
    {
        classesContainer.removeClass(Integer.parseInt(number), letter);
        StudentClass studentClass = new StudentClass(Integer.parseInt(number), letter);
        classesContainer.addClass(studentClass);
    }
    
}
