package com.labelvie.lablecious.backend.services;

import java.util.List;
import com.labelvie.lablecious.backend.models.dto.MenuDto;
import com.labelvie.lablecious.backend.models.entity.Menu;
import com.labelvie.lablecious.backend.models.form.MenuPlateForm;

public interface MenuService {

    List<MenuDto> getMenus();
    MenuDto getMenuById(long id);
    MenuDto saveMenu(MenuDto menuDto);
    MenuDto updateMenu(long id, MenuDto menuDto);
    void deleteMenu(long id);
    Menu attachPlate(Menu menu, MenuPlateForm plateForm);
    Menu attachPlates(Menu menu, List<MenuPlateForm> plateForms);
    Menu findOrFail(long id);

}
