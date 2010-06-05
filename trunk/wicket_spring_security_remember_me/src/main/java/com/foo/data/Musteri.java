package com.foo.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author: Altug Bilgin ALTINTAS
 */

@Entity
@Table(name = "musteriler")
public class Musteri implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public List<Rol> getRolListesi() {
        return rolListesi;
    }

    public void setRolListesi(List<Rol> rolListesi) {
        this.rolListesi = rolListesi;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "eposta")
    private String eposta;

    @Column(name = "password")
    private String sifre;


    // bi-directional many to many dikkat.
    // kullanicinin rollerine ulasilabiir
    // ayni zamanda rollere ait kisiler bulunabilir.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "musteri_rol",
            joinColumns = {@JoinColumn(name = "musteri_id")},
            inverseJoinColumns = {@JoinColumn(name = "rol_id")})
    private List<Rol> rolListesi;


}
