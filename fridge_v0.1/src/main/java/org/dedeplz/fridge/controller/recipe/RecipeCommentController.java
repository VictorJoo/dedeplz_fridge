package org.dedeplz.fridge.controller.recipe;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
 * 댓글 입력하기(확인 눌렀을때) 
 */
/*@RequestMapping("insertComment.do")
 public Object insertComment(RecipeCommentVO rcvo,HttpSession session,Model model){
	System.out.println("controller insertComment rcvo :  "+rcvo);
	 MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
	 System.out.println("controller memberVO :"+memberVO);
	 rcvo.setCommentNick(memberVO.getNick());
	 recipeCommentService.insertComment(rcvo);	
	 System.out.println(rcvo.getCommentNo());	 
	 return  recipeCommentService.getCommentRecipeList(rcvo.getCommentRecipeNo());
	 
 }*/

/**
 * 댓글에 대한 댓글 입력하기(댓글 눌렀을때) 
 */
@RequestMapping("insertRecomment.do")

public Object insertRecomment(RecipeCommentVO rcvo,HttpSession session,Model model){
	System.out.println("controller insertComment rcvo :  "+rcvo);
	MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
	System.out.println("controller memberVO :"+memberVO);
	rcvo.setCommentNick(memberVO.getNick());
	recipeCommentService.insertComment(rcvo);	
	System.out.println(rcvo.getCommentNo());	 
	return  recipeCommentService.getCommentRecipeList(rcvo.getCommentRecipeNo());
	
}

	/**
	 * 댓글창에 댓글 리스트로 뿌리기(댓글달기 클릭시 처음뿌리기)
	 */
@RequestMapping("getCommentRecipeList.do")
public String getCommentRecipeList(RecipeCommentVO rcvo, Model model) {
	System.out.println("getCommentRecipeList.do  recipeNo:"+rcvo.getCommentRecipeNo());
	List<RecipeCommentVO> clvo = recipeCommentService.getCommentRecipeList(rcvo.getCommentRecipeNo());
	model.addAttribute("clvo", clvo);
	model.addAttribute("recipeNo", rcvo.getCommentRecipeNo());	
	return "/posting/commentRecipeForm";
}
@RequestMapping("registerComment.do")
public String registerComment(RecipeCommentVO rcvo,Model model,HttpSession session){
	MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
	rcvo.setCommentNick(memberVO.getNick());
	recipeCommentService.insertComment(rcvo);	
	List<RecipeCommentVO> clvo=recipeCommentService.getCommentRecipeList(rcvo.getCommentRecipeNo());
	model.addAttribute("clvo", clvo);
	model.addAttribute("recipeNo", rcvo.getCommentRecipeNo());
	return "/posting/commentRecipeForm";
}
@RequestMapping("registerRecomment.do")
public String registerRecomment(RecipeCommentVO rcvo,HttpSession session,Model model){
	System.out.println("rcvo:asdfsa"+rcvo);
	MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
	rcvo.setCommentRecipeNo(rcvo.getCommentRecipeNo());
	rcvo.setCommentRef(rcvo.getCommentNo());
	rcvo.setCommentNick(memberVO.getNick());
	rcvo.setCommentGroup(rcvo.getCommentNo());
	rcvo.setCommentLevel(1);
	recipeCommentService.insertRecomment(rcvo);	
	List<RecipeCommentVO> clvo=recipeCommentService.getCommentRecipeList(rcvo.getCommentRecipeNo());
	model.addAttribute("clvo", clvo);
	model.addAttribute("recipeNo", rcvo.getCommentRecipeNo());
	return "/posting/commentRecipeForm";
}
}
