package org.dedeplz.fridge.model.recipe;

public class RecipeCommentVO {
	private int commentNo;
	private int commentGroup;
	private int commentLevel;
	private String  commentContents;
	private String commentTime;
	private String commentNick;
	private int commentRecipeNo;
	private String commentRefNick;
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getCommentGroup() {
		return commentGroup;
	}
	public void setCommentGroup(int commentGroup) {
		this.commentGroup = commentGroup;
	}
	public int getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
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
	public int getCommentRecipeNo() {
		return commentRecipeNo;
	}
	public void setCommentRecipeNo(int commentRecipeNo) {
		this.commentRecipeNo = commentRecipeNo;
	}
	public String getCommentRefNick() {
		return commentRefNick;
	}
	public void setCommentRefNick(String commentRefNick) {
		this.commentRefNick = commentRefNick;
	}
	@Override
	public String toString() {
		return "RecipeCommentVO [commentNo=" + commentNo + ", commentGroup=" + commentGroup
				+ ", commentLevel=" + commentLevel + ", commentContents="
				+ commentContents + ", commentTime=" + commentTime
				+ ", commentNick=" + commentNick + ", commentRecipeNo="
				+ commentRecipeNo + ", commentRefNick=" + commentRefNick + "]";
	}
	public RecipeCommentVO(int commentNo, int commentGroup,
			int commentLevel, String commentContents, String commentTime,
			String commentNick, int commentRecipeNo, String commentRefNick) {
		super();
		this.commentNo = commentNo;
		this.commentGroup = commentGroup;
		this.commentLevel = commentLevel;
		this.commentContents = commentContents;
		this.commentTime = commentTime;
		this.commentNick = commentNick;
		this.commentRecipeNo = commentRecipeNo;
		this.commentRefNick = commentRefNick;
	}
	public RecipeCommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
