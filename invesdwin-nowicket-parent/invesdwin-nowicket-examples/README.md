## Website

This is the code for our documentation website, which is available here: **http://invesdwin.de/nowicket/**

Also here you find the code to a few MVP (Minimum Viable Product) websites that were created with this framework. They are also linked to in our documentation website from the top menu bar.

## Launching

The examples are built with spring-boot due to convenience, but you can use any platform you like with this framework. Most examples can be started on your own computer by launching the corresponding Main.java in the project from inside an IDE or by building the maven projects and running the standalone jar in the target folder. In either case, you can access the website at [http://localhost:8080](http://localhost:8080) after the application has started.

The war example project though is done the classic way with a web.xml and deployment in a servlet webserver. Run this example with `mvn tomcat7:run` and access the website at [http://localhost:8080](http://localhost:8080) afterwards.
