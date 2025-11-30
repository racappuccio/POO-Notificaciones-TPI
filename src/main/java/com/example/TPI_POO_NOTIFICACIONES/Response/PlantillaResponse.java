package com.example.TPI_POO_NOTIFICACIONES.Response;

import com.example.TPI_POO_NOTIFICACIONES.Entity.Canal;
import com.example.TPI_POO_NOTIFICACIONES.Entity.Plantilla;

public class PlantillaResponse {
    private Long id;
    private Canal canal;
    private String asunto;
    private String cuerpo;

    // Constructor desde entidad
    public PlantillaResponse(Plantilla p) {
        this.id = Long.valueOf(p.getId());
        this.canal = p.getCanal();
        this.asunto = p.getAsunto();
        this.cuerpo = p.getCuerpo();
    }

    // Getters
    public Long getId() { return id; }
    public Canal getCanal() { return canal; }
    public String getAsunto() { return asunto; }
    public String getCuerpo() { return cuerpo; }
}