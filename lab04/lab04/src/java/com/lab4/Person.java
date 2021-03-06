/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab4;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vegor
 */
@XmlRootElement(name = "osoba")

public class Person {
    public enum PersonType
    {
        NAUCZYCIEL,
        UCZEN
    }
    private String name;
    private String surname;
    private int birthYear;
    private PersonType type;

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }
    
    public Person()
    {
    }
    
    public Person(String name, String surname, int year)
    {
        this.name = name;
        this.surname = surname;
        this.birthYear = year;
    }
    
    public Person(String name, String surname, int year, PersonType type)
    {
        this.name = name;
        this.surname = surname;
        this.birthYear = year;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    
    public void setAsTutor()
    {
        this.type=PersonType.NAUCZYCIEL;
    }
    
    public void setAsStudent()
    {
        this.type=PersonType.UCZEN;
    }
    
    @Override
    public String toString()
    {
       StringBuilder sB = new StringBuilder();
       sB.append(name);
       sB.append(" ");
       sB.append(surname);
       sB.append(" rok urodzenia: ");
       sB.append(birthYear);
       return sB.toString();
    }
    
}
