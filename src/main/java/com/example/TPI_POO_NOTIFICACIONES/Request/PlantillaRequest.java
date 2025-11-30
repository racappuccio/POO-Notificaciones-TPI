package com.example.TPI_POO_NOTIFICACIONES.Request;

public class PlantillaRequest {
    private Long id;       // identificador único de la plantilla
    private String canal;    // "EMAIL" o "IN_APP"
    private String asunto;   // asunto del mensaje
    private String cuerpo;   // cuerpo con placeholders {{var}}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCanal() { return canal; }
    public void setCanal(String canal) { this.canal = canal; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getCuerpo() { return cuerpo; }
    public void setCuerpo(String cuerpo) { this.cuerpo = cuerpo; }
}