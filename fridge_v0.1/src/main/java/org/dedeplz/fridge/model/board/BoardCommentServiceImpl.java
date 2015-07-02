package org.dedeplz.fridge.model.board;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class BoardCommentServiceImpl implements BoardCommentService{
	
	@Resource
	private BoardCommentDAO boardCommentDAO;

	/**
	 * 자유게시판 댓글 등록
	 */
	@Override
	public void registBoardComment(BoardCommentVO bcvo) {
		boardCommentDAO.registBoardComment(bcvo);
		//boardCommentDAO.getListBoardComment();
	}

	/**
	 * 자유게시판 댓글 전체 리스트
	 */
	@Override
	public List<BoardCommentVO> getBoardCommentList(int boardNo) {
		System.out.println(boardCommentDAO.getBoardCommentList(boardNo));
		return boardCommentDAO.getBoardCommentList(boardNo);
	}

	/**
	 * 자유게시판 해당 댓글 삭제
	 */
	@Override
	public void deleteBoardComment(int commentNo) {
		boardCommentDAO.deleteBoardComment(commentNo);
	}

	/**
	 * 자유게시판 해당 댓글 수정
	 */
	@Override
	public void updateBoardComment(BoardCommentVO bcvo) {
		 boardCommentDAO.updateBoardComment(bcvo);
	}
	
	
	

}
