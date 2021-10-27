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
	public ArrayList<MemberVO> setchart(int Rank, String SongTitle, String SingerName,String AlbumTitle) {
			String SQL = "Insert INTO geniechart(Rank,SongTitle,SingerName,AlbumTitle) VALUES(?,?,?,?)";
			try {
				pstmt=con.prepareStatement(SQL);
				pstmt.setInt(1, Rank);
				pstmt.setString(2, SongTitle);
				pstmt.setString(3, SingerName);
				pstmt.setString(4, AlbumTitle);
				pstmt.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return dtos;
		}
	//음원 삭제
	public ArrayList<MemberVO> deletechart(String input_title) {
		String SQL = "delete from geniechart where SongTitle='"+input_title+"'";
		try {
			rs=st.executeQuery(SQL);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	//음원 수정
	public ArrayList<MemberVO> updatechart(int Rank, String SongTitle, String SingerName,String AlbumTitle) {
		String SQL = "UPDATE geniechart SET SongTitle=?, SingerName=?, AlbumTitle=? where Rank=?";
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, SongTitle);
			pstmt.setString(2, SingerName);
			pstmt.setString(3, AlbumTitle);
			pstmt.setInt(4, Rank);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
}


