package com.example.firebase;

public class Pokemon {
    private String id;
    private String nombre;
    private String vida;
    private String ataque;
    private String defensa;
    public Pokemon(){

    }
    public  Pokemon (String id,String nombre,String vida,String ataque,String defensa){
        this.id =  id;
        this.nombre =  nombre;
        this.ataque = ataque;
        this.defensa =  defensa;
        this.vida = vida;

    }

    @Override
    public String toString() {
        return nombre+" "+vida+" "+ataque+" "+defensa;
    }

    public String getDefensa() {
        return defensa;
    }

    public void setDefensa(String defensa) {
        this.defensa = defensa;
    }

    public String getAtaque() {
        return ataque;
    }

    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVida() {
        return vida;
    }

    public void setVida(String vida) {
        this.vida = vida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
