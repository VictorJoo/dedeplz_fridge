package org.dedeplz.fridge.model.recipe;

import java.util.List;

public interface RecipeCommentService {

	public void insertComment(RecipeCommentVO rcvo);
	public RecipeCommentVO getCommentInfoByCommentNo(int commentNo);
	public List<RecipeCommentVO>  getCommentRecipeList(int recipeNo);
	public void insertRecomment(RecipeCommentVO rcvo);
}
