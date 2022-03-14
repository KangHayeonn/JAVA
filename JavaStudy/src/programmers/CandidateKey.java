// �ĺ�Ű (���α׷��ӽ� LEVEL2 - Kakao)

/* [ �˰��� ]
 * 
 * 1. ��ü ���տ��� ���ϼ��� �����ϴ� �ֵ��� �������� map�� ���� (key: �Ӽ� �ε���, value: true/false) - ���ϼ� �����ϸ� true
 * 2. ���ϼ��� �����ϴ� key �� �������� ������ ���� �ּҼ��� üũ
 * 3. 2���� ���� key���� �׿� �ش��ϴ� ���հ�(key)�� �̹� true�̸� �ش� �κ��� �ּҼ��� �������� �����Ƿ� false�� ����
 * 4. ���� map���� value�� true�� key���� count�ؼ� answer�� ��� (���ϼ� & �ּҼ� ��� ����) 
 * 
 */

package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CandidateKey {
	static String[][] relation2;
	static Map<String, Boolean> map;
	
	public static void main(String args[]) {
		// String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","3"}};
		String[][] relation = {{"a","1","aaa","c","ng"},{"b","1","bbb","c","g"},{"c","1","aaa","d","ng"},{"d","2","bbb","d","ng"}};
		
		System.out.println(solution(relation));
	}
	public static int solution(String[][] relation) {
		int len = relation[0].length;
		int answer = 0;
		
		int[] numArr = new int[len];
		relation2 = new String[relation.length][len];

		map = new HashMap<>();
		
		long before = System.currentTimeMillis(); //�ڵ� ���� �� �ð�
		
		// Garbage Collection���� �޸� �ʱ�ȭ
        System.gc();
		
		// ������ �޸� ��뷮 ��ȸ
        long before2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Used Memory : " + before);
		
		for(int i=1; i<=len; i++) {
			numArr[i-1] = i;
		}
		
		// ���� ����
		for(int i=0; i<relation.length; i++) {
			for(int j=0; j<relation[0].length; j++) {
				relation2[i][j] = relation[i][j];
			}
		}
		
		for(int i=1; i<=len; i++) {
			boolean[] visited = new boolean[len];
			combi(numArr, visited, 0, len, i);	
		}
		
		for(String key : map.keySet()) {
			if(map.get(key)) {
				for(int i=1; i<key.length(); i++) {
					boolean[] visited = new boolean[len];
					combination(key, visited, 0, key.length(), i);
				}
			}
		}
		
		for(String key : map.keySet()) {
			if(map.get(key)) {
				answer++;
			}
		}
		
		long after = System.currentTimeMillis(); // �ڵ� ���� �� �ð�
        long diff = (after - before); //�� �ð��� ����
        System.out.println("�ð�����(�и�������) : "+diff);
        
        // Garbage Collection���� �޸� ����
        System.gc();
        
        // ���� �� �޸� ��뷮 ��ȸ
        long after2  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        
        // �޸� ��뷮 ����
        long usedMemory = (before2 - after2)/1024;
        
        System.out.println("Used Memory(MB): " + usedMemory);

		return answer;
	}
	
	// ����
	public static void combi(int[] arr, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			String s = "";
			for(int i=0; i<n; i++) {
	    		if (visited[i]) {
	    			s += i;
	    		}
	    	}
	    	map.put(s, false);
	    	candidateKeyCheck(arr, visited, n);
	        return;
	    }
		

	    for(int i=start; i<n; i++) {
    		visited[i] = true;
    		combi(arr, visited, i + 1, n, r - 1);
    		visited[i] = false;
	    }
	}
	
	// ���ڿ� ����
	public static void combination(String str, boolean[] visited, int start, int n, int r) {
		if(r==0) {
			String s = "";
			for (int i = 0; i < n; i++) {
	            if (visited[i]) {
	                s += str.charAt(i);
	            }
	        }
			if(map.get(s)) { // �ּҼ�
				map.put(str, false);
				return;
			}
			return;
		}
		
		for(int i=start; i<n; i++) {
			visited[i] = true;
			combination(str, visited, i+1, n, r-1);
			visited[i] = false;
		}
	}
	
	// �ĺ�Ű üũ
    static void candidateKeyCheck(int[] arr, boolean[] visited, int n) {
    	Set<String> set = new HashSet<>();
    	Queue<Integer> q = new LinkedList<>();
    	
    	for (int i = 0; i < n; i++) {
            if (visited[i]) {
            	q.add(i);
            }
        }
    	
    	for(int j=0; j<relation2.length; j++) {
    		String str = "";
        	for (int i = 0; i < n; i++) {
                if (visited[i] && !map.get(Integer.toString(i))) {
                	str += relation2[j][i];
                }
            }
        	set.add(str);
        }
    	
    	if(set.size() == relation2.length) { // ���ϼ�
    		String key = "";
    		
    		while(!q.isEmpty()) {
    			int num = q.poll();
    			key += num;
    		}
    		
    		map.put(key, true);
    	}
    }
}
