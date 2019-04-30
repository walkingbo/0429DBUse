package filter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class CommonFilter implements Filter {

    public CommonFilter() {
    }

    public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			//파라미터 인코딩 설정
			//파라미터을 읽을 때 마다 할 수 없어서 필터에서 수행
			request.setCharacterEncoding("utf-8");
		}
		catch(Exception e) {}
		//로그를 기록 : 접속한 ip, 요청 주소, 접속 시간
		FileOutputStream fos = 
			new FileOutputStream(
				"/Users/a503_18/Documents/log.txt", true);
		PrintWriter pw = 
				new PrintWriter(fos);
		//기록할 내용 만들기
		HttpServletRequest req = 
				(HttpServletRequest)request;
		String ip = req.getRemoteAddr();
		String requestURI = req.getRequestURI();
		Date today = new Date();
		//날짜를 원하는 포맷으로 출력하고자 하면 SimpleDateFormat을 참고
		String content = String.format(
				"%s %s %s", ip, requestURI, today.toString());
		pw.println(content);
		pw.flush();
		
		fos.close();
		pw.close();
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}



