package org.dedeplz.fridge.model.recipe;


import java.util.List;

import org.dedeplz.fridge.model.recipe.paging.ListVO;

public interface RecipeService {
	public  ListVO getRecipeList(String pageNo);
	public int getItemNo(String itemName);
	public void insertRecipeItem(RecipeItemVO rivo);
	public RecipeVO getRecipeInfo(int recipeNo);
	public int registerRecipe(RecipeVO rvo,String items,List<FileVO> fvoList) throws Exception;
	public String getItemTag(int recipeNo);
	public List<String> getFilePath(int recipeNo);
	public void deleteRecipeAll(int recipeNo);
	public List<String> getAllRecipeNo();
	public String getFileLastNo(String recipeNo);
	public String getFileLastNamePath(String fileLastNo);
	public int getRecipeNoByPath(String filePath);
	public List<String> getAllFilePahtByRecipeNo(int recipeNo);
	public List<String> getFileName(int recipeNo);
	public void updateRecipe(RecipeVO rvo);
	void insertRecipeFile(RecipeVO rvo,List<FileVO> fvoList);
	public void deleteRecipeFile(int recipeNo);
	public void deleteRecipeItem(int recipeNo);
	public void insertRecipeItem(RecipeVO rvo, String items);
	public List<String> getRecipeNoByItem(String items);
}
