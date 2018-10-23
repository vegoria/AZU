/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;


@Stateless
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
    
    @Override
    public List<User> getUsers()
    {
        return userList;
    }
    
    @Override
    public boolean addUser(User user)
    {
        User usr = findUser(user.getLogin());
        
        if(usr == null)
        {
            userList.add(user);
            return true;
        }
        return false;
    }
    
    @Override
    public User findUser(String login)
    {
        for(User usr: userList)
        {
            if(usr.getLogin() == null ? login == null : usr.getLogin().equals(login))
            {
                return usr;
            }
        }
        return null;
    }
    
    @Override
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
}
