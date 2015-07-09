package org.dedeplz.fridge.model.recipe;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class RecipeCommentServiceImpl implements  RecipeCommentService{
@Resource(name="recipeCommentDAOImpl")
private RecipeCommentDAO recipeCommentDAO;

/**
 * 댓글 입력하기
 */
 public void insertComment(RecipeCommentVO rcvo){
    recipeCommentDAO.insertComment(rcvo);   
 }
 /**
  * 댓글 리스트로 갖고오기.
  */
@Override
public List<RecipeCommentVO>  getCommentRecipeList(int recipeNo){   
   List<RecipeCommentVO> list=recipeCommentDAO.getCommentRecipeList(recipeNo);
      return list;
}
/**
 * 댓글 입력
 */
@Override
public void insertRecomment(RecipeCommentVO rcvo) {
   recipeCommentDAO.insertRecomment(rcvo);
}
/**
 * 댓글 번호를 이용
 * 해당 댓글을 삭제
 */
@Override
public void deleteComment(int commentNo) {
   recipeCommentDAO.deleteComment(commentNo); 
}
/**
 * 답글 수정
 */
@Override
public void updateRecomment(RecipeCommentVO rcvo) {
   recipeCommentDAO.updateRecomment(rcvo);
}
/**
 * 레시피 번호를 이용
 * 해당 레시피에 등록된 댓글의 총 갯수를 받아온다
 */
@Override
public int getCountOfCommentByRecipeNo(int recipeNo) {
	return recipeCommentDAO.getCountOfCommentByRecipeNo(recipeNo);
}
/**
 * 레시피 번호를 이용
 * 해당 레시피에 등록된
 * 댓글을 삭제
 */
@Override
public void deleteRecipeCommentByRecipeNo(int recipeNo) {
	recipeCommentDAO.deleteRecipeCommentByRecipeNo(recipeNo);
}
/**
 * 닉네임을 이용
 * 해당 닉네임으로 작성된 레시피 댓글의
 * 모든 no를 받아온다
 */
@Override
public List<Integer> getMyCommentNoListByNick(String nick) {
	return recipeCommentDAO.getMyCommentNoListByNick(nick);
}
/**
 * 레시피 댓글 번호를 이용
 * 등록된 댓글을 모두 삭제
 */
@Override
public void deleteAllRecipeCommentByCommnetNo(int commentNo) {
	recipeCommentDAO.deleteRecipeCommentByCommentNo(commentNo);
}
}