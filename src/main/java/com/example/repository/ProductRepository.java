package com.example.repository;

import com.example.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select t from Product t where t.product is null")
    public List<Product> findAllWithoutRelationships();

    @Query("select t from Product t where t.name = ?1")
    public Product findByName(String name);

    @Query("select t from Product t join t.images i where i.id = ?2 and t.product.id = ?1")
    public List<Product> findByRelationships(Long idProduct, Long idImage);

    @Query("select t from Product t where t.product.id = ?1")
    public List<Product> findChildByProduct(Long idProduct);

    @Query("select t from Product t join t.images i where t.product.id = ?1")
    public List<Product> findImagesByProduct(Long idProduct);

//    public Product findByRelationships(@Param("idProduct") Long idProduct, @Param("idImage") Long idImage);

//    @Query("Select m from Message m join fetch m.sender ms join fetch m.receiver mr where ms.id = :senderId or mr.id = :receiverId order by m.time desc")
//    List<Message> findBySenderIdOrReceiverIdOrderByTimeDesc(Long senderId, Long receiverId);


//    @Query("select c from Customer c join c.addresses a where (a.city = :cityName)")
//    List<Customer> findByCity(@Param("cityName")String city);

}
