// ���̼� ���� (���� 10814��)

package baekjoon;

// ver1 : Map�� �̿��� ���� -> ������ �̸�, ���̸� key�� �������� ���� ������
// ����

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.LinkedList;
import java.util.List;

public class Q10814 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = Integer.parseInt(br.readLine());
		int i=0;
		
		Map<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
		// ���� Map ������ ���ÿ� ���� �μ��� ������ �� Map���� ����ϸ� ����
	
		while(i < count) {
			String str = br.readLine();
			String[] parts = str.split(" ");
			int age = Integer.valueOf(parts[0]);
			String name = parts[1];
			linkedHashMap.put(name, age); // map key �ߺ� �ȵ�
			i++;
		}
		
		Map<String, Integer> result = sortMapMyKey(linkedHashMap);
		for(Map.Entry<String, Integer> entry : result.entrySet()) {
			System.out.println(entry.getValue()+ " "+ entry.getKey());
		}
		/*
		for(Entry<Integer, String> entrySet: linkedHashMap.entrySet()) {
			int Min = entrySet.getKey();
			int idx = entrySet.indexOf.getKey();
			for(Entry<Integer, String> entrySet2: linkedHashMap.entrySet()) {
				if(Min)
			}
			System.out.println(entrySet.getKey()+" : "+entrySet.getValue());
		}
		
	}
	public static LinkedHashMap<String, Integer> sortMapMyKey(Map<String, Integer> map){
		List<Map.Entry<String, Integer>> entries = new LinkedList<>(map.entrySet());
		Collections.sort(entries, (a,b) -> a.getValue().compareTo(b.getValue())); 
		// ���ذ�.compareTo(�񱳰�) -> ���ذ��� �� ������ ����
		
		LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
		for(Entry<String, Integer> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}*/

// ver2 : �迭 �̿�
// ���ڷ� ��ȯ�ϴ� ���� ���� -> �ð� �ʰ�
// ����
/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q10814 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		String[][] arr = new String[count][3];
		int i=0;

		while(i<count) {
			String str = br.readLine();
			String parts[] = str.split(" ");
			arr[i][0] = parts[0]; //age
			arr[i][1] = parts[1]; //name
			arr[i][2] = String.valueOf(i);
			i++;
		}
		
		for(int j=0; j<arr.length; j++) {
			int Min = Integer.valueOf(arr[j][0]);
			int idx = j;
			int enter = Integer.valueOf(arr[j][2]);
			
			for(int k=j+1; k<arr.length; k++) {
				if(Min > Integer.valueOf(arr[k][0])) {
					Min = Integer.valueOf(arr[k][0]);
					idx = k;
				}
				else if(Min == Integer.valueOf(arr[k][0]) && enter > Integer.valueOf(arr[k][2])) {
					idx = k;
				}
			}
			
			if(idx != j) {
				String a = String.valueOf(arr[j][0]);
				String b = arr[j][1];
				String c = String.valueOf(arr[j][2]);
				arr[j][0] = arr[idx][0];
				arr[j][1] = arr[idx][1];
				arr[j][2] = arr[idx][2];
				arr[idx][0] = a;
				arr[idx][1] = b;
				arr[idx][2] = c;
			}
		}
		for(int z = 0; z < count; z++) {
			System.out.println(arr[z][0] + " " + arr[z][1]);
		}
	}
}*/

// ver2 : �迭�� compareTo �̿�
/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

public class Q10814 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		String[][] arr = new String[count][3];
		int i=0;

		while(i<count) {
			String str = br.readLine();
			String parts[] = str.split(" ");
			arr[i][0] = parts[0]; //age
			arr[i][1] = parts[1]; //name
			arr[i][2] = String.valueOf(i);
			i++;
		}
		
		Arrays.sort(arr, (a, b)-> a[0].compareTo(b[0]));
		/*
		for(int j=0; j<arr.length; j++) {
			String Min = arr[j][0];
			int idx = j;
			String enter = arr[j][2];
			
			for(int k=j+1; k<arr.length; k++) {
				if(Min.compareTo(arr[k][0]) > 0) {
					Min = arr[k][0];
					idx = k;
				}
				else if(Min.compareTo(arr[k][0]) ==0 && enter.compareTo(arr[k][2])>0) {
					idx = k;
				}
			}
			
			if(idx != j) {
				String a = arr[j][0];
				String b = arr[j][1];
				String c = arr[j][2];
				arr[j][0] = arr[idx][0];
				arr[j][1] = arr[idx][1];
				arr[j][2] = arr[idx][2];
				arr[idx][0] = a;
				arr[idx][1] = b;
				arr[idx][2] = c;
			}
		}
		for(int z = 0; z < count; z++) {
			System.out.println(arr[z][0] + " " + arr[z][1]);
		}
	}
}*/

// ver3 : ����
// ���ٽ�, comparator || comparable �̿�
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.io.IOException;

public class Q10814 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		String[][] arr = new String[count][2];
		int i=0;

		while(i<count) {
			String str = br.readLine();
			String parts[] = str.split(" ");
			arr[i][0] = parts[0]; //age
			arr[i][1] = parts[1]; //name
			i++;
		}
		
		Arrays.sort(arr, (a, b)-> Integer.valueOf(a[0]).compareTo(Integer.valueOf(b[0])));
		//Arrays.sort(arr, (a, b)-> Integer.parseInt(a[0]) - Integer.parseInt(b[0]));
		/*
		Arrays.sort(arr, new Comparator<String[]>() {	
	          @Override
	          public int compare(String[] s1, String[] s2) {
	            return Integer.parseInt(s1[0]) - Integer.parseInt(s2[0]);
	          }
	    });*/
		for(int z = 0; z < count; z++) {
			System.out.println(arr[z][0] + " " + arr[z][1]);
		}
	}
}