import xmlschema
import generateDS
from enum import Enum

class TypOsoby(Enum):
    NAUCZYCIEL = 1
    UCZEN = 2

class Osoba():
    def __init__(self):
        self.typOsoby = None
        self.imie = None
        self.nazwisko = None
        self.rokUrodzenia = None

class Klasa():
    def __init__(self):
        self.numer = None
        self.litera = None
        self.wychowawca = None
        self.uczniowie = {}

def saveToXml(klasa):
    pass

def saveFromXml():
    pass


klasa = None
option = 1
while option !='3':
    print('Co chcesz zrobic?')
    print('1. Stworzyc xml z klasy')
    print('1. Stworzyc obiekt z pliku xml')
    print('3. Walidacja pliku XML')
    print('4. Wyjscie')
    option = input()

    if option == '1':
        klasa = Klasa()
        print("Podaj numer klasy")
        klasa.numer = input()
        print("Podaj litere klasy")
        klasa.litera = input()

        print("Wprowadzanie wychowawcy")
        wychowawca = Osoba()
        wychowawca.typOsoby = TypOsoby.NAUCZYCIEL
        print("Podaj imie")
        wychowawca.imie = input()
        print("Podaj nazwisko")
        wychowawca.nazwisko = input()
        print("Podaj rok urodzenia")
        wychowawca.rok = input()
        klasa.wychowawca = wychowawca

        print("Teraz uczniowie")
        innerOption = 'y'

        while innerOption == 'y' or innerOption == 'Y':
            uczen = Osoba()
            uczen.typOsoby = TypOsoby.UCZEN
            print("Podaj imie")
            uczen.imie = input()
            print("Podaj nazwisko")
            uczen.nazwisko = input()
            print("Podaj rok urodzenia")
            uczen.rok = input()
            klasa.uczniowie.append(uczen)
            print("Czy chcesz dodac kolejnego ucznia? [y/n]")
            innerOption=input()
        saveToXml(klasa)
    elif option == '2':
        saveFromXml()
    elif option == '3':
        print("Podaj sciezke xml:")
        xmlFilePath = input()
        print("Podaj sciezke xsd:")
        xsdFilePath = input()
        mySchema = xmlschema.XMLSchema(xsdFilePath)
        print("sprawdzanie poprawnosci:")
        print(mySchema.is_valid(xmlFilePath))
    elif option == '4':
        break
