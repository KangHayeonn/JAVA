// ����ä�ù� (���α׷��ӽ� LEVEL2)
package programmers;

import java.util.*;

public class Openchat_v2 {
	public static void main(String args[]) {
		Openchat_v2 v = new Openchat_v2();
		String[] record = {
				"Enter uid1234 Muzi", 
				"Enter uid4567 Prodo",
				"Leave uid1234",
				"Enter uid1234 Prodo",
				"Change uid4567 Ryan"
		};
		
		System.out.println(Arrays.toString(v.solution(record)));
	}
	public String[] solution(String[] record) {
		Map<String, String> map = new HashMap<>();
		ArrayList<Type> log = new ArrayList<>();
		
		String[] strArr;
		String input, id, nickname;
		for(int i=0; i<record.length; i++) {
			strArr = record[i].split(" ");
			input = strArr[0];
			id = strArr[1];
			nickname = strArr.length > 2 ? strArr[2] : "";
			
			if("Enter".equals(input)) {
				map.put(id, nickname);
				log.add(new Type(id, 1));
			} else if("Leave".equals(input)) {
				log.add(new Type(id, -1));
			} else {
				map.put(id, nickname);
			}
		}
		
		String[] answer = new String[log.size()];
		String output;
		for(int i=0; i<log.size(); i++) {
			Type t = log.get(i);
			
			if(t.input == 1) {
				output = "���� ���Խ��ϴ�.";
			} else {
				output = "���� �������ϴ�.";
			}
			
			answer[i] = map.get(t.id) + output;
		}
		
		return answer; 
	}
	public class Type {
		String id; 
		int input; // 1 : Enter, -1 : Leave
		
		public Type(String id, int input) {
			this.id = id;
			this.input = input;
		}
	}
}
