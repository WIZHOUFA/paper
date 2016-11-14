package com.fnst.paper.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.fnst.paper.criteria.CriteriaPaper;
import com.fnst.paper.criteria.KlPointDistribution;
import com.fnst.paper.criteria.QtTypeDistribution;
import com.fnst.paper.pojo.KnowledgePoint;
import com.fnst.paper.pojo.Paper;
import com.fnst.paper.pojo.PaperQuestionRel;
import com.fnst.paper.pojo.Question;
import com.fnst.paper.service.KnowledgePointService;
import com.fnst.paper.service.PaperQuestionRelService;
import com.fnst.paper.service.PaperService;
import com.fnst.paper.service.QuestionService;
import com.fnst.paper.util.StringUtil;

@Controller("paperAction")
public class PaperAction extends BaseAction<CriteriaPaper>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@Resource
	private KnowledgePointService knowledgeService;
	
	@Resource
	private QuestionService questionService;
	
	@Resource
	private PaperService paperService;
	
	@Resource
	private PaperQuestionRelService paperQuestionRelService;
	
	private String selectedList;
	
	private String deletedList;
	
	private String content = "";
	
	private List<Question> questionsA = new ArrayList<Question>();
	private List<Question> questionsB = new ArrayList<Question>();
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(String selectedList) {
		this.selectedList = selectedList;
	}

	public String getDeletedList() {
		return deletedList;
	}

	public void setDeletedList(String deletedList) {
		this.deletedList = deletedList;
	}
	

	public String paperAddPage () {
		List<KnowledgePoint> knowledges = knowledgeService.listAllKnowledge(content);
		request.put("knowledges",knowledges);
		return "paperAddPage";
	}
	
	public String paperCreate () {
		String createDate = model.getCreateDate();
		Date date = new Date();
		if(createDate == null || createDate.trim().equals("")){
			model.setCreateDate(StringUtil.formartDate("yyyy年MM月dd日", date));
			model.setCreateTime(date );
		}
		Set<KlPointDistribution> klps = getKlPointDistribution();
		Set<QtTypeDistribution> qtts = getQtTypeDistribution();
		model.setKlPointDistributions(klps);
		model.setQtTypeDistributions(qtts);
		questionsA = questionService.getPaperQuestions(model);
		request.put("questionsA", questionsA);
		questionsB = questionService.getPaperQuestions(model);
		request.put("questionsB", questionsB);  
		session.put("model", model);
		StringBuffer html = new StringBuffer("");
		html.append("<h3 align='center'>" + model.getPaperName() + "A卷</h3>")
		.append("<h5 align='center'>出卷时间：" + model.getCreateDate() + "&nbsp;&nbsp;&nbsp;总分：" + model.getTotalScore() + "</h5>");
		int j = 1;
		if ( model.getChooseNum() != 0) {
			html.append("<h4>一.选择题（" + model.getChooseNum() + "*" + model.getChooseScore() + "分）</h4>");
			
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(1))
					html.append( questionsA.get(i).getContent().replaceFirst(">", ">" + (j++) + "."))
					.append("<br><br><br>");
			}
		}
		j = 1;
		if ( model.getBlankNum() != 0) {
			html.append("<h4>二.填空题（" + model.getBlankNum() + "*" + model.getBlankScore() + "分）</h4>");
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(2))
					html.append(questionsA.get(i).getContent().replaceFirst(">", ">" + (j++) + ".") + "<br><br>");
			}
		}
		j = 1;
		if ( model.getAnswerNum() != 0) {
			html.append("<h4>三.问答题（" + model.getAnswerNum() + "*" + model.getAnswerScore() + "分）</h4>");
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(3))
					html.append(questionsA.get(i).getContent()
							.replaceFirst(">", ">" + (j++) + ".")+ "<br><br><br><br>");
			}
		}		
		
		StringBuffer html3 = new StringBuffer("");
		
		html3.append("<h3 align='center'>" + model.getPaperName() + "A卷答案卷</h3>")
		.append("<h5 align='center'>出卷时间：" + model.getCreateDate() + "&nbsp;&nbsp;&nbsp;总分：" + model.getTotalScore() + "</h5>");
		j = 1;
		if ( model.getChooseNum() != 0) {
			html3.append("<h4>一.选择题（" + model.getChooseNum() + "*" + model.getChooseScore() + "分）</h4>");
			
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(1))
					html3.append( "<p>"+(j++) + "."+ questionsA.get(i).getAnswer() +"</p>");
			}
		}
		j = 1;
		if ( model.getBlankNum() != 0) {
			html3.append("<h4>二.填空题（" + model.getBlankNum() + "*" + model.getBlankScore() + "分）</h4>");
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(2))
					html3.append("<p>" + (j++)  + ".："+ questionsA.get(i).getAnswer() +"</p>");
			}
		}
		j = 1;
		if ( model.getAnswerNum() != 0) {
			html3.append("<h4>三.问答题（" + model.getAnswerNum() + "*" + model.getAnswerScore() + "分）</h4>");
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(3))
					html3.append("<p>"+ (j++) + "."+ questionsA.get(i).getAnswer() +"</p>");
			}
		}	
		
		StringBuffer html2 = new StringBuffer("");
		html2.append("<h3 align='center'>" + model.getPaperName() + "B卷</h3>")
		.append("<h5 align='center'>出卷时间：" + model.getCreateDate() + "&nbsp;&nbsp;&nbsp;总分：" + model.getTotalScore() + "</h5>");
		j = 1;
		if ( model.getChooseNum() != 0) {
			html2.append("<h4>一.选择题（" + model.getChooseNum() + "*" + model.getChooseScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(1))
					html2.append(questionsB.get(i).getContent().replaceFirst(">", ">" + (j++) + "."))
					.append("<br><br/><br/>");
			}
		}
		j = 1;
		if ( model.getBlankNum() != 0) {
			html2.append("<h4>二.填空题（" + model.getBlankNum() + "*" + model.getBlankScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(2))
					html2.append(questionsB.get(i).getContent().replaceFirst(">", ">" + (j ++) + ".") + "<br><br>");
			}
		}
		j = 1;
		if ( model.getAnswerNum() != 0) {
			html2.append("<h4>三.问答题（" + model.getAnswerNum() + "*" + model.getAnswerScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(3))
					html2.append( questionsB.get(i).getContent().replaceFirst(">", ">" + (j++) + ".")+ "<br><br><br><br>");
			}
		}	
		
		StringBuffer html4 = new StringBuffer("");
		html4.append("<h3 align='center'>" + model.getPaperName() + "B卷答案卷</h3>")
		.append("<h5 align='center'>出卷时间：" + model.getCreateDate() + "&nbsp;&nbsp;&nbsp;总分：" + model.getTotalScore() + "</h5>");
		j = 1;
		if ( model.getChooseNum() != 0) {
			html4.append("<h4>一.选择题（" + model.getChooseNum() + "*" + model.getChooseScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(1))
					html4.append("<p>" + (j++) + "." + questionsB.get(i).getAnswer()+"<br/><br/>");
			}
		}
		j = 1;
		if ( model.getBlankNum() != 0) {
			html4.append("<h4>二.填空题（" + model.getBlankNum() + "*" + model.getBlankScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(2))
					html4.append("<p>" + (j ++) + "." + questionsA.get(i).getAnswer() + "<br><br>");
			}
		}
		j = 1;
		if ( model.getAnswerNum() != 0) {
			html4.append("<h4>三.问答题（" + model.getAnswerNum() + "*" + model.getAnswerScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(3))
					html4.append( "<p>" + (j++) + "."+  questionsA.get(i).getAnswer()+ "<br><br><br><br>");
			}
		}	
		
		request.put("questionAHTML", html.toString());
		request.put("questionBHTML", html2.toString());
		request.put("questionAAHTML", html3.toString());
		request.put("questionBAHTML", html4.toString());
		session.put("questionsA", questionsA);
		session.put("questionsB", questionsB);
		return "paperShow";
	}
	
	public String paperReCreate () {
		
		model = (CriteriaPaper) session.get("model");
		String createDate = model.getCreateDate();
		Date date = new Date();
		if(createDate == null || createDate.trim().equals("")){
			model.setCreateDate(StringUtil.formartDate("yyyy年MM月dd日", date));
			model.setCreateTime(date );
		}
		questionsA = questionService.getPaperQuestions(model);
		request.put("questionsA", questionsA);
		questionsB = questionService.getPaperQuestions(model);
		request.put("questionsB", questionsB);  
		session.put("model", model);
		StringBuffer html = new StringBuffer("");
		html.append("<h3 align='center'>" + model.getPaperName() + "A卷</h3>")
		.append("<h5 align='center'>出卷时间：" + model.getCreateDate() + "&nbsp;&nbsp;&nbsp;总分：" + model.getTotalScore() + "</h5>");
		int j = 1;
		if ( model.getChooseNum() != 0) {
			html.append("<h4>一.选择题（" + model.getChooseNum() + "*" + model.getChooseScore() + "分）</h4>");
			
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(1))
					html.append( questionsA.get(i).getContent().replaceFirst(">", ">" + (j++) + "."))
					.append("<br><br><br>");
			}
		}
		j = 1;
		if ( model.getBlankNum() != 0) {
			html.append("<h4>二.填空题（" + model.getBlankNum() + "*" + model.getBlankScore() + "分）</h4>");
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(2))
					html.append(questionsA.get(i).getContent().replaceFirst(">", ">" + (j++) + ".") + "<br><br>");
			}
		}
		j = 1;
		if ( model.getAnswerNum() != 0) {
			html.append("<h4>三.问答题（" + model.getAnswerNum() + "*" + model.getAnswerScore() + "分）</h4>");
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(3))
					html.append(questionsA.get(i).getContent()
							.replaceFirst(">", ">" + (j++) + ".")+ "<br><br><br><br>");
			}
		}		
		
		StringBuffer html3 = new StringBuffer("");
		
		html3.append("<h3 align='center'>" + model.getPaperName() + "A卷答案卷</h3>")
		.append("<h5 align='center'>出卷时间：" + model.getCreateDate() + "&nbsp;&nbsp;&nbsp;总分：" + model.getTotalScore() + "</h5>");
		j = 1;
		if ( model.getChooseNum() != 0) {
			html3.append("<h4>一.选择题（" + model.getChooseNum() + "*" + model.getChooseScore() + "分）</h4>");
			
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(1))
					html3.append( "<p>"+(j++) + "."+ questionsA.get(i).getAnswer() +"</p>");
			}
		}
		j = 1;
		if ( model.getBlankNum() != 0) {
			html3.append("<h4>二.填空题（" + model.getBlankNum() + "*" + model.getBlankScore() + "分）</h4>");
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(2))
					html3.append("<p>" + (j++)  + ".："+ questionsA.get(i).getAnswer() +"</p>");
			}
		}
		j = 1;
		if ( model.getAnswerNum() != 0) {
			html3.append("<h4>三.问答题（" + model.getAnswerNum() + "*" + model.getAnswerScore() + "分）</h4>");
			for (int i = 0;i < questionsA.size(); i++) {
				if (questionsA.get(i).getType().getQtId().equals(3))
					html3.append("<p>"+ (j++) + "."+ questionsA.get(i).getAnswer() +"</p>");
			}
		}	
		
		StringBuffer html2 = new StringBuffer("");
		html2.append("<h3 align='center'>" + model.getPaperName() + "B卷</h3>")
		.append("<h5 align='center'>出卷时间：" + model.getCreateDate() + "&nbsp;&nbsp;&nbsp;总分：" + model.getTotalScore() + "</h5>");
		j = 1;
		if ( model.getChooseNum() != 0) {
			html2.append("<h4>一.选择题（" + model.getChooseNum() + "*" + model.getChooseScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(1))
					html2.append(questionsB.get(i).getContent().replaceFirst(">", ">" + (j++) + "."))
					.append("<br><br/><br/>");
			}
		}
		j = 1;
		if ( model.getBlankNum() != 0) {
			html2.append("<h4>二.填空题（" + model.getBlankNum() + "*" + model.getBlankScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(2))
					html2.append(questionsB.get(i).getContent().replaceFirst(">", ">" + (j ++) + ".") + "<br><br>");
			}
		}
		j = 1;
		if ( model.getAnswerNum() != 0) {
			html2.append("<h4>三.问答题（" + model.getAnswerNum() + "*" + model.getAnswerScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(3))
					html2.append( questionsB.get(i).getContent().replaceFirst(">", ">" + (j++) + ".")+ "<br><br><br><br>");
			}
		}	
		
		StringBuffer html4 = new StringBuffer("");
		html4.append("<h3 align='center'>" + model.getPaperName() + "B卷答案卷</h3>")
		.append("<h5 align='center'>出卷时间：" + model.getCreateDate() + "&nbsp;&nbsp;&nbsp;总分：" + model.getTotalScore() + "</h5>");
		j = 1;
		if ( model.getChooseNum() != 0) {
			html4.append("<h4>一.选择题（" + model.getChooseNum() + "*" + model.getChooseScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(1))
					html4.append("<p>" + (j++) + "." + questionsB.get(i).getAnswer()+"<br/><br/>");
			}
		}
		j = 1;
		if ( model.getBlankNum() != 0) {
			html4.append("<h4>二.填空题（" + model.getBlankNum() + "*" + model.getBlankScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(2))
					html4.append("<p>" + (j ++) + "." + questionsA.get(i).getAnswer() + "<br><br>");
			}
		}
		j = 1;
		if ( model.getAnswerNum() != 0) {
			html4.append("<h4>三.问答题（" + model.getAnswerNum() + "*" + model.getAnswerScore() + "分）</h4>");
			for (int i = 0;i < questionsB.size(); i++) {
				if (questionsB.get(i).getType().getQtId().equals(3))
					html4.append( "<p>" + (j++) + "."+  questionsA.get(i).getAnswer()+ "<br><br><br><br>");
			}
		}	
		
		request.put("questionAHTML", html.toString());
		request.put("questionBHTML", html2.toString());
		request.put("questionAAHTML", html3.toString());
		request.put("questionBAHTML", html4.toString());
		session.put("questionsA", questionsA);
		session.put("questionsB", questionsB);
		return "paperShow";
	}
	
	private Set<QtTypeDistribution> getQtTypeDistribution() {
		Set<QtTypeDistribution> qtts = new HashSet<QtTypeDistribution>();
		QtTypeDistribution qt1 = new QtTypeDistribution(1,model.getChooseNum(),model.getChooseScore());
		QtTypeDistribution qt2 = new QtTypeDistribution(2,model.getBlankNum(),model.getBlankScore());
		QtTypeDistribution qt3 = new QtTypeDistribution(3,model.getAnswerNum(),model.getAnswerScore());
		qtts.add(qt1);
		qtts.add(qt2);
		qtts.add(qt3);
		return qtts;
	}
	/**
	 * 获取知识点分布
	 * @return
	 */
	private Set<KlPointDistribution> getKlPointDistribution() {
		String[] del = deletedList.split(",");
		String[] sel = selectedList.split(",");
		String[] left = StringUtil.delStrings(sel, del);
		model.setqId(left);
		Set<KlPointDistribution> klps = new HashSet<KlPointDistribution>();
		for (int i = 0 ;i < left.length; i ++) {
			if(i > 0) {
				boolean flag = true;
				for(int j = 0; j < i; j ++) {
					if(left[i].equals(left[j]))
						flag = false;
				}
				if(flag) {
					KlPointDistribution k = new KlPointDistribution(Integer.parseInt(left[i])
							,Integer.parseInt(ServletActionContext.getRequest().getParameter(left[i])));
					klps.add(k);
				}
			} else {
				KlPointDistribution k = new KlPointDistribution(Integer.parseInt(left[i])
						,Integer.parseInt(ServletActionContext.getRequest().getParameter(left[i])));
				klps.add(k);
			}
		}
		return klps;
	}
	
	/**
	 * A卷内容
	 */
	private String AContent;
	
	/**
	 * B卷内容
	 */
	private String BContent;
	
	private String AAContent;
	
	private String BAContent;
	
	public String getAContent() {
		return AContent;
	}

	public void setAContent(String aContent) {
		AContent = aContent;
	}

	public String getBContent() {
		return BContent;
	}

	public void setBContent(String bContent) {
		BContent = bContent;
	}
	
	
	public String getAAContent() {
		return AAContent;
	}

	public void setAAContent(String aAContent) {
		AAContent = aAContent;
	}

	public String getBAContent() {
		return BAContent;
	}

	public void setBAContent(String bAContent) {
		BAContent = bAContent;
	}

	@SuppressWarnings("unchecked")
	public String paperSave() {
		String msg = "保存成功！";
		model = (CriteriaPaper) session.get("model");
		Paper p1 = new Paper(model.getPaperName(),model.getCreateTime(),"A",model.getTotalScore());
		Date date ;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {				
			e.printStackTrace();
			msg="数据库出错";
			request.put("msg", msg);
			return "showques";
		}
		p1.setCreateTime(date);
		p1.setPaperId(0);
		AContent = AContent.replaceAll("<", "&it");
		AContent = AContent.replaceAll(">", "&gt");
		AAContent = AAContent.replaceAll("<", "&it").replaceAll(">", "&gt");
		p1.setAnswerContent(AAContent);
		p1.setContent(AContent);
		paperService.savePaper(p1);
		Paper p2 = new Paper(model.getPaperName(),model.getCreateTime(),"B",model.getTotalScore());
		p2.setPaperId(1);
		BContent = BContent.replaceAll("<", "&it");
		BContent = BContent.replaceAll(">", "&gt");
		BAContent = BAContent.replaceAll("<", "&it").replaceAll(">", "&gt");
		p2.setAnswerContent(BAContent);
		p2.setContent(BContent);
		p2.setCreateTime(date);
		paperService.savePaper(p2);
		questionsA = (List<Question>) session.get("questionsA");
		questionsB = (List<Question>) session.get("questionsA");
		for (Question q:questionsA) {
			PaperQuestionRel pqr = new PaperQuestionRel();
			pqr.setId(0);
			pqr.setQuestion(q);
			pqr.setPaper(p1);
			paperQuestionRelService.savePaperQuestionRel(pqr);
		}
		
		for (Question q:questionsB) {
			PaperQuestionRel pqr = new PaperQuestionRel();
			pqr.setId(0);
			pqr.setQuestion(q);
			pqr.setPaper(p2);
			paperQuestionRelService.savePaperQuestionRel(pqr);
		}
		request.put("msg", msg);
		return "success";
	}
	
	public String paperList() {
		List<Paper> papers = paperService.listPaper();
		request.put("papers", papers);
		return "paperList";
	}
	
	public String paperDelete(){
		Paper paper = new Paper();
		paper.setPaperId(model.getPaperId());
		paperQuestionRelService.deleteRel(model.getPaperId());
		paperService.deletePaper(paper);
		request.put("msg", "删除成功");
		return "paperDelete";
	}
}
