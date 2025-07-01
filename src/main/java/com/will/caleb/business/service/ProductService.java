package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {

    Product incluir(Product product);

    Product edit(Product product);

    Page<Product> list(Pageable pageable);

}
