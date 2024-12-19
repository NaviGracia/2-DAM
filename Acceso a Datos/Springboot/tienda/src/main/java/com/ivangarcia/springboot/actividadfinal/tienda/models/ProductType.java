package com.ivangarcia.springboot.actividadfinal.tienda.models;

public class ProductType {
    private Long id;
    private String name;
    private String description;

    public ProductType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void generarTipos(){
        ProductType pt1 = new ProductType(1L, "Play Booster", "Sobre que contiene 15 cartas");
        ProductType pt2 = new ProductType(2L, "Bundle", "Caja Temática del set que contiene 9 Play Boosters, 1 dado d20, 1 Sobre de Coleccionista de Muestra y 40 cartas de tierra (20 Full-Art Foil y 20 Full-Art)");
    }

    public String getNombrePorID(ProductType pt){
        return pt.getName()
    }
}
