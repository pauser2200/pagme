# --- Estágio 1: Build (Compilação) ---
# Usamos uma imagem com Maven e JDK 21 para compilar o projeto
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia todo o código fonte para dentro do container
COPY . .

# Compila o projeto e gera o .jar (Pula os testes para agilizar o deploy)
RUN mvn clean package -DskipTests

# --- Estágio 2: Runtime (Execução) ---
# Usamos uma imagem leve do JDK 21 apenas para rodar a aplicação
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copia apenas o arquivo .jar gerado no estágio anterior
# O asterisco *.jar pega o nome do arquivo independente da versão
COPY --from=build /app/target/*.jar app.jar

# Informa ao Render que a porta 8080 será usada
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]