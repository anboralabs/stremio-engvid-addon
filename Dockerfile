FROM oracle/graalvm-ce:20.0.0-java8 AS build

RUN yum install -y zip unzip
RUN gu install native-image

WORKDIR /src/
COPY . /src/

RUN ./gradlew buildNative

WORKDIR /work/
RUN cp /src/build/*-runner /work/application

RUN chmod 775 /work /work/application

FROM registry.access.redhat.com/ubi8/ubi-minimal:8.1
WORKDIR /work/
COPY --from=build  /work/application /work/application

# set up permissions for user `1001`
RUN chmod 775 /work /work/application \
  && chown -R 1001 /work \
  && chmod -R "g+rwX" /work \
  && chown -R 1001:root /work

USER 1001

CMD ["./application", "-Xmx100m", "-Xmn70m", "-Dquarkus.http.host=0.0.0.0"]
