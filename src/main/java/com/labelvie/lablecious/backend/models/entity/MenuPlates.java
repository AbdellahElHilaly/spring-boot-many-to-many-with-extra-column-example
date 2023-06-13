package com.labelvie.lablecious.backend.models.entity;

import com.labelvie.lablecious.backend.models.dto.request.MenuRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_plates")
public class MenuPlates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "plate_id", nullable = false, referencedColumnName = "id")
    private Plate plate;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false, referencedColumnName = "id")
    private Menu menu;

}
