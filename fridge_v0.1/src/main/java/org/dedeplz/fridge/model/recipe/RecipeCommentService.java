package org.dedeplz.fridge.model.recipe;

import java.util.List;

public interface RecipeCommentService {

   public void insertComment(RecipeCommentVO rcvo);
   public List<RecipeCommentVO>  getCommentRecipeList(int recipeNo);
   public void insertRecomment(RecipeCommentVO rcvo);
   public void deleteComment(int commentNo);
   public void updateRecomment(RecipeCommentVO rcvo);
   public int getCountOfCommentByRecipeNo(int recipeNo);
   public void deleteRecipeCommentByRecipeNo(int recipeNo);
   public List<Integer> getMyCommentNoListByNick(String nick);
   public void deleteAllRecipeCommentByCommnetNo(int commentNo);
}