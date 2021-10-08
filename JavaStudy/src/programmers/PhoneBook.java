// 전화번호 목록 ( 프로그래머스 LEVEL2 )

package programmers;

import java.util.Arrays;

// ver1 : 실패
// 정확성 테스트 1,5,13,14 & 효율성 테스트 테스트 1-4 실패
/*
public class PhoneBook {
	public static void main(String args[]) {
		String[] phone_book = {"442", "97674223", "1195524421"};
		PhoneBook s = new PhoneBook();
		System.out.println(s.solution(phone_book));
	}
	
	public boolean solution(String[] phone_book) {
		boolean answer = true;
        for(int i=0; i<phone_book.length; i++) {
			for(int j=0; j<phone_book.length; j++) {
				if(i!=j && phone_book[j].contains(phone_book[i])) answer = false;
				else continue;
			}
		}
        return answer;
	}
}*/

// ver2 : 실패
// 효율성 테스트 1-4 실패 (정확성 테스트는 모두 통과)
/*
public class PhoneBook {
	public static void main(String args[]) {
		String[] phone_book = {"442", "97674223", "1195524421"};
		PhoneBook s = new PhoneBook();
		System.out.println(s.solution(phone_book));
	}
	
	public boolean solution(String[] phone_book) {
		boolean answer = true;
		for(int i=0; i<phone_book.length; i++) {
			for(int j=0; j<phone_book.length; j++) {
				int comparison = phone_book[j].indexOf(phone_book[i]);
				if(i!=j && comparison==0) {
					answer = false;
				}
				else continue;
			}
		}
		return answer;
	}
}*/

// ver3 : 성공
// 효율성을 위해 sort를 먼저 해줌

public class PhoneBook {
	public static void main(String args[]) {
		String[] phone_book = {"442", "97674223", "1195524421"};
		PhoneBook s = new PhoneBook();
		System.out.println(s.solution(phone_book));
	}
	
	public boolean solution(String[] phone_book) {
		boolean answer = true;
		Arrays.sort(phone_book);
		for(int i=1; i<phone_book.length; i++) {
            int comparison = phone_book[i].indexOf(phone_book[i-1]);
            if(comparison==0) {
                answer = false;
            }
            else continue;
		}
		return answer;
	}
}
