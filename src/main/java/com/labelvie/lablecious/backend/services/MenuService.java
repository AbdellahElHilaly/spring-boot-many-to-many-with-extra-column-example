package com.labelvie.lablecious.backend.services;

import java.util.List;

import com.labelvie.lablecious.backend.models.dto.request.MenuRequest;
import com.labelvie.lablecious.backend.models.dto.response.MenuResponse;
import com.labelvie.lablecious.backend.models.entity.Menu;


public interface MenuService {

    List<MenuResponse> getMenus();
    MenuResponse getMenuById(long id);
    MenuResponse saveMenu(MenuRequest menu);
    MenuResponse updateMenu(long id, MenuRequest menu);
    void deleteMenu(long id);
    Menu findOrFail(long id);

}
