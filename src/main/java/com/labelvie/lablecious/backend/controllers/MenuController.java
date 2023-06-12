package com.labelvie.lablecious.backend.controllers;


import com.labelvie.lablecious.backend.models.dto.request.MenuRequest;
import com.labelvie.lablecious.backend.models.dto.response.MenuResponse;
import com.labelvie.lablecious.backend.services.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/menus")
@AllArgsConstructor
public class MenuController {

    private  final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuResponse>> getMenus() {
        List<MenuResponse> menus = menuService.getMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable long id) {
        MenuResponse menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest menu) {
        return ResponseEntity.ok(menuService.saveMenu(menu));
    }


}
