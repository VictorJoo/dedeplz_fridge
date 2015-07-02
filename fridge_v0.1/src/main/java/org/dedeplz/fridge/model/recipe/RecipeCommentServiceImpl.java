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
@Override
public void insertRecomment(RecipeCommentVO rcvo) {
   recipeCommentDAO.insertRecomment(rcvo);
}
@Override
public void deleteComment(int commentNo) {
   recipeCommentDAO.deleteComment(commentNo);
   
}
@Override
public void updateRecomment(RecipeCommentVO rcvo) {
   recipeCommentDAO.updateRecomment(rcvo);
   
}
}