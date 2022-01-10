package vo;

import java.util.Date;

public class CommentVO {
	
	private int comment_num;			// 댓글 번호
	private String comment_id;			// 작성자 아이디
	private String comment_name;		// 작성자 이름
	private String comment_content;		// 작성 내용
	private Date comment_date;			// 작성 시간
	private String comment_delete;		// 삭제 여부
	private int comment_board_num;		// 게시글 번호
	
	public CommentVO() {}
	
	public CommentVO(String comment_id, String comment_name, String comment_content, int comment_board_num) {
		this.comment_id = comment_id;
		this.comment_name = comment_name;
		this.comment_content = comment_content;
		this.comment_board_num = comment_board_num;
	}

	public CommentVO(int comment_num, String comment_id, String comment_name, String comment_content, Date comment_date,
			String comment_delete, int comment_board_num) {
		this.comment_num = comment_num;
		this.comment_id = comment_id;
		this.comment_name = comment_name;
		this.comment_content = comment_content;
		this.comment_date = comment_date;
		this.comment_delete = comment_delete;
		this.comment_board_num = comment_board_num;
	}

	// getter & setter & toString
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_name() {
		return comment_name;
	}
	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_delete() {
		return comment_delete;
	}
	public void setComment_delete(String comment_delete) {
		this.comment_delete = comment_delete;
	}
	public int getComment_board_num() {
		return comment_board_num;
	}
	public void setComment_board_num(int comment_board_num) {
		this.comment_board_num = comment_board_num;
	}
	@Override
	public String toString() {
		return "CommentVO [comment_num=" + comment_num + ", comment_id=" + comment_id + ", comment_name=" + comment_name
				+ ", comment_content=" + comment_content + ", comment_date=" + comment_date + ", comment_delete="
				+ comment_delete + ", comment_board_num=" + comment_board_num + "]";
	}
	
}



