package UteCo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class test3 {
	public static void main(String args[]) {
		String[] ings = {"x 25", "y 20", "z 1000"};
		String[] menu = {"AAAA xyxy 15", "TTT yy 30", "BBBB xx 30"};
		String[] sell = {"BBBB 3", "TTT 2"};
		
		System.out.print(solution(ings, menu, sell));
	}
	
	public static class type {
		private String MENU_NAME; // 판매된 메뉴
		private int SELL_COUNT;   // 판매된 수량
		private String ING_LIST;  // 재료
		private int MENU_PRICE;   // 판매가
		
		
		public type (String MENU_NAME, int SELL_COUNT, String ING_LIST, int MENU_PRICE) {
			this.MENU_NAME = MENU_NAME;
			this.SELL_COUNT = SELL_COUNT;
			this.ING_LIST = ING_LIST;
			this.MENU_PRICE = MENU_PRICE;
		}
		
		public String toString() {
			return MENU_NAME + " : " + SELL_COUNT + " : " + ING_LIST + " : " + MENU_PRICE;
			//return value + "";
		}
	}
	
	public static class ings_type {
		private char ING_NAME; // 재료: 알파벳 소문자 하나
		private int ING_PRICE; // 재료가격
		
		public ings_type (char ING_NAME, int ING_PRICE) {
			this.ING_NAME = ING_NAME;
			this.ING_PRICE = ING_PRICE;
		}
		
		public String toString() {
			return ING_NAME + " : " + ING_PRICE;
		}
	}
	
	public static int solution(String[] ings, String[] menu, String[] sell) {
		ArrayList<ings_type> arr = new ArrayList<>();
		int i, j, sum;
		int answer = 0;
		
		Arrays.sort(ings);
		
		for(i=0; i<ings.length; i++) {
			String[] parts = ings[i].split(" ");
			arr.add(new ings_type(parts[0].charAt(0), Integer.parseInt(parts[1])));
		}/*
		for(int idx=0; idx<arr.size(); idx++) {
			System.out.println(arr.get(idx));
		}*/
		
		Arrays.sort(menu);
		Arrays.sort(sell);
		
		ArrayList<type> menu_sell = new ArrayList<>();
		
		for(i=0, j=0; i<menu.length; i++) {
			String[] menu_parts = menu[i].split(" ");
			String[] sell_parts = sell[j].split(" ");
			
			if(menu_parts[0].equals(sell_parts[0])) {
				menu_sell.add(new type(menu_parts[0], Integer.parseInt(sell_parts[1]), menu_parts[1], Integer.parseInt(menu_parts[2])));
				j++;
				if(j==sell.length) break;
			}
		}
		/*
		for(int idx=0; idx<menu_sell.size(); idx++) {
			for(int k=0; k<menu_sell.get(idx).ING_LIST.length(); k++) {
				switch(menu_sell.get(idx).ING_LIST.charAt(k)) {
				case 
				}
			}
			System.out.println(menu_sell.get(idx));
		}*/
		Map<Character, Integer> map;
		for(int idx=0; idx<menu_sell.size(); idx++) {
			map = new HashMap<>();
			sum =0;
			
			for(int k=0; k<menu_sell.get(idx).ING_LIST.length(); k++) {
				int value = map.containsKey(menu_sell.get(idx).ING_LIST.charAt(k)) ? map.get(menu_sell.get(idx).ING_LIST.charAt(k)) : 0;
				map.put(menu_sell.get(idx).ING_LIST.charAt(k), value + 1);
			}
			for(Entry<Character, Integer> entrySet : map.entrySet()) {
				for(int r=0; r<arr.size(); r++) {
					if(arr.get(r).ING_NAME == entrySet.getKey()) sum += arr.get(r).ING_PRICE * entrySet.getValue();
					//System.out.println(arr.get(idx));
				}
				//System.out.println(entrySet.getKey() + " " +entrySet.getValue());
			}
			answer += (menu_sell.get(idx).MENU_PRICE - sum) * menu_sell.get(idx).SELL_COUNT;
			//System.out.println("//");
		}
		
		//System.out.println(Arrays.toString(menu));
		//System.out.println(Arrays.toString(sell));
		//System.out.println(Arrays.deepToString(arr));
		
		return answer;
	}
}
