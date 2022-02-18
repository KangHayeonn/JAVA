// �Ű� ��� �ޱ� (���α׷��ӽ� LEVEL1 - Kakao)

/* [ �˰��� ]
 * 
 * 1. report�� �Է¹��� �� �ߺ��� �����ϰ� �迭�� ����
 * 2. id_list�� key, value�� �����ϱ� ���� map�� ���
 * 3. 1���� �ݺ����� ���鼭 �ش� report�� ī��Ʈ �ؼ� map�� ����
 * 4. value�߿� k�� �̻��� ���� ���� ���ο� �迭�� ����
 * 
 */
package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReportResult {
	public static void main(String args[]) {
		// String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		// String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
		
		String[] id_list = {"con", "ryan"};
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
		
		int k = 2;
		
		System.out.println(solution(id_list, report, k));
	}
	
	public static int[] solution(String[] id_list, String[] report, int k) {
		
		// �迭 -> Set (�ߺ� ����)
		Set<String> set = new HashSet<String>(Arrays.asList(report));
		System.out.println(set);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Integer> mail = new HashMap<String, Integer>();
		
		for(int i=0; i<id_list.length; i++) {
			String str = id_list[i];
			map.put(str, 0);
			mail.put(str, 0);
		}
		
		for(String r: set) {
			String[] rList = r.split(" ");
			String report_id = rList[1];
			
			System.out.println("value : " + map.get(report_id));
			map.put(report_id, map.get(report_id)+1);
		}
		
		for(String key : map.keySet()) {
			if(map.get(key) >= k ) {
				for(String s : set) {
					String[] sList = s.split(" ");
					String user_id = sList[0];
					String report_id = sList[1];
					
					if(key.equals(report_id)) {
						mail.put(user_id,mail.get(user_id)+1);
					}
 				}
			}
			System.out.println("key : " + key + " value : " + map.get(key));
		}
		
		int[] answer = new int[mail.size()];
		int i = 0;
		for(String s : mail.keySet()) {
			answer[i] = mail.get(s);
			i++;
		}
		
		System.out.println(Arrays.toString(answer));
		
		return answer;
	}
}
