FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
# For JDK 11
#FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/addon
WORKDIR /home/app/addon

RUN native-image --verbose --no-server \
                           --no-fallback \
                           --initialize-at-build-time=org.postgresql.Driver \
                           --initialize-at-build-time=org.postgresql.util.SharedTimer \
                           -cp build/libs/addon-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/addon/addon /app/addon
ENTRYPOINT ["/app/addon"]
