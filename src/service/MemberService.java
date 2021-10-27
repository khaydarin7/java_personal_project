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
	public ArrayList<MemberVO> setMembers(int no, String title, String singer,String lyricist,String songwriter,String release_date,String genre) {
		return dao.setMembers(no,title, singer,lyricist,songwriter,release_date,genre);
	}
	public ArrayList<MemberVO> Search(String condition,String input_name) {
		return dao.Search(condition,input_name);
	}
	public ArrayList<MemberVO> SearchByRank(int input_Rank) {
		return dao.SearchByRank(input_Rank);
	}
	public ArrayList<MemberVO> deleteMembers(String input_title) {
		return dao.deleteMembers(input_title);
	}
	public ArrayList<MemberVO> updateMembers(int no, String title, String singer,String lyricist,String songwriter,String release_date,String genre) {
		return dao.updateMembers(no,title, singer,lyricist,songwriter,release_date,genre);
	}
	public ArrayList<MemberVO> truncatechart() {
		return dao.truncatechart();
	}
}
