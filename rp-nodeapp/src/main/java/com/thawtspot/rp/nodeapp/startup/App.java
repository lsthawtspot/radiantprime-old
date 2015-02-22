package com.thawtspot.rp.nodeapp.startup;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Startup the Node
 */
public class App {
	
	public static void main(String[] args) throws Exception {
		
		String tempDirName = ServerProperties.getString("app.war_temp_dir");
		Integer httpPort = ServerProperties.getInteger("app.port");
		if (httpPort == null || tempDirName == null) {
			System.out.println("Failed to get server properties: WAR directory and HTTP Port.");
			return;
		}
		
        Server server = new Server();
        int port = httpPort;
        
        if (args.length > 0) {
        	try {
				port = Integer.parseInt(args[0]);
                System.out.println("Overriding: binding to port: " + port);
			} 
        	catch (Exception e) {
				System.out.println("Illegal port: " + args[0] +". Using default port: " + port);
                System.out.println("arguments: ");
                for (String arg : args) {
                    System.out.println(arg);
                }
				e.printStackTrace(System.err);
			}
        }

        ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);
        
        //TODO: add a SSL connector
		server.setConnectors(new Connector[]{connector});
        
        WebAppContext context = new WebAppContext();
         
		ProtectionDomain protectionDomain = App.class.getProtectionDomain();
		URL location = protectionDomain.getCodeSource().getLocation();
		String warLocation = location.toExternalForm();
		context.setWar(warLocation);
		context.setContextPath("/");
		
		File tempDir = new File(tempDirName);
		if (!tempDir.exists()) tempDir.mkdirs();
		
		if(tempDir.canWrite()){
			System.out.println("Using " + tempDirName + " to extract the war file");
			context.setTempDirectory(tempDir);
		}
		else{
			System.out.println(tempDirName + ": either does not exist or is not writable. Using system temp dir to extract the war.");
		}
        
		context.setErrorHandler(new JettyErrorHandler());
		context.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
		
        server.setHandler(context);

        try{
            server.start();
            server.join();
        }
        catch (Throwable t){
        	System.out.println("Couldn't start Jetty server.");
            t.printStackTrace(System.err);
        }        

	}

}
