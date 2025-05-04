package samostatny_projekt;

import java.util.ArrayList;
import java.util.List;

public abstract class Student {
	protected int id;
	protected String jmeno;
	protected String prijmeni;
	protected int rokNarozeni;
	protected List<Integer> znamky;
	protected float studijniPrumer;

	public Student(String jmeno, String prijmeni, int rokNarozeni) {
		this.jmeno = jmeno;
		this.prijmeni = prijmeni;
		this.rokNarozeni = rokNarozeni;
		this.znamky = new ArrayList<Integer>();
		this.studijniPrumer = 0;
	}

	public abstract void spustitDovednost();

	public void pridatZnamku(int znamka) {
		znamky.add(znamka);
		spocitatPrumer();
	}

	public void spocitatPrumer() {
		if (!znamky.isEmpty()) {
			float suma = 0;
			for (int i = 0; i < znamky.size(); i++) {
				suma += znamky.get(i);
			}
			studijniPrumer = suma / znamky.size();
		}
	}
	
	public String getJmeno() {
		return jmeno;
	}
	
	public String getPrijmeni() {
		return prijmeni;
	}
	
	public int getRokNarozeni() {
		return rokNarozeni;
	}
	
	public float getStudijniPrumer() {
		return studijniPrumer;
	}
	
	public int getID() {
		return id;
	}
	
	public List<Integer> getZnamky() {
		return znamky;
	}
	public void vytiskInfo() {
		System.out.println("ID: " + id + ", jméno: " + jmeno + " " + prijmeni + ", rok narození: " + rokNarozeni + ", Studijní průměr: " + studijniPrumer + "znamky: " + znamky);
	}

	
}
