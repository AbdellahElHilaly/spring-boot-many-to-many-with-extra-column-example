package com.labelvie.lablecious.backend.services.empl;

import com.labelvie.lablecious.backend.exceptions.handler.ResourceNotFoundException;
import com.labelvie.lablecious.backend.models.dto.CategoryDto;
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


    private List<MenuPlates>  menuPlatesList = new ArrayList<>();
    private Menu menu = new Menu();
    private List<Menu> menuList = new ArrayList<>();
    private List<MenuResponse.Plates> platesList = new ArrayList<>();
    private MenuPlates menuPlate = new MenuPlates();


    @Override
    public List<MenuResponse> getMenus() {
        menuList = menuRepository.findAll();
        return menuList.stream().map(menu -> {
            menuPlatesList = menuPlatesService.findByMenu(menu);
            platesList = MenuResponse.fromMenuPlatesListToPlatesList(menuPlatesList);
            return MenuResponse.fromMenu(menu, platesList);
        }).collect(Collectors.toList());
//        for (Menu menu : menuList) {
//             menuPlatesList = menuPlatesService.findByMenu(menu);
//             platesList = MenuResponse.fromMenuPlatesListToPlatesList(menuPlatesList);
//            menuResponsesList.add(MenuResponse.fromMenu(menu, platesList));
//        }
//        return menuResponsesList;

    }

    @Override
    public MenuResponse getMenuById(long id) {
        menu = findOrFail(id);
        menuPlatesList = menuPlatesService.findByMenu(menu);
        platesList = MenuResponse.fromMenuPlatesListToPlatesList(menuPlatesList);
        return MenuResponse.fromMenu(menu, platesList);
    }


    @Override
    public MenuResponse saveMenu(MenuRequest menuRequest) {
        menu = menuRepository.save(menuRequest.toMenu());
        return MenuResponse.fromMenuPlatesList(attachMenuPlatesList(menu, menuRequest));
    }

    @Override
    public MenuResponse updateMenu(long id, MenuRequest menuRequest) {

        menuRequest.setId(this.findOrFail(id).getId());
        MenuResponse.fromMenu(menuRepository.save(menuRequest.toMenu()));
        menuPlatesService.deleteByMenu(menuRequest.toMenu());
        return MenuResponse.fromMenuPlatesList(attachMenuPlatesList(menuRequest.toMenu(), menuRequest));

    }



    @Override
    @Transactional
    public void deleteMenu(long id) {
        menu = findOrFail(id);
        menuPlatesService.deleteByMenu(menu);
        menuRepository.delete(menu);
    }


    @Override
    public Menu findOrFail(long id) {
        return menuRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("The menu with id " + id + " does not exist"));
    }

    private MenuPlates attachMenuPlate(MenuPlates menuPlate) {
        return menuPlatesService.save(menuPlate);
    }

    private List<MenuPlates> attachMenuPlatesList(Menu menu, MenuRequest menuRequest) {

        return menuRequest.getPlates().stream()
                .map(platesRequest -> {

                    menuPlate.setMenu(menu);
                    menuPlate.setPlate(plateService.findOrFail(platesRequest.getPlateId()));
                    menuPlate.setQuantity(platesRequest.getQuantity());

                    return attachMenuPlate(menuPlate);

                })
                .collect(Collectors.toList());
//        menuPlate.setMenu(menu);
//        for (MenuRequest.MenuPlatesRequest platesRequest : menuRequest.getPlates()) {
//
//            menuPlate.setPlate(plateService.findOrFail(platesRequest.getPlateId()));
//            menuPlate.setQuantity(platesRequest.getQuantity());
//
//            menuPlate = attachMenuPlate(menuPlate);
//            menuPlatesList.add(menuPlate);
//        }
//        return menuPlatesList;
    }

    private MenuPlates attachMenuPlate2(MenuPlates menuPlates) {
        return menuPlatesService.save(menuPlates);
    }


}

