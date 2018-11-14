/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab4;
import java.util.List;
import javax.ejb.Remote;
/**
 *
 * @author vegor
 */
@Remote
public interface IClassesContainer
{
    public List<StudentClass> getClasses();
    public StudentClass findClass(int number, char letter);
    public boolean removeClass(int number, char letter);
    public void addClass(StudentClass studentClass);
    public boolean replaceClass(StudentClass newClass);
}
