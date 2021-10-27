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
				
			}else if(choice==4) {//음원 삭제
				System.out.print("삭제하실 노래제목을 입력하세요.");
				String input_title = sc.next();
				dtos=service.deleteMembers(input_title);
				System.out.print("입력하신 노래정보가 삭제되었습니다.");
			}else if(choice==5) {
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
		System.out.println("4.음원삭제");
		System.out.println("5.종료");
	}
	public static void conditionMenu() {
		System.out.println("1.순위 조회");
		System.out.println("2.제목 조회");
		System.out.println("3.가수 조회");
		System.out.println("4.앨범 조회");
	}
}
