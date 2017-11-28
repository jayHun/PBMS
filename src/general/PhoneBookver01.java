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
		System.out.println(wrongChoice + "�� �ش��ϴ� ������ �������� �ʽ��ϴ�.");
	}
}

class PhoneInfoManager{
	final int max_size=5;
	
	// 2�� �̻��� �ν��Ͻ� ���� ����
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
		System.out.print("�����ϼ���...\n1. �������Է�\n2. �����Ͱ˻�\n3. �����ͻ���\n4. ���α׷�����\n���� : ");
		choice = sc.nextInt();
		if(choice<menu_first.insert || choice>menu_first.delete){
			throw new MenuChoiceException(choice);
		}
		return choice;
	}
	
	public int selType()throws MenuChoiceException, InputMismatchException{
		int type;
		System.out.print("1. �Ϲ�, 2. ����, 3. ȸ��\n����>> ");
		type=sc.nextInt();
		if(type<menu_second.general || type>menu_second.company){
			throw new MenuChoiceException(type);
		}
		return type;
	}
	
	// ������ ����
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
					System.out.print("�̸� : ");
					name=sc.next();
					System.out.print("���� : ");
					phoneNumber=sc.next();
					pinfo[arraysize++]=new PhoneInfo(name, phoneNumber);
					break;
				case menu_second.college:
					System.out.print("�̸� : ");
					name=sc.next();
					System.out.print("���� : ");
					phoneNumber=sc.next();
					System.out.print("���� : ");
					major=sc.next();
					System.out.print("���� : ");
					year=sc.nextInt();
					pinfo[arraysize++]=new PhoneUnivInfo(name, phoneNumber, major, year);
					break;
				case menu_second.company:
					System.out.print("�̸� : ");
					name=sc.next();
					System.out.print("���� : ");
					phoneNumber=sc.next();
					System.out.print("ȸ�� : ");
					company=sc.next();
					pinfo[arraysize++]=new PhoneCompanyInfo(name, phoneNumber, company);
					break;
				default:
					System.out.println("1~3�� ���ڸ� �Է��ϼ���...");
					return false;
			}
			System.out.println("������ �Է��� �Ϸ�Ǿ����ϴ�.\n");
			return false;
		}catch(NullPointerException e){
			e.printStackTrace();
			return true;
		}
	}
	
	// ������ �˻�
	protected void searchData(){
		System.out.println("������ �˻��� �����մϴ�...");
		System.out.print("�̸� : ");
		String sname=sc.next();
		int idx=findIdx(sname);
		if(idx>=0){
			pinfo[idx].getAll();
			System.out.println("������ �˻��� �Ϸ�Ǿ����ϴ�.\n");
		}else{
			System.out.println("�ش� �����Ͱ� �������� �ʽ��ϴ�.\n");
		}
	}
	
	// ������ ����
	protected void deleteData(){
		System.out.print("�̸� : ");
		String sname=sc.next();
		int idx = findIdx(sname);
		if(idx>=0){
			for(int i=idx; i<arraysize-1; i++){
				pinfo[i]=pinfo[i+1];
			}
			arraysize--;
			System.out.println("\n������ ���� �Ϸ�...");
		}else{
			System.out.println("�ش� �����Ͱ� �������� �ʽ��ϴ�.\n");
		}
	}
	
	// �˻�, ������ �������� �ε����� ��ȯ�ϴ� �޼���
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
	
	// ��� ������ �Է��� ��� ȣ��Ǵ� ������
	public PhoneInfo(String name, String phoneNumber){
		this.name=name;
		this.phoneNumber=phoneNumber;
				
	}
	
	// ��� ������ ����ϴ� �޼���
	protected void getAll(){
		System.out.println("�̸� : " + name);
		System.out.println("���� : " + phoneNumber);
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
		System.out.println("���� : " + major);
		System.out.println("���� : " + year);
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
		System.out.println("ȸ�� : " + company);
	}
}

class PhoneBookver01{
	// ���� �޼���
	public static void main(String[] args){
		PhoneInfoManager pim=PhoneInfoManager.pmgrInst();
		int choice;
		do{
			try{
				choice = PhoneInfoManager.selMenu();
				PhoneInfoManager.sc.nextLine();
				if(choice == menu_first.insert){
					if(pim.insertData()){
						System.out.println("Warning : ��ȭ��ȣ�ΰ� �� á���Ƿ� �����Ͻʽÿ�.\n");
						continue;
					}
				}else if(choice == menu_first.search){
					pim.searchData();
					continue;
				}else if(choice == menu_first.delete){
					pim.deleteData();
					continue;
				}else if(choice == menu_first.exit){
					System.out.println("���α׷� ����");
					PhoneInfoManager.sc.close();
					return;
				}
			}catch(MenuChoiceException e){
				e.showWrongChoice();
				System.out.println("�޴� ������ ó������ �ٽ� �����մϴ�\n");
			}catch(InputMismatchException e){
				PhoneInfoManager.sc = new Scanner(System.in);
				System.out.println("���ڸ� �Է��ϼ���.\n");
			}
		}while(true);
	}
}