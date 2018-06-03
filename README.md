- Build -
1) Download the source code
2) Execute Maven commands in the path at which the pom.xml is located. e.g.
	> cd /workspace/simsoc
	> mvn clean
	> mvn package
3) After Maven package, a file named simsoc-assembly.tar.gz is created under /workspace/simsoc/target/ directory


- Deploy -
1) Target environment should have JRE-1.8 in place
2) Put simsoc-assembly.tar.gz to any directory where you expect the application to be deployed
3) Extract the tar.gz file with below command
	> tar -zxvf simsoc-assembly.tar.gz
4) The application's folder 'simsoc' will be created. Inside the folder, there will be 'bin', 'config' folders and an executable .jar file


- Configure -
1) Configure the deployed 'simsoc/config/logback.xml', especially on the path where you expect the log to be written
	<property name="LOG_PATH" value="/tmp/log" />
2) Surely, you can configure whatever you want inside 'simsoc/config/logback.xml'
3) You can also configure 'simsoc/config/application.properties' if you are familiar with Spring Boot and would like to override some of the default properties of Spring Boot

*** IMPORTANT *** Please do keep this property logging.config=${root_dir}/config/logback.xml, as it specifies the location of logging configuration file for this application ****


- Run -
1) To start up the application, execute the script under 'simsoc/bin/startup.sh'
2) Open browser and input http://localhost:8080/ in the address bar
3) To shut down the application, execute the script under 'simsoc/bin/shutdown.sh'


