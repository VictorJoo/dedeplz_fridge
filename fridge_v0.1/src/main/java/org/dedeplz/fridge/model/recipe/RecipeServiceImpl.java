package org.dedeplz.fridge.model.recipe;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.dedeplz.fridge.model.member.MemberVO;
import org.dedeplz.fridge.model.recipe.paging.FavoriteListVO;
import org.dedeplz.fridge.model.recipe.paging.PagingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecipeServiceImpl implements RecipeService{
	
	@Resource(name="recipeDAOImpl")
	private RecipeDAO recipeDAO;
	@Resource(name="uploadPath")
	private String path;
	
	/**
	 * 등록된 모든 레시피의 번호를 받아온다.
	 */
	@Override
	public List<String> getAllRecipeNo() {
		return recipeDAO.getAllRecipeNo();
	}
	
	/**
	 * home.do 에서 사용
	 * 레시피 번호를 목록으로 받아서
	 * 각 레시피의 정보와 마지막 사진,태그,좋아요 수
	 * 를 받아온다
	 */
	@Override
	public List<HashMap<String, Object>> getFileLastNamePath() {
		List<String> recipeNoList =recipeDAO.getAllRecipeNo();			
		return getRecipeInfoList(recipeNoList);
	}

	/**
	 * home.do 에서 사용
	 * top3에 해당하는 레시피 목록을 받아서
	 * 해당 레시피들의 정보(레시피 정보,마지막 사진 주소,
	 * 태그,좋아요 수를 받아온다.)
	 */
	@Override
	public List<HashMap<String, Object>> getTopFileLastNamePath() {
		List<String> topRecipeNoList =getTopPointRecipeList();		
		return getRecipeInfoList(topRecipeNoList);
	}

	  /**
	    *  (No Hits!!)레시피 번호를 이용해서 해당 레시피의 정보를 받아온다
	    */
	   @Override
	   public RecipeVO getRecipeInfoNoHits(int recipeNo) {
	      RecipeVO rvo=recipeDAO.getRecipeInfo(recipeNo);
	      return rvo;
	   }
	   
	   /**
		 * 레시피 아이템을 받아와서 테그 형식으로 변환
		 */
		@Override
		public String getItemTag(int recipeNo) {
			List<String> itemNoList=recipeDAO.getItemNoList(recipeNo);
			String tag="";
			for(int i=0;i<itemNoList.size();i++){
				int itemNo=Integer.parseInt(itemNoList.get(i));
				String itemName=recipeDAO.getItemNameByItemNo(itemNo);
				tag+="#"+itemName;
			}
			return tag;
		}  
	
	/**
	 * searchRecipe.do에서 사용
	 * 입력한 테크를 이용해 레시피 번호를 받아온다
	 * 레시피 번호를 이용해서 해당 레시피의 정보
	 * 마지막 사진 주소,태그,좋아요 수를 받아온다
	 */
	@Override
	public Map<String, Object> getSearchRecipeInfo(String items) {
		List<String> recipeNoList = getRecipeNoByItem(items);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("recipeNoList", recipeNoList.size());
		resultMap.put("filePath", getRecipeInfoList(recipeNoList));
		return resultMap;
	}
	
	/**
	 * 레시피 no를 태크,레시피의 모든 파일패스,
	 * 모든 good과 모든 bad의 수를 받아온다
	 */
	@Override
	public Map<String, Object> getShowContentsInfo(int recipeNo) {
		Map<String, Object> resultMap =new HashMap<String, Object>();
		List<String> allFilePath = recipeDAO.getAllFilePahtByRecipeNo(recipeNo);
		String tag = getItemTag(recipeNo);
		int totalGood=recipeDAO.getTotalGood(recipeNo);
		int totalBad=recipeDAO.getTotalBad(recipeNo);
		resultMap.put("tag",tag);
		resultMap.put("allFilePath", allFilePath.toString());
		resultMap.put("totalGood", totalGood);
		resultMap.put("totalBad", totalBad);
		return resultMap;
	}
	
	
	/**
	 * 태그를 이용한 레시피 검색
	 */
	public List<String> getRecipeNoByItem(String items) {
		StringTokenizer st = new StringTokenizer(items,"#"); 	
		List<String> recipeList=null;
		List<String> firstRecipeList = null;
		List<String> itemList = new ArrayList<String>();
		List<String> searchResult = new ArrayList<String>();
		while (st.hasMoreTokens()){ 
			 String temp = st.nextToken(); 
			 itemList.add(temp.trim());
		 }
		Set<String> set = new LinkedHashSet<String>();
		for (int i = 0; i < itemList.size(); i++) { //아이템 갯수만큼 for
			recipeList = getRecipeNo(itemList.get(i));  //재료하나에 따른 레시피 검색
			if(i==0){
				firstRecipeList=recipeList;
				if(itemList.size()==1){
					System.out.println("하나일때"+recipeList);
					return recipeList;
				}
			}else{
				for (int j = 0; j < firstRecipeList.size(); j++) { // 재료 하나에 의한 레시피 리스트
					for (int j2 = 0; j2 < recipeList.size(); j2++) {						
						if (firstRecipeList.get(j).equals(recipeList.get(j2))) {							
							set.add(recipeList.get(j));							
						}
					}
				}
			}			
		}
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			searchResult.add(it.next());
		}
		
		return searchResult;
	}
	
	
	/**
	 * 아이템 등록(아이템 네임 중복 확인)
	 * @param itemName
	 */
	public void itemUpdate(String itemName){
		int no=recipeDAO.getCountItemNo(itemName);
		if(no==0){
			recipeDAO.insertItem(itemName);
		}	     
	}
	/**
	 * 아이템 이름으로 해당 아이템 번호 받아온다. 
	 */
	public int getItemNo(String itemName) {
		int no=recipeDAO.getItemNo(itemName);
		return no;
	}
	/**
	 * 해당 아이템 이름으로	 레시피 정보를 받아온다.
	 */
	public List<String> getRecipeNo(String item){
		return recipeDAO.getRecipeNoByItem(item);
	}
	/**
	 * 레시피 아이템 테이블에 정보 입력
	 */
	@Override
	public void insertRecipeItem(RecipeItemVO rivo) {
		recipeDAO.insertRecipeItem(rivo);		
	}
	/**
	 * 레시피 등록
	 */
	@Override
	@Transactional
	public int registerRecipe(RecipeVO rvo,String items,List<FileVO> fvoList) throws Exception {
			recipeDAO.registerRecipe(rvo);
			String id=rvo.getMemberId();
			insertRecipeItem(rvo,items);
			int recipeNo=rvo.getRecipeNo();
			System.out.println(path);
			String uploadPath=path+id;
			File file=new File(uploadPath);
			System.out.println(file.isDirectory());
			if(!file.exists()){
				file.mkdir();
			}
			insertRecipeFile(rvo,fvoList);
			return recipeNo;
	}
	/**
	 * file 테이블 정보 등록
	 */
	@Override
	@Transactional
	public void insertRecipeFile(RecipeVO rvo,List<FileVO> fvoList){
		for(int i=0;i<fvoList.size();i++){
			FileVO fvo=new FileVO();
			fvo.setFileName(fvoList.get(i).getFileName());
			fvo.setFilePath(fvoList.get(i).getFilePath());
			fvo.setRecipeNo(rvo.getRecipeNo());
			recipeDAO.insertRecipeFile(fvo);
			System.out.println("fileupload ok:"+fvoList.get(i).getFileName());
		}
	}
	/**
	 * 레시피 번호를 이용해서 해당 레시피의 모든 정보 삭제
	 * (레시피 테이블, 레시피 아이템 테이블, 파일 테이블,추천테이블,아이디 디렉토리 내 사진)
	 */
	@Override
	@Transactional
	public void deleteRecipeAll(String id,int recipeNo) {
		List<String> list = recipeDAO.getFileName(recipeNo);
		File file = new File(path + id);
		File f[] = file.listFiles();
		if(f.length!=0){
			for (int i = 0; i < list.size(); i++) {
				for (int y = 0; y < f.length; y++) {
					if (f[y].getName().equals(list.get(i))) {
						f[y].delete();
					}//if
				}//for
			}//for
		}	
		int gnBNoAllCount=recipeDAO.getGoodAndBadNoCountByRecipeNo(recipeNo);
		int favoriteNoAllCount=recipeDAO.getFavoriteNoAllList(recipeNo);
		List<Integer> recipeCommentNoList=recipeDAO.getCommentNoListByRecipeNo(recipeNo);
		recipeDAO.deleteRecipeFile(recipeNo);
		recipeDAO.deleteRecipeItem(recipeNo);
		if(gnBNoAllCount!=0){
			recipeDAO.deleteGoodAndBad(recipeNo);
		}
		if(favoriteNoAllCount!=0){
			recipeDAO.deleteFavorites(recipeNo);
		}
		if(recipeCommentNoList!=null){
			for(int i=0;i<recipeCommentNoList.size();i++){
				recipeDAO.deleteRecipeCommentByCommentNo(recipeCommentNoList.get(i));
			}
		}
		recipeDAO.deleteRecipe(recipeNo);
	}
	
	
	/**
	 * 사진의 path를 이용해 해당 레시피의 번호를 받아온다.
	 */
	@Override
	public int getRecipeNoByPath(String filePath) {
		return recipeDAO.getRecipeNoByPath(filePath);
	}
	/**
	 * 레시피 번호를 이용해 해당 레시피의 모든 사진 이름을 받아온다.
	 */
/*	@Override
	public List<String> getFileName(int recipeNo) {
		List<String> list=recipeDAO.getFileName(recipeNo);
		return list;
	}*/
	/**
	 * 레시피 수정
	 */
	@Override
	public void updateRecipe(RecipeVO rvo) {
		recipeDAO.deleteRecipeFile(rvo.getRecipeNo());
	    recipeDAO.deleteRecipeItem(rvo.getRecipeNo());
		recipeDAO.updateRecipe(rvo);
	}
	/**
	 * 태크 값을 이용해서 recipe_item 테이블에 정보 입력
	 */
	@Override
	@Transactional
	public void insertRecipeItem(RecipeVO rvo,String items){
		StringTokenizer st = new StringTokenizer(items,"#"); 	
		while (st.hasMoreTokens()){ 
			 RecipeItemVO rivo=new RecipeItemVO();
			 String temp = st.nextToken(); 
			 itemUpdate(temp.trim());
			 int no=getItemNo(temp.trim());
			 System.out.println(no);
			 //레시피 아이템 등록
			 rivo.setItemNo(no);
			 rivo.setRecipeNo(rvo.getRecipeNo());
			 recipeDAO.insertRecipeItem( rivo );
		 }
	}
	/**
	   * 로그인 한 사용자가 레시피를 즐겨찾기 등록
	 */
	@Override
	public String registerFavorite(FavoriteVO fvo) {
		String str = "fail";
		if(getRecipeNoById(fvo) == -1){
			recipeDAO.registerFavorite(fvo);
			str = "ok";	
		}
		return str;
	}
	/**
	 * recipeNo 중복 체크 
	 */
	 public int getRecipeNoById(FavoriteVO fvo){
		int  index = -1;
	    List<Integer> recipeList = recipeDAO.findRecipeNoById(fvo.getMemberId());
	    System.out.println(recipeList);
	    for(int i = 0; i < recipeList.size(); i++){
	        if(fvo.getRecipeNo() == recipeList.get(i)){
	           index = i;
	        }
	    }
	    return index;
	}
	   @Override
	   public FavoriteListVO getFavoriteRecipeList(String pageNo, String id) {
	      if(pageNo==null||pageNo=="") {
	         pageNo="1";
	      }
	      Map< String, Object> favoriteMap = new HashMap<String, Object>();
	      favoriteMap.put("pageNo", pageNo);
	      favoriteMap.put("id", id);
	      List<FavoriteVO> favoriteList=recipeDAO.getFavoriteRecipeList(favoriteMap);
	      System.out.println(favoriteList);
	      int total=recipeDAO.totalFavoriteContent(id);
	      System.out.println("토탈"+total);
	      PagingBean paging=new PagingBean(total,Integer.parseInt(pageNo));
	      FavoriteListVO lvo=new FavoriteListVO(favoriteList,paging);
	      return lvo;
	   }
	   
	   /**
	    * 즐겨찾기 삭제
	    */
	   @Override
	   public void deleteFavorite(FavoriteVO fvo) {
	      recipeDAO.deleteFavorite(fvo);
	   }
	   
	   /**
	    * 레시피 번호를 이용해서 해당 레시피의 정보를 받아온다.
	    */
	   @Override
	   public RecipeVO getRecipeInfo(int recipeNo) {
	      recipeDAO.updateHitsByRecipeNo(recipeNo);
	      RecipeVO rvo=recipeDAO.getRecipeInfo(recipeNo);
	      return rvo;
	   }
	 
	   /**
	    * 
	    */
	@Override
	public int getFavoriteRecipe(HashMap<String,Object> map) {
		System.out.println(map);
		return recipeDAO.getFavoriteRecipe(map);
	}
	/**
	 * 
	 */
	@Override
	public List<String> getMyRecipeList(String id) {
		return recipeDAO.getMyRecipeList(id);
	}
	/**
	 * 
	 */
	public List<String> getTopPointRecipeList(){
		List<String> list = getAllRecipeNo();
		List<TopRecipeVO> pointOfRecipeList = new ArrayList<TopRecipeVO>();
		List<String> topList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			TopRecipeVO tvo = new TopRecipeVO();			
			tvo.setRecipeNo(Integer.parseInt(list.get(i)));
			tvo.setTotalGood(recipeDAO.getTotalGood(Integer.parseInt(list.get(i))));
			pointOfRecipeList.add(tvo);
		}
		Collections.sort(pointOfRecipeList, new NoDescCompare());
		
		for (int i = 0; i < 3; i++) {
			topList.add(String.valueOf(pointOfRecipeList.get(i).getRecipeNo()));
		}
		return topList;
		
	}
	/**
	 * No 내림차순
	 * @author Ju
	 *
	 */
	static class NoDescCompare implements Comparator<TopRecipeVO> {
 
		/**
		 * 내림차순(DESC)
		 */
		@Override
		public int compare(TopRecipeVO arg0, TopRecipeVO arg1) {
			return arg0.getTotalGood() > arg1.getTotalGood() ? -1 : arg0.getTotalGood() < arg1.getTotalGood() ? 1:0;
		}
	}
	@Override
	public Map<String, Object> getUpdateFormInfo(int recipeNo) {
		Map<String ,Object> map=new HashMap<String, Object>();
		RecipeVO rvo = getRecipeInfoNoHits(recipeNo);
		String tag = getItemTag(recipeNo);
		map.put("rvo", rvo);
		map.put("tag", tag);
		return map;
	}
	/**
	 * 아이디와 레시피 no,goodCase를 이용해서
	 * good정보 수정
	 */
	@Override
	public void updateGood(String memberId, int recipeNo,String goodCase) {	
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("recipeNo", recipeNo);
		if(goodCase.equals("0")){
			recipeDAO.insertGood(map);
		}else if(goodCase.equals("1")){
			recipeDAO.updateCancleGood(map);
		}else if(goodCase.equals("2")){
			recipeDAO.updateUpGood(map);
		}
	}
	/**
	 * 아이디와 레시피 no,badCase를 이용해서
	 * bad정보 수정
	 */
	@Override
	public void updateBad(String memberId, int recipeNo, String badCase) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("recipeNo", recipeNo);
		if(badCase.equals("0")){
			recipeDAO.insertBad(map);
		}else if(badCase.equals("1")){
			recipeDAO.updateCancleBad(map);
		}else if(badCase.equals("2")){
			recipeDAO.updateUpBad(map);
		}
	}
	/**
	 * 추천 비추천 테이블 유무 체크 및
	 * good bad 값 호출
	 */
	@Override
	public HashMap<String, Object> checkGoodAndBad(String memberId, int recipeNo) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("recipeNo", recipeNo);
		int count=recipeDAO.getGoodAndBadNoCountByRecipeNoAndMemberId(map);
		int good=recipeDAO.getGood(map);
		int bad=recipeDAO.getBad(map);
		HashMap<String,Object> goodAndBadResult=new HashMap<String, Object>();
		goodAndBadResult.put("count", count);
		goodAndBadResult.put("good",good);
		goodAndBadResult.put("bad", bad);
		return goodAndBadResult;
	}
	
	/**
	 * 자신이 추가한 즐겨찾기 리스트를 가져와 home에 출력한다.
	 */
	@Override
	public List<HashMap<String, Object>> getFavoriteInfo(MemberVO mvo) {
		List<String> fList = recipeDAO.getFavoriteListByMemberId(mvo.getId());
		return getRecipeInfoList(fList);
	}

	/**
	 * 자기가 쓴 레시피 정보를 출력한다.
	 */
	@Override
	public Map<String, Object> getMyRecipeInfo(String id) {
		List<String> recipeNoList = getMyRecipeList(id);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("recipeNoList", recipeNoList.size());
		resultMap.put("filePath", getRecipeInfoList(recipeNoList));
		return resultMap;
	}
	
	/**
	 * 레시피 번호 리스트를 매개변수로 받아
	 * 각 레시피의 정보들을 맵에 put 하여 list에 add 한뒤
	 * 리턴한다.
	 * @param recipeNoList
	 * @return
	 */
	public List<HashMap<String, Object>> getRecipeInfoList(List<String> recipeNoList){		
		List<HashMap<String,Object>> fileLastNamePath = new ArrayList<HashMap<String,Object>>();		
		for (int i = 0; i < recipeNoList.size(); i++) {
			String fileLastPath =recipeDAO.getFileLastNamePath(recipeNoList.get(i));
			 RecipeVO rvo=getRecipeInfoNoHits(Integer.parseInt(recipeNoList.get(i)));
			 String tag=getItemTag(Integer.parseInt(recipeNoList.get(i)));
			 int goodPoint =recipeDAO.getTotalGood(Integer.parseInt(recipeNoList.get(i)));
			 int commentCount =recipeDAO.getCountOfCommentByRecipeNo(Integer.parseInt(recipeNoList.get(i)));
			 
			 HashMap<String, Object> map=new HashMap<String, Object>();
	         map.put("rvo",rvo);
	         map.put("fileLastPath", fileLastPath);
	         map.put("tag", tag);
	         map.put("goodPoint",goodPoint);
	         map.put("commentCount", commentCount);
	         fileLastNamePath.add(map);
		}
		return fileLastNamePath;
	}
	/**
	 * 닉네임으로 해당 댓글의 모든 commentNo를 
	 * 받아온다
	 */
	@Override
	public List<Integer> getMyCommentNoListByNick(String nick) {
		return recipeDAO.getMyCommentNoListByNick(nick);
	}
	/**
	 * commentNo를 이용
	 * 정보를 삭제
	 */
	@Override
	public void deleteAllRecipeCommentByCommnetNo(int commentNo) {
		recipeDAO.deleteRecipeCommentByCommentNo(commentNo);
	}
	
	
}
