package com.priscila.catsapi.service.service.impl;

import com.priscila.catsapi.dto.RacaDto;
import com.priscila.catsapi.model.Raca;
import com.priscila.catsapi.model.Imagem;
import com.priscila.catsapi.repository.RacaRepository;
import com.priscila.catsapi.repository.ImagemRepository;
import com.priscila.catsapi.service.RacaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RacaServiceImpl implements RacaService  {

    private final RacaRepository racaRepository;
    private final ImagemRepository imagemRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_RACAS = "https://api.thecatapi.com/v1/breeds";
    private static final String API_IMAGENS = "https://api.thecatapi.com/v1/images/search?limit=3&breed_ids={id}";

    @Override
    public void importarRacas() {
        log.info("Buscando raças na API TheCatAPI...");
        RacaDto[] racas = restTemplate.getForObject(API_RACAS, RacaDto[].class);

        if (racas != null) {
            Arrays.stream(racas).forEach(dto -> {
                Raca raca = Raca.builder()
                        .id(dto.getId())
                        .nome(dto.getName())
                        .descricao(dto.getDescription())
                        .temperamento(dto.getTemperament())
                        .origem(dto.getOrigin())
                        .build();

                raca = racaRepository.save(raca); // salva e pega referência com ID

                buscarImagensPorRaca(raca); // busca e salva 3 imagens
            });
        }
    }

    private void buscarImagensPorRaca(Raca raca) {
        String url = API_IMAGENS.replace("{id}", raca.getId());

        try {
            List<Object> imagens = Arrays.asList(restTemplate.getForObject(url, Object[].class));

            imagens.forEach(imagemObj -> {
                String json = imagemObj.toString(); // gambiarrazinha só p/ exemplo rápido
                String urlImagem = json.split("url=")[1].split(",")[0].replace("}", "");

                Imagem imagem = Imagem.builder()
                        .url(urlImagem)
                        .raca(raca)
                        .categoria(null)
                        .build();

                imagemRepository.save(imagem);
            });

        } catch (Exception e) {
            log.warn("Erro ao buscar imagens para raça: {}", raca.getNome(), e);
        }
    }

}
