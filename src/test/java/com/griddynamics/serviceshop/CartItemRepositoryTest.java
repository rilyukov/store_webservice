package com.griddynamics.serviceshop;

import com.griddynamics.serviceshop.dto.ProductDto;
import com.griddynamics.serviceshop.repository.CartItemJdbcRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartItemRepositoryTest {
    @TestConfiguration
    static class CartItemRepositoryTestImpl{
        @Bean
        public CartItemJdbcRepository cartItemJdbcRepository(){
            return new CartItemJdbcRepository();

        }
    }

    @Autowired
    CartItemJdbcRepository cartItemJdbcRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void item_should_be_added(){
        //given
        ProductDto product = new ProductDto(1,55);

        //when
        cartItemJdbcRepository.addItem(product,"MySession");

        //then
        Assert.assertNotNull(jdbcTemplate.queryForRowSet("select * from cart" ));

    }

    @Test
    public void getCartItemsTest(){
        //given
        ProductDto product1 = new ProductDto(1,55);
        ProductDto product2 = new ProductDto(2,22);

        //when
        cartItemJdbcRepository.addItem(product1, "q");
        cartItemJdbcRepository.addItem(product2, "q");

        //then
        Assert.assertNotNull(cartItemJdbcRepository.getCartItems("q"));
        Assert.assertEquals(2, cartItemJdbcRepository.getCartItems("q").size());

    }
}
