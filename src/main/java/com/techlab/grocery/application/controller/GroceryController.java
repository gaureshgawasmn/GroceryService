package com.techlab.grocery.application.controller;

import com.techlab.grocery.application.entity.Grocery;
import com.techlab.grocery.application.service.GroceryService;
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
 * The class GroceryController
 * <p>
 * Controller to handle all grocery related requests.
 */
@RestController
@RequestMapping("/v1")
public class GroceryController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private GroceryService groceryService;

    @Autowired
    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @Operation(description = "Save Grocery, Min Role: ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grocery saved successfully", content = @Content(mediaType = "text", schema = @Schema(implementation = String.class), examples = {@ExampleObject(name = "Success", value = "Grocery saved successfully")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/admin/groceries")
    public ResponseEntity<String> saveGrocery(@RequestBody Grocery grocery) {
        Grocery savedGrocery = groceryService.save(grocery);
        if (savedGrocery != null) {
            logger.info("Grocery saved successfully");
            return ResponseEntity.ok("Grocery saved successfully");
        } else {
            logger.error("Grocery not saved");
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Delete Grocery, Min Role: ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grocery deleted successfully", content = @Content(mediaType = "text", schema = @Schema(implementation = String.class), examples = {@ExampleObject(name = "Success", value = "Grocery deleted successfully")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/admin/groceries/{id}")
    public ResponseEntity<String> deleteGrocery(@PathVariable int id) {
        groceryService.deleteById(id);
        logger.info("Grocery deleted successfully");
        return ResponseEntity.ok("Grocery deleted successfully");
    }

    @Operation(description = "Update Grocery, Min Role: ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grocery updated successfully", content = @Content(mediaType = "text", schema = @Schema(implementation = String.class), examples = {@ExampleObject(name = "Success", value = "Grocery updated successfully")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/admin/groceries")
    public ResponseEntity<String> updateGrocery(@RequestBody Grocery grocery) {
        Grocery updatedGrocery = groceryService.update(grocery);
        if (updatedGrocery != null) {
            logger.info("Grocery updated successfully");
            return ResponseEntity.ok("Grocery updated successfully");
        } else {
            logger.error("Grocery not updated");
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Find all grocery by Id, Min Role: User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Groceries found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Grocery.class),
                            examples = {@ExampleObject(name = "grocery-list", value = "{\"id\":1,\"name\":\"Rice\",\"price\":55,\"quantity\":500}")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/admin/groceries/{id}")
    public ResponseEntity<Grocery> findGroceryById(@PathVariable int id) {
        Grocery grocery = groceryService.findById(id);
        if (grocery != null) {
            logger.info("Grocery found successfully");
            return ResponseEntity.ok(grocery);
        } else {
            logger.error("Grocery not found");
            throw HttpServerErrorException.create(HttpStatus.NOT_FOUND, "Grocery not found", null, null, null);
        }
    }

    @Operation(description = "Find all groceries, Min Role: ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Groceries found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Grocery.class),
                            examples = {@ExampleObject(name = "grocery-list", value = "[{\"id\":1,\"name\":\"Rice\",\"price\":55,\"quantity\":500},{\"id\":2,\"name\":\"Oil\",\"price\":90,\"quantity\":300}]")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/admin/groceries")
    public ResponseEntity<List<Grocery>> findAllGrocery() {
        return groceryService.findAllGroceryEntity();
    }

    @Operation(description = "Find all groceries, Min Role: User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Groceries found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Grocery.class),
                            examples = {@ExampleObject(name = "grocery-list", value = "[{\"id\":1,\"name\":\"Rice\",\"price\":55,\"quantity\":500},{\"id\":2,\"name\":\"Oil\",\"price\":90,\"quantity\":300}]")})),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/user/groceries")
    public ResponseEntity<List<Grocery>> findAllGroceryForUser() {
        return groceryService.findAllGroceryEntity();
    }
}
