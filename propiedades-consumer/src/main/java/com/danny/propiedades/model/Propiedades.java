package com.danny.propiedades.model;

import java.util.List;

public class Propiedades {
    private int id;
    private List<Propiedad> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Propiedad> getItems() {
        return items;
    }

    public void setItems(List<Propiedad> items) {
        this.items = items;
    }
}
