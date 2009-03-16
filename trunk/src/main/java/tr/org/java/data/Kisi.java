package tr.org.java.data;

import java.io.Serializable;

public class Kisi implements Serializable {

	private int id;
	private String ad;
	private String soyad;
	
	public Kisi(int id, String ad, String soyad) {
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
	}

	public Kisi() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Kisi) {
			Kisi kisi = (Kisi) obj;
			if (this.id == kisi.getId())
				return true;
		}
		return false;
	}
}