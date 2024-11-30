# 使用OpenJDK 17作为基础镜像
FROM openjdk:17-jdk-slim

# 设置容器内的工作目录
WORKDIR /app

# 将构建的Spring Boot JAR包复制到容器内
COPY wordbook-api-1.0.jar /app/wordbook-api-1.0.jar

# 暴露应用所使用的端口
EXPOSE 8080

# 运行Spring Boot应用
ENTRYPOINT ["java", "-jar", "/app/wordbook-api-1.0.jar"]