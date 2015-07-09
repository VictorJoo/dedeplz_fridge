package org.dedeplz.fridge.model.recipe;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dedeplz.fridge.model.member.MemberVO;
import org.dedeplz.fridge.model.recipe.paging.FavoriteListVO;

public interface RecipeService {
	public void insertRecipeItem(RecipeItemVO rivo);
	public RecipeVO getRecipeInfo(int recipeNo);
	public int registerRecipe(RecipeVO rvo,String items,List<FileVO> fvoList) throws Exception;
	public String getItemTag(int recipeNo);
	public void deleteRecipeAll(String id,int recipeNo);
	public List<String> getAllRecipeNo();
	public int getRecipeNoByPath(String filePath);
	public void updateRecipe(RecipeVO rvo);
	public void insertRecipeFile(RecipeVO rvo,List<FileVO> fvoList);
	public void insertRecipeItem(RecipeVO rvo, String items);
	
	public String registerFavorite(FavoriteVO fvo);
	public FavoriteListVO getFavoriteRecipeList(String pageNo, String id);
	public void deleteFavorite(FavoriteVO fvo);
	
	public RecipeVO getRecipeInfoNoHits(int recipeNo);
	public int getFavoriteRecipe(HashMap<String,Object> map);
	public List<String> getMyRecipeList(String id);
	
	//수정 수선대로
	public List<HashMap<String, Object>> getFileLastNamePath();
	public List<HashMap<String, Object>> getTopFileLastNamePath();
	public Map<String, Object> getSearchRecipeInfo(String items);
	public Map<String, Object> getShowContentsInfo(int recipeNo);
	public Map<String, Object> getUpdateFormInfo(int recipeNo);
	public void updateGood(String memberId, int recipeNo,String goodCase);
	public void updateBad(String memberId, int recipeNo, String badCase);
	public HashMap<String, Object> checkGoodAndBad(String memberId, int recipeNo);
	public List<HashMap<String, Object>> getFavoriteInfo(MemberVO mvo);
	public Map<String, Object> getMyRecipeInfo(String id);
	public List<String> getItemListByPart(String value);
	public void deleteRecipeRelationInfo(int recipeNo);
	public void deleteRecipe(int recipeNo);
	public void updateRecipeNickName(MemberVO vo);
	public List<Integer> getMyGoodAndBadNoList(String id);
	public void deleteGoobAndBadAll(int gnbNo);
	public List<Integer> getMyFavoriteNoList(String id);
	public void deleteFavoriteAll(int favoriteNo);
}
