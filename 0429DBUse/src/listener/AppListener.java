package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import util.GlobalApplication;

//web.xml 파일에 Listener를 등록하는 대신 사용할 수 있는 어노테이션
//이 어노테이션이 없으면 web.xml 파일에 리스너를 등록
//등록하는 코드 servers 디렉토리에 있는 web.xml 파일을 확인
@WebListener
public class AppListener implements ServletContextListener {
	//생성자 -  인스턴스가 생성될 때 호출되는 메소드
    public AppListener() {
    }
    //웹 애플리케이션(Context)이 종료될 때 호출되는 메소드
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	//데이터베이스 연결 해제 메소드 호출
    	GlobalApplication.shardInstance().close();
    }
    //웹 애플리케이션이 시작될 때 호출되는 메소드
    public void contextInitialized(ServletContextEvent arg0)  { 
    	try {
    		//오라클 드라이버 클래스 로드
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		//로드되었는지 확인하기 위해서 콘솔에 텍스트를 출력
    		//System.out.println("오라클 드라이버 클래스 로드 성공");
    		
    		//데이터베이스 연결 메소드 호출
    		GlobalApplication.shardInstance().connection();
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		e.printStackTrace();
    	}
    }
}














