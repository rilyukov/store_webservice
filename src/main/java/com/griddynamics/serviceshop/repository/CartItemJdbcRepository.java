package com.griddynamics.serviceshop.repository;

import com.griddynamics.serviceshop.dto.ProductDto;
import com.griddynamics.serviceshop.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("cartItemRepository")
public class CartItemJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addItem(ProductDto productDto, String sessionId) {
        jdbcTemplate.update("insert into cart values(?,?,?)",
                sessionId, productDto.getId(), productDto.getQuantity());

    }

    public List<CartItem> getCartItems(String sesionId) {
        return jdbcTemplate.query("select cart.product_id as productId," +
                        "product.price as price, product.title as title from cart inner join product on " +
                        "cart.product_id = product.id where cart.session_id = ?",
                new Object[]{sesionId}, new CartItemRowMapper());
    }

    public void updateCartItem(Long id, Long quantity, String sessionId) {
        jdbcTemplate.update("update cart set quantity = ? where session_id = ? and product_id = ?",
                quantity, sessionId, id);
    }

    public void removeCartItem(Long id, String sessionId){
        
    }

    class CartItemRowMapper implements RowMapper<CartItem> {

        @Override
        public CartItem mapRow(ResultSet resultSet, int i) throws SQLException {
            CartItem item = new CartItem();
            item.setProductId(resultSet.getLong("productId"));
            item.setPrice(resultSet.getDouble("price"));
            item.setProductTitle(resultSet.getString("title"));
            return item;
        }
    }

}
