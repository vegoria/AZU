import xmlschema
from enum import Enum
from xml.etree import ElementTree

class TypOsoby(Enum):
    NAUCZYCIEL = 1
    UCZEN = 2

class Osoba():
    def __init__(self):
        self.typOsoby = None
        self.imie = None
        self.nazwisko = None
        self.rokUrodzenia = None

    def toDict(self):
        dict = {
            "typOsoby": self.typOsoby,
            "imie": self.imie,
            "nazwisko": self.nazwisko,
            "rokUrodzenia": self.rokUrodzenia
        }
        return dict

class Klasa():
    def __init__(self):
        self.numer = None
        self.litera = None
        self.wychowawca = None
        self.uczniowie = []

    def toDict(self):
        dict = {
            "klasa": {
                "numer": self.numer,
                "litera": self.litera,
                "wychowawca": self.wychowawca.toDict(),
                "uczniowie": [ucz.toDict()for ucz in self.uczniowie]}
        }
        return dict

    def makeXML(self):
        pass

def saveToXml(klasa):
    data = klasa.makeXML() #TODO zaimplementowac makeXML
    file = open('output.xml', 'w')
    file.write(data)
    file.close()

def saveFromXml():
    print("Podaj sciezke do pliku xml:")
    xmlPath = input()
    print("Podaj sciezke do schematu xsd")
    xsdPath = input()
    parsedXml = ElementTree.parse(xmlPath)
    mySchema = xmlschema.XMLSchema(xsdPath)
    root = parsedXml.getroot()
    # klasy = mySchema.elements['klasa'].decode(root[0]))
    #  #TODO finish parsing

klasa = None
option = 1
while option !='3':
    print('Co chcesz zrobic?')
    print('1. Stworzyc xml z klasy')
    print('2. Stworzyc obiekt z pliku xml')
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
