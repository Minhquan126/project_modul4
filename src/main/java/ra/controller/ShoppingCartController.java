package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.ShoppingCart;
import ra.model.service.shoppingCart.ShoppingCartServiceIMPL;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shoppingCartController")
public class ShoppingCartController {
    ShoppingCartServiceIMPL shoppingCartServiceIMPL = new ShoppingCartServiceIMPL();
    List<ShoppingCart> list = new ArrayList<>();
    @GetMapping("getAll")
    //public String getAll(@RequestParam("userId") int userId , Model model)
    public String getAll(Model model){
//       list = shoppingCartServiceIMPL.showAll(userId);
//       model.addAttribute("shoppingCart",list);
       return "shoppingCart";
    }
}
