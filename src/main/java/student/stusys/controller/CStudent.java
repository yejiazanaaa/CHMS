package student.stusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import student.stusys.entity.Homework_Student;
import student.stusys.entity.Student;
import student.stusys.entity.Student_Course;
import student.stusys.logic.LStudent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CStudent{


    //显示学生登录页面的请求，thymeleaf数据绑定的方式
    @RequestMapping("/login_student")
    public String showLogin_Thymeleaf(Model model){
        Student student = new Student();
            model.addAttribute("student", student);
        return "login_student";
    }

    //请求跳转到教师页面
    @RequestMapping("/goto_login_teacher")
    public String showGoto(Model model){
        return "redirect:login_teacher";
    }

    //得到学生姓名
    @RequestMapping("/get_student_name")
    public @ResponseBody
    String getStudentName(HttpServletRequest request){
        String stu_code = request.getParameter("stu_code").toString();
        LStudent student = new LStudent();
        String name = student.queryAStudent(stu_code);
        return name;
    }

    //thymeleaf结合数据绑定的方式，接受登录页面的请求处理用户名，密码验证
    @RequestMapping(value="check_student_info_thymeleaf", method= RequestMethod.GET)
    public String checkStudent_Thymeleaf(@ModelAttribute Student student, HttpSession httpSession){
        LStudent stu = new LStudent();
        if (stu.checkStudentInfo(student.getStu_code(), student.getStu_pwd())) {
            httpSession.setAttribute("stu_code", student.getStu_code());
            System.out.println("1`");
            return "redirect:to_main_student";

        }
        else
            return "login_student";
    }
    //显示学生端主页面
    @RequestMapping(value="to_main_student", method= RequestMethod.GET)
    public String to_student_main(Model model, HttpSession httpSession){
        String stu_code=httpSession.getAttribute("stu_code").toString();
        httpSession.setAttribute("stu_code",stu_code);
        LStudent StudentInfo = new LStudent();
        Student Student_info =  StudentInfo.queryAStudentInfo(stu_code);
        //teacher_courseList把数据加入到model里面

        model.addAttribute("student_info", Student_info);
        System.out.println(Student_info.getStu_name());
        return "main_student";
    }
         //显示学生端课程
        @RequestMapping(value = "query_student_course", method = RequestMethod.GET)
        public String query_student_Course(Model model, HttpSession session){
            String stu_code = session.getAttribute("stu_code").toString();
            String sqlTxt = "select * from course_student_info tc INNER JOIN teacher_course_info t ON tc.course_code = t.course_code ";
            sqlTxt = sqlTxt + " where tc.stu_code ='" + stu_code + "'";
            System.out.println(sqlTxt);
            LStudent student = new LStudent();
            List<Student_Course> student_courseList = student.queryTeacher_Course(sqlTxt);

            //teacher_courseList把数据加入到model里面
            model.addAttribute("student_courseList", student_courseList);
            //默认用teacher_courseList集合的第一条记录去取学生信息。
           /* sqlTxt = "select teacher_name,teacher_code from teacher_info ";
            sqlTxt = sqlTxt + " where teacher_code = '" + teacher_code + "'";
            System.out.println(sqlTxt);
            List<Teacher> teachersList =
                    teacher.queryTeacher_info(sqlTxt);
            //student_courseList把数据加入到model里面
            model.addAttribute("teachersList", teachersList);*/

            return "query_student_course";//要渲染返回的页面/////////query_teacher_course
        }
        @RequestMapping(value = "show_student_course_homework", method = RequestMethod.GET)
        public String show_student_course_homework(Model model, HttpSession session,HttpServletRequest request){
            String stu_code = session.getAttribute("stu_code").toString();
            String course_code=request.getParameter("course_code").toString();
            String sqlTxt = "select * from homework_student tc INNER JOIN course_homework t ON tc.homework_code = t.homework_code ";
            sqlTxt = sqlTxt + " where tc.stu_code ='" + stu_code + "' and homework_course_code='"+course_code+"'" ;
            System.out.println(sqlTxt);
            LStudent student = new LStudent();
            List<Homework_Student> student_course_homeworkList = student.queryStudent_Course_Homework(sqlTxt);
            //teacher_courseList把数据加入到model里面
            model.addAttribute("student_course_homeworkList", student_course_homeworkList);
            //默认用teacher_courseList集合的第一条记录去取学生信息。
           /* sqlTxt = "select teacher_name,teacher_code from teacher_info ";
            sqlTxt = sqlTxt + " where teacher_code = '" + teacher_code + "'";
            System.out.println(sqlTxt);
            List<Teacher> teachersList =
                    teacher.queryTeacher_info(sqlTxt);
            //student_courseList把数据加入到model里面
            model.addAttribute("teachersList", teachersList);*/

            return "show_student_course_homework";//要渲染返回的页面/////////query_teacher_course

        }
    //学生做作业界面
    @RequestMapping(value = "show_student_do_homework", method = RequestMethod.GET)
    public String show_student_do_homework(Model model, HttpSession session,HttpServletRequest request) {
        String stu_code = session.getAttribute("stu_code").toString();
        String homework_stu_code = request.getParameter("homework_stu_code").toString();
//        String homework_date = request.getParameter("homework_date").toString();
        session.setAttribute("homework_stu_code",homework_stu_code);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = simpleDateFormat.format(date);
      //  System.out.println(stu_code + homework_date + homework_stu_code + currentTime);
       // if (homework_date.compareTo(currentTime) > 0)
        /************/
        System.out.println("you re in time");
            String sqlTxt = "select * from homework_student tc INNER JOIN homework_info t ON tc.homework_code = t.homework_code ";
            sqlTxt = sqlTxt + " where tc.homework_stu_code='"+homework_stu_code+"'" ;
            System.out.println(sqlTxt);
            LStudent student = new LStudent();
            List<Homework_Student> student_course_homeworkList = student.queryStudent_Course_Homework(sqlTxt);
            //teacher_courseList把数据加入到model里面
            model.addAttribute("student_course_homeworkList", student_course_homeworkList);
            //默认用teacher_courseList集合的第一条记录去取学生信息。
       /* sqlTxt = "select teacher_name,teacher_code from teacher_info ";
        sqlTxt = sqlTxt + " where teacher_code = '" + teacher_code + "'";
        System.out.println(sqlTxt);
        List<Teacher> teachersList =
                teacher.queryTeacher_info(sqlTxt);
        //student_courseList把数据加入到model里面
        model.addAttribute("teachersList", teachersList);*/
            return "student_do_homework_page";//要渲染返回的页面/////////query_teacher_course

    }
}
