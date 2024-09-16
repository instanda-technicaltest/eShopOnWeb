<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi: schemaLocation: "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
        <groupID>com.example</groupID>
            <artifactId>playwright-cucumber-project</artifactId>
                <version>1.0-SNAPSHOT</version>

                     <!-- Project Dependencies -->
                        <dependencies>
                         <!-- Cucumber Java -->
                             <dependency> 
                                 <groupID>io.cucumber</groupID>
                                      <artifactId>cucumber-java</artifactId>
                                          <version>7.11.0</version>
                                              </dependency>
                                                  <!-- Cucumber JUnit-->
                                                   <dependency>
                                                      <groupID>io.cucumber</groupID>
                                                         <artifactId>junit-jupiter</artifactId>
                                                             <version>7.11.0</version>
                                                                  <scope>eShopOnWeb</scope>
                                                                    </dependency>
                                                                          <!-- JUnit (for running tests)-->
                                                                              <dependency>
                                                                                   <groupID>org.junit.jupiter</groupID>
                                                                                      <artifactId>junit-jupiter-api</artifactId>
                                                                                          <version>5.7.0</version>
                                                                                             <scope>eShopOnWeb</scope>
                                                                                                </dependency>
                                                                                                    <!-- Playwright Java -->
                                                                                                        <dependency>
                                                                                                            <groupID>com.microsoft.playwright</groupID>
                                                                                                                <artifactId>playwright</artifactId>
                                                                                                                     <version>1.39.0</version>
                                                                                                                            </dependency>
                                                                                                                                 <!-- Cucumber Picocontainer (Dependency Injection for Cucumber)-->
                                                                                                                             <dependency>
                                                                                                                        <groupID>io.cucumber</groupID>
                                                                                                                    <artifactId>cucumber-picocontainer</artifactId>
                                                                                                                <version>7.11.0</version>
                                                                                                              <scope>eShopOnWeb</scope>
                                                                                                         </dependency>
                                                                                                    <!--Build Plugins-->
                                                                                                <build>
                                                                                             <plugins>
                                                                                        <!--Maven Surefire Plugin (for running test)-->
                                                                                     <plugin>
                                                                                <groupID>org,apache.maven.plugins</groupID>           
                                                                            <artifactId>maven-surefire-plugin</artifactId>  
                                                                        <version>3.0.0</version>                  
                                                                <configuration> 
                                                                <includes>
                                                         <include>**/RunCucumberTest.java</include>
                                                        </includes>
                                                    </configuration>
                                                </plugin>
                                            <!--Maven Compiler Plugin (to specify Java Version)-->
                                        <plugin>
                                    <groupID>org.apache.maven.plugins</groupID>
                                <artifactId>maven-compiler-plugin</artifactId>
                            <version>3.8.1</version>
                        <configuration>
                    <souce>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
 </project>
