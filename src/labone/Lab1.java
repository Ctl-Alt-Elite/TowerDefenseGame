package labone;

public class Lab1 {
	
	public static void main(String[] args) {
		System.out.println(countCaps("PiZZa PlaNet"));
		System.out.println(countCaps("hello"));
		System.out.println(countChars("Hello", 'l'));
		System.out.println(countChars("java", 'J'));
		System.out.println(convertURL("Strings are cool"));
		System.out.println(convertURL("Java"));
		System.out.println(reverse("park hill"));
		System.out.println(reverse("lets write code"));
		System.out.println(isPalindrome("racecar"));
		System.out.println(isPalindrome("java"));
		System.out.println(isPalindrome("never odd or even"));
		System.out.println(removeAll("Remove them all!", "e"));
		System.out.println(removeAll("Remove them all!", "em"));
	}
	//Counts the capital letters in a string passed.
	public static int countCaps(String s) {
		int capsCount = 0;
		for (char letter : s.toCharArray()) {
			if (letter>64&&letter<91) {
				capsCount++;
			}
		}
		return capsCount;
	}
	//Counts the number of times a given char is in a string. (Ignores Case)
	public static int countChars(String s, char c) {
		int charCount = 0;
		s = s.toLowerCase();
		if (c>64&&c<90) {c += 32;}
		for (char letter : s.toCharArray()) {
			if (letter==c) {charCount++;}
		}
		return charCount;
	}
	//Converts a given string into a URL, replacing all spaces with %20.
	public static String convertURL(String url) {
		String newURL = "";
		for (char letter : url.toCharArray()) {
			if (letter==' ') {newURL = newURL.concat("%20");}
			else {newURL = newURL.concat(Character.toString(letter));}
		}
		return newURL;
	}
	//Reverses the order of words in a given String.
	public static String reverse(String s) {
		String reverse = "";
		int temp;
		while (s.lastIndexOf(" ")>-1) {
			temp = s.lastIndexOf(" ");
			reverse = reverse.concat(s.substring(temp+1)+" ");
			s = s.substring(0,temp);
		}
		if (s!="") {reverse = reverse.concat(s);}
		reverse = reverse.replace("!", "");
		reverse = reverse.replace(".", "");
		return reverse;
	}
	//Returns a boolean for whether or not a string is a palindrome.
	public static Boolean isPalindrome(String s) {
		int temp;
		String reversed = "";
		s = s.replaceAll(" ", "");
		s = s.toLowerCase();
		String normal = s;
		temp=s.length();
		for (int x = temp; x>0; x--) {
			reversed = reversed.concat(s.substring(temp-1));
			s = s.substring(0, temp-1);
			temp--;
		}
		if (normal.equals(reversed)) {return true;}
		else {return false;}
	}
	//Removes all occurences of second passed string from the first passed string.
	public static String removeAll(String p1, String p2) {
		int maxBound;
		int index;
		while (p1.contains(p2) ) {
			maxBound = p2.length();
			index = p1.indexOf(p2);
			p1 = p1.substring(0, index).concat(p1.substring(index+maxBound));
		}
		return p1;
	}
}
