package org.dargor.productmsreactive.core.util;

import org.dargor.productmsreactive.app.dto.ProductRequestDto;
import org.dargor.productmsreactive.core.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {

    public static List<Product> toProducts(List<ProductRequestDto> products) {
        return products
                .stream()
                .map(productRequestDto -> Product.builder()
                        .denomination(productRequestDto.getDenomination())
                        .unitPrice(productRequestDto.getUnitPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
