package com.example.TPI_POO_NOTIFICACIONES.Request;

import com.example.TPI_POO_NOTIFICACIONES.Entity.Canal;
import com.example.TPI_POO_NOTIFICACIONES.Entity.EstadoNotificacion;

import java.util.Map;

public class NotificacionRequest {
    private String canal;       // "EMAIL" o "IN_APP"
    private Long userId;        // ID del usuario destinatario
    private String destinatario;          // dirección de correo o identificador interno
    private Long plantillaId;   // 🔹 corregido: coincide con la entidad y el service
    private Map<String, String> vars; // variables dinámicas para renderizar el cuerpo
    private String cuerpoFinal;
    private EstadoNotificacion estado;
    //private Canal canal;

    // Getters y setters
    public String getCanal() { return canal; }
    public void setCanal(String canal) { this.canal = canal; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public Long getPlantillaId() { return plantillaId; }
    public void setPlantillaId(Long plantillaId) { this.plantillaId = plantillaId; }

    public Map<String, String> getVars() { return vars; }
    public void setVars(Map<String, String> vars) { this.vars = vars; }

    public String getCuerpoFinal() { return cuerpoFinal; }
    public void setCuerpoFinal (String cuerpoFinal) { this.cuerpoFinal = cuerpoFinal;}

    public EstadoNotificacion getEstado() { return estado; }
    public void setEstado(EstadoNotificacion estado) { this.estado = estado; }
}