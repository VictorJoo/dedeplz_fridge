package org.dedeplz.fridge.controller.recipe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dedeplz.fridge.model.member.MemberVO;
import org.dedeplz.fridge.model.recipe.FileVO;
import org.dedeplz.fridge.model.recipe.RecipeService;
import org.dedeplz.fridge.model.recipe.RecipeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 레시피 컨트롤러
 * 
 * @author KOSTA
 * 
 */
@Controller
public class RecipeController {
	@Resource(name = "recipeServiceImpl")
	private RecipeService recipeService;
	@Resource(name = "uploadPath")
	public String path;

	/**
	 * multiplePhotoUpload 메서드의 FileVO값을 저장
	 */
	/**
	 * 메인 페이지에 레시피 마지막 사진을 전송
	 * 
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("home.do")
	public ModelAndView home(String pageNo) {
		System.out.println("home");
		List<String> recipeNoList = recipeService.getAllRecipeNo();
		System.out.println(recipeNoList);
		 List<HashMap<String,Object>> fileLastNamePath = new ArrayList<HashMap<String,Object>>();
		System.out.println(fileLastNamePath);
		for (int i = 0; i < recipeNoList.size(); i++) {
			String fileLastNo = recipeService.getFileLastNo(recipeNoList.get(i));
			System.out.println(fileLastNo);
			String fileLastPath = recipeService.getFileLastNamePath(fileLastNo);
			System.out.println(fileLastPath);
			 RecipeVO rvo=recipeService.getRecipeInfo(Integer.parseInt(recipeNoList.get(i)));
			 String tag=recipeService.getItemTag(Integer.parseInt(recipeNoList.get(i)));
			 HashMap<String, Object> map=new HashMap<String, Object>();
	         map.put("rvo",rvo);
	         map.put("fileLastPath", fileLastPath);
	         map.put("tag", tag);
	         fileLastNamePath.add(map);
		}
		return new ModelAndView("home", "fileLastNamePath", fileLastNamePath);
	}
	/**
	 * 레시피 검색
	 * @param items
	 * @return
	 */
    @RequestMapping("searchRecipe.do")
    public ModelAndView searchRecipe(String items){
    	System.out.println("아이템 들어오나"+items);
    	List<String> recipeNoList = recipeService.getRecipeNoByItem(items);
    	System.out.println("refipeNo 리스트: "+recipeNoList);
		 List<HashMap<String,Object>> fileLastNamePath = new ArrayList<HashMap<String,Object>>();
		System.out.println(fileLastNamePath);
		for (int i = 0; i < recipeNoList.size(); i++) {
			String fileLastNo = recipeService.getFileLastNo(recipeNoList.get(i));
			System.out.println(fileLastNo);
			String fileLastPath = recipeService.getFileLastNamePath(fileLastNo);
			System.out.println(fileLastPath);
			 RecipeVO rvo=recipeService.getRecipeInfo(Integer.parseInt(recipeNoList.get(i)));
			 String tag=recipeService.getItemTag(Integer.parseInt(recipeNoList.get(i)));
			 HashMap<String, Object> map=new HashMap<String, Object>();
	         map.put("rvo",rvo);
	         map.put("fileLastPath", fileLastPath);
	         map.put("tag", tag);
	         fileLastNamePath.add(map);
		}
		return new ModelAndView("home", "fileLastNamePath", fileLastNamePath);
    }
	/**
	 * 레시피 상세 정보 보기
	 * 
	 * @param fvo
	 * @param model
	 * @return
	 */
	@RequestMapping("showRecipe.do")
	@ResponseBody
	public ModelAndView showContents(FileVO fvo) {
		// 레시피 no로 레시피 정보
		System.out.println(fvo.getFilePath() + " dsfasf");
		int recipeNo = recipeService.getRecipeNoByPath(fvo.getFilePath());
		RecipeVO rvo = recipeService.getRecipeInfo(recipeNo);
		List<String> allFilePath = recipeService
				.getAllFilePahtByRecipeNo(recipeNo);
		System.out.println(allFilePath.toString());
		String tag = recipeService.getItemTag(rvo.getRecipeNo());
		Map<String, Object> resultMap = new HashMap<String, Object> ();
		resultMap.put("rvo", rvo);
		resultMap.put("tag",tag);
		resultMap.put("allFilePath", allFilePath.toString());
		ModelAndView model = new ModelAndView("jsonView",resultMap);
		return model;
	}

	/**
	 * 레시피 등록 폼으로 이동
	 * 
	 * @return
	 */
	@RequestMapping("registerRecipeForm.do")
	public String registerRecipeForm() {
		System.out.println("ddd");
		return "register_recipe";
	}

	/**
	 * 레시피 등록
	 * 
	 * @param rvo
	 * @param ivo
	 * @param items
	 * @return
	 */
	@RequestMapping("registerRecipe.do")
	public ModelAndView registerRecipe(RecipeVO rvo, String items) {
		System.out.println("에디터 결과");
		int recipeNo = 0;
		String pageNo = null;
		String contents = rvo.getContents();
		List<String> list = convertHtmlimg(contents);
		System.out.println("list:"+list);
		List<FileVO> fvoList=new ArrayList<FileVO>();
		for (String imgUrl : list) {
			FileVO fvo = new FileVO();
			String imgName[]=imgUrl.split("/");
			fvo.setFileName(imgName[imgName.length-1].toString());
			fvo.setFilePath(imgUrl);
			fvoList.add(fvo);
		}
		System.out.println(fvoList);
		try {
			recipeNo = recipeService.registerRecipe(rvo,items,fvoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(rvo);
		return new ModelAndView("redirect:registerResult.do?recipeNo="
				+ recipeNo);
	}

		
	/**
	 * 레시피 등록 후 레시피 상세 페이지로 이동
	 * 
	 * @param rvo
	 * @param model
	 * @return
	 */
	@RequestMapping("registerResult.do")
	public String registerResult(RecipeVO rvo, Model model) {
		System.out.println(rvo.getRecipeNo());
		rvo = recipeService.getRecipeInfo(rvo.getRecipeNo());
		String tag = recipeService.getItemTag(rvo.getRecipeNo());
		model.addAttribute("rvo", rvo);
		model.addAttribute("tag", tag);
		return "show_recipe";
	}

	/**
	 * 레시피를 삭제
	 * 
	 * @param rvo
	 * @param session
	 * @return
	 */
	@RequestMapping("deleteRecipe.do")
	public ModelAndView deleteForm(RecipeVO rvo, HttpSession session) {
		String pageNo = "";
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		String id = mvo.getId();
		List<String> list = recipeService.getFileName(rvo.getRecipeNo());
		File file = new File(path + "\\" + id);
		System.out.println(file.getPath());
		File f[] = file.listFiles();
		System.out.println("rvo : "+rvo);
		System.out.println("파일리스트:"+f);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			for (int y = 0; y < f.length; y++) {
				if (f[y].getName().equals(list.get(i))) {
					f[y].delete();
				}
			}
		}
		recipeService.deleteRecipeAll(rvo.getRecipeNo());
		return home(pageNo);
	}
	/**
	 * 
	 * @param rvo
	 * @param model
	 * @return
	 */
	@RequestMapping("updateForm.do")
	public String updateForm(RecipeVO rvo, Model model) {
		rvo = recipeService.getRecipeInfo(rvo.getRecipeNo());
		String tag = recipeService.getItemTag(rvo.getRecipeNo());
		List<String> fileNameList = recipeService
				.getFileName(rvo.getRecipeNo());
		System.out.println(fileNameList);
		model.addAttribute("rvo", rvo);
		model.addAttribute("tag", tag);
		model.addAttribute("fileNameList", fileNameList);
		return "update_recipe";
	}

	/**
	 * 다중파일업로드
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "multiplePhotoUpload.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void multiplePhotoUpload(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		System.out.println("컨트롤러 multiplePhotoUpload.do");
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		try {
			// 파일정보
			String sFileInfo = "";
			// 파일명을 받는다 - 일반 원본파일명
			String oriName = request.getHeader("file-name");
			oriName = URLDecoder.decode(oriName, "UTF-8");
			// oriName=new String(oriName.getBytes("8859_1"),"utf-8");
			System.out.println("filename :" + oriName);
			// 파일 확장자
			String filename_ext = oriName
					.substring(oriName.lastIndexOf(".") + 1);
			// 확장자를소문자로 변경
			filename_ext = filename_ext.toLowerCase();

			System.out.println("dftFilePath :" + path);
			// 파일 기본경로 _ 상세경로
			// String filePath = dftFilePath + "photo_upload\\";
			System.out.println("filePath :" + path);
			String destPath = path + "\\" + mvo.getId() + "\\";
			File file = new File(destPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String today = formatter.format(new java.util.Date());
			fileName = today + UUID.randomUUID().toString()
					+ oriName.substring(oriName.lastIndexOf("."));
			String filePath = destPath + fileName;
			System.out.println("rlFileNm :" + filePath);
			/**
			 * recipe_file table에 입력
			 */

			/**
			 * 서버에 파일쓰기
			 */
			InputStream is = request.getInputStream();
			OutputStream os = new FileOutputStream(filePath);
			int numRead;
			byte b[] = new byte[Integer
					.parseInt(request.getHeader("file-size"))];
			while ((numRead = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, numRead);
			}
			if (is != null) {
				is.close();
			}
			os.flush();
			os.close();
			/**
			 * path에 파일 저장
			 */
			/**
			 * 정보 출력
			 */
			sFileInfo += "&bNewLine=true";
			// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
			sFileInfo += "&sFileName=" + oriName;
			sFileInfo += "&sFileURL=" + "/fridge_v0.1/photo_upload/" + mvo.getId()
					+ "/" + fileName;
			// C:\java-kosta\servers\fridge-tomcat\webapps\fridge
			System.out.println("sFileInfo :" + sFileInfo);
			PrintWriter print = response.getWriter();
			print.print(sFileInfo);
			print.flush();
			print.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * java 정규 표현식을 이용한 java 이미지 태그 추출(이미지 저장 경로)
	 * 
	 * @param img
	 * @return
	 */
	public static List<String> convertHtmlimg(String img) {
		Pattern nonValidPattern = Pattern
				.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

		List<String> result = new ArrayList<String>();
		Matcher matcher = nonValidPattern.matcher(img);
		while (matcher.find()) {
			result.add(matcher.group(1));
		}
		System.out.println(result);
		return result;
	}
	   /**
	    * 업데이트
	    * @param request
	    * @param rvo
	    * @return
	    */
	   @RequestMapping("updateRecipe.do")
	   public ModelAndView updateRecipe(RecipeVO rvo,String items){
	       List<String> list = convertHtmlimg(rvo.getContents());
	       String pageNo = null;
	       recipeService.deleteRecipeFile(rvo.getRecipeNo());
	       recipeService.deleteRecipeItem(rvo.getRecipeNo());
	       recipeService.updateRecipe(rvo);  
	       List<FileVO> fvoList=new ArrayList<FileVO>();
			for (String imgUrl : list) {
				FileVO fvo = new FileVO();
				String imgName[]=imgUrl.split("/");
				fvo.setFileName(imgName[imgName.length-1].toString());
				fvo.setFilePath(imgUrl);
				fvoList.add(fvo);
			}
			recipeService.insertRecipeItem(rvo,items);
			recipeService.insertRecipeFile(rvo, fvoList);  
	      return home(pageNo);
	   }
	   
		@RequestMapping("{viewId}.do")
		public String showView(@PathVariable String viewId){
			return viewId;
		}
}
