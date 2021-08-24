// ��ǥ : ��ü����� ���� ���ؿ� Map �������̽��� ���

package MapTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	
	static void printMap(Map<String, Integer> map) {
        for(String key : map.keySet()) {
           System.out.println(key+":"+map.get(key));
        }
        /*  // Ŭ���� �޼��� ���� �ν��Ͻ� �޼��� ȣ��
        Solution s1 = new Solution();
        s1.solution("�Ͽ�");
        */
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("�ȳ��ϼ���");
		s.solution("one4seveneight");
		
		HashMap<String, Integer> hashmap = new HashMap<>(); // Ư�� ��Ģ���� ���
        hashmap.put("One", 1);
        hashmap.put("Two", 2);
        hashmap.put("Three", 3);
        printMap(hashmap);
        TreeMap<String, Integer> tmap = new TreeMap<>(hashmap); // Ű ���� ���ĺ� ������ ����
        printMap(tmap);
        LinkedHashMap<String, Integer> lmap = new LinkedHashMap<>(hashmap); // Ű ���� �Է� ������� ��� (��ũ�帮��Ʈ�� ����)
        printMap(lmap);
	}
	
	public int solution(String s) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();  
        // map : key,value ������ �����͸� ������ �� �ִ� �������̽� / HashMap : map�� ����ϱ� ���� ���� Ŭ����
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println(map);
        Map<String, Integer> tmap = new TreeMap<>(map);
        System.out.println(tmap);
        Map<String, Integer> lmap = new LinkedHashMap<>(map);
        System.out.println(lmap);
        solution1("hello");
        System.out.println(s);
        return answer;
    }
	
	public int solution1(String s) {
        int answer = 0;
        System.out.println("����");
        return answer;
    }
}


// public , static, private ����
/*
public : ���� ������ ���� , but �ٸ� Ŭ���� ������ ��ü�� ������ �� ��� ����
protected : ������ ��Ű�� ���� �����ϰų� ��ӹ��� ���� Ŭ�������� ����� �� ����
default : ������ ��Ű�� ������ ���� ����
private : �ڱ� �ڽ��� Ŭ������������ ������ ���� (private ������ ����� Ŭ����)
static : "����"�� �ǹ̸� ��� Ű����, Ŭ������ �ν��Ͻ�ȭ �Ǿ����� �ʴ��� ���� ���� (��ü ��� ���� ����)

Ŭ���� ���� : ���� ��ü���� ��ü �ۿ����� ���� ������ ���� (ex, static int count =0)
�ν��Ͻ� ���� : ��ü ���������� �� �� �ִ� ��ü ������ ����
*/