## Website

This is the code for our documentation website, which is available here: **http://invesdwin.de/nowicket/**

Also here you find the code to a few MVP (Minimum Viable Product) and example websites that were created with this framework. Some of them are also linked to in our documentation website from the top menu bar.

## Launching

### Spring-Boot
The documentation examples (`invesdwin-nowicket-examples-guide`, `invesdwin-nowicket-examples-mvp-bsgcoach`, `invesdwin-nowicket-examples-mvp-eva`) are built with [spring-boot](https://projects.spring.io/spring-boot/) due to convenience, but you can use any platform you like with this framework. Most examples can be started on your own computer by launching the corresponding Main.java in the project from inside an IDE or by building the maven projects and running the standalone jar in the target folder. In either case, you can access the website at [http://localhost:8080](http://localhost:8080) after the application has started.

### Classic-War
The war example project (`invesdwin-nowicket-examples-war`) is done the classic way with a web.xml and deployment in a servlet webserver. Run this example with `mvn tomcat7:run` and access the website at [http://localhost:8080](http://localhost:8080) afterwards. The same applies to the [Apache Isis](https://isis.apache.org) example project (`invesdwin-nowicket-examples-isis`).

### Invesdwin-Context
The remotelist example project (`granatasoft-remotelist-parent`) is built with [invesdwin-context-client-wicket](https://github.com/subes/invesdwin-context-client)  (which uses `invesdwin-nowicket`) to demonstrate usage with the [invesdwin-context](https://github.com/subes/invesdwin-context) platform. It can be launched via the `com.granatasoft.remotelist.run.InteractiveWebserver` test case inside `granatasoft-remotelist-website` from inside an IDE or by building the maven projects and running the standalone distribution jar in the target folder of `granatasoft-remotelist-website-dist`. Access the website at [http://localhost:8080](http://localhost:8080) afterwards.
