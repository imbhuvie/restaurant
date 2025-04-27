package com.restaurant.Restaurant.controller;

import com.restaurant.Restaurant.entity.*;
import com.restaurant.Restaurant.service.InventoryService;
import com.restaurant.Restaurant.service.ItemService;
import com.restaurant.Restaurant.service.MeasurementUnitService;
import com.restaurant.Restaurant.service.SupplierService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/home")
    public String goToIndex(HttpSession session, RedirectAttributes redirect) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            return "home";
        } else {
            redirect.addFlashAttribute("error", "Login please");
            return "redirect:login";
        }
    }

    //    =======================================SUPPLIER END-POINTS====================================================
//    SHOW ALL SUPPLIERS
    @GetMapping("/suppliers")
    public String findAllSuppliers(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            List<Supplier> suppliers = supplierService.findAllSuppliers();
            if (suppliers.isEmpty())
                model.addAttribute("ifNotSupplier", "No suppliers Found.");
            else
                model.addAttribute("suppliers", suppliers);
            return "suppliers";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    //  TO GOTO ADD SUPPLIER PAGE
    @GetMapping("/add-supplier")
    public String goToAddSuppliers(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            model.addAttribute("supplier", new Supplier());
            return "addSupplier";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    //    ADD SUPPLIER
    @PostMapping("/addSupplier")
    public String insertSupplier(@ModelAttribute("supplier") Supplier supplier, RedirectAttributes redirectAttributes, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            Supplier supplier1 = supplierService.insertSupplier(supplier);
            if (supplier1 != null) {
                redirectAttributes.addFlashAttribute("msg", "Supplier added");
                return "redirect:add-supplier";
            } else {
                redirectAttributes.addFlashAttribute("error", "Supplier not added");
                return "redirect:add-supplier";
            }
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }


//    =======================================ITEMS END-POINTS====================================================
//    TO SHOW ALL ITEMS

    @GetMapping("/items")
    public String getAllItems(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            List<Item> items = itemService.findAllItems();
            if (items.isEmpty())
                model.addAttribute("ifNotItems", "No Items Found.");
            else
                model.addAttribute("items", items);
            return "items";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    //    TO GOTO ADD ITEMS PAGE
    @GetMapping("/add-item")
    public String goToAddItems(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            Item item = new Item();
            model.addAttribute("item", item);
            model.addAttribute("units", measurementUnitService.allUnits());
            return "addItems";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }
//    ADD ITEMS

    @PostMapping("/addItem")
    public String insertItem(@ModelAttribute("item") Item item, RedirectAttributes redirectAttributes, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            System.out.println(item);
            Item item1 = itemService.insertItem(item);
            if (item1 != null) {
                redirectAttributes.addFlashAttribute("msg", "Item added");
                return "redirect:add-item";
            } else {
                redirectAttributes.addFlashAttribute("error", "Item not added");
                return "redirect:add-item";
            }
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }


//    =======================================INVENTORY END-POINTS====================================================
//  TO SHOW ALL INVENTORIES

    @GetMapping("/inventories")
    public String findAllInventories(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            List<Inventory> inventories = inventoryService.findAllInventory();
            System.out.println(inventories);
            if (inventories.isEmpty())
                model.addAttribute("ifNotInventories", "No inventories Found.");
            else
                model.addAttribute("inventories", inventories);
            return "inventories";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    //    TO GOTO ADD INVENTORY PAGE
    @GetMapping("/add-inventory")
    public String goToAddInventory(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            List<Item> items = itemService.findAllItems();
            List<Supplier> suppliers = supplierService.findAllSuppliers();
            System.out.println(items);
            System.out.println(suppliers);
            model.addAttribute("items", items);
            model.addAttribute("suppliers", suppliers);
            model.addAttribute("inventory", new Inventory());
            return "addInventory";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    //  PURCHASE INVENTORY
    @PostMapping("/addInventory")
    public String insertInventory(@ModelAttribute("inventory") Inventory inventory, RedirectAttributes redirectAttributes, HttpSession session) {
//        System.out.println(inventory);
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
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
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    //  ISSUE INVENTORY
    @GetMapping("/issue-inventory")
    public String goToIssueInventory(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            List<Item> items = itemService.findAllItems();
//        List<Supplier> suppliers = supplierService.findAllSuppliers();
            System.out.println(items);
//        System.out.println(suppliers);
            model.addAttribute("items", items);
//        model.addAttribute("suppliers", suppliers);
            model.addAttribute("inventory", new Inventory());
            return "issueInventory";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    @GetMapping("/get-available-stock")
    @ResponseBody  // This is IMPORTANT for AJAX to receive raw data
    public double getAvailableStock(@RequestParam("id") long id, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            return inventoryService.getStockById(id);
        } else return 0;
    }

    //  PURCHASE INVENTORY
    @PostMapping("/issueInventory")
    public String issuedInventory(@ModelAttribute("inventory") Inventory inventory, RedirectAttributes redirectAttributes, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            System.out.println(inventory.getIssuedStock());
            Inventory inventoryExists = inventoryService.findInventoryByItemId(inventory.getItem().getItemId());

            if (inventoryExists != null) {
                if (inventory.getIssuedStock() < inventoryExists.getTotalStock()) {
                    inventoryExists.setOpeningStock(inventoryExists.getTotalStock());
                    inventoryExists.setTotalStock(inventoryExists.getTotalStock() - inventory.getIssuedStock());
                    inventoryExists.setClosingStock((inventoryExists.getTotalStock()));
                    Inventory inventory1 = inventoryService.insertInventory(inventoryExists);
                    redirectAttributes.addFlashAttribute("msg", "stock issued successfully.");
                    return "redirect:issue-inventory";
                } else {
                    redirectAttributes.addFlashAttribute("error", "Stock is not sufficiant.");
                    return "redirect:issue-inventory";
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Item does not exists.Add first please.");
                return "redirect:issue-inventory";
            }
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }

    }


    //    =======================================LOGIN & REGISTER END-POINTS====================================================
//    TO SHOW MEASUREMENT UNITS
    @GetMapping("/units")
    public String showAllUnits(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            List<MeasurementUnit> units = measurementUnitService.allUnits();
            if (units.isEmpty()) {
                model.addAttribute("ifNotUnits", "Unit list is empty, Add first.");
            } else {
                model.addAttribute("units", units);
            }
            return "units";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    //    TO GOTO ADD MEASUREMENT UNITS
    @GetMapping("/add-unit")
    public String goToAddMeasurementUnit(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            model.addAttribute("measurementUnit", new MeasurementUnit());
            return "addUnits";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    //    TO INSERT UNIT DB
    @PostMapping("/addUnit")
    public String insertMeasurementUnit(@ModelAttribute("measurementUnit") MeasurementUnit unit, RedirectAttributes redirectAttributes, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            MeasurementUnit measurementUnit = measurementUnitService.addUnit(unit);
            if (measurementUnit != null) {
                redirectAttributes.addFlashAttribute("msg", "Unit Added.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Unit not added.");
            }
            return "redirect:add-unit";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }
}




