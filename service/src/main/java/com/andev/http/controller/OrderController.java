package com.andev.http.controller;

import com.andev.dto.OrderCreateEditDto;
import com.andev.entity.enums.Payment;
import com.andev.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "order/orders";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return orderService.findByID(id)
                .map(order -> {
                    model.addAttribute("order", order);
                    model.addAttribute("payments", Payment.values());
                    return "order/order";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute OrderCreateEditDto order) {
        model.addAttribute("order", order);
        model.addAttribute("payments", Payment.values());
        return "order/registration";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated OrderCreateEditDto order,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("order", order);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/orders/registration";
        }
        return "redirect:/orders/" + orderService.create(order).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id, @ModelAttribute @Validated OrderCreateEditDto orderDto) {
        return orderService.update(id, orderDto)
                .map(it -> "redirect:/orders/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String update(@PathVariable Integer id) {
        if (!orderService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/orders";
    }
}

