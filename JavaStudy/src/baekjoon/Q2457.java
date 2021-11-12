// 공주님의 정원 (백준 2457번)

/* [ 알고리즘 ]
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q2457 {
	public static class type {
		private int start_month;
		private int start_day;
		private int end_month;
		private int end_day;
		
		public type(int start_month, int start_day, int end_month, int end_day) {
			this.start_month = start_month;
			this.start_day = start_day;
			this.end_month = end_month;
			this.end_day = end_day;
		}
	}
	public static void main (String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		ArrayList<type> arr = new ArrayList<>();
		
		int StartMonth = 3;
		int StartDay = 1;
		//int EndMonth = 11;
		//int EndDay =30;
		
		for(int i=0 ; i<count; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr.add(new type(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(arr, new Comparator<type>() {
			@Override
			public int compare(type s1, type s2) {
				if(s1.start_month == s2.start_month) {
					if(s1.start_day== s2.start_day) {
						if(s1.end_month==s2.end_month) {
							return s1.end_day - s2.end_day;
						}
						else return s1.end_month - s2.end_month;
					}
					else return s1.start_day - s2.start_day;
				}
				else return s1.start_month - s2.start_month;
			}
		});
		
		int C_Month = 0;
		int C_Day = 0;
		
		int idx =0;
		int answer =0;
		
		boolean check = false;
		
		for(int i=0; i<arr.size(); i++) {
			//System.out.println(arr.get(i).start_month + " " + arr.get(i).start_day + " " + arr.get(i).end_month +" " + arr.get(i).end_day);
			if(arr.get(i).start_month < StartMonth) {
				if(C_Month < arr.get(i).end_month) {
					C_Month = arr.get(i).end_month; // 3
					C_Day = arr.get(i).end_day; // 23
					check =true;
				} else if(C_Month == arr.get(i).end_month) {
					if(C_Day < arr.get(i).end_day) {
						C_Month = arr.get(i).end_month; // 3
						C_Day = arr.get(i).end_day; // 23
						check =true;
					} else continue;
				} else continue;
			} else if (arr.get(i).start_month == StartMonth && arr.get(i).start_day == StartDay) {
				if(C_Month < arr.get(i).end_month) {
					C_Month = arr.get(i).end_month;
					C_Day = arr.get(i).end_day;
					check =true;
				} else if(C_Month == arr.get(i).end_month) {
					if(C_Day < arr.get(i).end_day) {
						C_Month = arr.get(i).end_month; 
						C_Day = arr.get(i).end_day;
						check =true;
					} else continue;
				} else continue;
			} else {
				if(!check) {
					System.out.print(0);
					System.exit(0);
				}
				answer++;
				idx = i;
				break;
			}
		}

		StartMonth = C_Month; // 4 
		StartDay = C_Day; // 25
		
		//System.out.println("Check : " + C_Month + " : " + C_Day + " "  +answer);
		
		//Comparison(arr, idx, StartMonth, StartDay, C_Month, C_Day, answer);
		
		System.out.print(Comparison(arr, idx, StartMonth, StartDay, C_Month, C_Day, answer));
	}
	
	public static int Comparison(ArrayList<type>arr, int idx, int StartMonth, int StartDay, int C_Month, int C_Day, int answer) {
		//System.out.println("몇번 들어오는지? " +" idx : "+ idx + " answer : " + answer); 
		if(idx>=arr.size()) return answer;
		else {
			if(C_Month > 11) return answer;
		}

		
		boolean check = false;
		
		//System.out.println("Check : " + C_Month + " : " + C_Day + " "  +answer);
		
		for(int i=idx; i<arr.size(); i++) {
			if(arr.get(i).start_month < StartMonth) {
				if(C_Month < arr.get(i).end_month) {
					C_Month = arr.get(i).end_month; 
					C_Day = arr.get(i).end_day;
					check = true;
				} else if(C_Month == arr.get(i).end_month) {
					if(C_Day < arr.get(i).end_day) {
						C_Month = arr.get(i).end_month; 
						C_Day = arr.get(i).end_day;
						check = true;
					} //else if(C_Day == arr.get(i).end_day) {
						//check = true;
						//answer = answer-1;
					//} 
					else continue;
				} else continue;
			} else if (arr.get(i).start_month == StartMonth && arr.get(i).start_day <= StartDay) {  // 6 15
				if(C_Month < arr.get(i).end_month) { 
					C_Month = arr.get(i).end_month; 
					C_Day = arr.get(i).end_day;
					check = true;
				} else if(C_Month == arr.get(i).end_month) {
					if(C_Day <= arr.get(i).end_day) {
						C_Month = arr.get(i).end_month; 
						C_Day = arr.get(i).end_day;
						check = true;
					} else continue;
				} else continue;
			} else {
				if(!check) {
					return 0;
				}
				idx = i;
				break;
			}
		}
	
		
		StartMonth = C_Month; // 4 
		StartDay = C_Day; // 25
		
		return Comparison(arr, idx, StartMonth, StartDay, C_Month, C_Day, answer+1);
	}
}
