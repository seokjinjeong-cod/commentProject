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

	// 글입력.. 1)현재id 2)id+1 값으로 글등록 3)현재id값 변경
	public Comment insertComment(Comment comment) {
		connect();
		int currId = 0;
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select value from id_repository where name='COMMENT'");
			if(rs.next()) {
				currId = rs.getInt("value");
			}
			currId++;
			psmt = conn.prepareStatement("insert into comments values(?,?,?)");
			psmt.setInt(1, currId);
			psmt.setString(2, comment.getName());
			psmt.setString(3, comment.getContent());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력");
			
			psmt = conn.prepareStatement("update id_repository set value=? where name='COMMENT'");
			psmt.setInt(1, currId);
			r = psmt.executeUpdate();
			System.out.println(r + "건 변경.");
			
			conn.commit();
			comment.setId(String.valueOf(currId));
			return comment;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		} finally {
			disconnect();
		}
	}

	// 글수정..
	public void updateComment() {

	}

	// 글삭제..
	public void deleteComment() {

	}
}