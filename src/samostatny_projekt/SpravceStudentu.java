package samostatny_projekt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SpravceStudentu {
	private HashMap<Integer, Student> studenti;
	private int nextId;
	Scanner sc = new Scanner(System.in);
	
	public SpravceStudentu() {
		studenti = new HashMap<>();
		nextId = 1;
	}
 	
	public void vytvoritStudenta() {
		System.out.println("Zadejte obor: 1 = Telekomunikace, 2 = Kyberbezpečnost");
		int obor = sc.nextInt();
		if (obor < 1 || obor > 2) {
			return;
		}
		
		System.out.println("Zadejte jmeno, prijmeni a rok narozeni");
		String jmeno = sc.next();
		String prijmeni = sc.next();
		int rokNarozeni = sc.nextInt();
		int id = nextId++;
		
		if (obor == 1) {
			StudentTelekomunikaci student = new StudentTelekomunikaci(jmeno, prijmeni, rokNarozeni);
			student.id = id;
			studenti.put(id, student);
		}
		else if (obor == 2) {
			StudentKyberbezpecnosti student = new StudentKyberbezpecnosti(jmeno, prijmeni, rokNarozeni);
			student.id = id;
			studenti.put(id, student);
		}
		
	}
	
	public void pridatZnamku(int id, int znamka) {
		if (studenti.containsKey(id)) {
			studenti.get(id).pridatZnamku(znamka);
		}
	}
	
	public void odstranitStudenta(int id) {
		if (studenti.containsKey(id)) {
			studenti.remove(id);
		}
	}
	
	public void vypisInfoStudenta(int id) {
		if (studenti.containsKey(id)) {
			studenti.get(id).vytiskInfo();
		}
	}
	
	public void spustitDovednost(int id) {
		if (studenti.containsKey(id)) {
			studenti.get(id).spustitDovednost();
		}
	}
	
	public HashMap<Integer, Student> getAllStudenti() {
		return studenti;
	}
	public void abecedneVypis(int obor) {
		List<Student> studentiOboru = new ArrayList<>();
		if (obor == 1) {
			for (Student st : studenti.values()) {
				if (st instanceof StudentTelekomunikaci) {
					studentiOboru.add(st);
				}
			}
		}
		else if (obor == 2) {
			for (Student st : studenti.values()) {
				if (st instanceof StudentKyberbezpecnosti) {
					studentiOboru.add(st);
				}
			}
		}
		
		studentiOboru.sort(Comparator.comparing(Student -> Student.prijmeni));
		for  (Student st : studentiOboru) {
			st.vytiskInfo();
		}
	}
	
	public float průměrVOboru(int obor) {
		float suma = 0;
		int pocet = 0;
		if (obor == 1) {
			for (Student st : studenti.values()) {
				if (st instanceof StudentTelekomunikaci) {
					suma += st.getStudijniPrumer();
					pocet++;
				}
			}
		}
		else if (obor == 2) {
			for (Student st : studenti.values()) {
				if (st instanceof StudentKyberbezpecnosti) {
					suma += st.getStudijniPrumer();
					pocet++;
				}
			}
		}
		
		return suma/pocet;		
	}
	
	public int studentuVOboru(int obor) {
		int pocet = 0;
		if (obor == 1) {
			for (Student st : studenti.values()) {
				if (st instanceof StudentTelekomunikaci) {
					pocet++;
				}
			}
		}
		else if (obor == 2) {
			for (Student st : studenti.values()) {
				if (st instanceof StudentKyberbezpecnosti) {
					pocet++;
				}
			}
		}
		return pocet;
	}
	
	public void ulozeniDoSouboru(int id, String filename) throws IOException {
		FileWriter fw = new FileWriter(filename);
		Student info = studenti.get(id);
		int obor;
		if (info instanceof StudentTelekomunikaci) {
			obor = 1;
		}
		else {
			obor = 2;
		}
		List<Integer> znamky = info.getZnamky();
		
    		fw.write("ID, obor, jmeno, prijmeni, rok narozeni, studijni prumer, znamky\n");
		fw.write(info.getID() + ", " + obor + ", "  + info.getJmeno() +", " + info.getPrijmeni() + ", " + info.getRokNarozeni() + ", " + info.getStudijniPrumer() + ", " + znamky + "\n");
       		fw.close();
       		System.out.println("Výpis studenta ukončen");
	}
	
	public void nacteniZeSouboru(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
    		BufferedReader in = new BufferedReader(fr);
    		in.readLine();
    		String radek;
    		int id;
    		Student student;
    		while ((radek = in.readLine()) != null) {
    			String info[] = radek.split(", ", 7);
    			id = pridatStudenta(info[2], info[3], Integer.valueOf(info[4]), Integer.valueOf(info[1]));
    			student = studenti.get(id);
    			String znamkyText = info[6].replace("[", "").replace("]", "");
    			for (String s : znamkyText.split(", ")) {
    				student.pridatZnamku(Integer.valueOf(s));
    			}    		
    		}
    		fr.close();
	}
	
	public int pridatStudenta(String jmeno, String prijmeni, int rokNarozeni, int obor) {
		Student student;
		int id = nextId++;
		if (obor == 1) {
			student = new StudentTelekomunikaci(jmeno, prijmeni, rokNarozeni);
		}
		else {
			student = new StudentKyberbezpecnosti(jmeno, prijmeni, rokNarozeni);
		}
		student.id = id;
		studenti.put(id, student);
		return id;
	}
}
