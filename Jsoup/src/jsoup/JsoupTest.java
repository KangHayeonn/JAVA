package jsoup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
	public static void main(String[] args) {
		
		Document doc = null;
		Elements elem = new Elements();
		Element text;
		String URL = "https://olympics.com/tokyo-2020/ko/";
		
		try {
			Connection connection = Jsoup.connect(URL);
					/*.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
					.header("Accept", "text/html")
	                .header("Accept-Encoding", "gzip,deflate")
	                .header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
	                .header("Connection", "keep-alive")
	                .ignoreContentType(true)
	                .timeout(30000);*/
			doc = connection.get();
			String folder = doc.title();
			/*elem = doc.select(".tk-homepage__slider");
			
			//text = elem.select(".tk-homepage__slider-main-card").get(0);
			text = elem.select("a").get(1);
			System.out.println(text.attr("href"));*/
			
			elem = doc.select(".tk-homepage__slider");
			text = elem.select("img").get(1);
			System.out.println(text.attr("src"));
			System.out.println(text.attr("alt"));
			String url1 = text.getElementsByAttribute("src").attr("src");
			
			
			try {
				URL imgUrl = new URL(url1);
				BufferedImage jpg = ImageIO.read(imgUrl);
				File file = new File("C:\\Users\\user\\eclipse-workspace\\Jsoup\\img\\"+"0"+".jpg");
				ImageIO.write(jpg, "jpg", file);
			
			} catch(IOException e1) {
				e1.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	
		//String url = "http://www.cgv.co.kr/movies/";
		/*
		String url = "https://search.naver.com/";
		Document doc = null;
	
		//int page=0;
		int page=0;
		
		try {
			doc = Jsoup.connect(url).get();
			System.out.println("되나?");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		String folder = doc.title();
		System.out.println("오마이갓"+folder);
		//Element element = doc.select("div.box-image").get(6);
		Element element;
		element = doc.select("div.thumb_area").get(0);
		Elements img = element.select("img");
		for(Element e: img) {
			String url1 = e.getElementsByAttribute("src").attr("src");
			
			
			try {
				URL imgUrl = new URL(url1);
				BufferedImage jpg = ImageIO.read(imgUrl);
				File file = new File("C:\\Users\\user\\photo"+folder+"\\"+page+".jpg");
				ImageIO.write(jpg, "jpg", file);
			
			} catch(IOException e1) {
				e1.printStackTrace();
			}
			page +=1;
		}
		*/
		/*
		for(int i=0; i<7; i++) {
			page=i;
			switch(i) {
			
				case 0:
					//element = doc.select("div.box-image").get(0);
					element = doc.select("div.style-scope ytd-video-renderer").get(0);
					Elements href = element.select("a");
					for(Element e22: href) {
						
						String content = e22.getElementsByAttribute("href").attr("href");
						String text = content.toString();
						System.out.println(text);
					}
					Elements img = element.select("img");
					for(Element e: img) {
						String url1 = e.getElementsByAttribute("src").attr("src");
						
						
						try {
							URL imgUrl = new URL(url1);
							BufferedImage jpg = ImageIO.read(imgUrl);
							File file = new File("C:\\Users\\user\\photo"+folder+"\\"+page+".jpg");
							ImageIO.write(jpg, "jpg", file);
						
						} catch(IOException e1) {
							e1.printStackTrace();
						}
						page +=1;
					}
				case 1:
					element = doc.select("div.style-scope ytd-video-renderer").get(1);
					Elements img1 = element.select("img");
					for(Element e: img1) {
						String url1 = e.getElementsByAttribute("src").attr("src");
						
						
						try {
							URL imgUrl = new URL(url1);
							BufferedImage jpg = ImageIO.read(imgUrl);
							File file = new File("C:\\Users\\user\\photo"+folder+"\\"+page+".jpg");
							ImageIO.write(jpg, "jpg", file);
						
						} catch(IOException e1) {
							e1.printStackTrace();
						}
						page +=1;
					}
				case 2:
					element = doc.select("div.style-scope ytd-video-renderer").get(2);
					Elements img2 = element.select("img");
					for(Element e: img2) {
						String url1 = e.getElementsByAttribute("src").attr("src");
						
						
						try {
							URL imgUrl = new URL(url1);
							BufferedImage jpg = ImageIO.read(imgUrl);
							File file = new File("C:\\Users\\user\\photo"+folder+"\\"+page+".jpg");
							ImageIO.write(jpg, "jpg", file);
						
						} catch(IOException e1) {
							e1.printStackTrace();
						}
						page +=1;
					}
				case 3:
					element = doc.select("div.style-scope ytd-video-renderer").get(3);
					Elements img3 = element.select("img");
					for(Element e: img3) {
						String url1 = e.getElementsByAttribute("src").attr("src");
						
						
						try {
							URL imgUrl = new URL(url1);
							BufferedImage jpg = ImageIO.read(imgUrl);
							File file = new File("C:\\Users\\user\\photo"+folder+"\\"+page+".jpg");
							ImageIO.write(jpg, "jpg", file);
						
						} catch(IOException e1) {
							e1.printStackTrace();
						}
						page +=1;
					}
				case 4:
					element = doc.select("div.style-scope ytd-video-renderer").get(4);
					Elements img4 = element.select("img");
					for(Element e: img4) {
						String url1 = e.getElementsByAttribute("src").attr("src");
						
						
						try {
							URL imgUrl = new URL(url1);
							BufferedImage jpg = ImageIO.read(imgUrl);
							File file = new File("C:\\Users\\user\\photo"+folder+"\\"+page+".jpg");
							ImageIO.write(jpg, "jpg", file);
						
						} catch(IOException e1) {
							e1.printStackTrace();
						}
						page +=1;
					}
				case 5:
					element = doc.select("div.style-scope ytd-video-renderer").get(5);
					Elements img5 = element.select("img");
					for(Element e: img5) {
						String url1 = e.getElementsByAttribute("src").attr("src");
						
						
						try {
							URL imgUrl = new URL(url1);
							BufferedImage jpg = ImageIO.read(imgUrl);
							File file = new File("C:\\Users\\user\\photo"+folder+"\\"+page+".jpg");
							ImageIO.write(jpg, "jpg", file);
						
						} catch(IOException e1) {
							e1.printStackTrace();
						}
						page +=1;
					}
				case 6:
					element = doc.select("div.style-scope ytd-video-renderer").get(6);
					Elements img6 = element.select("img");
					for(Element e: img6) {
						String url1 = e.getElementsByAttribute("src").attr("src");
						
						
						try {
							URL imgUrl = new URL(url1);
							BufferedImage jpg = ImageIO.read(imgUrl);
							File file = new File("C:\\Users\\user\\photo"+folder+"\\"+page+".jpg");
							ImageIO.write(jpg, "jpg", file);
						
						} catch(IOException e1) {
							e1.printStackTrace();
						}
						page +=1;
					}
			}
			
			
			
		}
	
	    */
		/*
		Elements element = doc.select("div.sect-mocie-chart");
		System.out.println("-----------------------------------");
		
		Iterator<Element> ie1 = element.select("strong.rank").iterator();
		Iterator<Element> ie2 = element.select("strong.title").iterator();
		
		while(ie1.hasNext()) {
			System.out.println(ie1.next().text()+'\t'+ie2.next().text());
		}*/
		/*
		Elements img = element.select("img");
		for(Element e: img) {
			String url1 = e.getElementsByAttribute("src").attr("src");
			
			
			try {
				URL imgUrl = new URL(url1);
				BufferedImage jpg = ImageIO.read(imgUrl);
				File file = new File("C:\\Users\\user\\photo"+folder+"\\"+page+".jpg");
				ImageIO.write(jpg, "jpg", file);
			
			} catch(IOException e1) {
				e1.printStackTrace();
			}
			page +=1;
		}
		*/
		System.out.println("-----------------------------------");
	}
}
