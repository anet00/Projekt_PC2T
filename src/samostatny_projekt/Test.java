package samostatny_projekt;

import java.io.IOException;
import java.util.Scanner;


public class Test {
	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}

	
	public static void main(String[] args) {
		SpravceStudentu spravce = new SpravceStudentu();
		int id;
		int obor;
		String jmeno;
		Databaze db = new Databaze();
		Scanner sc = new Scanner(System.in);
		
		db.DBstartupload(spravce);
		boolean run = true;
		while(run)
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vložení nového studenta");
			System.out.println("2 .. zadat známku");
			System.out.println("3 .. propustit studenta");
			System.out.println("4 .. vypis informace o studentovi");
			System.out.println("5 .. spustit dovednost studenta");
			System.out.println("6 .. seřazený výpis studentů");
			System.out.println("7 .. obecný studijní průměr");
			System.out.println("8 .. celkový počet studentů");
			System.out.println("9 .. uložit studenta do souboru");
			System.out.println("10 .. načíst studenta ze souboru");
			System.out.println("11 .. ukončit program");

			int volba;
			volba=pouzeCelaCisla(sc);
			switch(volba)
			{
				case 1:
					spravce.vytvoritStudenta();
					break;
				case 2:
					System.out.println("Zadejte ID studenta a známku");
					id = sc.nextInt();
					int znamka = sc.nextInt();
					spravce.pridatZnamku(id, znamka);
					break;
				case 3:
					System.out.println("Zadejte ID studenta");
					id =sc.nextInt();
					spravce.odstranitStudenta(id);
					break;
				case 4:
					System.out.println("Zadejte ID studenta");
					id =sc.nextInt();
					spravce.vypisInfoStudenta(id);
					break;
					
				case 5:
					System.out.println("Zadejte ID studenta");
					id = sc.nextInt();
					spravce.spustitDovednost(id);
					break;
					
				case 6:
					System.out.println("Zadejte číslo oboru");
					obor = sc.nextInt();
					spravce.abecedneVypis(obor);
					break;
					
				case 7:
					System.out.println("Zadejte číslo oboru");
					obor = sc.nextInt();
					if (obor >= 1 && obor <= 2) {
						float prumer = spravce.průměrVOboru(obor);
						System.out.println("Průměr v oboru: " + prumer);
					}
					else {
						System.out.println("špatné číslo oboru");
					}
					break;
				case 8:
					System.out.println("Zadejte číslo oboru");
					obor = sc.nextInt();
					if (obor >= 1 && obor <= 2) {
						int pocet = spravce.studentuVOboru(obor);
						System.out.println("Počet studentů v oboru: " + pocet);
					}
					else {
						System.out.println("špatné číslo oboru");
					}
					break;
				case 9:
					System.out.println("Zadejte ID studenta a název souboru pro uložení");
					id = sc.nextInt();
					jmeno = sc.next();
					try {
						spravce.ulozeniDoSouboru(id, jmeno);
					} catch (IOException e) {
						System.out.println("Nastala vyjimka typu "+e.toString());
						System.out.println("Nepovedlo se uložit data do souboru");
					}
					break;
				case 10:
					System.out.println("zadejte název souboru pro načtení");
					jmeno = sc.next();
				try {
					spravce.nacteniZeSouboru(jmeno);
				} catch (IOException e) {
					System.out.println("Nepodařilo se načíst data ze souboru " + e.toString());
				}
					break;
				case 11:
					run=false;
					db.DBexitsave(spravce);
					break;
			}
			
		}


	}

}
