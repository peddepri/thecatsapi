package com.priscila.catsapi.controller;

import com.priscila.catsapi.model.Raca;
import com.priscila.catsapi.repository.RacaRepository;
import com.priscila.catsapi.service.RacaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/racas")
@RequiredArgsConstructor
public class RacaController {

    private final RacaService racaService;
    private final RacaRepository racaRepository;

    @PostMapping("/importar")
    public ResponseEntity<Void> importar() {
        racaService.importarRacas();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Raca>> listarTodas() {
        return ResponseEntity.ok(racaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Raca> buscarPorId(@PathVariable String id) {
        return racaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/temperamento/{valor}")
    public ResponseEntity<List<Raca>> buscarPorTemperamento(@PathVariable String valor) {
        return ResponseEntity.ok(racaRepository.findByTemperamentoContainingIgnoreCase(valor));
    }

    @GetMapping("/origem/{valor}")
    public ResponseEntity<List<Raca>> buscarPorOrigem(@PathVariable String valor) {
        return ResponseEntity.ok(racaRepository.findByOrigemContainingIgnoreCase(valor));
    }
}
