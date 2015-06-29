package org.dedeplz.fridge.model.recipe;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDAOImpl implements RecipeDAO {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 레시피 리스트를 받아온다
	 */
	@Override
	public List<RecipeVO> getRecipeList(String pageNo) {
		return sqlSessionTemplate.selectList("recipe.getRecipeList", pageNo);
	}

	/**
	 * 등록된 레시피 총 수
	 */
	@Override
	public int totalContent() {
		return sqlSessionTemplate.selectOne("recipe.totalContent");
	}

	/**
	 * 레시피 아이템 등록
	 */
	@Override
	public void insertRecipeItem(RecipeItemVO rivo) {
		sqlSessionTemplate.insert("recipe.insertRecipeItem", rivo);
	}

	/**
	 * 재료 등록
	 */
	@Override
	public void insertItem(String itemName) {
		sqlSessionTemplate.insert("recipe.insertItem", itemName);
	}

	/**
	 * 재료 등록 유무 체크
	 */
	@Override
	public int getCountItemNo(String itemName) {
		return sqlSessionTemplate.selectOne("recipe.getCountItemNo", itemName);
	}

	/**
	 * 재료 이름으로 재료 번호 찾기
	 */
	@Override
	public int getItemNo(String itemName) {
		return sqlSessionTemplate.selectOne("recipe.getItemNo", itemName);
	}

	/**
	 * 레시피 정보
	 */
	@Override
	public RecipeVO getRecipeInfo(int recipeNo) {
		return sqlSessionTemplate.selectOne("recipe.showRecipe", recipeNo);
	}

	/**
	 * 레시피 정보 등록
	 */
	@Override
	public void registerRecipe(RecipeVO rvo) {
		sqlSessionTemplate.insert("recipe.registerRecipe", rvo);
	}

	/**
	 * recipe_file 테이블에 정보 등록
	 */
	@Override
	public void insertRecipeFile(FileVO fvo) {
		sqlSessionTemplate.insert("recipeFile.insertRecipeFile", fvo);

	}

	/**
	 * 해당 레시피의 아이템 no List
	 */
	@Override
	public List<String> getItemNoList(int recipeNo) {
		return sqlSessionTemplate.selectList("recipe.getRecipeItemList",
				recipeNo);
	}

	/**
	 * 아이템 번호로 아이템 이름 찾기
	 */
	@Override
	public String getItemNameByItemNo(int itemNo) {
		return sqlSessionTemplate.selectOne("recipe.getItemNameByItemNo",
				itemNo);
	}

	/**
	 * 해당 레시피의 사진 주소 찾기
	 */
	@Override
	public List<String> getFilePath(int recipeNo) {
		return sqlSessionTemplate
				.selectList("recipeFile.getFilePath", recipeNo);
	}

	/**
	 * 레시피 삭제
	 */
	@Override
	public void deleteRecipe(int recipeNo) {
		sqlSessionTemplate.delete("recipe.deleteRecipe", recipeNo);
	}

	/**
	 * 레시피 아이템 삭제
	 */
	@Override
	public void deleteRecipeItem(int recipeNo) {
		sqlSessionTemplate.delete("recipe.deleteRecipeItem", recipeNo);
	}

	/**
	 * 파일 삭제
	 */
	@Override
	public void deleteRecipeFile(int recipeNo) {
		sqlSessionTemplate.delete("recipeFile.deleteRecipeFile", recipeNo);
	}

	/**
	 * 모든 recipe_no
	 */
	@Override
	public List<String> getAllRecipeNo() {
		return sqlSessionTemplate.selectList("recipe.getAllRecipeNo");
	}

	/**
	 * 마지막 사진 번호
	 */
	@Override
	public String getFileLastNo(String recipeNo) {
		return sqlSessionTemplate.selectOne("recipeFile.getFileLastNo", recipeNo);
	}

	/**
	 * 메인에 들어갈 레시피의 마지막 사진 주소
	 */
	@Override
	public String getFileLastNamePath(String fileLastNo) {
		return sqlSessionTemplate.selectOne("recipeFile.getFileLastNamePath",fileLastNo);
	}

	/**
	 * 메인의 사진 주소로 그 레시피의 번호 받아오기
	 */
	@Override
	public int getRecipeNoByPath(String filePath) {
		return sqlSessionTemplate.selectOne("recipeFile.getRecipeNoByPath",
				filePath);
	}

	/**
	 * 레시피 번호에 해당하는 모든 사진의 path를 받아온다
	 */
	@Override
	public List<String> getAllFilePahtByRecipeNo(int recipeNo) {
		return sqlSessionTemplate.selectList(
				"recipeFile.getAllFilePahtByRecipeNo", recipeNo);
	}

	/**
	 * 레시피 번호에 해당하는 모든 사진의 파일네임을 받아온다.
	 */
	@Override
	public List<String> getFileName(int recipeNo) {
		return sqlSessionTemplate
				.selectList("recipeFile.getFileName", recipeNo);
	}

	/**
	 * 레시피 수정
	 */
	@Override
	public void updateRecipe(RecipeVO rvo) {
		sqlSessionTemplate.update("recipe.updateRecipe", rvo);
	}
	/**
	 * 재료로 레시피 정보를 가져온다.
	 */
	@Override
	public List<String> getRecipeNoByItem(String item) {		
		return sqlSessionTemplate.selectList("recipe.getRecipeNoByItem",item);
	}
	/**
	    * 해당 레시피에 해당하는 모든 good을 리스트로 받아온다
	    */
   @Override
   public Integer getTotalGood(int recipeNo) {
      return sqlSessionTemplate.selectOne("recipe.getTotalGood",recipeNo);
   }
}
