package com.example.controller;

import com.example.domain.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll() {
        final List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value = "/list/withoutRelationships", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAllWithoutRelationships() {
        final List<Product> products = productService.findAllWithoutRelationships();
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        final Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(value = "/name/{value}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable String value) {
        final Product product = productService.findByName(value);
        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(value = "/relationships/{idProduct}/{idImage}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findByChildId(@PathVariable Long idProduct, @PathVariable Long idImage) {
        final List<Product> products = productService.findByRelationships(idProduct, idImage);
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value = "/child/{idProduct}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findChildByProduct(@PathVariable Long idProduct) {
        final List<Product> products = productService.findChildByProduct(idProduct);
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Product> save(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public ResponseEntity<List<Product>> save(@RequestBody List<Product> products) {
        if (products != null) {
            products.forEach(product -> {
                productService.save(product);
            });
        }
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
