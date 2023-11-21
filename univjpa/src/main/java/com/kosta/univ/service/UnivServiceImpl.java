package com.kosta.univ.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;
@Service
public class UnivServiceImpl implements UnivService{
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Student> studentListByName(String studName) throws Exception {
		List<Student> stuList = studentRepository.findByName(studName);
		if(stuList.isEmpty()) {
			throw new Exception("이름 오류");
		}
		return stuList;
	}

	@Override
	@Transactional
	public List<Student> studentListInDept1ByDeptName(String Deptname) throws Exception {
		Optional<Department> dept = departmentRepository.findByDname(Deptname);
		if(dept.isEmpty()) {
			throw new Exception("학과명 오류");
		}
		List<Student> stuList = new ArrayList<Student>(dept.get().getStudent1());
		return stuList;
	}

	@Override
	public List<Student> studentListInDept2ByDeptName(String Deptname) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> studentListByGrade(Integer grade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> studentListByNoProfessor() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student studentByStudno(Integer studno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student studentByJumin(String jumin) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> studentListByProfNo(Integer profNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> studentListByProfName(String profName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor professorByProfNo(Integer profNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Professor> professorListByProfName(String profName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Professor> professorListByDeptNo(Integer deptNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	   public List<Professor> professorListByDeptName(String deptName) throws Exception {
	      Optional<Department> odepartment = departmentRepository.findByDname(deptName);
	      if(odepartment.isEmpty()) throw new Exception("학과명 오류");
	      // TODO Auto-generated method stub
	      return new ArrayList<>(odepartment.get().getProfessorList());
	   }

	@Override
	public List<Professor> professorListByPosition(String position) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department departmentByDeptNo(Integer deptNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department departmentByDeptName(String deptName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> departmentListByPart(Integer part) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> departmentListByBuild(String build) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
