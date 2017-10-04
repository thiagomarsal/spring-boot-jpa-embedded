package com.example.service;

import com.example.domain.Image;
import com.example.domain.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllWithoutRelationships() {
        return productRepository.findAllWithoutRelationships();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByRelationships(Long idProduct, Long idImage) {
        return productRepository.findByRelationships(idProduct, idImage);
    }

    @Override
    public List<Product> findChildByProduct(Long idProduct) {
        return productRepository.findChildByProduct(idProduct);
    }

    @Override
    public List<Image> findImagesByProduct(Long idProduct) {
        final List<Product> products = productRepository.findImagesByProduct(idProduct);
        final List<Image> images = new ArrayList<>();
        products.forEach(product -> images.addAll(product.getImages()));
        return images;
    }
}
