package basicSyntax.component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class IpBlockingFilter extends OncePerRequestFilter {

    private final Set<String> blockedIps = Set.of(
            "192.168.1.100",
            "10.0.0.25",
            "203.0.113.50"
    );

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException{

        String clientIp = request.getRemoteAddr();

        if(blockedIps.contains(clientIp)){
            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Access Denied"
            );
            return;
        }

        filterChain.doFilter(request,response);
    }
}
