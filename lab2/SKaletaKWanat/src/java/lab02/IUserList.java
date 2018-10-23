/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface IUserList {
    public List<User> getUsers();
    public boolean addUser(User usr);
    public boolean removeUser(String login);
    public User findUser(String login);
    public void setSession(String login, String session);
}
