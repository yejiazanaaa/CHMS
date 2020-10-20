package student.stusys.entity;

public class Course_Homework{
    private String homework_code;//作业号主键
    private String homework_course_code;//课程作业号
    private String homework_date;//
    private String homework_title;


    public String getHomework_code() {
        return homework_code;
    }

    public void setHomework_code(String homework_code) {
        this.homework_code = homework_code;
    }

    public String getHomework_course_code() {
        return homework_course_code;
    }
    public String getCourse_code() {
        return homework_course_code;
    }

    public void setCourse_code(String course_code) {
        this.homework_course_code = course_code;
    }



    public void setHomework_course_code(String homework_course_code) {
        this.homework_course_code = homework_course_code;
    }

    public String getHomework_date() {
        return homework_date;
    }

    public void setHomework_date(String homework_date) {
        this.homework_date = homework_date;
    }

    public String getHomework_title() {
        return homework_title;
    }

    public void setHomework_title(String homework_title) {
        this.homework_title = homework_title;
    }
}
