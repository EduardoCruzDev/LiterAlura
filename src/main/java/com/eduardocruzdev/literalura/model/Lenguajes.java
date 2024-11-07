package com.eduardocruzdev.literalura.model;

public enum Lenguajes {
    ESPANOL("Español", "es"),
    INGLES("Inglés", "en"),
    MANDARIN("Mandarín", "zh"),
    HINDI("Hindi", "hi"),
    ARABE("Árabe", "ar"),
    BENGALI("Bengalí", "bn"),
    PORTUGUES("Portugués", "pt"),
    RUSO("Ruso", "ru"),
    JAPONES("Japonés", "ja"),
    PUNJABI("Punjabi", "pa"),
    FRANCES("Francés", "fr"),
    ALEMAN("Alemán", "de"),
    ITALIANO("Italiano", "it"),
    KOREANO("Coreano", "ko"),
    TURCO("Turco", "tr"),
    VIETNAMITA("Vietnamita", "vi"),
    POLACO("Polaco", "pl"),
    MARATHI("Marathi", "mr"),
    TELUGU("Telugu", "te"),
    WU("Wu", "wuu"),
    MALAYALAM("Malayalam", "ml"),
    THAI("Tailandés", "th"),
    URDU("Urdu", "ur"),
    JIN("Jin", "jin"),
    PERSA("Persa", "fa"),
    ROMENO("Rumano", "ro"),
    GREGO("Griego", "el"),
    HUNGARO("Húngaro", "hu"),
    CZECHO("Checo", "cs"),
    FINLANDES("Finlandés", "fi"),
    SUECO("Sueco", "sv"),
    DANES("Danés", "da"),
    NORUEGO("Noruego", "no"),
    HEBREO("Hebreo", "he"),
    MALAYO("Malayo", "ms"),
    CANTONES("Cantonés", "yue"),
    INDONESIO("Indonesio", "id");

    private final String nombre;
    private final String acronimo;

    // Constructor del enum
    Lenguajes(String nombre, String acronimo) {
        this.nombre = nombre;
        this.acronimo = acronimo;
    }

    // Métodos para acceder a los valores del enum
    public String getNombre() {
        return nombre;
    }

    public String getAcronimo() {
        return acronimo;
    }

    @Override
    public String toString() {
        return nombre + " (" + acronimo + ")";
    }
}

