package org.dedeplz.fridge.model.board;

public class BoardCommentVO {
   private int commentNo;
   private String  commentContents;
   private String commentTime;
   private String commentNick;
   private int commentBoardNo;
public BoardCommentVO() {
   super();
   // TODO Auto-generated constructor stub
}
public BoardCommentVO(int commentNo, String commentContents,
      String commentTime, String commentNick, int commentBoardNo) {
   super();
   this.commentNo = commentNo;
   this.commentContents = commentContents;
   this.commentTime = commentTime;
   this.commentNick = commentNick;
   this.commentBoardNo = commentBoardNo;
}
public int getCommentNo() {
   return commentNo;
}
public void setCommentNo(int commentNo) {
   this.commentNo = commentNo;
}
public String getCommentContents() {
   return commentContents;
}
public void setCommentContents(String commentContents) {
   this.commentContents = commentContents;
}
public String getCommentTime() {
   return commentTime;
}
public void setCommentTime(String commentTime) {
   this.commentTime = commentTime;
}
public String getCommentNick() {
   return commentNick;
}
public void setCommentNick(String commentNick) {
   this.commentNick = commentNick;
}
public int getCommentBoardNo() {
   return commentBoardNo;
}
public void setCommentBoardNo(int commentBoardNo) {
   this.commentBoardNo = commentBoardNo;
}
	@Override
	public String toString() {
	   return "BoardCommentVO [commentNo=" + commentNo + ", commentContents="
	         + commentContents + ", commentTime=" + commentTime
	         + ", commentNick=" + commentNick + ", commentBoardNo="
	         + commentBoardNo + "]";
	}
}