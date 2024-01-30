package com.techlab.grocery.application.service.impl;

import com.techlab.grocery.application.config.AuthorizationService;
import com.techlab.grocery.application.dao.OrderRepository;
import com.techlab.grocery.application.dao.UserRepository;
import com.techlab.grocery.application.entity.DTO.OrderDTO;
import com.techlab.grocery.application.entity.Grocery;
import com.techlab.grocery.application.entity.Order;
import com.techlab.grocery.application.entity.User;
import com.techlab.grocery.application.service.GroceryService;
import com.techlab.grocery.application.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The DefaultOrderService class
 * <p>
 * This class implements OrderService interface.
 */
@Service
public class DefaultOrderService implements OrderService {

    private OrderRepository repository;

    private UserRepository userRepository;

    private GroceryService groceryService;

    private AuthorizationService authorizationService;

    @Autowired
    public DefaultOrderService(OrderRepository repository, UserRepository userRepository, GroceryService groceryService, AuthorizationService authorizationService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.groceryService = groceryService;
        this.authorizationService = authorizationService;
    }

    @Override
    public OrderDTO save(OrderDTO orderDetail, String userId) throws Exception {
        Order order = getOrderFromOrderDetails(orderDetail, userId);
        Order savedOrder = repository.save(order);
        if (savedOrder != null) {
            return orderDetail;
        } else {
            throw new Exception("Order not saved");
        }
    }

    @Override
    public Order findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<OrderDTO> findAllByUserId() {
        String userId = authorizationService.getLoggedInUserId();
        List<Order> orders = repository.findAllByUserUserId(userId);
        List<OrderDTO> returnOrders = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO orderDetail = new OrderDTO();
            orderDetail.setId(order.getId());
            orderDetail.setGroceryId(order.getGrocery().getId());
            orderDetail.setQuantity(order.getQuantity());
            orderDetail.setTotalPrice(order.getTotalPrice());
            returnOrders.add(orderDetail);
        }
        return returnOrders;
    }

    @Override
    public boolean saveAll(List<OrderDTO> orderDetails) {
        String userId = authorizationService.getLoggedInUserId();
        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDetail : orderDetails) {
            orders.add(getOrderFromOrderDetails(orderDetail, userId));
        }
        repository.saveAll(orders);
        return true;
    }

    @Transactional
    private Order getOrderFromOrderDetails(OrderDTO orderDetail, String userId) {
        Order order = new Order();
        User user = userRepository.findById(userId).orElse(null);
        Grocery grocery = groceryService.findById(orderDetail.getGroceryId());
        order.setId(orderDetail.getId());
        order.setGrocery(grocery);
        order.setUser(user);
        order.setQuantity(orderDetail.getQuantity());
        if (grocery.getQuantity() < orderDetail.getQuantity()) {
            throw new RuntimeException("Not enough quantity to place order");
        }
        grocery.setQuantity(grocery.getQuantity() - orderDetail.getQuantity());
        order.setTotalPrice(grocery.getPrice() * orderDetail.getQuantity());
        return order;
    }
}
