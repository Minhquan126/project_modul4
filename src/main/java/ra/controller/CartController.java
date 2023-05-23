package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.service.cart.CartServiceIMPL;

@Controller
@RequestMapping("/cartController")
public class CartController {
    CartServiceIMPL cartServiceIMPL = new CartServiceIMPL();
@GetMapping("delete")
    public String delete(@RequestParam("userId") int userId, Model model){
    cartServiceIMPL.deleteById(userId);
    return "home";
}
}
