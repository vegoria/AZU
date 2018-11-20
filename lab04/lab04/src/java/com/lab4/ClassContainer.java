/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab4;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author vegor
 */
@Stateless
public class ClassContainer implements IClassesContainer
{
    List<StudentClass> classesList;
    public ClassContainer()
    {
        classesList = new ArrayList<>();
        
    }
    @Override
    public List<StudentClass> getClasses()
    {
        return classesList;
    }
    
    @Override
    public StudentClass findClass(int number, String letter)
    {
        for(StudentClass currClass: classesList)
        {
            if(currClass.getNumber() == number && (currClass.getLetter() == null ? letter == null : currClass.getLetter().equals(letter)))
            {
                return currClass;
            }
        }
        return null;
    }
    @Override
    public boolean removeClass(int number, String letter)
    {
        for(StudentClass currClass: classesList)
        {
            if(currClass.getNumber() == number && (currClass.getLetter() == null ? letter == null : currClass.getLetter().equals(letter)))
            {
                classesList.remove(currClass);
                return true;
            }
        }
        return false;
    }
    @Override
    public void addClass(StudentClass studentClass)
    {
        classesList.add(studentClass);
    }
 
    @Override
    public boolean replaceClass(StudentClass newClass)
    {
        for(StudentClass currClass: classesList)
        {
            if(currClass.getNumber() == newClass.getNumber() && (currClass.getLetter() == null ? newClass.getLetter() == null : currClass.getLetter().equals(newClass.getLetter())))
            {
                currClass = newClass;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String printAllClasses()
    {
        StringBuilder sB = new StringBuilder();
        for(StudentClass stClass: classesList)
        {
            sB.append(stClass.toString());
            sB.append("\n\n");
        }
        return sB.toString();
    }

  
    @Override
    public void addTutorToClass(int number, String letter, Person tutor)
    {
        for(StudentClass currClass: classesList)
        {
            if(currClass.getNumber() == number && (currClass.getLetter() == null ? letter == null : currClass.getLetter().equals(letter)))
            {
                currClass.setTutor(tutor);
            }
        }
    }

    @Override
    public void addStudentToClass(int number, String letter, Person student) {
        for(StudentClass currClass: classesList)
        {
            if(currClass.getNumber() == number && (currClass.getLetter() == null ? letter == null : currClass.getLetter().equals(letter)))
            {
                currClass.addStudent(student);
            }
        }
    }
}
