FROM oracle/graalvm-ce:20.0.0-java8

RUN yum install -y zip unzip
RUN gu install native-image

WORKDIR /src/
COPY . /src/

RUN ./gradlew buildNative

WORKDIR /work/
RUN cp /src/build/*-runner /work/application

RUN chmod 775 /work /work/application

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
