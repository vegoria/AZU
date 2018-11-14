/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vegor
 */
public class StudentClass {
    private Person tutor;
    private List<Person> students;
    private int number;
    private char letter;
    
    public StudentClass()
    {
        students = new ArrayList<Person>();
    }
    
    public StudentClass(int number, char letter)
    {
        students = new ArrayList<Person>();
        this.number = number;
        this.letter = letter;
    }
    
    public Person getTutor() {
        return tutor;
    }

    public void setTutor(Person tutor) {
        this.tutor = tutor;
        this.tutor.setType(Person.PersonType.NAUCZYCIEL);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
    
    public void addStudent(Person student)
    {
        students.add(student);
    }
    
    public List<Person> getStudents()
    {
        return students;
    }
    
}
