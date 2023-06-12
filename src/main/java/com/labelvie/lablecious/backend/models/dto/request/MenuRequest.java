package com.labelvie.lablecious.backend.models.dto.request;

import com.labelvie.lablecious.backend.models.entity.Menu;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuRequest {
    @NotBlank(message = "Date is required")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$")
    private Date date;

    @NotBlank(message = "Plates is required")
    private List<MenuPlatesRequest> plates;

    @Data
    public static class MenuPlatesRequest {
        @NotBlank(message = "PlateId is required")
        private Long plateId;

        @NotBlank(message = "Quantity is required")
        @Min(value = 1, message = "Quantity must be greater than 0")
        private int quantity;
    }

    public Menu toMenu() {
        return Menu.builder()
                .date(this.getDate())
                .build();
    }
}


