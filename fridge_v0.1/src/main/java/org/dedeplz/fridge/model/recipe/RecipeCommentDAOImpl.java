package org.dedeplz.fridge.model.recipe;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeCommentDAOImpl implements RecipeCommentDAO {
@Resource(name = "sqlSessionTemplate")
private SqlSessionTemplate sqlSessionTemplate;

/**
 * 댓글 입력하기
 */
 @Override
public void insertComment(RecipeCommentVO rcvo){
	 sqlSessionTemplate.insert("recipeComment.insertComment",rcvo);
 }
/**
 *  commentNo로 댓글정보 찾기
 */
@Override
public RecipeCommentVO getCommentInfoByCommentNo(int commentNo) {
	return sqlSessionTemplate.selectOne("recipeComment.getCommentInfoByCommentNo",commentNo);
}

/**
 *  recipeNo 에 해당하는 댓글 개수
 */
@Override
public int totalCommentByRecipeNo(int recipeNo){
	System.out.println("dao totalCommentByRecipeNo: "+recipeNo);
	return sqlSessionTemplate.selectOne("recipeComment.totalCommentByRecipeNo",recipeNo);
}
/**
 *  한페이지에 해당하는 댓글 리스트 
 */
@Override
public List<RecipeCommentVO> getCommentRecipeList(int recipeNo) {
	return sqlSessionTemplate.selectList("recipeComment.getCommentRecipeList",recipeNo);
}
/**
 * 
 */
@Override
public void insertRecomment(RecipeCommentVO rcvo) {
	 sqlSessionTemplate.insert("recipeComment.insertRecomment",rcvo);
	
}

}
