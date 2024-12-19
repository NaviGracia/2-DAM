package com.ivangarcia.springboot.actividadfinal.tienda.models;

import java.util.ArrayList;

public class Product implements Cloneable{
    ArrayList<ProductType> tiposProducto = generarTiposProducto();
    private Long id;
    private String nombre;
    private Long precio;
    private Long precioConTaxas;
    private Long idTipoProducto;
    private String nombreTipoProducto;

    public Product() {}

    public Product(Long id, String nombre, Long precio, Long idTipoProducto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.precioConTaxas = calculatePrecioWithIVA(precio);
        this.idTipoProducto = idTipoProducto;
        this.nombreTipoProducto = getNombrePorID(tiposProducto, idTipoProducto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getPrecioConTaxas() {
        return precioConTaxas;
    }

    public void setPrecioConTaxas(Long precioConTaxas) {
        this.precioConTaxas = precioConTaxas;
    }

    public Long getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Long idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }

    private Long calculatePrecioWithIVA(Long precio){
        return precio + ((precio * 21) / 100);
    }

    public ArrayList<ProductType> generarTiposProducto(){
        ArrayList<ProductType> tiposProductos = new ArrayList<>();
        tiposProductos.add(new ProductType(1L, "Play Booster", "Sobre que contiene 15 cartas"));
        tiposProductos.add(new ProductType(2L, "Bundle", "Caja Temática del set que contiene 9 Play Boosters, 1 dado d20, 1 Sobre de Coleccionista de Muestra y 40 cartas de tierra (20 Full-Art Foil y 20 Full-Art)"));
        return tiposProductos;
    }

    public String getNombrePorID(ArrayList<ProductType> tipos, Long id){
        String nombre = "";
        for (ProductType tipo : tipos) {
            if (tipo.getId()==id) {
                nombre = tipo.getNombre();
            }
        }
        return nombre;
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
