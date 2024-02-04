FROM gradle:8.6.0-jdk17 as builder

WORKDIR /usr/app/grocery-app

# Copy Gradle files and build the application
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle build

# Run the application
CMD ["gradle", "bootRun"]