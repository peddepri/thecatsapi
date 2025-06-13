FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Cria um argumento para o nome da aplicação
ARG JAR_FILE=target/*.jar

# Copia o jar compilado da etapa anterior
COPY target/catsapi-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Define o comando padrão para rodar a aplicação
CMD ["java", "-jar", "app.jar"]