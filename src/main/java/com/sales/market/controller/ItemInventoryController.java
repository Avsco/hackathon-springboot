package com.sales.market.controller;

import com.sales.market.dto.ItemInventoryDto;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;

import com.sales.market.service.GenericService;
import com.sales.market.service.ItemInventoryService;
import com.sales.market.service.ItemInventoryServiceImpl;
import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/itemInventory")
public class ItemInventoryController extends GenericController<ItemInventory, ItemInventoryDto>{
    private ItemInventoryServiceImpl service;

    public ItemInventoryController(ItemInventoryServiceImpl service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }

    @GetMapping("/$expirationDate")
    public List<Item> itemsexpiration(int days) { //introduce los dias

        List<ItemInventory> listItemInventory = service.findAll();
        LocalDateTime now = LocalDateTime.now(); //dia de hoy
        LocalDateTime dayexpiration = now.plusDays(days); //dias en los que espero que expire
        List<Item> items = new ArrayList<>();
        for(ItemInventory i : listItemInventory){
            LocalDateTime expiration = i.getItem().getExpirationDate();
            if(expiration.isBefore(dayexpiration)){
                items.add(i.getItem()); //suponiendo que los items se eliminan cuando caducan solo faltaria los que faltan por caducar
            }

        }
        return items;
    }

}
