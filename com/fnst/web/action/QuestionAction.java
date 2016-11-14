package com.fnst.paper.web.action;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.fnst.paper.pojo.KnowledgePoint;
import com.fnst.paper.pojo.Question;
import com.fnst.paper.pojo.QuestionType;
import com.fnst.paper.service.KnowledgePointService;
import com.fnst.paper.service.QuestionService;
import com.fnst.paper.util.ChartData;
import com.fnst.paper.util.ChartSeries;
import com.fnst.paper.util.HttpStatusCode;
import com.fnst.paper.util.JsonData;

@Controller("questionAction")
public class QuestionAction extends BaseAction<Question> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5086743977630040839L;
	private Question ques;
	private QuestionType type;
	private KnowledgePoint kPoint;
	private List<Question> questionList;
	private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型
    private String msg;//操作提示信息
    private String kpContent;
    private String typeContent;
    private String searchContent;//条件查询 问题内容
    private String searchType;//条件查询类型
    private String searchLevel;//条件查询难度
    private String searchKnowledge;//条件查询知识点
    private String searchFlag;//查询标志位
    private String knowledgeChecked;
    private String typeChecked;
	
	@Resource
	private QuestionService questionService;
	@Resource
	private KnowledgePointService knowledgePointService;
	
	/**
	 *显示所有的 
	 * @return
	 * @throws IOException 
	 */

	public String questiontest() throws IOException {
		//TODO
		System.out.println(searchContent+searchType+searchLevel+searchKnowledge);
	    return "list";
	}
	
	public String questionList() {
		if (searchFlag!=null && searchFlag.equals("condition")) {
			List<Question> questions = questionService.searchByConditon(searchContent,searchType,searchLevel,searchKnowledge,rows,page);
			request.put("questions", questions);
	        int totalQues=questionService.getTotalByCondition(searchContent, searchType, searchLevel, searchKnowledge);
	        int totalPage=(totalQues+rows-1)/rows;
	        request.put("totalQues", totalQues);
	        request.put("totalPage", totalPage);
			request.put("questions", questions);
			request.put("searchFlag", "condition");
			request.put("searchT", searchType);
			request.put("searchL", searchLevel);
			request.put("searchK",searchKnowledge );
			request.put("searchC", searchContent);
		}else {
			List<Question> questions = questionService.getQuestionByPage(rows,page);
			int totalQues=questionService.getTotal();
	        int totalPage=(totalQues+rows-1)/rows;
	        request.put("totalQues", totalQues);
	        request.put("totalPage", totalPage);
			request.put("questions", questions);
			request.put("searchFlag", "all");
			request.put("searchT", -1);
			request.put("searchL", -1);
			request.put("searchK", -1 );
			request.put("searchC", -1);
		}
		List<QuestionType> types = questionService.findAllType();
		List<KnowledgePoint> kPoints = questionService.findAllKP();
		request.put("types", types);
		request.put("kPoints", kPoints);
        request.put("msg", msg);
        msg="";
		return "questionList";
	}
	
	/**
	 * 预添加，查询类型，知识点
	 */
	public String questiontoAdd(){
		List<QuestionType> types = questionService.findAllType();
		List<KnowledgePoint> kPoints = questionService.findAllKP();
		request.put("types", types);
		request.put("kPoints", kPoints);
		return "toAdd";
	}
	
	/**
	 * 添加知识点
	 */
	public String questionAddKp(){
		int kpid = knowledgePointService.isExsit(kpContent);
		if (kpid!=-1) {
			request.put("knowledgeChecked",kpid );
			return "gototoAdd";
		}else {
			KnowledgePoint kp = new KnowledgePoint();
			kp.setkId(0);
			kp.setContent(kpContent);
			if(knowledgePointService.saveKp(kp)){
				request.put("knowledgeChecked",knowledgePointService.isExsit(kpContent));
				return "gototoAdd";
			}else {
				msg="新增失败";
				request.put("msg", msg);
				return "showques";
			}
		}
	}
	/**
	 * 添加题型
	 */
	public String questionAddType(){
		int tid = questionService.isTypeExist(typeContent);
		if (tid!=-1) {
			request.put("typeChecked",tid );
			System.out.println("yicunzai"+tid);
			return "gototoAdd";
		}else{
			QuestionType qt = new QuestionType();
			qt.setQtId(0);
			qt.setTypeName(typeContent);
			if (questionService.saveType(qt)) {
				request.put("typeChecked",questionService.isTypeExist(typeContent));
				return "gototoAdd";
			}else{
				msg="新增失败";
				request.put("msg", msg);
				return "showques";
			}
		}
	}
/**
 * 添加
 * @return
 * @throws IOException 
 */
	public String questionAdd() throws IOException {
	    //保存图片
//	    String realpath = ServletActionContext.getServletContext().getRealPath("/images");
//        String attachment = realpath+"/"+imageFileName;
//        System.out.println(attachment);
//        if (image != null) {
//            File savefile = new File(new File(realpath), imageFileName);
//            if (!savefile.getParentFile().exists())
//                savefile.getParentFile().mkdirs();
//            FileUtils.copyFile(image, savefile);
//        }
        //获取时间
        Date date;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {				
			e.printStackTrace();
			msg="数据库出错";
			request.put("msg", msg);
			return "showques";
		}
		ques.setqId(0);
		ques.setCreateTime(date);
		ques.setAvaliable(1);
//		ques.setAttachMent(attachment);
		boolean flag = questionService.saveQues(ques);
		if(flag){
			msg="新增题目成功";
			request.put("msg", msg);
			return "showques";
		}else{
			msg="数据库错误";
			request.put("msg", msg);
			return "showques";
		}
	}
	/**
	 * 删除
	 * @return
	 */
	public String questionDel() {
		System.out.println("deleteAction");
		Question question = new Question();
		HttpServletRequest req = ServletActionContext.getRequest(); 
		String qId = req.getParameter("qId");
		question.setqId(Integer.valueOf(qId));
		Question q = questionService.one(question);
		if (q != null) {
			try {
				q.setAvaliable(0);
				System.out.println(q.getqId()+q.getContent());
				questionService.updateQues(q);
				msg = "删除成功";
				request.put("msg", msg);
				return "showques";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "删除失败";
				request.put("msg", msg);
				return "showques";
			}
		}else{
			msg = "删除失败";
			request.put("msg", msg);
			return "showques";
		}
	}
	/**
	 * 条件查询问题
	 * xuwei
	 */
	public String questionSearch(){
		request.put("searchT", searchType);
		request.put("searchL", searchLevel);
		request.put("searchK",searchKnowledge );
		List<QuestionType> types = questionService.findAllType();
		List<KnowledgePoint> kPoints = questionService.findAllKP();
		request.put("types", types);
		request.put("kPoints", kPoints);
		if (searchFlag.equals("condition")) {
			List<Question> questions = questionService.searchByConditon(searchContent,searchType,searchLevel,searchKnowledge,rows,page);
			request.put("questions", questions);
	        int totalQues=questionService.getTotalByCondition(searchContent, searchType, searchLevel, searchKnowledge);
	        int totalPage=(totalQues+rows-1)/rows;
	        request.put("totalQues", totalQues);
	        request.put("totalPage", totalPage);
			request.put("questions", questions);
			request.put("searchFlag", "condition");
		}
		request.put("msg", msg);
        msg="";
		return "list";
	}
	/**
	 * 查看某一个问题
	 * @return
	 */
	public String questionQueryById(){
		Question question = new Question();
		HttpServletRequest req = ServletActionContext.getRequest(); 
		String qId = req.getParameter("qId");
		question.setqId(Integer.valueOf(qId));
		Question q = questionService.one(question);
		request.put("question", q);
		return "queryq";
	}
	/**
	 * 修改预操作
	 */
	public String questionToUpdate(){
		Question q = questionService.one(ques);
		
		//判断是否出现在某张试卷中，假如存在，则新建
		
		request.put("upquestion", q);
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String questionUpdate(){
		//判断是否出现过 ，转到添加页面
		HttpServletRequest req = ServletActionContext.getRequest(); 
		System.out.println( req.getParameter("ques.qId"));
		Question question = questionService.one(ques);
		try {
			Date date;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date = sdf.parse(sdf.format(new Date()));
			} catch (ParseException e) {				
				e.printStackTrace();
				msg="修改失败";
				request.put("msg", msg);
				return "showques";
			}
			question.setContent(ques.getContent());
			question.setChoice(ques.getChoice());
			question.setAnswer(ques.getAnswer());
			question.setLevel(ques.getLevel());
			question.setModifyTime(date);
			questionService.updateQues(question);
			msg="修改成功";
			request.put("msg", msg);
			return "showques";
		} catch (Exception e) {			
			e.printStackTrace();
			msg="修改失败";
			request.put("msg", msg);
			return "showques";
		}
	}
	
	private String charts;
	
	private String statisType;
	
	private Map<String,Object> dataMap;  
	
	public Map<String, Object> getDataMap() {  
	   return dataMap;  
	}  
	
	public String questionAnalysis() {
		dataMap = new HashMap<String, Object>(); 
		JsonData<ChartData> data = new JsonData<ChartData>();
		if (charts != null && statisType != null){
			ChartData chart = new ChartData();
			Map<String, Long> statisticData = questionService.statisticsQuestion(statisType);
			if (statisType.equals("1")) {
				statisType = "题目类型-数量";
			} else if (statisType.equals("2")) {
				statisType = "题目知识点-数量";
			} else if (statisType.equals("3")) {
				statisType = "题目难度-数量";
			}
			if (charts.equals("1")) {
				charts = "柱状图";
			} else if (charts.equals("2")) {
				charts = "折线图";
			}
			chart.setTitle(statisType + charts);
			chart.setLegend(Arrays.asList(new String[] {"题目数"}));
			List<String> xAxis = new ArrayList<String>();
			List<ChartSeries> series = new ArrayList<ChartSeries>();
			List<Integer> lineData = new ArrayList<Integer>();
			for(Entry<String, Long> sdata:statisticData.entrySet()) {
				xAxis.add(sdata.getKey());
				lineData.add(Integer.parseInt(sdata.getValue().toString()));
			}
			ChartSeries chartSeries = new ChartSeries("题目数", "bar", lineData);
			if (charts.equals("折线图"))
				chartSeries.setType("line");
			series.add(chartSeries);
			chart.setxAxis(xAxis);
			chart.setChartSeries(chartSeries);
			data.setData(chart);
			data.setSUCCESS(true);
			data.setMessage("获取信息成功");
			data.setStatusCode(HttpStatusCode.OK);
		} else {
			data.setMessage("信息输入错误");
			data.setSUCCESS(false);
		}
		dataMap.put("data", data);
		return SUCCESS;
	}
	
/**
 * get set
 * 
 */
	
	
	public Question getQues() {
		return ques;
	}

	public String getStatisType() {
	return statisType;
}

public void setStatisType(String statisType) {
	this.statisType = statisType;
}

	public String getCharts() {
		return charts;
	}

	public void setCharts(String charts) {
		this.charts = charts;
	}

	public void setQues(Question ques) {
		this.ques = ques;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public KnowledgePoint getkPoint() {
		return kPoint;
	}

	public void setkPoint(KnowledgePoint kPoint) {
		this.kPoint = kPoint;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getKpContent() {
		return kpContent;
	}

	public void setKpContent(String kpContent) {
		this.kpContent = kpContent;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchLevel() {
		return searchLevel;
	}

	public void setSearchLevel(String searchLevel) {
		this.searchLevel = searchLevel;
	}

	public String getSearchKnowledge() {
		return searchKnowledge;
	}

	public void setSearchKnowledge(String searchKnowledge) {
		this.searchKnowledge = searchKnowledge;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getKnowledgeChecked() {
		return knowledgeChecked;
	}

	public void setKnowledgeChecked(String knowledgeChecked) {
		this.knowledgeChecked = knowledgeChecked;
	}

	public String getTypeChecked() {
		return typeChecked;
	}

	public void setTypeChecked(String typeChecked) {
		this.typeChecked = typeChecked;
	}

	public String getTypeContent() {
		return typeContent;
	}

	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}
	
	
}
