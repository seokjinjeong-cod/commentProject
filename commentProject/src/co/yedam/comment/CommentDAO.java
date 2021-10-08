package co.yedam.comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DAO;

public class CommentDAO extends DAO {

	private static CommentDAO singleton = new CommentDAO();

	private CommentDAO() {

	}

	public static CommentDAO getInstance() {
		return singleton;
	}

	// 글목록..
	public List<Comment> getCommentList() {
		connect();
		List<Comment> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from comments order by 1");
			while(rs.next()) {
				Comment cmt = new Comment();
				cmt.setId(rs.getString("id"));
				cmt.setContent(rs.getString("content"));
				cmt.setName(rs.getString("name"));
				list.add(cmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 글입력..
	public void insertComment() {

	}

	// 글수정..
	public void updateComment() {

	}

	// 글삭제..
	public void deleteComment() {

	}
}