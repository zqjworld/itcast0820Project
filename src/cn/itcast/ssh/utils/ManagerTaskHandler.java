package cn.itcast.ssh.utils;

import javax.servlet.ServletContext;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.catalina.core.ApplicationContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.accessibility.internal.resources.accessibility;
import com.sun.glass.ui.Application;

import cn.itcast.ssh.domain.Employee;
import cn.itcast.ssh.service.IEmployeeService;

/**
 * 员工经理任务分配
 *
 */
@SuppressWarnings("serial")
public class ManagerTaskHandler implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		//从session中获取当前用户
//		Employee employee=SessionContext.get();
//		delegateTask.setAssignee(employee.getManager().getName());
		//将当前用户的领导放置到办理人中
		
		/**从新查询当前用户，在获取当前用户对应的领导*/
		Employee employee=SessionContext.get();
		//当前用户
		String name=employee.getName();
		//使用当前用户查询用户的详细信息
		WebApplicationContext wc=WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		IEmployeeService employeeService=(IEmployeeService) wc.getBean("employeeService");
		Employee emp=employeeService.findEmployeeByName(name);
		delegateTask.setAssignee(emp.getManager().getName());
	}

}
