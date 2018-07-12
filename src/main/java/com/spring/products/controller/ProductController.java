/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.products.controller;

import com.spring.products.model.Product;
import com.spring.products.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author stas
 */
@Controller
public class ProductController {
    
    @Autowired
    private ProductServiceImpl serviceImpl;
    
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/products/add")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "edit";
    }
    
    @PostMapping("/products")
    public String saveProduct(Product product) {
        serviceImpl.saveProduct(product);
        return "redirect:/products";
    }
    
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", serviceImpl.getAllProducts());
        return "products";
    }
    
    @GetMapping("/products/edit/{id}")
    public String editProduct(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("product", serviceImpl.findProduct(id));
        return "edit";
    }
    
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") String id) {
        serviceImpl.deleteProduct(id);
        return "redirect:/products";
    }
    
}
