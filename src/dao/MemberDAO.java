package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.MemberVO;

public class MemberDAO {
	private ArrayList<MemberVO> dtos;
	private Connection con;
	private Statement st;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO() {
		dtos = new ArrayList<MemberVO>();
		try {
			String user="system";
			String pw="1234";
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,user,pw);
			st=con.createStatement();
		}catch(Exception e) {
			System.out.println("데이터베이스 연결 오류:"+e.getMessage());
		}
	}
	public ArrayList<MemberVO> InsertChart(int Rank, String SongTitle, String SingerName,String AlbumTitle) {
		String SQL = "INSERT INTO geniechart(RANK, SONGTITLE, SINGERNAME,albumtitle) VALUES(?,?,?,?)";
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setInt(1, Rank);
			pstmt.setString(2, SongTitle);
			pstmt.setString(3, SingerName);
			pstmt.setString(4, AlbumTitle);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	public ArrayList<MemberVO> getAllChart() {
		String SQL="SELECT * FROM geniechart";
		try {
			rs=st.executeQuery(SQL);
			while(rs.next()) {	
				int Rank = rs.getInt("Rank");
				String SongTitle = rs.getString("SongTitle");
				String SingerName = rs.getString("SingerName");
				String AlbumTitle = rs.getString("AlbumTitle");
				MemberVO VO=new MemberVO(Rank,SongTitle,SingerName,AlbumTitle);
				dtos.add(VO);
				//ArrayList에 회원정보 추가
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	public ArrayList<MemberVO> SearchByRank(int input_Rank) {
		String SQL = "SELECT * FROM geniechart where Rank like "+input_Rank;
		try {
			rs=st.executeQuery(SQL);
			while(rs.next()) {
				int Rank = rs.getInt("Rank");
				String SongTitle = rs.getString("SongTitle");
				String SingerName = rs.getString("SingerName");
				String AlbumTitle = rs.getString("AlbumTitle");
				MemberVO VO=new MemberVO(Rank,SongTitle,SingerName,AlbumTitle);
				dtos.add(VO);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	public ArrayList<MemberVO> Search(String condition,String input_name) {
		String SQL = "SELECT * FROM geniechart where "+condition+" like '%"+input_name+"%'";
		try {
			rs=st.executeQuery(SQL);
			while(rs.next()) {
				int Rank = rs.getInt("Rank");
				String SongTitle = rs.getString("SongTitle");
				String SingerName = rs.getString("SingerName");
				String AlbumTitle = rs.getString("AlbumTitle");
				MemberVO VO=new MemberVO(Rank,SongTitle,SingerName,AlbumTitle);
				dtos.add(VO);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	public ArrayList<MemberVO> truncatechart() {
		String SQL = "TRUNCATE TABLE geniechart";
		try {
			rs=st.executeQuery(SQL);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	//음원 추가
	public ArrayList<MemberVO> setMembers(int no, String title, String singer,String lyricist,String songwriter,String release_date,String genre) {
			String SQL = "Insert INTO music_chart(no,title,singer,lyricist,songwriter,release_date,genre) VALUES(?,?,?,?,?,?,?)";
			try {
				pstmt=con.prepareStatement(SQL);
				pstmt.setInt(1, no);
				pstmt.setString(2, title);
				pstmt.setString(3, singer);
				pstmt.setString(4, lyricist);
				pstmt.setString(5, songwriter);
				pstmt.setString(6, release_date);
				pstmt.setString(7, genre);
				pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return dtos;
		}
	//음원 삭제
	public ArrayList<MemberVO> deleteMembers(String input_title) {
		String SQL = "delete from music_chart where title='"+input_title+"'";
		try {
			rs=st.executeQuery(SQL);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	//음원 수정
	public ArrayList<MemberVO> updateMembers(int no, String title, String singer,String lyricist,String songwriter,String release_date,String genre) {
		String SQL = "UPDATE music_chart SET title=?, singer=?, lyricist=?, songwriter=?, release_date=?, genre=? where no=?";
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, title);
			pstmt.setString(2, singer);
			pstmt.setString(3, lyricist);
			pstmt.setString(4, songwriter);
			pstmt.setString(5, release_date);
			pstmt.setString(6, genre);
			pstmt.setInt(7, no);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
}


