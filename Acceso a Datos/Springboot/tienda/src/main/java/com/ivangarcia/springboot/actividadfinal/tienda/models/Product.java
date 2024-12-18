package com.ivangarcia.springboot.actividadfinal.tienda.models;

public class Product implements Cloneable{

    private Long id;
    private String name;
    private Long price;
    private Long priceWithTaxes;
    private Long productTypeId;
    private String productTypeName;

    public Product() {}

    public Product(Long id, String name, Long price, Long productTypeId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.priceWithTaxes = calculatePriceWithIVA(price);
        this.productTypeId = productTypeId;
        this.productTypeName = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPriceWithTaxes() {
        return priceWithTaxes;
    }

    public void setPriceWithTaxes(Long priceWithTaxes) {
        this.priceWithTaxes = priceWithTaxes;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    private Long calculatePriceWithIVA(Long price){
        return price + ((price * 21) / 100);
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

}
