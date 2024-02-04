package com.techlab.grocery.application.controller;

import com.techlab.grocery.application.entity.DTO.OrderDTO;
import com.techlab.grocery.application.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * The class OrderController
 * <p>
 * Controller to handle all order related requests.
 */
@RestController
@RequestMapping("/v1")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(description = "Save orders, Min Role: USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders saved successfully", content = @Content(mediaType = "text", schema = @Schema(implementation = String.class), examples = {@ExampleObject(name = "Success", value = "Orders saved successfully")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/user/orders")
    public ResponseEntity<String> saveOrder(@RequestBody List<OrderDTO> orderDetails) {
        boolean savedOrders = orderService.saveAll(orderDetails);
        if (savedOrders) {
            logger.info("Order saved successfully");
            return ResponseEntity.ok("Order saved successfully");
        } else {
            logger.error("Order not saved");
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Get all orders for self, Min Role: USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class),
                            examples = {@ExampleObject(name = "OrderList", value = "[{\"id\":1,\"groceryId\":20,\"quantity\":5,\"totalPrice\":120},{\"id\":5,\"groceryId\":56,\"quantity\":10,\"totalPrice\":500}]")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/user/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByUserId() {
        List<OrderDTO> orders = orderService.findAllByUserId();
        if (orders != null) {
            logger.info("Orders retrieved successfully");
            return ResponseEntity.ok(orders);
        } else {
            logger.error("Orders not retrieved");
            throw HttpServerErrorException.create(HttpStatus.NOT_FOUND, "Orders not found", null, null, null);
        }
    }
}
