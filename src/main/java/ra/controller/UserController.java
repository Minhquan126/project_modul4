package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Cart;
import ra.model.entity.CartItem;
import ra.model.entity.User;
import ra.model.service.cart.CartServiceIMPL;
import ra.model.service.cartItem.CartItemServiceIMPL;
import ra.model.service.user.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/userController")
public class UserController {
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
    CartServiceIMPL cartServiceIMPL = new CartServiceIMPL();
    CartItemServiceIMPL cartItemServiceIMPL = new CartItemServiceIMPL();
    @GetMapping("getAll")
    public String getAll(Model model){
        List<User> list = userServiceIMPL.findAll();
        model.addAttribute("listUser",list);
        return "user/user";
    }
    @GetMapping("delete")
    public String delete(@RequestParam("id") int id,Model model){
        userServiceIMPL.deleteById(id);
        return "redirect:getAll";
    }
    @GetMapping("registerPage")
    public String registerPage(Model model){
        return "user/register";
    }
    @GetMapping("loginPage")
    public String loginPage(Model model){
        return "user/login";
    }
    @PostMapping("register")
    public String register(@RequestParam("userName") String userName,@RequestParam("email") String email,@RequestParam("password") String password,Model model){
        if (userName.trim().equals("")){
            model.addAttribute("userName","userName is required");
            return "user/register";
        } else if (email.trim().equals("")) {
            model.addAttribute("email","email is required!");
            return "user/register";
        } else if (password.trim().equals("")) {
            model.addAttribute("password","password is required");
            return "user/register";
        } else if (userServiceIMPL.checkExistName(userName)) {
            model.addAttribute("userName","userName is existed!");
            return "user/register";
        } else if (userServiceIMPL.checkExistEmail(email)) {
            model.addAttribute("email","email is existed");
            return "user/register";
        } else {
            userServiceIMPL.save(new User(userName,email,password));
            List<User> userList = userServiceIMPL.findAll();
            for (User user:userList) {
                if (user.getUserName()==userName){
                    cartServiceIMPL.create(user.getUserId());
                }
            }
            return "user/login";
        }
    }
    @PostMapping("login")
    public String login(@RequestParam String userName, @RequestParam String password, Model model, HttpServletRequest request){

        if (userName.trim().equals("")||password.trim().equals("")){
            model.addAttribute("login","userName,password is required");
            return "user/login";
        }
        User userLogin = userServiceIMPL.login(new User(userName,password));
        if (userLogin==null) {
            model.addAttribute("login","Account does not exist!");
            return "user/login";
        } else {
            request.getSession().setAttribute("userLogin",userLogin);
            if (userLogin.isRole()){
                return "redirect:/adminController/getAll";
            } else {
                model.addAttribute("userId",userLogin.getUserId());
                return "redirect:/homeController/getAll";
            }
        }
    }
    @PostMapping("update")
    public String update(@ModelAttribute("userLogin") User user,@RequestParam("userId") int userId,@RequestParam("total") float total,Model model){
   userServiceIMPL.update(user);
List<CartItem> list = cartItemServiceIMPL.showAll(user.getUserId());
model.addAttribute("listCartItem",list);
model.addAttribute("total",total);
model.addAttribute("user",user);
model.addAttribute("userId",userId);
return "order";
    }
    @GetMapping("blockUser")
    public String blockUser(@RequestParam("id") int id){
        userServiceIMPL.blockUser(id);
        return "redirect:getAll";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("userLogin");
        return "redirect:/homeController/goHome";
    }
}
