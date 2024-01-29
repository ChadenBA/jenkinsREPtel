# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Compile the Java code
RUN javac -d ./target/classes ./src/Main.java

# Define the command to run your application
CMD ["java", "-cp", "./target/classes", "Main"]
