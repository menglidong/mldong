# 指定基础镜像
FROM openjdk:8u212-jdk-alpine
# 维护者信息
MAINTAINER mldong <524719755@qq.com>
# 创建应用目录
RUN mkdir -p /app && mkdir -p /app/config
# 进入工作目录
WORKDIR /app
# 复制jar
COPY app.jar .
# 配置配置文件
COPY config/* .
# EXPOSE 映射端口
EXPOSE 8080
# CMD 运行以下命令(如果yaml文件定义了command会被覆盖)
CMD ["/bin/sh","-c","set -e && java -jar app.jar --spring.profiles.active=dev --server.port=8080"]
