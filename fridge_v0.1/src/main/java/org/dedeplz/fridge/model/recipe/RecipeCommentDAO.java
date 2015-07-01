package org.dedeplz.fridge.model.recipe;

import java.util.HashMap;
import java.util.List;

public interface RecipeCommentDAO {

	public  void insertComment(RecipeCommentVO rcvo);
	public RecipeCommentVO getCommentInfoByCommentNo(int commentNo);
    public int totalCommentByRecipeNo(int recipeNo);
    public List<RecipeCommentVO> getCommentRecipeList(int recipeNo);
	public void insertRecomment(RecipeCommentVO rcvo);
	
}