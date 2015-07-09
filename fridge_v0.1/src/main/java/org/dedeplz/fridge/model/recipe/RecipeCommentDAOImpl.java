package org.dedeplz.fridge.model.recipe;

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
 *  레시피 번호를 이용
 *  해당 레시피에 등록된 
 *  댓글 리스트를 받아온다 
 */
@Override
public List<RecipeCommentVO> getCommentRecipeList(int recipeNo) {
   return sqlSessionTemplate.selectList("recipeComment.getCommentRecipeList",recipeNo);
}
/**
 * 답글 등록
 */
@Override
public void insertRecomment(RecipeCommentVO rcvo) {
    sqlSessionTemplate.insert("recipeComment.insertRecomment",rcvo);
}
/**
 * 댓글 답글 삭제
 */
@Override
public void deleteComment(int commentNo) {
    sqlSessionTemplate.delete("recipeComment.deleteComment",commentNo);
   
}
/**
 * 댓글 답글 수정
 */
@Override
public void updateRecomment(RecipeCommentVO rcvo) {
   sqlSessionTemplate.update("recipeComment.updateRecomment",rcvo);
   
}
/**
 * 레시피 번호를 통해 
 * 해당 레시피의 총 댓글수를 가져와 리턴한다.
 */
@Override
public int getCountOfCommentByRecipeNo(int recipeNo) {
	return sqlSessionTemplate.selectOne("recipeComment.getCountOfCommentByRecipeNo",recipeNo);
}
/**
 * 레시피 번호를 이용
 * 해당 레시피에 등록된 댓글을 삭제
 */
@Override
public void deleteRecipeCommentByRecipeNo(int recipeNo) {
	sqlSessionTemplate.delete("recipeComment.deleteRecipeCommentByRecipeNo",recipeNo);
}
/**
 * 닉네임을 이용
 * 해당 닉네임으로 작성된 모든 댓글의
 * 번호를 받아온다.
 */
@Override
public List<Integer> getMyCommentNoListByNick(String nick) {
	return sqlSessionTemplate.selectList("recipeComment.getMyCommentNoListById", nick);
}
/**
 * commentNo를 이용
 * 해당 댓글을 삭제
 */
@Override
public void deleteRecipeCommentByCommentNo(int commentNo) {
	sqlSessionTemplate.delete("recipeComment.deleteRecipeCommentByCommentNo",commentNo);	
}
}