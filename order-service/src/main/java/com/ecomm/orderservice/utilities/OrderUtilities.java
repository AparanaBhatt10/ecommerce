package com.ecomm.orderservice.utilities;

import java.math.BigDecimal;
import java.util.List;

import com.ecomm.orderservice.domain.Item;

public class OrderUtilities {

    public static BigDecimal countTotalPrice(List<Item> cart){
        BigDecimal total = BigDecimal.ZERO;
        for(int i = 0; i < cart.size(); i++){
            total = total.add(cart.get(i).getSubTotal());
        }
        return total;
    }
}
