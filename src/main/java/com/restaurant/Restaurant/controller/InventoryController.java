package com.restaurant.Restaurant.controller;

import com.restaurant.Restaurant.entity.Inventory;
import com.restaurant.Restaurant.entity.Item;
import com.restaurant.Restaurant.entity.MeasurementUnit;
import com.restaurant.Restaurant.entity.Supplier;
import com.restaurant.Restaurant.service.InventoryService;
import com.restaurant.Restaurant.service.ItemService;
import com.restaurant.Restaurant.service.MeasurementUnitService;
import com.restaurant.Restaurant.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    ItemService itemService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    MeasurementUnitService measurementUnitService;

    //    TO GOTO HOMEPAGE
    @GetMapping("/")
    public String goToIndex() {
        return "index";
    }

    //    =======================================SUPPLIER END-POINTS====================================================
//    SHOW ALL SUPPLIERS
    @GetMapping("/suppliers")
    public String findAllSuppliers(Model model) {
        List<Supplier> suppliers = supplierService.findAllSuppliers();
        if (suppliers.isEmpty())
            model.addAttribute("ifNotSupplier", "No suppliers Found.");
        else
            model.addAttribute("suppliers", suppliers);
        return "suppliers";
    }

    //  TO GOTO ADD SUPPLIER PAGE
    @GetMapping("/add-supplier")
    public String goToAddSuppliers(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "addSupplier";
    }

    //    ADD SUPPLIER
    @PostMapping("/addSupplier")
    public String insertSupplier(@ModelAttribute("supplier") Supplier supplier, RedirectAttributes redirectAttributes) {
        Supplier supplier1 = supplierService.insertSupplier(supplier);
        if (supplier1 != null) {
            redirectAttributes.addFlashAttribute("msg", "Supplier added");
            return "redirect:add-supplier";
        } else {
            redirectAttributes.addFlashAttribute("error", "Supplier not added");
            return "redirect:add-supplier";
        }

    }


//    =======================================ITEMS END-POINTS====================================================
//    TO SHOW ALL ITEMS

    @GetMapping("/items")
    public String getAllItems(Model model) {
        List<Item> items = itemService.findAllItems();
        if (items.isEmpty())
            model.addAttribute("ifNotItems", "No Items Found.");
        else
            model.addAttribute("items", items);
        return "items";
    }

    //    TO GOTO ADD ITEMS PAGE
    @GetMapping("/add-item")
    public String goToAddItems(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        model.addAttribute("units", measurementUnitService.allUnits());
        return "addItems";
    }
//    ADD ITEMS

    @PostMapping("/addItem")
    public String insertItem(@ModelAttribute("item") Item item, RedirectAttributes redirectAttributes) {
        System.out.println(item);
        Item item1 = itemService.insertItem(item);
        if (item1 != null) {
            redirectAttributes.addFlashAttribute("msg", "Item added");
            return "redirect:add-item";
        } else {
            redirectAttributes.addFlashAttribute("error", "Item not added");
            return "redirect:add-item";
        }
    }


//    =======================================INVENTORY END-POINTS====================================================
//  TO SHOW ALL INVENTORIES

    @GetMapping("/inventories")
    public String findAllInventories(Model model) {
        List<Inventory> inventories = inventoryService.findAllInventory();
        System.out.println(inventories);
        if (inventories.isEmpty())
            model.addAttribute("ifNotInventories", "No inventories Found.");
        else
            model.addAttribute("inventories",inventories );
        return "inventories";
    }

    //    TO GOTO ADD INVENTORY PAGE
    @GetMapping("/add-inventory")
    public String goToAddInventory(Model model) {
        List<Item> items = itemService.findAllItems();
        List<Supplier> suppliers = supplierService.findAllSuppliers();
        System.out.println(items);
        System.out.println(suppliers);
        model.addAttribute("items", items);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("inventory", new Inventory());
        return "addInventory";
    }

    //  PURCHASE INVENTORY
    @PostMapping("/addInventory")
    public String insertInventory(@ModelAttribute("inventory") Inventory inventory, RedirectAttributes redirectAttributes) {
//        System.out.println(inventory);
        Inventory inventoryExists = inventoryService.findInventoryByItemId(inventory.getItem().getItemId());
        System.out.println("====================================");
        System.out.println(inventoryExists);
        System.out.println("+=======================================");
        if (inventoryExists != null) {
            inventory.setOpeningStock(inventoryExists.getTotalStock());
            inventory.setTotalStock(inventoryExists.getTotalStock() + inventory.getCurrentPurchases());
            inventory.setClosingStock(inventory.getTotalStock());
        } else {
            inventory.setOpeningStock(0);
            inventory.setTotalStock(inventory.getCurrentPurchases());
            inventory.setClosingStock(inventory.getTotalStock());
        }
        Inventory inventory1 = inventoryService.insertInventory(inventory);
        if (inventory1 != null) {
            redirectAttributes.addFlashAttribute("msg", "Inventory added.");
            return "redirect:add-inventory";
        } else {
            redirectAttributes.addFlashAttribute("error", "Inventory not added.");
            return "redirect:add-inventory";
        }
    }

    //  ISSUE INVENTORY
    @GetMapping("/issue-inventory")
    public String goToIssueInventory(Model model) {
        List<Item> items = itemService.findAllItems();
        List<Supplier> suppliers = supplierService.findAllSuppliers();
        System.out.println(items);
        System.out.println(suppliers);
        model.addAttribute("items", items);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("inventory", new Inventory());
        return "issueInventory";
    }

    //  PURCHASE INVENTORY
    @PostMapping("/issueInventory")
    public String issuedInventory(@ModelAttribute("inventory") Inventory inventory, RedirectAttributes redirectAttributes) {
//        System.out.println(inventory);
        Inventory inventoryExists = inventoryService.findInventoryByItemId(inventory.getItem().getItemId());
        System.out.println("====================================");
        System.out.println(inventoryExists);
        System.out.println("+=======================================");
        if (inventoryExists != null && inventory.getIssuedStock() < inventoryExists.getTotalStock()) {
            inventory.setOpeningStock(inventoryExists.getTotalStock());
            inventory.setTotalStock(inventoryExists.getTotalStock() - inventory.getIssuedStock());
            inventory.setClosingStock(inventory.getTotalStock());
        } else {
            redirectAttributes.addFlashAttribute("error", "Item does not exists.");
            return "redirect:issue-inventory";
        }
        Inventory inventory1 = inventoryService.insertInventory(inventory);
        if (inventory1 != null) {
            redirectAttributes.addFlashAttribute("msg", "stock issued successfully.");
            return "redirect:issue-inventory";
        } else {
            redirectAttributes.addFlashAttribute("error", "stock issued failed.");
            return "redirect:issue-inventory";
        }
    }


//    =======================================LOGIN & REGISTER END-POINTS====================================================
//    TO SHOW MEASUREMENT UNITS
    @GetMapping("/unit")
    public String showAllUnits(Model model){
        List<MeasurementUnit> units=measurementUnitService.allUnits();
        if(units.isEmpty()){
            model.addAttribute("ifNotUnits","Unit list is empty, Add first.");
        }else {
        model.addAttribute("units",units);
        }
        return "units";
    }

//    TO GOTO ADD MEASUREMENT UNITS
    @GetMapping("/add-unit")
    public String goToAddMeasurementUnit(Model model){
        model.addAttribute("measurementUnit", new MeasurementUnit());
        return "addUnits";
    }
//    TO INSERT UNIT DB
    @PostMapping("/addUnit")
    public String insertMeasurementUnit(@ModelAttribute("measurementUnit") MeasurementUnit unit,RedirectAttributes redirectAttributes){
        System.out.println("Unit added : "+unit);
        MeasurementUnit measurementUnit = measurementUnitService.addUnit(unit);
        if(measurementUnit!=null){
            redirectAttributes.addFlashAttribute("msg","Unit Added.");
        }else {
            redirectAttributes.addFlashAttribute("error","Unit not added.");
        }
        return "redirect:add-unit";
    }

//    =======================================LOGIN & REGISTER END-POINTS====================================================

    //    TO GOTO LOGIN PAGE
    @GetMapping("/login")
    public String goToLogin() {
        return "login";
    }

    //    TO GOTO REGISTER PAGE
    @GetMapping("/register")
    public String goToRegister() {
        return "register";
    }

}
