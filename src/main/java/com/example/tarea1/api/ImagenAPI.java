package com.example.tarea1.api;

import com.example.tarea1.bl.ImagenBL;
import com.example.tarea1.dto.ImagenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ImagenAPI {
    private final ImagenBL imagenBL;
    @Autowired
    public ImagenAPI(ImagenBL imagenBL) {
        this.imagenBL = imagenBL;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/imagen")
    public ImagenDTO getRandomImagen() {
        return imagenBL.getImagen();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/imagen/{id}")
    public ResponseEntity<String> deleteImagen(@PathVariable Long id) {
        imagenBL.deleteImagen(id);
        return ResponseEntity.ok("Imagen eliminada con éxito");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/imagen/{id}")
    public ResponseEntity<ImagenDTO> updateImagen(@PathVariable Long id, @RequestBody ImagenDTO updatedImagenDTO) {
        ImagenDTO updatedImagen = imagenBL.updateImagen(id, updatedImagenDTO);
        if (updatedImagen != null) {
            return ResponseEntity.ok(updatedImagen);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/imagen/{id}")
    public ResponseEntity<ImagenDTO> getImagenById(@PathVariable Long id) {
        ImagenDTO imagen = imagenBL.getImagenById(id);
        if (imagen != null) {
            return ResponseEntity.ok(imagen);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/imagenes")
    public ResponseEntity<List<ImagenDTO>> getAllImagenes() {
        List<ImagenDTO> imagenes = imagenBL.getAllImagenes();
        return ResponseEntity.ok(imagenes);
    }
}

