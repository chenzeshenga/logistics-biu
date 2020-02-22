
```
mvn install:install-file -Dfile=MWSOrders_2013-09-01_v2018-10-31.jar  -DgroupId=com.amazonservices.mws  -DartifactId=MWSOrders -Dversion=1.0  -Dpackaging=jar
mvn install:install-file -Dfile=MWSClientJavaRuntime-1.0.jar  -DgroupId=com.amazonservices.mws  -DartifactId=MWSClientJavaRuntime -Dversion=1.0  -Dpackaging=jar
```

pom.xml 新增依赖
```
        <dependency>
            <groupId>com.amazonservices.mws</groupId>
            <artifactId>MWSClientJavaRuntime</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>com.amazonservices.mws</groupId>
            <artifactId>MWSOrders</artifactId>
            <version>1.0</version>
        </dependency>
```
