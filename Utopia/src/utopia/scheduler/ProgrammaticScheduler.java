package utopia.scheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import utopia.enumeration.ConfigEnum;
import utopia.service.ConfigService;
import utopia.service.GroupService;
import utopia.service.IConfigService;
import utopia.service.IGroupService;
import utopia.service.IUserGroupService;
import utopia.service.IUserService;
import utopia.service.UserGroupService;
import utopia.service.UserService;
import utopia.session.IConfigImplLocal;
import utopia.session.IGroupImplLocal;
import utopia.session.IUserGroupImplLocal;
import utopia.session.IUserImplLocal;
import utopia.utils.TimerUtils;


@Singleton
@LocalBean
public class ProgrammaticScheduler {
	
	@Resource
	private TimerService timerService;
	
	@EJB(name = "user")
	private IUserImplLocal userFacade;
	
	@EJB(name = "group")
	private IGroupImplLocal groupFacade;
	
	@EJB(name = "config")
	private IConfigImplLocal configFacade;

	@EJB(name = "ug")
	private IUserGroupImplLocal ugFacade;
	

	
	IConfigService configService;
	IUserService userService;
	IGroupService groupService;
	IUserGroupService ugService;
	TimerUtils timerUtils;

	
	
	@PostConstruct
	public void init() {
		try {
			timerUtils = new TimerUtils();
			HashMap<String, String> configMap = configService.getConfigMap();
			timerService.createCalendarTimer(timerUtils.getScheduleExpression(configMap.get(ConfigEnum.SCHEDULER.toString())), timerUtils.getTimerConfig("dev_weblogic_info"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Timeout
	@AccessTimeout(value = 60, unit = TimeUnit.MINUTES)
	public void execute(Timer timer) {
		
		System.out.println("Timer Service : " + timer.getInfo());
		System.out.println("Execution Time : " + new Date());
		System.out.println("____________________________________________");

	}

}
