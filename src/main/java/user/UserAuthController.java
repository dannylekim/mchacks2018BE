//package user;
package main.java.user;
import user.*;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserAuthController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        UserDAO dao = new UserDAO();
        try{
            if(dao.isValidPassword(username, password)) {
                return "Login success";
            }
        }
        catch(Exception ex) {
            return null;
        }
        return "Login fail";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String firstname,
                           @RequestParam String lastname, @RequestParam String email) {
        UserDAO dao = new UserDAO();
        try{
            boolean userExists = dao.usernameExists(username);
            boolean emailExists = dao.emailExists(email);

            if(!userExists && !emailExists){
                //do insert
                boolean insertUserSuccess = dao.insertNewUser(username, password, firstname, lastname, email);
                return "Registration success";
            }
        }
        catch (Exception ex){
            return null;
        }
        return "Registration fail";
    }
}