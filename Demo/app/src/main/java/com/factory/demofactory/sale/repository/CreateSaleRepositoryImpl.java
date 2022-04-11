package com.factory.demofactory.sale.repository;

import android.content.Context;

import com.factory.database.database.AppDataBase;
import com.factory.database.entity.ProducView;
import com.factory.database.entity.Product;
import com.factory.database.entity.ProductSale;
import com.factory.database.entity.Sale;
import com.factory.demofactory.R;
import com.factory.demofactory.sale.presenter.CreateSalePresenter;

import java.util.ArrayList;
import java.util.List;

public class CreateSaleRepositoryImpl implements CreateSaleRepository {

    private Context context;
    private AppDataBase appDataBase;
    private CreateSalePresenter presenter;

    public CreateSaleRepositoryImpl(Context context, CreateSalePresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        appDataBase = AppDataBase.getAppDatabase(context);
    }

    @Override
    public void createSale(List<ProducView> productViews, int clientId, int transmitterId) {
        double total = getTotal(productViews);
        Sale sale = new Sale();
        sale.clientId = clientId;
        sale.transmitterId = transmitterId;
        sale.total = total;
        int saleId = (int) appDataBase.saleDao().insertSale(sale);
        if (saleId > 0) {
            List<ProducView> products = insertProducts(productViews);
            if (products.size() > 0) {
                List<ProductSale> productSales = generateProductSale(products, saleId);
                appDataBase.productSaleDao().insertAll(productSales);
                presenter.success(saleId);
            } else {
                presenter.error(context.getString(R.string.create_sale), context.getString(R.string.no_insert_product));
            }
        } else {
            presenter.error(context.getString(R.string.create_sale), context.getString(R.string.no_insert_sale));
        }
    }

    @Override
    public double calculateTotal(List<ProducView> productViews) {
        return getTotal(productViews);
    }

    private double getTotal(List<ProducView> productLists) {
        double total = 0;
        for (ProducView product : productLists) {
            double sumProduct = product.quantity * product.price;
            total = total + sumProduct;
        }
        return total;
    }

    private List<ProducView> insertProducts(List<ProducView> productLists) {
        List<ProducView> productListsResult = new ArrayList<>();
        for (int i = 0; i < productLists.size(); i++) {
            ProducView productList = productLists.get(i);
            Product product = new Product();
            product.code = productList.code;
            product.description = productList.description;
            product.price = productList.price;
            int id = (int) appDataBase.productDao().insertProduct(product);
            if (id > 0) {
                productList.id = id;
                productListsResult.add(productList);
            }
        }
        return productListsResult;
    }

    private List<ProductSale> generateProductSale(List<ProducView> productLists, int saleId) {
        List<ProductSale> productSales = new ArrayList<>();
        for (ProducView productList : productLists) {
            ProductSale productSale = new ProductSale();
            productSale.productId = productList.id;
            productSale.saleId = saleId;
            productSale.quantity = productList.quantity;
            productSales.add(productSale);
        }
        return productSales;
    }
}
