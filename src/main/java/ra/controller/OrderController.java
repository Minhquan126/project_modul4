package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.CartItem;
import ra.model.entity.Order;
import ra.model.entity.User;
import ra.model.service.cartItem.CartItemServiceIMPL;
import ra.model.service.order.OrderServiceIMPL;
import ra.model.service.user.UserServiceIMPL;

import javax.xml.soap.SAAJResult;
import java.util.List;

@Controller
@RequestMapping("/orderController")
public class OrderController {
    OrderServiceIMPL orderServiceIMPL = new OrderServiceIMPL();
    CartItemServiceIMPL cartItemServiceIMPL = new CartItemServiceIMPL();
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();

    @GetMapping("create")
    public String createOrder(@RequestParam("userId") int userId, @RequestParam("total") float total, Model model){
        User user = userServiceIMPL.findById(userId);
        List<CartItem> list = cartItemServiceIMPL.showAll(userId);
        model.addAttribute("listCartItem",list);
        model.addAttribute("user",user);
        model.addAttribute("total",total);
        model.addAttribute("userId",userId);
        return "order";
    }
}
