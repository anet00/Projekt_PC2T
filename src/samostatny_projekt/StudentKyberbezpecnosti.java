package samostatny_projekt;

public class StudentKyberbezpecnosti extends Student {

	public StudentKyberbezpecnosti(String jmeno, String prijmeni, int rokNarozeni) {
		super(jmeno, prijmeni, rokNarozeni);
	}

	@Override
	public void spustitDovednost() {
		System.out.println(jmeno + " " + prijmeni + " ve formě hashe: " + textToHash(jmeno + " " + prijmeni));
	}

	public int textToHash(String text) {
		return text.hashCode();
	}
}
