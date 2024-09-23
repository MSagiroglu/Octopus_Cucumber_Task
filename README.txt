Test Framework Overview

This test framework has been developed using a Lenovo Z580 with an Intel Core i5 processor
running Microsoft 10. It has been verified to function seamlessly in an environment compatible with Chrome
version 129.0.6668.58 (x64)  and Edge version 129.0.2792.52 (64-bit), ensuring reliable test execution.

Project Description

This README document serves as a comprehensive guide to the Java Selenium Cucumber test project(JDK 17). The project primarily
focuses on UI testing for three core functionalities: Login and Signup and Add Screen.

Test Features

The test features are organized into separate files:

Octopus_01_SignIn.feature (Location: src/test/resources/features/Octopus_01_SignIn.feature)
Octopus_02_SignUp.feature (Location: src/test/resources/features/src/test/resources/features/Octopus_02_SignUp.feature)
Octopus_03_AddScreen.feature (Location: src/test/resources/features/Octopus_03_AddScreen.feature)

Test Execution

Test execution is orchestrated through the Runner class, which handles the Login , Signup and Screen Keyword features.
You can find the Runner class at the following location:

Runner (Location: src/test/java/runners/Runner.java)
For selective execution or test configuration adjustments, utilize specific tags. Simply modify the 'tags' parameter
accordingly to execute specific scenarios or features.

Screenshots

During test execution, screenshots are captured and stored in the projectImages directory.

Browser Configuration

To alter the browser configuration for testing purposes, modify the browser name in the configuration.properties file.
Following any changes, ensure to rebuild the project prior to executing the tests as per the instructions provided above.

Should you have any inquiries or encounter difficulties, please do not hesitate to reach out for assistance.


<dependencies>
        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.18.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.15.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.15.0</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.2.5</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.github.javafaker/javafaker -->
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
        </dependency>


    </dependencies>
    <build>
        <plugins>
            <!-- paralel test icin  -->

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                    <testFailureIgnore>true</testFailureIgnore>   <!-- senaryo fail olursa calismaya devam et  -->
                    <skipTests>false</skipTests>
                    <includes>
                        <include>**/runners/*Runner*.java</include>       <!-- bu yolun dogru oldugunu kontrol et  -->
                    </includes>
                </configuration>
            </plugin>

           <!-- paralel test icin  -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                    <parallel>methods</parallel>
                    <threadCount>4</threadCount> <!--aynı anda 2 browser çalıştırılacağı anlamına gelir. -->
                    <perCoreThreadCount>false</perCoreThreadCount>
                    <forkCount>4</forkCount> <!-- iki ayrı işlemi başlatır ve her biri farklı bir test paketi çalıştırır. Bu, testlerin daha hızlı tamamlanmasını sağlar. Ancak, bu ayarın artırılması, sistemin kaynaklarını tüketebilir ve performans sorunlarına neden olabilir. -->
                    <reuseForks>true</reuseForks> <!--Maven önceki testlerde oluşturulmuş işlemleri yeniden kullanır -->
                    <argLine>-Duser.language=en</argLine>
                    <argLine>-Dfile.encoding=UTF-8</argLine>
                    <includes>
                        <include>**/runners/*Paralell*.java</include>
                    </includes>
                    <testFailureIgnore>true</testFailureIgnore> <!--testin başarısız olmasına rağmen diğer testlere devam eder ve sonunda tüm testleri çalıştırır. -->
                </configuration>
            </plugin>


            <!-- cucumber report icin  -->

            <plugin>
                 <groupId>net.masterthought</groupId>
                 <artifactId>maven-cucumber-reporting</artifactId>
                 <version>5.0.0</version>
                 <executions>
                     <execution>
                         <id>execution</id>
                         <phase>verify</phase>
                         <goals>
                             <goal>generate</goal>
                         </goals>
                         <configuration>
                             <projectName>cucumber-framework</projectName>
                             <outputDirectory>${project.build.directory}</outputDirectory>
                             <inputDirectory>${project.build.directory}</inputDirectory>
                             <jsonFiles>
                                 <param>**/json-reports/*.json</param>
                             </jsonFiles><classificationFiles>->
                             <param>sample.properties</param>
                             <param>other.properties</param>
                         </classificationFiles>
                         </configuration>
                     </execution>
                 </executions>
             </plugin>


        </plugins>
    </build>

