package com.kosta.univ.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;
import com.kosta.univ.repository.UnivRepository;
import com.querydsl.core.Tuple;

@Service
public class UnivServiceImpl implements UnivService{
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private UnivRepository univRepository;
	
	@Override
	public void enterStudent(Student stud) throws Exception {
		Optional<Student> student = studentRepository.findById(stud.getStudno());
		if(student.isEmpty()) {
			studentRepository.save(stud);			
		} else {
			throw new Exception("이미 있는 학생번호입니다.");
		}
	}

	@Override
	public Student getStudentByNo(Integer studno) throws Exception {

		Optional<Student> student = studentRepository.findById(studno);
		if(student.isEmpty()) {
			throw new Exception("학생번호 오류");
			
		} else{
			return student.get();
		}
	}

	//학번으로 학생 정보 조회(학과명 포함)
	@Override
	public Map<String, Object> getstudentByNoWithDeptName(Integer studno) throws Exception {
		Tuple tuple = univRepository.findStudentNoWithDeptNameByStudno(studno);
		Map<String,Object> res = new HashMap<String, Object>();
		if(tuple == null) {
			throw new Exception("학생번호가 이상하다");
		} else {
		res.put("studNo", tuple.get(0, Integer.class));
		res.put("deptName", tuple.get(1, String.class));
		return res;
		}
	}
//	4
	@Override
	public Map<String, Object> getstudentByNoWithProfName(Integer studno) throws Exception {
		Tuple tuple = univRepository.findStudentByNoWithProfName(studno);
		Map<String,Object> res = new HashMap<String, Object>();
		if(tuple == null) {
			throw new Exception("학생번호가 이상하다");
		} else {
			res.put("studNo", tuple.get(0, Integer.class));
			res.put("profName", tuple.get(1,String.class));
			return res;
		}
	}

	@Override
	public Map<String, Object> getstudentByNoWithDeptNameAndProfName(Integer studno) throws Exception {
		Tuple tuple = univRepository.findStudentByNoWithDeptNameAndProfName(studno);
		Map<String,Object> res = new HashMap<String, Object>();
		if(tuple == null) {
			throw new Exception("학생번호가 이상하다");
		} else {
			res.put("studNo", tuple.get(0, Integer.class));
			res.put("deptName", tuple.get(1, String.class));
			res.put("profName", tuple.get(2,String.class));
			return res;
		}
	}

	@Override
	public List<Student> getStudentByName(String name) throws Exception {
		List<Student> studentList = studentRepository.findByName(name);
		if(studentList.isEmpty()) {
			throw new Exception("학생이름에 맞는 학생없음");
		}else {
			return studentList;
		}
	}

	@Override
	public List<Student> getStudentByDeptNo(Integer deptno) throws Exception {
//		List<Student> studentList = studentRepository
		List<Student> studentList = univRepository.findStudentByDeptno(deptno);
		if(studentList.isEmpty()) {
			throw new Exception("학과번호 오류");
		}else {
			return studentList;
		}
	}

	@Override
	public List<Student> getStudentByDeptName(String deptName) throws Exception {
		List<Student> studentList = univRepository.findStudentByDeptName(deptName);
		if(studentList.isEmpty()) {
			throw new Exception("학과이름 오류");
		} else {
			return studentList;
		}
	}

	@Override
	public List<Student> getStudentByGrade(Integer grade) throws Exception {
		List<Student> studentList = studentRepository.findByGrade(grade);
		if(studentList.isEmpty()) {
			throw new Exception("학년 오류");
		} else {
			return studentList;
		}
	}

	@Override
	public List<Student> getStudentByDeptNameAndGrade(String deptName, Integer grade) throws Exception {
		List<Student> studentList = univRepository.findStudentListByDeptNameAndGrade(deptName, grade);
		if(studentList.isEmpty()) {
			throw new Exception("학년 or 학과이름 오류");
		} else {
			return studentList;
		}
	}

	@Override
	public List<Student> getStudentByDeptno1OrDeptno2(Integer deptno1, Integer deptno2) throws Exception {
		List<Student> studentList = univRepository.findStudentByDeptno1OrDeptno2(deptno1, deptno2);
		if(studentList.isEmpty()) {
			throw new Exception("학과번호 오류");
		} else {
			return studentList;
		}
	}

	@Override
	public List<Student> getStudentByDeptno1OrDeptno2(Integer deptno) throws Exception {
		List<Student> studentList = univRepository.findStudentByDeptno1OrDeptno2(deptno);
		if(studentList.isEmpty()) {
			throw new Exception("학과번호 오류");
		} else {
			return studentList;
		}
	}

	@Override
	public List<Student> getStudentByDeptName1OrDeptName2(String deptName1, String deptName2) throws Exception {
		List<Student> studentList = univRepository.findStudentByDeptName1OrDeptName2(deptName1, deptName2);
		if(studentList.isEmpty()) {
			throw new Exception("학과이름 오류");
		} else {
			return studentList;
		}
	}

	@Override
	public List<Student> getStudentByDeptName1OrDeptName2(String deptName) throws Exception {
		List<Student> studentList = univRepository.findStudentListByDeptName1OrDeptName2(deptName);
		if(studentList.isEmpty()) {
			throw new Exception("학과이름 오류");
		} else {
			return studentList;
		}
	}
//17
	@Override
	public List<Student> getStudentByProfessorNo(Integer profno) throws Exception {
		List<Student> studentList = studentRepository.findByProfno(profno);
		if(studentList.isEmpty()) {
			throw new Exception("교수번호 오류");
		} else {
			return studentList;
		}
	}

	@Override
	public void enterProfessor(Professor prof) throws Exception {
		// TODO Auto-generated method stub
		Optional<Professor> oProfessor = professorRepository.findById(prof.getProfno());
		if(oProfessor.isEmpty()) {
			professorRepository.save(prof);
		}else {
			throw new Exception("이미 있는 교수번호 입니다.");
		}
	}

	@Override
	public Professor getProfessorByProfno(Integer profno) throws Exception {
		Optional<Professor> oProfessor = professorRepository.findById(profno);
		if(oProfessor.isEmpty()) {
			throw new Exception("교수번호 오류");
		}else {
			return oProfessor.get();
		}
	}

	@Override
	public List<Professor> getProfessorByProfName(String name) throws Exception {
		List<Professor> profList = professorRepository.findByName(name);
		if(profList.isEmpty()) {
			throw new Exception("교수이름 오류");
		}else {
			return profList;
		}
	}
//19
	@Override
	public Map<String, Object> getProfessorByProfnoWithDeptName(Integer profno) throws Exception {
		Tuple tuple = univRepository.findProfessorByProfnoWithDeptName(profno);
		Map<String,Object> res = new HashMap<String, Object>();
		if(tuple.size()==0) {
			throw new Exception("교수 번호 오류");
		} else {
			res.put("profNo", tuple.get(0, Integer.class));
			res.put("deptName", tuple.get(1, String.class));
			return res;
		}
	}
//20
	@Override
	public Professor getProfessorByStudno(Integer studno) throws Exception {
		Professor prof = univRepository.findProfessorByStudno(studno);
		if(prof == null) {
			throw new Exception("학생번호 오류");
		}else {
			return prof;
		}
	}
//21
	@Override
	public List<Professor> getProfessorByDeptno(Integer deptno) throws Exception {
		List<Professor> profList = univRepository.findProfessorByDeptno(deptno);
		if(profList.isEmpty()) {
			throw new Exception("학과번호 오류");
		}else {
			return profList;
		}
	}
//22
	@Override
	public List<Professor> getProfessorByDeptName(String deptName) throws Exception {
		List<Professor> profList = univRepository.findProfessorByDeptName(deptName);
		if(profList.isEmpty()) {
			throw new Exception("학과명 오류");
		}else {
			return profList;
		}
	}
//23
	@Override
	public void foundDepartment(Department dept) throws Exception {
		Optional<Department> Odept = departmentRepository.findById(dept.getDeptno());
		if(Odept.isEmpty()) {
			departmentRepository.save(dept);
		} else {
			throw new Exception("기존에 학과번호가 존재합니다.");
		}
	}
//24
	@Override
	public Department getDepartmentByDeptno(Integer deptno) throws Exception {
		Optional<Department> Odept = departmentRepository.findById(deptno);
		if(Odept.isEmpty()) {
			throw new Exception("학과번호 오류");
		}else {
			return Odept.get();
		}
	}

	@Override
	public Department getDepartmentByDeptName(String dname) throws Exception {
		Optional<Department> Odept = departmentRepository.findByDname(dname);
		if(Odept.isEmpty()) {
			throw new Exception("학과이름 오류");
		}else {
			return Odept.get();
		}
	}

	@Override
	public Department getDepartmentByStudNo(Integer studno) throws Exception {
		Department dept = univRepository.findDepartmentByStudNo(studno);
		if(dept == null) {
			throw new Exception("학생 번호 오류");
		}else {
			return dept;
		}
	}

	@Override
	public List<Department> getDepartmentByBuild(String build) throws Exception {
		List<Department> deptList = univRepository.findDepartmentByBuild(build);
		if(deptList.isEmpty()) {
			throw new Exception("건물 오류");
		}else {
			return deptList;
		}
	}

}
