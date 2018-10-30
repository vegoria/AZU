import xmlschema
import lxml
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

    def toXml(self):
        if self.typOsoby == TypOsoby.NAUCZYCIEL:
            typ = "NAUCZYCIEL"
        else:
            typ = "UCZEN"
        xml = "\n".join(["\t\t\t<typOsoby>"+typ+"</typOsoby>",
                         "\t\t\t<imie>" + self.imie + "</imie>",
                         "\t\t\t<nazwisko>" + self.nazwisko + "</nazwisko>",
                         "\t\t\t<rokUrodzenia>" + str(self.rokUrodzenia) + "</rokUrodzenia>"])
        return xml

class Klasa():
    def __init__(self):
        self.numer = None
        self.litera = None
        self.wychowawca = None
        self.uczniowie = []

    def toXml(self):
        xml = "\n".join(["<klasa>",
                           "\t<numer>"+str(self.numer)+"</numer>",
                           "\t<litera>" + str(self.litera) + "</litera>",
                           "\t<numer>" + str(self.numer) + "</numer>",
                           "\t<wychowawca>",
                           self.wychowawca.toXml(),
                           "\t</wychowawca>",
                           "\t<uczniowie>"])
        for ucz in self.uczniowie:
            xml = "\n".join([xml,
                             "\t\t<uczen>",
                             ucz.toXml(),
                             "\t\t</uczen>"])
        xml = "\n".join([xml, "\t</uczniowie>"])
        return xml

def saveToXml(klasa):
    data = klasa.toXml()
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
def findByName():
    print("Podaj sciezke do pliku xml:")
    xmlPath = input()
    print("Podaj imie ucznia:")
    imie = input()
    print("Podaj nazwisko ucznia:")
    nazwisko = input()
    path = '/klasa/uczniowie/uczen[imie="' + imie + '"][nazwisko="' + nazwisko + '"]'
    tree = lxml.etree.parse(xmlPath)
    students = tree.xpath(path)
    for e in students:
        print("Imie: " + e.find("imie").text)
        print("Nazwisko: " + e.find("nazwisko").text)
        print("Rok urodzenia: " + e.find("rokUrodzenia").text)
def findByBirthDate():
    print("Podaj sciezke do pliku xml:")
    xmlPath = input()
    print("Podaj date urodzenia ucznia:")
    date = input()
    date=str(date)
    tree = lxml.etree.parse(xmlPath)
    students = tree.xpath('/klasa/uczniowie/uczen[rokUrodzenia="%s"]'%date)
    for e in students:
        print("Imie: "+e.find("imie").text)
        print("Nazwisko: "+e.find("nazwisko").text)
        print("Rok urodzenia: "+e.find("rokUrodzenia").text)
def makeHtmlFile():
    print("Podaj sciezke do pliku xml:")
    xmlPath = input()
    print("Podaj sciezke do pliku xslt:")
    xsltPath = input()
    xml=lxml.etree.parse(xmlPath)
    xslt=lxml.etree.parse(xsltPath)
    transform = lxml.etree.XSLT(xslt)
    data=transform(xml)
    file=open('klasa.html','w')
    data=str(data)
    file.write(data)
    file.close()

def checkXMLFile():
    print("Podaj sciezke do pliku xml:")
    xmlPath = input()
    xml = ElementTree.parse(xmlPath)
    tree=xml.getroot()
    print("Klasa: "+tree[0].text+tree[1].text)
    print("WYCHOWAWCA: "+tree[2][1].text+" "+tree[2][2].text)
    print("UCZNIOWIE:")
    for student in tree.findall('uczniowie/uczen'):
        print(student.find('imie').text+" "+student.find('nazwisko').text)
    error=False
    if tree[2][0].text=='UCZEN':
        tree[2][0].text='NAUCZYCIEL'
        error=True
    for student in tree.findall('uczniowie/uczen'):
        if student.find('typOsoby').text=='NAUCZYCIEL':
            student.find('typOsoby').text='UCZEN'
            error=True
    if error==True:
        print('w pliku wystapily bledy')
        xml.write('klasanew.xml')

klasa = None
option = 1
while option !='3':
    print('Co chcesz zrobic?')
    print('1. Stworzyc xml z klasy')
    print('2. Stworzyc obiekt z pliku xml')
    print('3. Walidacja pliku XML')
    print('4. Wypisanie informacji i poprawa błednych typów')
    print('5. Wyszukiwanie po imieniu i nazwisku')
    print('6. Wyszukiwanie po dacie urodzenia')
    print('7. Utwórz plik HTML na podstawie pliku XSLT')
    print('8. Wyjscie')
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
        checkXMLFile()
    elif option == '5':
        findByName()
    elif option == '6':
        findByBirthDate()
    elif option == '7':
        makeHtmlFile()
    elif option == '8':
        break
