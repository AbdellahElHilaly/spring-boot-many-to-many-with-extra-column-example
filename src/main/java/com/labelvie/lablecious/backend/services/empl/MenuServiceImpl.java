package com.labelvie.lablecious.backend.services.empl;

import com.labelvie.lablecious.backend.exceptions.handler.ResourceNotFoundException;
import com.labelvie.lablecious.backend.models.dto.request.MenuRequest;
import com.labelvie.lablecious.backend.models.dto.response.MenuResponse;
import com.labelvie.lablecious.backend.models.entity.Menu;
import com.labelvie.lablecious.backend.models.entity.MenuPlates;
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
    private Menu tempMenu = new Menu();
    private final MenuRequest tempMenuRequest = new MenuRequest();
    private final MenuResponse tempMenuResponse = new MenuResponse();
    private final MenuPlates tempMenuPlates = new MenuPlates();

    @Override
    public List<MenuResponse> getMenus() {
        return MenuResponse.fromMenus(menuRepository.findAll());
    }

    @Override
    public MenuResponse getMenuById(long id) {
        return MenuResponse.fromMenu(this.findOrFail(id));
    }

    @Override
    public MenuResponse saveMenu(MenuRequest menuRequest) {


//        this.tempMenuPlates.setMenu(menuRepository.save(menuRequest.toMenu()));
//        this.tempMenuPlates.setPlate(plateService.findOrFail(menuRequest.getPlates().getPlateId()));
//        this.tempMenuPlates.setQuantity(menuRequest.getPlates().getQuantity());
//
//        this.menuPlatesService.save(this.tempMenuPlates);
//
//        return MenuResponse.fromMenu(this.tempMenuPlates.getMenu());

        return null;
    }

    @Override
    public MenuResponse updateMenu(long id, MenuRequest menu) {
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

