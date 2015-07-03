package org.dedeplz.fridge.model.board;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardCommentDAOImpl implements BoardCommentDAO{
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 자유게시판 댓글등록
	 */
	@Override
	public void registBoardComment(BoardCommentVO bcvo) {
		 sqlSessionTemplate.insert("boardComment.registerBoardComment",bcvo);
	}

	/**
	 * 자유게시판 댓글 전체 리스트 
	 */
	@Override
	public List<BoardCommentVO> getBoardCommentList(int boardNo) {
		return sqlSessionTemplate.selectList("boardComment.getBoardCommentList",boardNo);
	}

	/**
	 * 자유게시판 해당 댓글 삭제
	 */
	@Override
	public void deleteBoardComment(int commentNo) {
		sqlSessionTemplate.delete("boardComment.deleteBoardComment",commentNo);
	}

	/**
	 * 자유게시판 해당 댓글 수정
	 */
	@Override
	public void updateBoardComment(BoardCommentVO bcvo) {
		 sqlSessionTemplate.update("boardComment.updateBoardComment",bcvo);
	}
	
	/**
	    * 자유게시판 자신이 쓴 모든 댓글 번호 가져오기 (탈퇴할때)
	    */
	   @Override
	   public List<Integer> getMyBoardCommentList(String nick) {
	      return sqlSessionTemplate.selectList("boardComment.getMyBoardCommentList",nick);
	   }

}
