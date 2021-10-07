// ½ÎÀÌ¹ö°³°­ÃÑÈ¸ (¹éÁØ 19583¹ø)
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q19583 {
	public static void main(String args[]) throws IOException {
		
		//String[] parts = s1.split(":");
		//int Hour = Integer.valueOf(parts[0]);
		//int Minute = Integer.valueOf(parts[1]);
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer s = new StringTokenizer(bf.readLine());
		String S = s.nextToken();
		String E = s.nextToken();
		String Q = s.nextToken();
		
		int S_Hour = Integer.valueOf(S.split(":")[0]);
		int S_Minute = Integer.valueOf(S.split(":")[1]);
		int E_Hour = Integer.valueOf(E.split(":")[0]);
		int E_Minute = Integer.valueOf(E.split(":")[1]);
		int Q_Hour = Integer.valueOf(Q.split(":")[0]);
		int Q_Minute = Integer.valueOf(Q.split(":")[1]);
		
		System.out.println(S_Hour + " "+ S_Minute);
		String str = null;
		HashMap <String, Integer> hashmap = new HashMap<String, Integer>();
		
		while((str = bf.readLine())!=null) {
			String[] parts = str.split(" ");
			int Hour = Integer.valueOf(parts[0].split(":")[0]);
			int Minute = Integer.valueOf(parts[0].split(":")[1]);

			String name = parts[1];
			System.out.println(Hour+"//"+Minute+ ":"+name);
			
			if(Hour<S_Hour) hashmap.put(name, 1);
			else if(Hour==S_Hour && Minute<=S_Minute) hashmap.put(name, 1);
			
			if(Hour>E_Hour) {
				if(Hour>Q_Hour) continue;
				else if(Hour==Q_Hour && Minute>Q_Minute) continue;
				else {
					if(hashmap.containsKey(name)){
		                hashmap.replace(name, 2);
		            }
				}
			} else if(Hour==E_Hour && Minute>=E_Minute){
				if(Hour==Q_Hour && Minute>Q_Minute) continue;
				else {
					if(hashmap.containsKey(name)){
		                hashmap.replace(name, 2);
		            }
				}
			}
			
		}
		
		bf.close();
		for(String key : hashmap.keySet()) {
			System.out.println(key + ":" + hashmap.get(key));
		}
		System.out.println("123");
		
	}
}
