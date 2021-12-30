package EbayTest;

import java.util.ArrayList;
import java.util.Arrays;

public class Q5 {
	public static void main(String args[]) {
		String[] str = {"21","123","111","11"};
		System.out.println(Arrays.toString(solution(str)));
	}
	public static String[] solution(String []P) {
		ArrayList<String> answer = new ArrayList<>();
		//String[] answer = new String[2];
		String[] str = new String[P.length];
		for(int i=0; i<P.length; i++) {
			str[i] = P[i];
		}
		
		for(int i=1; i<P.length; i++) {
			
			P = Array_Copy(str, P);
			String p = P[i];
			if(Palindrome(P[0]+P[i]) || Palindrome(P[i]+P[0])) {
				P[0] = "-1";
				P[i] = "-1";
				if(check(P)) {
					answer.add(p);
				}
			}
		}
		String[] result = answer.toArray(new String[0]);
		return result;
	}
	public static boolean check(String[] str) {
		for(int i=0; i<str.length-1; i++) {
			if(str[i].equals("-1")) continue;
			for(int j=i+1; j<str.length; j++) {
				if(str[j].equals("-1")) continue;
				if(!(Palindrome(str[i]+str[j])||Palindrome(str[j]+str[i]))) {
					return false;
				}	
			}
		}
		return true;
	}
	public static boolean Palindrome(String str) {
		int left = 0;
		int right = str.length()-1;
		while(left < right) {
			char leftC = str.charAt(left);
			char rightC = str.charAt(right);
			
			if(leftC!=rightC) return false;
			
			if(leftC==rightC) {
				left++;
				right--;
			}
		}
		return true;
	}
	public static String[] Array_Copy(String[] arr, String[] copy) {
		for(int i=0; i<arr.length; i++) {
			copy[i] = arr[i];
		}
		return copy;
	}
 }
