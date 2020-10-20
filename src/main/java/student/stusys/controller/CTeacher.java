package student.stusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import student.stusys.entity.*;
import student.stusys.logic.LStudent;
import student.stusys.logic.LTeacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CTeacher {

    //显示学生登录页面的请求，thymeleaf数据绑定的方式
    @RequestMapping("/login_teacher")
    public String showLogin_Thymeleaf(Model model){
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "login_teacher";
    }

    //请求跳转到学生页面
    @RequestMapping("/goto_login_student")
    public String showGoto(Model model){
        return "redirect:login_student";
    }


    //得到教师姓名
    @RequestMapping("/get_teacher_name")
    public @ResponseBody
    String getStudentName(HttpServletRequest request){
        String teacher_code = request.getParameter("tea_code").toString();
        LTeacher teacher = new LTeacher();
        String name = teacher.queryATeacher(teacher_code);
        return name;
    }
    //thymeleaf结合数据绑定的方式，接受登录页面的请求处理用户名，密码验证
    @RequestMapping(value="check_teacher_info_thymeleaf", method= RequestMethod.GET)
    public String checkTeacher_Thymeleaf(@ModelAttribute Teacher teacher, HttpSession httpSession){
        LTeacher tea = new LTeacher();
        if (tea.checkTeacherInfo(teacher.getTea_code(), teacher.getTea_pwd())) {
            httpSession.setAttribute("teacher_code", teacher.getTea_code());
            return "redirect:query_teacher_course";
        }
        else
            return "login_teacher";
    }
    //显示教师主页面
    @RequestMapping(value = "query_teacher_course", method = RequestMethod.GET)
    public String query_Teacher_Course(Model model, HttpSession session){
        String teacher_code = session.getAttribute("teacher_code").toString();
        String sqlTxt = "select * from teacher_course_info tc INNER JOIN teacher_info t ON tc.teacher_code = t.teacher_code ";
        sqlTxt = sqlTxt + " where tc.teacher_code ='" + teacher_code + "'";
        System.out.println(sqlTxt);
        LTeacher teacher = new LTeacher();
        List<Teacher_Course> teacher_courseList = teacher.queryTeacher_Course(sqlTxt);
        //teacher_courseList把数据加入到model里面
        model.addAttribute("teacher_courseList", teacher_courseList);
        //默认用teacher_courseList集合的第一条记录去取教师信息。
        sqlTxt = "select teacher_name,teacher_code from teacher_info";
        sqlTxt = sqlTxt + " where teacher_code = '" + teacher_code + "'";
        System.out.println(sqlTxt);
        List<Teacher> teachersList =
                teacher.queryTeacher_info(sqlTxt);
        //student_courseList把数据加入到model里面
        model.addAttribute("teachersList", teachersList);
        return "query_teacher_course";//要渲染返回的页面
    }
    //显示创建教师课程页面
    @RequestMapping(value = "show_create_teacher_course_page", method = RequestMethod.GET)
    public String show_create_teacher_course_page(Model model, HttpSession session){
        String teacher_code = session.getAttribute("teacher_code").toString();
        LTeacher teacher = new LTeacher();
        String sqlTxt= "select teacher_name,teacher_code from teacher_info ";
        sqlTxt = sqlTxt + " where teacher_code = '" + teacher_code + "'";
        System.out.println(sqlTxt);
        List<Teacher> teachersList =
                teacher.queryTeacher_info(sqlTxt);
        model.addAttribute("teachersList", teachersList);
        return "create_teacher_course";
    }
    //创建注册课程并插入数据库的过程
    @RequestMapping(value = "do_create_teacher_course", method = RequestMethod.GET)
    public String registerCourse_Thymeleaf(@ModelAttribute Teacher_Course teacher_course, Model model, HttpSession session){
        String teacher_code = session.getAttribute("teacher_code").toString();
        boolean flag = true;
        LTeacher teacher_courses = new  LTeacher();
        flag = teacher_courses.createCourse(teacher_course,teacher_code);
        if (flag)
            return "redirect:query_teacher_course";
        else
            return  "error";
    }
    //请求显示该课程的学生信息页面
    @RequestMapping(value = "show_course_student", method = RequestMethod.GET)
    public String show_course_student(HttpServletRequest request,HttpSession httpSession){
        String course_code = request.getParameter("course_code").toString();
        httpSession.setAttribute("course_code",course_code);

        return "redirect:teacher_query_course_student_info";
    }
    /*@RequestMapping(value = "query_teacher_course_student", method = RequestMethod.GET)
    public String query_teacher_course_student_session(Model model, HttpServletRequest request, HttpSession session){
        String course_code = session.getAttribute("course_code").toString();
        System.out.println(course_code);
        boolean flag = true;
        String sqlTxt = "select sc.stu_course_code, sc.stu_code, sc.qm_grade, s.stu_name, s.stu_class, s.stu_pro from course_student_info sc inner join student_info s on sc.stu_code=s.stu_code  ";
        sqlTxt = sqlTxt + " where sc.course_code = '" + course_code + "'";
        System.out.println(sqlTxt);
        LTeacher course_student = new LTeacher();
        List<Student_Course> student_courseList =
                course_student.queryStudent_Course(sqlTxt);
        //student_courseList把数据加入到model里面
        model.addAttribute("student_courseList", student_courseList);

        String sqlTxt2 = "select * from teacher_course_info ";
        sqlTxt2 = sqlTxt2 + " where course_code ='" + course_code + "'";
        System.out.println(sqlTxt2);
        LTeacher teacher_course = new LTeacher();
        List<Teacher_Course> teacher_courseList =  teacher_course.queryCourseInfo(sqlTxt2);
        //teacher_courseList把数据加入到model里面
        model.addAttribute("teacher_courseList", teacher_courseList);

        return "query_course_student";
    }*/

    //请求没有选择该课的学生的页面
    @RequestMapping(value = "query_course_student_notExist")
    public String query_course_student_notExist(Model model, HttpSession session){
        String course_code = session.getAttribute("course_code").toString();
        System.out.println(course_code+"query noExist");
        //查询得到属于该老师的课程代码和课程名
        String sqlTxt = "SELECT * FROM student_info" +
                " WHERE  stu_code  NOT  IN  ( SELECT stu_code FROM course_student_info WHERE course_code = '"
                +course_code+"' "+
                " )";
        System.out.println(sqlTxt);
        LTeacher teacher = new LTeacher();
        //默认用teacher_courseList集合的第一条记录去取学生信息。

        //sqlTxt = sqlTxt + " where sc.course_code = '" + course_code + "'";
        List<Student> course_studentList = teacher.queryStudents(sqlTxt);
        //student_courseList把数据加入到model里面
        model.addAttribute("student_courseList", course_studentList);
        return "query_course_student_notExist";//要渲染返回的页面
    }
    //保存期末课程成绩
    @RequestMapping(value = "input_course_grade", method = RequestMethod.POST)
    public String input_Course_Grade(HttpServletRequest request){
        String[] grades = request.getParameterValues("grade");
        String[] stu_course_codes = request.getParameterValues("stu_course_code");
        String[] sqlTxts = new String[grades.length];
        for(int i = 0; i < grades.length; i++) {
            //    System.out.println(stu_course_codes[i] + "----" + grades[i]);
            if(grades[i].equals(""))
                sqlTxts[i] = "update course_student_info set qm_grade = '' where stu_course_code = '" + stu_course_codes[i] + "'";
            else
                sqlTxts[i] = "update course_student_info set qm_grade = "
                        + grades[i] + " where stu_course_code = '" + stu_course_codes[i] + "'";
            System.out.println(sqlTxts[i]);
        }
        LTeacher teacher = new LTeacher();
        teacher.updateStudentGrade(sqlTxts);
        return "redirect:/teacher_query_course_student";
    }
//保存课程作业成绩
    @RequestMapping(value = "input_homework_grade", method = RequestMethod.POST)
    public String input_homework_grade(HttpServletRequest request,HttpSession session){
        String course_code = session.getAttribute("course_code").toString();
        String[] grades = request.getParameterValues("grade");
        String[] homework_stu_codes = request.getParameterValues("homework_stu_code");
        String[] sqlTxts = new String[grades.length];
        for(int i = 0; i < grades.length; i++) {
            //System.out.println(homework_stu_codes[i] + "----" + grades[i]);
            if(grades[i].equals(""))
                sqlTxts[i] = "update homework_student set homework_grade = '' where homework_stu_code = '" +homework_stu_codes[i] + "'";
            else
                sqlTxts[i] = "update homework_student set homework_grade = "
                        + grades[i] + " where homework_stu_code = '" +homework_stu_codes[i] + "'";
            System.out.println(sqlTxts[i]);
        }
        LTeacher teacher = new LTeacher();
        teacher.updateStudentGrade(sqlTxts);
        return "redirect:query_homework_student_session";
    }

    //让不存在的学生添加到该课程中
    @RequestMapping(value = "do_add_course_student", method = RequestMethod.GET)
    public String show_add_course_student_page(Model model, HttpSession session,HttpServletRequest request){
        String course_code = session.getAttribute("course_code").toString();
        String stu_code = request.getParameter("stu_code").toString();
        boolean flag = true;
        System.out.println(course_code);
        LTeacher teacher_add_student = new  LTeacher();
        flag = teacher_add_student.addCourseStudent(course_code,stu_code);
        return "redirect:query_course_student_notExist";
    }

    //请求跳转到课程学生页面
    @RequestMapping("/goto_query_course_student")
    public String showGotoPrev(Model model){
        return "redirect:query_course_student";
    }

    //请求显示课程作业页面
    @RequestMapping(value = "show_course_homework", method = RequestMethod.GET)
    public String show_course_homework(HttpServletRequest request,HttpSession httpSession){
        String course_code = request.getParameter("course_code").toString();
        httpSession.setAttribute("course_code",course_code);
        System.out.println(course_code);
        return "redirect:query_course_homework";
    }
    @RequestMapping(value = "query_course_homework", method = RequestMethod.GET)
    public String query_course_homework(Model model, HttpServletRequest request, HttpSession session){
        String course_code = session.getAttribute("course_code").toString();
        System.out.println(course_code);
        boolean flag = true;
        String sqlTxt = "select * from course_homework ";
        sqlTxt = sqlTxt + " where homework_course_code = '" + course_code + "'";
        System.out.println(sqlTxt);
        LTeacher course_homework = new LTeacher();
        List<Course_Homework> course_homeworkList =
                course_homework.queryCourse_homework(sqlTxt);
        //student_courseList把数据加入到model里面
        model.addAttribute("course_homeworkList", course_homeworkList);

        String sqlTxt2 = "select * from teacher_course_info ";
        sqlTxt2 = sqlTxt2 + " where course_code ='" + course_code + "'";
        System.out.println(sqlTxt2);
        LTeacher teacher_course = new LTeacher();
        List<Teacher_Course> teacher_courseList =  teacher_course.queryCourseInfo(sqlTxt2);
        //teacher_courseList把数据加入到model里面
        model.addAttribute("teacher_courseList", teacher_courseList);

        return "query_course_homework";
    }
//请求显示新建作业页面
@RequestMapping(value = "show_create_homework_page", method = RequestMethod.GET)
public String show_create_homework_page(Model model, HttpSession session){
    String course_code = session.getAttribute("course_code").toString();
    System.out.println(course_code);
    return "create_course_homework";
}

    //创建注册作业并插入数据库的过程
    @RequestMapping(value = "do_create_course_homework", method = RequestMethod.GET)
    public String do_create_course_homework(@ModelAttribute Course_Homework homework,Model model, HttpSession session){
        String course_code = session.getAttribute("course_code").toString();
        boolean flag = true;
        LTeacher teacher_homework = new  LTeacher();
        flag = teacher_homework.createHomework(homework,course_code);
        if (flag)
            return "redirect:query_course_homework";
        else
            return  "error";
    }
    //请求显示某个作业的学生页面
    @RequestMapping(value = "show_homework_student", method = RequestMethod.GET)
    public String show_homework_student(HttpServletRequest request,HttpSession httpSession){
        String homework_code = request.getParameter("homework_code").toString();
        httpSession.setAttribute("homework_code",homework_code);

        return "redirect:query_homework_student_session";
    }

    @RequestMapping(value = "query_homework_student_session", method = RequestMethod.GET)
    public String query_homework_student_session(Model model, HttpServletRequest request, HttpSession session){
        String homework_code = session.getAttribute("homework_code").toString();
        System.out.println(homework_code);
        boolean flag = true;
        String sqlTxt = "select sc.homework_filename,sc.homework_txt,sc.homework_stu_code,sc.stu_code, sc.homework_grade, s.stu_name, s.stu_class, s.stu_pro from homework_student sc inner join student_info s on sc.stu_code=s.stu_code  ";
        sqlTxt = sqlTxt + " where sc.homework_code = '" + homework_code + "'";
        System.out.println(sqlTxt);
        LTeacher homework_student = new LTeacher();
        List<Homework_Student> homework_studentList =
                homework_student.queryStudent_Homework(sqlTxt);
        //student_courseList把数据加入到model里面
        model.addAttribute("homework_studentList", homework_studentList);

        String sqlTxt2 = "select * from course_homework ";
        sqlTxt2 = sqlTxt2 + " where homework_code ='" + homework_code + "'";
        System.out.println(sqlTxt2);
        LTeacher teacher_course = new LTeacher();
        Course_Homework homeworkInfo =  teacher_course.queryAHomework(homework_code);
        //teacher_courseList把数据加入到model里面
        System.out.println(homeworkInfo.getHomework_title());
        model.addAttribute("homework_info", homeworkInfo);

        return "query_homework_student";
    }
    //请求将作业同步到学生端
    @RequestMapping(value = "send_course_homework_toStu", method = RequestMethod.GET)
    public String send_course_homework_toStu(Model model, HttpSession session,HttpServletRequest request){
        String homework_code = request.getParameter("homework_code").toString();
        String course_code=session.getAttribute("course_code").toString();
        boolean flag = true;
        System.out.println(homework_code+" be sent"+course_code);
        LTeacher teacher_send_homework_student = new  LTeacher();
        flag = teacher_send_homework_student.send_homework_to_stu(homework_code,course_code);
        return "redirect:query_course_homework_session";
    }

}

