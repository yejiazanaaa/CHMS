package student.stusys.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import student.stusys.SpringUtil;
import student.stusys.entity.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LTeacher {

    //验证教师的用户信息是否正确
    public boolean checkTeacherInfo(String tea_code, String teacher_pwd) {
        //System.out.println(stu_code + "....." + stu_pwd);
        String sqlTxt = "select * from teacher_info " +
                " where teacher_code='" + tea_code + "' and "
                + " teacher_pwd='" + teacher_pwd + "'";
        //System.out.println(sqlTxt);
        //JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //DbManage db = new DbManage();
        //jdbcTemplate.setDataSource(db.getDataSource());
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        int i = 0;
        for (Map<String, Object> map : list) {
            i++;
        }
        boolean flag = false;
        if (i > 0)
            flag = true;
        return flag;
    }

    //向数据库查询教师信息数据，并返回list格式类型的数据。
    public String queryATeacher(String teacher_code) {
        String name = "用户不存在";
        String sqlTxt =
                "SELECT teacher_name FROM teacher_info WHERE teacher_code='"
                        + teacher_code + "'";
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.
                        getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for (Map<String, Object> map : list) {
            name = map.get("teacher_name").toString();
        }
        return name;
    }

    //处理注册课程信息过程，返回成功或者失败，
    public boolean createCourse(Teacher_Course teacher_course, String teacher_code) {
        boolean flag = true;
        String sqlTxt = "insert into teacher_course_info"
                + "(course_code, teacher_code, course_name, course_class, course_time,course_year) values ("
                + "'" + teacher_course.getCourse_code() + "',"
                + "'" + teacher_code + "',"
                + "'" + teacher_course.getCourse_name() + "',"
                + "'" + teacher_course.getCourse_class() + "',"
                + "'" + teacher_course.getCourse_year() + "',"
                + "'" + teacher_course.getCourse_time() + "')";
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        int i = 0;
        i = jdbcTemplate.update(sqlTxt);
        if (i <= 0)
            flag = false;
        return flag;
    }

    //向数据库查询教师信息数据，并返回list格式类型的数据。
    public List<Teacher> queryTeacher_info(String sqlTxt) {
        List<Teacher> teachers = new ArrayList<Teacher>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for (Map<String, Object> map : list) {
            Teacher teacher_info = new Teacher();
            teacher_info.setTea_name(map.get("teacher_name").toString());
            teacher_info.setTea_code(map.get("teacher_code").toString());
            teachers.add(teacher_info);
        }
        return teachers;
    }

    //向数据库查询教师课程信息数据，并返回list格式类型的数据
    public List<Teacher_Course> queryTeacher_Course(String sqlTxt) {
        List<Teacher_Course> teacher_course = new ArrayList<Teacher_Course>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for (Map<String, Object> map : list) {
            Teacher_Course teacherCourse = new Teacher_Course();
            teacherCourse.setCourse_code(map.get("course_code").toString());
            teacherCourse.setCourse_class(map.get("course_class").toString());
            teacherCourse.setCourse_name(map.get("course_name").toString());
            teacherCourse.setCourse_year(map.get("course_year").toString());
            teacherCourse.setCourse_time(map.get("course_time").toString());
            teacherCourse.setTeacher_code(map.get("teacher_code").toString());
            teacherCourse.setTeacher_name(map.get("teacher_name").toString());
            teacher_course.add(teacherCourse);
        }
        return teacher_course;
    }

    //向数据库查询课程学生信息数据，并返回list格式类型的数据
    public List<Student> queryStudents(String sqlTxt) {
        List<Student> course_student = new ArrayList<Student>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for (Map<String, Object> map : list) {
            Student courseStudent = new Student();
            courseStudent.setStu_code(map.get("stu_code").toString());
            courseStudent.setStu_name(map.get("stu_name").toString());
            courseStudent.setStu_pro(map.get("stu_pro").toString());
            courseStudent.setStu_class(map.get("stu_class").toString());
            course_student.add(courseStudent);
        }
        return course_student;
    }

    public List<Student_Course> queryStudent_Course(String sqlTxt) {
        List<Student_Course> student_course = new ArrayList<Student_Course>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for (Map<String, Object> map : list) {
            Student_Course studentCourse = new Student_Course();
            studentCourse.setStu_course_code(map.get("stu_course_code").toString());
            studentCourse.setStu_code(map.get("stu_code").toString());
            //studentCourse.setCourse_code(map.get("course_code").toString());
            studentCourse.setStu_name(map.get("stu_name").toString());
            studentCourse.setStu_pro(map.get("stu_pro").toString());
            studentCourse.setStu_class(map.get("stu_class").toString());
            studentCourse.setQm_grade(map.get("qm_grade").toString());

            student_course.add(studentCourse);
        }
        return student_course;
    }

    //向数据库查询课程信息数据，并返回list格式类型的数据
    public List<Teacher_Course> queryCourseInfo(String sqlTxt) {
        List<Teacher_Course> teacher_course = new ArrayList<Teacher_Course>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for (Map<String, Object> map : list) {
            Teacher_Course teacherCourse = new Teacher_Course();
            teacherCourse.setCourse_class(map.get("course_class").toString());
            teacherCourse.setCourse_name(map.get("course_name").toString());
            teacherCourse.setCourse_year(map.get("course_year").toString());
            teacherCourse.setCourse_time(map.get("course_time").toString());

            teacher_course.add(teacherCourse);
        }
        return teacher_course;
    }
    //更新成绩
    public void updateStudentGrade(String[] sqlTxts){
        JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        for (int i = 0; i < sqlTxts.length; i++) {
            jdbcTemplate.update(sqlTxts[i]);
        }
    }
    //往课程里插入不存在的学生
    public boolean addCourseStudent(String course_code,String stu_code){
        boolean flag = true;
        String sqlTxt = "insert into course_student_info"
                + "(course_code, stu_code) values ("
                + "'" + course_code + "',"
                + "'" + stu_code + "')";
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        int i = 0;
        i = jdbcTemplate.update(sqlTxt);
        if (i <= 0)
            flag = false;
        return flag;
    }



    //向数据库查询课程作业信息数据，并返回list格式类型的数据
    public List<Course_Homework> queryCourse_homework(String sqlTxt){
        List<Course_Homework> course_homework = new ArrayList<Course_Homework>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String, Object> map : list){
            Course_Homework courseHomework = new Course_Homework();
            courseHomework.setCourse_code(map.get("homework_course_code").toString());
            courseHomework.setHomework_code(map.get("homework_code").toString());
            courseHomework.setHomework_date(map.get("homework_date").toString());
            courseHomework.setHomework_title(map.get("homework_title").toString());

            course_homework.add(courseHomework);
        }
        return  course_homework;
    }
    //处理注册作业信息过程，返回成功或者失败，
    public boolean createHomework(Course_Homework homework,String course_code){
        boolean flag = true;
        String sqlTxt = "insert into course_homework"
                + "(homework_course_code, homework_date, homework_title) values ("
                + "'" + course_code + "',"
                + "'" + homework.getHomework_date()+ "',"
                + "'" + homework.getHomework_title() + "')";
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        int i = 0;
        i = jdbcTemplate.update(sqlTxt);
        if (i <= 0)
            flag = false;
        return flag;
    }

    public List<Homework_Student> queryStudent_Homework(String sqlTxt){
        List<Homework_Student> homework_studentList = new ArrayList<Homework_Student>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String, Object> map : list){
            Homework_Student hw_students = new Homework_Student();
            hw_students.setHomework_txt(map.get("homework_txt").toString());
            hw_students.setHomework_stu_code(map.get("homework_stu_code").toString());
            hw_students.setHomework_filename(map.get("homework_filename").toString());
            hw_students.setStu_code(map.get("stu_code").toString());
            hw_students.setHomework_grade(map.get("homework_grade").toString());
            //studentCourse.setCourse_code(map.get("course_code").toString());
            hw_students.setStu_name(map.get("stu_name").toString());
            hw_students.setStu_pro(map.get("stu_pro").toString());
            hw_students.setStu_class(map.get("stu_class").toString());
            //System.out.println( hw_students.getHw_txt());
            homework_studentList.add(hw_students);
        }
        return  homework_studentList;
    }
    public Course_Homework queryAHomework(String homework_code) {
        String name = "";
        Course_Homework a=new Course_Homework();
        String sqlTxt =
                "SELECT homework_title,homework_date FROM homework_info WHERE homework_code='"
                        + homework_code + "'";
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.
                        getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String, Object> map : list){
            a.setHomework_title(map.get("homework_title").toString());
            a.setHomework_date(map.get("homework_date").toString());
            a.setHomework_code(homework_code);
        }
        return  a;
    }

    //将作业发送到学生端
    public boolean send_homework_to_stu(String homework_code,String course_code){
        boolean flag = true;
        String sqlTxt = "insert ignore into homework_student"
                + " (stu_code,homework_code) SELECT stu_code,"
                +"'"+homework_code+ "'"
                +" FROM student_course_info WHERE"
                +" course_code ="
                // + "'" + course_code + "',"
                + "'" + course_code + "'";
        // INSERT INTO homework_student (hw_stu_code,hw_code)
        // SELECT stu_code,'1800003'
        // FROM student_course_info WHERE course_code = '(2018-2019-1)-J0905380-40275-4';
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        int i = 0;
        i = jdbcTemplate.update(sqlTxt);
        if (i <= 0)
            flag = false;
        return flag;
    }

}

