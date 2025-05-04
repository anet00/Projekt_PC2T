package samostatny_projekt;

import java.util.Map;

public class StudentTelekomunikaci extends Student {

	public StudentTelekomunikaci(String jmeno, String prijmeni, int rokNarozeni) {
		super(jmeno, prijmeni, rokNarozeni);
	}

	@Override
	public void spustitDovednost() {
		System.out.println(jmeno + " " + prijmeni + " v Morseově abecedě: " + textToMorse(jmeno + " " + prijmeni));
	}

	public String textToMorse(String text) {
		Map<Character, String> AlphMorseMap = Map.ofEntries(
				Map.entry('A', ".-"), 
				Map.entry('B', "-..."), 
				Map.entry('C', "-.-."), 
				Map.entry('D', "-.."), 
				Map.entry('E', "."), 
				Map.entry('F', "..-."), 
				Map.entry('G', "--."), 
				Map.entry('H', "...."), 
				Map.entry('I', ".."), 
				Map.entry('J', ".---"),
				Map.entry('K', "-.-"),
				Map.entry('L', ".-.."),
				Map.entry('M', "--"),
				Map.entry('N', "-."),
				Map.entry('O', "---"),
				Map.entry('P', ".--."),
				Map.entry('Q', "--.-"),
				Map.entry('R', ".-."),
				Map.entry('S', "..."),
				Map.entry('T', "-"),
				Map.entry('U', "..-"),
				Map.entry('V', "...-"),
				Map.entry('W', ".--"),
				Map.entry('X', "-..-"),
				Map.entry('Y', "-.--"),
				Map.entry('Z', "--.."));
		text = text.toUpperCase();
		String morse = "";
		
		for (char c: text.toCharArray()) {
			if (Character.isLetter(c)) {
				morse += AlphMorseMap.get(c) + " / ";
			}
			else {
				morse += " // ";
			}
		}
		return morse;
	}
}
