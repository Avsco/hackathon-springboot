/**
 * @author: Alan Benavides
 */

package com.sales.market.repository.provider;

import com.sales.market.model.purchases.ProviderItem;
import com.sales.market.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ProviderItemRepository extends GenericRepository<ProviderItem> {
    @Query("SELECT pi FROM ProviderItem pi WHERE pi.itemCode = ?1")
    Collection<ProviderItem> findByProviderItemCode(String ItemCode);
}