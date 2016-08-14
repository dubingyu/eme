package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.SurveyDao;
import cn.xidian.entity.Survey;
import cn.xidian.entity.SurveyQuestion;
import cn.xidian.entity.SurveyReplyer;
import cn.xidian.entity.SurveySelector;
import cn.xidian.entity.Teacher;
import cn.xidian.entity.TextAnswer;

@Component("surveyDaoImpl")
public class SurveyDaoImpl implements SurveyDao{
	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean createSurvey(Survey survey) {
		// TODO Auto-generated method stub
		currentSession().save(survey);
		
		return true;
	}

	@Override
	public boolean addQuestion(SurveyQuestion sq) {
	
		currentSession().save(sq);
		return true;
	}

	@Override
	public boolean updateSurvey(Survey survey) {
		// TODO Auto-generated method stub
		String sql="update Survey s set s.startTime=?,s.endTime=? where s.surveyId=? ";
		Query query=currentSession().createQuery(sql);
		query.setDate(0, survey.getStartTime());
		query.setDate(1, survey.getEndTime());
		query.setInteger(2, survey.getSurveyId());
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean addSelector(SurveySelector surveySelector) {
		// TODO Auto-generated method stub
		currentSession().save(surveySelector);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> selectAllSurveys(Teacher teacher) {
		// TODO Auto-generated method stub
		String sql="from Survey where tchrId=? and delState=1 order by surveyId desc";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, teacher.getTchrId());
		List<Survey> surveys=query.list();
		return surveys;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> findSurveys(Teacher teacher, Integer begin, Integer limit) {
		// TODO Auto-generated method stub
		String sql="from Survey where tchrId=? and delState=1 order by surveyId desc";
		Query query=currentSession().createQuery(sql).setFirstResult(begin).setMaxResults(limit);
		query.setInteger(0, teacher.getTchrId());
		List<Survey> surveys=query.list();
		return surveys;
	}

	@Override
	public Survey selectSurveyById(Integer surveyId) {
		// TODO Auto-generated method stub
		String sql="from Survey where surveyId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, surveyId);
		Survey survey=new Survey();
		survey=(Survey) query.uniqueResult();
		return survey;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyQuestion> selectQuestionBysurveyId(Integer surveyId) {
		// TODO Auto-generated method stub
		String sql="from SurveyQuestion where surveyId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, surveyId);
		List<SurveyQuestion> surveyQuestions=query.list();
		return surveyQuestions;
	}

	@Override
	public boolean updateSelectorNum(Integer surveyId, Integer questionId, Integer selectorNum) {
		// TODO Auto-generated method stub
		String sql="update SurveySelector  set sumNum=sumNum +'1' where surveyId=? and questionId=? and selectorNum=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, surveyId);
		query.setInteger(1, questionId);
		query.setInteger(2, selectorNum);
		query.executeUpdate();
		return true;
	}

	@Override
	public SurveyQuestion selectQuestionById(Integer questionId) {
		// TODO Auto-generated method stub
		String sql="from SurveyQuestion where questionId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, questionId);
		SurveyQuestion surveyQuestion=new SurveyQuestion();
		surveyQuestion=(SurveyQuestion) query.uniqueResult();
		return surveyQuestion;
	}

	@Override
	public boolean addTextAnswer(TextAnswer textAnswer) {
		// TODO Auto-generated method stub
		currentSession().save(textAnswer);
		return true;
	}

	@Override
	public boolean updateSurveySumById(Integer surveyId) {
		// TODO Auto-generated method stub
		String sql="update Survey set sumNum=sumNum+'1' where surveyId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, surveyId);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean addSurveyReplyer(SurveyReplyer surveyReplyer) {
		// TODO Auto-generated method stub
		currentSession().save(surveyReplyer);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveySelector> selectSurveySelectors(Integer surveyId, Integer questionId) {
		// TODO Auto-generated method stub
		String sql="from SurveySelector where surveyId=? and questionId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, surveyId);
		query.setInteger(1, questionId);
		List<SurveySelector> surveySelectors=query.list();
		return surveySelectors;
	}

	@Override
	public boolean publishSurvey(Integer surveyId) {
		// TODO Auto-generated method stub
		String sql="update Survey set state= 1 where surveyId =?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, surveyId);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean deleteSurvey(Integer surveyId) {
		// TODO Auto-generated method stub
		String sql="update Survey set delState=0 where surveyId=?";
		Query query=currentSession().createQuery(sql);
		query.setInteger(0, surveyId);
		query.executeUpdate();
		return true;
	}

}
