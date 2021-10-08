package co.yedam.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		CommentDAO dao = CommentDAO.getInstance();
		PrintWriter out = response.getWriter();
		String cmd = request.getParameter("cmd");
		if(cmd == null) {
			System.out.println("<h1>빈페이지입니다</h1>");
			dao.getCommentList();
			
		} else if(cmd.equals("list")){
			System.out.println("<h1>리스트페이지입니다.</h1>");
			List<Comment> list = dao.getCommentList();
			Gson gson = new GsonBuilder().create();
			out.println(gson.toJson(list));
			
		} else if(cmd.equals("add")){
			out.println("<h1>추가페이지입니다.</h1>");
			
		} else if(cmd.equals("mod")){
			out.println("<h1>수정페이지입니다.</h1>");
			
		} else if(cmd.equals("del")){
			out.println("<h1>삭제페이지입니다.</h1>");
			
		} else {
			out.println("<h1>" + cmd + "</h1>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
