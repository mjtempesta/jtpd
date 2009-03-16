/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.org.java.dao;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import tr.org.java.data.Kisi;


public class KisiBean implements Serializable{

	private List<Kisi> kisiler = new ArrayList<Kisi>();
	private Kisi mevcutKisi = new Kisi();

	/**
     * 
     * @return Kisi listesini geri dondurur.
     */
    public List<Kisi> getKisiler() {
		// varsayilan liste
		if (kisiler.isEmpty()) {

            kisiler.add(new Kisi(1, "Mert", "Çalışkan"));
            kisiler.add(new Kisi(2, "Ali Ozan", "Çil"));
            kisiler.add(new Kisi(3, "Taner", "Diler"));
            kisiler.add(new Kisi(4, "Altuğ Bilgin", "Altıntaş"));
            kisiler.add(new Kisi(5, "Batuhan", "Kayhan"));
			kisiler.add(new Kisi(6, "Çağatay", "Çivici"));
			kisiler.add(new Kisi(7, "Yiğit", "Darçın"));
			kisiler.add(new Kisi(8, "Kerem", "Koşaner"));
			kisiler.add(new Kisi(9, "Emre", "Uzunoğlu"));

		}
		return kisiler;
	}

	public String ekle() {
		mevcutKisi.setId(new Random().nextInt());
		kisiler.add(mevcutKisi);
		setMevcutKisi(new Kisi());
		return "";
	}

	public String guncelle() {
		for (Kisi kisi : kisiler) {
			if (kisi.getId() == mevcutKisi.getId()) {
				kisi = mevcutKisi;
			}
		}
		setMevcutKisi(new Kisi());
		return "";
	}

	public String sil() {
		kisiler.remove(mevcutKisi);
		return "";
	}

	public String mevcutKisiTemizle() {
		setMevcutKisi(new Kisi());
		return "";
	}

	public void setKisiler(List<Kisi> kisiler) {
		this.kisiler = kisiler;
	}

	public Kisi getMevcutKisi() {
		return mevcutKisi;
	}

	public void setMevcutKisi(Kisi mevcutKisi) {
		this.mevcutKisi = mevcutKisi;
	}
}