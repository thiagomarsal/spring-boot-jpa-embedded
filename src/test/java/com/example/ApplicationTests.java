package com.example;

import com.example.app.SpringBootJpaApplication;
import com.example.domain.Image;
import com.example.domain.Product;
import com.example.service.ImageService;
import com.example.service.ProductService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @Test
    public void test1Setup() {
        Image image = new Image();
        image.setName("image-setup");
        image.setPath("/src/image-setup.jpg");
        imageService.save(image);

        image = new Image();
        image.setName("image-setup2");
        image.setPath("/src/image-setup2.jpg");
        imageService.save(image);

        Product product = new Product();
        product.setName("product-setup");
        product.setPrice(new BigDecimal(95.55));
        productService.save(product);

        product = new Product();
        product.setName("product-setup2");
        product.setPrice(new BigDecimal(55.55));
        productService.save(product);

        product = new Product();
        product.setName("product-setup3");
        product.setPrice(new BigDecimal(999.99));
        product.setProduct(productService.findById(1L));
        product.setImages(Arrays.asList(imageService.findById(1L)));
        productService.save(product);
    }

    @Test
    public void test2FindAllImages() {
        final List entity = this.testRestTemplate.getForObject("/image/list", List.class);
        then(entity).isNotEmpty();
    }

    @Test
    public void test3GetAllProductsExcludingEelationships() {
        final List entity = this.testRestTemplate.getForObject("/product/list/withoutRelationships", List.class);
        then(entity).isNotEmpty();
    }

    @Test
    public void test4GetAllProductsIncludingSpecifiedRelationships() {
        final String idProduct = "1";
        final String idImage = "1";
        final List entity = this.testRestTemplate.getForObject("/product/relationships/" + idProduct + "/" + idImage, List.class);
        System.out.println(entity);
        then(entity).isNotEmpty();
    }

    @Test
    public void test5GetUsingSpecificProductIdentity() {
        final Product entity = this.testRestTemplate.getForObject("/product/1", Product.class);
        then(entity).isNotNull();
    }

    @Test
    public void test6GetUsingSpecificProductIdentity() {
        final Product entity = this.testRestTemplate.getForObject("/product/name/product-setup", Product.class);
        then(entity).isNotNull();
    }

    @Test
    public void test7GetSetOfChildProductsForSpecificProduct() {
        final List entity = this.testRestTemplate.getForObject("/product/child/1", List.class);
        then(entity).isNotEmpty();
    }

    @Test
    public void test8GetSetOfImagesForSpecificProduct() {
        final List entity = this.testRestTemplate.getForObject("/image/child/1", List.class);
        then(entity).isNotEmpty();
    }

}
