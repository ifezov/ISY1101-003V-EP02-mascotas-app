# ── Etapa 1: Build ────────────────────────────────────────────────────────────
FROM gradle:8.13.0-jdk21 AS build

WORKDIR /app

COPY build.gradle settings.gradle gradle.properties ./

RUN gradle dependencies --no-daemon || true

COPY src ./src

RUN gradle clean bootJar -x test --no-daemon

# ── Etapa 2: Runtime ──────────────────────────────────────────────────────────
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

RUN groupadd --system appgroup && useradd --system --gid appgroup appuser

COPY --from=build /app/build/libs/mascotas-app-1.0.0.jar app.jar

RUN chown appuser:appgroup app.jar

USER appuser

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
