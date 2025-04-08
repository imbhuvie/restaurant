package com.restaurant.Restaurant.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(nullable = false)
    private double openingStock;

    @Column(nullable = false)
    private double closingStock;

    @Column(nullable = false)
    private double currentPurchases;

    @Column(nullable = false)
    private double issuedStock;

    @Column(nullable = false)
    private double totalStock;

    @Temporal(TemporalType.DATE)
    private Date date;

    @PrePersist
    protected void oncreate(){
        this.date=new Date();
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getOpeningStock() {
        return openingStock;
    }

    public void setOpeningStock(double openingStock) {
        this.openingStock = openingStock;
    }

    public double getClosingStock() {
        return closingStock;
    }

    public void setClosingStock(double closingStock) {
        this.closingStock = closingStock;
    }

    public double getCurrentPurchases() {
        return currentPurchases;
    }

    public void setCurrentPurchases(double currentPurchases) {
        this.currentPurchases = currentPurchases;
    }

    public double getIssuedStock() {
        return issuedStock;
    }

    public void setIssuedStock(double issuedStock) {
        this.issuedStock = issuedStock;
    }

    public double getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(double totalStock) {
        this.totalStock = totalStock;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", item=" + item +
                ", supplier=" + supplier +
                ", openingStock=" + openingStock +
                ", closingStock=" + closingStock +
                ", currentPurchases=" + currentPurchases +
                ", issuedStock=" + issuedStock +
                ", totalStock=" + totalStock +
                ", date=" + date +
                '}';
    }
}
