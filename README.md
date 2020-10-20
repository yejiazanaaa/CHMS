# CHMS
<h1>校园作业平台管理系统</h1><br><br>
<h2>系统架构:</h2><br>本平台所采用是SSM框架，由Spring、Spring MVC、MyBatis三个开源框架整合而成，常作为数据源较简单的web项目的框架。<br><br>
<h2>系统工作原理:</h2><br>1.客户端发送请求到DispacherServlet（分发器）。<br>
2.由DispacherServlet控制器查询HanderMapping，找到处理请求的Controller（控制器）。<br>
3.Controller调用业务逻辑处理后，返回ModelAndView。<br>
4.DispacherSerclet查询视图解析器，找到ModelAndView指定的视图。<br>
5.视图负责将结果显示到客户端<br><br>
<h2>功能模块设计:</h2><br><br>
<h3>角色：</h3><br>
<h4>1.学生端：</h4><br>
1.1查看课程<br>
1.2查看作业<br>
1.3提交作业<br>
1.4上传文件<br>
1.5查看作业打分情况<br>
<h4>2.教师端</h4><br>
2.1查看课程以及新建课程<br>
2.2查看课程相应学生<br>
2.3查看以及新建作业<br>
2.4给作业分<br>
<h2>业务逻辑层设计:</h2><br><br>
校园作业管理平台”主要是包括登录、教师端、添加课程以及课程学生信息、作业信息等教师模块，查看课程查看作业等学生模块。因此，可以将系统主要划分为前台和后台，细分教师端和学生端。<br>
(1)教师端模块设计:登陆通过输入工号密码实现、
创建课程和作业、
往课程中添加学生信息、
往作业中同步学生信息、
给予作业成绩和期末成绩、
下载学生上传的作业<br>
(2)学生模块设计:登陆通过输入学号密码实现、查看课程以及课程作业、上传文件、查看作业成绩及期末成绩<br> 
<h2>系统数据模型设计:</h2><br><br>
数据库管理系统的后台，存储着所有的信息，数据库在一个管理系统中占有非常重要的地位，数据库结构设计的好坏将直接对应用系统的效率以及实现的效率产生影响。合理的数据库结构可以提高存储的效率，保证数据的完整性和一致性。只有系统管理员才有对数据库系统直接的操作权。<br>
数据库表结构设计：在系统的数据库设计中，首先要创建系统数据库，然后在数据库中创建需要的表和字段。在这个数据库管理系统中要建立7张数据表。这8张数据表的字段说明，分别为教师表(teacher_info)、学生表(student_info)、教师课程表(teacher_course_info)、课程作业表(course_homework)、作业表(homework_info)、课程学生表(course_student)、学生作业表(homework_student)。

