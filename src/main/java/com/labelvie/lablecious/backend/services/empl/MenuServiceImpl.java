package com.labelvie.lablecious.backend.services.empl;

import com.labelvie.lablecious.backend.exceptions.handler.ResourceNotFoundException;
import com.labelvie.lablecious.backend.models.dto.MenuDto;
import com.labelvie.lablecious.backend.models.entity.Menu;
import com.labelvie.lablecious.backend.models.entity.MenuPlate;
import com.labelvie.lablecious.backend.models.entity.Plate;
import com.labelvie.lablecious.backend.models.form.MenuPlateForm;
import com.labelvie.lablecious.backend.repository.MenuRepository;
import com.labelvie.lablecious.backend.services.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private  final MenuRepository menuRepository;
    private final PlateServiceImpl plateService;


    @Override
    public List<MenuDto> getMenus() {
        return MenuDto.fromMenus(menuRepository.findAll());
    }

    @Override
    public MenuDto getMenuById(long id) {
        return MenuDto.fromMenu(this.findOrFail(id));
    }

    @Override
    public MenuDto saveMenu(MenuDto menuDto) {
        Menu menu = menuDto.toMenu();
        menu = menuRepository.save(menu);
        menu = attachPlates(menu , menuDto.getPlates());

        return MenuDto.fromMenu(menu);
    }

    @Override
    public MenuDto updateMenu(long id, MenuDto menuDto) {
        return null;
    }

    @Override
    public void deleteMenu(long id) {

    }

    @Override
    public Menu attachPlate(Menu menu, MenuPlateForm plateForm) {
        Plate plate = plateService.findOrFail(plateForm.getId());
//        menu.getPlates().add(plate);
        return menu;
    }

    @Override
    public Menu attachPlates(Menu menu, List<MenuPlateForm> plateForms) {
        for (MenuPlateForm plateForm : plateForms) {
            menu = attachPlate(menu, plateForm);
        }
        return menu;
    }

    @Override
    public Menu findOrFail(long id) {
        return menuRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("The menu with id " + id + " does not exist"));
    }


}
