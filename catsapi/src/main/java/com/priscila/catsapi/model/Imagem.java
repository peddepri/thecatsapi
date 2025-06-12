package com.priscila.catsapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String categoria;

    @ManyToOne
    @JoinColumn(name = "raca_id")
    @JsonBackReference
    private Raca raca;
}
