package com.labelvie.lablecious.backend.services;

import java.util.Date;
import java.util.List;

import com.labelvie.lablecious.backend.models.entity.MenuPlates;
import com.labelvie.lablecious.backend.transfer.request.MenuRequest;
import com.labelvie.lablecious.backend.transfer.response.MenuResponse;
import com.labelvie.lablecious.backend.models.entity.Menu;

public interface MenuService {

    List<MenuResponse> getMenus();
    MenuResponse getMenuById(long id);
    MenuResponse saveMenu(MenuRequest menu);
    MenuResponse updateMenu(long id, MenuRequest menu);
    void deleteMenu(long id);
    Menu findOrFail(long id);

    List<MenuResponse> getMenuByDate(Date date);
}
