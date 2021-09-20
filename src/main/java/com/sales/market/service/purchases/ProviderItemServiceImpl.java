/**
 * @author: Alan Benavides
 */

package com.sales.market.service.purchases;

import com.sales.market.model.purchases.ProviderItem;
import com.sales.market.model.purchases.PurchaseOrderDetail;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.provider.ProviderItemRepository;
import com.sales.market.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProviderItemServiceImpl extends GenericServiceImpl<ProviderItem> implements ProviderItemService {
    private final ProviderItemRepository repository;

    public ProviderItemServiceImpl(ProviderItemRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<ProviderItem> getRepository() {
        return repository;
    }

    @Override
    public Optional<ProviderItem> getBestPrice (String ProviderItemCode) {
        Collection<ProviderItem> items = this.repository.findByProviderItemCode(ProviderItemCode);

        Optional<ProviderItem> providerItem = items.stream().reduce((acc, item) -> {
            if(item.getPrice() < acc.getPrice()) return item;
            return acc;
        });

        return providerItem;
    }
}