package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.CartItem;
import ra.model.entity.Catalog;
import ra.model.entity.FakeProduct;
import ra.model.entity.Product;
import ra.model.service.cartItem.CartItemServiceIMPL;
import ra.model.service.catalog.CatalogServiceIMPL;
import ra.model.service.product.ProductServiceIMPL;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/productController")
public class ProductController {
    ProductServiceIMPL productServiceIMPL = new ProductServiceIMPL();
    CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
    CartItemServiceIMPL cartItemServiceIMPL = new CartItemServiceIMPL();
    @GetMapping("/getAll")
    public String getAll(Model model){
        List<Product> productList = productServiceIMPL.findAll();
        model.addAttribute("listProduct",productList);
        return "product/product";
    }
    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("product/createProduct");
        modelAndView.addObject("listCatalog",catalogServiceIMPL.findAll());
        return modelAndView;
    }
    @GetMapping("/delete")
    public String delete(@RequestParam int id){
        productServiceIMPL.deleteById(id);
        return "redirect:getAll";
    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("product/editProduct");
        modelAndView.addObject("editProduct",productServiceIMPL.findById(id));
        modelAndView.addObject("listCatalog",catalogServiceIMPL.findAll());
        return modelAndView;
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("newProduct") FakeProduct fakeProduct, @RequestParam("image") MultipartFile image, Model model) throws IOException {
        String uploadPath = "C:\\Users\\ADMIN\\Desktop\\modul4Project\\src\\main\\resources\\assets\\images";
        String fileName = image.getOriginalFilename();
        File file = new File(uploadPath);
        FileCopyUtils.copy(image.getBytes(),new File(uploadPath+File.separator + fileName));
        Product product = new Product(fakeProduct.getProductId(), fakeProduct.getProductName(),
        fakeProduct.getPrice(),fakeProduct.getQuantity(),fakeProduct.getDescription(),
                fileName,fakeProduct.getCatalogId(),fakeProduct.isStatus(), fakeProduct.getHeart());
        productServiceIMPL.save(product);
        return "redirect:getAll";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("updateProduct") FakeProduct fakeProduct,@RequestParam("image") MultipartFile image,Model model) throws IOException {
        String uploadPath = "C:\\Users\\ADMIN\\Desktop\\modul4Project\\src\\main\\resources\\assets\\images";
        String fileName = image.getOriginalFilename();
        File file = new File(uploadPath);
        FileCopyUtils.copy(image.getBytes(),new File(uploadPath+File.separator + fileName));
        Product product = new Product(fakeProduct.getProductId(), fakeProduct.getProductName(),
                fakeProduct.getPrice(),fakeProduct.getQuantity(),fakeProduct.getDescription(),
                fileName,fakeProduct.getCatalogId(),fakeProduct.isStatus(), fakeProduct.getHeart());
        productServiceIMPL.update(product);
        return "redirect:getAll";
    }
@RequestMapping("searchByCatalogId")
    public String searchByCatalogId(@RequestParam("catalogId") int catalogId,Model model){
     List<Product> list =  productServiceIMPL.searchByCatalogId(catalogId);
     model.addAttribute("listProduct",list);
     return "home";
}
@RequestMapping("showListProduct")
public String showListProduct(@RequestParam("userId") int userId,Model model){
    List<CartItem> cartItemList = cartItemServiceIMPL.showAll(userId);
    int count = 0;
    for (int i = 0; i < cartItemList.size(); i++) {
        count ++;
    }
    List<Catalog> catalogList = catalogServiceIMPL.findAll();
    List<Product> productList = productServiceIMPL.findAll();

    model.addAttribute("count",count);
    model.addAttribute("listCatalog",catalogList);
    model.addAttribute("listProduct",productList);
    return "home";
}
@RequestMapping("setHeart")
    public String setHeart(@RequestParam("userId") int userId,@RequestParam("productId") int productId,Model model){
        productServiceIMPL.setHeart(productId);
        return "redirect:/homeController/getAll?userId=" + userId;
}
@RequestMapping("showDetail")
    public String showDetail(@RequestParam("productId") int productId,@RequestParam("userId") int userId,Model model){
      Product product =  productServiceIMPL.findById(productId);
    Catalog catalog = catalogServiceIMPL.findById(product.getCatalogId());
    model.addAttribute("product",product);
    model.addAttribute("catalog",catalog);
    model.addAttribute("userId",userId);
    return "product/detail";
}
}
