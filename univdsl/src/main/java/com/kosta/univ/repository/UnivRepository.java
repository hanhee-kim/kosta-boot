package com.kosta.univ.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.QDepartment;
import com.kosta.univ.entity.QProfessor;
import com.kosta.univ.entity.QStudent;
import com.kosta.univ.entity.Student;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UnivRepository {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	//3
	public Tuple findStudentNoWithDeptNameByStudno(Integer studno)throws Exception {
		QStudent student = QStudent.student;
		QDepartment dept = QDepartment.department;
		
		Tuple tuple = jpaQueryFactory.select(student.studno,dept.dname)
		.from(student)
		.join(dept)
		.on(student.deptno1.eq(dept.deptno)
//				.or(student.deptno2.eq(dept.deptno))
				)
		.where(student.studno.eq(studno))
		.fetchOne();
		return tuple;
	}
	//4
	public Tuple findStudentByNoWithProfName(Integer studno) throws Exception{
		QStudent student = QStudent.student;
		QProfessor prof = QProfessor.professor;
		
		Tuple tuple = jpaQueryFactory.select(student.studno,prof.name)
				.from(student)
				.join(prof)
				.on(student.profno.eq(prof.profno))
				.where(student.studno.eq(studno))
				.fetchOne();
		return tuple;
	}
	//5
	public Tuple findStudentByNoWithDeptNameAndProfName(Integer studno) throws Exception{
		QStudent stud = QStudent.student;
		QDepartment dept = QDepartment.department;
		QProfessor prof = QProfessor.professor;
		Tuple tuple = jpaQueryFactory
				.select(stud.studno,dept.dname,prof.name)
				.from(dept)
				.join(prof)
				.on(dept.deptno.eq(prof.deptno))
				.join(stud)
				.on(prof.profno.eq(stud.profno))
				.where(stud.studno.eq(studno))
				.fetchOne();
		return tuple;
	}
	//7
	public List<Student> findStudentByDeptno(Integer deptno) throws Exception{
		QStudent stud = QStudent.student;
		List<Student> stuList = jpaQueryFactory
		.selectFrom(stud).distinct()
		.where(
				stud.deptno1.eq(deptno)
				.or(
						stud.deptno2.eq(deptno)
						)
				)
		.fetch();
		return stuList;
	}
	//8
	public List<Student> findStudentByDeptName(String deptName) throws Exception {
		QStudent stud = QStudent.student;
		QDepartment dept = QDepartment.department;
		List<Student> stuList = jpaQueryFactory
				.selectFrom(stud)
				.join(dept)
				.on(stud.deptno1.eq(dept.deptno).or(stud.deptno2.eq(dept.deptno)))
				.where(dept.dname.eq(deptName))
				.fetch();
		return stuList;
	}
	
	public List<Student> findStudentListByDeptNameAndGrade(String deptName,Integer grade)throws Exception{
		QStudent stud = QStudent.student;
		QDepartment dept = QDepartment.department;
		List<Student> stuList = jpaQueryFactory
				.selectFrom(stud)
				.join(dept)
				.on(stud.deptno1.eq(dept.deptno))
				.where(stud.grade.eq(grade).and(dept.dname.eq(deptName)))
				.fetch();
		return stuList;
	}
	
	public List<Student> findStudentByDeptno1OrDeptno2(Integer deptno1,Integer deptno2)throws Exception{
		QStudent stud = QStudent.student;
		List<Student> stuList = jpaQueryFactory
				.selectFrom(stud)
				.where(stud.deptno1.eq(deptno1).or(stud.deptno2.eq(deptno2)))
				.fetch();
		return stuList;
	}
	
	public List<Student> findStudentByDeptno1OrDeptno2(Integer deptno)throws Exception{
		QStudent stud = QStudent.student;
		List<Student> stuList = jpaQueryFactory
				.selectFrom(stud)
				.where(stud.deptno1.eq(deptno).or(stud.deptno2.eq(deptno)))
				.fetch();
		return stuList;
	}
//	//13.주전공이름이 deptName1이거나 부전공이 deptName2인 학생 조회
	public List<Student> findStudentByDeptName1OrDeptName2(String deptName1,String deptName2)throws Exception{
		QDepartment dept = QDepartment.department;
		QStudent stud = QStudent.student;
		List<Student> stuList = jpaQueryFactory
				.selectFrom(stud).distinct()
				.join(dept)
				.on(stud.deptno1.eq(dept.deptno).or(stud.deptno2.eq(dept.deptno)))
				.where(
						stud.deptno1.eq(dept.deptno).and(dept.dname.eq(deptName1))
						.or(
							stud.deptno2.eq(dept.deptno).and(dept.dname.eq(deptName2))
								)
						)
				.fetch();
		return stuList;
	}
	//강사님 코드
//	public List<Student> findStudentListByDeptName1OrDeptName2(String deptName1,String deptName2) {
//		QStudent student = QStudent.student;
//		QDepartment department1 = new QDepartment("department1");
//		QDepartment department2 = new QDepartment("department2");
//		return jpaQueryFactory.selectFrom(student)
//				.join(department1)
//				.on(student.deptno1.eq(department1.deptno))
//				.leftJoin(department2)
//				.on(student.deptno2.eq(department2.deptno))
//				.where(department1.dname.eq(deptName1).or(department2.dname.eq(deptName2)))
//				.fetch();
//	}


//	//14.주전공이나 부전공이 특정학과이름인 학생 조회
//	List<Student> get;
	//강사님 코드
	public List<Student> findStudentListByDeptName1OrDeptName2(String deptName) {
		QStudent student = QStudent.student;
		QDepartment department1 = new QDepartment("department1");
		QDepartment department2 = new QDepartment("department2");
		return jpaQueryFactory.selectFrom(student)
				.join(department1)
				.on(student.deptno1.eq(department1.deptno))//dept1조인
				.leftJoin(department2)
				.on(student.deptno2.eq(department2.deptno))//dept2조인
				.where(
						department1.dname.eq(deptName)//dept1에서 찾고
						.or(department2.dname.eq(deptName))//dept2에서 찾고
						)
				.fetch();
	}
//	//19.교수번호로 교수정보 조회(학과명 포함)
	public Tuple findProfessorByProfnoWithDeptName(Integer profno) throws Exception{
		QDepartment dept = QDepartment.department;
		QProfessor prof = QProfessor.professor;
		Tuple tuple = jpaQueryFactory
				.select(prof.profno,dept.dname)
				.from(prof)
				.join(dept)
				.on(prof.deptno.eq(dept.deptno))
				.where(prof.profno.eq(profno))
				.fetchOne();
		return tuple;
	}
//	//20.학생의 담당교수 조회
//	Professor getProfessorByStudno(Integer studno) throws Exception;
	public Professor findProfessorByStudno(Integer studno) throws Exception{
		QStudent stud = QStudent.student;
		QProfessor prof = QProfessor.professor;
		Professor professor = jpaQueryFactory
				.select(prof)
				.from(prof)
				.join(stud)
				.on(prof.profno.eq(stud.profno))
				.where(stud.studno.eq(studno))
				.fetchOne();
		return professor;
	}
//	//21.특정 학과 교수 정보 조회(학과번호로)
//	;
	public List<Professor> findProfessorByDeptno(Integer deptno) throws Exception{
		QDepartment dept = QDepartment.department;
		QProfessor prof = QProfessor.professor;
		List<Professor> profList = jpaQueryFactory
				.selectFrom(prof)
				.join(dept)
				.on(prof.deptno.eq(dept.deptno))
				.where(dept.deptno.eq(deptno))
				.fetch();
		return profList;
	}
//	//22.특정학과 교수 정보 조회(학과명으로)
//	;
	public List<Professor> findProfessorByDeptName(String deptName) throws Exception{
		QDepartment dept = QDepartment.department;
		QProfessor prof = QProfessor.professor;
		List<Professor> profList = jpaQueryFactory
				.selectFrom(prof)
				.join(dept)
				.on(prof.deptno.eq(dept.deptno))
				.where(dept.dname.eq(deptName))
				.fetch();
		return profList;
		
	}

//	//26.학번으로 학과 정보 조회
//	;
	public Department findDepartmentByStudNo(Integer studno) throws Exception{
		QDepartment dept = QDepartment.department;
		QStudent stud = QStudent.student;
		Department department = jpaQueryFactory
				.select(dept)
				.from(dept)
				.join(stud)
				.on(stud.deptno1.eq(dept.deptno).or(stud.deptno2.eq(dept.deptno)))
				.where(stud.studno.eq(studno))
				.fetchOne();
		return department;
	}
//	//27.건물로 학과 조회
//	;
	public List<Department> findDepartmentByBuild(String build) throws Exception{
		QDepartment dept = QDepartment.department;
		List<Department> deptList = jpaQueryFactory
				.selectFrom(dept)
				.where(dept.build.eq(build))
				.fetch();
		return deptList;
	}
//
}
	