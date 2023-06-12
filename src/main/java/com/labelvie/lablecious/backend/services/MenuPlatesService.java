package com.labelvie.lablecious.backend.services;

import com.labelvie.lablecious.backend.models.entity.MenuPlates;
import java.util.List;
public interface MenuPlatesService {

    List<MenuPlates> findAll();
    MenuPlates findById(Long id);

    MenuPlates save(MenuPlates menuPlates);
    void delete(Long id);
    MenuPlates update(Long id, MenuPlates menuPlates);
    MenuPlates findOrFail(Long id);


}
