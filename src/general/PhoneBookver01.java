package general;
import java.util.InputMismatchException;
import java.util.Scanner;

class PhoneInfoManager{
	final int max_size=5;
	PhoneInfo[] pinfo = new PhoneInfo[max_size];
	int arraysize=0;
	static Scanner sc = new Scanner(System.in);
	
	public static void selMenu(){
		System.out.print("선택하세요...\n1. 데이터입력\n2. 데이터검색\n3. 데이터삭제\n4. 프로그램종료\n선택 : ");
	}
	
	public void selType(){
		System.out.print("1. 일반, 2. 대학, 3. 회사\n선택>> ");
	}
	
	// 데이터 삽입
	protected boolean insertData(){
		int type;
		String name;
		String phoneNumber;
		String company;
		String major;
		int year;
		
		try{
			if(arraysize==5){
				return true;
			}
			selType();
			type=sc.nextInt();
			switch(type){
				case 1:
					System.out.print("이름 : ");
					name=sc.next();
					System.out.print("전번 : ");
					phoneNumber=sc.next();
					pinfo[arraysize++]=new PhoneInfo(name, phoneNumber);
					break;
				case 2:
					System.out.print("이름 : ");
					name=sc.next();
					System.out.print("전번 : ");
					phoneNumber=sc.next();
					System.out.print("전공 : ");
					major=sc.next();
					System.out.print("나이 : ");
					year=sc.nextInt();
					pinfo[arraysize++]=new PhoneUnivInfo(name, phoneNumber, major, year);
					break;
				case 3:
					System.out.print("이름 : ");
					name=sc.next();
					System.out.print("전번 : ");
					phoneNumber=sc.next();
					System.out.print("회사 : ");
					company=sc.next();
					pinfo[arraysize++]=new PhoneCompanyInfo(name, phoneNumber, company);
					break;
				default:
					System.out.println("1~3의 숫자를 입력하세요...");
					return false;
			}
			System.out.println("데이터 입력이 완료되었습니다.\n");
			return false;
		}catch(NullPointerException e){
			e.printStackTrace();
			return true;
		}
	}
	
	// 데이터 검색
	protected void searchData(){
		System.out.println("데이터 검색을 시작합니다...");
		System.out.print("이름 : ");
		String sname=sc.next();
		int idx=findIdx(sname);
		if(idx>=0){
			pinfo[idx].getAll();
			System.out.println("데이터 검색이 완료되었습니다.\n");
		}else{
			System.out.println("해당 데이터가 존재하지 않습니다.\n");
		}
	}
	
	// 데이터 삭제
	protected void deleteData(){
		System.out.print("이름 : ");
		String sname=sc.next();
		int idx = findIdx(sname);
		if(idx>=0){
			for(int i=idx; i<arraysize-1; i++){
				pinfo[i]=pinfo[i+1];
			}
			arraysize--;
			System.out.println("\n데이터 삭제 완료...");
		}else{
			System.out.println("해당 데이터가 존재하지 않습니다.\n");
		}
	}
	
	// 검색, 삭제할 데이터의 인덱스를 반환하는 메서드
	protected int findIdx(String name){
		for(int i=0; i<arraysize; i++){
			if(name.compareTo(pinfo[i].name)==0){
				return i;
			}
		}
		return -1;
	}
}

class PhoneInfo {
	protected String name;
	protected String phoneNumber;
	
	// 모든 정보를 입력할 경우 호출되는 생성자
	public PhoneInfo(String name, String phoneNumber){
		this.name=name;
		this.phoneNumber=phoneNumber;
				
	}
	
	// 모든 정보를 출력하는 메서드
	protected void getAll(){
		System.out.println("이름 : " + name);
		System.out.println("전번 : " + phoneNumber);
	}
}

class PhoneUnivInfo extends PhoneInfo{
	protected String major;
	protected int year;
	
	PhoneUnivInfo(String name, String phoneNumber, String major, int year){
		super(name, phoneNumber);
		this.major=major;
		this.year=year;
	}
	
	protected void getAll(){
		System.out.println("이름 : " + name);
		System.out.println("전번 : " + phoneNumber);
		System.out.println("전공 : " + major);
		System.out.println("나이 : " + year);
	}
}

class PhoneCompanyInfo extends PhoneInfo{
	protected String company;
	
	PhoneCompanyInfo(String name, String phoneNumber, String company){
		super(name, phoneNumber);
		this.company=company;
	}
	
	protected void getAll(){
		System.out.println("이름 : " + name);
		System.out.println("전번 : " + phoneNumber);
		System.out.println("회사 : " + company);
	}
}

class PhoneBookver01{
	// 메인 메서드
	public static void main(String[] args){
		PhoneInfoManager pim=new PhoneInfoManager();
		int choice;
		do{
			try{
				PhoneInfoManager.selMenu();
				choice = PhoneInfoManager.sc.nextInt();
				PhoneInfoManager.sc.nextLine();
				if(choice==1){
					if(pim.insertData()){
						System.out.println("Warning : 전화번호부가 꽉 찼으므로 삭제하십시오.\n");
						continue;
					}
				}else if(choice==2){
					pim.searchData();
					continue;
				}else if(choice==3){
					pim.deleteData();
					continue;
				}else if(choice==4){
					System.out.println("프로그램 종료");
					PhoneInfoManager.sc.close();
					return;
				}else{
					System.out.println("1~4번 중 하나를 입력하세요\n");
				}
			}catch(InputMismatchException e){
				PhoneInfoManager.sc = new Scanner(System.in);
				System.out.println("숫자를 입력하세요.\n");
			}
		}while(true);
	}
}