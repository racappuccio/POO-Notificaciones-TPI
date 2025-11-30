package com.example.TPI_POO_NOTIFICACIONES.Entity;

import jakarta.persistence.*;
import java.util.Map;

@Entity
@Table(name = "plantilla") // nombre explícito de la tabla
public class Plantilla {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental
    private String id;

    @Enumerated(EnumType.STRING)
    private Canal canal;

    private String asunto;

    @Column(length = 2000)
    private String cuerpo;

    // Constructores
    public Plantilla() {}

    // 🔹 Constructor sin id (el id lo genera la BD)
    public Plantilla(Canal canal, String asunto, String cuerpo) {
        this.canal = canal;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    // 👉 Método render para reemplazar variables en el cuerpo
    public String render(Map<String, String> vars) {
        if (vars == null) return cuerpo;
        String result = cuerpo;
        for (var entry : vars.entrySet()) {
            result = result.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return result;
    }

    // Getters,
    public String getId() { return id; }
    public Canal getCanal() { return canal; }
    public String getAsunto() { return asunto; }
    public String getCuerpo() { return cuerpo; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setCanal(Canal canal) { this.canal = canal; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public void setCuerpo(String cuerpo) { this.cuerpo = cuerpo; }
}