package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.OrderShow;
import ra.model.service.ShowOrderService;
import ra.model.service.order.OrderServiceIMPL;

import java.util.List;

@Controller
@RequestMapping("/showOrderController")
public class showOrderController {
   ShowOrderService showOrderService = new ShowOrderService();
  @GetMapping("showAll")
  public String showAll(Model model){
     List<OrderShow> list = showOrderService.findAll();
     model.addAttribute("list",list);
     return "showOrder";
  }
  @GetMapping("orderUser")
    public String orderUser(@RequestParam("userId") int userId,Model model){
      List<OrderShow> list = showOrderService.findByUserId(userId);
      model.addAttribute("orderUser",list);
      return "orderUser";
  }
}
