package com.sales.market.repository;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ItemInventoryRepository extends GenericRepository<ItemInventory>{
    /*@Query("SELECT i FROM ItemInventory i WHERE i.item=item")
    Collection<ItemInventory> findByItem(Item item);*/
}
