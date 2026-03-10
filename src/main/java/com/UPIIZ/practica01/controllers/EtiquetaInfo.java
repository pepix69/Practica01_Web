package com.UPIIZ.practica01.controllers;

import java.util.List;

public class EtiquetaInfo {
    private String nombre;
    private String categoria;
    private String descripcion;
    private String sintaxis;
    private String atributos;
    private String ejemploCodigo;
    private String ejemploRenderizado;

    public EtiquetaInfo(String nombre, String categoria, String descripcion,
                        String sintaxis, String atributos,
                        String ejemploCodigo, String ejemploRenderizado) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.sintaxis = sintaxis;
        this.atributos = atributos;
        this.ejemploCodigo = ejemploCodigo;
        this.ejemploRenderizado = ejemploRenderizado;
    }

    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    public String getSintaxis() { return sintaxis; }
    public String getAtributos() { return atributos; }
    public String getEjemploCodigo() { return ejemploCodigo; }
    public String getEjemploRenderizado() { return ejemploRenderizado; }
}

