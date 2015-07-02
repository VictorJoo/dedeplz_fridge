package org.dedeplz.fridge.controller.recipe;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.dedeplz.fridge.model.common.LoginCheck;
import org.dedeplz.fridge.model.member.MemberVO;
import org.dedeplz.fridge.model.recipe.RecipeCommentService;
import org.dedeplz.fridge.model.recipe.RecipeCommentVO;
import org.dedeplz.fridge.model.recipe.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeCommentController {
@Resource(name="recipeCommentServiceImpl")
private RecipeCommentService recipeCommentService;
@Resource(name="recipeServiceImpl")
private RecipeService recipeService;

/**
 * 댓글창에 댓글 리스트로 뿌리기
 * (레시피에서 댓글 달기 클릭 시,
 * 댓글 입력, 삭제, 답글 입력 수정 삭제 시
 * @param rcvo
 * @param model
 * @return
 */

@RequestMapping("getCommentRecipeList.do")
public String getCommentRecipeList(RecipeCommentVO rcvo, Model model) {
   List<RecipeCommentVO> clvo = recipeCommentService.getCommentRecipeList(rcvo.getCommentRecipeNo());
   model.addAttribute("clvo", clvo);
   model.addAttribute("recipeNo", rcvo.getCommentRecipeNo());   
   return "/posting/commentRecipeForm";
}

/**
 * 댓글 삭제
 * @param rcvo
 * @return
 */
@LoginCheck
@RequestMapping("deleteComment.do")
public String deleteComment(RecipeCommentVO rcvo) {
   recipeCommentService.deleteComment(rcvo.getCommentNo());  
   return "redirect:getCommentRecipeList.do?CommentRecipeNo="+rcvo.getCommentRecipeNo();
}

/**
 * 댓글 입력
 * @param rcvo
 * @param session
 * @return
 */
@LoginCheck
@RequestMapping("registerComment.do")
public String registerComment(RecipeCommentVO rcvo,HttpSession session){
   MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
   rcvo.setCommentNick(memberVO.getNick());
   recipeCommentService.insertComment(rcvo);
   return "redirect:getCommentRecipeList.do?CommentRecipeNo="+rcvo.getCommentRecipeNo();
}
/**
 * 댓글에 답글을 눌러 작성
 * @param rcvo
 * @param session
 * @param model
 * @return
 */
@LoginCheck
@RequestMapping("registerRecomment.do")
public String registerRecomment(RecipeCommentVO rcvo,HttpSession session){
   MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
   System.out.println("memberVO:"+memberVO);
   rcvo.setCommentRecipeNo(rcvo.getCommentRecipeNo());
   rcvo.setCommentNick(memberVO.getNick());
   rcvo.setCommentGroup(rcvo.getCommentGroup());
   rcvo.setCommentLevel(1);
   recipeCommentService.insertRecomment(rcvo);
   return "redirect:getCommentRecipeList.do?CommentRecipeNo="+rcvo.getCommentRecipeNo();
}
/**
 * 답글 수정
 * @param rcvo
 * @return
 */
@LoginCheck
@RequestMapping("updateRecomment.do")
public String updateRecomment(RecipeCommentVO rcvo){
   rcvo.setCommentContents(rcvo.getCommentContents());
   recipeCommentService.updateRecomment(rcvo);
   return "redirect:getCommentRecipeList.do?CommentRecipeNo="+rcvo.getCommentRecipeNo();
}
}