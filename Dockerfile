FROM gradle:6.5.1-jdk11 AS BUILD

WORKDIR /src/
COPY . /src/

RUN ./gradlew build

FROM adoptopenjdk/openjdk11-openj9:ubi-minimal
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV AB_ENABLED=jmx_exporter
RUN mkdir /opt/shareclasses
RUN chmod a+rwx -R /opt/shareclasses
RUN mkdir /opt/app
COPY --from=BUILD /src/build/lib/* /opt/app/lib/
COPY --from=BUILD /src/build/*-runner.jar /opt/app/app.jar
CMD ["java", "-Xmx128m", "-XX:+IdleTuningGcOnIdle", "-Xtune:virtualized", "-Xscmx128m", "-Xscmaxaot100m", "-Xshareclasses:cacheDir=/opt/shareclasses", "-jar", "/opt/app/app.jar"]
