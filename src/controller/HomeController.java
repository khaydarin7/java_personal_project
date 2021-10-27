package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dto.MemberVO;
import service.MemberService;

public class HomeController {
	
	public static void main(String[] args)
	{
		ArrayList<MemberVO> dtos = null;
		MemberService service = new MemberService();
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			menu();
			choice=sc.nextInt();
			if(choice==1) {
				dtos=service.getAllChart();
				for(int i=0; i<dtos.size(); i++) {
					System.out.printf("%s위 ",dtos.get(i).getRank());
					System.out.printf("%s - ",dtos.get(i).getSongTitle());
					System.out.printf("%s",dtos.get(i).getSingerName());
					System.out.printf(" / %s\n",dtos.get(i).getAlbumTitle());
				}
				System.out.printf("\n");
				dtos.removeAll(dtos);
			}else if(choice==2) {
				conditionMenu();
				int conditionchoice = sc.nextInt();
				switch(conditionchoice) {
				
				case 1:
					System.out.print("조회하실 순위를 입력해주세요: ");
					int input_Rank = sc.nextInt();
					dtos=service.SearchByRank(input_Rank);
					break;
				case 2:
					System.out.print("조회하실 제목을 입력해주세요: ");
					String input_name = sc.next();
					dtos=service.Search("songtitle", input_name);
					break;
				case 3:
					System.out.print("조회하실 가수를 입력해주세요: ");
					input_name = sc.next();
					dtos=service.Search("singername", input_name);
					break;
				case 4:
					System.out.print("조회하실 앨범를 입력해주세요: ");
					input_name = sc.next();
					dtos=service.Search("albumtitle", input_name);
					break;
				default : System.out.println("잘못 입력하셨습니다.");
					break;
				}
				for(int i=0; i<dtos.size(); i++) {
					System.out.printf("%s위 ",dtos.get(i).getRank());
					System.out.printf("%s - ",dtos.get(i).getSongTitle());
					System.out.printf("%s",dtos.get(i).getSingerName());
					System.out.printf(" / %s\n",dtos.get(i).getAlbumTitle());
					}
				dtos.removeAll(dtos);

			}else if(choice==3) {
				dtos=service.truncatechart();
				RealtimeChart.UpdateRealtimeChart();
				System.out.println("업데이트 완료!\n");
			}else if(choice==4) {
				System.out.printf("1.수정\n");
				System.out.printf("2.추가\n");
				choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					while(true) {
						System.out.print("수정하실 음원순위를 입력하세요.\n");
						int input_Rank = sc.nextInt();
						dtos=service.SearchByRank(input_Rank);
						System.out.printf("%s위 ",dtos.get(0).getRank());
						System.out.printf("%s - ",dtos.get(0).getSongTitle());
						System.out.printf("%s",dtos.get(0).getSingerName());
						System.out.printf(" / %s\n",dtos.get(0).getAlbumTitle());
						System.out.print("이 음원을 수정하시겠습니까?(1.예 2.아니오)\n");
						int choice_update = sc.nextInt();
						if(choice_update == 1) {
							int Rank = input_Rank;
							System.out.print("수정정보를 입력하세요.\n");
							System.out.print("제목: ");
							String SongTitle = sc.next();
							System.out.print("가수: ");
							String SingerName = sc.next();
							System.out.print("앨범: ");
							String AlbumTitle = sc.next();
							dtos=service.updatechart(Rank,SongTitle, SingerName,AlbumTitle);
							break;
						}else if(choice_update == 2) {
							break;
						}
					}
					break;
				case 2:
					System.out.print("추가하실 노래 정보를 입력하세요.\n");
					System.out.print("순위:");
					int Rank = sc.nextInt();
					System.out.print("수정정보를 입력하세요.\n");
					System.out.print("제목: ");
					String SongTitle = sc.next();
					System.out.print("가수: ");
					String SingerName = sc.next();
					System.out.print("앨범: ");
					String AlbumTitle = sc.next();
					dtos=service.setchart(Rank,SongTitle,SingerName,AlbumTitle);
					break;
				default : System.out.println("잘못 입력하셨습니다.");
					break;
				}
				dtos.removeAll(dtos);
			}
			
			else if(choice==5) {//음원 삭제
				System.out.print("삭제하실 노래제목을 입력하세요.");
				String input_title = sc.next();
				dtos=service.Search("SongTitle", input_title);
				for(int i=0; i<dtos.size(); i++) {
					System.out.printf("%s. ",i+1);
					System.out.printf("%s - ",dtos.get(i).getSongTitle());
					System.out.printf("%s",dtos.get(i).getSingerName());
					System.out.printf(" / %s\n",dtos.get(i).getAlbumTitle());
					}
				System.out.print("삭제하실 노래의 번호을 입력하세요.");
				int i = sc.nextInt()-1;
				input_title = dtos.get(i).getSongTitle();
				System.out.printf("%s ",dtos.get(i).getSongTitle());
				dtos=service.deletechart(input_title);
				System.out.print("노래정보가 삭제되었습니다.\n");
				dtos.removeAll(dtos);
			}else if(choice==6) {
				System.out.print("프로그램을 종료합니다.");
				break;
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		sc.close();
	}
	public static void menu() {
		System.out.println("1.음원 모두 조회");
		System.out.println("2.항목별 조건 조회");
		System.out.println("3.음원 데이터 업데이트");
		System.out.println("4.음원 수정/추가");
		System.out.println("5.음원 삭제");
		System.out.println("6.종료");
	}
	public static void conditionMenu() {
		System.out.println("1.순위 조회");
		System.out.println("2.제목 조회");
		System.out.println("3.가수 조회");
		System.out.println("4.앨범 조회");
	}
}
