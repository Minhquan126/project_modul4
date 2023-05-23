package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.CartItem;
import ra.model.entity.Catalog;
import ra.model.entity.Product;
import ra.model.service.cartItem.CartItemServiceIMPL;
import ra.model.service.catalog.CatalogServiceIMPL;
import ra.model.service.product.ProductServiceIMPL;

import java.util.List;

@Controller
@RequestMapping("/homeController")
public class HomeController {
    CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
    ProductServiceIMPL productServiceIMPL = new ProductServiceIMPL();
    CartItemServiceIMPL cartItemServiceIMPL = new CartItemServiceIMPL();
    @GetMapping("getAll")
    public String getAll(@RequestParam("userId") int userId, Model model){
        List<CartItem> cartItemList = cartItemServiceIMPL.showAll(userId);
        int count = 0;
        for (int i = 0; i < cartItemList.size(); i++) {
            count ++;
        }
        List<Catalog> catalogList = catalogServiceIMPL.findAll();
        List<Product> productList = productServiceIMPL.showList();

                model.addAttribute("count",count);
        model.addAttribute("listCatalog",catalogList);
        model.addAttribute("listProduct",productList);
        return "home";
    }
    @GetMapping("goHome")
    public String goHome(Model model){
        List<Catalog> catalogList = catalogServiceIMPL.findAll();
        List<Product> productList = productServiceIMPL.showList();
        model.addAttribute("listCatalog",catalogList);
        model.addAttribute("listProduct",productList);
        return "home";
    }
}
