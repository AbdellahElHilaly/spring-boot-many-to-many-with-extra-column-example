package com.labelvie.lablecious.backend.models.dto;

import com.labelvie.lablecious.backend.models.entity.Menu;
import com.labelvie.lablecious.backend.models.entity.MenuPlate;
import com.labelvie.lablecious.backend.models.entity.Plate;
import com.labelvie.lablecious.backend.models.form.MenuPlateForm;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@Builder
public class MenuDto {
    private long id;
    @NotBlank(message = "Date is required")
    private Date date;
    private List<MenuPlateForm> plates;
    private List<MenuPlateDto> platesDto;


    public  MenuDto(){
        this.date = new Date();
    }

    public  static MenuDto fromMenu(Menu menu){
        return MenuDto.builder()
                .id(menu.getId())
                .date(menu.getDate())
                .build();
    }


    public  static List<MenuDto> fromMenus(List<Menu> menus){
        return menus.stream()
                .map(MenuDto::fromMenu)
                .collect(Collectors.toList());
    }

    public Menu toMenu(){
        return Menu.builder()
                .id(this.getId())
                .date(this.getDate())
                .build();
    }




    @Data
    @AllArgsConstructor
    @Builder
    private static class MenuPlateDto{
        private  Plate plate;
        private  int quantity;

    }


}


