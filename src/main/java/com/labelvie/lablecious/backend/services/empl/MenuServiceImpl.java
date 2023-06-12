package com.labelvie.lablecious.backend.services.empl;

import com.labelvie.lablecious.backend.exceptions.handler.ResourceNotFoundException;
import com.labelvie.lablecious.backend.models.dto.request.MenuRequest;
import com.labelvie.lablecious.backend.models.dto.response.MenuResponse;
import com.labelvie.lablecious.backend.models.entity.Menu;
import com.labelvie.lablecious.backend.models.entity.MenuPlates;
import com.labelvie.lablecious.backend.models.entity.Plate;
import com.labelvie.lablecious.backend.repository.MenuRepository;
import com.labelvie.lablecious.backend.services.MenuService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private  final MenuRepository menuRepository;
    private final PlateServiceImpl plateService;
    private final MenuPlatesServiceImpl menuPlatesService;
    private final List<MenuPlates>  menuPlatesList = new ArrayList<>();


    @Override
    public List<MenuResponse> getMenus() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuResponse> menuResponses = new ArrayList<>();

        for (Menu menu : menus) {
            List<MenuPlates> menuPlatesList = menuPlatesService.findByMenu(menu);
            List<MenuResponse.Plates> platesList = MenuResponse.fromMenuPlatesListToPlatesList(menuPlatesList);
            MenuResponse menuResponse = MenuResponse.fromMenu(menu, platesList);
            menuResponses.add(menuResponse);
        }
        return menuResponses;
    }

    @Override
    public MenuResponse getMenuById(long id) {
        Menu menu = findOrFail(id);
        List<MenuPlates> menuPlatesList = menuPlatesService.findByMenu(menu);
        List<MenuResponse.Plates> platesList = MenuResponse.fromMenuPlatesListToPlatesList(menuPlatesList);
        return MenuResponse.fromMenu(menu, platesList);
    }


    @Override
    public MenuResponse saveMenu(MenuRequest menuRequest) {

        Menu menu = menuRepository.save(menuRequest.toMenu());
        List<MenuPlates> menuPlatesList = attachMenuPlates(menu, menuRequest);
        return MenuResponse.fromMenuPlatesList(menuPlatesList);

    }

    @Override
    public MenuResponse updateMenu(long id, MenuRequest menu) {
        return null;
    }

    @Override
    @Transactional
    public void deleteMenu(long id) {
        Menu menu = findOrFail(id);
        menuPlatesService.deleteByMenu(menu);
        menuRepository.delete(menu);
    }


    @Override
    public Menu findOrFail(long id) {
        return menuRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("The menu with id " + id + " does not exist"));
    }

    private MenuPlates attachMenuPlate(Menu menu, Plate plate, int quantity) {
        MenuPlates menuPlates = MenuPlates.toMenuPlates(menu, plate, quantity);
        return menuPlatesService.save(menuPlates);
    }

    private List<MenuPlates> attachMenuPlates(Menu menu, MenuRequest menuRequest) {

        for (MenuRequest.MenuPlatesRequest platesRequest : menuRequest.getPlates()) {
            Plate plate = plateService.findOrFail(platesRequest.getPlateId());
            MenuPlates menuPlate = attachMenuPlate(menu, plate, platesRequest.getQuantity());
            menuPlatesList.add(menuPlate);
        }
        return menuPlatesList;
    }

}

