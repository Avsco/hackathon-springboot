package com.sales.market.dto;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;

import java.math.BigDecimal;

public class ItemInventoryDto extends DtoBase<ItemInventory> {
    private Item item;
    private BigDecimal stockQuantity;

    private BigDecimal lowerBoundThreshold;
    private BigDecimal upperBoundThreshold;
    private BigDecimal totalPrice;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
