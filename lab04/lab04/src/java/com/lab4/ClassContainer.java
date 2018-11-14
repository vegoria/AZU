/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab4;

import java.util.ArrayList;
import java.util.List;
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
        classesList = new ArrayList<StudentClass>();
    }
    @Override
    public List<StudentClass> getClasses()
    {
        return classesList;
    }
    @Override
    public StudentClass findClass(int number, char letter)
    {
        for(StudentClass currClass: classesList)
        {
            if(currClass.getNumber() == number && currClass.getLetter()==letter)
            {
                return currClass;
            }
        }
        return null;
    }
    @Override
    public boolean removeClass(int number, char letter)
    {
        for(StudentClass currClass: classesList)
        {
            if(currClass.getNumber() == number && currClass.getLetter()==letter)
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
            if(currClass.getNumber() == newClass.getNumber() && currClass.getLetter()==newClass.getLetter())
            {
                currClass = newClass;
                return true;
            }
        }
        return false;
    }
}
