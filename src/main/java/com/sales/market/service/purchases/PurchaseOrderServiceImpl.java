/**
 * @author: Alan Benavides
 */

package com.sales.market.service.purchases;

import com.sales.market.model.Item;
import com.sales.market.model.purchases.MeasureUnit;
import com.sales.market.model.purchases.ProviderItem;
import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.model.purchases.PurchaseOrderDetail;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.provider.PurchaseOrderRepository;
import com.sales.market.service.GenericServiceImpl;
import com.sales.market.service.ItemServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl extends GenericServiceImpl<PurchaseOrder> implements PurchaseOrderService {
    private final PurchaseOrderRepository repository;
    private final ProviderItemServiceImpl providerItemService;
    private final ItemServiceImpl itemService;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repository, ProviderItemServiceImpl providerItemService, ItemServiceImpl itemService) {
        this.repository = repository;
        this.providerItemService = providerItemService;
        this.itemService = itemService;
    }

    @Override
    protected GenericRepository<PurchaseOrder> getRepository() {
        return repository;
    }

    @Override
    public void buyMostCheapProviderItem(String codeItem, int quantity) {
        Optional<ProviderItem> itemOptional = this.providerItemService.getBestPrice(codeItem);

        if (itemOptional.isPresent()){
            ProviderItem providerItem = itemOptional.get();
            Item item = this.itemService.getItemByCode(codeItem);

            PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
            purchaseOrderDetail.setItem(item);
            purchaseOrderDetail.setItemCode(codeItem);
            purchaseOrderDetail.setProviderItemCode(providerItem.getProviderCode());
            purchaseOrderDetail.setUnitCost(new BigDecimal(providerItem.getPrice()));
            purchaseOrderDetail.setQuantity(new BigDecimal(quantity));
            purchaseOrderDetail.setTotalAmount(new BigDecimal(quantity), new BigDecimal(providerItem.getPrice()));

            // Se puede pasar al servicio para measureUnit y recuperarlo con la clave de unidad
            MeasureUnit measureUnit = new MeasureUnit();
            measureUnit.setMeasureUnitCode("unidad");
            measureUnit.setName("UNIDAD");
            measureUnit.setDescription("Un solo item");
            purchaseOrderDetail.setMeasureUnit(measureUnit);

            // Falta implementar, pero esto ya son setters sin logica interesante
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setDate(new Date());
            //purchaseOrder.setProvider(provider);
            purchaseOrder.setGloss("");
            purchaseOrder.setDefaultDetail(purchaseOrderDetail);
            purchaseOrder.setProviderCode(providerItem.getProviderCode());

            purchaseOrderDetail.setPurchaseOrder(purchaseOrder);

            this.repository.save(purchaseOrder);
        }
    }
}