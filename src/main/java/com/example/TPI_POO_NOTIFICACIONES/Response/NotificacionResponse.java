package com.example.TPI_POO_NOTIFICACIONES.Response;
import com.example.TPI_POO_NOTIFICACIONES.Entity.Canal;
import com.example.TPI_POO_NOTIFICACIONES.Entity.EstadoNotificacion;
import com.example.TPI_POO_NOTIFICACIONES.Entity.Notificacion;

public class NotificacionResponse {
    private Long id;
    private Long userId;
    private String destinatario;
    private Canal canal;
    private String cuerpoFinal;
    private EstadoNotificacion estado;
    private Long plantillaId;
    private String fechaCreacion;

    // Constructor desde entidad
    public NotificacionResponse(Notificacion n) {
        this.id = Long.valueOf(n.getId());
        this.userId = Long.valueOf(n.getUserId());
        this.destinatario = n.getDestinatario();
        this.canal = n.getCanal();
        this.cuerpoFinal = n.getCuerpoFinal();
        this.estado = n.getEstado();
        this.plantillaId = Long.valueOf(n.getPlantilla().getId());
        this.fechaCreacion = n.getFechaCreacion().toString();
    }

    // Getters
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getdestinatario() { return destinatario; }
    public Canal getCanal() { return canal; }
    public String getCuerpoFinal() { return cuerpoFinal; }
    public EstadoNotificacion getEstado() { return estado; }
    public Long getPlantillaId() { return plantillaId; }
    public String getFechaCreacion() { return fechaCreacion; }
}