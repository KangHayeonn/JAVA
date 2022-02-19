// 신고 결과 받기 (프로그래머스 LEVEL1 - Kakao)

/* [ 알고리즘 ]
 * 
 * 1. report를 입력받을 때 중복을 제거하고 배열에 저장
 * 2. report를 카운트 해서 해당 value를 map에 저장
 * 3. 2번의 value중 k를 넘지 않는 것들을 제외하고 정답배열에 +1 해줌
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
		
		// 배열 -> Set (중복 제거)
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
