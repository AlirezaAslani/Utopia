package utopia.webservice.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Alireza.Aslani <a href='alireza.a.eng@gmail.com'> alireza </a>
 */
@WebFilter("/rest/*")
public class SecurityFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityFilter.class);
    /**api_key by request parameter*/
    private static final String API_KEY_PARAM = "api_key";
    /**X-API-Key as request Header*/
    private static final String HEADER_NAME_API_KEY = "X-API-Key";
    
    /**whilelistIps*/
    private static final List<String>  WHITELISTED_IPS = new ArrayList<>();
    
    /**value - you can define either in config or read from db*/
    private static final String API_KEY_VALUE = "sd3209Sdkl2DF3dfzsDGEsZ8476";
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        LOG.info("init Security filter");
        
        /**initialize ip address either or you get fromconfig*/
        WHITELISTED_IPS.add("127.0.0.1");
        WHITELISTED_IPS.add("192.168.0.1");
        WHITELISTED_IPS.add("192.168.3.14");
        //WHITELISTED_IPS.add("0:0:0:0:0:0:0:1");
        //WHITELISTED_IPS.add("10.1.2.29");
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        final String requestUri = request.getRequestURI();
        
        LOG.debug(">> Request method {} - URI : {}",request.getMethod(), requestUri);
        
        LOG.debug(String.format(">> Client's IP address: %s, api_key: %s, X-API-Key: %s", request.getRemoteAddr(), request.getParameter(API_KEY_PARAM),
                request.getHeader(HEADER_NAME_API_KEY)));
        System.out.println(request.getRemoteAddr());
        System.out.println(request.getHeader(HEADER_NAME_API_KEY));
        System.out.println(request.getParameter(API_KEY_PARAM));
        System.out.println(verifyIpAddress(request.getRemoteAddr()));
        //check request api_key present ?
        if ( (verifyApiKey(request) || verifyIpAddress(request.getRemoteAddr()))) {
            LOG.error("Either the client's IP address is not allowed, API key is invalid");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Either the client's IP address is not allowed, API key is invalid");
            return;
        }
        
        chain.doFilter(req, resp);
    }
    /**
     * verify api key either request param or request Header
     * @param request
     * @return
     */
    private boolean verifyApiKey(HttpServletRequest request) {
        return API_KEY_VALUE.equals(request.getHeader(HEADER_NAME_API_KEY))
                || API_KEY_VALUE.equals(request.getParameter(API_KEY_PARAM));
    }
    
    
    /**
     * verify api key either request param or request Header
     * @param request
     * @return
     */
    private boolean verifyIpAddress(String ipAddress) {
        return !WHITELISTED_IPS.contains(ipAddress);
    }
    
    @Override
    public void destroy() {}
}
