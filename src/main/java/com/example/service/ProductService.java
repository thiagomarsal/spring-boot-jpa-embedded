package com.example.service;

import com.example.domain.Image;
import com.example.domain.Product;

import java.util.List;

public interface ProductService {

    public void delete(Long id);

    public Product save(Product product);

    public List<Product> findAll();

    public List<Product> findAllWithoutRelationships();

    public Product findById(Long id);

    public Product findByName(String name);

    public List<Product> findByRelationships(Long idProduct, Long idImage);

    public List<Product> findChildByProduct(Long idProduct);

    public List<Image> findImagesByProduct(Long idProduct);
}
