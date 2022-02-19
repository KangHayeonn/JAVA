// �Ű� ��� �ޱ� (���α׷��ӽ� LEVEL1 - Kakao)

/* [ �˰��� ]
 * 
 * 1. report�� �Է¹��� �� �ߺ��� �����ϰ� �迭�� ����
 * 2. report�� ī��Ʈ �ؼ� �ش� value�� map�� ����
 * 3. 2���� value�� k�� ���� �ʴ� �͵��� �����ϰ� ����迭�� +1 ����
 * 
 */
package programmers;

import java.util.ArrayList;
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
		
		int[] answer = new int[id_list.length];
		
		// �迭 -> Set (�ߺ� ����)
		Set<String> set = new HashSet<String>(Arrays.asList(report));	
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(String s : set) {
			String[] sList = s.split(" ");
			String report_id = sList[1];
			
			if(map.containsKey(report_id)) {
				map.put(report_id, map.get(report_id)+1);
			} else {
				map.put(report_id, 1);
			}
		}
		
		ArrayList<String> arr = new ArrayList<>();

		for(String s : map.keySet()) {
			if(map.get(s) < k) {
				arr.add(s);
			}
		}

		for(String s  : set) {
			String[] sList = s.split(" ");
			String user_id = sList[0];
			String report_id = sList[1];
			
			if(arr.contains(report_id)) continue;
			
			for(int i=0; i<id_list.length; i++) {
				if(user_id.equals(id_list[i])) {
					answer[i] += 1;
				}
			}
		}
		
		return answer;
	}
}
