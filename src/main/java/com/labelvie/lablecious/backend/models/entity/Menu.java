package com.labelvie.lablecious.backend.models.entity;

import com.labelvie.lablecious.backend.models.dto.response.MenuResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date date;


    public MenuResponse toResponse() {
        return MenuResponse.builder()
                .id(this.getId())
                .date(this.getDate())
                .build();
    }
}
