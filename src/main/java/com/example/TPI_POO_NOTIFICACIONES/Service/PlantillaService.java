package com.example.TPI_POO_NOTIFICACIONES.Service;

import com.example.TPI_POO_NOTIFICACIONES.Entity.Canal;
import com.example.TPI_POO_NOTIFICACIONES.Entity.Plantilla;
import com.example.TPI_POO_NOTIFICACIONES.Repository.PlantillaRepository;
import com.example.TPI_POO_NOTIFICACIONES.Repository.NotificacionRepository;
import com.example.TPI_POO_NOTIFICACIONES.Request.PlantillaRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlantillaService {

    private final PlantillaRepository plantillaRepository;
    private final NotificacionRepository notificacionRepository;

    public PlantillaService(PlantillaRepository plantillaRepository,
                            NotificacionRepository notificacionRepository) {
        this.plantillaRepository = plantillaRepository;
        this.notificacionRepository = notificacionRepository;
    }

    @Transactional
    public Plantilla crearPlantilla(PlantillaRequest dto) {
        Plantilla plantilla = new Plantilla(
                Canal.valueOf(dto.getCanal()),
                dto.getAsunto(),
                dto.getCuerpo()
        );
        return plantillaRepository.save(plantilla);
    }

    public List<Plantilla> obtenerTodas() {
        return plantillaRepository.findAll();
    }

    public Plantilla obtenerPorId(Long id) {
        return plantillaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plantilla no encontrada"));
    }

    @Transactional
    public Plantilla actualizarParcial(Long id, PlantillaRequest dto) {
        Plantilla plantilla = plantillaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plantilla no encontrada"));

        if (dto.getCanal() != null) {
            plantilla.setCanal(Canal.valueOf(dto.getCanal()));
        }
        if (dto.getAsunto() != null) {
            plantilla.setAsunto(dto.getAsunto());
        }
        if (dto.getCuerpo() != null) {
            plantilla.setCuerpo(dto.getCuerpo());
        }

        return plantillaRepository.save(plantilla);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!plantillaRepository.existsById(id)) {
            throw new RuntimeException("Plantilla no encontrada");
        }

        long count = notificacionRepository.countByPlantilla_Id(id);
        if (count > 0) {
            throw new RuntimeException("No se puede borrar la plantilla porque está en uso");
        }

        plantillaRepository.deleteById(id);
    }
}