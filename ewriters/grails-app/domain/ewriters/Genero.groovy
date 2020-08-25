package ewriters

enum Genero {
    TERROR("TERROR"),
    CIENCIA_FICCION("CIENCIA_FICCION"),
    NO_FICCION("NO_FICCION"),
    POESIA("POESIA"),
    FANTASIA("FANTASIA"),
    ROMANCE("ROMANCE"),
    MISTERIO("MISTERIO"),
    HUMOR("HUMOR")

    final String value

    Genero(String value) {
        this.value = value
    }

    String getValue() {
        this.value
    }

    String toString(){
        value
    }
}