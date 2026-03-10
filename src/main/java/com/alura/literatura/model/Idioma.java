package com.alura.literatura.model;

public enum Idioma {

    ES("es"),
    EN("en"),
    FR("fr");

    private String codigo;

    Idioma(String codigo){
        this.codigo = codigo;
    }

}