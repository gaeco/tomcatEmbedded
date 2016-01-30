package gaeco.tomcat.embedded;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import java.io.File;

/**
 * Created by gaedong on 1/31/16.
 */
public class TomcatServer {
    private Tomcat tomcat = null;
    private int port = 8080;
    private String contextPath = null;
    private String docBase = null;
    private Context rootContext = null;

    public TomcatServer(){
        init();
    }

    public TomcatServer(int port, String contextPath, String docBase){
        this.port = port;
        this.contextPath = contextPath;
        this.docBase = docBase;
        init();
    }

    private void init(){
        tomcat = new Tomcat();
        tomcat.setPort(port);
        if(contextPath == null){
            contextPath = "";
        }
        if(docBase == null){
            File base = new File(System.getProperty("java.io.tmpdir"));
            docBase = base.getAbsolutePath();
        }
        rootContext = tomcat.addContext(contextPath, docBase);
    }

    public void addServlet(String servletName, String uri, HttpServlet servlet){
        Tomcat.addServlet(this.rootContext, servletName, servlet);
        rootContext.addServletMapping(uri, servletName);
    }

    public void startServer() throws LifecycleException {
        tomcat.start();
        tomcat.getServer().await();
    }

    public void stopServer() throws LifecycleException {
        tomcat.stop();
    }

    public static void main(String[] args) throws Exception {
        File base = new File(System.getProperty("java.io.tmpdir"));
        //TomcatServer tomcatServer = new TomcatServer(8080,"/timeApp",base.getAbsolutePath());
        TomcatServer tomcatServer = new TomcatServer();
        tomcatServer.addServlet("timeServlet", "/now", new TimePrintServlet());
        tomcatServer.startServer();
    }
}
