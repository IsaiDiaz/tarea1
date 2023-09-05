package com.example.tarea1.bl;

import com.example.tarea1.dao.entities.Categoria;
import com.example.tarea1.dao.entities.Imagen;
import com.example.tarea1.dao.repositories.CategoriaRepository;
import com.example.tarea1.dao.repositories.ImagenRepository;
import com.example.tarea1.dto.ImagenDTO;
import com.example.tarea1.utils.ApiException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ImagenBL {
    private final ImagenRepository imagenRepository;
    private final CategoriaRepository categoriaRepository;
    private static final Logger logger = LoggerFactory.getLogger(ImagenBL.class);

    @Value ("${api.base-url}")
    private String url;
    @Autowired
    public ImagenBL(ImagenRepository imagenRepository, CategoriaRepository categoriaRepository) {
        this.imagenRepository = imagenRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public void saveImagen(ImagenDTO imagenDTO) {
        logger.info("Guardando imagen");
        Imagen imagen = new Imagen();
        imagen.setUrl(imagenDTO.getUrl());
        imagen.setMessage(imagenDTO.getMessage());
        imagenRepository.saveAndFlush(imagen);
        logger.info("Imagen guardada");
    }

    public void deleteImagen(Long imagenId) {
        logger.info("Eliminando imagen con ID: " + imagenId);

        Imagen imagen = imagenRepository.findById(imagenId).orElse(null);
        if (imagen != null) {
            imagenRepository.delete(imagen);
            logger.info("Imagen eliminada");
        } else {
            logger.error("No se encontró la imagen con el ID proporcionado");
            throw new ApiException(HttpStatus.NOT_FOUND, "No se encontró la imagen con el ID proporcionado");
        }
    }

    public ImagenDTO updateImagen(Long imagenId, ImagenDTO updatedImagenDTO) {
        logger.info("Actualizando imagen con ID: " + imagenId);

        Imagen imagen = imagenRepository.findById(imagenId).orElse(null);
        if (imagen != null) {
            imagen.setUrl(updatedImagenDTO.getUrl());
            imagen.setMessage(updatedImagenDTO.getMessage());
            imagenRepository.save(imagen);

            logger.info("Imagen actualizada");
            return updatedImagenDTO;
        } else {
            logger.error("No se encontró la imagen con el ID proporcionado");
            throw new ApiException(HttpStatus.NOT_FOUND, "No se encontró la imagen con el ID proporcionado");
        }
    }

    public ImagenDTO getImagenById(Long imagenId) {
        logger.info("Obteniendo imagen con ID: " + imagenId);

        Imagen imagen = imagenRepository.findById(imagenId).orElse(null);
        if (imagen != null) {
            return new ImagenDTO(imagen.getUrl(), imagen.getMessage());
        } else {
            logger.error("No se encontró la imagen con el ID proporcionado");
            throw new ApiException(HttpStatus.NOT_FOUND, "No se encontró la imagen con el ID proporcionado");
        }
    }

    public List<ImagenDTO> getAllImagenes() {
        logger.info("Obteniendo todas las imágenes");

        List<Imagen> imagenes = imagenRepository.findAll();
        List<ImagenDTO> imagenDTOs = new ArrayList<>();

        for (Imagen imagen : imagenes) {
            imagenDTOs.add(new ImagenDTO(imagen.getId(), imagen.getUrl(), imagen.getMessage()));
        }

        return imagenDTOs;
    }


    public ImagenDTO getImagen() {
        logger.info("Obteninedo imagen");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        logger.info("Imagen obtenida");
        logger.info(response);
        ObjectMapper objectMapper = new ObjectMapper();
        ImagenDTO imagenDTO = null;
        try {
            imagenDTO = objectMapper.readValue(response, ImagenDTO.class);
            saveImagen(imagenDTO);
            return imagenDTO;
        } catch (Exception e) {
            logger.error("Error al obtener imagen");
            logger.error(e.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener imagen");
        }
    }
}

