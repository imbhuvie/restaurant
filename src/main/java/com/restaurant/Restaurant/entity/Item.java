package com.restaurant.Restaurant.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Item {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String itemId;
    @Column(nullable = false)
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "unitId")
    private MeasurementUnit unit;

    private String itemDescription;


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasurementUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", unit=" + unit +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
