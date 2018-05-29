package com.griddynamics.serviceshop.repository;

import com.griddynamics.serviceshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("productRepository")
public class ProductJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("select * from product", new ProductRowMapper());
    }

    public Product getProductById(Long id) {
        Product foundedProduct = null;
        try {
            foundedProduct = jdbcTemplate.queryForObject("Select * from product where id = ?",
                    new Object[]{id}, new ProductRowMapper());
        } catch (DataAccessException ex) {

        }
        return foundedProduct;

    }

    public Product getProductByTitle(String title) {
        Product foundedProduct = null;
        try {
            foundedProduct = jdbcTemplate.queryForObject("Select * from product where title = ?",
                    new Object[]{title}, new ProductRowMapper());
        } catch (DataAccessException ex) {

        }
        return foundedProduct;

    }

    public void updateProduct(Long productId, Long quantity) {
        jdbcTemplate.update("update product set quantity = ? where id = ?", quantity, productId);
    }


    class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setTitle(resultSet.getString("title"));
            product.setQuantity(resultSet.getLong("quantity"));
            product.setPrice(resultSet.getDouble("price"));
            return product;
        }
    }
}
