package decorator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class UsingDecorator {

	public static void main(String[] args)throws Exception,NoParamException {
		if(args.length == 0)
			throw new NoParamException("没有指定文件名称！");//当没有指定要读的文件名时抛出异常
		else{
			try{
				StudentHelper sh = new StudentHelper(new BufferedReader(new FileReader(args[0])));
				Student s = sh.readStudent();
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=root");
				
				while(s != null){
					sh.writeStudentToDataBase(s, conn);
					s = sh.readStudent();
				}
				conn.close();
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
			
		}

	}

}

/**
 * 学生类
 * */
class Student{
	private int id;
	private String name;
	private int age;
	
	public Student(int id, String name, int age){
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	
}


class StudentHelper extends FilterReader{

	protected StudentHelper(BufferedReader bf) {
		super(bf);
		
	}
	//从txt文件中读取数据
	public Student readStudent()throws IOException{
		
		Student s;
		int id = 0;
		String name = "";
		int age = 0;
		
		String buf;
		StringTokenizer st;
		BufferedReader br =(BufferedReader)in;
		buf = br.readLine();
		if(buf != null){
			st = new StringTokenizer(buf, " ");
			try{
				id = Integer.parseInt(st.nextToken());
				name = st.nextToken();
				age = Integer.parseInt(st.nextToken());
			}catch (NumberFormatException e) {
				e.printStackTrace();//打印异常
			}
			
			s = new Student(id,name,age);
			return s;
		}else return null;
		
	}
	//将从txt文件中读取的数据写入数据库中
	public void writeStudentToDataBase(Student s, Connection conn)throws IOException{
		try {
			PreparedStatement ps = conn.prepareStatement("INSTER INTO test(id, name, age) values(?, ?, ?)");
			ps.setInt(1, s.getId());
			ps.setString(2, s.getName());
			ps.setInt(3, s.getAge());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
}

//没有定义参数异常
class NoParamException extends Exception {
	public NoParamException(String msg){
		super(msg);
	}
}

