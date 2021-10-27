package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dto.MemberVO;
import service.MemberService;

public class RealtimeChart {
	public static void UpdateRealtimeChart() {
		ArrayList<MemberVO> dtos;
		MemberService service = new MemberService();
		String url="https://www.genie.co.kr/chart/top200";
	    Document doc = null;
	    try {
	        doc=Jsoup.connect(url).get();
	        Elements element = doc.select("table.list-wrap");
	        Iterator<Element> Title = element.select("a.title.ellipsis").iterator();
	        Iterator<Element> artist = element.select("a.artist.ellipsis").iterator();
	        Iterator<Element> Album = element.select("a.albumtitle.ellipsis").iterator();
	        int Rank = 0;
	        while(Rank < 50) {
	            Rank++;
	            String SongTitle = Title.next().text();
	            String SingerName = artist.next().text();
	            String AlbumTitle = Album.next().text();
	            dtos=service.InsertChart(Rank,SongTitle,SingerName,AlbumTitle);
	        }
	        }catch(IOException e) {
	            e.printStackTrace();
	        }
	    url="https://www.genie.co.kr/chart/top200?ditc=D&ymd=20210702&hh=10&rtm=Y&pg=2";
	    doc = null;
	    try {
	        doc=Jsoup.connect(url).get();
	        Elements element = doc.select("table.list-wrap");
	        Iterator<Element> Title = element.select("a.title.ellipsis").iterator();
	        Iterator<Element> artist = element.select("a.artist.ellipsis").iterator();
	        Iterator<Element> Album = element.select("a.albumtitle.ellipsis").iterator();
	        int Rank = 50;
	        while(Rank < 100) {
	            Rank++;
	            String SongTitle = Title.next().text();
	            String SingerName = artist.next().text();
	            String AlbumTitle = Album.next().text();
	            dtos=service.InsertChart(Rank,SongTitle,SingerName,AlbumTitle);
	        }
	        }catch(IOException e) {
	            e.printStackTrace();
	        }
	}

}
