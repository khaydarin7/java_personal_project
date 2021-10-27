package service;

import java.util.ArrayList;
import dao.MemberDAO;
import dto.MemberVO;

public class MemberService {
	
	//dao¸¦ Æ÷ÇÔ
	private MemberDAO dao;
	public MemberService() {
		dao=new MemberDAO();
	}
	public ArrayList<MemberVO> InsertChart(int Rank, String SongTitle, String SingerName,String AlbumTitle){
		return dao.InsertChart(Rank, SongTitle, SingerName, AlbumTitle);
	}
	public ArrayList<MemberVO> getAllChart(){
		return dao.getAllChart();
	}
	public ArrayList<MemberVO> setchart(int Rank, String SongTitle, String SingerName,String AlbumTitle) {
		return dao.setchart(Rank,SongTitle,SingerName,AlbumTitle);
	}
	public ArrayList<MemberVO> Search(String condition,String input_name) {
		return dao.Search(condition,input_name);
	}
	public ArrayList<MemberVO> SearchByRank(int input_Rank) {
		return dao.SearchByRank(input_Rank);
	}
	public ArrayList<MemberVO> deletechart(String input_title) {
		return dao.deletechart(input_title);
	}
	public ArrayList<MemberVO> updatechart(int Rank, String SongTitle, String SingerName,String AlbumTitle) {
		return dao.updatechart(Rank,SongTitle,SingerName,AlbumTitle);
	}
	public ArrayList<MemberVO> truncatechart() {
		return dao.truncatechart();
	}
}
