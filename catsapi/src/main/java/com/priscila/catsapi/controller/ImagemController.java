package com.priscila.catsapi.controller;

import com.priscila.catsapi.service.ImagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagens")
@RequiredArgsConstructor
public class ImagemController {
    private final ImagemService imagemService;

    @PostMapping("/importar/chapeu")
    public ResponseEntity<Void> importarChapeu() {
        imagemService.importarImagensComCategoria("chapeu", 1);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/importar/oculos")
    public ResponseEntity<Void> importarOculos() {
        imagemService.importarImagensComCategoria("oculos", 4);
        return ResponseEntity.ok().build();
    }
}

