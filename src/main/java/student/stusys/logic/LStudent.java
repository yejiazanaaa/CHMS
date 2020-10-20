package student.stusys.logic;

import org.springframework.jdbc.core.JdbcTemplate;
import student.stusys.SpringUtil;
import student.stusys.entity.Homework_Student;
import student.stusys.entity.Student;
import student.stusys.entity.Student_Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LStudent {

    //验证学生的用户信息是否正确
    public boolean checkStudentInfo(String stu_code, String stu_pwd){
        //System.out.println(stu_code + "....." + stu_pwd);
        String sqlTxt = "select * from patient " +
                " where usercode='" + stu_code + "' and "
                + " userpwd='" + stu_pwd + "'";
        //System.out.println(sqlTxt);
        //JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //DbManage db = new DbManage();
        //jdbcTemplate.setDataSource(db.getDataSource());
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        int i = 0;
        for(Map<String, Object> map : list){
            i++;
        }
        boolean flag = false;
        if (i > 0)
            flag = true;
        return flag;
    }

    //向数据库查询学生信息数据，并返回list格式类型的数据。
    public String queryAStudent(String stu_code) {
        String name = "用户不存在";
        String sqlTxt =
                "SELECT stu_name FROM student_info WHERE stu_code='"
                        + stu_code + "'";
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.
                        getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String, Object> map : list){
            name = map.get("stu_name").toString();
        }
        return  name;
    }

    public Student queryAStudentInfo(String stu_code) {
        Student stu=new Student();
        String sqlTxt =
                "SELECT * FROM student_info WHERE stu_code='"
                        + stu_code + "'";
        JdbcTemplate jdbcTemplate =
                (JdbcTemplate) SpringUtil.applicationContext.
                        getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String, Object> map : list){
            stu.setStu_name(map.get("stu_name").toString());
            stu.setStu_pro(map.get("stu_pro").toString());
            stu.setStu_class(map.get("stu_class").toString());
        }
        return  stu;
    }
    public List<Student_Course> queryTeacher_Course(String sqlTxt) {
        List<Student_Course> student_courses = new ArrayList<Student_Course>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String, Object> map : list){
            Student_Course stu_course = new Student_Course();
            stu_course.setCourse_code(map.get("course_code").toString());
            stu_course.setCourse_name(map.get("course_name").toString());
            stu_course.setCourse_time(map.get("course_time").toString());
            stu_course.setCourse_class(map.get("course_class").toString());
            stu_course.setCourse_year(map.get("course_year").toString());
            student_courses.add( stu_course);
        }
        return  student_courses;
    }
    public List<Homework_Student> queryStudent_Course_Homework(String sqlTxt) {
        List<Homework_Student> student_course_homework = new ArrayList<Homework_Student>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String, Object> map : list){
            Homework_Student stu_course_homework = new Homework_Student();
            //stu_course_hw.setHw_code(map.get("hw_code").toString());
            stu_course_homework.setHomework_name(map.get("homework_title").toString());
            stu_course_homework.setHomework_date(map.get("homework_date").toString());
            stu_course_homework.setHomework_stu_code(map.get("homework_stu_code").toString());
            stu_course_homework.setHomework_txt(map.get("homework_date").toString());
            student_course_homework.add(stu_course_homework);
        }
        return  student_course_homework;
    }
    public void updateHomeworkUrl(String sqlTxt){
        JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.update(sqlTxt);

    }
}
