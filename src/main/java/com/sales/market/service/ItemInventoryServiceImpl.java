package com.sales.market.service;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInventoryRepository;
import com.sales.market.repository.ItemRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService{
    private final ItemInventoryRepository repository;

    @Autowired
    private EntityManager entityManager;
    public ItemInventoryServiceImpl(ItemInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return repository;
    }

    public List<ItemInventory> findByItem(Item itemI) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<ItemInventory> theQuery = currentSession.createQuery("from ItemInventory i where i.item=:itemFind");

        theQuery.setParameter("itemFind",itemI);
        theQuery.executeUpdate();
        List<ItemInventory> itemResult = theQuery.getResultList();
        return itemResult;

    }

}
