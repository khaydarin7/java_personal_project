package dto;

public class MemberVO {

	private int rank;
	private String SongTitle;
	private String SingerName;
	private String AlbumTitle;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSongTitle() {
		return SongTitle;
	}

	public void setSongTitle(String songTitle) {
		SongTitle = songTitle;
	}

	public String getSingerName() {
		return SingerName;
	}

	public void setSingerName(String singerName) {
		SingerName = singerName;
	}

	public String getAlbumTitle() {
		return AlbumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		AlbumTitle = albumTitle;
	}

	public MemberVO(int rank, String SongTitle, String SingerName, String AlbumTitle){
		
		this.rank=rank;
		this.SongTitle=SongTitle;
		this.SingerName=SingerName;
		this.AlbumTitle=AlbumTitle;
	}
}
