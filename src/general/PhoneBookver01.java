package general;
import java.util.InputMismatchException;
import java.util.Scanner;

class PhoneInfoManager{
	final int max_size=5;
	PhoneInfo[] pinfo = new PhoneInfo[max_size];
	int arraysize=0;
	static Scanner sc = new Scanner(System.in);
	
	public static void selMenu(){
		System.out.print("�����ϼ���...\n1. �������Է�\n2. �����Ͱ˻�\n3. �����ͻ���\n4. ���α׷�����\n���� : ");
	}
	
	public void selType(){
		System.out.print("1. �Ϲ�, 2. ����, 3. ȸ��\n����>> ");
	}
	
	// ������ ����
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
					System.out.print("�̸� : ");
					name=sc.next();
					System.out.print("���� : ");
					phoneNumber=sc.next();
					pinfo[arraysize++]=new PhoneInfo(name, phoneNumber);
					break;
				case 2:
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
				case 3:
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
		System.out.println("�̸� : " + name);
		System.out.println("���� : " + phoneNumber);
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
		System.out.println("�̸� : " + name);
		System.out.println("���� : " + phoneNumber);
		System.out.println("ȸ�� : " + company);
	}
}

class PhoneBookver01{
	// ���� �޼���
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
						System.out.println("Warning : ��ȭ��ȣ�ΰ� �� á���Ƿ� �����Ͻʽÿ�.\n");
						continue;
					}
				}else if(choice==2){
					pim.searchData();
					continue;
				}else if(choice==3){
					pim.deleteData();
					continue;
				}else if(choice==4){
					System.out.println("���α׷� ����");
					PhoneInfoManager.sc.close();
					return;
				}else{
					System.out.println("1~4�� �� �ϳ��� �Է��ϼ���\n");
				}
			}catch(InputMismatchException e){
				PhoneInfoManager.sc = new Scanner(System.in);
				System.out.println("���ڸ� �Է��ϼ���.\n");
			}
		}while(true);
	}
}