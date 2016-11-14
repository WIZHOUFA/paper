package com.fnst.paper.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fnst.paper.criteria.CriteriaPaper;
import com.fnst.paper.criteria.KlPointDistribution;
import com.fnst.paper.criteria.QtTypeDistribution;
import com.fnst.paper.dao.impl.KnowledgePointDao;
import com.fnst.paper.dao.impl.QuestionDAO;
import com.fnst.paper.dao.impl.QuestionTypeDao;
import com.fnst.paper.pojo.KnowledgePoint;
import com.fnst.paper.pojo.Question;
import com.fnst.paper.pojo.QuestionType;
import com.fnst.paper.service.QuestionService;

@Transactional
@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService{
	
	@Resource
	private QuestionDAO questionDAO;
	@Resource
	private QuestionTypeDao questionTypeDao;
	@Resource
	private KnowledgePointDao knowledgePointDao;
	
	public List<Question> getAllQuestions() {
		String hql = "select q from Question q ,QuestionType t ,KnowledgePoint k where q.avaliable=1 and q.type=t and q.knowledge=k";
		return questionDAO.find(hql);
	}

	@Override
	public boolean saveQues(Question ques) {
		try {
			questionDAO.save(ques);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Question one(Question ques) {
		return questionDAO.findOne("select q from Question q ,QuestionType t ,KnowledgePoint k where q.avaliable=1 and q.type=t and q.knowledge=k and q.qId="+ques.getqId());
	}

	@Override
	public List<QuestionType> findAllType() {
		String hql = "select qt from QuestionType qt";
		return questionTypeDao.getTypes(hql);
	}

	@Override
	public List<KnowledgePoint> findAllKP() {
		String hql ="select kp from KnowledgePoint kp";
		return knowledgePointDao.getKPoints(hql);
	}
	
	@Override
	public List<Question> getQuestionByPage(int rows, int page) {
		String hql="select q from Question q ,QuestionType t ,KnowledgePoint k where q.avaliable=1 and q.type=t and q.knowledge=k";
		return questionDAO.find(hql, page, rows);
	}
	
	
	
	@SuppressWarnings("unused")
	@Override
	public List<Question> getPaperQuestions(CriteriaPaper riteriaPaper) {

		List<Question> QuestionList = new ArrayList<Question>();

		int qtTotalNum = riteriaPaper.getTotalNum();
		int yearNum = riteriaPaper.getMatchYears();
		int degree = riteriaPaper.getMatchDegree();
		int paperLevel = riteriaPaper.getPaperLevel();
		String levelStr = "1,2,3";
		if (paperLevel == 2)
			levelStr = "2,3,4";
		if (paperLevel == 3)
			levelStr = "3,4,5";

		Set<KlPointDistribution> klPointDistributions = new HashSet<KlPointDistribution>();
		Set<QtTypeDistribution> qtTypeDistributions = new HashSet<QtTypeDistribution>();
		klPointDistributions = riteriaPaper.getKlPointDistributions();
		qtTypeDistributions = riteriaPaper.getQtTypeDistributions();

		// 第一步根据匹配度取得往年出现的试题
		List<Question> matchlist = getMatchQuestions(qtTotalNum, yearNum,
				degree, klPointDistributions, levelStr);

		List<KlPointDistribution> kpdList = new ArrayList<KlPointDistribution>();
		List<QtTypeDistribution> qtdList = new ArrayList<QtTypeDistribution>();

		Iterator<KlPointDistribution> it = klPointDistributions.iterator();

		while (it.hasNext()) {
			KlPointDistribution kpd = new KlPointDistribution();
			KlPointDistribution kpdTemp = it.next();
			kpd.setkId(kpdTemp.getkId());
			kpd.setkNum(kpdTemp.getkNum());
			kpdList.add(kpd);
		}
		qtdList.addAll(qtTypeDistributions);
		int needNum = qtTotalNum; // 剩余需要的试题数

		// 第二步减去题型分布与知识点分布中的已取得的试题数量
		if (matchlist != null && !matchlist.isEmpty()) {
			QuestionList.addAll(matchlist);
			needNum -= matchlist.size();
			for (int i = 0; i < matchlist.size(); i++) {
				matchlist.get(i).getType();

				// 计算新的知识点分布
				// System.out.println(kpdList.size());
				for (int p = 0; p < kpdList.size(); p++) {
					if (kpdList.get(p).getkId() == matchlist.get(i)
							.getKnowledge().getkId()) {
						kpdList.get(p).setkNum(kpdList.get(p).getkNum() - 1);
					}
					if (kpdList.get(p).getkNum() == 0) {
						kpdList.remove(p);
						break;
					}
				}

				// 计算新的题型分布
				for (int p = 0; p < qtdList.size(); p++) {
					if (qtdList.get(p).getQtId() == matchlist.get(i).getType()
							.getQtId()) {
						qtdList.get(p).setQtNum(qtdList.get(p).getQtNum() - 1);
					}
					if (qtdList.get(p).getQtNum() == 0) {
						qtdList.remove(p);
						break;
					}

				}
			}

		}

		for (int m = 0; m < qtdList.size() - 1; m++) {
			for (int n = m + 1; n < qtdList.size(); n++) {
				if (qtdList.get(m).getQtNum() > qtdList.get(n).getQtNum()) {
					QtTypeDistribution temp = qtdList.get(m);
					qtdList.set(m, qtdList.get(n));
					qtdList.set(n, temp);
				}
			}
		}

		// 按题型筛选
		// 匹配年份区域beginTime至endTime
		Date now = new Date();
		String endTime = DateFormat.getDateInstance(DateFormat.DEFAULT).format(
				now);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(now);
		rightNow.add(Calendar.YEAR, -yearNum);
		String beginTime = DateFormat.getDateInstance(DateFormat.DEFAULT)
				.format(rightNow.getTime());

		for (int i = 0; i < qtdList.size(); i++) {
			int qtId = qtdList.get(i).getQtId(); // 题型id
			int qtNum = qtdList.get(i).getQtNum(); // 该题型需筛选的试题量
			int kpNum = kpdList.size(); // 剩余需筛选试题的知识点数

			while (qtNum > kpNum) {
				// 题型范围
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < kpdList.size(); j++) {
					sb.append(kpdList.get(j).getkId());
					sb.append(",");
				}
				String qtIds = null;
				if (kpdList.size() > 0)
					qtIds = sb.toString().substring(0,
							sb.toString().length() - 1);

				// 需要排除的已经取得的q_id
				StringBuffer sb2 = new StringBuffer();
				String qIds = null;
				for (int j = 0; j < QuestionList.size(); j++) {
					sb2.append(QuestionList.get(j).getqId());
					sb2.append(",");
				}
				if (QuestionList.size() > 0) {
					qIds = sb2.toString().substring(0,
							sb2.toString().length() - 1);
				}

				StringBuffer sb1 = new StringBuffer();
				sb1.append("SELECT q FROM Question q ,QuestionType t ,KnowledgePoint k WHERE q.qId NOT IN("
						+ "SELECT r.question.qId FROM PaperQuestionRel r WHERE r.paper IN("
						+ "SELECT r.paper FROM Paper p WHERE p.createTime BETWEEN '");
				sb1.append(beginTime);
				sb1.append("' AND '");
				sb1.append(endTime);
				sb1.append("')) AND q.knowledge IN(");
				sb1.append(qtIds);
				if (qIds != null) {
					sb1.append(") AND q.qId NOT IN(");
					sb1.append(qIds);
				}
				sb1.append(") AND q.level In(");
				sb1.append(levelStr);
				sb1.append(") AND q.avaliable=1 AND q.type=");
				sb1.append(qtId);
				sb1.append(" AND q.type=t AND q.knowledge=k ");
				if(kpNum > 1){
				   sb1.append("GROUP BY q.knowledge ");
				}
				sb1.append("ORDER BY RAND()");

				String hql1 = sb1.toString();
				List<Question> list = questionDAO.find(hql1, 0, qtNum);
				if (list.isEmpty())
					return QuestionList;
				// 将筛选出的试题加入到QuestionList中
				QuestionList.addAll(list);

				qtNum -= list.size(); // 该题型需筛选的试题量

				// 计算新的知识点分布
				for (int k = 0; k < list.size(); k++) {
					for (int p = 0; p < kpdList.size(); p++) {
						if (kpdList.get(p).getkId() == list.get(k)
								.getKnowledge().getkId())
							kpdList.get(p)
									.setkNum(kpdList.get(p).getkNum() - 1);
						if (kpdList.get(p).getkNum() == 0) {
							kpdList.remove(p);
							break;
						}
					}
				}
				kpNum = kpdList.size();// 剩余需筛选试题的知识点数
			}

			while (qtNum > 0) {
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < kpdList.size(); j++) {
					sb.append(kpdList.get(j).getkId());
					sb.append(",");
				}
				String qtIds = null;
				if (sb.toString().length() != 0)
					qtIds = sb.toString().substring(0,
							sb.toString().length() - 1);
				// System.out.println(qtIds);

				// 需要排除的已经取得的q_id
				StringBuffer sb2 = new StringBuffer();
				for (int j = 0; j < QuestionList.size(); j++) {
					sb2.append(QuestionList.get(j).getqId());
					sb2.append(",");
				}
				String qIds = null;
				if (QuestionList.size() > 0)
					qIds = sb2.toString().substring(0,
							sb2.toString().length() - 1);
				// System.out.println(qIds);

				StringBuffer sb1 = new StringBuffer();
				sb1.append("SELECT q FROM Question q ,QuestionType t ,KnowledgePoint k WHERE q.qId NOT IN("
						+ "SELECT r.question.qId FROM PaperQuestionRel r WHERE r.paper IN("
						+ "SELECT r.paper FROM Paper p WHERE p.createTime BETWEEN '");
				sb1.append(beginTime);
				sb1.append("' AND '");
				sb1.append(endTime);
				sb1.append("')) AND q.knowledge IN(");
				sb1.append(qtIds);
				if (qIds != null) {
					sb1.append(") AND q.qId NOT IN(");
					sb1.append(qIds);
				}
				sb1.append(") AND q.level In(");
				sb1.append(levelStr);
				sb1.append(") AND q.avaliable=1 AND q.type=");
				sb1.append(qtId);
				sb1.append(" AND q.type=t AND q.knowledge=k GROUP BY q.knowledge ORDER BY RAND()");

				String hql1 = sb1.toString();
				List<Question> list = questionDAO.find(hql1, 0, qtNum);
				if (list.isEmpty())
					return QuestionList;

				// 将筛选出的试题加入到QuestionList中
				QuestionList.addAll(list);

				qtNum -= list.size();

				// 计算新的知识点分布
				for (int k = 0; k < list.size(); k++) {
					for (int p = 0; p < kpdList.size(); p++) {
						if (kpdList.get(p).getkId() == list.get(k)
								.getKnowledge().getkId())
							kpdList.get(p)
									.setkNum(kpdList.get(p).getkNum() - 1);
						if (kpdList.get(p).getkNum() == 0) {
							kpdList.remove(p);
							break;
						}
					}
				}
			}
		}
		return QuestionList;
	}

	@Override
	public List<Question> getMatchQuestions(Integer totalNum,
			Integer matchYears, Integer matchDegree,
			Set<KlPointDistribution> klPointDistributions, String levelStr) {

		// 随机确定一个允许范围内的匹配的算出取得往年题目的数量
		System.out.println(Math.random());
		int matchNum = (int) (Math.random() * matchDegree / 100 * totalNum);

		// 若数量大于0则去随机取得相应的题目
		if (matchNum > 0) {
			// 匹配年份区域beginTime至endTime
			if (matchNum > klPointDistributions.size())
				matchNum = klPointDistributions.size();
			Date now = new Date();
			String endTime = DateFormat.getDateInstance(DateFormat.DEFAULT)
					.format(now);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(now);
			rightNow.add(Calendar.YEAR, -matchYears);
			String beginTime = DateFormat.getDateInstance(DateFormat.DEFAULT)
					.format(rightNow.getTime());

			Iterator<KlPointDistribution> it = klPointDistributions.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				sb.append(it.next().getkId());
				sb.append(",");
			}
			String kIds = null;
			if (sb.toString().length() != 0)
				kIds = sb.toString().substring(0, sb.toString().length() - 1);
			// System.out.println(kIds);

			// 根据匹配的所有PaperIds取得相应的q_id,再根据q_id随机取matchNum条试题
			StringBuffer sb1 = new StringBuffer();
			sb1.append("SELECT q FROM Question q ,QuestionType t ,KnowledgePoint k WHERE q.qId IN("
					+ "SELECT r.question.qId FROM PaperQuestionRel r WHERE r.paper IN("
					+ "SELECT r.paper FROM Paper p WHERE p.createTime BETWEEN '");
			sb1.append(beginTime);
			sb1.append("' AND '");
			sb1.append(endTime);
			sb1.append("')) AND q.knowledge IN(");
			sb1.append(kIds);
			sb1.append(") AND q.level In(");
			sb1.append(levelStr);
			sb1.append(") AND q.avaliable=1 AND q.type.typeName!='问答题' AND q.type=t AND q.knowledge=k ORDER BY RAND()");
			String hql1 = sb1.toString();
			// System.out.println(hql1);

			return questionDAO.find(hql1, 1, matchNum);
		} else {
			return null;
		}
	}

	@Override
	public boolean updateQues(Question ques) {
		try {
			questionDAO.updateQues(ques);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public int getTotal() {
		String hql = "select q from Question q  where q.avaliable=1";
		List<Question> quesList = questionDAO.find(hql);
		if (quesList ==null || quesList.size()==0) {
			return 0;
		}else{
			return quesList.size();
		}
	}

	@Override
	public List<Question> searchByConditon(String searchContent,
			String searchType, String searchLevel, String searchKnowledge, int rows, int page) {
		String hql = "select q from Question q , QuestionType t , KnowledgePoint k where q.avaliable=1 and "
				+ "q.type=t and q.knowledge=k and q.content like'%"+searchContent+"%' ";
		if (!searchType.equals("-1")) {
			hql=hql+" and q.type.qtId ="+searchType;
		}
		if (!searchLevel.equals("-1")) {
			hql=hql+" and q.level = "+searchLevel;
		}
		if (!searchKnowledge.equals("-1")) {
			hql=hql+" and q.knowledge.kId = "+searchKnowledge;
		}
		return questionDAO.find(hql, page, rows);
	}

	@Override
	public int getTotalByCondition(String searchContent, String searchType,
			String searchLevel, String searchKnowledge) {
		String hql = "select q from Question q , QuestionType t , KnowledgePoint k where q.avaliable=1 and "
				+ "q.type=t and q.knowledge=k and q.content like'%"+searchContent+"%' ";
		if (!searchType.equals("-1")) {
			hql=hql+" and q.type.qtId ="+searchType;
		}
		if (!searchLevel.equals("-1")) {
			hql=hql+" and q.level = "+searchLevel;
		}
		if (!searchKnowledge.equals("-1")) {
			hql=hql+" and q.knowledge.kId = "+searchKnowledge;
		}
		List<Question> quesList = questionDAO.find(hql);
		if (quesList ==null || quesList.size()==0) {
			return 0;
		}else{
			return quesList.size();
		}
	}

	@Override
	public int isTypeExist(String typeContent) {
		String hql="From QuestionType qt where qt.typeName='"+typeContent+"'";
		List<QuestionType> types = questionTypeDao.find(hql);
		if(types!=null && types.size()>0){
			return types.get(0).getQtId();
		}else{
			return -1;
		}
	}

	@Override
	public boolean saveType(QuestionType qt) {
		try {
			questionTypeDao.saveQt(qt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
    
	
	private static final String STATISBYQUESTYPE = "select qt.type_name as typeName, count(qt.type_name) as num " +
													"from question q " + 
													"left join question_type qt " +
													" on q.qt_id = qt.qt_id " +
													"group by qt.type_name";
	private static final String STATISBYKNOWPOINT = "select kp.content as typeName, count(q.q_id) as num " +
													"from  question q " + 
													"right join knowledge_point kp " +
													"on q.k_id = kp.k_id " +
													"group by kp.content";
	private static final String STATISBYLEVEL = "SELECT Q.LEVEL as typeName,COUNT(q.LEVEL) as num FROM QUESTION Q GROUP BY Q.LEVEL order by Q.level";
	@Override
	public Map<String, Long> statisticsQuestion(String type) {
		
        String sql = "";
        
        if (type.equals("1")) {
        	sql = STATISBYQUESTYPE;
        } else if (type.equals("2")) {
        	sql = STATISBYKNOWPOINT;
        } else if (type.equals("3")) {
        	sql = STATISBYLEVEL;
        }
        
		return questionDAO.statisticsQuestion(sql);
	}
}
