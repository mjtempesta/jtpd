package com.foo.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author: Altug Bilgin ALTINTAS
 */


@Entity
@Table(name = "roller")
public class Rol implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolIsmi() {
        return rolIsmi;
    }

    public void setRolIsmi(String rolIsmi) {
        this.rolIsmi = rolIsmi;
    }

    public void setMusteriListesi(List<Musteri> musteriListesi) {
        this.musteriListesi = musteriListesi;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rol_ismi")
    private String rolIsmi;

    public List<Musteri> getMusteriListesi() {
        return musteriListesi;
    }
    
    @ManyToMany(mappedBy = "rolListesi", cascade = CascadeType.ALL)
    private List<Musteri> musteriListesi;


}
