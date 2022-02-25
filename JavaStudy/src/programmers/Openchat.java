// 오픈채팅방 (프로그래머스 LEVEL2 - Kakao)

// Hashmap 이용

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Openchat {
	public static class type {
		private String userId;
		private String comment;
		
		public type(String userId, String comment) {
			this.userId = userId;
			this.comment = comment;
		}
	}
	public static void main(String args[]) {
		String[] record = {
			"Enter uid1234 Muzi", 
			"Enter uid4567 Prodo",
			"Leave uid1234","Enter uid1234 Prodo",
			"Change uid4567 Ryan"	
		};
		System.out.println(Arrays.toString(solution(record)));
	}
	public static String[] solution(String[] record) {
		Map<String, String> users = new HashMap<>();
		ArrayList<type> answer = new ArrayList<>();
		
		for(int i=0; i<record.length; i++) {
			String[] str = record[i].split(" "); // 띄어쓰기를 기준으로 문자열을 배열로 변환
			
			if(str[0].equals("Enter")) {
				users.put(str[1], str[2]);
				answer.add(new type(str[1], "님이 들어왔습니다."));
			} else if(str[0].equals("Leave")) {
				answer.add(new type(str[1], "님이 나갔습니다."));
			} else {
				users.put(str[1], str[2]);
			}
		}
		
		ArrayList<String> list = new ArrayList<>();
		
		for(type s : answer) {
			list.add(users.get(s.userId)+s.comment);
		}
		
		String[] result = list.toArray(new String[0]);
		
		return result;
	}
}
