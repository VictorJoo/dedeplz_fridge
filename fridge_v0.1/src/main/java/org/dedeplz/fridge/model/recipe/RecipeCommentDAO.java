package org.dedeplz.fridge.model.recipe;

import java.util.List;

public interface RecipeCommentDAO {

   public  void insertComment(RecipeCommentVO rcvo);
    public int totalCommentByRecipeNo(int recipeNo);
    public List<RecipeCommentVO> getCommentRecipeList(int recipeNo);
   public void insertRecomment(RecipeCommentVO rcvo);
   public void deleteComment(int commentNo);
   public void updateRecomment(RecipeCommentVO rcvo);
   
}