package com.example.TPI_POO_NOTIFICACIONES.Service;

import com.example.TPI_POO_NOTIFICACIONES.Entity.Canal;
import com.example.TPI_POO_NOTIFICACIONES.Entity.EstadoNotificacion;
import com.example.TPI_POO_NOTIFICACIONES.Entity.Notificacion;
import com.example.TPI_POO_NOTIFICACIONES.Entity.Plantilla;
import com.example.TPI_POO_NOTIFICACIONES.Exceptions.ResourceNotFoundException;
import com.example.TPI_POO_NOTIFICACIONES.Repository.NotificacionRepository;
import com.example.TPI_POO_NOTIFICACIONES.Repository.PlantillaRepository;
import com.example.TPI_POO_NOTIFICACIONES.Request.NotificacionRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final PlantillaRepository plantillaRepository;

    public NotificacionService(NotificacionRepository notificacionRepository,
                               PlantillaRepository plantillaRepository) {
        this.notificacionRepository = notificacionRepository;
        this.plantillaRepository = plantillaRepository;
    }

    @Transactional
    public Notificacion crearNotificacion(NotificacionRequest request) {
        Plantilla plantilla = plantillaRepository.findById(request.getPlantillaId())
                .orElseThrow(() -> new RuntimeException("Plantilla no encontrada"));

        String cuerpoFinal = plantilla.getCuerpo();
        for (var entry : request.getVars().entrySet()) {
            cuerpoFinal = cuerpoFinal.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }

        Notificacion notificacion = new Notificacion(
                request.getUserId(),
                request.getDestinatario(),
                Canal.valueOf(request.getCanal()),
                plantilla
        );
        notificacion.setCuerpoFinal(cuerpoFinal);
        notificacion.setEstado(EstadoNotificacion.EN_COLA);
        notificacion.setFechaCreacion(LocalDateTime.now());


        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> obtenerTodas() {
        return notificacionRepository.findAll();
    }

    public List<Notificacion> obtenerPorUsuario(Long userId) {
        return notificacionRepository.findByUserId(userId);
    }

    @Transactional
    public boolean eliminar(Long id) {
        notificacionRepository.deleteById(id);
        return true;
    }
    public Notificacion actualizarParcial(Long id, String nuevoEstado) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación con id " + id + " no encontrada"));

        try {
            notificacion.setEstado(EstadoNotificacion.valueOf(nuevoEstado));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado inválido: " + nuevoEstado);
        }

        return notificacionRepository.save(notificacion);
    }


}