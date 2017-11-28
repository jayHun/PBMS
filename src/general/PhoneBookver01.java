package general;
import java.util.InputMismatchException;
import java.util.Scanner;

interface menu_first{
	final int insert=1, search=2, delete=3, exit=4;
}

interface menu_second{
	final int general=1, college=2, company=3;
}

@SuppressWarnings("serial")
class MenuChoiceException extends Exception{
	int wrongChoice;
	public MenuChoiceException(int num){
		wrongChoice = num;
	}
	public void showWrongChoice(){
		System.out.println(wrongChoice + "에 해당하는 선택은 존재하지 않습니다.");
	}
}

class PhoneInfoManager{
	final int max_size=5;
	
	// 2개 이상의 인스턴스 생성 방지
	private static PhoneInfoManager pmgr = null;
	private PhoneInfoManager(){}
	public static PhoneInfoManager pmgrInst(){
		if(pmgr == null){
			pmgr = new PhoneInfoManager();
		}
		return pmgr;
	}
	
	PhoneInfo[] pinfo = new PhoneInfo[max_size];
	int arraysize=0;
	static Scanner sc = new Scanner(System.in);
	
	public static int selMenu()throws InputMismatchException, MenuChoiceException{
		int choice;
		System.out.print("선택하세요...\n1. 데이터입력\n2. 데이터검색\n3. 데이터삭제\n4. 프로그램종료\n선택 : ");
		choice = sc.nextInt();
		if(choice<menu_first.insert || choice>menu_first.delete){
			throw new MenuChoiceException(choice);
		}
		return choice;
	}
	
	public int selType()throws MenuChoiceException, InputMismatchException{
		int type;
		System.out.print("1. 일반, 2. 대학, 3. 회사\n선택>> ");
		type=sc.nextInt();
		if(type<menu_second.general || type>menu_second.company){
			throw new MenuChoiceException(type);
		}
		return type;
	}
	
	// 데이터 삽입
	protected boolean insertData()throws MenuChoiceException, InputMismatchException{
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
			type=selType();
			type=sc.nextInt();
			switch(type){
				case menu_second.general:
					System.out.print("이름 : ");
					name=sc.next();
					System.out.print("전번 : ");
					phoneNumber=sc.next();
					pinfo[arraysize++]=new PhoneInfo(name, phoneNumber);
					break;
				case menu_second.college:
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
				case menu_second.company:
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
		super.getAll();
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
		super.getAll();
		System.out.println("회사 : " + company);
	}
}

class PhoneBookver01{
	// 메인 메서드
	public static void main(String[] args){
		PhoneInfoManager pim=PhoneInfoManager.pmgrInst();
		int choice;
		do{
			try{
				choice = PhoneInfoManager.selMenu();
				PhoneInfoManager.sc.nextLine();
				if(choice == menu_first.insert){
					if(pim.insertData()){
						System.out.println("Warning : 전화번호부가 꽉 찼으므로 삭제하십시오.\n");
						continue;
					}
				}else if(choice == menu_first.search){
					pim.searchData();
					continue;
				}else if(choice == menu_first.delete){
					pim.deleteData();
					continue;
				}else if(choice == menu_first.exit){
					System.out.println("프로그램 종료");
					PhoneInfoManager.sc.close();
					return;
				}
			}catch(MenuChoiceException e){
				e.showWrongChoice();
				System.out.println("메뉴 선택을 처음부터 다시 진행합니다\n");
			}catch(InputMismatchException e){
				PhoneInfoManager.sc = new Scanner(System.in);
				System.out.println("숫자를 입력하세요.\n");
			}
		}while(true);
	}
}