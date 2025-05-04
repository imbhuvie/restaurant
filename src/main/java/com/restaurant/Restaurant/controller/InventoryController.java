package com.restaurant.Restaurant.controller;

import com.restaurant.Restaurant.entity.*;
import com.restaurant.Restaurant.service.InventoryService;
import com.restaurant.Restaurant.service.ItemService;
import com.restaurant.Restaurant.service.MeasurementUnitService;
import com.restaurant.Restaurant.service.SupplierService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public String goToAddSuppliers(Model model, HttpSession session,@RequestParam(value ="id", required = false) String id) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {

            Supplier supplier = new Supplier();
            if(id!=null){
                supplier = supplierService.findSupplierById(id);
            }
            model.addAttribute("supplier", supplier);
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
            String id = supplier.getSupplierId();
            Supplier supplier1 = supplierService.insertSupplier(supplier);
            if (supplier1 != null) {
                if(id!=null){
                    redirectAttributes.addFlashAttribute("msg", "Supplier updated.");
                    return "redirect:/suppliers";
                }else{
                redirectAttributes.addFlashAttribute("msg", "Supplier added");
                return "redirect:add-supplier";
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Supplier not added");
                return "redirect:add-supplier";
            }
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    // Delete Supplier by Id
    @GetMapping("delete-supplier")
    public String deleteSupplierById(@RequestParam("id") String id, HttpSession session,RedirectAttributes redirect) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            System.out.println(id);
            if(supplierService.deleteSupplierById(id)){
                redirect.addFlashAttribute("msg","Supplier deleted.");
                return "redirect:/suppliers";
            }else {
                redirect.addFlashAttribute("error","Supplier not deleted.");
                return "redirect:/suppliers";
            }
        } else {
            redirect.addFlashAttribute("error","Login please");
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
    public String goToAddItems(Model model, HttpSession session,@RequestParam(value ="id", required = false) String id) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            Item item = new Item();
//            System.out.println(id);
            if(id!=null){
                item= itemService.getItemById(id);
//                System.out.println(item);
            }else{
            }
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
            String id=item.getItemId();
            Item item1 = itemService.insertItem(item);
            if (item1 != null) {
                if(id!=null){
                    System.out.println(item);
                    redirectAttributes.addFlashAttribute("msg", "Item updated");
                    return "redirect:/items";
                }else {
                    redirectAttributes.addFlashAttribute("msg", "Item added");
                    return "redirect:add-item";
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Item not added");
                return "redirect:add-item";
            }
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    // Delete Item by Id
    @GetMapping("delete-item")
    public String deleteItemById(@RequestParam("id") String id, HttpSession session,RedirectAttributes redirect) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            System.out.println(id);
            if(itemService.deleteItemById(id)){
                redirect.addFlashAttribute("msg","Item deleted.");
                return "redirect:/items";
            }else {
                redirect.addFlashAttribute("error","Item not deleted.");
                return "redirect:/items";
            }
        } else {
            redirect.addFlashAttribute("error","Login please");
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
//            System.out.println(items);
            model.addAttribute("items", items);
            model.addAttribute("inventory", new Inventory());
            return "issueInventory";
        } else {
//            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }

    @GetMapping("/get-available-stock")
    @ResponseBody  // This is IMPORTANT for AJAX to receive raw data
    public double getAvailableStock(@RequestParam("id") String id, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            System.out.println("===================="+id+"==================");
            return inventoryService.getStockById(id);
        } else return 0;
    }

    //  ISSUE INVENTORY
    @PostMapping("/issueInventory")
    public String issuedInventory(@ModelAttribute("inventory") Inventory inventory, RedirectAttributes redirectAttributes, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            System.out.println("STOCK TOBE ISSUE : "+inventory.getIssuedStock());
            Inventory inventoryExists = inventoryService.findInventoryByItemId(inventory.getItem().getItemId());
            System.out.println(inventoryExists);
            if (inventoryExists != null) {
                if (inventory.getIssuedStock() < inventoryExists.getTotalStock()) {
                    inventory.setOpeningStock(inventoryExists.getTotalStock());
                    inventory.setTotalStock(inventoryExists.getTotalStock() - inventory.getIssuedStock());
                    inventory.setClosingStock((inventory.getTotalStock()));
                    Inventory inventory1 = inventoryService.insertInventory(inventory);
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
    public String goToAddMeasurementUnit(Model model, HttpSession session,@RequestParam(value ="id", required = false) String id) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            MeasurementUnit unit = new MeasurementUnit();
            if(id!=null){
                unit=measurementUnitService.findUnitById(id);
            }
            model.addAttribute("measurementUnit", unit);
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
            String id=unit.getId();
            System.out.println(unit);
            String message = measurementUnitService.addUnit(unit);
            if (message.equals("added")) {
                if(id!=null){
                redirectAttributes.addFlashAttribute("msg", "Unit updated.");
                    return "redirect:/units";
                }else{
                redirectAttributes.addFlashAttribute("msg", "Unit Added.");
                }
            } else if(message.equals("exists")){
                redirectAttributes.addFlashAttribute("error", "Unit Exists.");
            }else{
                redirectAttributes.addFlashAttribute("error", "Unit not added.");
            }
            return "redirect:add-unit";
        } else {
            redirectAttributes.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }
    // Delete Unit by Id
    @GetMapping("delete-unit")
    public String deleteUnitById(@RequestParam("id") String id, HttpSession session,RedirectAttributes redirect) {
        Employee employee = (Employee) session.getAttribute("currentUser");
        if (employee != null) {
            System.out.println(id);
            if(measurementUnitService.deleteUnitById(id)){
                redirect.addFlashAttribute("msg","Unit deleted.");
                return "redirect:/units";
            }else {
                redirect.addFlashAttribute("error","Unit not deleted.");
                return "redirect:/units";
            }
        } else {
            redirect.addFlashAttribute("error","Login please");
            return "redirect:login";
        }
    }
}




