/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author vegor
 */
@Stateful
public class UserList implements IUserList {
    private List<User> userList;
    public UserList()
    {
        userList = new ArrayList<>();
        userList.add(new User("anna", "anna"));
        userList.add(new User("ola", "ola"));
        userList.add(new User("maciek", "maciek"));
        userList.add(new User("michal", "michal"));
    }
    
    public List<User> getUsers()
    {
        return userList;
    }
    
    public void addUser(User usr)
    {
        userList.add(usr);
    }
    
    public boolean removeUser(String login)
    {
         for(User usr: userList)
        {
            if(usr.getLogin() == null ? login == null : usr.getLogin().equals(login))
            {
                userList.remove(usr);
                return true;
            }
        }
         return false;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
