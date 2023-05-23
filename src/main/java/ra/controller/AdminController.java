package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.Order;
import ra.model.entity.OrderShow;
import ra.model.service.ShowOrderService;
import ra.model.service.order.OrderServiceIMPL;
import ra.model.service.user.UserServiceIMPL;

import java.util.List;

@Controller
@RequestMapping("/adminController")
public class AdminController {
    OrderServiceIMPL orderServiceIMPL = new OrderServiceIMPL();
    ShowOrderService showOrderService = new ShowOrderService();
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
@GetMapping("/getAll")
    public String getAll(Model model){
    float sum = orderServiceIMPL.getTotal();
    float sumMonth = orderServiceIMPL.getTotalInMonth();
    int count = 0;
    int countTrue = orderServiceIMPL.getCountTrue();
    List<Order> list = orderServiceIMPL.findAll();
    for (int i = 0; i < list.size(); i++) {
        count ++;
    }
    List<Order> orderList = orderServiceIMPL.getOrderFalse();
    model.addAttribute("sum",sum);
    model.addAttribute("sumMonth",sumMonth);
    model.addAttribute("count",count);
    model.addAttribute("countTrue",countTrue);
    model.addAttribute("listOrders",orderList);
    return "admin";
}

@GetMapping("goAdmin")
public String goAdmin(Model model){
    float sum = orderServiceIMPL.getTotal();
    float sumMonth = orderServiceIMPL.getTotalInMonth();
    model.addAttribute("sum",sum);
    model.addAttribute("sumMonth",sumMonth);
    return "admin";
}
@GetMapping("setStatus")
    public String getCount(@RequestParam("orderId") int orderId,Model model){
    orderServiceIMPL.setStatus(orderId);
    return "redirect:getAll";
}
@GetMapping("showOrder")
    public String showOrder(@RequestParam("orderId") int orderId,Model model){
   List<OrderShow> list = showOrderService.findByOrderId(orderId);
   model.addAttribute("list",list);
   model.addAttribute("orderId",orderId);
    return "checkOrder";
}
}
