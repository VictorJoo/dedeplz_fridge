package org.dedeplz.fridge.controller.board;

import java.util.List;

import org.dedeplz.fridge.model.board.BoardCommentService;
import org.dedeplz.fridge.model.board.BoardCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardCommentController {
	@Autowired
	private BoardCommentService boardCommentService;

	/**
	 * 자유게시판 댓글 등록
	 * @param bcvo
	 * @return
	 */
	@RequestMapping("registerBoardComment.do")
	@ResponseBody
	public List<BoardCommentVO> registerBoardComment(BoardCommentVO bcvo) {
		boardCommentService.registBoardComment(bcvo);
		return boardCommentService.getBoardCommentList(bcvo.getCommentBoardNo());
	}
	
	/**
	 * 자유게시판 댓글 삭제
	 * @param bcvo
	 * @return
	 */
	@RequestMapping("deleteBoardComment.do")
	@ResponseBody
	public List<BoardCommentVO> deleteBoardComment(BoardCommentVO bcvo){
		boardCommentService.deleteBoardComment(bcvo.getCommentNo());
		return boardCommentService.getBoardCommentList(bcvo.getCommentBoardNo());
	}
	
	/**
	 * 자유게시판 댓글 수정form 으로 
	 * @param bcvo
	 * @return
	 */
	@RequestMapping("showBoardComment.do")
	@ResponseBody
	public List<BoardCommentVO> showBoardComment(BoardCommentVO bcvo){
		return boardCommentService.getBoardCommentList(bcvo.getCommentBoardNo());
	}
	
	/**
	 * 자유게시판 수정 완료
	 * @param bcvo
	 * @return
	 */
	@RequestMapping("updateBoardComment.do")
	@ResponseBody
	public List<BoardCommentVO> updateBoardComment(BoardCommentVO bcvo){
		boardCommentService.updateBoardComment(bcvo);
		return boardCommentService.getBoardCommentList(bcvo.getCommentBoardNo());
	}
}