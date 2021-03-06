package com.sales.market.model.purchases;

import com.sales.market.model.Item;
import com.sales.market.model.ModelBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class PurchaseOrderDetail extends ModelBase {
    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal quantity;

    @Column(precision = 16, scale = 6)
    private BigDecimal unitCost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MeasureUnit measureUnit;

    @Column(precision = 16, scale = 6)
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    @Column(nullable = false)
    private String itemCode;

    @Column(nullable = false)
    private String providerItemCode;
}
