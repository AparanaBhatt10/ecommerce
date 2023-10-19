package com.ecomm.orderservice.utilities;

import java.math.BigDecimal;

import com.ecomm.orderservice.domain.Product;

public class CartUtilities {

    public static BigDecimal getSubTotalForItem(Product product, int quantity){
       return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}
