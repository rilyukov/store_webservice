package com.griddynamics.serviceshop;

import com.griddynamics.serviceshop.repository.ProductJdbcRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ProductRepositoryTest {

    @Autowired
    ProductJdbcRepository productJdbcRepository;

    @Test
    public void allProductsShouldBeFound() {
        Assert.assertNotNull(productJdbcRepository.getAllProducts());
        Assert.assertEquals(4, productJdbcRepository.getAllProducts().size());
    }

    @Test
    public void productShouldBeFoundById() {
        Assert.assertNotNull(productJdbcRepository.getProductById(1L));
        Assert.assertEquals(1, productJdbcRepository.getProductById(1L).getId());
    }

    @Test
    public void productShouldBeUpdated() {
        //when
        productJdbcRepository.updateProduct(1L, 99L);

        //then
        Assert.assertEquals(99, productJdbcRepository.getProductById(1L).getQuantity());
    }

}
