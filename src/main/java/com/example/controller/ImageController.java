package com.example.controller;

import com.example.domain.Image;
import com.example.service.ImageService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Image>> findAll() {
        final List<Image> images = imageService.findAll();
        return ResponseEntity.ok().body(images);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Image> findById(@PathVariable Long id) {
        final Image image = imageService.findById(id);
        return ResponseEntity.ok().body(image);
    }

    @RequestMapping(value = "/child/{idProduct}", method = RequestMethod.GET)
    public ResponseEntity<List<Image>> findChildByProduct(@PathVariable Long idProduct) {
        final List<Image> products = productService.findImagesByProduct(idProduct);
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Image> save(@RequestBody Image image) {
        imageService.save(image);
        return ResponseEntity.ok(image);
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public ResponseEntity<List<Image>> save(@RequestBody List<Image> images) {
        if (images != null) {
            images.forEach(image -> {
                imageService.save(image);
            });
        }
        return ResponseEntity.ok(images);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable Long id) {
        imageService.delete(id);
    }
}
