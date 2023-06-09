package com.labelvie.lablecious.backend.controllers;


import com.labelvie.lablecious.backend.models.dto.MenuDto;
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
    public ResponseEntity<List<MenuDto>> getMenus() {
        List<MenuDto> menus = menuService.getMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable long id) {
        MenuDto menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        MenuDto createdMenu = menuService.saveMenu(menuDto);
        return ResponseEntity.ok(createdMenu);
    }


}
