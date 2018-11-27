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
    public StudentClass findClass(int number, String letter);
    public boolean removeClass(int number, String letter);
    public void addClass(StudentClass studentClass);
    public void addTutorToClass(int number, String letter, Person tutor);
    public void addStudentToClass(int number, String letter,Person student);
    public boolean replaceClass(StudentClass newClass);
    public String printAllClasses();
    public List<String> getAllClasses();
}
