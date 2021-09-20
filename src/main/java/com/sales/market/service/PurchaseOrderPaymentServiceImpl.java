package com.sales.market.service;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;
import com.sales.market.model.purchases.*;
import com.sales.market.repository.*;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PurchaseOrderPaymentServiceImpl extends GenericServiceImpl<PurchaseOrderPayment> implements PurchaseOrderPaymentService{
    private final PurchaseOrderPaymentRepository repository;
    private final PurchaseOrderService orderService;
    private final ItemInventoryServiceImpl itemInventoryService;

    public PurchaseOrderPaymentServiceImpl(PurchaseOrderPaymentRepository repository, PurchaseOrderService orderService, ItemInventoryServiceImpl itemInventoryService) {
        this.repository = repository;
        this.orderService = orderService;
        this.itemInventoryService = itemInventoryService;
    }


    protected GenericRepository<PurchaseOrderPayment> getRepository() {
        return repository;
    }

    @Override
    public PurchaseOrderPayment save(PurchaseOrderPayment model) {
        validateSave(model);
        PurchaseOrderPayment orderPayment = getRepository().save(model);
        PurchaseOrderPaymentKind paymentype= orderPayment.getPurchaseOrderPaymentKind();

        PurchaseOrder order = orderPayment.getPurchaseOrder();
        purchaseOrderInventory(order); //rebajar el stock del inventario
        if(paymentype == PurchaseOrderPaymentKind.ADVANCE_PAYMENT){
            order.setPaymentStatus(PurchaseOrderPaymentStatus.PARTIAL_PAYMENT);

        }else{
            order.setPaymentStatus(PurchaseOrderPaymentStatus.FULLY_PAID);
        }
        orderService.save(order);
        return findById(orderPayment.getId());
    }

    private void purchaseOrderInventory(PurchaseOrder model){
        List<PurchaseOrderDetail> listOfOrderDetails = model.getPurchaseOrderDetailList(); //lista de detalles
        for(PurchaseOrderDetail pDetail : listOfOrderDetails){
            BigDecimal quanty = pDetail.getQuantity(); //cantidad en ordendecompradetalle
            Item itemToFind = pDetail.getItem(); //item
            List<ItemInventory> listOfItem= itemInventoryService.findByItem(itemToFind); //encontrar el item en inventory
            for(ItemInventory lItem: listOfItem){
                BigDecimal rest = lItem.getStockQuantity();  //cantidad en inventario
                lItem.setStockQuantity(rest.subtract(quanty)); //set la (cantidadinventario - cantidad de orden de compra)
                itemInventoryService.save(lItem);
            }
        }
    }

    @Override
    public PurchaseOrderPayment findById(Long id) {
        Optional<PurchaseOrderPayment> orderPayment = repository.findById(id);
        if (orderPayment.isPresent()) {
            return orderPayment.get();
        }
        throw new NoResultException();
    }

}
