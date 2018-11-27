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
    public List<String> getAllClasses() {
        return classesContainer.getAllClasses();
    }
    
    @WebMethod(operationName = "getConcreteClass")
    public StudentClass getConcreteClass(@WebParam(name = "number") String number,
                                   @WebParam(name = "letter") String letter)
    {
        
        int classNumber = Integer.parseInt(number);
        
        StudentClass studentClass = classesContainer.findClass(classNumber, letter); 
        return studentClass;
    }
    
    @WebMethod(operationName = "createNewClass")
    public String createNewClass(@WebParam(name = "sClass") StudentClass sClass)
    {
        
        classesContainer.addClass(sClass);
        return sClass.toString();
    }
     
    @WebMethod(operationName = "removeClass")
    public String removeClass(@WebParam(name = "number") String number,
                            @WebParam(name = "letter") String letter)
    {
        classesContainer.removeClass(Integer.parseInt(number), letter);
        return "Klasa usunięta";
    }
    
    @WebMethod(operationName = "replaceClass")
    public String replaceClass(@WebParam(name = "sClass") StudentClass sClass)
    {
        classesContainer.removeClass(sClass.getNumber(), sClass.getLetter());
        classesContainer.addClass(sClass);
        return "Klasa zamieniona";
    }
    
}
