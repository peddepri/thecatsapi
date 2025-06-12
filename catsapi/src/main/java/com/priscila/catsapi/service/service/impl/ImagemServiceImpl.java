package com.priscila.catsapi.service.service.impl;

import com.priscila.catsapi.model.Imagem;
import com.priscila.catsapi.repository.ImagemRepository;
import com.priscila.catsapi.service.ImagemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImagemServiceImpl implements ImagemService {

    private final ImagemRepository imagemRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void importarImagensComCategoria(String categoria, int categoryId) {
        String url = "https://api.thecatapi.com/v1/images/search?limit=3&category_ids=" + categoryId;

        List<Map<String, Object>> imagens = Arrays.asList(restTemplate.getForObject(url, Map[].class));

        imagens.forEach(imagemMap -> {
            String urlImagem = (String) imagemMap.get("url");

            Imagem imagem = Imagem.builder()
                    .url(urlImagem)
                    .categoria(categoria)
                    .raca(null)
                    .build();

            imagemRepository.save(imagem);
        });

        log.info("Imagens da categoria {} importadas com sucesso!", categoria);
    }
}
