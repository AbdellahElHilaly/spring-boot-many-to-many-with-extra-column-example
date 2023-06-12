package com.labelvie.lablecious.backend.services.empl;

import com.labelvie.lablecious.backend.exceptions.handler.ResourceNotFoundException;
import com.labelvie.lablecious.backend.models.dto.MenuDto;
import com.labelvie.lablecious.backend.models.entity.Menu;
import com.labelvie.lablecious.backend.models.entity.Plate;
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
    private final MenuPlatesServiceImpl menuPlatesService;

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

        menuDto.getMenuPlates().setMenu(menuRepository.save(menuDto.toMenu()));
        menuDto.getMenuPlates().setPlate(plateService.findOrFail(menuDto.getMenuPlatesRequest().getPlateId()));
        menuDto.getMenuPlates().setQuantity(menuDto.getMenuPlatesRequest().getQuantity());

        return menuDto;

    }

    @Override
    public MenuDto updateMenu(long id, MenuDto menuDto) {
        return null;
    }

    @Override
    public void deleteMenu(long id) {

    }

    @Override
    public Menu findOrFail(long id) {
        return menuRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("The menu with id " + id + " does not exist"));
    }

}

