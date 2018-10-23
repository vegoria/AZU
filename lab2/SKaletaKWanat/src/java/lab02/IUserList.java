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
    public void addUser(User usr);
    public boolean removeUser(String login);
}
