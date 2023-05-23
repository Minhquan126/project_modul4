package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.CartItem;
import ra.model.entity.OrderDetail;
import ra.model.entity.Product;
import ra.model.service.cartItem.CartItemServiceIMPL;
import ra.model.service.order.OrderServiceIMPL;
import ra.model.service.orderDetail.OrderDetailServiceIMPL;
import ra.model.service.product.ProductServiceIMPL;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cartItemController")
public class CartItemController {
    CartItemServiceIMPL cartItemServiceIMPL = new CartItemServiceIMPL();
    ProductServiceIMPL productServiceIMPL = new ProductServiceIMPL();
    OrderDetailServiceIMPL orderDetailServiceIMPL = new OrderDetailServiceIMPL();
    OrderServiceIMPL orderServiceIMPL = new OrderServiceIMPL();

    @GetMapping("getAll")
    public String getAll(@RequestParam("userId") int userId,@RequestParam("message") String message, Model model) {
        List<CartItem> list = cartItemServiceIMPL.showAll(userId);
        float sum = cartItemServiceIMPL.total(userId);
        model.addAttribute("total",sum);
        model.addAttribute("listCartItem", list);
        model.addAttribute("userId",userId);
        model.addAttribute("message",message);
        return "shoppingCart";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("userId") int userId, @RequestParam("productId") int productId, Model model) {
        cartItemServiceIMPL.delete(userId, productId);
        String message = "";
        return "redirect:getAll?userId=" + userId + "&message=" + message;
    }

    @GetMapping("create")
    public String createCartItem(@RequestParam("userId") int userId, @RequestParam("productId") int productId, Model model) {
        List<CartItem> itemList = cartItemServiceIMPL.showAll(userId);
        Product product = productServiceIMPL.findById(productId);
        CartItem cartItem = new CartItem(userId, productId, product.getImage(), product.getProductName(), product.getPrice(), 1, 1);
        boolean check = true;
String message = "";
        for (CartItem cart : itemList) {
            if (cart.getProductId() == cartItem.getProductId() && cart.getUserId() == cartItem.getUserId()) {
                check = false;
        }
            }
            if (check) {
                cartItemServiceIMPL.save(cartItem);
            } else {
                cartItemServiceIMPL.productExist(userId, productId);
            }
            return "redirect:getAll?userId=" + userId + "&message=" + message;
        }
        @GetMapping("/checkout")
            public String checkout ( @RequestParam("userId") int userId,@RequestParam("total") float total){
            int orderId = orderServiceIMPL.create(userId);
            System.out.println("orderId=============>" + orderId);
boolean check = false;
            List<CartItem> cartItemList = cartItemServiceIMPL.showAll(userId);
            for (CartItem cartItem: cartItemList) {
              check =  orderDetailServiceIMPL.create(orderId,cartItem);
            }
            if (check){
                orderServiceIMPL.setTotal(orderId,total);
            }
            cartItemServiceIMPL.checkout(userId);
            return "redirect:/homeController/getAll?userId=" + userId;
        }
        @GetMapping("setQuantity")
    public String setQuantity(@RequestParam("userId") int userId,@RequestParam("productId") int productId,@RequestParam("quantity") int quantity,Model model){
        Product product = productServiceIMPL.findById(productId);
String message = "Product quantity is " + product.getQuantity();
        if (quantity <= product.getQuantity()){
            message = "";
            cartItemServiceIMPL.setQuantity(userId,productId,quantity);
            return "redirect:getAll?userId=" + userId + "&message=" + message;
        }
            return "redirect:getAll?userId=" + userId + "&message=" + message;
        }
    }
