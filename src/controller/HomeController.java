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
					System.out.printf("%s�� ",dtos.get(i).getRank());
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
					System.out.print("��ȸ�Ͻ� ������ �Է����ּ���: ");
					int input_Rank = sc.nextInt();
					dtos=service.SearchByRank(input_Rank);
					break;
				case 2:
					System.out.print("��ȸ�Ͻ� ������ �Է����ּ���: ");
					String input_name = sc.next();
					dtos=service.Search("songtitle", input_name);
					break;
				case 3:
					System.out.print("��ȸ�Ͻ� ������ �Է����ּ���: ");
					input_name = sc.next();
					dtos=service.Search("singername", input_name);
					break;
				case 4:
					System.out.print("��ȸ�Ͻ� �ٹ��� �Է����ּ���: ");
					input_name = sc.next();
					dtos=service.Search("albumtitle", input_name);
					break;
				default : System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					break;
				}
				for(int i=0; i<dtos.size(); i++) {
					System.out.printf("%s�� ",dtos.get(i).getRank());
					System.out.printf("%s - ",dtos.get(i).getSongTitle());
					System.out.printf("%s",dtos.get(i).getSingerName());
					System.out.printf(" / %s\n",dtos.get(i).getAlbumTitle());
					}
				dtos.removeAll(dtos);

			}else if(choice==3) {
				dtos=service.truncatechart();
				RealtimeChart.UpdateRealtimeChart();
				System.out.println("������Ʈ �Ϸ�!\n");
			}else if(choice==4) {
				System.out.printf("1.����\n");
				System.out.printf("2.�߰�\n");
				choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					while(true) {
						System.out.print("�����Ͻ� ���������� �Է��ϼ���.\n");
						int input_Rank = sc.nextInt();
						dtos=service.SearchByRank(input_Rank);
						System.out.printf("%s�� ",dtos.get(0).getRank());
						System.out.printf("%s - ",dtos.get(0).getSongTitle());
						System.out.printf("%s",dtos.get(0).getSingerName());
						System.out.printf(" / %s\n",dtos.get(0).getAlbumTitle());
						System.out.print("�� ������ �����Ͻðڽ��ϱ�?(1.�� 2.�ƴϿ�)\n");
						int choice_update = sc.nextInt();
						if(choice_update == 1) {
							int Rank = input_Rank;
							System.out.print("���������� �Է��ϼ���.\n");
							System.out.print("����: ");
							String SongTitle = sc.next();
							System.out.print("����: ");
							String SingerName = sc.next();
							System.out.print("�ٹ�: ");
							String AlbumTitle = sc.next();
							dtos=service.updatechart(Rank,SongTitle, SingerName,AlbumTitle);
							break;
						}else if(choice_update == 2) {
							break;
						}
					}
					break;
				case 2:
					System.out.print("�߰��Ͻ� �뷡 ������ �Է��ϼ���.\n");
					System.out.print("����:");
					int Rank = sc.nextInt();
					System.out.print("���������� �Է��ϼ���.\n");
					System.out.print("����: ");
					String SongTitle = sc.next();
					System.out.print("����: ");
					String SingerName = sc.next();
					System.out.print("�ٹ�: ");
					String AlbumTitle = sc.next();
					dtos=service.setchart(Rank,SongTitle,SingerName,AlbumTitle);
					break;
				default : System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					break;
				}
				dtos.removeAll(dtos);
			}
			
			else if(choice==5) {//���� ����
				System.out.print("�����Ͻ� �뷡������ �Է��ϼ���.");
				String input_title = sc.next();
				dtos=service.Search("SongTitle", input_title);
				for(int i=0; i<dtos.size(); i++) {
					System.out.printf("%s. ",i+1);
					System.out.printf("%s - ",dtos.get(i).getSongTitle());
					System.out.printf("%s",dtos.get(i).getSingerName());
					System.out.printf(" / %s\n",dtos.get(i).getAlbumTitle());
					}
				System.out.print("�����Ͻ� �뷡�� ��ȣ�� �Է��ϼ���.");
				int i = sc.nextInt()-1;
				input_title = dtos.get(i).getSongTitle();
				System.out.printf("%s ",dtos.get(i).getSongTitle());
				dtos=service.deletechart(input_title);
				System.out.print("�뷡������ �����Ǿ����ϴ�.\n");
				dtos.removeAll(dtos);
			}else if(choice==6) {
				System.out.print("���α׷��� �����մϴ�.");
				break;
			}else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
		sc.close();
	}
	public static void menu() {
		System.out.println("1.���� ��� ��ȸ");
		System.out.println("2.�׸� ���� ��ȸ");
		System.out.println("3.���� ������ ������Ʈ");
		System.out.println("4.���� ����/�߰�");
		System.out.println("5.���� ����");
		System.out.println("6.����");
	}
	public static void conditionMenu() {
		System.out.println("1.���� ��ȸ");
		System.out.println("2.���� ��ȸ");
		System.out.println("3.���� ��ȸ");
		System.out.println("4.�ٹ� ��ȸ");
	}
}
