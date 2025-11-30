package com.example.TPI_POO_NOTIFICACIONES.Repository;

import com.example.TPI_POO_NOTIFICACIONES.Entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUserId(Long userId);
    long countByPlantilla_Id(Long plantillaId); // ✅ versión corregida
}