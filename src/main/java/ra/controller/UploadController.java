package ra.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @GetMapping("/image")
    public String image(Model model){
        return "uploadFile";
    }
    @PostMapping("/k")
    public String upload(@RequestParam("image") MultipartFile image, Model model) throws IOException {
String uploadPath = "C:\\Users\\ADMIN\\Desktop\\modul4Project\\src\\main\\resources\\assets\\image";
String fileName = image.getOriginalFilename();
File file = new File(uploadPath);
if (!file.exists()){
    file.mkdirs();
}
        FileCopyUtils.copy(image.getBytes(),new File(uploadPath+File.separator + fileName));
        model.addAttribute("fileName",fileName);
        return "createProduct";
    }
}
