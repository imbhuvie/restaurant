package com.restaurant.Restaurant.controller;

import com.restaurant.Restaurant.entity.Employee;
import com.restaurant.Restaurant.service.implimentation.EmployeeServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserAuthController {
    @Autowired
    EmployeeServiceImpl employeeService;
    //    TO goto Register page
    @GetMapping("/register")
    public String gotoRegistrationForm(Model model, HttpSession session) {
        System.out.println(session.getAttribute("currentUser"));
        model.addAttribute("employee", session.getAttribute("currentUser"));
        return "register";
    }

    //  To save employee details to database
    @PostMapping("/register")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, Model model, RedirectAttributes redirect, HttpSession session) {
        boolean isEmployeeAlreadyExists = employeeService.getEmployeeByUserName(employee.getUserName()) != null;
        if (isEmployeeAlreadyExists) {
            redirect.addFlashAttribute("error", "Username already exists.");
            return "redirect:register";
        }
        isEmployeeAlreadyExists = employeeService.getEmployeeByContact(employee.getContact())!=null;
        if (isEmployeeAlreadyExists) {
            redirect.addFlashAttribute("error", "Contact already exists.");
            return "redirect:register";
        }
        Employee employee1 = employeeService.insertEmployee(employee);
        if (employee1!=null) {
            session.setAttribute("currentUser",employee);
            redirect.addFlashAttribute("success", "Employee Registered successfully.");
            return "redirect:/home";
        } else {
            redirect.addFlashAttribute("error", "Employee Not Registered.");
            return "redirect:register";
        }
    }

    //=================================LOGIN ENDPOINTS=========================
    //    TO goto Login page
    @GetMapping("/login")
    public String gotoLoginForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "/login";
    }

    @PostMapping("/login")
    public String verifyLogin(@ModelAttribute("employee") Employee employee, HttpSession session, RedirectAttributes redirect) {
        Employee validEmployee = employeeService.getEmployeeByUserName(employee.getUserName());
        if (validEmployee == null) {
            redirect.addFlashAttribute("error", "Invalid username. Try again.");
            return "redirect:login";
        } else {
            if (validEmployee.getPassword().equals(employee.getPassword())) {
                session.setAttribute("currentUser", validEmployee);
                redirect.addFlashAttribute("success", "Login successfully.");
                return "redirect:/home";
            } else {
                redirect.addFlashAttribute("error", "Invalid password. Try again.");
                return "redirect:login";
            }
        }


    }

    //  To Logout
    @GetMapping("/logout")
    public String logoutEmployee(HttpSession session) {
//        Employee employee = (Employee) session.getAttribute("currentUser");
//        if (employee != null) {
//            session.invalidate();
//            return "redirect:login";
//        } else return "redirect:login";
        session.invalidate();
            return "redirect:login";
    }

}
