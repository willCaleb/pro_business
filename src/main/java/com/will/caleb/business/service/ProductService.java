package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.Product;
import com.will.caleb.business.model.records.responses.artificial_intelligence.ProductAnaliseIAResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product incluir(Product product);

    Product edit(Product product);

    Page<Product> list(Pageable pageable);

    ProductAnaliseIAResponse generateAiAnalytics(Pageable pageable);

}
