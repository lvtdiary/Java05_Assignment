package com.example.assignment01.services;

import com.example.assignment01.models.Order;
import com.example.assignment01.models.OrderDetail;
import com.example.assignment01.models.Product;
import com.example.assignment01.models.User;
import com.example.assignment01.repositories.OrderDetailRepository;
import com.example.assignment01.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public void createOrder(User user, List<Integer> productIds, List<Integer> quantities) {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setUser(user);
        order.setOrderDetails(new ArrayList<>());

        float totalMoney = 0;

        for (int i = 0; i < productIds.size(); i++) {
            Product product = this.productService.findById(productIds.get(i));
            Integer qty = quantities.get(i);
            float price = product.getPrice();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetail.setQty(qty);
            orderDetail.setPrice(price);

            order.getOrderDetails().add(orderDetail);
            totalMoney += qty * price;
        }
        order.setTotalMoney(totalMoney);
        this.orderRepository.save(order);
    }
}
