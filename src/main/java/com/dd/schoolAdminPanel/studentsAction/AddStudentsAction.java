package com.dd.schoolAdminPanel.studentsAction;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dd.schoolAdminPanel.constants.LangUtils;
import com.dd.schoolAdminPanel.constants.SessionConstants;
import com.dd.schoolAdminPanel.studentsBean.Students;
import com.dd.schoolAdminPanel.studentsService.StudentsService;
import com.dd.schoolAdminPanel.studentsService.StudentsServiceInterface;
import com.dd.schoolAdminPanel.userManagementBean.UserDetails;

import net.sf.json.JSONObject;

@WebServlet("/AddStudents")
@MultipartConfig
public class AddStudentsAction extends HttpServlet {

    private Log log = LogFactory.getLog(AddStudentsAction.class);
    private static final long serialVersionUID = 1;
    public HttpSession session;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Entering into the method doPost in class AddStudentsAction");
        Students students = new Students();
        PrintWriter out = response.getWriter();
        JSONObject addStudentsResponse = new JSONObject();
        StudentsServiceInterface studentsServiceInterface = new StudentsService();
        LangUtils langUtils = new LangUtils();
        UserDetails userTOSession = (UserDetails) this.getValueFromSessionVariable(request, SessionConstants.SESSION_VITAL_USER);
        try {

            String studentId = request.getParameter("studentId");
            String studentName = request.getParameter("studentName");
            String fathersName = request.getParameter("fathersName");
            String mothersName = request.getParameter("mothersName");
            String emergencyContactNumber = request.getParameter("emergencyContactNumber");
            String bloodGroup = request.getParameter("bloodGroup");
            String age = request.getParameter("age");
            String classId = request.getParameter("classId");
            String className = request.getParameter("className");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");

            if (langUtils.validateIsNotEmpty(studentId)) {
                students.setStudentsId(langUtils.assignNumber(studentId));
            }
            if (langUtils.validateIsNotEmpty(studentName)) {
                students.setStudentName(langUtils.assignString(studentName));
            }
            if (langUtils.validateIsNotEmpty(fathersName)) {
                students.setFathersName(langUtils.assignString(fathersName));
            }
            if (langUtils.validateIsNotEmpty(mothersName)) {
                students.setMothersName(langUtils.assignString(mothersName));
            }
            if (langUtils.validateIsNotEmpty(emergencyContactNumber)) {
                students.setEmergencyContactNumber(langUtils.assignString(emergencyContactNumber));
            }
            if (langUtils.validateIsNotEmpty(bloodGroup)) {
                students.setBloodGroup(langUtils.assignString(bloodGroup));
            }
            if (langUtils.validateIsNotEmpty(gender)) {
                students.setGender(langUtils.assignString(gender));
            }
            if (langUtils.validateIsNotEmpty(age)) {
                students.setAge(langUtils.assignNumber(age));
            }
            if (langUtils.validateIsNotEmpty(classId)) {
                students.setClassId(langUtils.assignNumber(classId));
            }
            if (langUtils.validateIsNotEmpty(className)) {
                students.setClassName(langUtils.assignString(className));
            }
            if (langUtils.validateIsNotEmpty(address)) {
                students.setAddress(langUtils.assignString(address));
            }

            students.setCreatedBy(userTOSession.getUserName());
            students.setUpdatedBy(userTOSession.getUserName());


            studentsServiceInterface.addStudents(students);

            addStudentsResponse.accumulate("success", true);
            addStudentsResponse.accumulate("message", "Successfully Students Values Inserted");
            response.setContentType("text/json; charset=UTF-8");
            out.print(addStudentsResponse);


        } catch (Exception e) {
            log.error("Error while executing the method doPost in class AddStudents " + e.getMessage());
            if(e.getMessage().contains("ConstraintViolationException")) {
            	addStudentsResponse.accumulate("failure", true);
            	addStudentsResponse.accumulate("message", "Student Already Exists, Kindly try to add different students");
        	}else {
        		addStudentsResponse.accumulate("failure", true);
        		addStudentsResponse.accumulate("message", e.getMessage());
        	}
            response.setContentType("text/json; charset=UTF-8");
            out.print(addStudentsResponse);
        } finally {
            log.info("Exiting the method doPost in class UserManagementAction");
        }

    }

    public Object getValueFromSessionVariable(HttpServletRequest request, String key) {
        this.session = request.getSession(true);
        return this.session.getAttribute(key);
    }




}