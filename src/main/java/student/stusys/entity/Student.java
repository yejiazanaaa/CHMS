package student.stusys.entity;

public class Student {
    private String stu_code;
    private String stu_name;
    private String stu_pwd;
    private String stu_pro;
    private String stu_class;



    public Student(String stu_code, String stu_name, String stu_pwd,String stu_pro,String stu_class) {
        this.stu_code = stu_code;
        this.stu_name = stu_name;
        this.stu_pwd = stu_pwd;
        this.stu_pro = stu_pro;
        this.stu_class = stu_class;


    }
    public Student() {
    }
    public String getStu_code() {
        return stu_code;
    }

    public void setStu_code(String stu_code) {
        this.stu_code = stu_code;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_pwd() {
        return stu_pwd;
    }

    public void setStu_pwd(String stu_pwd) {
        this.stu_pwd = stu_pwd;
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
}



