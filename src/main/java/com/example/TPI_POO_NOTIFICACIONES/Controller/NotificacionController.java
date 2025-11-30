package com.example.TPI_POO_NOTIFICACIONES.Controller;

import com.example.TPI_POO_NOTIFICACIONES.Entity.Notificacion;
import com.example.TPI_POO_NOTIFICACIONES.Exceptions.BadRequestException;
import com.example.TPI_POO_NOTIFICACIONES.Exceptions.ResourceNotFoundException;
import com.example.TPI_POO_NOTIFICACIONES.Request.NotificacionRequest;
import com.example.TPI_POO_NOTIFICACIONES.Response.NotificacionResponse;
import com.example.TPI_POO_NOTIFICACIONES.Service.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notificacion")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    // 🔹 Crear una nueva notificación
    @PostMapping
    public ResponseEntity<NotificacionResponse> crear(@RequestBody NotificacionRequest request) {
        if (request.getDestinatario() == null || request.getDestinatario().isEmpty()) {
            throw new BadRequestException("El campo 'destinatario' es obligatorio");
        }
        Notificacion notificacion = notificacionService.crearNotificacion(request);
        return ResponseEntity.ok(new NotificacionResponse(notificacion));
    }

    // 🔹 Obtener todas las notificaciones
    @GetMapping
    public ResponseEntity<List<NotificacionResponse>> obtenerTodas() {
        List<Notificacion> notificaciones = notificacionService.obtenerTodas();
        if (notificaciones.isEmpty()) {
            throw new ResourceNotFoundException("No hay notificaciones registradas");
        }
        List<NotificacionResponse> response = notificaciones.stream()
                .map(NotificacionResponse::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    // 🔹 Obtener notificaciones por usuario
    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificacionResponse>> obtenerPorUsuario(@PathVariable Long userId) {
        List<Notificacion> notificaciones = notificacionService.obtenerPorUsuario(userId);
        if (notificaciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron notificaciones para el usuario con id " + userId);
        }
        List<NotificacionResponse> response = notificaciones.stream()
                .map(NotificacionResponse::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    // 🔹 Eliminar notificación por id
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = notificacionService.eliminar(id);
        if (!eliminado){
            throw new ResourceNotFoundException("No se pudo eliminar: Notificación con id " + id + " no encontrada");
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<NotificacionResponse> actualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String nuevoEstado = body.get("estado");
        Notificacion notificacion = notificacionService.actualizarParcial(id, nuevoEstado);
        return ResponseEntity.ok(new NotificacionResponse(notificacion));
    }
}