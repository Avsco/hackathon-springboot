/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model.purchases;

import com.sales.market.model.Item;
import com.sales.market.model.ModelBase;

public class ProviderItem extends ModelBase {

    private Provider provider;
    //codigo con el que el proveedor conoce al item
    private String providerItemCode;

    //facilitara los queries
    private String providerCode;

    private Item item;

    //facilitara los queries
    private String itemCode;

    private MeasureUnit measureUnit;

    private Double price;

    public void setProvider(Provider provider) {
        this.provider = provider;
        this.providerCode = provider.getCode();
    }

    public void setItem(Item item) {
        this.item = item;
        this.itemCode = item.getCode();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
}
