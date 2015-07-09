package org.dedeplz.fridge.model.member;

import java.util.List;

import javax.annotation.Resource;

import org.dedeplz.fridge.model.board.BoardCommentService;
import org.dedeplz.fridge.model.board.BoardService;
import org.dedeplz.fridge.model.common.FileManager;
import org.dedeplz.fridge.model.recipe.RecipeCommentService;
import org.dedeplz.fridge.model.recipe.RecipeDAO;
import org.dedeplz.fridge.model.recipe.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
	@Resource
	private MemberDAO memberDAO;
	@Resource
	private RecipeDAO recipeDAO;
	@Resource
	private RecipeService recipeService;
	@Resource
	private BoardService boardService;
	@Resource
	private BoardCommentService boardCommentService;
	@Resource
	private RecipeCommentService recipeCommentService;
	@Resource
	private FileManager fileManager;
       /**
       * 아이디 찾기
       */
   public MemberVO findById(String id) {
      return memberDAO.findById(id);
   }
   /**
    * 회원등록
    */
   @Override
   public void registerMember(MemberVO vo) {
      memberDAO.registerMember(vo);      
   }
   /**
    * 아이디 중복체크
    */
   @Override
   public String idCheck(String id) {
      return memberDAO.idCheck(id);
   }
   /**
    * 로그인
    */
    @Override
      public MemberVO login(MemberVO vo) {
         return memberDAO.login(vo);
      }
   /**
    * 회원 정보 수정
    */
   @Override
   public void updateMember(MemberVO vo) {
      memberDAO.updateMember(vo);
      recipeService.updateRecipeNickName(vo);
   }
   /**
    * 닉네임 중복체크
   */
   @Override
   public String nickCheck(String nick) {
      return memberDAO.nickCheck(nick);
   }
   /**
    * 내 아이디 찾기
    */
      @Override
      public String findMyId(MemberVO vo) {
         return memberDAO.findMyId(vo);
      }
    /**
     * 내 비밀번호 찾기
     */
   @Override
   public String findMyPassword(MemberVO vo) {
      return memberDAO.findMyPassword(vo);
   }
   /**
    * 회원 리스트
    */
   @Override
   public MemberListVO getMemberList(String pageNo) {
      if(pageNo==null||pageNo=="") 
         pageNo="1";
      List<MemberVO> list=memberDAO.getMemberList(pageNo);
      int total=memberDAO.totalMember();
      MemberPagingBean paging=new MemberPagingBean(total,Integer.parseInt(pageNo));
      MemberListVO lvo=new MemberListVO(list,paging);
      return lvo;
   }
   /**
    * 레벨 변경
    */
   @Override
   public void levelChange(MemberVO vo) {
      memberDAO.levelChange(vo);
   }
   /** 좋아요 수에따른
    * 자동 렙업
    */
   @Override
   public void updateMemberGrade(MemberVO vo){
      memberDAO.updateMemberGrade(vo);
   }
   /**
    * 로그인 아이디 이용
    * 아이디의 모든 레시피의 good과 bad를 이용
    * 모든 good과 모든 bad의 합을 totalLove로
    * 리턴 totalLove
    * 
    */
   @Override
   public int getTotalLove(String id) {
      List<String> list = recipeDAO.getMyRecipeList(id);
      int memberTotalGood = 0;
      int memberTotalBad = 0;
      System.out.println(list);
      for (int i = 0; i < list.size(); i++) {
         memberTotalGood += recipeDAO.getTotalGood(Integer.parseInt(list.get(i)));
         memberTotalBad += recipeDAO.getTotalBad(Integer.parseInt(list.get(i)));
      }
      int totalLove = memberTotalGood - memberTotalBad;
      return totalLove;
      
   }
   /**
    * MemberVO의 id를 이용해
    * love값을 업데이트
    */
   @Override
   public void updateMemberLove(MemberVO mvo) {
      memberDAO.updateMemberLove(mvo);

   }
   /**
    * 로그인 한 아이디와 닉네임을 이용
    * 해당 아이디로 탈퇴할 때 회원과 관련된 
    * 모든 정보 삭제
    */
   @Transactional
   @Override
   public void deleteAllMemberInfoByIdAndNick(MemberVO mvo) {  
	   List<Integer> commentNoList = recipeCommentService.getMyCommentNoListByNick(mvo.getNick());
 	  if(commentNoList!=null){
			for (int y = 0; y < commentNoList.size(); y++) {
				recipeCommentService.deleteAllRecipeCommentByCommnetNo(commentNoList.get(y));
			}
		  }
       //아이디로 추천한 좋아요 싫어요 삭제
 	  List<Integer> goobAndBadList=recipeService.getMyGoodAndBadNoList(mvo.getId());
 		if(goobAndBadList!=null){
 			for (int i = 0; i < goobAndBadList.size(); i++) {
 				recipeService.deleteGoobAndBadAll(goobAndBadList.get(i));
 			}
 		}
       //아이디로 등록한 즐겨찾기 리스트 삭제
 		List<Integer> favoriteList =recipeService.getMyFavoriteNoList(mvo.getId());
 		if(favoriteList!=null){
 			for (int i = 0; i < favoriteList.size(); i++) {
 				recipeService.deleteFavoriteAll(favoriteList.get(i));
 			}
 		}
 	  //아이디로 작성된 레시피 번호 받아서 정보 삭제
      List<String> recipeList=recipeService.getMyRecipeList(mvo.getId());
      if(recipeList!=null){
    	  for(int i=0;i<recipeList.size();i++){
    		  recipeService.deleteRecipeRelationInfo(Integer.parseInt(recipeList.get(i)));
    		  recipeService.deleteRecipe(Integer.parseInt(recipeList.get(i)));
      	  }
      }
      //닉네임으로 작성된 보드댓글 삭제
      List<Integer> boardCommentNoList=boardCommentService.getMyBoardCommentNoByNick(mvo.getNick());
      if(boardCommentNoList!=null){
      	for (int i = 0; i < boardCommentNoList.size(); i++) {
				boardCommentService.deleteBoardComment(boardCommentNoList.get(i));
			}
      }
      //아이디로 작성된 게시물 정보 삭제
      List<Integer> boardNoList=boardService.getMyBoardNoListById(mvo.getId());
      if(boardNoList!=null){
     	for(int i=0;i<boardNoList.size();i++){
     		boardService.deleteBoardAll(boardNoList.get(i));
     	}
      }
      fileManager.deleteDirectoryById(mvo.getId());
      memberDAO.deleteMember(mvo);
}
}