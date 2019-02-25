package com.ck.ck181228.product_info.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ck.ck181228.init.ServiceUtil.ServiceUtil;
import com.ck.ck181228.init.isempty.IsEmpty;

import com.ck.ck181228.product_info.mapper.Product_infoMapper;
import com.ck.ck181228.product_info.model.Product_infoModel;

@Service("product_infoService")
public class Product_infoService extends ServiceUtil<Product_infoModel> {
	@Autowired
	private Product_infoMapper<Product_infoModel> mapper;

	@Override
	public Product_infoMapper<Product_infoModel> getmapper() {
		return mapper;
	}

	// 重写添加方法,判断商品是否已存在,存在即修改商品信息,不存在即添加新的商品
	@Override
	public String insert(Product_infoModel model) {
		Product_infoModel pmodel = new Product_infoModel();
		pmodel.setCode(model.getCode());
		Product_infoModel p = getmapper().selectModel(pmodel);
		if (p == null) {
			model.setCost(model.getSum() * model.getCost_unit_price());
			return getmapper().insert(model) > 0 ? "success" : "err";
		} else {
			int a = model.getSum() + p.getSum();
			model.setSum(a);
			model.setCost(a * p.getCost_unit_price());
			return getmapper().updateModel(model) > 0 ? "success" : "err";
		}
	}

	//根据数组长度判断是否是批量删除
	public String delete(Product_infoModel model, String[] code) {
		int i = 0;
		if (code.length > 0) {
			for (String ss : code) {
				model.setCode(ss);
				i = getmapper().deleteModel(model);
			}
		} else {
			i = getmapper().deleteModel(model);
		}
		return i > 0 ? "success" : "err";
	}

	//判断是否存在查询字段,拼接查询字段返回结果数目以及内容
	public String selectdata(Product_infoModel model) {
		if (IsEmpty.str(model.getCode())) {
			model.setCode("%" + model.getCode() + "%");
		}
		if (IsEmpty.str(model.getName())) {
			model.setName("%" + model.getName() + "%");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", getmapper().selectList(model));
		map.put("count", getmapper().selectCount(model));
		return new JSONObject(map).toString();
	}

	//重写修改方法,判断是否修改数量,如修改数量判断是否为0,如果为0,删除该商品,如果不为0,修改数量的同时修改成本价
	@Override
	public String update(Product_infoModel model) {
		Product_infoModel pmodel = new Product_infoModel();
		pmodel.setCode(model.getCode());
		if (model.getSum() != null) {
			model.setCost(getmapper().selectModel(pmodel).getCost_unit_price() * model.getSum());
		} else if (model.getSum() == 0) {
			return getmapper().deleteModel(model) > 0 ? "success" : "err";
		}
		return getmapper().updateModel(model) > 0 ? "success" : "err";
	}

	//下载模板定制格式
	public void downloadExcel(HttpServletResponse response) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("product_info");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell1 = row.createCell(0);
		cell1.setCellValue("商品编号");
		XSSFCell cell2 = row.createCell(1);
		cell2.setCellValue("商品名称");
		XSSFCell cell3 = row.createCell(2);
		cell3.setCellValue("数量");
		XSSFCell cell4 = row.createCell(3);
		cell4.setCellValue("成本单价");

		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;filename=product_info.xlsx");
		OutputStream out = response.getOutputStream();
		workbook.write(out);
		workbook.close();
		out.close();
	}

	//上传文档
	public String uploadExcel(CommonsMultipartResolver multipartResolver, HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("code", "0");
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				result.put("count", parse(file.getInputStream())[0]);
				result.put("ncount", parse(file.getInputStream())[1]);
			}
		}
		return new JSONObject(result).toString();
	}

	//读取文档内容到页面
	private int[] parse(InputStream fis) throws IOException {
		// 由输入流得到工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// 得到工作表
		XSSFSheet sheet = workbook.getSheet("product_info");
		int i = 0;
		int a = 0;
		int[] b = new int[2];
		for (Row row : sheet) {
			++a;
			if (0 == row.getRowNum()) {
				continue;
			}
			Product_infoModel product_infomodel = new Product_infoModel();
			product_infomodel.setCode(getValue(row.getCell(0)));
			product_infomodel.setName(getValue(row.getCell(1)));
			product_infomodel.setSum(Integer.parseInt(getValue(row.getCell(2))));
			product_infomodel.setCost_unit_price(Integer.parseInt(getValue(row.getCell(3))));
			product_infomodel.setCost(product_infomodel.getSum() * product_infomodel.getCost_unit_price());
			i += insert(product_infomodel) == "success" ? 1 : 0;
		}
		workbook.close();
		fis.close();
		b[0] = i;
		b[1] = a - i - 1;
		return b;
	}

	//获取行内容
	private String getValue(Cell cell) {
		CellType type = cell.getCellTypeEnum();
		if (CellType.STRING.equals(type)) {
			return cell.getStringCellValue();
		} else if (CellType.NUMERIC.equals(type)) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return null;
	}

}
