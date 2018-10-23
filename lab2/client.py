import requests

cookie = None
allCookies = None
url = "http://localhost:8080/my_app/"

while(True):
    print("Wybierz opcje:")
    print("1. loguj")
    print("2. pokaz aktywnych uzytkownikow")
    print("3. wyloguj")
    print("4. wyjdz")
    option = input()

    if(option==str(1)):
        login = input("podaj login: ")
        passwd = input("podaj haslo: ")
        reqTxt = "\n".join([login, passwd])
        req = requests.post("".join([url, "login"]), data=reqTxt)
        cookie = req.cookies['name']
        jar = req.cookies
    elif(option==str(2)):
        req = requests.get("".join([url, "login"]), cookies=allCookies)
        print(req.content)
    elif(option==str(3)):
        req = requests.get("".join([url, "logout"]), cookies=allCookies)
    elif(option==str(4)):
        break;
    else:
        print("Nie rozpoznano komendy")
