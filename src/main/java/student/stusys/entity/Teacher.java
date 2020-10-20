package student.stusys.entity;

public class Teacher {
    private String tea_code;
    private String tea_name;
    private String tea_pwd;

    public Teacher(String tea_code, String tea_name, String tea_pwd) {
        this.tea_code = tea_code;
        this.tea_name = tea_name;
        this.tea_pwd = tea_pwd;

    }
    public Teacher() {
    }
    public String getTea_code() {
        return tea_code;
    }

    public void setTea_code(String tea_code) {
        this.tea_code = tea_code;
    }

    public String getTea_name() {
        return tea_name;
    }

    public void setTea_name(String tea_name) {
        this.tea_name = tea_name;
    }

    public String getTea_pwd() {
        return tea_pwd;
    }

    public void setTea_pwd(String tea_pwd) {
        this.tea_pwd = tea_pwd;
    }
}
