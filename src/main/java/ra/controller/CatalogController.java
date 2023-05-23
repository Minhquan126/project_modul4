package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.Catalog;
import ra.model.entity.CatalogFake;
import ra.model.entity.FakeProduct;
import ra.model.entity.Product;
import ra.model.service.catalog.CatalogServiceIMPL;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/catalogController")
public class CatalogController {
    CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
    @GetMapping("getAll")
    public String getAll(Model model){
        List<Catalog> catalogList = catalogServiceIMPL.findAll();
        model.addAttribute("listCatalog",catalogList);
        return "catalog/catalog";
    }
    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("catalog/createCatalog");
        modelAndView.addObject("newCatalog",new Catalog());
        return modelAndView;
    }
    @GetMapping("/delete")
    public String delete(@RequestParam int id){
        catalogServiceIMPL.deleteById(id);
        return "redirect:getAll";
    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("catalog/editCatalog");
        modelAndView.addObject("editCatalog",catalogServiceIMPL.findById(id));
        return modelAndView;
    }
    @PostMapping("/add")
            public String add(@ModelAttribute("newCatalog") CatalogFake catalogFake, @RequestParam("image") MultipartFile image,Model model) throws IOException {
    String uploadPath = "C:\\Users\\ADMIN\\Desktop\\modul4Project\\src\\main\\resources\\assets\\images";

    String fileName = image.getOriginalFilename();
    File file = new File(uploadPath);
        FileCopyUtils.copy(image.getBytes(),new File(uploadPath+File.separator + fileName));
    Catalog catalog = new Catalog(catalogFake.getCatalogId(), catalogFake.getCatalogName(),
            catalogFake.getDescription(),catalogFake.getCountry(),fileName);
        catalogServiceIMPL.save(catalog);
        return "redirect:getAll";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("updateCatalog") CatalogFake catalogFake, @RequestParam("image") MultipartFile image,Model model) throws IOException {
        String uploadPath = "C:\\Users\\ADMIN\\Desktop\\modul4Project\\src\\main\\resources\\assets\\images";

        String fileName = image.getOriginalFilename();
        File file = new File(uploadPath);
        FileCopyUtils.copy(image.getBytes(),new File(uploadPath+File.separator + fileName));
        Catalog catalog = new Catalog(catalogFake.getCatalogId(), catalogFake.getCatalogName(),
                catalogFake.getDescription(),catalogFake.getCountry(),fileName);
        catalogServiceIMPL.update(catalog);
        return "redirect:getAll";
    }
}
