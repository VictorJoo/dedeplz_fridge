package org.dedeplz.fridge.model.recipe;


import java.util.List;

public interface RecipeDAO {
	public  List<RecipeVO> getRecipeList(String pageNo);
	public int totalContent();
	public void insertRecipeItem(RecipeItemVO rivo);
	public void insertItem(String itemName);
	public int getCountItemNo(String itemName);
	public int getItemNo(String itemName);
	public RecipeVO getRecipeInfo(int recipeNo);
	public void registerRecipe(RecipeVO rvo);
	public void insertRecipeFile(FileVO fvo);
	public List<String> getItemNoList(int recipeNo);
	public String getItemNameByItemNo(int itemNo);
	public List<String> getFilePath(int recipeNo);
	public void deleteRecipe(int recipeNo);
	public void deleteRecipeItem(int recipeNo);
	public void deleteRecipeFile(int recipeNo);
	public List<String> getAllRecipeNo();
	public String getFileLastNo(String recipeNo);
	public String getFileLastNamePath(String fileLastNo);
	public int getRecipeNoByPath(String filePath);
	public List<String> getAllFilePahtByRecipeNo(int recipeNo);
	public List<String> getFileName(int recipeNo);
	public void updateRecipe(RecipeVO rvo);
	public List<String> getRecipeNoByItem(String item);
	public Integer getTotalGood(int recipeNo);
}
