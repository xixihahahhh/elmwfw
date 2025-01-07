package elm.controller;

import elm.pojo.Business;
import elm.pojo.Food;
import elm.pojo.Orderdetailet;
import elm.pojo.Orders;
import elm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/add")
    public int add(@RequestBody Orders orders){
        try{
            orders.setOrderdate(new Date());
            ordersService.createdOrder(orders);
            return orders.getOrderid();
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @PostMapping("/addList")
    public boolean addList(@RequestBody List<Orderdetailet> orderdetailets){
        System.out.println(orderdetailets);
        try{
            for(Orderdetailet orderdetailet : orderdetailets){
                ordersService.createdOrderDetailet(orderdetailet);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/getAll")
    public List<Orders> getAll(String userid,Integer orderstate){
        List<Orders> allOrders = ordersService.findAllOrders(userid, orderstate);
        for(Orders orders : allOrders){
            List<Orderdetailet> allOrderDetail = ordersService.findAllOrderDetail(orders.getOrderid());
            for(Orderdetailet orderdetailet : allOrderDetail){
                Food food = ordersService.findFood(orderdetailet.getFoodid());
                orderdetailet.setFood(food);
            }
            Business business = ordersService.findBusiness(orders.getBusinessid());
            orders.setBusiness(business);
            orders.setDetailList(allOrderDetail);
        }
        return allOrders;
    }

    @PostMapping("/edit/{orderid}")
    public boolean edit(@PathVariable("orderid") Integer orderid){
        try{
            ordersService.updateOrdersState(orderid);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/getOrder")
    public Orders getOrder(Integer orderid){
        Orders orders = ordersService.findOne(orderid);
        List<Orderdetailet> allOrderDetail = ordersService.findAllOrderDetail(orders.getOrderid());
        for(Orderdetailet orderdetailet : allOrderDetail){
            Food food = ordersService.findFood(orderdetailet.getFoodid());
            orderdetailet.setFood(food);
        }
        Business business = ordersService.findBusiness(orders.getBusinessid());
        orders.setBusiness(business);
        orders.setDetailList(allOrderDetail);
        return orders;
    }

    @DeleteMapping("/delete/{orderid}")
    public boolean delete(@PathVariable("orderid") Integer orderid) {
        return ordersService.deleteOrder(orderid);
    }
}
