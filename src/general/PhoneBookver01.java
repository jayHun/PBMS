package general;
import java.util.Scanner;

class PhoneInfoManager{
	final int max_size=5;
	PhoneInfo[] pinfo = new PhoneInfo[max_size];
	int arraysize=0;
	static Scanner sc = new Scanner(System.in);
	
	public static void selMenu(){
		System.out.print("선택하세요...\n1. 데이터입력\n2. 데이터검색\n3. 데이터삭제\n4. 프로그램종료\n선택 : ");
	}
	
	// 데이터 삽입
	protected boolean insertData(){
		try{
			if(arraysize==5){
				return true;
			}
			System.out.print("이름 : ");
			String name=sc.next();
			System.out.print("전화번호 : ");
			String pnum=sc.next();
			System.out.print("생년월일 : ");
			String birth=sc.next();
			pinfo[arraysize++]=new PhoneInfo(name, pnum, birth);
			return false;
		}catch(NullPointerException e){
			e.printStackTrace();
			return true;
		}
	}
	
	// 데이터 검색
	protected void searchData(){
		System.out.print("이름 : ");
		String sname=sc.next();
		int idx=findIdx(sname);
		System.out.println("\n입력된 정보 출력...");
		pinfo[idx].getAll();
	}
	
	// 데이터 삭제
	protected void deleteData(){
		System.out.print("이름 : ");
		String sname=sc.next();
		int idx = findIdx(sname);
		for(int i=idx; i<arraysize-1; i++){
			pinfo[i]=pinfo[i+1];
		}
		arraysize--;
		System.out.println("\n데이터 삭제 완료...");
	}
	
	// 검색, 삭제할 데이터의 인덱스를 반환하는 메서드
	protected int findIdx(String name){
		int idx=0;
		for(int i=0; i<arraysize; i++){
			if(pinfo[i].name.equals(name)){
				idx=i;
				break;
			}
		}
		return idx;
	}
}

class PhoneInfo {
	protected String name;
	protected String phoneNumber;
	protected String birthday;
	
	// 모든 정보를 입력할 경우 호출되는 생성자
	public PhoneInfo(String name, String phoneNumber, String birthday){
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.birthday=birthday;
	}
	
	// 생일을 입력하지 않았을 경우 호출되는 생성자
	public PhoneInfo(String name, String phoneNumber){
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	
	// 모든 정보를 출력하는 메서드
	public void getAll(){
		System.out.println("이름 : " + this.name);
		System.out.println("전화번호 : " + this.phoneNumber);
		System.out.println("생일 : " + this.birthday + "\n");
	}
}

class PhoneBookver01{
	// 메인 메서드
	public static void main(String[] args){
		PhoneInfoManager pim=new PhoneInfoManager();
		int choice;
		do{
			PhoneInfoManager.selMenu();
			choice = PhoneInfoManager.sc.nextInt();
			if(choice==1){
				if(pim.insertData()){
					System.out.println("전화번호부가 꽉 찼으므로 삭제하십시오.");
					continue;
				}
				System.out.println("입력 완료\n");
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
		}while(true);
	}
}