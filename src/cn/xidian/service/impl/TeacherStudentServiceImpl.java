package cn.xidian.service.impl;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.TeacherStudentDao;
import cn.xidian.entity.Clazz;
import cn.xidian.entity.EvaluateResult;
import cn.xidian.entity.MaxEva;
import cn.xidian.entity.PageBean;
import cn.xidian.entity.Student;
import cn.xidian.entity.StudentCourse;
import cn.xidian.service.TeacherStudentService;
import cn.xidian.utils.PageUtils;
import cn.xidian.web.bean.AdminStuLimits;

@Component
public class TeacherStudentServiceImpl implements TeacherStudentService {

	private TeacherStudentDao teacherStudentDao;

	@Resource(name="teacherStudentDaoImpl")
	public void teacherStudentDao(TeacherStudentDao teacherStudentDao) {
		this.teacherStudentDao = teacherStudentDao;
	}

	@Override
	public List<Clazz> findChargeCla(Integer id) {
		// TODO Auto-generated method stub
		return teacherStudentDao.selectChargeCla(id);
	}

	@Override
	public List<Student> selectChargeStus(Integer id) {
		// TODO Auto-generated method stub
		return teacherStudentDao.selectChargeStus(id);
	}

	@Override
	public Set<Student> selectStuLimits(AdminStuLimits limits, List<Clazz> clazzs) {
		// TODO Auto-generated method stub
		return teacherStudentDao.selectStuLimits(limits,clazzs);
	}

	@Override
	public boolean addEvaScore(EvaluateResult evaluateResult) {
		// TODO Auto-generated method stub
		return teacherStudentDao.addEvaScore(evaluateResult);
	}

	@Override
	public Integer selectSummaryEva(Integer claId, String schoolYear) {
		// TODO Auto-generated method stub
		return teacherStudentDao.selectSummaryEva(claId,schoolYear);
	}

	@Override
	public Clazz selectClazzById(Integer id) {
		// TODO Auto-generated method stub
		return teacherStudentDao.selectClazzById(id);
	}

	@Override
	public boolean deleteEvas(Integer claId, String schoolYear) {
		// TODO Auto-generated method stub
		return teacherStudentDao.deleteEvas(claId,schoolYear);
	}

	@Override
	public List<StudentCourse> selectStuGrades(Integer stuId, String schoolYear) {
		// TODO Auto-generated method stub
		return teacherStudentDao.selectStuGrades(stuId,schoolYear);
	}

	@Override
	public EvaluateResult selectEvaluateResultById(Integer id) {
		// TODO Auto-generated method stub
		
		return teacherStudentDao.selectEvaluateResultById(id);
	}

	@Override
	public MaxEva selectMaxEva(String schoolYear) {
		// TODO Auto-generated method stub
		MaxEva eva=new MaxEva();	
		eva.setMaxM1(teacherStudentDao.selectMaxEva(schoolYear,1).getM1());
		eva.setMaxM2(teacherStudentDao.selectMaxEva(schoolYear,2).getM2());
		eva.setMaxM3(teacherStudentDao.selectMaxEva(schoolYear,3).getM3());
		eva.setMaxM4(teacherStudentDao.selectMaxEva(schoolYear,4).getM4());
		eva.setMaxM5(teacherStudentDao.selectMaxEva(schoolYear,5).getM5());
		return eva;
	}

	@Override
	public PageBean<EvaluateResult> findByPageCid(Integer claId,String schoolYear, Integer page) {
		// TODO Auto-generated method stub
		PageBean<EvaluateResult> pageBean= new PageBean<EvaluateResult>();
		int totalCount=0;
		totalCount = teacherStudentDao.selectSummaryEva(claId, schoolYear);
		pageBean=PageUtils.page(page, totalCount);
		List<EvaluateResult> list=teacherStudentDao.findByPageCid(claId,schoolYear,pageBean.getBegin(), pageBean.getLimit());
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean<StudentCourse> selectStuGradesByPage(Integer stuId, String schoolYear, Integer page) {
		// TODO Auto-generated method stub
		PageBean<StudentCourse> pageBean=new PageBean<StudentCourse>();
		List<StudentCourse> studentCourses=teacherStudentDao.selectStuGrades(stuId, schoolYear);
		pageBean=PageUtils.page(page, studentCourses.size());
		List<StudentCourse> list=teacherStudentDao.findStuGradesByPage(stuId,schoolYear,pageBean.getBegin(), pageBean.getLimit());
		pageBean.setList(list);
		return pageBean;
	}

	

	
}
