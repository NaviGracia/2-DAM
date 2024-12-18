package com.ig.springboot.id.app.springboot_id2.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ig.springboot.id.app.springboot_id2.models.Product;

@Repository
public class ProductRepositoryFoo implements ProductRepository{
    @Override
    public List<Product> findAll(){
        return Collections.singletonList(new Product(1L, "Ozolith", 600L));
    }

    @Override
    public Product findById(Long id){
        return new Product(id, "Ozolith", 600L);
    }
}
package com.ig.springboot.id.app.springboot_id2.repositories;
