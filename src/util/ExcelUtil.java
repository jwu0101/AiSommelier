package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

public class ExcelUtil {
	static CommonUtil comm = new CommonUtil();
	static String tableName = "";
	static String sqlR = "";
	static String sqlU = "";
	static String sqlC = "";
	static String insKey = "";
	static String insVal = "";
	static String updateSet = "";
	static String id = "";
	static int rowCount = 0;

	static FileInputStream fis = null;
	static FileOutputStream fos = null;
//	static XSSFWorkbook workbook = null;
//	static XSSFSheet sheet = null;
//	FileInputStream fis =null;
//	Workbook workbook = null;
//	Sheet sheet =null;

	static Connection conn = null;
	static ResultSet rs = null;
	static Statement st = null;

	public void imp(String file) throws IOException, InvalidFormatException, ClassNotFoundException, SQLException {

		Workbook workbook = null;
		Sheet sheet =null;
		
		//判断是否为excel类型文件
		if(!file.endsWith(".xls")&&!file.endsWith(".xlsx")) {
			System.out.println("文件不是excel类型");
		}

//		FileInputStream fis =null;
//		Workbook wookbook = null;
//		Sheet sheet =null;
		try {
			//获取一个绝对地址的流
			fis = new FileInputStream(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
 
		try {
			//2003版本的excel，用.xls结尾
			workbook = new HSSFWorkbook(fis);//得到工作簿
		} catch (Exception ex) {
			try {
				//2007版本的excel，用.xlsx结尾
				fis = new FileInputStream(file);
				workbook = new XSSFWorkbook(fis);//得到工作簿
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Map<String, PictureData> maplist=null;
		sheet = workbook.getSheetAt(0); 
		// 判断用07还是03的方法获取图片 
		if (file.endsWith(".xls")) { 
			maplist = ExcelImg.getXLSPictures((HSSFSheet) sheet); 
		} else if(file.endsWith(".xlsx")){ 
			maplist = ExcelImg.getXLSXPictures((XSSFSheet) sheet); 
		}
		
		conn = DBConn.getConn();
		st = conn.createStatement();
		int sheetCount = workbook.getNumberOfSheets();
		
		
		for (int s = 0; s < sheetCount; s++) {
			boolean isWine = workbook.getSheetName(s).equals("wine");
			tableName = workbook.getSheetName(s);
			sheet = workbook.getSheetAt(s);
			rowCount = sheet.getPhysicalNumberOfRows();

			// Row
			for (int r = 1; r < rowCount; r++) { 
				id = sheet.getRow(r).getCell(isWine ? 1 : 0).toString();

				// id為必填，若未填寫則跳出迴圈
				if (id.isEmpty())
					break;
				// 判斷匯入的商品是否已存在於db
				sqlR = "select count(*) from " + tableName + " where id = '" + id + "'";
				System.out.println("sqlR = " + sqlR);
				rs = st.executeQuery(sqlR);
				
				//已存在則update商品資料
				if (rs.next() && rs.getInt(1) > 0) {

					// Cell
					// 組update set字串
					updateSet = "";
					for (int c = isWine ? 1 : 0 ; c < sheet.getRow(0).getPhysicalNumberOfCells(); c++) {
						updateSet += sheet.getRow(0).getCell(c) + "='"
								+ sheet.getRow(r).getCell(c).toString().replace("'", "''") + "',";
					}
					sqlU = "update " + tableName + " set " + comm.delFinalWord(updateSet) + " where id = '" + id + "'";
					System.out.println("sqlU = " + sqlU);
					st.executeUpdate(sqlU);

				} else { //不存在則insert商品資料

					insKey = "";
					insVal = "";
					// 組insert 字串
					for (int c = isWine ? 1 : 0 ; c < sheet.getRow(0).getPhysicalNumberOfCells(); c++) {
						insKey += sheet.getRow(0).getCell(c) + ",";
						insVal += "'" + sheet.getRow(r).getCell(c).toString().replace("'", "''") + "',";
					}
					sqlC = "insert into " + tableName + " (" + comm.delFinalWord(insKey) + ")" + " values("
							+ comm.delFinalWord(insVal) + ")";
					System.out.println("sqlC = " + sqlC);
					st.executeUpdate(sqlC);

				}
				
				// 获取图片流 
				PictureData pic = maplist.get(r+"-0"); 
				System.out.println("129 pic="+pic);
				if(pic != null && isWine) {
					// 获取图片格式 
					String ext = pic.suggestFileExtension(); 
					byte[] data = pic.getData(); 
					
					//图片保存路径
					String imgFilePath = ExcelImg.class.getClassLoader().getResource("/").getPath()+"../../";
					String imgFileName = "imgs/"+sheet.getRow(r).getCell(isWine ? 1 : 0)+"." + ext;
					System.out.println("139 imgfilePath="+imgFilePath+imgFileName);
					FileOutputStream out = new FileOutputStream(imgFilePath+imgFileName); 
					out.write(data); 
					out.close(); 
					
					//更新圖片路徑至db
					sqlU = "update " + tableName + " set imgPath = '" + imgFileName + "' where id = '" + id + "'";
					System.out.println("180 sqlU = " + sqlU);
					st.executeUpdate(sqlU);
				}
			}
		}
		conn.close();
		st.close();
		fis.close();
		workbook.close();

	}

	public void exp(String file, List<String> list) throws ClassNotFoundException, SQLException, IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = null;
		Row row = null;
		Cell cell = null;
		BufferedImage bufferImg = null;
		XSSFDrawing patriarch = null;
		XSSFClientAnchor anchor = null;
		String filePath = "";
		
		for (String str : list) {
			sqlR = "select * from " + str;
			conn = DBConn.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sqlR);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			sheet = workbook.createSheet(str);
			System.out.println(str);
			int hasImg = 0;
			if(str.contentEquals("wine")) {
				sheet.setColumnWidth(0, (short)(50*100));
				hasImg = 1;
			}
			// 凍結窗格(列，欄)
			sheet.createFreezePane(hasImg > 0 ? 2 : 1, 1); 
			Font font = workbook.createFont();
			font.setColor(XSSFFont.COLOR_RED); // 文字色

			CellStyle csTitle = workbook.createCellStyle();
			csTitle.setFont(font);
			csTitle.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); // 背景色
			csTitle.setFillPattern((short) 1); // 加這行背景色才會出來
			csTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 水平置中
			csTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直置中

			int r = 0;
			row = sheet.createRow(r++);
			row.setHeight((short)(20*20));
			// 寫入欄位名稱
			for (int c = 0; c < columnCount; c++) {
				if(hasImg > 0) {
					cell = row.createCell(0);
					cell.setCellValue("商品圖片");
					cell.setCellStyle(csTitle);
				}
				cell = row.createCell(c+hasImg);
				cell.setCellValue(rsmd.getColumnName(c+1));
				cell.setCellStyle(csTitle);
			}

			// 寫入內容
			while (rs.next()) {
				row = sheet.createRow(r);
				if(hasImg > 0) {
					row.setHeight((short)(20*100));
				}
				
				if(hasImg > 0) {
					filePath = this.getClass().getClassLoader().getResource("/").getPath()+"../../"+rs.getString("imgPath");
//					System.out.println(filePath);
					if(!comm.getString(rs.getString("imgPath")).isEmpty()) {
						// 載入圖片
						ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
						bufferImg = ImageIO.read(new File(filePath));
						ImageIO.write(bufferImg, rs.getString("imgPath").substring(rs.getString("imgPath").lastIndexOf(".")+1), byteArrayOut);
						patriarch = sheet.createDrawingPatriarch();
						anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) 0, r, (short) 1, r+1);
						// 插入圖片 1
						patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
					}
				}
				
				for (int c = 0; c < columnCount; c++) {
					row.createCell(c+hasImg).setCellValue(rs.getString(c+1));
				}
				r++;
			}

			// 自動調整欄寬
			for (int c = 0+hasImg; c < columnCount+hasImg; c++) {
				sheet.autoSizeColumn(c); // 自動調整欄寬
				int columnWidth = sheet.getColumnWidth(c);
				int maxWidth = 10000; // 最大欄寬
				int extraWidth = 500; // 多出寬度，避免覺得太擠
				if (columnWidth > maxWidth) {
					sheet.setColumnWidth(c, maxWidth);
				} else {
					sheet.setColumnWidth(c, columnWidth + extraWidth);
				}
			}
		}
		
		fos = new FileOutputStream(new File(file));
		workbook.write(fos);
		fos.flush();
		fos.close();

		conn.close();
		st.close();
		workbook.close();
		
	}

}