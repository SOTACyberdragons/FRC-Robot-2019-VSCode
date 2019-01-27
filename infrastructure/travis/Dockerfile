FROM ubuntu:18.04

ENV WPILIB_VERSION=2019.2.1

RUN apt-get update -q && apt-get install -qy wget

RUN wget -q https://github.com/wpilibsuite/allwpilib/releases/download/v$WPILIB_VERSION/WPILib_Linux-$WPILIB_VERSION.tar.gz \
    && mkdir -p /root/frc2019/ && tar -xzf WPILib_Linux-$WPILIB_VERSION.tar.gz -C /root/frc2019/ \
    && rm WPILib_Linux-$WPILIB_VERSION.tar.gz

ADD . /app

ENV JAVA_HOME=/root/frc2019/jdk

WORKDIR /app

RUN PATH=$JAVA_HOME/bin:$PATH ./gradlew buildEnvironment

