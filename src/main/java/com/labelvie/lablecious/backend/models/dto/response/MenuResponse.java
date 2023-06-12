package com.labelvie.lablecious.backend.models.dto.response;

import com.labelvie.lablecious.backend.models.entity.Menu;
import com.labelvie.lablecious.backend.models.entity.Plate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class MenuResponse {

    private long id;
    private Date date;
    private Plates plates;


    @Builder
    @Data
    @AllArgsConstructor
    public  static class Plates {
        private  Plate plate;
        private  int quantity;
    }


    public static MenuResponse fromMenu(Menu menu){
        return MenuResponse.builder()
                .id(menu.getId())
                .date(menu.getDate())
                .build();
    }

    public static List<MenuResponse> fromMenus(List<Menu> menus){
        return menus.stream()
                .map(MenuResponse::fromMenu)
                .collect(Collectors.toList());
    }

    public Menu toMenu(){
        return Menu.builder()
                .id(this.getId())
                .date(this.getDate())
                .build();
    }

}
