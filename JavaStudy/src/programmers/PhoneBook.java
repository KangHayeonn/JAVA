// ��ȭ��ȣ ��� ( ���α׷��ӽ� LEVEL2 )

package programmers;

import java.util.Arrays;

// ver1 : ����
// ��Ȯ�� �׽�Ʈ 1,5,13,14 & ȿ���� �׽�Ʈ �׽�Ʈ 1-4 ����
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

// ver2 : ����
// ȿ���� �׽�Ʈ 1-4 ���� (��Ȯ�� �׽�Ʈ�� ��� ���)
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

// ver3 : ����
// ȿ������ ���� sort�� ���� ����

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
