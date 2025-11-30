package com.example.TPI_POO_NOTIFICACIONES.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion") // nombre explícito de la tabla
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental
    private Long id;

    private Long userId;
    private String destinatario;

    @Enumerated(EnumType.STRING)
    private Canal canal;

    // Relación con Plantilla
    @ManyToOne (optional = false)
    @JoinColumn(name = "plantilla_id", nullable = false)
    private Plantilla plantilla;

    @Column(length = 2000)
    private String cuerpoFinal;

    @Enumerated(EnumType.STRING)
    private EstadoNotificacion estado;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    // Constructores
    //public Notificacion(Long userId, String destinatario, Canal canal, Plantilla plantilla) {}

    public Notificacion(Long userId, String destinatario, Canal canal, Plantilla plantilla) {
        this.userId = userId;
        this.destinatario = destinatario;
        this.canal = canal;
        this.plantilla = plantilla;
        this.estado = EstadoNotificacion.EN_COLA;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Notificacion() {

    }

    // Métodos de negocio
    public void marcarEnCola() { this.estado = EstadoNotificacion.EN_COLA; }
    public void marcarEnviada() { this.estado = EstadoNotificacion.ENVIADA; }
    public void marcarError() { this.estado = EstadoNotificacion.ERROR; }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public Canal getCanal() { return canal; }
    public void setCanal(Canal canal) { this.canal = canal; }

    public Plantilla getPlantilla() { return plantilla; }
    public void setPlantilla(Plantilla plantilla) { this.plantilla = plantilla; }

    public String getCuerpoFinal() { return cuerpoFinal; }
    public void setCuerpoFinal(String cuerpoFinal) { this.cuerpoFinal = cuerpoFinal; }

    public EstadoNotificacion getEstado() { return estado; }
    public void setEstado(EstadoNotificacion estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    // Getter auxiliar para simplificar el Response
    public Long getPlantillaId() {
        return plantilla != null ? Long.valueOf(plantilla.getId()) : null;
    }


}