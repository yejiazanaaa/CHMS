package student.stusys.controller;

import com.sun.org.apache.xpath.internal.operations.Lt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import student.stusys.entity.Homework_Student;
import student.stusys.entity.Student;
import student.stusys.entity.Student_Course;
import student.stusys.entity.Teacher_Course;
import student.stusys.logic.LStudent;
import student.stusys.logic.LTeacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//实现分页与查询课程学生信息
@Controller
//@RestController
//@RequestMapping("/student")
public class CPage {
    int page1;
    int sum;
    String key="";
    @RequestMapping(value = "search_course_student_info",method = RequestMethod.POST)
    public String search_course_student_info(HttpServletRequest request,Model model,HttpSession session){
        page1=0;
        String course_code = session.getAttribute("course_code").toString();
        List<Student_Course> course_stus = null;
        LTeacher query_course_stu = new LTeacher();
        key = request.getParameter("key");
        if(key.equals("")){
            return  "redirect:teacher_query_course_student_info";
        }
       //String currentpage = request.getParameter("currentpage");
        String sqlTxt = "select sc.stu_course_code, sc.stu_code, sc.qm_grade, s.stu_name, s.stu_class, s.stu_pro from course_student_info sc inner join student_info s on sc.stu_code=s.stu_code  ";
        sqlTxt = sqlTxt + " where sc.course_code = '" + course_code + "'";
        String sqlTxt1 = sqlTxt+" and stu_name like '%"+key+"%'";
        System.out.println("分页"+sqlTxt1);
        LStudent student = new LStudent();
        try {
            course_stus = query_course_stu.queryStudent_Course(sqlTxt1);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        if(course_stus.size()%5==0){
            sum = course_stus.size()/5;
        }else{
            sum =course_stus.size()/5+1;
            System.out.println("查询页数"+sum);
        }
       /*try {//将String转化为int
            page1 = Integer.parseInt(currentpage);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }*/
        if(page1<1){
            page1=1;
        }
        else if(page1>sum){
            page1=sum;
        }else{
        }

        String sqlTxt2 =sqlTxt1+ " limit "+(page1-1)*5+", 5";
        System.out.println(sqlTxt2);
        try {
            course_stus = query_course_stu.queryStudent_Course(sqlTxt2);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("student_courseList", course_stus);

        String sqlTxt3 = "select * from teacher_course_info ";
        sqlTxt3 = sqlTxt3 + " where course_code ='" + course_code + "'";
        System.out.println(sqlTxt3);
        LTeacher teacher_course = new LTeacher();
        List<Teacher_Course> teacher_courseList =  teacher_course.queryCourseInfo(sqlTxt3);
        //teacher_courseList把数据加入到model里面
        model.addAttribute("teacher_courseList", teacher_courseList);

        return "query_course_student";
    }
    //下一页
    @RequestMapping(value = "add_page_search_tqcstu",method = RequestMethod.POST)
    public String add_page_search_tqcstu(HttpServletRequest request,Model model,HttpSession session){
        String course_code = session.getAttribute("course_code").toString();
        String sqlTxt = "select sc.stu_course_code, sc.stu_code, sc.qm_grade, s.stu_name, s.stu_class, s.stu_pro from course_student_info sc inner join student_info s on sc.stu_code=s.stu_code  ";
        sqlTxt = sqlTxt + " where sc.course_code = '" + course_code + "'";
        String sqlTxt1 = sqlTxt+" and stu_name like '%"+key+"%'";
        try {//将String转化为int
            page1 += 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(page1<1){
            page1=1;
        }
        else if(page1>sum){
            page1=sum;
        }else{
        }

        List<Student_Course> course_stus = null;
        LTeacher addstu = new LTeacher();
        String sqlTxt2 = sqlTxt1+"limit "+(page1-1)*5+", 5";
        System.out.println(sqlTxt2);
        try {
            course_stus = addstu.queryStudent_Course(sqlTxt2);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("student_courseList", course_stus);

        String sqlTxt3 = "select * from teacher_course_info ";
        sqlTxt3 = sqlTxt3 + " where course_code ='" + course_code + "'";
        System.out.println(sqlTxt3);
        LTeacher teacher_course = new LTeacher();
        List<Teacher_Course> teacher_courseList =  teacher_course.queryCourseInfo(sqlTxt3);
        //teacher_courseList把数据加入到model里面
        model.addAttribute("teacher_courseList", teacher_courseList);

        return "query_course_student";
    }
    //上一页
    @RequestMapping(value = "pre_page_search_tqcstu",method = RequestMethod.POST)
    public String pre_page_search_tqcstu(HttpServletRequest request,Model model,HttpSession session){
        String course_code = session.getAttribute("course_code").toString();
        String sqlTxt = "select sc.stu_course_code, sc.stu_code, sc.qm_grade, s.stu_name, s.stu_class, s.stu_pro from course_student_info sc inner join student_info s on sc.stu_code=s.stu_code  ";
        sqlTxt = sqlTxt + " where sc.course_code = '" + course_code + "'";
        String sqlTxt1 = sqlTxt+" and stu_name like '%"+key+"%'";
        try {
            page1 -= 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(page1<1){
            page1=1;
        }
        else if(page1>sum){
            page1=sum;
        }else{
        }

        List<Student_Course> course_stus = null;
        LTeacher prestu = new LTeacher();
        String sqlTxt2 = sqlTxt1+" limit "+(page1-1)*5+", 5";
        System.out.println(sqlTxt2);
        try {
            course_stus = prestu.queryStudent_Course(sqlTxt2);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("student_courseList", course_stus);

        String sqlTxt3 = "select * from teacher_course_info ";
        sqlTxt3 = sqlTxt3 + " where course_code ='" + course_code + "'";
        //System.out.println(sqlTxt3);
        LTeacher teacher_course = new LTeacher();
        List<Teacher_Course> teacher_courseList =  teacher_course.queryCourseInfo(sqlTxt3);
        //teacher_courseList把数据加入到model里面
        model.addAttribute("teacher_courseList", teacher_courseList);

        return "query_course_student";
    }





    /******************************/
    @RequestMapping(value = "teacher_query_course_student_info",method = RequestMethod.GET)
    public String teacher_query_course_student(HttpServletRequest request,Model model,HttpSession session){

        List<Student_Course> course_stus = null;
        String course_code = session.getAttribute("course_code").toString();
        System.out.println(course_code);
        //key = request.getParameter("key");
        String currentpage = request.getParameter("currentpage");
        //String sqlTxt1 = "select * from student_info where stu_name like '%"+key+"%'";
        String sqlTxt = "select sc.stu_course_code, sc.stu_code, sc.qm_grade, s.stu_name, s.stu_class, s.stu_pro from course_student_info sc inner join student_info s on sc.stu_code=s.stu_code  ";
        sqlTxt = sqlTxt + " where sc.course_code = '" + course_code + "'";
        LTeacher query_course_stu = new LTeacher();
        try {
            course_stus = query_course_stu.queryStudent_Course(sqlTxt);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        if( course_stus.size()%5==0){
            sum =  course_stus.size()/5;
        }else{
            sum = course_stus.size()/5+1;
        }
       /*try {//将String转化为int
            page1 = Integer.parseInt(currentpage);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }*/
        if(page1<1){
            page1=1;
        }
        else if(page1>sum){
            page1=sum;
        }else{
        }
        String sqlTxt2 = sqlTxt+ " limit "+(page1-1)*5+", 5";
        System.out.println(sqlTxt2);
        try {
            course_stus = query_course_stu.queryStudent_Course(sqlTxt2);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        model.addAttribute("student_courseList", course_stus);

        String sqlTxt3 = "select * from teacher_course_info ";
        sqlTxt3 = sqlTxt3 + " where course_code ='" + course_code + "'";
        System.out.println(sqlTxt3);
        LTeacher teacher_course = new LTeacher();
        List<Teacher_Course> teacher_courseList =  teacher_course.queryCourseInfo(sqlTxt3);
        //teacher_courseList把数据加入到model里面
        model.addAttribute("teacher_courseList", teacher_courseList);

        return "query_course_student";
    }
    //下一页
    @RequestMapping(value = "add_page_tqcstu",method = RequestMethod.POST)
    public String add_page_tqcstu(HttpServletRequest request,Model model,HttpSession session){
        String course_code = session.getAttribute("course_code").toString();
        String sqlTxt = "select sc.stu_course_code, sc.stu_code, sc.qm_grade, s.stu_name, s.stu_class, s.stu_pro from course_student_info sc inner join student_info s on sc.stu_code=s.stu_code  ";
        sqlTxt = sqlTxt + " where sc.course_code = '" + course_code + "'";
        try {//将String转化为int
            page1 += 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(page1<1){
            page1=1;
        }
        else if(page1>sum){
            page1=sum;
        }else{
        }
        List<Student_Course> course_stus = null;
            LTeacher addstu = new LTeacher();
        String sqlTxt2 = sqlTxt+"limit "+(page1-1)*5+", 5";
        System.out.println(sqlTxt2);
        try {
            course_stus = addstu.queryStudent_Course(sqlTxt2);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("student_courseList", course_stus);

        String sqlTxt3 = "select * from teacher_course_info ";
        sqlTxt3 = sqlTxt3 + " where course_code ='" + course_code + "'";
        System.out.println(sqlTxt3);
        LTeacher teacher_course = new LTeacher();
        List<Teacher_Course> teacher_courseList =  teacher_course.queryCourseInfo(sqlTxt3);
        //teacher_courseList把数据加入到model里面
        model.addAttribute("teacher_courseList", teacher_courseList);

        return "query_course_student";
    }
//上一页
    @RequestMapping(value = "pre_page_tqcstu",method = RequestMethod.POST)
    public String pre_page_tqcstu(HttpServletRequest request,Model model,HttpSession session){

        String course_code = session.getAttribute("course_code").toString();
        String sqlTxt = "select sc.stu_course_code, sc.stu_code, sc.qm_grade, s.stu_name, s.stu_class, s.stu_pro from course_student_info sc inner join student_info s on sc.stu_code=s.stu_code  ";
        sqlTxt = sqlTxt + " where sc.course_code = '" + course_code + "'";
        try {
            page1 -= 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(page1<1){
            page1=1;
        }
        else if(page1>sum){
            page1=sum;
        }else{
        }

        List<Student_Course> course_stus = null;
        LTeacher addstu = new LTeacher();
        String sqlTxt2 = sqlTxt+" limit "+(page1-1)*5+", 5";
        System.out.println(sqlTxt2);
        try {
            course_stus = addstu.queryStudent_Course(sqlTxt2);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("student_courseList", course_stus);

        String sqlTxt3 = "select * from teacher_course_info ";
        sqlTxt3 = sqlTxt3 + " where course_code ='" + course_code + "'";
        System.out.println(sqlTxt3);
        LTeacher teacher_course = new LTeacher();
        List<Teacher_Course> teacher_courseList =  teacher_course.queryCourseInfo(sqlTxt3);
        //teacher_courseList把数据加入到model里面
        model.addAttribute("teacher_courseList", teacher_courseList);

        return "query_course_student";
    }


}
