package org.dedeplz.fridge.model.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
@Service
public class FileManager {
	@Resource(name="uploadPath")
	private String path;
	/**
	 * java 정규 표현식을 이용한 java 이미지 태그 추출(이미지 저장 경로)
	 * 
	 * @param img
	 * @return
	 */
	public List<String> convertHtmlimg(String img) {
		Pattern nonValidPattern = Pattern
				.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		List<String> result = new ArrayList<String>();
		Matcher matcher = nonValidPattern.matcher(img);
		while (matcher.find()) {
			result.add(matcher.group(1));
		}
		return result;
	}
	/**
	 * 레시피,게시물 삭제 시 이미지리스트와
	 * 아이디를 이용
	 * 아이디명 디렉토리내 존재하는 이미지리스트와
	 * 일치하는 이미지 파일을 모두 삭제
	 * @param list
	 * @param id
	 */
	public void deleteImg(List<String> list, String id) {
		File file = new File(path + id);
		File f[] = file.listFiles();
		for (int i = 0; i < list.size(); i++) {
			for (int y = 0; y < f.length; y++) {
				if (f[y].getName().equals(list.get(i))) {
					f[y].delete();
				}
			}
		}
	}
	/**
	 * 아이디이름의 디렉토리 존재 유무를 확인
	 * 없는 경우 생성
	 * @param id
	 * @return
	 */
	public String checkUploadPath(String id) {
		String uploadPath=path+id+"\\";
		File file=new File(uploadPath);
		if(!file.exists()){
			file.mkdir();
		}
		return uploadPath;	
	}
	/**
	 * 회원 탈퇴시
	 * 아이디명 디렉토리 내 이미지를 모두 지우고 
	 * 해당 디렉토리를 삭제
	 * @param id
	 */
	public void deleteDirectoryById(String id) {
		String uploadPath=path+id;
		File file=new File(uploadPath);
		if(file.exists()){
			File files[]=file.listFiles();
			if(files.length!=0){
				for(int i=0;i<files.length;i++){
					files[i].delete();
				}
				file.delete();
			}else{
				file.delete();
			}
		}
	}
}
