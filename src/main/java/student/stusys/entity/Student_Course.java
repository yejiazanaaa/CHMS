package student.stusys.entity;

public class Student_Course {
    private String stu_course_code; //学生课程号主键
    private String stu_code; //学号
    private String qm_grade; //期末成绩
    private String course_code;//学生选课的课程号
    private String course_name;
    private String course_time;
    private String course_class;
    private String course_year;
    private String stu_name;//学生姓名
    private String stu_pro; //学生的专业
    private String stu_class;//学生班级

    public String getCourse_class() {
        return course_class;
    }

    public void setCourse_class(String course_class) {
        this.course_class = course_class;
    }

    public String getCourse_time() {
        return course_time;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }

    public String getStu_course_code() {
        return stu_course_code;
    }

    public void setStu_course_code(String stu_course_code) {
        this.stu_course_code = stu_course_code;
    }

    public String getStu_code() {
        return stu_code;
    }

    public void setStu_code(String stu_code) {
        this.stu_code = stu_code;
    }

    public String getQm_grade() {
        return qm_grade;
    }

    public void setQm_grade(String qm_grade) {
        this.qm_grade = qm_grade;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_pro() {
        return stu_pro;
    }

    public void setStu_pro(String stu_pro) {
        this.stu_pro = stu_pro;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_year() {
        return course_year;
    }

    public void setCourse_year(String course_year) {
        this.course_year = course_year;
    }
}
