package com.labelvie.lablecious.backend.models.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MenuPlateForm{
    private  long id;
    private  int quantity;
}
