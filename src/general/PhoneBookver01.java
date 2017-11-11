package general;
import java.util.Scanner;

class PhoneInfoManager{
	final int max_size=5;
	PhoneInfo[] pinfo = new PhoneInfo[max_size];
	int arraysize=0;
	static Scanner sc = new Scanner(System.in);
	
	public static void selMenu(){
		System.out.print("�����ϼ���...\n1. �������Է�\n2. �����Ͱ˻�\n3. �����ͻ���\n4. ���α׷�����\n���� : ");
	}
	
	// ������ ����
	protected boolean insertData(){
		try{
			if(arraysize==5){
				return true;
			}
			System.out.print("�̸� : ");
			String name=sc.next();
			System.out.print("��ȭ��ȣ : ");
			String pnum=sc.next();
			System.out.print("������� : ");
			String birth=sc.next();
			pinfo[arraysize++]=new PhoneInfo(name, pnum, birth);
			return false;
		}catch(NullPointerException e){
			e.printStackTrace();
			return true;
		}
	}
	
	// ������ �˻�
	protected void searchData(){
		System.out.print("�̸� : ");
		String sname=sc.next();
		int idx=findIdx(sname);
		System.out.println("\n�Էµ� ���� ���...");
		pinfo[idx].getAll();
	}
	
	// ������ ����
	protected void deleteData(){
		System.out.print("�̸� : ");
		String sname=sc.next();
		int idx = findIdx(sname);
		for(int i=idx; i<arraysize-1; i++){
			pinfo[i]=pinfo[i+1];
		}
		arraysize--;
		System.out.println("\n������ ���� �Ϸ�...");
	}
	
	// �˻�, ������ �������� �ε����� ��ȯ�ϴ� �޼���
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
	
	// ��� ������ �Է��� ��� ȣ��Ǵ� ������
	public PhoneInfo(String name, String phoneNumber, String birthday){
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.birthday=birthday;
	}
	
	// ������ �Է����� �ʾ��� ��� ȣ��Ǵ� ������
	public PhoneInfo(String name, String phoneNumber){
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	
	// ��� ������ ����ϴ� �޼���
	public void getAll(){
		System.out.println("�̸� : " + this.name);
		System.out.println("��ȭ��ȣ : " + this.phoneNumber);
		System.out.println("���� : " + this.birthday + "\n");
	}
}

class PhoneBookver01{
	// ���� �޼���
	public static void main(String[] args){
		PhoneInfoManager pim=new PhoneInfoManager();
		int choice;
		do{
			PhoneInfoManager.selMenu();
			choice = PhoneInfoManager.sc.nextInt();
			if(choice==1){
				if(pim.insertData()){
					System.out.println("��ȭ��ȣ�ΰ� �� á���Ƿ� �����Ͻʽÿ�.");
					continue;
				}
				System.out.println("�Է� �Ϸ�\n");
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
		}while(true);
	}
}