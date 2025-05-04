package com.restaurant.Restaurant.entity;

import jakarta.persistence.*;

@Entity
public class Supplier {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String supplierId;
    @Column(nullable = false)
    private String supplierName;
    private String supplierAddress;

    @Column(nullable = false)
    private String supplierContact;

    private String supplierPersonName;

    private String supplierPersonContact;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getSupplierPersonName() {
        return supplierPersonName;
    }

    public void setSupplierPersonName(String supplierPersonName) {
        this.supplierPersonName = supplierPersonName;
    }

    public String getSupplierPersonContact() {
        return supplierPersonContact;
    }

    public void setSupplierPersonContact(String supplierPersonContact) {
        this.supplierPersonContact = supplierPersonContact;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierAddress='" + supplierAddress + '\'' +
                ", supplierContact='" + supplierContact + '\'' +
                ", supplierPersonName='" + supplierPersonName + '\'' +
                ", supplierPersonContact='" + supplierPersonContact + '\'' +
                '}';
    }
}
