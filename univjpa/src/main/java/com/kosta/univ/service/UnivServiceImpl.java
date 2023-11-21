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
			throw new Exception("제 1학과명 오류");
		}
		List<Student> stuList = new ArrayList<Student>(dept.get().getStudent1());
		return stuList;
	}

	@Override
	@Transactional
	public List<Student> studentListInDept2ByDeptName(String Deptname) throws Exception {
		Optional<Department> dept = departmentRepository.findByDname(Deptname);
		if(dept.isEmpty()) {
			throw new Exception("제 2학과명 오류");
		}
		List<Student> stuList = new ArrayList<Student>(dept.get().getStudent2());
		return stuList;
	}

	@Override
	public List<Student> studentListByGrade(Integer grade) throws Exception {
		List<Student> stuList = studentRepository.findByGrade(grade);
		if(stuList.isEmpty()) {
			throw new Exception("학년 오류");
		}
		return stuList;
	}

	@Override
	@Transactional
	public List<Student> studentListByNoProfessor() throws Exception {
		List<Student> stuList = studentRepository.findByProfessor_profnoIsNull();
		if(stuList.isEmpty()) {
			throw new Exception("교수번호 없는 학생 없음");
		}
		return stuList;
	}

	@Override
	public Student studentByStudno(Integer studno) throws Exception {
		Optional<Student> stu = studentRepository.findById(studno);
		if(stu.isEmpty()) {
			throw new Exception("학생번호 오류");
		}
		return stu.get();
	}

	@Override
	public Student studentByJumin(String jumin) throws Exception {
		Optional<Student> stu = studentRepository.findByJumin(jumin);
		if(stu.isEmpty()) {
			throw new Exception("주민번호 오류");
		}
		return stu.get();
	}

	@Override
	@Transactional
	public List<Student> studentListByProfNo(Integer profNo) throws Exception {
		List<Student> stuList = studentRepository.findByProfessor_profno(profNo);
		if(stuList.isEmpty()) {
			throw new Exception("교수 번호 오류");
		}
		return stuList;
	}

	@Override
	@Transactional
	public List<Student> studentListByProfName(String profName) throws Exception {
		List<Student> stuList = studentRepository.findByProfessor_name(profName);
		if(stuList.isEmpty())throw new Exception("교수 이름 오류");
		return stuList;
	}

	@Override
	public Professor professorByProfNo(Integer profNo) throws Exception {
		Optional<Professor> prof = professorRepository.findById(profNo);
		if(prof.isEmpty())throw new Exception("교수 번호 오류");
		return prof.get();
	}

	@Override
	public List<Professor> professorListByProfName(String profName) throws Exception {
		List<Professor> profList = professorRepository.findByName(profName);
		if(profList.isEmpty())throw new Exception("교수 이름 이상해");
		return profList;
	}

	@Override
	@Transactional
	public List<Professor> professorListByDeptNo(Integer deptNo) throws Exception {
		Optional<Department> dept = departmentRepository.findById(deptNo);
		if(dept.isEmpty()) {
			throw new Exception("과목번호 수상해");
		}
		return new ArrayList<Professor>(dept.get().getProfessorList());
	}

	@Override
	@Transactional
	   public List<Professor> professorListByDeptName(String deptName) throws Exception {
	      Optional<Department> odepartment = departmentRepository.findByDname(deptName);
	      if(odepartment.isEmpty()) throw new Exception("학과명 오류");
	      return new ArrayList<>(odepartment.get().getProfessorList());
	   }

	@Override
	public List<Professor> professorListByPosition(String position) throws Exception {
		List<Professor> profList = professorRepository.findByPosition(position);
		if(profList.isEmpty())throw new Exception("포지션 오류");
		return profList;
	}

	@Override
	public Department departmentByDeptNo(Integer deptNo) throws Exception {
		Optional<Department> dept = departmentRepository.findById(deptNo);
		if(dept.isEmpty())throw new Exception("과목번호 수상해");
		return dept.get();
	}

	@Override
	public Department departmentByDeptName(String deptName) throws Exception {
		Optional<Department> dept = departmentRepository.findByDname(deptName);	
		if(dept.isEmpty())throw new Exception("과목이름 요상해");
		return dept.get();
	}

	@Override
	public List<Department> departmentListByPart(Integer part) throws Exception {
		List<Department> deptList = departmentRepository.findByPart(part);
		if(deptList.isEmpty())throw new Exception("파트 요놈!");
		return deptList;
	}

	@Override
	public List<Department> departmentListByBuild(String build) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
