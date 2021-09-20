package com.sales.market.service;

import com.sales.market.model.Role;
import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.PurchaseOrderRepository;
import com.sales.market.repository.RoleRepository;

public class PurchaseOrderImpl extends GenericServiceImpl<PurchaseOrder> implements PurchaseOrderService{
    private PurchaseOrderRepository repository;

    public PurchaseOrderImpl(PurchaseOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<PurchaseOrder> getRepository() {
        return repository;
    }

}
