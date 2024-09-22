Test Framework Overview

This test framework has been developed using a MacBook Pro (Retina, 13-inch, Mid 2014) with an Intel Core i7 processor
running macOS Monterey 12.7.4. It has been verified to function seamlessly in an environment compatible with Chrome
version 123.0.6312.87 (x86_64) and Firefox version 124.0.1 (64-bit), ensuring reliable test execution.

Project Description

This README document serves as a comprehensive guide to the Java Selenium Cucumber test project. The project primarily
focuses on UI testing for two core functionalities: N11Login and N11SearchKeyword.

Test Features

The test features are organized into separate files:

LoginN11.feature (Location: src/test/resources/features/N11Login.feature)
SearchKeywordN11.feature (Location: src/test/resources/features/N11SearchKeyword.feature)

Test Execution

Test execution is orchestrated through the Runner class, which handles both the Login and Search Keyword features.
You can find the Runner class at the following location:

Runner (Location: src/test/java/runners/Runner.java)
For selective execution or test configuration adjustments, utilize specific tags. Simply modify the 'tags' parameter
accordingly to execute specific scenarios or features.

Screenshots

During test execution, screenshots are captured and stored in the test-output/Screenshots directory.

Login Error Log

Any errors related to login attempts are logged in the loginerror.txt file, located in the main project folder.

Browser Configuration

To alter the browser configuration for testing purposes, modify the browser name in the configuration.properties file.
Following any changes, ensure to rebuild the project prior to executing the tests as per the instructions provided above.

Should you have any inquiries or encounter difficulties, please do not hesitate to reach out for assistance.


ependencies>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.13.0</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.28</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.13.0</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.11.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.4.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.github.javafaker/javafaker -->
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.3.1</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.5.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.testng/testng -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.8.0</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi  excell için-->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.2.3</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml excell için-->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.2.3</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.2</version>
        </dependency>
        <!-- java objecti json objesine dönüştürme ihtiyacı var olduğunda kullanılan Jackon dependency'i eklememiz gerekiyor. -->
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M1</version>
                <configuration>
                    <parallel>methods</parallel>
                    <threadCount>2</threadCount> <!--aynı anda 2 browser çalıştırılacağı anlamına gelir. -->
                    <perCoreThreadCount>false</perCoreThreadCount>
                    <forkCount>2</forkCount> <!-- iki ayrı işlemi başlatır ve her biri farklı bir test paketi çalıştırır. Bu, testlerin daha hızlı tamamlanmasını sağlar. Ancak, bu ayarın artırılması, sistemin kaynaklarını tüketebilir ve performans sorunlarına neden olabilir. -->
                    <reuseForks>true</reuseForks> <!--Maven önceki testlerde oluşturulmuş işlemleri yeniden kullanır -->
                    <includes>
                        <include>**/runners/*Paralell*.java</include>
                    </includes>
                    <testFailureIgnore>true</testFailureIgnore> <!--testin başarısız olmasına rağmen diğer testlere devam eder ve sonunda tüm testleri çalıştırır. -->
                </configuration>
            </plugin>

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

