package com.example.TPI_POO_NOTIFICACIONES.Controller;

import com.example.TPI_POO_NOTIFICACIONES.Entity.Plantilla;
import com.example.TPI_POO_NOTIFICACIONES.Request.PlantillaRequest;
import com.example.TPI_POO_NOTIFICACIONES.Service.PlantillaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantillas")
public class PlantillaController {

    private final PlantillaService plantillaService;

    public PlantillaController(PlantillaService plantillaService) {
        this.plantillaService = plantillaService;
    }

    // 🔹 Crear nueva plantilla
    @PostMapping
    public ResponseEntity<Plantilla> crear(@RequestBody PlantillaRequest request) {
        return ResponseEntity.ok(plantillaService.crearPlantilla(request));
    }

    // 🔹 Obtener todas las plantillas
    @GetMapping
    public ResponseEntity<List<Plantilla>> obtenerTodas() {
        return ResponseEntity.ok(plantillaService.obtenerTodas());
    }

    // 🔹 Obtener plantilla por id
    @GetMapping("/{id}")
    public ResponseEntity<Plantilla> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(plantillaService.obtenerPorId(id));
    }

    // 🔹 Actualizar parcialmente (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Plantilla> actualizarParcial(
            @PathVariable Long id,
            @RequestBody PlantillaRequest request) {
        return ResponseEntity.ok(plantillaService.actualizarParcial(id, request));
    }

    // 🔹 Eliminar plantilla por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        plantillaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}