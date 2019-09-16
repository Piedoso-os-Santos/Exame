package com.ronomicro.exame.adapters;

/**
 * Created by AMINE on 04/05/2017.
 */

public class row {
    public row() {

    }
    public row(String titre, int img) {
        this.titre = titre;
        this.img = img;
    }

    public String getTitre() {
        return titre;
    }

    public int getImg() {
        return img;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setImg(int img) {
        this.img = img;
    }

    String titre;
    int img;
}
