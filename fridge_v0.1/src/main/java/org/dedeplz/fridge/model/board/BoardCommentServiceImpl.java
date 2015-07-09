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
	/**
	 * 게시판 번호를 이용
	 * 해당 게시물에 작성된 모든 댓글을 삭제
	 */
	@Override
	public void deleteBoardCommentByBoardNo(int boardNo) {
		boardCommentDAO.deleteBoardCommentByBoardNo(boardNo);
		
	}
	/**
	 * 회원 닉네임을 이용해
	 * 회원 닉네임으로 작성된 게시판 댓글의 
	 * 모든 번호를 받아온다
	 */
	@Override
	public List<Integer> getMyBoardCommentNoByNick(String nick) {
		return boardCommentDAO.getMyBoardCommentNoByNick(nick);
	}
	
	

}
