package com.sales.market.service.purchases;

import com.sales.market.model.purchases.ProviderItem;

import java.util.Optional;

public interface ProviderItemService {
    Optional<ProviderItem> getBestPrice(String ProviderItemCode);
}
