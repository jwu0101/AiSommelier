package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

public class ExcelImg {
	public static void getDataFromExcel(String filePath) throws IOException {

		//判断是否为excel类型文件
		if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx")) {
			System.out.println("文件不是excel类型");
		}

		FileInputStream fis =null;
		Workbook wookbook = null;
		Sheet sheet =null;
		try {
			//获取一个绝对地址的流
			fis = new FileInputStream(filePath);
		} catch(Exception e) {
			e.printStackTrace();
		}
 
		try {
			//2003版本的excel，用.xls结尾
			wookbook = new HSSFWorkbook(fis);//得到工作簿
		} catch (Exception ex) {
			try {
				//2007版本的excel，用.xlsx结尾
				fis = new FileInputStream(filePath);
				wookbook = new XSSFWorkbook(fis);//得到工作簿
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Map<String, PictureData> maplist=null;
		sheet = wookbook.getSheetAt(0); 
		// 判断用07还是03的方法获取图片 
		if (filePath.endsWith(".xls")) { 
			maplist = getXLSPictures((HSSFSheet) sheet); 
		} else if(filePath.endsWith(".xlsx")){ 
			maplist = getXLSXPictures((XSSFSheet) sheet); 
		} 
		try {
			// 寫出圖片檔
			printImg(maplist);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	* 获取图片和位置 (xls)
	* @param sheet
	* @return
	* @throws IOException
	*/
	public static Map<String, PictureData> getXLSPictures (HSSFSheet sheet) throws IOException {
		Map<String, PictureData> map = new HashMap<String, PictureData>();
		List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
		for (HSSFShape shape : list) {
			if (shape instanceof HSSFPicture) {
				HSSFPicture picture = (HSSFPicture) shape;
				HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
				PictureData pdata = picture.getPictureData();
				String key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
				System.out.println(key);
				map.put(key, pdata);
			}
		}
		return map;
	}

	/**
	* 获取图片和位置 (xlsx)
	* @param sheet
	* @return
	* @throws IOException
	*/
	public static Map<String, PictureData> getXLSXPictures (XSSFSheet sheet) throws IOException {
		Map<String, PictureData> map = new HashMap<String, PictureData>();
		List<POIXMLDocumentPart> list = sheet.getRelations();
		for (POIXMLDocumentPart part : list) {
			if (part instanceof XSSFDrawing) {	
				XSSFDrawing drawing = (XSSFDrawing) part;
				List<XSSFShape> shapes = drawing.getShapes();
				for (XSSFShape shape : shapes) {
					XSSFPicture picture = (XSSFPicture) shape;
					XSSFClientAnchor anchor = picture.getPreferredSize();
					CTMarker marker = anchor.getFrom();
					String key = marker.getRow() + "-" + marker.getCol();
					map.put(key, picture.getPictureData());
				}
			}
		}
		return map;
	 }
	
	 //图片写出
	 public static void printImg(Map<String, PictureData> sheetList) throws IOException { 
 
		Object key[] = sheetList.keySet().toArray(); 
		for (int i = 0; i < sheetList.size(); i++) { 
			// 获取图片流 
			PictureData pic = sheetList.get(key[i]); 
			// 获取图片索引 
			String picName = key[i].toString(); 
			// 获取图片格式 
			String ext = pic.suggestFileExtension(); 
			 
			byte[] data = pic.getData(); 
			
			 //图片保存路径
			String filePath = ExcelImg.class.getClassLoader().getResource("/").getPath()+"../../imgs/pic"+ picName + "." + ext;
			System.out.println(filePath);
			FileOutputStream out = new FileOutputStream(filePath); 
			out.write(data); 
			out.close(); 
		} 
	} 

	//执行 测试 。图片保存到 D:\\img 就算成功
	 public static void main(String[] args) throws Exception {
		 String Excel = "/Users/nicole/22/AI_Bartender 商品資料/wineOutput_20200317.xlsx";
		 getDataFromExcel(Excel);
	 } 
}
